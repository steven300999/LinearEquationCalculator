public class LinearEquation {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public LinearEquation(Point p1, Point p2) {
        this.x1 = p1.x;
        this.y1 = p1.y;
        this.x2 = p2.x;
        this.y2 = p2.y;
    }
    public LinearEquation(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double distance() {
        return Math.round(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) * 100.0) / 100.0;
    }

    public double yIntercept() {
        return Math.round((y2 - y1) * (x1 - x2) * 100.0) / 100.0;
    }

    public String slope() {
        if (x2 == x1) {
            return "∞"; // Represent vertical line as infinity
        }
        int numerator = y2 - y1;
        int denominator = x2 - x1;
        return simplifyFraction(numerator, denominator);
    }

    private String simplifyFraction(int numerator, int denominator) {
        int gcd = calculateGCD(Math.abs(numerator), Math.abs(denominator));
        int simplifiedNumerator = numerator / gcd;
        int simplifiedDenominator = denominator / gcd;

        if (simplifiedDenominator == 1) {
            return Integer.toString(simplifiedNumerator);
        }
        return simplifiedNumerator + "/" + simplifiedDenominator;
    }

    private int calculateGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return calculateGCD(b, a % b);
    }

    public String equation() {
        String slopeStr = slope();
        double yInt = yIntercept();

        if (slopeStr.equals("∞")) {
            return "Vertical line";
        }

        if (slopeStr.equals("0")) {
            return "Horizontal line";
        }

        if (yInt == 0) {
            return "y = " + slopeStr + "x";
        }

        if (yInt < 0) {
            return "y = " + slopeStr + "x - " + Math.abs(yInt);
        }

        return "y = " + slopeStr + "x + " + yInt;
    }

    public String coordinateForX(double x) {
        double y = yIntercept() + parseSlope(slope()) * x;
        return "(" + x + ", " + Math.round(y * 100.0) / 100.0 + ")";
    }

    private double parseSlope(String slopeStr) {
        if (slopeStr.equals("∞")) {
            throw new ArithmeticException("Cannot calculate coordinates for vertical line");
        }

        int numerator = 0;
        int denominator = 1;

        if (slopeStr.contains("/")) {
            String[] parts = slopeStr.split("/");
            numerator = Integer.parseInt(parts[0]);
            denominator = Integer.parseInt(parts[1]);
        } else {
            numerator = Integer.parseInt(slopeStr);
        }

        return (double) numerator / denominator;
    }

    public String lineInfo() {
        return "The two points are: (" + x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ")\n" +
                "The equation of the line between these points is: " + equation() + "\n" +
                "The y-intercept of this line is: " + yIntercept() + "\n" +
                "The slope of this line is: " + slope() + "\n" +
                "The distance between these points is: " + distance();
    }
}


