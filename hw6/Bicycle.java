
public class Bicycle extends Transportation {
    private String color;

    public Bicycle(String name, String color) {
        super(name, 20);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String makeNoise() {
        return "ding-a-ding";
    }
}
