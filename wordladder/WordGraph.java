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
		// System.out.println(v.getWord());
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

	
	
	public void wordLadder(int start, int goal) {
		//assign each vertex to be unvisited;
		for (Vertex v : vertices){
			v.setVisited(false);
		}	
		
		//set up an initially empty queue of  
         //		visited but unprocessed vertices;
		Queue<Vertex> toProcess = new LinkedList<Vertex>();
    		//if v is not visited 
      		//assign v to be visited;
      		vertices[start].setVisited(true);
      		vertices[start].setPredecessor(-1);
      		//assign the predecessor of v;
      		//add v to the queue;
			toProcess.add(vertices[start]);
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
						w.setVisited(true);
						w.setPredecessor(u.getIndex());
						//System.out.println(w.getWord());
						if (w.getIndex() == goal){
							while (w.getPredecessor() != -1){
								System.out.println (vertices[w.getPredecessor()].getWord());
								w = vertices[w.getPredecessor()];
							}
							return;
						}
           					//add w to the queue;
						toProcess.add(w);
         			} 
       			}
       		}
       			System.out.println("No viable word ladder exists");
	}
	

}
