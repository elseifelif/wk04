public class Armor {
    private int armorID;
    private String armorName;
    private int protection;
    private int price;

    public Armor(int armorID, String armorName, int protection, int price) {
        this.armorID = armorID;
        this.armorName = armorName;
        this.protection = protection;
        this.price = price;
    }

    public static Armor[] armorTypes() {
        Armor [] armorList = new Armor[3];
        armorList [0] = new Armor(1,"Hafif",1,15);
        armorList [1] = new Armor(2,"Orta",3,25);
        armorList [2] = new Armor(3,"Ağır",5,40);
        return armorList;
    }

    public static Armor getArmorObjectByID (int armorID) {
        Armor armor = null;
        for (Armor n: armorTypes()) {
            if(n.getArmorID()==armorID) {
                armor = n;
            }
        }
        return armor;
    }

    public int getArmorID() {
        return armorID;
    }

    public void setArmorID(int armorID) {
        this.armorID = armorID;
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getArmorName() {
        return armorName;
    }

    public void setArmorName(String armorName) {
        this.armorName = armorName;
    }
}
