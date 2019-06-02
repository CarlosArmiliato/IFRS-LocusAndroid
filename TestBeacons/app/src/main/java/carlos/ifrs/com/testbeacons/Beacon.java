package carlos.ifrs.com.testbeacons;

/**
 * Created by carlos on 08/01/18.
 */

public class Beacon {
    private int Id;
    private String UUID;
    private int Major;
    private int Minor;
    private double Proximity;
    private double Distance;
    private double RSSI;
    private double x;
    private double y;
    private double z;
    private boolean Active;

    public Beacon(int paId, String paUUID, int paMajor, int paMinor, double paX, double paY, double paZ, boolean paActive) {
        this.Id = paId;
        this.UUID = paUUID;
        this.Major = paMajor;
        this.Minor = paMinor;
        this.x = paX;
        this.y = paY;
        this.z = paZ;
        this.Active = paActive;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public int getMajor() {
        return Major;
    }

    public void setMajor(int major) {
        Major = major;
    }

    public int getMinor() {
        return Minor;
    }

    public void setMinor(int minor) {
        Minor = minor;
    }

    public double getProximity() {
        return Proximity;
    }

    public void setProximity(double proximity) {
        Proximity = proximity;
    }

    public double getDistance() {
        return Distance;
    }

    public void setDistance(double distance) {
        Distance = distance;
    }

    public double getRSSI() {
        return RSSI;
    }

    public void setRSSI(double RSSI) {
        this.RSSI = RSSI;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

}
