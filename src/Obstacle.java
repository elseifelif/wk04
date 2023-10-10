public class Obstacle {
    private int obstacleID;
    private String obstacleName;
    private int obstacleDamage;
    private int obstacleHealth;
    private int obstacleOriginalHealth;
    private int moneyReward;

    public Obstacle (int obstacleID, String obstacleName, int obstacleDamage, int obstacleHealth, int moneyReward) {
        this.obstacleID = obstacleID;
        this.obstacleName = obstacleName;
        this.obstacleDamage = obstacleDamage;
        this.obstacleHealth = obstacleHealth;
        this.obstacleOriginalHealth = obstacleHealth;
        this.moneyReward = moneyReward;

    }

    public int getObstacleOriginalHealth() {
        return obstacleOriginalHealth;
    }

    public void setObstacleOriginalHealth(int obstacleOriginalHealth) {
        this.obstacleOriginalHealth = obstacleOriginalHealth;
    }

    public int getObstacleID() {
        return obstacleID;
    }

    public void setObstacleID(int obstacleID) {
        this.obstacleID = obstacleID;
    }

    public String getObstacleName() {
        return obstacleName;
    }

    public void setObstacleName(String obstacleName) {
        this.obstacleName = obstacleName;
    }

    public int getObstacleDamage() {
        return obstacleDamage;
    }

    public void setObstacleDamage(int obstacleDamage) {
        this.obstacleDamage = obstacleDamage;
    }

    public int getObstacleHealth() {
        return obstacleHealth;
    }

    public void setObstacleHealth(int obstacleHealth) {
        if (obstacleHealth < 0) {
            obstacleHealth = 0;
        }
        this.obstacleHealth = obstacleHealth;
    }

    public int getMoneyReward() {
        return moneyReward;
    }

    public void setMoneyReward(int moneyReward) {
        this.moneyReward = moneyReward;
    }
}
