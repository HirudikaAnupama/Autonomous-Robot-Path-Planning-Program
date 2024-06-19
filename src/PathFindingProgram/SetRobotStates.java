package PathFindingProgram;
public class SetRobotStates {

    // ANSI escape codes for color formatting
    public static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";


    // Coordinates of the starting point and goal point
    private final int starting_Point_Row_Coordinate;
    private final int starting_Point_Column_Coordinate;
    private final int goal_Point_Row_Coordinate;
    private final int goal_Point_Column_Coordinate;


    // Number of rows and columns in the grid.
    private final int rowCount;
    private final int columnCount;


    // Grid representation for set starting and goal point as a 2D array of strings.
    private final String[][] gridWithRobotStates;


    // Constructs a RobotState object with given coordinates and grid representation.
    public SetRobotStates(int startRowCoordinate,int startColumnCoordinate,int goalRowCoordinate,
                          int goalColumnCoordinate,GridRepresentation grid) {

        this.starting_Point_Row_Coordinate = startRowCoordinate;
        this.starting_Point_Column_Coordinate = startColumnCoordinate;
        this.goal_Point_Row_Coordinate = goalRowCoordinate;
        this.goal_Point_Column_Coordinate = goalColumnCoordinate;


        // Get number of rows and columns using GridRepresentation class
        SetObstacles obstacles = new SetObstacles(grid);

        this.rowCount = grid.getRowCount();
        this.columnCount = grid.getColumnCount();
        this.gridWithRobotStates= obstacles.getGridWithObstacles(); //Get the obstacle grid within the set obstacle class
    }


    // Sets the starting and goal points on the grid.
    public void setStartingPoint() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (starting_Point_Row_Coordinate == i && starting_Point_Column_Coordinate == j) {
                    gridWithRobotStates[i][j] = "S";
                }
                if (goal_Point_Row_Coordinate == i && goal_Point_Column_Coordinate == j) {
                    gridWithRobotStates[i][j] = "G";
                }
            }
        }
    }


    // Displays the grid representation with obstacles, starting, and goal points
    public void display() {

        System.out.print("\nGrid representation with obstacles and starting and goal point\n" +
                "(starting point = 'S' goal point = 'G' )\n\n");

        // Print horizontal numbering
        System.out.print("    ");
        for (int j = 0; j < columnCount; j++) {
            System.out.printf("%-4d", j);
        }
        System.out.println();

        // Print grid with obstacles , starting and goal point.
        for (int i = 0; i < rowCount; i++) {
            // Print vertical numbering
            System.out.printf("%-4d", i);

            for (int j = 0; j < columnCount; j++) {

                // Print start cell in blue
                if (gridWithRobotStates[i][j].equals("S")) {
                    System.out.print(ANSI_BLUE + gridWithRobotStates[i][j] + ANSI_RESET + "   ");

                    // Print goal cell in blue
                } else if (gridWithRobotStates[i][j].equals("G")) {
                    System.out.print(ANSI_BLUE + gridWithRobotStates[i][j] + ANSI_RESET + "   ");

                    // Print obstacle cell in red
                } else if (gridWithRobotStates[i][j].equals("O")) {
                    System.out.print(ANSI_RED + "■" + ANSI_RESET + "   ");

                    // Print non-obstacle cell in green
                } else {
                    System.out.print(ANSI_GREEN + gridWithRobotStates[i][j] + ANSI_RESET + "   ");
                }
            }
            System.out.println();
        }
    }




    // Gets the starting-row-coordinate of the starting point.
    public int getStarting_Point_Row_Coordinate(){
        return starting_Point_Row_Coordinate;
    }


    // Gets the starting-column-coordinate of the starting point.
    public int getStarting_Point_Column_Coordinate(){
        return starting_Point_Column_Coordinate;
    }


    // Gets the goal-row-coordinate of the starting point.
    public int getGoal_Point_Row_Coordinate(){
        return goal_Point_Row_Coordinate;
    }


    // Gets the goal-column-coordinate of the starting point.
    public int getGoal_Point_Column_Coordinate(){
        return goal_Point_Column_Coordinate;
    }

    // Gets the new grid with robot states and obstacles.
    public String[][] getNewGrid(){
        return gridWithRobotStates;
    }


}
