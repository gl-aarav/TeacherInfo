
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
 * 	 - Stores the words after "Class:" stores the course number into teacherData[1]
 *   - Stores the words after course number stores everything into teacherData[2] until the String ends and it reaches "Room:"
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
		for (int i = 0; i < teacherData.length; i++)
			teacherData[i] = "";
		askUser();
	}

	public void askUser()
	{
		Scanner in = new Scanner(System.in);
		String inFileName = "";
		String courseNum = "";
		System.out.print("\n\n\nPlease enter the name of the teacher’s file including the extension: ");
		inFileName = in.nextLine();
		System.out.print("Please enter the course number for data you would like: ");
		courseNum = in.next();
		teacherData[1] = courseNum;
		openIt(in, inFileName, courseNum);
		makeIt(inFileName.substring(0,inFileName.length()-4).concat("-results.txt"));
	}

	public void openIt(Scanner in, String inFileName, String courseNum)
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
		decideNumbers(in, courseNum, inFileName);
	}

	public void decideNumbers(Scanner in, String courseNumber, String inFileName)
	{	
		String token = new String (""), token1 = new String ("");
		
		token = in.next();
		token1 = in.next();
			
		while (in.hasNext() && !token1.equals("Class:"))
		{
				teacherData[0]  = teacherData[0] + token1 + " ";
				token1 = in.next();
		}
	
		while (in.hasNext() && courseNumber.equals(teacherData[1]))
		{
		
			if (token1.equals("Class:"))
			{
				token1 = in.next();		
				courseNumber = token1.substring(0, token1.length() - 2);
				token1 = in.next();
				
				if (courseNumber.equals(teacherData[1]))
				{
					while (in.hasNext() && !token1.equals("Room:"))
					{
						teacherData[2] = teacherData[2] + token1 + " ";
						token1 = in.next();
					}
				}
			}
				
			if (token1.equals("Scores:"))
			{
				token1 = in.next();
				while (in.hasNext() && !token1.equals("Teacher:"))
				{
					
					double temp = Double.parseDouble(token1);
					scores[(int)temp]++;
					if (temp >= 90)
						grades[0]++;
					else if (temp >= 80 && temp <= 89)
						grades[1]++;
					else if (temp >= 70 && temp <= 79)
						grades[2]++;
					else if (temp >= 60 && temp <= 69)
						grades[3]++;
					else
						grades[4]++;
					token1 = in.next();
				}
			}
			token1 = in.next();
		}
		if (!courseNumber.equals(teacherData[1]))
			errorPrint(inFileName);
	}
	
	public void makeIt(String outFileName)
	{
		PrintWriter output = null;
		File outFile = new File(outFileName);
		try
		{
			output = new PrintWriter(outFile);
		}
		catch (IOException e)
		{
			System.err.println("\n\n\nERROR: Cannot create " + outFileName + " file.\n\n\n");
			System.exit(2);
		}	
		printItConsole();
		printItTXT(output);
	}

	public void printItConsole()
	{
		int total = 0;
		for (int i = 0; i < scores.length; i++)
			total += scores[i];

		int printALine = 0;
		System.out.println("\nTeacher Name: " + teacherData[0]);
		System.out.println("Course Number: " + teacherData[1]);
		System.out.println("Course Name: " + teacherData[2]);

		System.out.println("\nScores Distribution (high to low):");
		for (int i = scores.length - 1; i >= 0; i--)
		{
			for (int y = 100; y < scores[i]; y--)
			{
				if (printALine == 15)
				{
					System.out.printf("\n%5d", y);
					printALine = 0;
				}
				else
				{
					System.out.printf("%-5d", y);
					printALine++;
				}
			}
		}
		System.out.print("\nA (90-100): " + grades[0] + "\t");
		System.out.printf("%2f\n",(double)grades[0]/total);
		
		System.out.print("B (80-89): " + grades[1] + "\t");
		System.out.printf("%2f\n",(double)grades[1]/total);
		
		System.out.print("C (70-79): " + grades[2] + "\t");
		System.out.printf("%2f\n",(double)grades[2]/total);
		
		System.out.print("D (60-69): " + grades[3] + "\t");
		System.out.printf("%2f\n",(double)grades[3]/total);
		
		System.out.print("F (0-59): " + grades[4] + "\t");
		System.out.printf("%2f\n",(double)grades[4]/total);

		System.out.println("\n\n\n");
	}

	public void printItTXT(PrintWriter output)
	{
		int printALine = 0;
		int total = 0;
		for (int i = 0; i < scores.length; i++)
			total += scores[i];

		try
		{
			output = new PrintWriter("TeacherInfo-results.txt");

			output.println("\n\nTeacher Information:");
			output.println("Teacher Name: " + teacherData[0]);
			output.println("Course Number: " + teacherData[1]);
			output.println("Course Name: " + teacherData[2]);

			for (int i = scores.length - 1; i >= 0; i--)
			{
				for (int y = 100; y < scores[i]; y--)
				{
					if (printALine == 15)
					{
						output.printf("\n%5d", y);
						printALine = 0;
					}
					else
					{
						output.printf("%-5d", y);
						printALine++;
					}
				}
			}
			output.println("\nA (90-100): " + grades[0] + "\t" + (double)grades[0]/total);
			output.println("B (80-89): " + grades[1] + "\t" + (double)grades[1]/total);
			output.println("C (70-79): " + grades[2] + "\t" + (double)grades[2]/total);
			output.println("D (60-69): " + grades[3] + "\t" + (double)grades[3]/total);
			output.println("F (0-59): " + grades[4] + "\t" + (double)grades[4]/total);

			output.close();
		}

		catch (IOException e)
		{
			System.err.println("\n\n\nERROR: Cannot write to file.\n\n\n");
			System.exit(3);
		}
		output.close();
	}

	public void errorPrint(String fileName)
	{
		System.err.println("The course number does not exist in \""+ fileName + "\"");
	}
}

