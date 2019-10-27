import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Trains {

    public static String[] buildGraphArr(String graphNodes) {
        String[] nodes = new String[graphNodes.length() / 2]; // Array of nodes with minimal memory allocation
        nodes = graphNodes.split(","); // All nodes in an array
        return nodes;
    }

    public static Map<Character, Map<Character, Integer>> buildGraph(String[] graph) {
        Map<Character, Map<Character, Integer>> routes = new HashMap<>();
        // This function builds the actual graph. We key in later to find distances
        Arrays.sort(graph);

        for (int i = 0; i < graph.length; i++) {
            if (routes.containsKey(graph[i].toString().charAt(0))) {
                routes.get(graph[i].toString().charAt(0)).put(graph[i].toString().charAt(1),
                        Integer.parseInt(graph[i].substring(2)));

            } else {
                Map<Character, Integer> nodeMap = new HashMap<>();
                nodeMap.put(graph[i].toString().charAt(1), Integer.parseInt(graph[i].substring(2)));
                routes.put(graph[i].toString().charAt(0), nodeMap);
            }
        }
        return routes;
    } 

    public static void main(String[] args) throws Exception{ //Main function
        // Read data file
        File file = new File("/Users/danielball/Desktop/Trains/data.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st = "";
        String[] graphString = new String[1]; 
        while ((st = br.readLine()) != null) graphString[0] = st; // Read first line
        if (graphString[0] == null) System.out.println("No input data"); //If first line is null print out not input
        
        
        Map<Character, Map<Character, Integer>> actualGraph = new HashMap<>();
        actualGraph = buildGraph(buildGraphArr(graphString[0]));
        int dist = 0;
        
        // first case
        dist += (actualGraph.get('A').get('B') + actualGraph.get('B').get('C'));
        System.out.println("The distance of the route A-B-C is " + dist);
        
        // second case
        dist = actualGraph.get('A').get('D');
        System.out.println("The distance of the route A-D is " + dist);

    }
}