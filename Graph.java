import java.util.*;

class Edge<T>
{
	T v;	//vertex name
	int wt;
	
	Edge(T v, int wt)
	{
		this.v = v;
		this.wt = wt;
	}
}

public class Graph<T>
{
	public boolean isDirected = false;	//by default undirected
	public Map<T, List<Edge<T>>> map;
	public boolean isWeighted = false;
	
	public Graph()
	{
		map = new HashMap<>();
	}
	
	public Graph(boolean isDirected)
	{
		map = new HashMap<>();
		this.isDirected = isDirected;
	}
	
	public void addEdge(T v1, T v2)
	{
		addEdge(v1,v2, 0);
	}
	
	public void addEdge(T v1, T v2, int wt)
	{
		addEdgeUtil(v1, v2, wt);
		
		if(!isDirected)	//Undirected, hence we need to add v2->v1 also
			addEdgeUtil(v2, v1, wt);
		else
		{
			if(!map.containsKey(v2))
				map.put(v2, null);
		}
			
		
		if(!isWeighted && wt != 0)
			isWeighted = true;
	}
	
	private void addEdgeUtil(T v1, T v2, int wt)
	{
		List<Edge<T>> list = null;
		
		if(map.containsKey(v1))
		{
			list = map.get(v1);
			if(list == null)
				list = new ArrayList<>(); 
		}
			
		else
			list = new ArrayList<>();
		
		Edge<T> e = new Edge(v2, wt);
		list.add(e);
		map.put(v1, list);			
	}
	
	public Set<T> getAllVertex()
	{
		return map.keySet();
	}
	
	public Set<Map.Entry<T, List<Edge<T>>>> getAllEdges()
	{
		return map.entrySet();
	}
	
	public List<Edge<T>> getAdjacentVertices(T v)
	{
		return map.get(v);
	}
	
	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		List<Edge<T>> list = null;
		for(T v : map.keySet())
		{
			list = map.get(v);
			if(list == null)
				continue;
			for(Edge<T> e : list)
			{
				str.append(v + "->" + e.v);
				if(isWeighted)
					str.append(" : " + e.wt);
				str.append("\n");
			}
		}
		return str.toString();
	}
}

class HeapNode<T>
{
	Integer wt;
	T v;
	
	public HeapNode(T v, int wt)
	{
		this.wt = wt;
		this.v = v;
	}
	
	@Override
	public String toString()
	{
		return (wt + " : " + v);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		
		if(this.getClass() != o.getClass())
			return false;
		
		if((this.v).equals(((HeapNode<T>)o).v))
			return true;
		
		return false;
	}
	
	@Override
	public int hashCode()
	{
		int prime = 31;		
		int val = (Integer) this.v;
		return prime*val;
	}

}

