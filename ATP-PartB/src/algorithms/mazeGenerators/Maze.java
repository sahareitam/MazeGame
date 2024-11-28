package algorithms.mazeGenerators;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Random;

public class Maze implements Serializable {
    private int columns;
    private int rows;
    private int[][] grid;
    private Position startPosition;
    private Position goalPosition;

    /**
     * Constructor to create a maze with the specified number of rows and columns.
     *
     * @param rows    The number of rows in the maze.
     * @param columns The number of columns in the maze.
     */
    public Maze(int rows, int columns) {
        this.columns = columns;
        this.rows = rows;
        this.grid = new int[rows][columns];
        this.startPosition = setRandomStartPosition();
        this.goalPosition = setRandomGoalPosition();
    }

    /**
     * Sets the start position of the maze randomly along its borders.
     *
     * @return The randomly generated start position.
     */    public Position setRandomStartPosition() {
        Random random = new Random();
        int side = random.nextInt(4); // Randomly choose one of the four sides

        switch (side) {
            case 0: // Top side
                return new Position(0, random.nextInt(columns));
            case 1: // Right side
                return new Position(random.nextInt(rows), columns - 1);
            case 2: // Bottom side
                return new Position(rows - 1, random.nextInt(columns));
            case 3: // Left side
                return new Position(random.nextInt(rows), 0);
            default:
                return null;
        }
    }

    // Method to set goal position randomly along the borders, avoiding duplication with start position
    public Position setRandomGoalPosition() {
        Random random = new Random();
        Position tempGoalPosition;
        do {
            int side = random.nextInt(4); // Randomly choose one of the four sides

            switch (side) {
                case 0: // Top side
                    tempGoalPosition = new Position(0, random.nextInt(columns));
                    break;
                case 1: // Right side
                    tempGoalPosition = new Position(random.nextInt(rows), columns - 1);
                    break;
                case 2: // Bottom side
                    tempGoalPosition = new Position(rows - 1, random.nextInt(columns));
                    break;
                case 3: // Left side
                    tempGoalPosition = new Position(random.nextInt(rows), 0);
                    break;
                default:
                    return null;
            }
        } while (tempGoalPosition.equals(startPosition)); // Ensure goal position is not the same as start position

        return tempGoalPosition;
    }

    // Getters and Setters
    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void setCell(int row, int col, int i) {
        this.grid[row][col] = i;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }

    // Method to print the maze
    public void print() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (startPosition != null && startPosition.getRowIndex() == i && startPosition.getColumnIndex() == j) {
                    System.out.print("S ");
                } else if (goalPosition != null && goalPosition.getRowIndex() == i && goalPosition.getColumnIndex() == j) {
                    System.out.print("E ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private void enterByteArray(int num, byte[] arr, int index)
    {
        byte [] temp= ByteBuffer.allocate(4).putInt(num).array();
        for (int i=0;i<temp.length;i++) {
            arr[index] = temp[i];
            index++;
        }
    }

    public byte[] toByteArray()
    {
        byte [] m=new byte[grid.length*grid[0].length+4*6];
        int index=24;
        enterByteArray(grid.length,m,0);
        enterByteArray(grid[0].length,m,4);
        enterByteArray(startPosition.getRowIndex(),m,8);
        enterByteArray(startPosition.getColumnIndex(),m,12);
        enterByteArray(goalPosition.getRowIndex(),m,16);
        enterByteArray(goalPosition.getColumnIndex(),m,20);

        for (int i=0;i<grid.length;i++)
            for (int j=0;j<grid[0].length;j++)
            {
                m[index]=(byte)grid[i][j];
                index++;
            }

        return  m;
    }


    private int convertToInt (byte [] arr, int index)
    {
        byte[] temp=new byte[4];
        for (int i=0;i<4;i++) {
            temp[i] = arr[index];
            index++;
        }
        return ByteBuffer.wrap(temp).getInt();
    }

    public Maze(byte [] arr) throws Exception {
        int index=24;
        int row=convertToInt(arr,0);
        int col=convertToInt(arr,4);
        startPosition=new Position(convertToInt(arr,8),convertToInt(arr,12));
        goalPosition=new Position(convertToInt(arr,16),convertToInt(arr,20));
        grid=new int[row][col];

        for (int i=0;i<grid.length;i++)
            for (int j=0;j<grid[0].length;j++)
            {
                grid[i][j]=arr[index];
                index++;
            }
    }

}
