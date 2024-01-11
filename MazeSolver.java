package MazeSolver;

/***** Imports *****/
import javax.swing.JOptionPane;
import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * [MazeSolver.java]
 * This class recursively traverses through a maze to find a path from the start to the end
 * @author Sally Jeong
 * @version 1.0, November 2021
 */
public class MazeSolver {

    public static void main (String[]args){

        /***** Variables *****/
        int startCol;
        int startRow;
        int exitCol;
        int exitRow;

        String fileName = JOptionPane.showInputDialog(null,"Enter file name: ");

        File mazeFile = new File("Mazes/"+fileName+".txt");

        Maze maze = new Maze(mazeFile);

        maze.getData();

        Visualizer visualizer = new Visualizer(maze);

        do{
            startRow=randomNumber(maze.getRowCount());
            startCol=randomNumber(maze.getColCount());
        } while(maze.getMap()[startRow][startCol]!=Const.EMPTY);

        maze.setMap(startRow, startCol, Const.START);

        do{
            exitRow=randomNumber(maze.getRowCount());
            exitCol=randomNumber(maze.getColCount());
        } while(maze.getMap()[exitRow][exitCol]!=Const.EMPTY);

        maze.setMap(exitRow, exitCol, Const.EXIT);

        if (solveMaze(maze, startRow, startCol)){
            System.out.println("Maze solved!");
        }else{
            System.out.println("Maze is unsolvable :(");
        }
    }

    /**
     * solveMaze
     * This method finds a path from start to destination by recursively moving through the map
     * @param maze A Maze object that holds the 2D char array to traverses through
     * @param row An integer that holds the row index
     * @param col An integer that holds the column index
     */
    public static boolean solveMaze(Maze maze, int row, int col){
        final int SLEEP_TIME = 50;
        int[][]direction = {{0,1},{1,0},{0,-1},{-1,0}};

        try {
            TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Sleep Interrupted");
        }

        printMaze(maze);

        /***** base case - arrive at destination *****/
        if (maze.getMap()[row][col]==Const.EXIT) {
            return true;
        }

        if (maze.getMap()[row][col]!=Const.START) {
            maze.setMap(row, col, Const.VISIT);
        }

        /***** moving in 4 directions *****/
        for (int i = 0; i<direction.length; i++){
            char currentChar = maze.getMap()[row+direction[i][0]][col+direction[i][1]];
            if (((currentChar==Const.EMPTY) ||(currentChar==Const.EXIT)) && (solveMaze(maze, row + direction[i][0], col + direction[i][1]))){
                return true;
            }
        }

        maze.setMap(row,col, Const.DEAD_END);
        return false;
    }

    /**
     * randomNumber
     * This method generates a random number below the set upper bound
     * @param max An integer that holds the upper bound
     * @return int, a random number within the set upperbound
     */
    public static int randomNumber(int max){
        Random random = new Random();
        return random.nextInt(max-1);
    }

    /**
     * printMaze
     * This method prints the maze
     * @param maze A Maze object that holds the map (2D char array of the maze)
     */
    public static void printMaze(Maze maze){
        for (int i = 0; i<maze.getRowCount(); i++){
            for (int j = 0; j<maze.getColCount(); j++){
                System.out.print(maze.getMap()[i][j]);
            }
            System.out.println();
        }
        System.out.println("_____________________________________________________________");
    }
}