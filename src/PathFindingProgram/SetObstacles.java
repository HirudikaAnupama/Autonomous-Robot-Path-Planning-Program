package PathFindingProgram;


import java.util.Scanner;

public class SetObstacles {

    // ANSI escape codes for color formatting
    private static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";


    // Number of rows and columns in the grid.
    private final int rowCount;
    private final int columnCount;


    // Grid representation for set obstacles as a 2D array of strings.
    private final String[][] gridWithObstacles;



    // Get number of rows and columns calling GridRepresentation class
    public SetObstacles(GridRepresentation grid) {
        this.gridWithObstacles = grid.getGrid();
        this.rowCount = grid.getRowCount();
        this.columnCount = grid.getColumnCount();

    }


    // Method to add obstacles to the grid
    public void addObstacles() {
        Scanner scanner = new Scanner(System.in);

        // Using scanner method to get obstacles count by user input
        System.out.print("\nEnter the number of obstacles to insert: ");
        int numOfObstacles = scanner.nextInt();

        while (numOfObstacles >= (rowCount * columnCount)-((rowCount * columnCount)/2)) {
            System.out.print(ANSI_RED + "\nToo many obstacles! Enter valid obstacle count : " + ANSI_RESET);
            numOfObstacles = scanner.nextInt();
        }

        for (int i = 0; i < numOfObstacles; i++) {

            // Get obstacle coordinates using user inputs according to the entered obstacle count
            System.out.println("\nEnter obstacle coordinates (row column) : ");
            System.out.print("Enter row coordinates : ");
            int row = scanner.nextInt();
            System.out.print("Enter column coordinates : ");
            int col = scanner.nextInt();



            // Check that the validations are correct
            if (isValidPosition(row, col)) {
                gridWithObstacles[row][col] = "O";
            } else {
                System.out.println(ANSI_RED + "\nInvalid coordinates! Enter new obstacle coordinates." + ANSI_RESET);
                i--;  // Decrement i to re-enter the obstacle coordinates
            }
        }
    }
    // Method to check if the given position is valid for adding an obstacle
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rowCount && col >= 0 && col < columnCount && !gridWithObstacles[row][col].equals("O");
    }



    // Method to display the grid with obstacles, highlighting obstacles in red color
    public void displayGrid() {

        System.out.print("\nGrid representation with obstacles\n");
        System.out.println();

        // Print horizontal numbering
        System.out.print("    ");
        for (int j = 0; j < columnCount; j++) {
            System.out.printf("%-4d", j);
        }
        System.out.println();

        // Print grid with obstacles
        for (int i = 0; i < rowCount; i++) {
            // Print vertical numbering
            System.out.printf("%-4d", i);

            for (int j = 0; j < columnCount; j++) {
                if (gridWithObstacles[i][j].equals("O")) {
                    // Print obstacle in red
                    System.out.print(ANSI_RED + "■" + ANSI_RESET + "   ");
                } else {
                    // Print non-obstacle cell
                    System.out.print(ANSI_GREEN + gridWithObstacles[i][j]+ ANSI_RESET + "   ");
                }
            }
            System.out.println();
        }
    }



    // Method to get the grid with obstacles
    public String[][] getGridWithObstacles() {
        return gridWithObstacles;
    }


}
