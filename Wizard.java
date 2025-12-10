
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.Consumer;
import java.util.function.Predicate;


@SuppressWarnings("InitializerMayBeStatic")
public class Wizard extends Thread implements Rng, GameCharacter {
    private CyclicBarrier barrier;
    int health = 8;
    int mana = 15;
    int dmg = rand.nextInt(9) + 2;
    
    // Lambda for checking if wizard has enough mana for spell
    private Predicate<Integer> hasMana = manaRequired -> manaRequired <= mana;
    
    // Lambda for outputting damage to dragon
    private Consumer<Integer> damageDragon = damage -> {
        if (Dragon.activeDragon != null) {
            Dragon.activeDragon.damageHealth(damage);
            System.out.println("The spell hits the Dragon for " + damage + " damage! Dragon HP is now " + Dragon.activeDragon.getHealth());
        } else {
            System.out.println("But there is no dragon to hit.");
        }
    };
    
    public Wizard(CyclicBarrier barrier) {
        this.barrier = barrier;
    }
    
    @Override
    public void run() {
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.err.println("Wizard failed to synchronize!");
            return;
        }
        System.out.println("The wise Wizard conjures a protective spell and sets off on an adventure!");
        Random rand = new Random();
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Wizard: Step " + i);
                Thread.sleep(rand.nextInt(1000) + 500);
                if (i == 1) {
                    System.out.println("The Wizard uses a spell to light the way through a dark forest!");
                        System.out.println("The spell is successful, and the Wizard proceeds safely.");
                }
                if (i == 2) {
                    System.out.println("The Wizard encounters a band of thieves!");
                        System.out.println("The Wizard attempts to outwit them with a clever spell!");
                        if (rng + 1 <= mana) {
                            System.out.println("The Wizard's spell confuses the thieves, allowing him to escape!");
                        } else {
                            System.out.println("The Wizard's spell fails, and he is robbed of some supplies!");
                            health -= 3;
                            if (health <= 0) {
                                System.out.println("The Wizard has fallen in battle!");
                                return;
                            }
                        }
                    }
                if (i == 3) {
                        System.out.println("Wizard casts a spell to overcome an obstacle!");
                        if (rng <= mana) {
                            System.out.println("The wizard's spell is successful!");
                        } else {
                            System.out.println("The wizard's spell fails, and he is weakened!");
                            health -= 2;
                            if (health <= 0) {
                                System.out.println("The Wizard has fallen in battle!");
                                return;
                            }
                        }
                    }
                if (i == 5){
                        System.out.println("the Wizard try to cast a Powerful speel against the dragon!");
                        if (hasMana.test(rng + 2)) {
                            System.out.println("The wizard's powerful spell is successful!");
                            damageDragon.accept(dmg);
                        } else {
                            System.out.println("The wizard's powerful spell fails, and he is severely weakened!");
                            health -= 4;
                            if (health <= 0) {
                                System.out.println("The Wizard has fallen in battle!");
                                return;
                            }
                        }
                    }
                    if (i == 4) {
                    System.out.println("The Wizard encounters a Dragon!");
                        System.out.println("The Wizard gets the help of a Knight and an Orc to defeat the dragon!");
                        if (rng == mana) {
                            System.out.println("The wizard's spell deals damage to the dragon!");
                            damageDragon.accept(dmg);
                        } else {
                            System.out.println("The dragon resists the wizard's spell and deals a blow to the wizard!");
                            int dragonDmg = 0;
                            if (Dragon.activeDragon != null) {
                                dragonDmg = Dragon.activeDragon.getDragondmg();
                            }
                            health -= dragonDmg;
                            System.out.println("The wizard takes " + dragonDmg + " damage! Wizard HP is now " + health);
                            if (health <= 0) {
                                System.out.println("The Wizard has fallen in battle!");
                                return;
                            }
                        }
                    
                    }
            }
        } catch (InterruptedException e) {
            System.err.println("Wizard's adventure was interrupted!");
        }
        if (health <= 0) {
            System.out.println("The Wizard has fallen in battle!");
            System.out.println("The Wizard's journey ends here.");
        } else {
            
            System.out.println("The Wizard emerges victorious with " + health + " health left!");
                    System.out.println("The Wizard returns having mastered a new spell!");

        }
    }
}
