import java.util.LinkedList;
import java.util.Queue;

/**
 class to represent an undirected graph using adjacency lists
 */
public class WordGraph {

	private Vertex[] vertices; // the (array of) vertices
	private int numVertices = 0; // number of vertices

	// possibly other fields representing properties of the graph

	/** 
	 creates a new instance of Graph with n vertices
	*/
	public WordGraph(int n) {
		numVertices = n;
		vertices = new Vertex[n];
		for (int i = 0; i < n; i++)
			vertices[i] = new Vertex(i);
	}

	public int size() {
		return numVertices;
	}

	public Vertex getVertex(int i) {
		return vertices[i];
	}

	public void setVertex(int i) {
		vertices[i] = new Vertex(i);
	}

	/**
	 visit vertex v, with predecessor index p,
	 during a depth first traversal of the graph
	*/
	private void Visit(Vertex v, int p) {
		System.out.println(v.getIndex());
		v.setVisited(true);
		v.setPredecessor(p);
		LinkedList<AdjListNode> L = v.getAdjList();
		for (AdjListNode node : L) {
			int n = node.getVertexNumber();
			if (!vertices[n].getVisited()) {
				Visit(vertices[n], v.getIndex());
			}
		}
	}

	/**
     carry out a depth first search/traversal of the graph
	*/
	public void dfs() {
		for (Vertex v : vertices)
			v.setVisited(false);
		for (Vertex v : vertices)
			if (!v.getVisited())
				Visit(v, -1);
	}

	/**
	 carry out a breadth first search/traversal of the graph
	 psedocode version
	 */
	public void bfs() {
		System.out.println("Order Visied: ");
		//assign each vertex to be unvisited;
		for (Vertex v : vertices)
			v.setVisited(false);
		//set up an initially empty queue of  
         //		visited but unprocessed vertices;
		Queue<Vertex> toProcess = new LinkedList<Vertex>();
  		//for each vertex v 
		for (Vertex v: vertices){
    		//if v is not visited 
			if (!v.getVisited()){
      			//assign v to be visited;
      			Visit(v, v.getIndex());
      			//assign the predecessor of v;
      			//add v to the queue;
				toProcess.add(v);
      			//while the queue is not empty {
				while (toProcess.peek() != null){
        			//remove vertex u from the queue;
					Vertex u = toProcess.remove();
        			//for each vertex w in the adjacency list of u 
					for (int i = 0; i < u.getAdjList().size(); i++){
						Vertex w = vertices[u.getAdjList().get(i).getVertexNumber()];
        				//if w is unvisited 
						if (!w.getVisited()){
           					//assign w to be visited;
           					//assign the predecessor of w;
							Visit(w, w.getIndex());
           					//add w to the queue;
							toProcess.add(w);
         				} 
       				}
       			}
    		}
    	}
	}
	
	public void wordLadder(Vertex start, Vertex end) {
		// Breadth First Search  - see slide 25
		// As breadth first search advances each 'path' 1 step at a time, should find the
		// Shortest path to word2. But how do we store the inbetween words? Predecessors?	
		// Perhaps once word2 found, create string array of size = steps to find word2			
		// then add predecessors to the array in reverse order, working back to start.
		// need to alter Vertex to have predecessor element and to contain words.
		
		Queue<Vertex> toProcess = new LinkedList<Vertex>();
		
	int depth = 0;
		// Get start's Adjacency List
		
		LinkedList<AdjListNode>currentList = start.getAdjList();
		
		// Add to toProcess List
		
		// Visit each node on List, add their unvisited adjacents to the List
		
		for (AdjListNode node: currentList){
			Vertex v = vertices[node.getVertexNumber];
			Visit (v, v.getIndex());
			toProcess.add(v)
		}
		
		// Visit each node on List, add their unvisited adjacents to the List
		
		// Repeat until word2 is reached or toProcess is empty
		
		// if word2 reached, create ArrayList and add word2 and its pedecessor to it
		// keep adding predecessors until back at word1.
		// then print ArrayList in reverse order (?)
		
	}

}
