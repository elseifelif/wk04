import java.util.Random;

public abstract class BattleLocation extends Location {
    private Obstacle obstacle;
    private int maxObstacle;

    public BattleLocation(Player player, String locationName, Obstacle obstacle, String prize, int maxObstacle) {
        super(player, locationName, prize);
        this.obstacle = obstacle;
        this.maxObstacle = maxObstacle;
    }

    @Override
    boolean onLocation() {
        int obstacleNumber = this.randomObstacleNumber();
        System.out.println("Şu anda buradasınız: " + this.getLocationName());
        System.out.println("Dikkatli ol, burada " + obstacleNumber + " tane "
                + this.getObstacle().getObstacleName() + " yaşıyor.");
        System.out.println("<S>avaş veya <K>aç");
        String selectCase = input.next();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("S") && combat(obstacleNumber)) {
            System.out.println(this.getLocationName() + " bölgesindeki tüm canavarları yendiniz.");
            if (!this.getPrize().equals("")) {
                System.out.println(this.getPrize()+" kazandınız.");
            }

            switch (getPrize()) {
                case ("Yemek"):
                    this.getPlayer().getInventory().setPrizeCave(getPrize());
                    break;
                case ("Odun"):
                    this.getPlayer().getInventory().setPrizeForest(getPrize());
                    break;
                case ("Su"):
                    this.getPlayer().getInventory().setPrizeRiver(getPrize());
                    break;
                default:
                    break;
            }
            if (controlWinning()) {
                Location location = null;
                System.out.println();
                System.out.println("Tebrikler Oyunu Kazandınız.");
                location=new SafeHouse(getPlayer());
                System.out.println(location.getLocationName() +" bölgesine geri döndünüz.");
                return false;
            }

            return true;

        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Canınız kalmadı, oyunu kaybettiniz.");
            return false;
        }
        return true;
    }

    public boolean controlWinning () {
        boolean control =false;
        if(this.getPlayer().getInventory().getPrizeCave().equals("Yemek") &&
                this.getPlayer().getInventory().getPrizeForest().equals("Odun") &&
                this.getPlayer().getInventory().getPrizeRiver().equals("Su")) {
            control = true;

        }
        return control;
    }

    public boolean combat(int obstacleNumber) {
        for (int i = 1; i <= obstacleNumber; i++) {
            this.getObstacle().setObstacleHealth(this.getObstacle().getObstacleOriginalHealth());
            playerStatus();
            System.out.println(obstacle.getObstacleName() + " #" + (i));
            obstacleStatus();
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getObstacleHealth() > 0) {
                System.out.println("<S>avaş veya <K>aç :");
                String selectCombat = input.next();
                if (selectCombat.equalsIgnoreCase("S")) {
                    Random r = new Random();
                    int number = r.nextInt(0,2);
                    if (number==0) {
                        System.out.println("Siz hamle yaptınız.");
                        this.getObstacle().setObstacleHealth(getObstacle().getObstacleHealth() -
                                this.getPlayer().getTotalDamage());
                        afterDamage();
                        if (this.getObstacle().getObstacleHealth() > 0) {
                            System.out.println();
                            System.out.println(getObstacle().getObstacleName()+" hamle yaptı.");
                            int calculatedObstacleDamage = this.getObstacle().getObstacleDamage() -
                                    this.getPlayer().getInventory().getArmor().getProtection();
                            if (calculatedObstacleDamage < 0) {
                                calculatedObstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - calculatedObstacleDamage);
                            afterDamage();
                        }

                    } else {

                        System.out.println(getObstacle().getObstacleName()+" hamle yaptı.");
                        int calculatedObstacleDamage = this.getObstacle().getObstacleDamage() -
                                this.getPlayer().getInventory().getArmor().getProtection();
                        if (calculatedObstacleDamage < 0) {
                            calculatedObstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - calculatedObstacleDamage);
                        afterDamage();

                        if (this.getPlayer().getHealth()>0) {
                            System.out.println("Siz hamle yaptınız.");
                            this.getObstacle().setObstacleHealth(getObstacle().getObstacleHealth() -
                                    this.getPlayer().getTotalDamage());
                            afterDamage();
                        }

                    }

                } else {
                    return false;
                }
            }

            if (this.getObstacle().getObstacleHealth() < this.getPlayer().getHealth()) {
                System.out.println("Canavarı yendiniz.");
                randomRewardMethod();
                if(!obstacle.getObstacleName().equals("Hayalet")) {
                    System.out.println(this.getObstacle().getMoneyReward() + " birim para kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getMoneyReward());
                    System.out.println("Güncel paranız: " + getPlayer().getMoney());
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public void afterDamage() {
        System.out.println("Sağlık durumunuz: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getObstacleName() + " sağlık durumu: " +
                this.getObstacle().getObstacleHealth());
        System.out.println("-------------------------------");
    }

    public void playerStatus() {
        System.out.println("Oyuncu Değerleri");
        System.out.println("-------------------------------------------");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.println("Silah: " + this.getPlayer().getInventory().getWeapon().getWeaponName());
        System.out.println("Hasar: " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh: " + this.getPlayer().getInventory().getArmor().getArmorName());
        System.out.println("Bloklama: " + this.getPlayer().getInventory().getArmor().getProtection());
        System.out.println("Para: " + this.getPlayer().getMoney());
        System.out.println();
    }

    public void obstacleStatus() {
        System.out.println(this.getObstacle().getObstacleName() + " Değerleri");
        System.out.println("-------------------------------------------");
        System.out.println("Sağlık: " + this.getObstacle().getObstacleHealth());
        System.out.println("Hasar: " + this.getObstacle().getObstacleDamage());
        System.out.println("Ödül: " + this.getObstacle().getMoneyReward());
        System.out.println();

    }

    public int randomObstacleNumber() {
        Random randomNumber = new Random();
        return randomNumber.nextInt(this.getMaxObstacle()) + 1;
    }

    public void randomRewardMethod () {
        if ( obstacle.getObstacleName().equals("Hayalet")) {
            Random random = new Random();
            int randomNumber1 = random.nextInt(0, 99);
            int randomNumber2;
            if (randomNumber1 < 15) {
                randomNumber2 = random.nextInt(0, 99);
                if (randomNumber2 < 20) {
                    System.out.println("Tüfek kazandınız. Silahınız güncellendi.");
                    getPlayer().getInventory().setWeapon(Weapon.getWeaponObjectByID(3));
                } else if (randomNumber2 < 50 && randomNumber2 >= 20) {
                    System.out.println("Kılıç kazandınız. Silahınız güncellendi.");
                    getPlayer().getInventory().setWeapon(Weapon.getWeaponObjectByID(2));
                } else {
                    System.out.println("Tabanca kazandınız. Silahınız güncellendi.");
                    getPlayer().getInventory().setWeapon(Weapon.getWeaponObjectByID(1));
                }
            } else if (randomNumber1 < 30 && randomNumber1 >= 15) {
                randomNumber2 = random.nextInt(0, 99);
                if (randomNumber2 < 20) {
                    System.out.println("Ağır Zırh kazandınız. Zırhınız güncellendi.");
                    getPlayer().getInventory().setArmor(Armor.getArmorObjectByID(3));
                } else if (randomNumber2 < 50 && randomNumber2 >= 20) {
                    System.out.println("Orta Zırh kazandınız. Zırhınız güncellendi.");
                    getPlayer().getInventory().setArmor(Armor.getArmorObjectByID(2));
                } else {
                    System.out.println("Hafif Zırh kazandınız. Zırhınız güncellendi.");
                    getPlayer().getInventory().setArmor(Armor.getArmorObjectByID(1));
                }

            } else if (randomNumber1 < 55 && randomNumber1 >= 30) {
                randomNumber2 = random.nextInt(0, 99);
                System.out.println("Para kazandınız.");
                if (randomNumber2 < 20) {
                    System.out.println("10 Para kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney()+10);
                    System.out.println("Güncel paranız: " + getPlayer().getMoney());
                } else if (randomNumber2 < 50 && randomNumber2 >= 20) {
                    System.out.println("5 Para Kazandınız");
                    this.getPlayer().setMoney(this.getPlayer().getMoney()+5);
                    System.out.println("Güncel paranız: " + getPlayer().getMoney());
                } else {
                    System.out.println("1 Para kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney()+1);
                    System.out.println("Güncel paranız: " + getPlayer().getMoney());
                }
            }
            else {
                System.out.println("Rastgele eşya veya para kazanamadınız.");
            }

        }
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

}
