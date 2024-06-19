package PathFindingProgram;

public class GridRepresentation {

    // ANSI escape codes for color formatting.
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";



    // Number of rows and columns in the grid.
    private final int rowCount;
    private final int columnCount;

    // Grid representation as a 2D array of strings.
    private final String[][] grid;


    // Constructs a GridRepresentation object with the specified number of rows and columns.
    public GridRepresentation(int row, int column) {
        this.rowCount = row;
        this.columnCount = column;
        this.grid = new String[rowCount][columnCount];
    }


    // Fills the grid with default values ('X') using 'generateGrid' method.
    public void generateGrid() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                this.grid[i][j] = "X";
            }
        }
    }


    // 'display' method for display generated grid.
    public void display() {

        // Display grid dimensions for identifying rows and columns
        System.out.println("\n" + rowCount + "x" + columnCount + " grid for finding the shortest path\n");

        // Display horizontal numbering for columns
        System.out.print("    ");
        for (int j = 0; j < columnCount; j++) {
            System.out.printf("%-4d", j);
        }
        System.out.println();

        // Print grid
        for (int i = 0; i < rowCount; i++) {
            // Print vertical numbering
            System.out.printf("%-4d", i);
            for (int j = 0; j < columnCount; j++) {
                System.out.print(ANSI_GREEN + grid[i][j]+ ANSI_RESET + "   ");
            }
            System.out.println();
        }
    }



    // Retrieves the grid representation.
    public String[][] getGrid() {
        return grid;
    }


    // Retrieves the number of rows in the grid.
    public int getRowCount() {
        return rowCount;
    }


    // Retrieves the number of columns in the grid.
    public int getColumnCount() {
        return columnCount;
    }


}
