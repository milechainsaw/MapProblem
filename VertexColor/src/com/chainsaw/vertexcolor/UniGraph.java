/**
 * 
 */
package com.chainsaw.vertexcolor;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Mile
 * 
 */
public class UniGraph<T> implements Iterable<T> {

	private final Map<T, Set<T>> graph = new HashMap<T, Set<T>>();

	public UniGraph() {

	}

	public void addNode(T node) {
		graph.put(node, new HashSet<T>());
	}

	public void removeNode(T node) throws Exception {
		if (graph.containsKey(node)) {
			graph.remove(node);
		}else{
			throw new Exception("Node not found");
		}

	}
	
	public void addEdge(T node1, T node2) throws Exception{
		if(!graph.containsKey(node1)||!graph.containsKey(node2)){
			throw new Exception("One or both nodes missing");
		}else{
			graph.get(node1).add(node2);
			graph.get(node2).add(node1);
		}
	}
	
	public void removeEdge(T node1, T node2) throws Exception{
		if(!graph.containsKey(node1)||!graph.containsKey(node2)){
			throw new Exception("One or both nodes missing");
		}else{
			graph.get(node1).remove(node2);
			graph.get(node2).remove(node1);
		}
	}
	
	public boolean hasEdge(T node1, T node2) throws Exception{
		if(!graph.containsKey(node1)||!graph.containsKey(node2)){
			throw new Exception("One or both nodes missing");
		}else{
			return graph.get(node1).contains(node2);
		}
	}
	
	public Set<T> getEdgeNodes(T node) throws Exception{
		Set<T> elements =  graph.get(node);
		if(elements==null)
			throw new Exception("Node not found");
		return Collections.unmodifiableSet(elements);
	}
	
	

	@Override
	public Iterator<T> iterator() {
		return graph.keySet().iterator();
	}
	
	public int size(){
		return graph.size();
	}
	
	public Iterable<T> getNodes(){
		return graph.keySet();
	}

}
