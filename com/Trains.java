import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Trains {

    
    public static void main(String[] args) throws Exception{
        // Read data file
        File file = new File("/Users/danielball/Desktop/Trains/data.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st = "";
        String[] arr = new String[1]; 
        
        while ((st = br.readLine()) != null) arr[0] = st; // Read first line
        if (arr[0] == null) System.out.println("No input data"); //If first line is null print out not input
        
        System.out.println(arr[0]);
    }
}