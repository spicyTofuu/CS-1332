
public abstract class Transportation {
    private String name;
    private double averageSpeed;
    private static int totalTransportationCreated;

    public String getName() {
        return name;
    }

    public void setName(String namee) {
        name = namee;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public int getTotalTransportationCreated() {
        return totalTransportationCreated;
    }

    public Transportation(String name, double averageSpeed) {
        this.name = name;
        this.averageSpeed = averageSpeed;
        totalTransportationCreated++;
    }

    public double calculateTravelTime(Location oldLocation, Location newLocation) {
        double diffx = newLocation.getLatitude() - oldLocation.getLatitude();
        double diffy = newLocation.getLongitude() - oldLocation.getLongitude();
        double dis = Math.sqrt(diffx * diffx + diffy * diffy);
        return dis / averageSpeed;
    }

    public abstract String makeNoise();

}
