//I worked on the homework assignment alone, using only course materials.
public class Location {
    private final String NAME; //name of the location
    private final double LATITUDE; //latitude (X) of the location
    private final double LONGITUDE; //longitude (y) of the location

    public Location(String name, double latitude, double longitude) {
        NAME = name;
        LATITUDE = latitude;
        LONGITUDE = longitude;
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Location)) {
            return false;
        }
        Location o = (Location) other;
        if (getLatitude() == o.getLatitude() && getLongitude() == o.getLongitude()) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return getNAME() + " @ " + "(" + getLatitude() + ", " + getLongitude() + ")";
    }

    public String getNAME() {
        return NAME;
    }

    public double getLatitude() {
        return LATITUDE;
    }

    public double getLongitude() {
        return LONGITUDE;
    }
}
