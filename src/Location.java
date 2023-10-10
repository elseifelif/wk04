import java.util.Scanner;

public abstract class Location {
    private Player player;
    private String locationName;
    private String prize;
    public static Scanner input = new Scanner(System.in);

    public Location(Player player, String locationName, String prize) {
        this.player = player;
        this.locationName = locationName;
        this.prize = prize;
    }

    abstract boolean onLocation();

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
}
