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
		teacherData = new String[4];
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
		System.out.print("Please enter the name of the teacher’s file including the extension: ");
		String inFileName = in.nextLine().trim();
		System.out.print("Please enter the course number for data you would like: ");
		int courseNum = in.nextInt();
		openIt(inFileName, courseNum);
		makeIt(inFileName);
		printIt();
	}

	public void openIt(String inFileName, int courseNum)
	{
		File inFile = new File(inFileName);
		Scanner fileInput = null;
		try
		{
			fileInput = new Scanner(inFile);
		}
		catch (FileNotFoundException e)
		{
			System.err.printf("\n\n\nERROR: Cannot find/open file \"%s\".\n\n\n", inFileName);
			System.exit(1);
		}
		decideNumbers(fileInput, courseNum);
		fileInput.close();
	}

	public void decideNumbers(Scanner input, int courseNum)
	{
		while (input.hasNext())
		{
			String token = input.next();
			if (token.equals("Teacher:"))
			{
				teacherData[0] = "";
				while (input.hasNext())
				{
					String nextToken = input.next();
					if (nextToken.equals("Class:"))
					{
						break;
					}
					teacherData[0] += nextToken + " ";
				}
				teacherData[0] = teacherData[0].trim();
			}
			else if (token.equals("Class:"))
			{
				teacherData[1] = input.hasNext() ? input.next() : "";
				teacherData[2] = "";
				while (input.hasNext())
				{
					String nextToken = input.next();
					if (nextToken.equals("Room:"))
					{
						break;
					}
					teacherData[2] += nextToken + " ";
				}
				teacherData[2] = teacherData[2].trim();
			}
			else if (token.equals("Room:"))
			{
				teacherData[3] = input.hasNext() ? input.next() : "";
			}
			else if (token.equals("Scores"))
			{
				while (input.hasNext())
				{
					String nextToken = input.next();
					if (nextToken.equals("Grades"))
					{
						break;
					}
					int score = Integer.parseInt(nextToken);
					if (score >= 0 && score <= 100)
					{
						scores[score]++;
						if (score >= 90)
						{
							grades[0]++;
						}
						else if (score >= 80)
						{
							grades[1]++;
						}
						else if (score >= 70)
						{
							grades[2]++;
						}
						else if (score >= 60)
						{
							grades[3]++;
						}
						else
						{
							grades[4]++;
						}
					}
				}
			}
		}
	}
	public void makeIt(String outFileName)
	{
		PrintWriter output = null;
		File outFile = new File(outFileName.substring(0, outFileName.length() - 4) + "-results.txt");
		try
		{
			output = new PrintWriter(outFile);
		}
		catch (IOException e)
		{
			System.err.println("\n\n\nERROR: Cannot create " + outFileName + " file.\n\n\n");
			System.exit(2);
		}
	}
	  public void printIt() 
	  {
	        int totalScores = 0;
	        for (int i = 0; i < scores.length; i++) 
	        {
	            totalScores += scores[i];
	        }
	        
	        System.out.println();
	        System.out.println("Data for: " + teacherData[0]);
	        System.out.println("Course number: " + teacherData[1]);
	        System.out.println("Course: " + teacherData[2]);
	        System.out.println("Room: " + teacherData[3]);
	        System.out.println("Total # of scores = " + totalScores);
	        System.out.println();
	       System.out.println("Data version 1: All scores printed from high to low.");
	        int countPerLine = 0;
	        for (int s = 100; s >= 0; s--) 
	        {
	            for (int i = 0; i < scores[s]; i++)
	            {
	                System.out.printf("%5d", s);
	                countPerLine++;
	                if (countPerLine == 15) 
	                {
	                    System.out.println();
	                    countPerLine = 0;
	                }
	            }
	        }
	        if (countPerLine != 0)
	            System.out.println();
	        
	        System.out.println();
	        System.out.println("Data version 3: Total number of each letter grade");
	        System.out.printf("A (90 - 100): %d  %5.2f%%\n", grades[0], totalScores==0 ? 0.0 : grades[0]*100.0/totalScores);
	        System.out.printf("B (80 - 89) : %d  %5.2f%%\n", grades[1], totalScores==0 ? 0.0 : grades[1]*100.0/totalScores);
	        System.out.printf("C (70 - 79) : %d  %5.2f%%\n", grades[2], totalScores==0 ? 0.0 : grades[2]*100.0/totalScores);
	        System.out.printf("D (60 - 69) : %d  %5.2f%%\n", grades[3], totalScores==0 ? 0.0 : grades[3]*100.0/totalScores);
	        System.out.printf("F (0 - 59)  : %d  %5.2f%%\n", grades[4], totalScores==0 ? 0.0 : grades[4]*100.0/totalScores);
	    }
}
