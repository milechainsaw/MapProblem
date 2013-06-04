package com.chainsaw.vertexcolor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class VertexColor {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		UniGraph<String> fullGraph = loadFile("DATA/macedonia.txt");

		System.out.print("Data Loaded\n");
		// Remove all duplicate connections
		System.out.print(reduceGraph(fullGraph).toString());

	}

	private static UniGraph<String> loadFile(String _filename) {
		UniGraph<String> graph = new UniGraph<String>();

		try {
			FileReader file = new FileReader(_filename);
			BufferedReader input = new BufferedReader(file);
			String line;
			LinkedHashMap<String, String[]> vertexInput = new LinkedHashMap<String, String[]>();
			while ((line = input.readLine()) != null) {
				String parts[] = line.split(":");
				vertexInput.put(parts[0], parts[1].split(","));
			}
			for (String node : vertexInput.keySet()) {
				graph.addNode(node);
			}
			for (Map.Entry<String, String[]> node : vertexInput.entrySet()) {
				for (String edge : node.getValue()) {
					graph.addEdge(node.getKey(), edge);
				}
			}
			input.close();

		} catch (Exception e) {
			System.out.print("Load Failed --> " + e);
		}

		return graph;
	}

	private static UniGraph<String> reduceGraph(UniGraph<String> inGraph) {
		UniGraph<String> minGraph = inGraph.clone();
		Iterator<String> nodeIterator = minGraph.iterator();

		while (nodeIterator.hasNext()) {
			String node = nodeIterator.next();
			try {
				if (minGraph.getEdgeNodes(node) == null) {
					nodeIterator.remove();
				} else {
					String[] edges = minGraph.getEdgeNodes(node).toArray(
							new String[0]);
					for (int i = 0; i < edges.length; i++) {
						minGraph.removeEdge(edges[i], node);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return minGraph;

	}
}
