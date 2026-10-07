package no.sofiya.aurora;

// minKp = roughly the Kp needed to see the aurora from the city
public enum City {
    TROMSO("Tromsø", 69.65, 18.96, 2),
    BODO("Bodø", 67.28, 14.40, 3),
    TRONDHEIM("Trondheim", 63.43, 10.39, 4),
    STJORDAL("Stjørdal", 63.47, 10.92, 4),
    BERGEN("Bergen", 60.39, 5.32, 5),
    GJOVIK("Gjøvik", 60.80, 10.69, 5),
    OSLO("Oslo", 59.91, 10.75, 5);

    private final String displayName;
    private final double latitude;
    private final double longitude;
    private final int minKp;

    City(String displayName, double latitude, double longitude, int minKp) {
        this.displayName = displayName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.minKp = minKp;
    }

    public String getDisplayName() { return displayName; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public int getMinKp() { return minKp; }

    public static City fromId(String id) {
        return City.valueOf(id.toUpperCase());
    }
}
