// Java Program to illustrate reading from 
// FileReader using FileReader 
import java.io.*; 
public class ReadFile 
{ 
public static void main(String[] args) throws Exception 
{ 
	// pass the path to the file as a parameter 
	FileReader fr = new FileReader("test.txt"); 

	int i; 
	while ((i=fr.read()) != -1) 
	System.out.print((char) i); 
} 
} 
