package MazeSolver;

/***** Imports *****/
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

/**
 * [Visualizer.java]
 * This class creates the JFrame to draw the maze on
 * @author Sally Jeong
 * @version 1.0, November 2021
 */
public class Visualizer extends JFrame {

    /***** Variables *****/
    Maze maze;
    MazePanel panel;

    final int MAX_X = (int) getToolkit().getScreenSize().getWidth();
    final int MAX_Y = (int) getToolkit().getScreenSize().getHeight();
    final int GRIDSIZE = MAX_Y / 100;

    Visualizer(Maze maze) {
        this.maze = maze;
        this.panel = new MazePanel();
        this.getContentPane().add(BorderLayout.CENTER, panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(MAX_X * 2/3, MAX_Y * 2/3);
        this.setVisible(true);
    }

    /**
     * [MazePanel.java]
     * This inner class contains all the details about drawing to the screen
     * @author Sally Jeong
     * @version 1.0, November 2021
     */
    private class MazePanel extends JPanel {

        /**
         * paintComponent
         * This method draws to the screen
         * @param g Graphics for drawing
         */
        @Override
        public void paintComponent(Graphics g)  {
            super.paintComponent(g);
            {
                /***** looping through the 2D array and drawing a rectangle for each element *****/
                for (int i = 0; i < maze.getRowCount(); i++) {
                    for (int j = 0; j < maze.getColCount(); j++) {
                        int rectX = j * GRIDSIZE;
                        int rectY = i * GRIDSIZE;
                        char currentChar = maze.getMap()[i][j];

                        if (currentChar == Const.WALL) {
                            g.setColor(new Color(85, 91, 110));

                        } else if (currentChar == Const.EMPTY) {
                            g.setColor(new Color(250, 249, 249));

                        } else if (currentChar == Const.VISIT) {
                            g.setColor(new Color(126, 230, 224));

                        } else if (currentChar == Const.EXIT) {
                            g.setColor(new Color(255, 99, 51));

                        } else if(currentChar == Const.START){
                            g.setColor(new Color(44, 176, 83));

                        } else if (currentChar == Const.DEAD_END){
                            g.setColor(new Color(147, 182, 191));
                        }
                        g.fillRect(rectX, rectY, GRIDSIZE, GRIDSIZE);
                    }
                }
                this.repaint();
            }
        }
    }
}

