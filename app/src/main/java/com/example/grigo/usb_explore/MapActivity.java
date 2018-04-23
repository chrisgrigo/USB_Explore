package com.example.grigo.usb_explore;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.grigo.usb_explore.R.id;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.example.grigo.usb_explore.R.layout.abstract_fragments_activity;

/**
 * Created by Jake on 27/02/2018.
 */

public class MapActivity extends AbstractFragmentsActivity {

    public static List<Floor> floorList = new ArrayList<>();
    public static ListGraph roomMap = new ListGraph();

    public MapActivity() {
        super(abstract_fragments_activity);
    }

    @Override
    protected void onPageChanged(int page) {
        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(id.frame, PinViewFragment.class.newInstance())
                    .commit();
        } catch (Exception e) {
            Log.d("CAUTION", e.getMessage());
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        floorSetup();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomePageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }


    public void floorSetup() {
        floorList.add(new Floor(0, "floor0.jpg"));
        floorList.add(new Floor(1, "floor1.jpg"));
        floorList.add(new Floor(2, "floor2.jpg"));
        floorList.add(new Floor(3, "floor3.jpg"));
        floorList.add(new Floor(4, "floor4.jpg"));
        floorList.add(new Floor(5, "floor5.jpg"));
        floorList.add(new Floor(6, "floor6.jpg"));

        nodeSetup();
    }

