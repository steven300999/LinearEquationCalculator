import java.util.InputMismatchException;
import java.util.Scanner;

public class LinearEquationLogic {
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean repeat = true;
        while (repeat) {
            try {
                System.out.print("Enter the first point without parentheses: ");
                Point point1 = parsePoint();

                System.out.print("Enter the second point without parentheses: ");
                Point point2 = parsePoint();

                LinearEquation equation = new LinearEquation(point1, point2);

                System.out.println(equation.lineInfo());

                System.out.print("Enter an x value: ");
                double x = scanner.nextDouble();
                System.out.println("Corresponding point on the line: " + equation.coordinateForX(x));

                System.out.print("Repeat? (yes/no): ");
                String choice = scanner.next().toLowerCase();
                repeat = choice.equals("yes");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid coordinates and numbers.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private Point parsePoint() throws InputMismatchException {
        while (true) {
            System.out.print("x, y: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Please enter a valid coordinate pair.");
                continue;
            }

            try {
                String[] parts = input.split(",");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid format. Expected x, y.");
                }
                int x = Integer.parseInt(parts[0].trim());
                int y = Integer.parseInt(parts[1].trim());

                return new Point(x, y);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please use the format 'x, y'.");
            }
        }
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
