package com.example.grigo.usb_explore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ListGraph {

	private Map<Node, Set<Node>> nodes;
	
	ListGraph()	{
		this.nodes = new HashMap<Node, Set<Node>>();
	}

	ListGraph(Map<Node, Set<Node>> nodes)	{
		this.nodes = nodes;
	}

	void addNode(Node node)	{
		nodes.put(node, new HashSet<Node>());
	}
	
	void addEdge(Node from, Node to)	{
		nodes.get(from).add(to);
		nodes.get(to).add(from);
	}

	Set<Node> getNodes()	{
		return nodes.keySet();
	}
	
	List<Node> Path(Node start, Node end)	{
		
		List<Node> path = new LinkedList<Node>();
		Queue<Node> toVisit = new LinkedList<Node>();
		Set<Node> visited = new HashSet<Node>();
		Map<Node, Node> route = new HashMap<Node, Node>();
		route.put(end, null);
		visited.add(end);
		toVisit.add(end);
		
		while (!toVisit.isEmpty())	{
			
			Node current = toVisit.poll();
			
			if (current == start)	{
				Node routeElement = current;
				while (routeElement != null)	{
					path.add(routeElement);
					routeElement = route.get(routeElement);
				}
				return path;
			}
			
			for (Node n : nodes.get(current))	{ 
				if (!visited.contains(n))	{
					route.put(n, current);
					visited.add(n);
					toVisit.offer(n);
				}
			}
			
		}
		
		return path;
		
	}
	
}