    public void nodeSetup() {

        // floor setup

        Node entrance = new Node(new Coord(2378, 1914), 0);
        floorList.get(0).addRoom(new Room("Entrance", new Coord(2215, 1805), new Coord(2752, 2325), new Coord(2485, 2067), entrance));
        Node reception0 = new Node(new Coord(3002, 1441), 0);
        floorList.get(0).addRoom(new Room("Reception", new Coord(2862, 1336), new Coord(3168, 1412), new Coord(3011, 1340), reception0));
        Node snackBar = new Node(new Coord(3277, 926), 0);
        floorList.get(0).addRoom(new Room("Snack Bar", new Coord(3161, 718), new Coord(3460, 898), new Coord(3301, 713), snackBar));

        Node lt1006 = new Node(new Coord(1224, 1832), 1);
        floorList.get(1).addRoom(new Room("Lecture Theatre 1.006", new Coord(480, 1859), new Coord(1315, 2827), new Coord(836, 2125), lt1006));
        Node r1019 = new Node(new Coord(1082, 1151), 1);
        floorList.get(1).addRoom(new Room("1.019", new Coord(480, 1052), new Coord(1058, 1246), new Coord(764, 1092), r1019));
        Node r1025 = new Node(new Coord(621, 533), 1);
        floorList.get(1).addRoom(new Room("1.025", new Coord(480, 200), new Coord(740, 500), new Coord(613, 285), r1025));
        Node r1025a = new Node(new Coord(880, 533), 1);
        floorList.get(1).addRoom(new Room("1.025A", new Coord(753, 200), new Coord(1032, 500), new Coord(886, 285), r1025a));
        Node r1025b = new Node(new Coord(1150, 533), 1);
        floorList.get(1).addRoom(new Room("1.025B", new Coord(1047, 200), new Coord(1257, 500), new Coord(1145, 285), r1025b));
        Node r1043 = new Node(new Coord(2547, 908), 1);
        floorList.get(1).addRoom(new Room("Open Lab 1.043", new Coord(1812, 200), new Coord(4074, 875), new Coord(2533, 482), r1043));

        Node r2004 = new Node(new Coord(1005, 2996), 2);
        floorList.get(2).addRoom(new Room("2.004", new Coord(741, 2843), new Coord(998, 3190), new Coord(868, 2982), r2004));
        Node r2005 = new Node(new Coord(1468, 2165), 2);
        floorList.get(2).addRoom(new Room("2.005", new Coord(1468, 2098), new Coord(1703, 2261), new Coord(1586, 2120), r2005));
        Node r2006 = new Node(new Coord(1472, 2026), 2);
        floorList.get(2).addRoom(new Room("2.006", new Coord(1468, 1914), new Coord(1707, 2080), new Coord(1586, 1967), r2006));
        Node lt2006 = new Node(new Coord(1308, 2026), 2);
        floorList.get(2).addRoom(new Room("Lecture Theatre 2.006", new Coord(480, 1859), new Coord(1315, 2827), new Coord(836, 2125), lt2006));
        Node reception2 = new Node(new Coord(1307, 1437), 2);
        floorList.get(2).addRoom(new Room("Reception", new Coord(768, 1240), new Coord(1274, 1615), new Coord(1002, 1371), reception2));
        Node r2017 = new Node(new Coord(793, 1555), 2);
        floorList.get(2).addRoom(new Room("2.017", new Coord(480, 1463), new Coord(759, 1615), new Coord(610, 1499), r2017));
        Node r2018 = new Node(new Coord(793, 1365), 2);
        floorList.get(2).addRoom(new Room("2.018", new Coord(480, 1240), new Coord(759, 1453), new Coord(615, 1305), r2018));
        Node r2019 = new Node(new Coord(918, 632), 2);
        floorList.get(2).addRoom(new Room("2.019", new Coord(480, 583), new Coord(1269, 1228), new Coord(736, 984), r2019));
        Node r2022 = new Node(new Coord(1086, 605), 2);
        floorList.get(2).addRoom(new Room("2.022", new Coord(480, 203), new Coord(1262, 573), new Coord(805, 356), r2022));
        Node r2026 = new Node(new Coord(1434, 822), 2);
        floorList.get(2).addRoom(new Room("2.026", new Coord(1470, 753), new Coord(1742, 870), new Coord(1605, 761), r2026));
        Node r2027 = new Node(new Coord(1434, 687), 2);
        floorList.get(2).addRoom(new Room("2.027", new Coord(1475, 622), new Coord(1746, 739), new Coord(1601, 635), r2027));
        Node r2038 = new Node(new Coord(2624, 564), 2);
        floorList.get(2).addRoom(new Room("2.038", new Coord(1817, 407), new Coord(3374, 505), new Coord(2656, 420), r2038));
        Node r2039 = new Node(new Coord(1889, 429), 2);
        floorList.get(2).addRoom(new Room("2.039", new Coord(1812, 200), new Coord(1979, 400), new Coord(1889, 200), r2039));
        Node r2040 = new Node(new Coord(2061, 429), 2);
        floorList.get(2).addRoom(new Room("2.040", new Coord(1988, 200), new Coord(2155, 400), new Coord(2061, 200), r2040));
        Node r2041 = new Node(new Coord(2232, 429), 2);
        floorList.get(2).addRoom(new Room("2.041", new Coord(2165, 200), new Coord(2327, 400), new Coord(2232, 200), r2041));
        Node r2042 = new Node(new Coord(2409, 429), 2);
        floorList.get(2).addRoom(new Room("2.042", new Coord(2338, 200), new Coord(2505, 400), new Coord(2409, 200), r2042));
        Node r2043 = new Node(new Coord(2585, 429), 2);
        floorList.get(2).addRoom(new Room("2.043", new Coord(2513, 200), new Coord(2676, 400), new Coord(2585, 200), r2043));
        Node r2044 = new Node(new Coord(2751, 429), 2);
        floorList.get(2).addRoom(new Room("2.044", new Coord(2688, 200), new Coord(2850, 400), new Coord(2751, 200), r2044));
        Node r2045 = new Node(new Coord(2928, 429), 2);
        floorList.get(2).addRoom(new Room("2.045", new Coord(2859, 200), new Coord(3022, 400), new Coord(2928, 200), r2045));
        Node r2046 = new Node(new Coord(3108, 429), 2);
        floorList.get(2).addRoom(new Room("2.046", new Coord(3036, 200), new Coord(3203, 400), new Coord(3108, 200), r2046));
        Node r2047 = new Node(new Coord(3275, 429), 2);
        floorList.get(2).addRoom(new Room("2.047", new Coord(3212, 200), new Coord(3375, 400), new Coord(3275, 200), r2047));
        Node r2048 = new Node(new Coord(2624, 564), 2);
        floorList.get(2).addRoom(new Room("2.048", new Coord(2692, 517), new Coord(4503, 901), new Coord(3317, 607), r2048));
        Node r2060 = new Node(new Coord(3874, 979), 2);
        floorList.get(2).addRoom(new Room("2.060", new Coord(3524, 928), new Coord(3890, 1257), new Coord(3704, 1040), r2060));

        Node r3004 = new Node(new Coord(1005, 2996), 3);
        floorList.get(3).addRoom(new Room("3.004", new Coord(742, 2852), new Coord(1006, 3315), new Coord(855, 2967), r3004));
        Node r3005 = new Node(new Coord(1302, 2314), 3);
        floorList.get(3).addRoom(new Room("3.005", new Coord(478, 2190), new Coord(1261, 2845), new Coord(864, 2255), r3005));
        Node r3008 = new Node(new Coord(1472, 2026), 3);
        floorList.get(3).addRoom(new Room("3.008", new Coord(1471, 1814), new Coord(1715, 2022), new Coord(1589, 1900), r3008));
        Node r3009 = new Node(new Coord(1468, 2165), 3);
        floorList.get(3).addRoom(new Room("3.009", new Coord(1471, 2026), new Coord(1715, 2275), new Coord(1583, 2049), r3009));
        Node r3015 = new Node(new Coord(1302, 1828), 3);
        floorList.get(3).addRoom(new Room("3.015", new Coord(482, 769), new Coord(1268, 1965), new Coord(907, 909), r3015));
        Node r3018 = new Node(new Coord(1302, 663), 3);
        floorList.get(3).addRoom(new Room("3.018", new Coord(471, 205), new Coord(1270, 767), new Coord(906, 254), r3018));
        Node r3031 = new Node(new Coord(2071, 520), 3);
        floorList.get(3).addRoom(new Room("3.031", new Coord(2689, 205), new Coord(4120, 513), new Coord(2978, 360), r3031));
        Node r3032 = new Node(new Coord(2243, 520), 3);
        floorList.get(3).addRoom(new Room("3.032", new Coord(2180, 515), new Coord(2980, 767), new Coord(2551, 573), r3032));
        Node r3034 = new Node(new Coord(1877, 435), 3);
        floorList.get(3).addRoom(new Room("3.034", new Coord(1802, 200), new Coord(1985, 400), new Coord(1877, 210), r3034));
        Node r3035 = new Node(new Coord(2071, 435), 3);
        floorList.get(3).addRoom(new Room("3.035", new Coord(1990, 200), new Coord(2160, 400), new Coord(2071, 210), r3035));
        Node r3036 = new Node(new Coord(2243, 435), 3);
        floorList.get(3).addRoom(new Room("3.036", new Coord(2161, 200), new Coord(2333, 400), new Coord(2243, 210), r3036));
        Node r3037 = new Node(new Coord(2414, 435), 3);
        floorList.get(3).addRoom(new Room("3.037", new Coord(2338, 200), new Coord(2512, 400), new Coord(2414, 210), r3037));
        Node r3038 = new Node(new Coord(2596, 435), 3);
        floorList.get(3).addRoom(new Room("3.038", new Coord(2514, 200), new Coord(2686, 400), new Coord(2596, 210), r3038));

        Node r4004 = new Node(new Coord(995, 3015), 4);
        floorList.get(4).addRoom(new Room("4.004", new Coord(741, 2852), new Coord(994, 3313), new Coord(855, 3006), r4004));
        Node r4005 = new Node(new Coord(1302, 2314), 4);
        floorList.get(4).addRoom(new Room("4.005", new Coord(488, 2189), new Coord(1266, 2852), new Coord(872, 2400), r4005));
        Node r4016 = new Node(new Coord(1302, 1828), 4);
        floorList.get(4).addRoom(new Room("4.016", new Coord(750, 1318), new Coord(1266, 1963), new Coord(1044, 1598), r4016));
        Node r4018 = new Node(new Coord(972, 1273), 4);
        floorList.get(4).addRoom(new Room("4.018", new Coord(483, 982), new Coord(939, 1307), new Coord(722, 1064), r4018));
        Node r4020 = new Node(new Coord(1016, 1259), 4);
        floorList.get(4).addRoom(new Room("4.020", new Coord(943, 985), new Coord(1097, 1228), new Coord(1016, 990), r4020));
        Node r4021 = new Node(new Coord(1192, 1259), 4);
        floorList.get(4).addRoom(new Room("4.021", new Coord(1106, 985), new Coord(1265, 1228), new Coord(1192, 990), r4021));
        Node r4022 = new Node(new Coord(1308, 587), 4);
        floorList.get(4).addRoom(new Room("4.022", new Coord(477, 206), new Coord(1269, 971), new Coord(786, 480), r4022));
        Node terrace = new Node(new Coord(1434, 1412), 4);
        floorList.get(4).addRoom(new Room("Terrace", new Coord(1470, 1241), new Coord(1723, 2802), new Coord(1574, 1561), terrace));
        Node r4037 = new Node(new Coord(2070, 522), 4);
        floorList.get(4).addRoom(new Room("4.037", new Coord(2684, 202), new Coord(3385, 758), new Coord(3028, 431), r4037));
        Node r4038 = new Node(new Coord(1894, 434), 4);
        floorList.get(4).addRoom(new Room("4.038", new Coord(1813, 200), new Coord(1984, 400), new Coord(1894, 210), r4038));
        Node r4039 = new Node(new Coord(2070, 434), 4);
        floorList.get(4).addRoom(new Room("4.039", new Coord(1989, 200), new Coord(2160, 400), new Coord(2070, 210), r4039));
        Node r4040 = new Node(new Coord(2251, 434), 4);
        floorList.get(4).addRoom(new Room("4.040", new Coord(2165, 200), new Coord(2332, 400), new Coord(2251, 210), r4040));
        Node r4041 = new Node(new Coord(2427, 434), 4);
        floorList.get(4).addRoom(new Room("4.041", new Coord(2341, 200), new Coord(2510, 400), new Coord(2427, 210), r4041));
        Node r4042 = new Node(new Coord(2600, 434), 4);
        floorList.get(4).addRoom(new Room("4.042", new Coord(2522, 200), new Coord(2689, 400), new Coord(2600, 210), r4042));
        Node r4053 = new Node(new Coord(3745, 825), 4);
        floorList.get(4).addRoom(new Room("4.053", new Coord(3392, 200), new Coord(4520, 1075), new Coord(4004, 610), r4053));

        Node r5012 = new Node(new Coord(760, 660), 5);
        floorList.get(5).addRoom(new Room("5.012", new Coord(674, 640), new Coord(854, 900), new Coord(760, 635), r5012));
        Node r5013 = new Node(new Coord(925, 660), 5);
        floorList.get(5).addRoom(new Room("5.013", new Coord(860, 640), new Coord(1035, 900), new Coord(935, 635), r5013));
        Node r5014 = new Node(new Coord(1121, 660), 5);
        floorList.get(5).addRoom(new Room("5.014", new Coord(1039, 640), new Coord(1221, 900), new Coord(1121, 635), r5014));
        Node r5015 = new Node(new Coord(1301, 660), 5);
        floorList.get(5).addRoom(new Room("5.015", new Coord(1222, 640), new Coord(1401, 900), new Coord(1301, 635), r5015));
        Node r5016 = new Node(new Coord(1487, 660), 5);
        floorList.get(5).addRoom(new Room("5.016", new Coord(1405, 640), new Coord(1586, 900), new Coord(1487, 635), r5016));
        Node r5017 = new Node(new Coord(1668, 660), 5);
        floorList.get(5).addRoom(new Room("5.017", new Coord(1587, 640), new Coord(1762, 900), new Coord(1668, 635), r5017));
        Node r5018 = new Node(new Coord(1882, 456), 5);
        floorList.get(5).addRoom(new Room("5.018", new Coord(1805, 200), new Coord(1977, 425), new Coord(1882, 190), r5018));
        Node r5019 = new Node(new Coord(2063, 456), 5);
        floorList.get(5).addRoom(new Room("5.019", new Coord(1980, 200), new Coord(2154, 425), new Coord(2063, 190), r5019));
        Node r5020 = new Node(new Coord(2230, 456), 5);
        floorList.get(5).addRoom(new Room("5.020", new Coord(2156, 200), new Coord(2325, 425), new Coord(2230, 190), r5020));
        Node r5021 = new Node(new Coord(2410, 456), 5);
        floorList.get(5).addRoom(new Room("5.021", new Coord(2330, 200), new Coord(2510, 425), new Coord(2410, 190), r5021));
        Node r5022 = new Node(new Coord(2745, 600), 5);
        floorList.get(5).addRoom(new Room("5.022", new Coord(1763, 430), new Coord(2720, 900), new Coord(2235, 605), r5022));
        Node r5024 = new Node(new Coord(2821, 550), 5);
        floorList.get(5).addRoom(new Room("5.024", new Coord(2512, 200), new Coord(3382, 510), new Coord(2926, 325), r5024));
        Node r5026 = new Node(new Coord(4035, 370), 5);
        floorList.get(5).addRoom(new Room("5.026", new Coord(3994, 200), new Coord(4170, 352), new Coord(4060, 250), r5026));
        Node r5027 = new Node(new Coord(4130, 472), 5);
        floorList.get(5).addRoom(new Room("5.027", new Coord(4086, 354), new Coord(4298, 479), new Coord(4212, 370), r5027));
        Node r5028 = new Node(new Coord(4251, 583), 5);
        floorList.get(5).addRoom(new Room("5.028", new Coord(4216, 480), new Coord(4416, 601), new Coord(4347, 482), r5028));
        Node r5029 = new Node(new Coord(4261, 656), 5);
        floorList.get(5).addRoom(new Room("5.029", new Coord(4261, 602), new Coord(4520, 803), new Coord(4342, 665), r5029));
        Node r5030 = new Node(new Coord(3998, 781), 5);
        floorList.get(5).addRoom(new Room("5.030", new Coord(3970, 618), new Coord(4260, 1010), new Coord(4049, 777), r5030));
        Node r5031 = new Node(new Coord(3773, 866), 5);
        floorList.get(5).addRoom(new Room("5.031", new Coord(3792, 816), new Coord(3969, 1106), new Coord(3907, 925), r5031));
        Node r5031a = new Node(new Coord(3718, 913), 5);
        floorList.get(5).addRoom(new Room("5.031A", new Coord(3489, 902), new Coord(3791, 1273), new Coord(3679, 1048), r5031a));

        Node r6013 = new Node(new Coord(801, 646), 6);
        floorList.get(6).addRoom(new Room("6.013", new Coord(747, 675), new Coord(868, 890), new Coord(801, 670), r6013));
        Node r6014 = new Node(new Coord(931, 646), 6);
        floorList.get(6).addRoom(new Room("6.014", new Coord(869, 675), new Coord(1004, 890), new Coord(931, 670), r6014));
        Node r6015 = new Node(new Coord(1139, 646), 6);
        floorList.get(6).addRoom(new Room("6.015", new Coord(1005, 675), new Coord(1294, 890), new Coord(1139, 750), r6015));
        Node r6016 = new Node(new Coord(1357, 646), 6);
        floorList.get(6).addRoom(new Room("6.016", new Coord(1295, 675), new Coord(1425, 890), new Coord(1357, 670), r6016));
        Node r6017 = new Node(new Coord(1492, 646), 6);
        floorList.get(6).addRoom(new Room("6.017", new Coord(1426, 675), new Coord(1565, 890), new Coord(1492, 670), r6017));
        Node r6018 = new Node(new Coord(1626, 646), 6);
        floorList.get(6).addRoom(new Room("6.018", new Coord(1566, 675), new Coord(1698, 890), new Coord(1626, 670), r6018));
        Node r6019 = new Node(new Coord(1910, 646), 6);
        floorList.get(6).addRoom(new Room("6.019", new Coord(1699, 675), new Coord(2175, 890), new Coord(1910, 750), r6019));
        Node r6020 = new Node(new Coord(2235, 646), 6);
        floorList.get(6).addRoom(new Room("6.020", new Coord(2176, 675), new Coord(2311, 890), new Coord(2235, 670), r6020));
        Node r6021 = new Node(new Coord(2379, 646), 6);
        floorList.get(6).addRoom(new Room("6.021", new Coord(2312, 675), new Coord(2447, 890), new Coord(2379, 670), r6021));
        Node r6022 = new Node(new Coord(2515, 646), 6);
        floorList.get(6).addRoom(new Room("6.022", new Coord(2448, 675), new Coord(2582, 890), new Coord(2515, 670), r6022));
        Node r6023 = new Node(new Coord(2641, 646), 6);
        floorList.get(6).addRoom(new Room("6.023", new Coord(2583, 675), new Coord(2717, 890), new Coord(2641, 670), r6023));
        Node r6024 = new Node(new Coord(2781, 646), 6);
        floorList.get(6).addRoom(new Room("6.024", new Coord(2718, 675), new Coord(2858, 890), new Coord(2781, 670), r6024));
        Node r6033 = new Node(new Coord(4035, 370), 6);
        floorList.get(6).addRoom(new Room("6.033", new Coord(3994, 200), new Coord(4170, 352), new Coord(4060, 250), r6033));
        Node r6034 = new Node(new Coord(4130, 442), 6);
        floorList.get(6).addRoom(new Room("6.034", new Coord(4086, 354), new Coord(4298, 479), new Coord(4212, 370), r6034));
        Node r6035 = new Node(new Coord(4251, 583), 6);
        floorList.get(6).addRoom(new Room("6.035", new Coord(4216, 480), new Coord(4416, 601), new Coord(4347, 495), r6035));
        Node r6036 = new Node(new Coord(4261, 646), 6);
        floorList.get(6).addRoom(new Room("6.036", new Coord(4261, 602), new Coord(4520, 803), new Coord(4385, 660), r6036));
        Node r6037 = new Node(new Coord(4049, 650), 6);
        floorList.get(6).addRoom(new Room("6.037", new Coord(4040, 623), new Coord(4260, 934), new Coord(4154, 742), r6037));
        Node r6038 = new Node(new Coord(3836, 795), 6);
        floorList.get(6).addRoom(new Room("6.038", new Coord(3792, 816), new Coord(4039, 1106), new Coord(3933, 887), r6038));
        Node r6039 = new Node(new Coord(3678, 885), 6);
        floorList.get(6).addRoom(new Room("6.039", new Coord(3489, 902), new Coord(3791, 1273), new Coord(3690, 1048), r6039));


        // node setup

        Node l0 = new Node(new Coord(1530, 1072), 0);
        floorList.get(0).addNode(l0); // lift
        Node l1 = new Node(new Coord(1530, 1072), 1);
        floorList.get(1).addNode(l1); // lift
        Node l2 = new Node(new Coord(1530, 1072), 2);
        floorList.get(2).addNode(l2); // lift
        Node l3 = new Node(new Coord(1530, 1072), 3);
        floorList.get(3).addNode(l3); // lift
        Node l4 = new Node(new Coord(1530, 1072), 4);
        floorList.get(4).addNode(l4); // lift
        Node l5 = new Node(new Coord(3504, 976), 5);
        floorList.get(5).addNode(l5); // lift
        Node l6 = new Node(new Coord(3504, 976), 6);
        floorList.get(6).addNode(l6); // lift

        Node s0 = new Node(new Coord(2128, 1344), 0);
        floorList.get(0).addNode(s0);
        Node sl0 = new Node(new Coord(3112, 1344), 0);
        floorList.get(0).addNode(sl0);
        Node s0l0 = new Node(new Coord(3112, 1188), 0);
        floorList.get(1).addNode(s0l0);
        Node s1l0 = new Node(new Coord(3112, 1188), 1);
        floorList.get(1).addNode(s1l0);
        Node s1l1 = new Node(new Coord(3112, 1104), 1);
        floorList.get(1).addNode(s1l1);
        Node s1d = new Node(new Coord(2848, 1104), 1);
        floorList.get(1).addNode(s1d);

        Node s1u = new Node(new Coord(2288, 984), 1);
        floorList.get(1).addNode(s1u);
        Node s1l2 = new Node(new Coord(2040, 984), 1);
        floorList.get(1).addNode(s1l2);
        Node s2l0 = new Node(new Coord(2040, 984), 2);
        floorList.get(1).addNode(s2l0);
        Node s2d = new Node(new Coord(1816, 984), 2);
        floorList.get(2).addNode(s2d);

        Node s2u = new Node(new Coord(1550, 1375), 2);
        floorList.get(2).addNode(s2u);
        Node s2l1 = new Node(new Coord(1550, 1550), 2);
        floorList.get(2).addNode(s2l1);
        Node s3l0 = new Node(new Coord(1550, 1550), 3);
        floorList.get(2).addNode(s3l0);
        Node s3d = new Node(new Coord(1550, 1710), 3);
        floorList.get(3).addNode(s3d);

        Node s3u = new Node(new Coord(1504, 850), 3);
        floorList.get(3).addNode(s3u);
        Node s3l1 = new Node(new Coord(1611, 850), 3);
        floorList.get(3).addNode(s3l1);
        Node s4l0 = new Node(new Coord(1611, 850), 4);
        floorList.get(3).addNode(s4l0);
        Node s4d = new Node(new Coord(1504, 850), 4);
        floorList.get(4).addNode(s4d);

        Node s4u = new Node(new Coord(2150, 850), 4);
        floorList.get(4).addNode(s4u);
        Node s4l1 = new Node(new Coord(2420, 850), 4);
        floorList.get(4).addNode(s4l1);
        Node s5l0 = new Node(new Coord(2420, 850), 5);
        floorList.get(5).addNode(s5l0);
        Node s5d = new Node(new Coord(2722, 850), 5);
        floorList.get(5).addNode(s5d);

        Node s5u = new Node(new Coord(2904, 850), 5);
        floorList.get(5).addNode(s5u);
        Node s5l1 = new Node(new Coord(3050, 850), 5);
        floorList.get(5).addNode(s5l1);
        Node s6l0 = new Node(new Coord(3050, 850), 6);
        floorList.get(5).addNode(s6l0);
        Node s6 = new Node(new Coord(3200, 850), 6);
        floorList.get(6).addNode(s6);

        /*------------------------------------------------------------------------------------------------*/

        // floor 0 nodes
        Node p000 = new Node(new Coord(2312, 1832), 0);
        floorList.get(0).addNode(p000);
        Node p001 = new Node(new Coord(1856, 1512), 0);
        floorList.get(0).addNode(p001);
        Node p002 = new Node(new Coord(1680, 1312), 0);
        floorList.get(0).addNode(p002);
        Node p003 = new Node(new Coord(1680, 1072), 0);
        floorList.get(0).addNode(p003);
        Node p004 = new Node(new Coord(3000, 1504), 0);
        floorList.get(0).addNode(p004);
        Node p005 = new Node(new Coord(3240,1504), 0);
        floorList.get(0).addNode(p005);

        // floor 1 nodes
        Node p100 = new Node(new Coord(621, 704), 1);
        floorList.get(1).addNode(p100);
        Node p101 = new Node(new Coord(880, 704), 1);
        floorList.get(1).addNode(p101);
        Node p102 = new Node(new Coord(1150, 704), 1);
        floorList.get(1).addNode(p102);
        Node p103 = new Node(new Coord(1352, 704), 1);
        floorList.get(1).addNode(p103);
        Node p104 = new Node(new Coord(1150, 1151), 1);
        floorList.get(1).addNode(p104);
        Node p105 = new Node(new Coord(1352, 1072), 1);
        floorList.get(1).addNode(p105);
        Node p106 = new Node(new Coord(1352, 1336), 1);
        floorList.get(1).addNode(p106);
        Node p107 = new Node(new Coord(1352, 1832), 1);
        floorList.get(1).addNode(p107);
        Node p108 = new Node(new Coord(1680, 1336), 1);
        floorList.get(1).addNode(p108);
        Node p109 = new Node(new Coord(1680, 1144), 1);
        floorList.get(1).addNode(p109);
        Node p110 = new Node(new Coord(2368, 1144), 1);
        floorList.get(1).addNode(p110);
        Node p111 = new Node(new Coord(2547, 1104), 1);
        floorList.get(1).addNode(p111);

        // floor 2 nodes
        Node p201 = new Node(new Coord(1205, 2887), 2);
        floorList.get(2).addNode(p201);
        Node p202 = new Node(new Coord(1397, 2772), 2);
        floorList.get(2).addNode(p202);
        Node p203 = new Node(new Coord(1397, 2165), 2);
        floorList.get(2).addNode(p203);
        Node p204 = new Node(new Coord(1397, 2026), 2);
        floorList.get(2).addNode(p204);
        Node p205 = new Node(new Coord(1397, 1437), 2);
        floorList.get(2).addNode(p205);
        Node p206 = new Node(new Coord(896, 1437), 2);
        floorList.get(2).addNode(p206);
        Node p207 = new Node(new Coord(896, 1555), 2);
        floorList.get(2).addNode(p207);
        Node p208 = new Node(new Coord(896, 1365), 2);
        floorList.get(2).addNode(p208);
        Node p209 = new Node(new Coord(1397, 1303), 2);
        floorList.get(2).addNode(p209);
        Node p210 = new Node(new Coord(1552, 1303), 2);
        floorList.get(2).addNode(p210);
        Node p211 = new Node(new Coord(1698, 1303), 2);
        floorList.get(2).addNode(p211);
        Node p212 = new Node(new Coord(1698, 984), 2);
        floorList.get(2).addNode(p212);
        Node p214 = new Node(new Coord(1397, 1072), 2);
        floorList.get(2).addNode(p214);
        Node p215 = new Node(new Coord(1397, 822), 2);
        floorList.get(2).addNode(p215);
        Node p216 = new Node(new Coord(1397, 687), 2);
        floorList.get(2).addNode(p216);
        Node p217 = new Node(new Coord(1397, 632), 2);
        floorList.get(2).addNode(p217);
        Node p218 = new Node(new Coord(1397, 572), 2);
        floorList.get(2).addNode(p218);
        Node p219 = new Node(new Coord(1086, 632), 2);
        floorList.get(2).addNode(p219);
        Node p220 = new Node(new Coord(2232, 572), 2);
        floorList.get(2).addNode(p220);
        Node p221 = new Node(new Coord(3874, 572), 2);
        floorList.get(2).addNode(p221);
        Node p222 = new Node(new Coord(1889, 467), 2);
        floorList.get(2).addNode(p222);
        Node p223 = new Node(new Coord(2061, 467), 2);
        floorList.get(2).addNode(p223);
        Node p224 = new Node(new Coord(2232, 467), 2);
        floorList.get(2).addNode(p224);
        Node p225 = new Node(new Coord(2409, 467), 2);
        floorList.get(2).addNode(p225);
        Node p226 = new Node(new Coord(2585, 467), 2);
        floorList.get(2).addNode(p226);
        Node p227 = new Node(new Coord(2751, 467), 2);
        floorList.get(2).addNode(p227);
        Node p228 = new Node(new Coord(2928, 467), 2);
        floorList.get(2).addNode(p228);
        Node p229 = new Node(new Coord(3108, 467), 2);
        floorList.get(2).addNode(p229);
        Node p230 = new Node(new Coord(3275, 467), 2);
        floorList.get(2).addNode(p230);

        // floor 3 nodes
        Node p300 = new Node(new Coord(1205, 2887), 3);
        floorList.get(3).addNode(p300);
        Node p301 = new Node(new Coord(1397, 2314), 3);
        floorList.get(3).addNode(p301);
        Node p302 = new Node(new Coord(1397, 2165), 3);
        floorList.get(3).addNode(p302);
        Node p303 = new Node(new Coord(1397, 2026), 3);
        floorList.get(3).addNode(p303);
        Node p304 = new Node(new Coord(1397, 1828), 3);
        floorList.get(3).addNode(p304);
        Node p305 = new Node(new Coord(1504, 1828), 3);
        floorList.get(3).addNode(p305);
        Node p306 = new Node(new Coord(1397, 1072), 3);
        floorList.get(3).addNode(p306);
        Node p307 = new Node(new Coord(1397, 832), 3);
        floorList.get(3).addNode(p307);
        Node p308 = new Node(new Coord(1397, 663), 3);
        floorList.get(3).addNode(p308);
        Node p309 = new Node(new Coord(2071, 663), 3);
        floorList.get(3).addNode(p309);
        Node p310 = new Node(new Coord(1877, 478), 3);
        floorList.get(3).addNode(p310);
        Node p311 = new Node(new Coord(2071, 478), 3);
        floorList.get(3).addNode(p311);
        Node p312 = new Node(new Coord(2243, 478), 3);
        floorList.get(3).addNode(p312);
        Node p313 = new Node(new Coord(2414, 478), 3);
        floorList.get(3).addNode(p313);
        Node p314 = new Node(new Coord(2596, 478), 3);
        floorList.get(3).addNode(p314);

        // floor 4 nodes
        Node p400 = new Node(new Coord(1397, 2730), 4);
        floorList.get(4).addNode(p400);
        Node p401 = new Node(new Coord(1397, 2314), 4);
        floorList.get(4).addNode(p401);
        Node p402 = new Node(new Coord(1397, 1828), 4);
        floorList.get(4).addNode(p402);
        Node p403 = new Node(new Coord(1397, 1292), 4);
        floorList.get(4).addNode(p403);
        Node p404 = new Node(new Coord(1192, 1292), 4);
        floorList.get(4).addNode(p404);
        Node p405 = new Node(new Coord(1016, 1292), 4);
        floorList.get(4).addNode(p405);
        Node p406 = new Node(new Coord(1397, 1072), 4);
        floorList.get(4).addNode(p406);
        Node p407 = new Node(new Coord(1397, 850), 4);
        floorList.get(4).addNode(p407);
        Node p408 = new Node(new Coord(1397, 587), 4);
        floorList.get(4).addNode(p408);
        Node p409 = new Node(new Coord(2070, 587), 4);
        floorList.get(4).addNode(p409);
        Node p410 = new Node(new Coord(2600, 587), 4);
        floorList.get(4).addNode(p410);
        Node p411 = new Node(new Coord(2600, 850), 4);
        floorList.get(4).addNode(p411);
        Node p412 = new Node(new Coord(3360, 850), 4);
        floorList.get(4).addNode(p412);
        Node p413 = new Node(new Coord(3575, 693), 4);
        floorList.get(4).addNode(p413);
        Node p414 = new Node(new Coord(3700, 850), 4);
        floorList.get(4).addNode(p414);
        Node p415 = new Node(new Coord(1894, 473), 4);
        floorList.get(4).addNode(p415);
        Node p416 = new Node(new Coord(2070, 473), 4);
        floorList.get(4).addNode(p416);
        Node p417 = new Node(new Coord(2251, 473), 4);
        floorList.get(4).addNode(p417);
        Node p418 = new Node(new Coord(2427, 473), 4);
        floorList.get(4).addNode(p418);
        Node p419 = new Node(new Coord(2600, 473), 4);
        floorList.get(4).addNode(p419);
        Node p420 = new Node(new Coord(2070, 850), 4);
        floorList.get(4).addNode(p420);
        Node p421 = new Node(new Coord(1397, 1412), 4);
        floorList.get(4).addNode(p421);

        // floor 5 nodes
        Node p500 = new Node(new Coord(760, 600), 5);
        floorList.get(5).addNode(p500);
        Node p501 = new Node(new Coord(925, 600), 5);
        floorList.get(5).addNode(p501);
        Node p502 = new Node(new Coord(1121, 600), 5);
        floorList.get(5).addNode(p502);
        Node p503 = new Node(new Coord(1301, 600), 5);
        floorList.get(5).addNode(p503);
        Node p504 = new Node(new Coord(1487, 600), 5);
        floorList.get(5).addNode(p504);
        Node p505 = new Node(new Coord(1668, 600), 5);
        floorList.get(5).addNode(p505);
        Node p506 = new Node(new Coord(1882, 600), 5);
        floorList.get(5).addNode(p506);
        Node p507 = new Node(new Coord(2063, 600), 5);
        floorList.get(5).addNode(p507);
        Node p508 = new Node(new Coord(2230, 600), 5);
        floorList.get(5).addNode(p508);
        Node p509 = new Node(new Coord(2410, 600), 5);
        floorList.get(5).addNode(p509);
        Node p511 = new Node(new Coord(2821, 600), 5);
        floorList.get(5).addNode(p511);
        Node p512 = new Node(new Coord(2821, 850), 5);
        floorList.get(5).addNode(p512);
        Node p513 = new Node(new Coord(3646, 600), 5);
        floorList.get(5).addNode(p513);
        Node p514 = new Node(new Coord(3778, 600), 5);
        floorList.get(5).addNode(p514);
        Node p515 = new Node(new Coord(3652, 836), 5);
        floorList.get(5).addNode(p515);
        Node p516 = new Node(new Coord(3740, 775), 5);
        floorList.get(5).addNode(p516);
        Node p517 = new Node(new Coord(3872, 700), 5);
        floorList.get(5).addNode(p517);
        Node p518 = new Node(new Coord(4048, 407), 5);
        floorList.get(5).addNode(p518);
        Node p519 = new Node(new Coord(4141, 517), 5);
        floorList.get(5).addNode(p519);
        Node p520 = new Node(new Coord(4230, 616), 5);
        floorList.get(5).addNode(p520);
        Node p521 = new Node(new Coord(4004, 600), 5);
        floorList.get(5).addNode(p521);

        // floor 6 nodes
        Node p600 = new Node(new Coord(801, 600), 6);
        floorList.get(6).addNode(p600);
        Node p601 = new Node(new Coord(931, 600), 6);
        floorList.get(6).addNode(p601);
        Node p602 = new Node(new Coord(1139, 600), 6);
        floorList.get(6).addNode(p602);
        Node p603 = new Node(new Coord(1357, 600), 6);
        floorList.get(6).addNode(p603);
        Node p604 = new Node(new Coord(1492, 600), 6);
        floorList.get(6).addNode(p604);
        Node p605 = new Node(new Coord(1626, 600), 6);
        floorList.get(6).addNode(p605);
        Node p606 = new Node(new Coord(1910, 600), 6);
        floorList.get(6).addNode(p606);
        Node p607 = new Node(new Coord(2235, 600), 6);
        floorList.get(6).addNode(p607);
        Node p608 = new Node(new Coord(2379, 600), 6);
        floorList.get(6).addNode(p608);
        Node p609 = new Node(new Coord(2515, 600), 6);
        floorList.get(6).addNode(p609);
        Node p610 = new Node(new Coord(2641, 600), 6);
        floorList.get(6).addNode(p610);
        Node p611 = new Node(new Coord(2781, 600), 6);
        floorList.get(6).addNode(p611);
        Node p612 = new Node(new Coord(3300, 600), 6);
        floorList.get(6).addNode(p612);
        Node p613 = new Node(new Coord(3300, 850), 6);
        floorList.get(6).addNode(p613);
        Node p614 = new Node(new Coord(3650, 600), 6);
        floorList.get(6).addNode(p614);
        Node p615 = new Node(new Coord(4000, 600), 6);
        floorList.get(6).addNode(p615);
        Node p616 = new Node(new Coord(3650, 810), 6);
        floorList.get(6).addNode(p616);
        Node p617 = new Node(new Coord(3800, 730), 6);
        floorList.get(6).addNode(p617);
        Node p618 = new Node(new Coord(4050, 450), 6);
        floorList.get(6).addNode(p618);
        Node p619 = new Node(new Coord(4150, 500), 6);
        floorList.get(6).addNode(p619);
        Node p620 = new Node(new Coord(4250, 630), 6);
        floorList.get(6).addNode(p620);

        /*------------------------------------------------------------------------------------------------*/

        for (Floor f : floorList)   {
            for (Room r : f.getRoomsList())    {
                roomMap.addNode(r.getNode());
            }
            for (Node n : f.getNodesList()) {
                roomMap.addNode(n);
            }
        }

        // lift edges
        roomMap.addEdge(l0, l1);
        roomMap.addEdge(l1, l2);
        roomMap.addEdge(l2, l3);
        roomMap.addEdge(l3, l4);
        roomMap.addEdge(l5, l6);

        // stair edges
        roomMap.addEdge(s0, sl0);
        roomMap.addEdge(sl0, s0l0);
        roomMap.addEdge(s0l0, s1l0);
        roomMap.addEdge(s1l0, s1l1);
        roomMap.addEdge(s1l1, s1d);

        roomMap.addEdge(s1u, s1l2);
        roomMap.addEdge(s1l2, s2l0);
        roomMap.addEdge(s2l0, s2d);

        roomMap.addEdge(s2u, s2l1);
        roomMap.addEdge(s2l1, s3l0);
        roomMap.addEdge(s3l0, s3d);

        roomMap.addEdge(s3u, s3l1);
        roomMap.addEdge(s3l1, s4l0);
        roomMap.addEdge(s4l0, s4d);

        roomMap.addEdge(s4u, s4l1);
        roomMap.addEdge(s4l1, s5l0);
        roomMap.addEdge(s5l0, s5d);

        roomMap.addEdge(s5u, s5l1);
        roomMap.addEdge(s5l1, s6l0);
        roomMap.addEdge(s6l0, s6);

        // floor 0 edges
        roomMap.addEdge(entrance, p000);
        roomMap.addEdge(p000, p001);
        roomMap.addEdge(p000, p004);
        roomMap.addEdge(p001, s0);
        roomMap.addEdge(p001, p002);
        roomMap.addEdge(p002, p003);
        roomMap.addEdge(p003, l0);
        roomMap.addEdge(p004, reception0);
        roomMap.addEdge(p004, p005);
        roomMap.addEdge(p005, snackBar);

        // floor 1 edges
        roomMap.addEdge(r1025, p100);
        roomMap.addEdge(p100, p101);
        roomMap.addEdge(r1025a, p101);
        roomMap.addEdge(p101, p102);
        roomMap.addEdge(r1025b, p102);
        roomMap.addEdge(p104, p102);
        roomMap.addEdge(r1019, p104);
        roomMap.addEdge(p102, p103);
        roomMap.addEdge(p103, p105);
        roomMap.addEdge(l1, p105);
        roomMap.addEdge(p105, p106);
        roomMap.addEdge(p106, p107);
        roomMap.addEdge(lt1006, p107);
        roomMap.addEdge(p106, p108);
        roomMap.addEdge(p108, p109);
        roomMap.addEdge(p109, p110);
        roomMap.addEdge(p110, p111);
        roomMap.addEdge(r1043, p111);
        roomMap.addEdge(s1d, p111);
        roomMap.addEdge(s1u, p111);

        // floor 2 edges
        roomMap.addEdge(s2d, p212);
        roomMap.addEdge(s2u, p210);
        roomMap.addEdge(l2, p214);
        roomMap.addEdge(r2004, p201);
        roomMap.addEdge(r2005, p203);
        roomMap.addEdge(r2006, p204);
        roomMap.addEdge(lt2006, p204);
        roomMap.addEdge(r2018, p208);
        roomMap.addEdge(r2017, p207);
        roomMap.addEdge(reception2, p205);
        roomMap.addEdge(reception2, p206);
        roomMap.addEdge(r2019, p219);
        roomMap.addEdge(r2022, p219);
        roomMap.addEdge(r2026, p215);
        roomMap.addEdge(r2027, p216);
        roomMap.addEdge(r2039, p222);
        roomMap.addEdge(r2040, p223);
        roomMap.addEdge(r2041, p224);
        roomMap.addEdge(r2042, p225);
        roomMap.addEdge(r2043, p226);
        roomMap.addEdge(r2044, p227);
        roomMap.addEdge(r2045, p228);
        roomMap.addEdge(r2046, p229);
        roomMap.addEdge(r2047, p230);
        roomMap.addEdge(r2060, p221);
        roomMap.addEdge(r2048, p220);
        roomMap.addEdge(r2048, p221);
        roomMap.addEdge(p201, p202);
        roomMap.addEdge(p202, p203);
        roomMap.addEdge(p203, p204);
        roomMap.addEdge(p204, p205);
        roomMap.addEdge(p206, p207);
        roomMap.addEdge(p206, p208);
        roomMap.addEdge(p205, p209);
        roomMap.addEdge(p209, p210);
        roomMap.addEdge(p210, p211);
        roomMap.addEdge(p211, p212);
        roomMap.addEdge(p209, p214);
        roomMap.addEdge(p214, p215);
        roomMap.addEdge(p215, p216);
        roomMap.addEdge(p216, p217);
        roomMap.addEdge(p217, p218);
        roomMap.addEdge(p217, p219);
        roomMap.addEdge(p218, p220);
        roomMap.addEdge(p220, p224);
        roomMap.addEdge(p222, p223);
        roomMap.addEdge(p223, p224);
        roomMap.addEdge(p224, p225);
        roomMap.addEdge(p225, p226);
        roomMap.addEdge(p226, p227);
        roomMap.addEdge(p227, p228);
        roomMap.addEdge(p228, p229);
        roomMap.addEdge(p229, p230);

        // floor 3 edges
        roomMap.addEdge(r3004, p300);
        roomMap.addEdge(r3005, p301);
        roomMap.addEdge(r3009, p302);
        roomMap.addEdge(r3008, p303);
        roomMap.addEdge(r3015, p304);
        roomMap.addEdge(s3d, p305);
        roomMap.addEdge(l3, p306);
        roomMap.addEdge(s3u, p307);
        roomMap.addEdge(r3018, p308);
        roomMap.addEdge(r3031, p309);
        roomMap.addEdge(r3031, p311);
        roomMap.addEdge(r3034, p310);
        roomMap.addEdge(r3035, p311);
        roomMap.addEdge(r3036, p312);
        roomMap.addEdge(r3037, p313);
        roomMap.addEdge(r3038, p314);
        roomMap.addEdge(r3032, p312);
        roomMap.addEdge(p300, p301);
        roomMap.addEdge(p301, p302);
        roomMap.addEdge(p302, p303);
        roomMap.addEdge(p303, p304);
        roomMap.addEdge(p304, p305);
        roomMap.addEdge(p304, p306);
        roomMap.addEdge(p306, p307);
        roomMap.addEdge(p307, p308);
        roomMap.addEdge(p308, p309);
        roomMap.addEdge(p310, p311);
        roomMap.addEdge(p311, p312);
        roomMap.addEdge(p312, p313);
        roomMap.addEdge(p313, p314);

        // floor 4 edges
        roomMap.addEdge(r4004, p400);
        roomMap.addEdge(r4005, p401);
        roomMap.addEdge(r4016, p402);
        roomMap.addEdge(r4018, p405);
        roomMap.addEdge(r4020, p405);
        roomMap.addEdge(r4021, p404);
        roomMap.addEdge(l4, p406);
        roomMap.addEdge(s4d, p407);
        roomMap.addEdge(r4022, p408);
        roomMap.addEdge(s4u, p420);
        roomMap.addEdge(r4037, p409);
        roomMap.addEdge(r4037, p416);
        roomMap.addEdge(r4053, p414);
        roomMap.addEdge(r4038, p415);
        roomMap.addEdge(r4039, p416);
        roomMap.addEdge(r4040, p417);
        roomMap.addEdge(r4041, p418);
        roomMap.addEdge(r4042, p419);
        roomMap.addEdge(terrace, p421);
        roomMap.addEdge(p400, p401);
        roomMap.addEdge(p401, p402);
        roomMap.addEdge(p402, p421);
        roomMap.addEdge(p421, p403);
        roomMap.addEdge(p403, p404);
        roomMap.addEdge(p404, p405);
        roomMap.addEdge(p403, p406);
        roomMap.addEdge(p406, p407);
        roomMap.addEdge(p407, p408);
        roomMap.addEdge(p408, p409);
        roomMap.addEdge(p420, p409);
        roomMap.addEdge(p409, p410);
        roomMap.addEdge(p410, p411);
        roomMap.addEdge(p411, p412);
        roomMap.addEdge(p412, p413);
        roomMap.addEdge(p413, p414);
        roomMap.addEdge(p415, p416);
        roomMap.addEdge(p416, p417);
        roomMap.addEdge(p417, p418);
        roomMap.addEdge(p418, p419);

        // floor 5 edges
        roomMap.addEdge(r5012, p500);
        roomMap.addEdge(r5013, p501);
        roomMap.addEdge(r5014, p502);
        roomMap.addEdge(r5015, p503);
        roomMap.addEdge(r5016, p504);
        roomMap.addEdge(r5017, p505);
        roomMap.addEdge(r5018, p506);
        roomMap.addEdge(r5019, p507);
        roomMap.addEdge(r5020, p508);
        roomMap.addEdge(r5021, p509);
        roomMap.addEdge(s5d, p512);
        roomMap.addEdge(s5u, p512);
        roomMap.addEdge(r5024, p511);
        roomMap.addEdge(l5, p515);
        roomMap.addEdge(r5031a, p515);
        roomMap.addEdge(r5031, p516);
        roomMap.addEdge(r5030, p517);
        roomMap.addEdge(r5026, p518);
        roomMap.addEdge(r5027, p519);
        roomMap.addEdge(r5028, p520);
        roomMap.addEdge(r5029, p520);
        roomMap.addEdge(p500, p501);
        roomMap.addEdge(p501, p502);
        roomMap.addEdge(p502, p503);
        roomMap.addEdge(p503, p504);
        roomMap.addEdge(p504, p505);
        roomMap.addEdge(p505, p506);
        roomMap.addEdge(p506, p507);
        roomMap.addEdge(p507, p508);
        roomMap.addEdge(p508, p509);
        roomMap.addEdge(r5022, p509);
        roomMap.addEdge(r5022, p511);
        roomMap.addEdge(p511, p512);
        roomMap.addEdge(p511, p513);
        roomMap.addEdge(p513, p514);
        roomMap.addEdge(p514, p516);
        roomMap.addEdge(p515, p516);
        roomMap.addEdge(p516, p517);
        roomMap.addEdge(p518, p519);
        roomMap.addEdge(p517, p521);
        roomMap.addEdge(p521, p519);
        roomMap.addEdge(p519, p520);
        roomMap.addEdge(p514, p521);

        // floor 6 edges
        roomMap.addEdge(r6013, p600);
        roomMap.addEdge(r6014, p601);
        roomMap.addEdge(r6015, p602);
        roomMap.addEdge(r6016, p603);
        roomMap.addEdge(r6017, p604);
        roomMap.addEdge(r6018, p605);
        roomMap.addEdge(r6019, p606);
        roomMap.addEdge(r6020, p607);
        roomMap.addEdge(r6021, p608);
        roomMap.addEdge(r6022, p609);
        roomMap.addEdge(r6023, p610);
        roomMap.addEdge(r6024, p611);
        roomMap.addEdge(s6, p613);
        roomMap.addEdge(l6, p616);
        roomMap.addEdge(r6039, p616);
        roomMap.addEdge(r6038, p617);
        roomMap.addEdge(r6037, p615);
        roomMap.addEdge(r6033, p618);
        roomMap.addEdge(r6034, p619);
        roomMap.addEdge(r6035, p620);
        roomMap.addEdge(r6036, p620);
        roomMap.addEdge(p600, p601);
        roomMap.addEdge(p601, p602);
        roomMap.addEdge(p602, p603);
        roomMap.addEdge(p603, p604);
        roomMap.addEdge(p604, p605);
        roomMap.addEdge(p605, p606);
        roomMap.addEdge(p606, p607);
        roomMap.addEdge(p607, p608);
        roomMap.addEdge(p608, p609);
        roomMap.addEdge(p609, p610);
        roomMap.addEdge(p610, p611);
        roomMap.addEdge(p611, p612);
        roomMap.addEdge(p612, p613);
        roomMap.addEdge(p612, p614);
        roomMap.addEdge(p614, p616);
        roomMap.addEdge(p614, p617);
        roomMap.addEdge(p616, p617);
        roomMap.addEdge(p617, p615);
        roomMap.addEdge(p615, p619);
        roomMap.addEdge(p618, p619);
        roomMap.addEdge(p619, p620);
    }
}