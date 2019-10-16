import java.util.*;

public class TopologicalSort<T> {
	
	public Deque<T> topoSort(Graph<T> graph)
	{
		if(graph == null)
			return null;
		
		Set<T> visited = new HashSet<>();
		Deque<T> stack = new ArrayDeque<>();
		
		for(T v : graph.getAllVertex())
		{
			if(visited.contains(v))
				continue;
			
			topoSortUtil(graph, v, visited, stack);
		}
		
		return stack;
	}
	
	private void topoSortUtil(Graph<T> graph, T v, Set<T> visited, Deque<T> stack)
	{
		visited.add(v);
		List<Edge<T>> list = graph.getAdjacentVertices(v);
		if(list != null)
		{
			for(Edge<T> e : list)
			{
				if(visited.contains(e.v))
					continue;
				
				topoSortUtil(graph, e.v, visited, stack);
			}		
		}
		//vertex to be moved from visited -> stack when its all adjacent nodes gets explored via DFS
		stack.offerFirst(v);		
	}
	
	public static void main(String[] args) {

		Graph<Integer> graph = new Graph<>(true);	//Directed Graph
		
		graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(5, 6);
        graph.addEdge(6, 3);
        graph.addEdge(3, 8);
        graph.addEdge(8, 11);
                
        Deque<Integer> stack = (new TopologicalSort<Integer>()).topoSort(graph);
        while(stack != null && !stack.isEmpty())
        	System.out.println(stack.poll());     
        
	}

}
