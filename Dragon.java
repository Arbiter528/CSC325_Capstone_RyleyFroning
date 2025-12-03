


public class Dragon extends Thread implements GameCharacter {
    private int health = 30;
    // Reference to an active dragon that other classes can affect (optional global game target)
    public static Dragon activeDragon = null;
    private int firePower = 25;
    public Dragon() {
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void damageHealth(int damage) {
        health = health - damage;
    }
    public int getFirePower() {
        return firePower;
    }
    public void setFirePower(int firePower) {
        this.firePower = firePower;
    }
    @Override
    public void run() {
        System.out.println("The mighty Dragon soars into the skies, searching for treasure and adventure!");
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Dragon: Flight " + i);
                Thread.sleep(800);
            }
        } catch (InterruptedException e) {
            System.err.println("Dragon's flight was interrupted!");
        }
        if (health <= 0) {
            System.out.println("The Dragon has been defeated in battle!");
            System.out.println("The Dragon's hoard is left for the victorious heroes!");
        } else {
            System.out.println("The Dragon remains undefeated with " + health + " health left!");
            System.out.println("The Dragon returns to its lair, laden with riches and stories of valor!");
        }
        //System.out.println("The Dragon returns to its lair, laden with riches and stories of valor!");
    }
}