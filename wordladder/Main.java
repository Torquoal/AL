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
  
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		/**
		while (in.hasNext()){
			System.out.println(in.next());
		}
		*/
		
		// NOTE: Need to find a way to size the graph, then iterate over it later. Store
		// in an array?
		
		List<String> wordBank = new ArrayList<String>();
		
		while (in.hasNext()){ 
			wordBank.add(in.next());
		}
		// build graph for size of word bank
		
		WordGraph graph = new WordGraph(wordBank.size());
		
		// fill the graph with words
		for (int i = 0; i<wordBank.size(); i++){

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
			
			System.out.print("WORD: ");
			System.out.println(currentVertex.getWord());
			for (AdjListNode nodes:currentVertex.getAdjList()){
				System.out.println(wordBank.get(nodes.getVertexNumber()));
			}
			
		}
		

			
		
		
		/**
			// for each vertex, we need to fill out their AdjList with words which
			// are one letter different than they are.
			String tempWord;
			
			// for each word in the file
			
			int count = 0;
			
			while (in.hasNext()){
			
			
				int check = 0;
				tempWord = in.next();
				
				//check to see if 4/5 chars in this word match word1
				for (int i = 0; i < tempWord.length(); i++){
    				char c = s.charAt(i);        
    				if (word1.indexOf(c) != -1) check ++;
				}
				// if word matches conditions, add this word to AdjList with its
				// numerical order
				if (check == (tempWord.length() - 1)){
					AdjListNode node = new AdjListNode(count);
					currentList.add(node);
				}
				// iterate number of the word we are testing for adjacency 
				count++:
			}
		*/	
			
			
		
		
		
		
		// read in the data here

        // create graph here
		
		// IDEA: store words in graph. A word's vertex has adjacent vertexes containingf
		//		 words were 4/5 of the letters are the same. i.e. adjancent vertexes are the
		//		 options available to that word on the ladder.
		
		// Breadth First Search  - see slide 25
		// As breadth first search advances each 'path' 1 step at a time, should find the
		// Shortest path to word2. But how do we store the inbetween words? Predecessors?	
		// Perhaps once word2 found, create string array of size = steps to find word2			
		// then add predecessors to the array in reverse order, working back to start.
		// need to alter Vertex to have predecessor element and to contain words.			

        reader.close();

        
		// do the work here
		
		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}

}
