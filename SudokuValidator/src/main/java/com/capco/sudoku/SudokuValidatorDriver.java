package com.capco.sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SudokuValidatorDriver {

	public static void main(String[] args) throws FileNotFoundException {
		String filename = null;
		int input = 0;
		if (args == null || args.length == 0 || args[0] == null)
			throw new IllegalArgumentException(" File Arguement is missing ");

		filename = args[0];

		File file = new File(filename);
		if (!file.exists())
			throw new FileNotFoundException("File is not present " + filename);

		int[][] sudoku = new int[9][9];
		int i = 0, j = 0;
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line;
		try {
			line = bufferedReader.readLine();
			while (line != null) {
				//System.out.println( "i=" + i + " j=" + j);
				//System.out.println(line);
			   String[] st= line != null ? line.split(",") : null;
			   for(String s:st)
			   {
				   sudoku[i][j] = Integer.parseInt(s);
				    j++;
			   }
			   //System.out.println( "i=" + i + " j=" + j);
			   line = bufferedReader.readLine();
			   i++;
			   j = 0;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n***Input Sudoku***");
		for (i = 0; i < 9; i++){
			for (j = 0; j < 9; j++) {
				System.out.print(sudoku[i][j] +" " );
			}
			System.out.println();
		}
		
		String isRangeCorrect = 0 == rangeCheck(sudoku) ? "Correct Range" : "Sudoku contains out of this range [0-9]";
		System.out.println("Is range valid ==>" + isRangeCorrect);
		
		String isRowValid = 0 == isRowValid(sudoku) ? "Row validation passed" : "Row validation failed"; 
		System.out.println("Is Row valid  ==>" + isRowValid);
		//assert  isRowValid(sudoku) < 0 : "Row test failed";
		
		String isInnerCubeValid = 0 == isValidSudoku(sudoku) ? " Inner Cube is valid " : "Inner Cube is not valid";
		System.out.println( "Is Cube valid ==>" + isInnerCubeValid );
		
	}
	
	private static int rangeCheck(int[][] sudoku)
	{
		for(int i = 0; i < 9; i++)
	    {
	        for(int j = 0; j < 9; j++)
	        {
	            if (sudoku[i][j] <= 0 || sudoku[i][j] > 9)
	            {
	                return -1;
	            }
	        }
	    }
		return 0;
	}
	
	private static int isRowValid(int[][] sudoku)
	{
		int result = 0;
		int rowSum = 0;
		for(int i =0 ; i< 9 ;i++)
		{
			for(int num: sudoku[i])
			{
				rowSum +=num;
			}
			if(rowSum != 45) 
				{
				 result = -1; break;
				}
		rowSum = 0;		
		}
		
		return result;
	}
	
	private static int isValidSudoku(int [][] sudoku) {
		
		int cubeValid = 0;
		for(int i = 0;i<9;i+=3)
		{
			for(int j = 0;j<9;j+=3)
			{
				if(!checkCube(sudoku,i,j))
				{
					cubeValid = -1;
					return cubeValid;
				}
			}
		}
		return cubeValid;
	}
	
	static private  boolean checkCube(int [][]sudoku,int i , int j)
	{
		int innerSum = 0;
		for(int a = i;a<i+3;a++)
		{
			for(int b = j;b<j+3;b++)
			{
				innerSum+=sudoku[a][b];
				System.out.print(sudoku[a][b] + " ");
			}
			System.out.println();
		}
		System.out.println("****");
		return innerSum == 45;
	}
	
}
