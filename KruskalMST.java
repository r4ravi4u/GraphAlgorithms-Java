import java.util.*;

class NodeEdge<T>
{
	Integer wt;
	T v1;
	T v2;
	
	public NodeEdge(int wt, T v1, T v2)
	{
		this.wt = wt;
		this.v1 = v1;
		this.v2 = v2;
	}
}

public class KruskalMST<T> {
	
	public List<List<T>> kruskalMST(Graph<T> graph)
	{
		DisjointSet<T> ds = new DisjointSet<>();
		Set<T> vertexSet = graph.getAllVertex();
		for(T v : vertexSet)
			ds.makeSet(v);
		
		//Get all edges & Sort edges in increasing manner (weight)
		Queue<NodeEdge<T>> heapList = new PriorityQueue<>(new Comparator<NodeEdge<T>>()
		{
			@Override
			public int compare(NodeEdge<T> n1, NodeEdge<T> n2)
			{
				return (n1.wt).compareTo(n2.wt);
			}
		});		
		for(Map.Entry<T, List<Edge<T>>> e : graph.getAllEdges())
			for(Edge<T> e1 : e.getValue())
				heapList.offer(new NodeEdge<T>(e1.wt, e.getKey(), e1.v));
		
		int edgeCount = 0;	//total edges in MST = total vertex - 1;
		List<List<T>> result = new ArrayList<>();
		while(heapList.size() > 0 && edgeCount < (vertexSet.size() - 1))
		{
			NodeEdge<T> edge = heapList.poll();
			if(edge!= null && ds.union(edge.v1, edge.v2))
			{
				List<T> list = new ArrayList<>(2);
				list.add(edge.v1);
				list.add(edge.v2);
				
				result.add(list);
				edgeCount++;
			}
		}
		
		return result;
	}
	public static void main(String[] args) {
		
		Graph<Integer> graph = new Graph<Integer>(false);
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 5, 1);
        graph.addEdge(2, 6, 3);
        graph.addEdge(2, 4, 2);
        graph.addEdge(6, 5, 2);
        graph.addEdge(6, 4, 3);
        graph.addEdge(4, 7, 2);
        graph.addEdge(3, 4, 5);
        graph.addEdge(3, 7, 8);
        
        System.out.println((new KruskalMST<Integer>()).kruskalMST(graph));
	}

}
