import java.io.*;
import java.util.*;
import java.util.ArrayList.*;

/**
 program to find word ladder with shortest path (i.e. minimum number edges
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		String inputFileName = args[0]; // dictionary
		String word1 = args[1]; // first word
		String word2 = args[2]; // second word
		
		int in1 = 0;
		int in2 = 0;
  
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		
		List<String> wordBank = new ArrayList<String>();
		int word1In = 0;
		int word2In = 0;
		
		while (in.hasNext()){ 
			wordBank.add(in.next());
		}
		
		 reader.close();
		 
		// build graph for size of word bank
		
		WordGraph graph = new WordGraph(wordBank.size());
		
		// fill the graph with words
		for (int i = 0; i<wordBank.size(); i++){
		
			//System.out.println(wordBank.get(i));
			//System.out.println(word1);
			if (wordBank.get(i).equals(word1)){
				in1 = i;
				System.out.println(wordBank.get(i));
			}
			
			if (wordBank.get(i).equals(word2)){
				in2 = i;
			}

			Vertex currentVertex = graph.getVertex(i);
			currentVertex.setWord(wordBank.get(i));
		
			
			// now that word is added to Vertex, we should iterate over the 
			// wordBank and look for adjacent possibilities, then add them
			
			
			LinkedList<AdjListNode> adjList = currentVertex.getAdjList();
			char c, d;
			int count = 0;
			for (String word: wordBank){
			
				int check = 0;
				
				for (int j = 0; j < word.length(); j++){
    				if ( (c = word.charAt(j)) == 
    					(d = currentVertex.getWord().charAt(j)) ){
    						check++;        
    				}		
				}
				// if word matches conditions, add this word to AdjList with its
				// numerical order
				if (check == (currentVertex.getWord().length() - 1)){
					AdjListNode node = new AdjListNode(count);
					adjList.add(node);
					
				}
				count++;
			}
			/**
			System.out.print("WORD: ");
			System.out.println(currentVertex.getWord());
			for (AdjListNode nodes:currentVertex.getAdjList()){
				System.out.println(wordBank.get(nodes.getVertexNumber()));
			}
			*/
			
		}
				
		graph.wordLadder(in1, in2);		
		// Breadth First Search  - see slide 25
		// As breadth first search advances each 'path' 1 step at a time, should find the
		// Shortest path to word2. But how do we store the inbetween words? Predecessors?	
		// Perhaps once word2 found, create string array of size = steps to find word2			
		// then add predecessors to the array in reverse order, working back to start.
		// need to alter Vertex to have predecessor element and to contain words.			

       

        
		// do the work here
		
		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}

}
