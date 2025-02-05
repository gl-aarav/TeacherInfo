/*
 * 1/31/2025
 * TeacherInfo.java
 * 
 * Pseudocode
 * 
 *  import Scannner and java.io.*
 * 	Declare Field Variables (Three arrays, scores[], grades[], teacherData[])
 * 	TeacherInfo()
 *   - Initialize arrays 
 * 		- scores[101]: Number of Grades for each number from 1 to 100
 * 		- grades[5]: Number of Grades for Each Category: A, B, C, D, F
 * 		- teacherData[3]: Teacher Name, Course Number, and Course Name
 * 
 *  main()
 * 	- create an instance of TeacherInfo
 * 	- use instance to call runIt()
 * 
 * 	runIt()
 * 	 - fake main, calls all the methods
 *
 * askUser()
 * 	 - Asks user for the Teacher's file name including ".txt" 
 * 	 - Asks user for the course Number
 *	 - Sends the user input to decideNumbers()
 * 
 * 	openIt()
 * 	 - Looks for the File and opens it, if can't find file throw error using try...catch method
 * 
 * decideNumbers()
 * 	 - Stores the words after "Teacher:" stores everything into teacherData[0] until the String ends and it reaches "Class:"
 * 	 - Stores the words after "Class:" stores the course name into teacherData[1]
 *   - Stores the words after course name stores everything into teacherData[2] until the String ends and it reaches "Room:"
 * 
 * 	 - Put the grades into the correct place in the array
 * 	 	- First, decide where to add the number in scores[]
 * 		- Second, decide where to add the number in grades[]
 * 		- Put the data for Teacher Name, Course Number, and Room Number into the correct place for teacherData[]
 * 
 * makeIt()
 * 	- creates the file and adds "-results.txt" at the end of the file name
 * 
 * printItConsole()
 *	- prints the data to the console
 * 
 * printItTXT()
 *  - prints the data to the txt file
 */ 
 
 
 import java.util.Scanner;
 import java.io.*;
 
 public class TeacherInfo
 {
	private int[] scores;
	private int[] grades;
	private String[] teacherData;
	public TeacherInfo()
	{
		scores = new int[101];
		grades = new int[5];
		teacherData = new String[3];
	}
	
	public static void main(String[] args)
	{
		TeacherInfo ti = new TeacherInfo();
		ti.runIt();
	}
	
	public void runIt()
	{
		askUser();
	}
	
	public void askUser()
	{
		Scanner in = new Scanner(System.in);
		String inFileName = new String("");
		int courseNum = 0;
		System.out.print("Please enter the name of the teacher’s file including the extension: ");
		inFileName = in.nextLine();
		System.out.println("Please enter the course number for data you would like: ");
		courseNum = in.nextInt();
		openIt(in, inFileName);
	}
	
	public void openIt(Scanner in, String inFileName)
	{
		File inFile = new File(inFileName);
		try
		{
			in = new Scanner(inFile);
		}
		catch (FileNotFoundException e)
		{
			System.err.printf("\n\n\nERROR: Cannot find/open file %s.\n\n\n", inFileName);
			System.exit(1);
		}
		decideNumbers(in);
	}
	
	public void decideNumbers(Scanner in)
	{
		String token, token1 = new String ("");
		while (in.hasNext())
		{ 
			token = in.next();
			if (token.equals("Teacher:"))
			{
				
				while (in.hasNext() && !token.equals("Class:"))
				{
					token1 = in.next();
					teacherData[0].concat(token1 + " ");
				}
			}
			else if (token.equals("Class:"))
			{
				
			}
				
		}
	}
	public void makeIt()
	{
		
	}
	
	public void printItConsole()
	{
		
	}
	
	public void printItTXT()
	{
		
	}
 }
