import java.util.*;

public class DisjointSet<T> {
	
	Map<T, Node> map = new HashMap<>();
	class Node
	{
		T data;
		Node parent;
		int rank;
	}
	
	public void makeSet(T data)
	{
		Node set = new Node();
		set.data = data;
		set.parent = set;
		set.rank = 0;
		
		map.put(data, set);
	}
	
	public boolean union(T data1, T data2)
	{
		Node p1 = findSet(map.get(data1));
		Node p2 = findSet(map.get(data2));
		
		if(p1.data == p2.data)
			return false;
		
		if(p1.rank >= p2.rank)
		{
			//increment rank iff both ranks are same else not
			p1.rank = (p1.rank == p2.rank) ? p1.rank+1 : p1.rank;
			
			p2.parent = p1;
		}
		else	
			p1.parent = p2;
		
		return true;
		
	}
	
	public T findSet(T data)
	{
		return findSet(map.get(data)).data;
	}
	
	private Node findSet(Node node)
	{
		Node p = node.parent;
		if(p == node)
			return p;
		node.parent = findSet(node.parent);	//Path Compression
		return node.parent;
	}

	public static void main(String[] args) {
		
		DisjointSet<Integer> ds = new DisjointSet<>();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);

        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);

        System.out.println(ds.findSet(1));
        System.out.println(ds.findSet(2));
        System.out.println(ds.findSet(3));
        System.out.println(ds.findSet(4));
        System.out.println(ds.findSet(5));
        System.out.println(ds.findSet(6));
        System.out.println(ds.findSet(7));

	}

}
