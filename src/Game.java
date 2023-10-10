import java.util.Scanner;
public class Game {
    private Scanner input = new Scanner(System.in);

    public void start(){
        System.out.println("Macera oyununa hoş geldiniz.");
        Player player = new Player();
        System.out.println("Lütfen oyuna başlamak için bir karakter seçiniz.");
        System.out.println("___________________________________________________________");
        System.out.println("Karakterler");
        player.selectChar();
        Location location = null;
        while (true) {
            player.printPlayerInfo();
            System.out.println();
            System.out.println("Bölgeler");
            System.out.println("1- Güvenli Ev");
            System.out.println("2- Eşya Dükkanı ");
            System.out.println("3- Mağara, Ödül: Yemek");
            System.out.println("4- Orman, Ödül: Odun");
            System.out.println("5- Nehir, Ödül: Su ");
            System.out.println("6- Maden, Rastgele bir eşya veya para kazanabilirsiniz.");
            System.out.println("0- Çıkış Yap: Oyunu sonlandır.");
            System.out.println("Lütfen gitmek istediğiniz bölgeyi seçiniz.");
            int selectLoc = input.nextInt();
            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if(player.getInventory().getPrizeCave().equals("Yemek")){
                        System.out.println("Daha önce Mağara bölgesindeki ödülü kazanmıştınız.");
                        System.out.println("Lütfen farklı bir bölge seçiniz.");
                        location = new RegionMenu(player);
                        break;
                    }
                    else {
                        location = new Cave(player);
                    }
                    break;
                case 4:
                    if(player.getInventory().getPrizeForest().equals("Odun")){
                        System.out.println("Daha önce Orman bölgesindeki ödülü kazanmıştınız.");
                        System.out.println("Lütfen farklı bir bölge seçiniz.");
                        location = new RegionMenu(player);
                        break;
                    }
                    else {
                        location = new Forest(player);
                    }
                    break;
                case 5:
                    if(player.getInventory().getPrizeRiver().equals("Su")){
                        System.out.println("Daha önce Nehir bölgesindeki ödülü kazanmıştınız.");
                        System.out.println("Lütfen farklı bir bölge seçiniz.");
                        location = new RegionMenu(player);
                        break;
                    }
                    else {
                        location = new River(player);
                    }
                    break;

                case 6:
                    location = new Mine(player);
                    break;

                default:
                    System.out.println("Lütfen geçerli bir bölge seçiniz.");
                    location = new RegionMenu(player);
                    break;
            }
            if (location == null) {
                System.out.println("Oyun bitti.");
                break;
            }
            if (!location.onLocation()){
                System.out.println("Game Over!");
                break;
            }

        }

    }
}
