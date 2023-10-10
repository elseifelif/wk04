import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int originalHealth;
    private int money;
    private String name;
    private String charName;
    private Inventory inventory;
    private Scanner input = new Scanner(System.in);

    public Player () {
        this.inventory = new Inventory();
    }


    public void selectChar () {
        GameChar [] charList = {new Samurai(), new Archor(), new Knight()};
        System.out.println("___________________________________________________________");
        for (GameChar gameChar : charList) {
            System.out.println("Karakter "+gameChar.getId()+": " +gameChar.getName()+
                    "\t Hasar: "+gameChar.getDamage()+
                    "\t Sağlık: "+ gameChar.getHealth()+
                    "\t Para: "+gameChar.getMoney());
        }
        System.out.println("___________________________________________________________");
        System.out.println("Lütfen bir karakter seçiniz.");
        int selectChar = input.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(charList[1]);
                break;
            case 3:
                initPlayer(charList[2]);
                break;
            default:
                initPlayer(charList[0]);
                break;
        }
        System.out.println(this.charName+" karakterini seçtiniz.");
        System.out.println("___________________________________________________________");
        System.out.println("Karakterin özellikleri:");
        System.out.println("Hasar: "+this.damage+", Sağlık: "+this.health+", Para: "+this.money);
        System.out.println("___________________________________________________________");
    }

    public void printPlayerInfo(){
        System.out.println("Güncel Durum:");
        System.out.println("Silah: "+this.getInventory().getWeapon().getWeaponName()+
                ", Zırh: " +this.getInventory().getArmor().getArmorName()+
                ", Zırh Koruması: "+this.getInventory().getArmor().getProtection()+
                ", Toplam Hasar: "+this.getTotalDamage()+
                ", Sağlık: "+ this.getHealth()+
                ", Para: "+this.getMoney()+
                ", Ödüller: "+this.getInventory().getPrizeCave()+" "
                + this.getInventory().getPrizeForest()
                + " "+this.getInventory().getPrizeRiver());
        System.out.println("___________________________________________________________");
    }

    public void initPlayer (GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOriginalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());

    }

    int selectChar;

    public int getTotalDamage () {
        return damage + this.getInventory().getWeapon().getDamage();
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }
}
