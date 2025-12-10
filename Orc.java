


import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Orc extends Thread implements Rng, GameCharacter {
    private CyclicBarrier barrier;
    private int health = 12;
    private int strength = 18;
    private int orcWealth = 0;
    private int dmg = strength - rng + 1;
    private Random rand = new Random();
    
    // Lambda for checking if orc has enough strength
    private Predicate<Integer> hasStrength = strengthRequired -> strengthRequired <= strength;
    
    // Lambda for outputting damage to dragon
    private Consumer<Integer> damageDragon = damage -> {
        if (Dragon.activeDragon != null) {
            Dragon.activeDragon.damageHealth(damage);
            System.out.println("The Orc hits the Dragon for " + damage + " damage! Dragon HP is now " + Dragon.activeDragon.getHealth());
        } else {
            System.out.println("But there is no dragon to hit.");
        }
    };
    
    public Orc(CyclicBarrier barrier) {
        this.barrier = barrier;
    }
    
    @Override
    public void run() {
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.err.println("Orc failed to synchronize!");
            return;
        }
        System.out.println("The brave Knight sets out on a quest to slay the dragon!");
        
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Knight: Step " + i);
                Thread.sleep(rand.nextInt(1000) + 500);
                if (i == 1) {
                    System.out.println("Orc draws his axe, ready for battle!");
                        System.out.println("A Human appears!");
                        if (rng <= strength) {
                            System.out.println("Orc bravely defeats the Human!");
                            System.err.println("The Human drops some gold!");
                            orcWealth += rng * 5;
                            System.err.println("Orc's wealth increases to " + orcWealth + " gold coins!");
                        } else {
                            System.out.println("Orc is wounded by the Human but presses on!");
                            health -= 2;
                            if (health <= 0) {
                                System.out.println("The Orc has fallen in battle!");
                                return;
                            }
                        }
                }
                if (i == 2) {
                    System.out.println("The orc sees a village to plunder");
                        System.out.println("He attempts to raid the village!");
                        if (rng + 2 <= strength) {
                            System.out.println("Orc successfully raids the village!");
                            orcWealth += rng * 10;
                            System.err.println("Orc's wealth increases to " + orcWealth + " gold coins!");
                        } else {
                            System.out.println("Orc is wounded by the villagers and fails to raid them!");
                            health -= 3;
                            if (health <= 0) {
                                System.out.println("The Orc has fallen in battle!");
                                return;
                            }
                        }
                }
                if (i == 3) {
                    System.out.println("Orc sees a rival clan!");
                        System.out.println("He attempts to challenge them!");
                        if (rng <= strength) {
                            System.out.println("The Orc beats the rival clan to a pulp!");
                        } else {
                            System.out.println("Orc is wounded by the rival clan but presses on!");
                            health -= 2;
                            if (health <= 0) {
                                System.out.println("The Orc has fallen in battle!");
                                return;
                            }
                        }
                }
                if (i == 4) {
                    System.out.println("The Orc encounters a Dragon!");
                        System.out.println("Orc gets the help of a wizard and a knight to defeat the dragon!");
                        if (hasStrength.test(rng)) {
                            System.out.println("the Orc bravely fights the dragon!");
                            damageDragon.accept(dmg);
                        } else {
                            System.out.println("Orc is wounded by the dragon but presses on!");
                            health -= 3;
                            if (health <= 0) {
                                System.out.println("The Orc has fallen in battle!");
                                return;
                            }
                        }
                }
                if (i == 5) {
                    System.out.println("The Orc continues to fight the Dragon!");
                        System.out.println("Orc gets the help of a wizard and a knight to defeat the dragon!");
                        if (hasStrength.test(rng)) {
                            System.out.println("the Orc bravely fights the dragon!");
                            damageDragon.accept(dmg);
                        } else {
                            System.out.println("Orc is wounded by the dragon but presses on!");
                            health -= 5;
                            if (health <= 0) {
                                System.out.println("The Orc has fallen in battle!");
                                return;
                            }
                        }
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Knight's adventure was interrupted!");
        }
        if (health > 0) {
            System.out.println("The Knight returns victorious from his quest!");
            System.out.println("The Knight emerges victorious with " + health + " health left!");

            System.out.println("The Knight returns home with " + orcWealth + " gold coins!");
            } else {
                System.out.println("The Knight has fallen in battle.");
            }
        }
    }
