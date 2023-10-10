public class ToolStore extends NormalLoc{

    ToolStore (Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        boolean displayMenu = true;
        while (displayMenu) {

            System.out.println("------ Mağazaya Hoşgeldiniz. ------");
            System.out.println("1- Silahlar");
            System.out.println("2- Zırhlar");
            System.out.println("3- Mağazadan Çıkış Yap");
            int selectCase = Location.input.nextInt();
            while (selectCase <1 || selectCase>3) {
                System.out.println("Lütfen yeni bir seçim yapınız.");
                selectCase = input.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Mağazadan çıkış yapılıyor.");
                    displayMenu=false;
                    break;

            }
        }

        return true;
    }

    public void printWeapon () {
        System.out.println("------ Silahlar ------");
        for (Weapon i: Weapon.weapons()) {
            System.out.println(i.getWeaponName()+", ID: "+i.getId()+" Para: "+i.getPrice()+" Hasar: "+i.getDamage());

        }
        System.out.println("0 - Çıkış Yap");


    }

    public void buyWeapon () {
        System.out.println("Bir silah seçiniz.");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID<0 ||selectWeaponID>Weapon.weapons().length) {
            System.out.println("Geçersiz değer yazıldı. Lütfen tekrar seçim yapınız.");
            selectWeaponID = input.nextInt();
        }
        if (selectWeaponID!=0){
            Weapon selectedWeapon = Weapon.getWeaponObjectByID(selectWeaponID);
            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice()> this.getPlayer().getMoney()) {
                    System.out.println("Bakiyeniz yetersiz.");
                } else {
                    System.out.println(selectedWeapon.getWeaponName()+" silahını satın aldınız.");
                    int balance = this.getPlayer().getMoney()-selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız:"+this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Yeni silahınız: "+this.getPlayer().getInventory().getWeapon().getWeaponName());
                }

            }

        }


    }

    public void printArmor () {
        System.out.println("------ Zırhlar ------");
        for (Armor i: Armor.armorTypes()) {
            System.out.println(i.getArmorName()+", ID: "+i.getArmorID()+" Para: "+
                    i.getPrice()+" Koruma: "+i.getProtection());

        }
        System.out.println("0 - Çıkış Yap");

    }

    public void buyArmor () {
        System.out.println("Bir zırh seçiniz.");
        int selectArmorID = input.nextInt();
        while (selectArmorID<0 ||selectArmorID>Armor.armorTypes().length) {
            System.out.println("Geçersiz değer yazıldı. Lütfen tekrar seçim yapınız.");
            selectArmorID = input.nextInt();
        }
        if (selectArmorID !=0) {
            Armor selectedArmor = Armor.getArmorObjectByID(selectArmorID);
            if (selectedArmor != null) {
                if (selectedArmor.getPrice()> this.getPlayer().getMoney()) {
                    System.out.println("Bakiyeniz yetersiz.");
                } else {
                    System.out.println(selectedArmor.getArmorName()+" zırhı satın aldınız.");
                    int balance = this.getPlayer().getMoney()-selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız:"+this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Yeni zırhınız: "+this.getPlayer().getInventory().getArmor().getArmorName());
                }

            }

        }

    }


}
