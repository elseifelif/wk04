public class SafeHouse extends NormalLoc {
    public SafeHouse(Player player) {
        super(player, "Güvenli Ev");
    }

    Inventory inventory = new Inventory();

    @Override
    public boolean onLocation() {

        System.out.println("Güvenli Evdesiniz");
        this.getPlayer().setHealth(getPlayer().getOriginalHealth());
        System.out.println("Canınız yenilendi.");
        return true;

    }

}
