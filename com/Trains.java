import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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

    // public static Integer trips(Map<Character, Map<Character, Integer>> graph, Character start, Character end, int num){
    //     int c = 0;
    //     Set<String> visited = new HashSet<String>();
    //     System.out.println(hash_Set.contains("D"));        
    //     HashMap queue[] = new HashMap[20];

    //     ArrayList<Map<Character, Integer>> mylist = new ArrayList<Map<Character, Integer>>();
    //     mylist.add(graph.get('C'));
    //     actualGraph.forEach((k, v) -> mylist.add(v));
    //     System.out.println(mylist);

    //     return 0;
    // }

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
        
        // Map<Character, Integer> queue[] = new Map<Character, Integer>[20];
        // List<Map<String , String>> myMap  = new ArrayList<Map<String,String>>();       
        // HashMap<String, HashMap> selects = new HashMap<String, HashMap>();

        // for (Map.Entry<Character, HashMap> entry : actualGraph.entrySet()) {
        //     String key = actualGraph.getKey();
        //     HashMap value = actualGraph.getValue();
        //     System.out.println(key);
        //     // do what you have to do here
        //     // In your case, another loop.
        // }
        ArrayList<Map<Character, Integer>> mylist = new ArrayList<Map<Character, Integer>>();
        // ArrayList<Map<Character, Integer>> mylist = new ArrayList<Map<Character, Integer>>();
        // Map<Character, Integer> newMap = new Map<Character, Integer>();

        // mylist.add(graph);
        // newMap=actualGraph.get('C');
        // System.out.println(newMap);
            System.out.println(actualGraph);
            
            // Map<Character, Integer> routers = new HashMap<>();
            // int c = 0;

            //  Set<String> visited = new HashSet<String>();
            // routers.put('C', 5);
            // mylist.add(routers);
            // routers = actualGraph.get('C');
            // routers.forEach((k, v) -> {
            // Map<Character, Integer> inner = new HashMap<>();
            //     inner.put(k, v);
            //     mylist.add(inner);
            //     // in = mylist.remove(0);
            //     // System.out.println(in);
            // });

        //     String key = "A";
        //     Integer masterCounter = 0;
        //     Integer counter = 0;
        //     System.out.println(routers);
        // // actualGraph.forEach((k, v) -> mylist.add(v));
        // System.out.println(mylist);
        //     Set<Character> visited = new HashSet<Character>();
       
            
        
                
        Set<Character> visited = new HashSet<Character>(); // visisted set
        Integer masterCount = 0; // total number of routes between start and finish
        Integer count = 0;  // our inside count to check against master
        ArrayList<Map<Character, Integer>> tripList = new ArrayList<Map<Character, Integer>>(); //main array
        Map<Character, Integer> nodes = new HashMap<>(); // hashmap to get values from actualgraph and seperate afterwards
        nodes = actualGraph.get('C');
        nodes.forEach((k, v) -> { // this function seperates {{d =>5, E= 4}} to {d=5},{e=4}}
            Map<Character, Integer> node = new HashMap<>(); // needed to prevent errors
            node.put(k, v);
            tripList.add(node);   //adds children to seperated arraty
        });
        // if (tripList.size() <= 0)   // ideally if nothing is in the array return 0
        while (tripList.size() > 0) {
            count += 1;
            Map<Character, Integer> current = new HashMap<>(); 
            current = tripList.remove(0);   //shift off first ele from array                                                  
            if (current.containsKey('C') && count <= 3){
                masterCount += 1;
                count = 0;
            }
            Character key = current.toString().charAt(1); // get key 
            if (!visited.contains(key)){ //check if visited includes key
                visited.add(key);  // add to visited should it not include the key
            }
            ArrayList<Map<Character, Integer>> children = new ArrayList<Map<Character, Integer>>();
            Map<Character, Integer> childNodes = new HashMap<>();
            childNodes = actualGraph.get(key);
            childNodes.forEach((k, v) -> { // this function seperates {{d =>5, E= 4}} to {d=5},{e=4}}
                Map<Character, Integer> node = new HashMap<>(); // needed to prevent errors
                node.put(k, v);
                children.add(node); // adds children to seperated arraty
            });

            if (children.size() < 0) {
                count = 0;
            } else {
                while (children.size() > 0) {
                    Map<Character, Integer> curr = new HashMap<>();
                    count += 1;
                    curr = children.remove(0); // shift off first ele from array
                    if (curr.containsKey('C') && count <= 3) {
                        masterCount += 1;
                        count = 0;
                        break;  // found C and < 3
                    } else {
                        Map<Character, Integer> kids = new HashMap<>();
                        kids = actualGraph.get(curr.toString().charAt(1));   //getting char key from hashMap
                        kids.forEach((k, v) -> {        // ADD children to children array seperated
                            Map<Character, Integer> node = new HashMap<>(); 
                            node.put(k, v);      
                            children.add(node); 
                        });
                    }
                }
            }
            System.out.println(masterCount);
        }
        
        System.out.println(tripList);
    }
}