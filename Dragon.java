

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.HashMap;
import java.util.Map;

public class Dragon extends Thread implements Rng, GameCharacter {
    private CyclicBarrier barrier;
    private int health = 30;
    // Reference to an active dragon that other classes can affect (optional global game target)
    public static Dragon activeDragon = null;
    private int firePower = 25;
    private int dragondmg = firePower - rng + 1;
    private int dragonwealth = 100;
    
    public Dragon() {
    }
    
    public Dragon(CyclicBarrier barrier) {
        this.barrier = barrier;
    }
    
    /**
     * Lambda expressions defining actions for each flight step
     */
    private Map<Integer, CharacterAction> flightActions = new HashMap<Integer, CharacterAction>() {{
        put(1, () -> {});
        
        put(2, () -> {
            System.out.println("The Dragon unleashes a fiery breath!");
            System.out.println("The surrounding is left as a smoldering ruin leaving nothing but ashes.");
        });
        
        put(3, () -> {
            System.out.println("The Dragon discovers a hidden cave filled with glittering treasure!");
            System.out.println("The Dragon gathers gold and jewels, adding to its hoard.");
            dragonwealth += rng * 10;
            System.out.println("The Dragon's wealth increases to " + dragonwealth + " gold coins!");
        });
        
        put(4, () -> {
            System.out.println("The Dragon spots a group of adventurers below!");
            System.out.println("The Dragon prepares to engage them in battle!");
        });
        
        put(5, () -> {
            System.out.println("The Dragon Fights valiantly against the adventurers!");
        });
    }};

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
    public int getDragondmg() {
        return dragondmg;
    }
    public void setDragondmg(int dragondmg) {
        this.dragondmg = dragondmg;
    }
    public int getDragonwealth() {
        return dragonwealth;
    }
    public void setDragonwealth(int dragonwealth) {
        this.dragonwealth = dragonwealth;
    }
    @Override
    public void run() {
        System.out.println("The mighty Dragon soars into the skies, searching for treasure and adventure!");
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.err.println("Dragon failed to synchronize!");
            return;
        }
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Dragon: Flight " + i);
                
                // Execute lambda expression for this flight step
                flightActions.getOrDefault(i, () -> 
                    System.out.println("The Dragon circles majestically in the sky.")
                ).execute();

                Thread.sleep(800);
            }
        } catch (InterruptedException e) {
            System.err.println("Dragon's flight was interrupted!");
        }
        if (health <= 0) {
            System.out.println("The Dragon has been defeated in battle!");
            System.out.println("The Dragon's hoard is left for the victorious heroes!");
            dragonwealth = dragonwealth / 3;

        } else {
            System.out.println("The Dragon remains undefeated with " + health + " health left!");
            System.out.println("The Dragon returns to its lair, laden with riches and stories of valor!");
        }
        //System.out.println("The Dragon returns to its lair, laden with riches and stories of valor!");
    }
}