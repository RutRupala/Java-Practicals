import java.util.Scanner;

public class GuardedCalculator {
    static double calculate(double a, double b, char op) throws DivideByZeroException {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new DivideByZeroException("Cannot divide " + a + " by zero");
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Unknown operator: " + op);
        };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int attempt = 0;
        boolean done = false;
        while (!done && sc.hasNextLine()) {
            attempt++;
            try {
                System.out.print("Enter: number1 operator number2 > ");
                String[] p = sc.nextLine().trim().split("\\s+");
                double a = Double.parseDouble(p[0]);
                char op = p[1].charAt(0);
                double b = Double.parseDouble(p[2]);
                System.out.println("Result = " + calculate(a, b, op));
                done = true;
            } catch (DivideByZeroException e) {
                System.out.println("Math error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number: " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("Bad input: " + e.getMessage());
            } finally {
                System.out.println("[log] attempt #" + attempt + " finished");
            }
        }
    }
}
