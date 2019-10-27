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

    public static boolean isTrue(Map<Character, Map<Character, Integer>> graph, Character char1, Character char2){
        
        if (graph.containsKey(char1) && graph.get(char1).containsKey(char2)){
            return true;
        } else {
            return false;
        }
    }

    public static Integer trips(Map<Character, Map<Character, Integer>> graph, int num){
        return 0;
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
        if (isTrue(actualGraph, 'A', 'B') && isTrue(actualGraph, 'B', 'C')){
            dist += (actualGraph.get('A').get('B') + actualGraph.get('B').get('C'));
            System.out.println("The distance of the route A-B-C is " + dist);
        } else {
            System.out.println("Not a valid route");
        }
        
        // second case
        if (isTrue(actualGraph, 'A', 'D')){
            dist = actualGraph.get('A').get('D');
            System.out.println("The distance of the route A-D is " + dist);
        } else {
            System.out.println("Not a valid route");
        }

        // third case
        if (isTrue(actualGraph, 'A', 'D') && isTrue(actualGraph, 'D', 'C')){

            dist = (actualGraph.get('A').get('D') + actualGraph.get('D').get('C'));
            System.out.println("The distance of the route A-D-C is " + dist);
        }   else {
            System.out.println("Not a valid route");
        }

        //fourth case 4. The distance of the route A-E-B-C-D.
        if (isTrue(actualGraph, 'A', 'E') && isTrue(actualGraph, 'E', 'B') && isTrue(actualGraph, 'B', 'C') && isTrue(actualGraph, 'C', 'D')){
            dist = (actualGraph.get('A').get('E') + actualGraph.get('E').get('B') +
                    actualGraph.get('B').get('C') + actualGraph.get('C').get('D'));
            System.out.println("The distance of the route A-E-B-C-D is " + dist);
        } else {
            System.out.println("Not a valid route");
        }

        //fifth case: 5. The distance of the route A-E-D.
        if(isTrue(actualGraph, 'A', 'E') && isTrue(actualGraph, 'E', 'D')){
            dist = actualGraph.get('A').get('E') + actualGraph.get('E').get('D');
            System.out.println("The distance of the route A-E-D is " + dist);
        } else {
            System.out.println("Not a valid route");
        }
        // boolean truthy = false;
        // truthy = (dist == int(dist));
        System.out.println(trips(actualGraph, 3));
    }
}