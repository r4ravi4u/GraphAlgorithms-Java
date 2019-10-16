import java.util.*;

public class Dijsktra<T> {
	
	public Map<T, Integer> shortestPathDijsktra(Graph<T> graph, T src)
	{
		if(graph == null)
			return null;
		
		Set<T> set = graph.getAllVertex();
		
		Queue<HeapNode<T>> heapMap = new PriorityQueue<>(set.size(), new Comparator<HeapNode<T>>()
		{
			@Override
			public int compare(HeapNode<T> node1, HeapNode<T> node2)
			{
				return (node1.wt).compareTo(node2.wt);
			}
		});
		
		Map<T, Integer> mapDistance = new HashMap<>();	//distance map which needs to be returned
		Map<T, T> mapParent = new HashMap<>();	//parent Map to get the path from source (key) -> dest (key)
		
		for(T v : set)
		{
			HeapNode<T> node;
					
			if(v.equals(src))
			{
				node = new HeapNode<>(v, 0);
				mapParent.put(v, null);
			}
				
			else
				node = new HeapNode<>(v, Integer.MAX_VALUE);
					
			heapMap.offer(node);
		}
		
		HeapNode<T> node, temp;
		while(heapMap.size() > 0)
		{
			node = heapMap.poll();
			mapDistance.put(node.v, node.wt);
			T v = node.v;
			List<Edge<T>> list = graph.getAdjacentVertices(node.v);
			if(list != null)
			{
				for(Edge<T> e : list)
				{
					node.v = e.v;
					Iterator<HeapNode<T>> itr = heapMap.iterator();
					HeapNode<T> tempNode = null; 
					while(itr.hasNext())
					{
						if((temp = itr.next()).equals(node))
						{
							if(temp.wt > (node.wt + e.wt))
							{
								tempNode = new HeapNode(node.v, node.wt + e.wt);
								itr.remove();
								mapParent.put(temp.v, v);
							}
							break;
						}
					}
					if(tempNode != null)
						heapMap.offer(tempNode);
				}
			}
			
		}
		
		return mapDistance;
	}

	public static void main(String[] args) {
		Graph<Integer> graph = new Graph<>(false);
        /*graph.addEdge(0, 1, 4);
        graph.addEdge(1, 2, 8);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 4, 9);
        graph.addEdge(4, 5, 10);
        graph.addEdge(2, 5, 4);
        graph.addEdge(1, 7, 11);
        graph.addEdge(0, 7, 8);
        graph.addEdge(2, 8, 2);
        graph.addEdge(3, 5, 14);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 8, 6);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 8, 7);*/

        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 2);
        graph.addEdge(1, 4, 9);
        graph.addEdge(1, 5, 3);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 4, 2);
        graph.addEdge(3, 4, 3);
        
        System.out.println((new Dijsktra<Integer>()).shortestPathDijsktra(graph, 1));

	}

}
