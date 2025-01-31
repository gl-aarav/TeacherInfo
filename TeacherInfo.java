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
 * 	readIt()
 * 	 - Looks for the File and reads it, if can't find file throw error using try...catch method
 * 
 * decideNumbers()
 * 	 - Starts looking for a letter after "Teacher:" stores everything into teacherData[1] until the String ends and it reaches "Class:"
 * 		 - use .trim() method
 * 	 - Starts looking for a letter after "Class:" stores everything into teacherData[2] until the String ends and it reaches a space
 *   - Starts looking for a letter after course name stores everything into teacherData[3] until the String ends and it reaches "Room:"
 * 		 - use.trim() method
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
		
	}
 }
