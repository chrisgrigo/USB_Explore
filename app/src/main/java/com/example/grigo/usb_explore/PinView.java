package com.example.grigo.usb_explore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.grigo.usb_explore.R.drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Extension of subsampling-scale-image-view by davemorrissey
 * - extended by Jake Holding on 15/02/2018
 */

public class PinView extends SubsamplingScaleImageView {

    private final Paint paint = new Paint();
    private final Paint paintLine = new Paint();
    private DashPathEffect dashPathEffect = new DashPathEffect(new float[] {10,10}, 5);
    private ArrayList<PointF> pinF = new ArrayList<>();
    private ArrayList<String> pinNames = new ArrayList<>();
    private Bitmap pin;
    private Path mPath = new Path();

    private PointF nodeFrom;
    private boolean exception;

    public PinView(Context context) {
        this(context, null);
    }

    public PinView(Context context, AttributeSet attr) {
        super(context, attr);
        initialise();
    }

    public boolean setPin(PointF pin, String pinName) {
        System.out.println("PIN ATTEMPTED TO BE DROPPED");
        if (!pinF.contains(pin)) {
            if (pinNames.contains(pinName)) {
                removePin(pinName);
            }
            this.pinF.add(pin);
            pinNames.add(pinName);
            initialise();
            invalidate();
            return true;
        }
        return false;
    }

    public void removePin(String pinName) {
        if (pinNames.contains(pinName)) {
            pinF.remove(pinNames.indexOf(pinName));
            pinNames.remove(pinName);
            invalidate();
        }
    }

    private void initialise() {
        pin = BitmapFactory.decodeResource(this.getResources(), drawable.pin);
        float density = getResources().getDisplayMetrics().densityDpi;
        float width = (density/1000f) * pin.getWidth();
        float height = (density/1000f) * pin.getHeight();
        pin = Bitmap.createScaledBitmap(pin, (int)width, (int)height, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Don't draw pin before image is ready so it doesn't move around during setup.
        if (!isReady()) {
            return;
        }

        paint.setAntiAlias(true);
        paintLine.setAntiAlias(true);

        if (PinViewFragment.colourBlindMode) {
            paintLine.setColor(Color.parseColor("#F70A32"));
        } else {
            paintLine.setColor(Color.parseColor("#0088FD"));
        }

        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(15);
        paintLine.setPathEffect(dashPathEffect);

        if (PinViewFragment.navigationModeEnabled) {

            Node fromNode = MapActivity.floorList.get(PinViewFragment.pinFromFloorNum).getRoomsList().get(PinViewFragment.pinFromIdx).getNode();
            Node toNode = MapActivity.floorList.get(PinViewFragment.pinToFloorNum).getRoomsList().get(PinViewFragment.pinToIdx).getNode();

            List<Node> path = MapActivity.roomMap.Path(fromNode, toNode);
            Node floorChange = null;

            for (int i = 0; i < path.size(); i++) {
                if (path.get(i).getFloor() == PinViewFragment.floorNum) {
                    floorChange = path.get(i);
                    break;
                }
            }

            if (PinViewFragment.pinFromFloorNum == PinViewFragment.floorNum) {
                nodeFrom = sourceToViewCoord((float) fromNode.getLocation().getX(), (float) fromNode.getLocation().getY());
            } else {
                if (floorChange != null) {
                    nodeFrom = sourceToViewCoord((float) floorChange.getLocation().getX(), (float) floorChange.getLocation().getY());
                }
                checkExceptions();
            }

            if (floorChange != null) {
                mPath.moveTo(nodeFrom.x, nodeFrom.y);
            }

            for (Node n : path) {
                addNode(n);
            }

            drawPath(canvas);
        }
        drawPins(canvas);
    }

    public void addNode(Node node) {
        if (node.getFloor() == PinViewFragment.floorNum) {
            mPath.lineTo((sourceToViewCoord((float) node.getLocation().getX(),
                    (float) node.getLocation().getY())).x,
                    (sourceToViewCoord((float) node.getLocation().getX(),
                            (float) node.getLocation().getY())).y);
        }
    }

    public void checkExceptions() {
        if (PinViewFragment.pinFromFloorNum <= 4 & PinViewFragment.pinToFloorNum > 4
                || PinViewFragment.pinFromFloorNum > 4 & PinViewFragment.pinToFloorNum <= 4) {

            if (PinViewFragment.floorNum == 4 && PinViewFragment.pinFromFloorNum != 4 && PinViewFragment.pinToFloorNum != 4) {
                System.out.println("FLOOR4"); // debugging for floor 4
                exception = true;

            }
            if (PinViewFragment.floorNum == 5 && PinViewFragment.pinFromFloorNum != 5 && PinViewFragment.pinToFloorNum != 5) {
                System.out.println("FLOOR5"); // debugging for floor 5
                exception = true;

            }
        }
    }

    public void drawPath(Canvas canvas) {
        if ((PinViewFragment.pinFromFloorNum == PinViewFragment.floorNum || PinViewFragment.pinToFloorNum == PinViewFragment.floorNum || exception)) {
            canvas.drawPath(mPath, paintLine);
            mPath.reset();
            exception = false;
        }
    }

    public void drawPins(Canvas canvas) {
        for (PointF point : pinF) {
            if (point != null && pin != null) {
                PointF vPin = sourceToViewCoord(point);
                float vX = vPin.x - (pin.getWidth() / 2);
                float vY = vPin.y - pin.getHeight();
                canvas.drawBitmap(pin, vX, vY, paint);
            }
        }
    }
}