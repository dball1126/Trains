import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;

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

    public static Integer maxNumOfTrips(Map<Character, Map<Character, Integer>> actualGraph, Character start, Character end, int num){
        Integer count = 0;  // our inside count to check against master
        List<Integer> numbers = new ArrayList<>();
        ArrayList<Map<Character, Integer>> queue = new ArrayList<Map<Character, Integer>>(); //main array
        Map<Character, Integer> nodes = new HashMap<>(); // hashmap to get values from actualgraph and seperate afterwards
        List<String> strRoutes = new ArrayList<>();        
        
        if (!actualGraph.containsKey(start)) return 0; // if nothing in graph return 0 number of trips
        nodes = actualGraph.get(start);
        nodes.forEach((k, v) -> { // this function seperates {{d =>5, E= 4}} to {d=5},{e=4}}
            Map<Character, Integer> node = new HashMap<>(); // needed to prevent errors
            node.put(k, v);
            queue.add(node);   //adds children to seperated arraty
        });
        
        while (queue.size() > 0) {
            count = 1;
            Map<Character, Integer> node = new HashMap<>();  // declare hashMap
            node = queue.remove(0); //queue.shift();
            Character charKey = node.toString().charAt(1); // get key
            if (actualGraph.containsKey(charKey)){
                List<Integer> childNums = new ArrayList<>();
                childNums = upToRoutes(actualGraph, charKey, end, count, num);
                if (childNums.size() > 0) {
                    numbers.addAll(childNums);
                    System.out.println(numbers);
                }
            }
            
        }
       return numbers.size();
    }

    public static List<Integer> upToRoutes(Map<Character, Map<Character, Integer>> actualGraph, Character start, Character end, int count, int num){
        int oldCount = count;

        List<Integer> collection = new ArrayList<>();
        ArrayList<Map<Character, Integer>> children = new ArrayList<Map<Character, Integer>>(); // main array
        Map<Character, Integer> nodes = new HashMap<>(); // hashmap to get values from actualgraph and seperate
        nodes = actualGraph.get(start);
        nodes.forEach((k, v) -> { // this function seperates {{d =>5, E= 4}} to {d=5},{e=4}}
            Map<Character, Integer> node = new HashMap<>(); // needed to prevent errors
            node.put(k, v);
            children.add(node); // adds children to seperated array // afterwards
        });
        
        while (children.size() > 0) {   // while children.size
            ArrayList<Map<Character, Integer>> queue = new ArrayList<Map<Character, Integer>>(); // main array
            Map<Character, Integer> node = new HashMap<>(); // declare hashMap
            Set<Character> visited = new HashSet<Character>(); // visisted set

            node = children.remove(0); // queue.shift();
            queue.add(node);

            while (queue.size() > 0) { // while children of children.size
                Map<Character, Integer> innerNode = new HashMap<>(); // declare hashMap
                innerNode = queue.remove(0);
                count += 1;
                Character charKey = innerNode.toString().charAt(1); // get key
                if (charKey == end && count <= num){
                    collection.add(count);
                    count = oldCount;
                    break;
                }
                if (actualGraph.containsKey(charKey) && !visited.contains(charKey)){ // add children of children of children
                    Map<Character, Integer> kids = new HashMap<>(); // hashmap to get values from actualgraph and
                    kids = actualGraph.get(charKey);
                    kids.forEach((k, v) -> { // this function seperates {{d =>5, E= 4}} to {d=5},{e=4}}
                        Map<Character, Integer> kid = new HashMap<>(); // needed to prevent errors
                        kid.put(k, v);
                        queue.add(kid); // adds children to seperated array // afterwards
                    });   
                }
                visited.add(charKey);
            }
        }
        return collection; 
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
        
        //Sixth case
            dist = maxNumOfTrips(actualGraph, 'C', 'C', 3);
            System.out.println("The number of trips starting at C and ending at C with a maximum of three stops: " + dist);
        
        //Seventh case // may have to check about "exactly 4 stops"  Outputs correct answer but may need to check.
            // dist = trips(actualGraph, 'A', 'C', 4);
            // System.out.println("The number of trips starting at A and ending at C with a maximum of four stops: " + dist);
         
    }
}







//  while (tripList.size() > 0) {
//             count = 1;
//             strRoutes.add(String.valueOf(start));
//             System.out.println("triplist" + tripList);
//             Map<Character, Integer> current = new HashMap<>(); 
//             current = tripList.remove(0);   //shift off first ele from array                                                  
//             if (current.containsKey(end) && count <= num){
//                 numbers.add(count);
//                 strRoutes.add(String.valueOf(end));
//                 masterCount += 1;
//                 count = 0;
//             }
//             Character key = current.toString().charAt(1); // get key 
//             // strRoutes.add(String.valueOf(key));
//             ArrayList<Map<Character, Integer>> children = new ArrayList<Map<Character, Integer>>();
//             Map<Character, Integer> childNodes = new HashMap<>();
//             childNodes = actualGraph.get(key);
//             childNodes.forEach((k, v) -> { // this function seperates {{d =>5, E= 4}} to {d=5},{e=4}}
//                 Map<Character, Integer> node = new HashMap<>(); // needed to prevent errors
//                 node.put(k, v);
//                 children.add(node); // adds children to seperated arraty
//             });

//             if (children.size() < 0) {
//                 count = 0;
//             } else {
//                 while (children.size() > 0) {
//                     Set<Character> visited = new HashSet<Character>(); // visisted set

//                     Map<Character, Integer> curr = new HashMap<>();
//                     count += 1;
//                     curr = children.remove(0); // shift off first ele from array
//                     System.out.println(count + " " + children + "current node " + curr);
//                     // if (count > 4) {
//                     //     count = 1;
//                     //     continue;
//                     // }
//                     Character charKey = current.toString().charAt(1); // get key
//                         strRoutes.add(String.valueOf(charKey));
//                     if (!visited.contains(charKey)) { // check if visited includes key
//                         visited.add(charKey); // add to visited should it not include the key
//                     }
                    
//                     if (curr.containsKey(end) && count <= num) {
//                         masterCount += 1;
//                         strRoutes.add(String.valueOf(end));
//                         numbers.add(count);
//                         count = 0;
//                         continue;  // found C and < 3  // CHANGE TO CONTINUE and numbers change from 3 to 4
//                     } else {
//                         Map<Character, Integer> kids = new HashMap<>();
//                         kids = actualGraph.get(curr.toString().charAt(1));   //getting char key from hashMap
//                         kids.forEach((k, v) -> {        // ADD children to children array seperated
//                             Map<Character, Integer> node = new HashMap<>(); 
//                             node.put(k, v);
//                             children.add(node); 
//                         });
//                     }
//                 }
//             }
//         }System.out.println(strRoutes);System.out.println(numbers);return masterCount;