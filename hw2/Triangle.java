//I worked on the homework assignment alone, using only course materials.
public class Triangle {
    private double side1;
    private double side2;
    private double side3;
    private boolean isEquilateral;
    public Triangle(double sid1, double sid2, double sid3) {
        side1 = sid1;
        side2 = sid2;
        side3 = sid3;
        isEquilateral = isEquilateral();
    }

    public Triangle(double sid1, double sid2) {
        this(sid1, sid2, Math.sqrt(sid1 * sid1 + sid2 * sid2));
    }

    public Triangle() {
        this(1, 1, 1);
    }

    public double getPerimeter() {
        double p = side1 + side2 + side3;
        return p;
    }

    public double getArea() {
        double per = 0.5 * getPerimeter();
        return Math.sqrt(per * (per - side1) * (per - side2) * (per - side3));
    }

    private boolean isEquilateral() {
        if (side1 == side2) {
            if (side1 == side3) {
                isEquilateral = true;
            } else {
                isEquilateral = false;
            }
        }
        return isEquilateral;
    }

    private void scale(double scal) {
        side1 = side1 * scal;
        System.out.print("\n" + side1 + ",");
        side2 = side2 * scal;
        System.out.print(side2 + ",");
        side3 = side3 * scal;
        System.out.print(side3 + ",");
    }

}
