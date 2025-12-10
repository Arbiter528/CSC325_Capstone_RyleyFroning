
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.Consumer;
import java.util.function.Predicate;


class Knight extends Thread implements Rng, GameCharacter {
    Random rand = new Random();
    private CyclicBarrier barrier;
    private int health = 10;
    private int swordPower = 20;
    private int knightdmg = swordPower - rng + 1;
    private int knightwealth = 50;
    //int gamerng = rand.nextInt(4) + 1;
    private int stamina = 10;
    
    // Lambda for checking if knight has enough stamina
    private Predicate<Integer> hasStamina = staminaRequired -> staminaRequired <= stamina;
    
    // Lambda for outputting damage to dragon
    private Consumer<Integer> damageDragon = damage -> {
        if (Dragon.activeDragon != null) {
            Dragon.activeDragon.damageHealth(damage);
            System.out.println("The Knight hits the Dragon for " + damage + " damage! Dragon HP is now " + Dragon.activeDragon.getHealth());
        } else {
            System.out.println("But there is no dragon to hit.");
        }
    };
    
    public Knight(CyclicBarrier barrier) {
        this.barrier = barrier;
    }
    
    @Override
    public void run() {
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.err.println("Knight failed to synchronize!");
            return;
        }
        System.out.println("The brave Knight sets out on a quest to slay the dragon!");
        
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Knight: Step " + i);
                if (i == 1) {
                    System.out.println("Knight draws his sword, ready for battle!");
                        System.out.println("A goblin appears!");
                        if (rng <= stamina) {
                            System.out.println("Knight bravely defeats the goblin!");
                        } else {
                            System.out.println("Knight is wounded by the goblin but presses on!");
                            health -= 2;
                            if (health <= 0) {
                                System.out.println("The Knight has fallen in battle!");
                                return;
                            }
                        }
                }
                if (i == 2) {
                    System.out.println("Knight see a person in need of help!");
                        System.out.println("He attempts to free them from a trap!");
                        if (rng + 2 <= stamina) {
                            System.out.println("Knight successfully frees the person!");
                        } else {
                            System.out.println("Knight is wounded by the trap and fails to save them!");
                            health -= 3;
                            if (health <= 0) {
                                System.out.println("The Knight has fallen in battle!");
                                return;
                            }
                        }
                }
                if (i == 3) {
                    System.out.println("Knight draws his sword, ready for battle!");
                        System.out.println("A Lizardman appears!");
                        if (rng <= stamina) {
                            System.out.println("Knight bravely defeats the Lizardman!");
                        } else {
                            System.out.println("Knight is wounded by the goblin but presses on!");
                            health -= 3;
                            if (health <= 0) {
                                System.out.println("The Knight has fallen in battle!");
                                return;
                            }
                        }
                }
                if (i == 4) {
                    System.out.println("The knight encounters a Dragon!");
                        System.out.println("The Knight gets the help of a wizard and a orc to defeat the dragon!");
                        if (hasStamina.test(rng)) {
                            System.out.println("the Knight bravely fights the dragon!");
                            damageDragon.accept(knightdmg);
                        } else {
                            System.out.println("Kight is wounded by the dragon but presses on!");
                            health -= 2;
                            if (health <= 0) {
                                System.out.println("The Knight has fallen in battle!");
                                return;
                            }
                        }
                        
                }
                if (i == 5) {
                    System.out.println("The knight continues to fight the Dragon!");
                    if (hasStamina.test(rng)) {
                            
                        System.out.println("the Knight bravely fights the dragon!");
                            damageDragon.accept(knightdmg);
                        } else {
                            System.out.println("Kight is wounded by the dragon but presses on!");
                            health -= 5;
                            if (health <= 0) {
                                System.out.println("The Knight has fallen in battle!");
                                return;
                            }
                        }
                }
                Thread.sleep(rand.nextInt(1000) + 500);
            }
        } catch (InterruptedException e) {
            System.err.println("Knight's adventure was interrupted!");
        }
        if (health > 0) {
            System.out.println("The Knight returns victorious from his quest!");
            System.out.println("The Knight emerges victorious with " + health + " health left!");

            System.out.println("The Knight returns home with " + knightwealth + " gold coins!");
            } else {
                System.out.println("The Knight has fallen in battle.");
            }
        }
    }
