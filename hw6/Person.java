
public class Person {
    private String name;
    private Location currentLocation;
    private Transportation transportation;

    public Person(String name, Location currentLocation, Transportation transportation) {
        this.name = name;
        this.currentLocation = currentLocation;
        this.transportation = transportation;
    }

    public Person(String name, Location currentLocation) {
        this(name, currentLocation, new Feet("feet", "sneakers"));
//      this.name = name;
//      this.currentLocation = currentLocation;
//      transportation = new Feet("feet", "sneakers");
    }

    public Person(String name, Transportation transportation) {
        this(name, new Location("unknown", 0.0, 0.0), transportation);
//      this.name = name;
//      this.transportation = transportation;
//      currentLocation = new Location("unknown", 0.0, 0.0);
    }

    public Person(String name) {
        this(name, new Location("unknown", 0.0, 0), new Feet("feet", "sneakers"));
//      this.name = name;
//      currentLocation = new Location("unknown", 0.0, 0);
//      transportation = new Feet("feet", "sneakers");
    }

    public String getName() {
        return name;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }

    public String moveTo(Location location) {
        double time = transportation.calculateTravelTime(currentLocation, location);
        if (time < 2) {
            Location preLocation = currentLocation;
            currentLocation = location;
            return getName() + " has successfully moved from " + preLocation.getNAME() + " @ ("
                    + preLocation.getLatitude() + ", "
                    + preLocation.getLongitude() + ") to " + currentLocation.getNAME() + " @ ("
                    + currentLocation.getLongitude() + ", "
                    + currentLocation.getLongitude() + ").";
        } else {
            return location.getNAME() + " is too far away by "
                + transportation.getName() + " with travel time: " + time + " hours.";
        }
    }

    public String toString() {
        return getName() + " is currently at " + currentLocation.getNAME()
                + " @ ((" + currentLocation.getLatitude() + "),"
                + "(" + currentLocation.getLongitude() + ")). "
                + getName() + "'s transportation is " + transportation.getName() + ".";
    }

}
