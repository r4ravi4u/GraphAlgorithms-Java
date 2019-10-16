import java.util.*;

public class PrimMST<T> {
	
	public List<List<T>> primMST(Graph<T> graph)	//T[] = v1@T[0] -> v2@T[1] corresponds to edge
	{
		if(graph == null)
			return null;
		
		Set<T> set = graph.getAllVertex();
		int size = set.size();
		
		Queue<HeapNode<T>> heapMap = new PriorityQueue<>(size, new Comparator<HeapNode<T>>()
		{
			@Override
			public int compare(HeapNode<T> node1, HeapNode<T> node2)
			{
				return (node1.wt).compareTo(node2.wt);
			}
		});
		
		List<List<T>> result = new ArrayList<>(size-1);
		Map<T, List<T>> map = new HashMap<>();		
		List<T> edge = null;
		
		boolean flag = true;
		for(T v : set)
		{
			HeapNode<T> node;
					
			if(flag)	//only 1 node marked weight as 0 rest all infinite
			{
				node = new HeapNode<>(v, 0);
				flag = false;
			}
				
			else
				node = new HeapNode<>(v, Integer.MAX_VALUE);
					
			heapMap.offer(node);
		}
		
		HeapNode<T> node, temp;
		Set<HeapNode<T>> checkSet = new HashSet<>(size);
		while(heapMap.size() > 0)
		{
			node = heapMap.poll();
			checkSet.add(node);	//for optimisation during iteration
			if(map.containsKey(node.v))
			{
				result.add(map.get(node.v));	//final result
			}
			
			T v = node.v;
			List<Edge<T>> list = graph.getAdjacentVertices(v);
			if(list != null)
			{
				for(Edge<T> e : list)
				{
					if(checkSet.contains(e))
						continue;
					
					node.v = e.v;
					node.wt = e.wt;
					Iterator<HeapNode<T>> itr = heapMap.iterator();
					HeapNode<T> tempNode = null; 
					while(itr.hasNext())
					{
						if((temp = itr.next()).equals(node))
						{
							if(temp.wt > node.wt)
							{
								tempNode = new HeapNode(node.v, node.wt);
								itr.remove();
								
								edge = new ArrayList<>(2);
								edge.add(v);
								edge.add(node.v);								
								map.put(node.v, edge);
							}
							break;
						}
					}
					if(tempNode != null)
						heapMap.offer(tempNode);
				}
				
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Graph<Integer> graph = new Graph<>(false);
	     /* graph.addEdge(0, 1, 4);
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

	        graph.addEdge(1, 2, 3);
	        graph.addEdge(2, 3, 1);
	        graph.addEdge(3, 1, 1);
	        graph.addEdge(1, 4, 1);
	        graph.addEdge(2, 4, 3);
	        graph.addEdge(4, 5, 6);
	        graph.addEdge(5, 6, 2);
	        graph.addEdge(3, 5, 5);
	        graph.addEdge(3, 6, 4);
	        
	        System.out.println((new PrimMST<Integer>()).primMST(graph));

	}

}
