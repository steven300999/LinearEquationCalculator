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

    public double distance() {
        return Math.round(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) * 100.0) / 100.0;
    }

    public double yIntercept() {
        return Math.round((y2 - y1) * (x1 - x2) * 100.0) / 100.0;
    }

    public double slope() {
        if (x2 == x1) {
            return 0;
        }
        return Math.round((y2 - y1) * 100.0) / ((x2 - x1) * 100.0);
    }

    public String equation() {
        double slope = slope();
        double yInt = yIntercept();

        if (Double.isNaN(slope)) {
            return "Vertical line";
        }

        if (slope == 0) {
            return "Horizontal line";
        }

        if (slope == 1) {
            return "y = x + " + yInt;
        }

        if (slope == -1) {
            return "-y = x + " + yInt;
        }

        if (yInt == 0) {
            return "y = " + slope + "x";
        }

        if (yInt < 0) {
            return "y = " + slope + "x - " + Math.abs(yInt);
        }

        return "y = " + slope + "x + " + yInt;
    }

    public String coordinateForX(double x) {
        double y = yIntercept() + slope() * x;
        return "(" + x + ", " + Math.round(y * 100.0) / 100.0 + ")";
    }

    public String lineInfo() {
        return "The two points are: (" + x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ")\n" +
                "The equation of the line between these points is: " + equation() + "\n" +
                "The y-intercept of this line is: " + yIntercept() + "\n" +
                "The slope of this line is: " + slope() + "\n" +
                "The distance between these points is: " + distance();
    }
}

