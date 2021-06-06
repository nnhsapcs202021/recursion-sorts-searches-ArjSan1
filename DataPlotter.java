import java.awt.*;
import java.io.*;
import java.util.*;
/**
 * This DataPlotter object reads a space delimited text file of elevations
 * and plots the result as a range of greyscale images, and then calculates 
 * and plots the steepest downward path from each location in the image.
 * 
 * @author 
 * @version 
 */
public class DataPlotter
{
    private static String fileName = "Colorado";
    private static int[][] grid;
    private static DrawingPanel panel;
    private static Scanner fileReader;
    private static int rows, cols;
    public static void main(String[] args) throws IOException
    {
        readValues();
        plotData();
        try {Thread.sleep(3000); } catch (Exception e){};  // pause display for 3 seconds
        plotAllPaths();
    }

    private static void readValues() throws IOException
    {
        fileReader = new Scanner(new File(fileName + ".dat"));
        rows = fileReader.nextInt();    // first integer in file
        cols = fileReader.nextInt();    // second integer in file

        // instantiate and initialize the instance variable grid 
        grid = new int[rows][cols];
        // then read all of the data into the array in row major order
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                grid[i][j] = fileReader.nextInt();

            }

        }
        //System.out.println("" + grid[0][0] + grid[0][cols-1] + grid[rows-1][0] + grid[rows-1][cols-1]);
    }

    // plot the altitude data read from file
    private static void plotData()
    {
        panel = new DrawingPanel(cols, rows);
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j] < min)
                {

                    min = grid[i][j];
                }
                else if(grid[i][j] > max)
                {
                    max = grid[i][j];

                }
            }

        }

        double scaleFactor = 255.0 / (max-min);
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                int value = (int) ((grid[i][j] - min) *  scaleFactor);
                Color color = new Color(value, value, value);
                panel.setPixel(j , i, color);
            }

        }

    }
    // for a given x, y value, plot the downhill path from there
    private static void plotDownhillPath(int x, int y)
    {
        boolean isSmallestPoint = false;
        ArrayList<Point> surroundingPoints = new ArrayList<Point>();
        //north
        if(x-1 > -1)
        {
            Point north = new Point(x-1, y);
            surroundingPoints.add(north);   

            if(y+1 < grid[0].length)
            {
                Point northEast = new Point(x-1,y+1);
                surroundingPoints.add(northEast);

            }
            if(y-1 > -1 )
            {
                Point northWest = new Point(x-1, y-1);
                surroundingPoints.add(northWest);

            }
        }
        //northeast

        //northwest

        //South
        if(x+1 < grid.length)
        {
            Point south = new Point(x+1, y );
            surroundingPoints.add(south);
            if(y-1 > -1 )
            {
                Point southWest = new Point(x+1, y-1);
                surroundingPoints.add(southWest);

            }
            if(y+1 < grid[0].length )

            {
                Point southEast = new Point(x+1, y+1);
                surroundingPoints.add(southEast);

            }

        }
        //southwest

        //East
        if(y+1 < grid[0].length)
        {

            Point east = new Point(x, y+1);
            surroundingPoints.add(east);

        }
        //west
        if(y-1 > -1)
        {

            Point west = new Point(x, y-1);
            surroundingPoints.add(west);

        }
        int height = grid[x][y];
        for(int i = 0; i<surroundingPoints.size();i++)
        {

            int newHeight = grid[(int)surroundingPoints.get(i).getX()][(int)surroundingPoints.get(i).getY()];

            if(height > newHeight)
            {

                isSmallestPoint = false;

            }
            else
            {

                isSmallestPoint = true;
            }
        }
        int smallestHeight;
        Point smallestPoint = new Point(x,y);
        if(isSmallestPoint)
        {
            panel.setPixel(y,x,Color.blue);

        }
        else
        {
            for(int i = 0; i < surroundingPoints.size(); i++)
            {
                smallestHeight =  grid[(int)smallestPoint.getX()][(int)smallestPoint.getY()];
                int newHeight = grid[(int)surroundingPoints.get(i).getX()][(int)surroundingPoints.get(i).getY()];
                if(smallestHeight > newHeight)
                {
                    smallestPoint = new Point((int)surroundingPoints.get(i).getX(), (int)surroundingPoints.get(i).getY());

                }
            }
            int smallestX = (int) smallestPoint.getX();
            int smallestY = (int) smallestPoint.getY();
            panel.setPixel(smallestY, smallestX, Color.blue);
            plotDownhillPath(smallestX, smallestY);
        }
    }

    private static void plotAllPaths()
    {
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j<grid[0].length; j++)
            {
                plotDownhillPath(i,j);

            }
        }
    }

}