package PathFindingProgram;

public class PathFindingAlgorithm {
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
    private final String[][] newGrid;

    public PathFindingAlgorithm(SetRobotStates robotState, GridRepresentation grid) {

        this.starting_Point_Row_Coordinate = robotState.getStarting_Point_Row_Coordinate();
        this.starting_Point_Column_Coordinate = robotState.getStarting_Point_Column_Coordinate();
        this.goal_Point_Row_Coordinate = robotState.getGoal_Point_Row_Coordinate();
        this.goal_Point_Column_Coordinate = robotState.getGoal_Point_Column_Coordinate();
        this.rowCount = grid.getRowCount();
        this.columnCount = grid.getColumnCount();
        this.newGrid = robotState.getNewGrid();
    }


    public CustomLinkedList pathFind() {
        CustomLinkedList path = new CustomLinkedList();
        CustomQueue queue = new CustomQueue(rowCount * columnCount);

        boolean[][] visited = new boolean[rowCount][columnCount];


        int[][] parentRow = new int[rowCount][columnCount];
        int[][] parentColumn = new int[rowCount][columnCount];

        queue.enqueue(new int[]{starting_Point_Row_Coordinate, starting_Point_Column_Coordinate});
        visited[starting_Point_Row_Coordinate][starting_Point_Column_Coordinate] = true;

        while (!queue.isEmpty()) {

            int[] current = queue.dequeue();
            int grid_Row_Coordinate = current[0];
            int grid_Column_Coordinate = current[1];


            // Define movement directions (including diagonals)
            int[] dx = {0, 1, 0, -1, -1, 1, -1, 1};
            int[] dy = {-1, 0, 1, 0, -1, -1, 1, 1};

            // Loop over 8 directions
            for (int i = 0; i < 8; i++) {
                int nextX = grid_Row_Coordinate + dx[i];
                int nextY = grid_Column_Coordinate + dy[i];

                // Check if the next cell is within the grid boundaries, not visited yet, and not an obstacle
                if (isValid(nextX, nextY) && !visited[nextX][nextY] && !newGrid[nextX][nextY].equals("O")) {

                    // Check for obstacle avoidance in diagonal movement
                    if (dx[i] != 0 && dy[i] != 0) {
                        // Calculate the coordinates of the two adjacent cells in diagonal movement
                        int obsX1 = grid_Row_Coordinate + dx[i];
                        int obsY1 = grid_Column_Coordinate;
                        int obsX2 = grid_Row_Coordinate;
                        int obsY2 = grid_Column_Coordinate + dy[i];

                        // Check if either of the adjacent cells is an obstacle
                        if ((isValid(obsX1, obsY1) && newGrid[obsX1][obsY1].equals("O")) ||
                                (isValid(obsX2, obsY2) && newGrid[obsX2][obsY2].equals("O"))) {
                            continue; // Skip this movement if there's an obstacle diagonally or adjacent to it
                        }
                    }

                    queue.enqueue(new int[]{nextX, nextY});

                    visited[nextX][nextY] = true;
                    parentRow[nextX][nextY] = grid_Row_Coordinate;
                    parentColumn[nextX][nextY] = grid_Column_Coordinate;


                }

                // Check if the current coordinates match the goal coordinates
                if (grid_Row_Coordinate == goal_Point_Row_Coordinate && grid_Column_Coordinate == goal_Point_Column_Coordinate) {
                    // Backtrack while the current coordinates are not the starting point
                    while (grid_Row_Coordinate != starting_Point_Row_Coordinate || grid_Column_Coordinate != starting_Point_Column_Coordinate) {
                        // Insert the current coordinates into the path
                        path.insert(new int[]{grid_Row_Coordinate, grid_Column_Coordinate});

                        // Get the previous coordinates from the parent arrays
                        int prevX = parentRow[grid_Row_Coordinate][grid_Column_Coordinate];
                        int prevY = parentColumn[grid_Row_Coordinate][grid_Column_Coordinate];

                        // Update the current coordinates to the previous coordinates
                        grid_Row_Coordinate = prevX;
                        grid_Column_Coordinate = prevY;
                    }
                    // Insert the starting point coordinates into the path
                    path.insert(new int[]{starting_Point_Row_Coordinate, starting_Point_Column_Coordinate});
                    // Return the path
                    return path;
                }
            }
        }

        return null;
    }


    // Checks if the given coordinates (x, y) are within the boundaries of the grid.
    private boolean isValid(int x, int y) {
        return x >= 0 && x < rowCount && y >= 0 && y < columnCount;
    }



    public void display(CustomLinkedList path) {
        // Check if the path is valid
        if (path == null || path.isEmpty()) {
            System.out.println(ANSI_RED + "\nNo path found! Enter correct coordinates." + ANSI_RESET);
            return;
        }

        // Create a boolean array to mark the cells in the path
        boolean[][] isPath = new boolean[rowCount][columnCount];

        // Create a linked list to store the path coordinates
        CustomLinkedList pathCoordinates = new CustomLinkedList();

        // Mark the cells in the path and store the coordinates
        while (!path.isEmpty()) {
            int[] cell = path.returnData();
            isPath[cell[0]][cell[1]] = true;
            pathCoordinates.insert(cell);
        }



        System.out.print("\nShortest path according to the your input data" +
                "\nPath = ' "+ ANSI_BLUE + "." + ANSI_RESET +" '\n\n");

        // Print horizontal numbering
        System.out.print("    ");
        for (int j = 0; j < columnCount; j++) {
            System.out.printf("%-4d", j);
        }
        System.out.println();

        for (int i = 0; i < rowCount; i++) {
            // Print vertical numbering
            System.out.printf("%-4d", i);
            for (int j = 0; j < columnCount; j++) {
                if (newGrid[i][j].equals("S") || newGrid[i][j].equals("G")) {
                    System.out.print(ANSI_BLUE + newGrid[i][j] + ANSI_RESET + "   ");
                } else if (newGrid[i][j].equals("O")) {
                    System.out.print(ANSI_RED + "■" + ANSI_RESET + "   ");
                } else if (isPath[i][j]) {
                    System.out.print(ANSI_BLUE + ".   " + ANSI_RESET);
                } else {
                    System.out.print(ANSI_GREEN + newGrid[i][j] + ANSI_RESET + "   ");
                }
            }
            System.out.println();
        }


    }
}
