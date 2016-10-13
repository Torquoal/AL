import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws IOException {

		String inputFileName = args[0];
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		String line = in.nextLine();
        	Scanner lineScanner = new Scanner(line);
		int numVertices = lineScanner.nextInt();
		
		reader.close();

		/* 	Construct a graph
			For each line i of matrix (total numVertices) go to graph's vertices[i]
			For each '1' in the line, add adjancey node to vertex's LinkedList
			Once all lines done, graph should be finished.
			
		*/
		
		Graph graph = new Graph(numVertices);
		
		for (int i=0; i<numVertices; i++){

			LinkedList<AdjListNode> currentList = graph.getVertex(i).getAdjList();
			String currentLine = in.nextLine();
			
			//string is double normal length due to spaces, hence
			// +1/2 below and j*2 for charAt.
			for (int j = 0; j < (currentLine.length() + 1)/2; j++){

				//System.out.println(currentLine.charAt(j*2));
				char x = currentLine.charAt(j*2);
				if (x == '1'){
					AdjListNode node = new AdjListNode(j);
					currentList.add(node);
				}			
			}
			//for (AdjListNode nodes:currentList){
			//	System.out.println("\n" + nodes.getVertexNumber());
			//}
		}	
		
		// conduct the breadth-first search
		graph.bfs();

		String outputFileName = args[1];
		FileWriter writer = new FileWriter(outputFileName);

		System.out.println("");
		
		for (int i=0; i < graph.size(); i++){
			System.out.println(graph.getVertex(i).getPredecessor());
			writer.write( String.valueOf(graph.getVertex(i).getPredecessor()));
		}
		
		writer.close();

	}

}
