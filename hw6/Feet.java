
public class Feet extends Transportation {
    private String shoeType;
    public Feet(String name, String shoeType) {
        super(name, 5);
        this.shoeType = shoeType;
    }

    public String getShoeType() {
        return shoeType;
    }

    public void setShoeType(String shoeType) {
        this.shoeType = shoeType;
    }

    public String makeNoise() {
        return "stamp-stomp";
    }

}
