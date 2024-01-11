package MazeSolver;

/***** Imports *****/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * [Maze.java]
 * This class creates a maze object
 * @author Sally Jeong
 * @version 1.0, November 2021
 */
public class Maze {

    /***** Variables *****/
    private int rowCount;
    private int colCount;
    private char [][]map;
    private File file;

    /**
     * Constructs a new maze
     * @param file A File holding the maze
     */
    public Maze(File file){
        this.file = file;
    }

    /**
     * getMap
     * This method returns the map
     * @return char[][], 2D array of the maze
     */
    public char[][] getMap(){
        return this.map;
    }

    /**
     * setMap
     * This method sets the given index of the map given to the character given
     * @param row  An integer that holds the row number
     * @param col  An integer that holds the column number
     * @param character An integer that holds the character
     */
    public void setMap(int row, int col, char character){
        map[row][col]=character;
    }

    /**
     * getRowCount
     * This method returns the number of rows in the map
     * @return int, the number of rows
     */
    public int getRowCount(){
        return this.rowCount;
    }

    /**
     * getColCount
     * This method returns the number of columns in the map
     * @return int, the number of columns
     */
    public int getColCount(){
        return this.colCount;
    }

    /**
     * getSize
     * This method finds the size of the map
     */
    public void getSize(){
        rowCount=0;
        colCount=0;

        try {
            Scanner readFile = new Scanner(file);
            String line="";

            while (readFile.hasNextLine()) {
                rowCount++;
                line = readFile.nextLine();
            }
            colCount = line.length();
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /**
     * getData
     * This method extracts data from a file and stores it into an array
     */
    public void getData(){
        getSize();

        try {
            Scanner readFile = new Scanner(file);
            String line;
            map = new char[rowCount][colCount];

            while (readFile.hasNext()) {
                for (int i = 0; i < rowCount; i++) {
                    line = readFile.nextLine();
                    for (int j = 0; j < colCount; j++) {
                        map[i][j] = line.charAt(j);
                    }
                }
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}