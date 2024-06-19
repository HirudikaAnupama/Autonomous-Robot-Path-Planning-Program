package PathFindingProgram;

import java.util.Scanner;

public class Main {

    // ANSI escape codes for color formatting
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            System.out.print("\n======= Welcome to the shortest path search game =======\n");

            //--------------------Grid Representation-------------------------------------------------------------------------------//
            int rowCount = 0;
            int columnCount = 0;

            // Loop until valid row and column counts are provided
            while (rowCount * columnCount < 4) {
                // Prompt the user to input the row count for the grid
                System.out.print("\nEnter row count (minimum 2): ");
                rowCount = scanner.nextInt();

                // Prompt the user to input the column count for the grid
                System.out.print("Enter column count (minimum 2): ");
                columnCount = scanner.nextInt();

                // Check if the product of row count and column count is less than 4
                if (rowCount * columnCount < 4) {
                    System.out.println(ANSI_RED + "\nThe grid is not enough to find the path! Enter row and column count again." + ANSI_RESET);
                }
            }

            // Create a GridRepresentation object with the provided dimensions
            GridRepresentation grid = new GridRepresentation(rowCount, columnCount);

            // Call generateGrid method
            grid.generateGrid();

            // Call display method
            grid.display();

            //------------------------Set Obstacles-------------------------------------------------------------------------------------//

            // Create a SetObstacles object with the provided dimensions
            SetObstacles gridWithObstacles = new SetObstacles(grid);

            // Add obstacles to the grid
            gridWithObstacles.addObstacles();

            // Display the grid with obstacles
            gridWithObstacles.displayGrid();

            //-----------------------Robot State------------------------------------------------------------------------------------------//

            int startRowCoordinate = 0;
            int startColumnCoordinate = 0;
            int goalRowCoordinate = 0;
            int goalColumnCoordinate = 0;

            boolean coordinatesValid = false;
            boolean StartingAndGoalCoordinate = false;

            // Loop until valid coordinates are entered
            while (!coordinatesValid || !StartingAndGoalCoordinate) {
                System.out.print("\nSet starting and goal point according to the grid numbering\n");
                // Input starting and goal points coordinates
                System.out.print("\nEnter starting point row coordinate: ");
                startRowCoordinate = scanner.nextInt();
                System.out.print("Enter starting point column coordinate: ");
                startColumnCoordinate = scanner.nextInt();
                System.out.print("\nEnter goal point row coordinate: ");
                goalRowCoordinate = scanner.nextInt();
                System.out.print("Enter goal point column coordinate: ");
                goalColumnCoordinate = scanner.nextInt();

                // Check starting and goal point coordinates are same or not
                if (startRowCoordinate == goalRowCoordinate && startColumnCoordinate == goalColumnCoordinate) {
                    System.out.print(ANSI_RED + "\nStarting point and goal point coordinates are same! Enter new coordinates \n" + ANSI_RESET);
                } else {
                    // Check if coordinates are valid
                    if (startRowCoordinate < 0 || startRowCoordinate >= grid.getRowCount() ||
                            startColumnCoordinate < 0 || startColumnCoordinate >= grid.getColumnCount() ||
                            goalRowCoordinate < 0 || goalRowCoordinate >= grid.getRowCount() ||
                            goalColumnCoordinate < 0 || goalColumnCoordinate >= grid.getColumnCount()) {
                        System.out.println(ANSI_RED + "\nEnter correct row and column coordinates within the input grid size" + ANSI_RESET);
                    } else {
                        // Coordinates are valid, exit the loop
                        coordinatesValid = true;
                        StartingAndGoalCoordinate = true;
                    }
                }
            }

            // Create a SetRobotStates object with the provided dimensions
            SetRobotStates robotState = new SetRobotStates(startRowCoordinate, startColumnCoordinate,
                    goalRowCoordinate, goalColumnCoordinate, grid);

            // Add starting point and goal point
            robotState.setStartingPoint();

            // Display the grid with starting and goal point
            robotState.display();

            //------------------Path finding algorithm--------------------------------------------------------------------------------//

            // Find and display path
            PathFindingAlgorithm find = new PathFindingAlgorithm(robotState, grid);
            CustomLinkedList path = find.pathFind();
            find.display(path);

            // Ask the user if they want to play again
            System.out.print("\nDo you want to play again? (yes/no): ");
            String userResponse = scanner.next().trim().toLowerCase();
            if (!userResponse.equals("yes")) {
                playAgain = false;
                System.out.print("\nThank you for playing! Goodbye!\n");
            }
        }

        scanner.close();
    }
}
