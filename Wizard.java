
import java.util.Random;


public class Wizard extends Thread implements Rng, GameCharacter {
    int health = 8;
    int mana = 15;
    @Override
    public void run() {
        System.out.println("The wise Wizard conjures a protective spell and sets off on an adventure!");
        Random rand = new Random();
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Wizard: Step " + i);
                Thread.sleep(rand.nextInt(1000) + 500);
                    if (i == 3) {
                        System.out.println("Wizard casts a spell to overcome an obstacle!");
                        if (rng <= mana) {
                            System.out.println("The wizard's spell is successful!");
                            
                        } else {
                            System.out.println("The wizard's spell fails, and he is weakened!");
                            health -= 2;
                        }
                    }
                    if (i == 4) {
                    System.out.println("The Wizard encounters a Dragon!");
                        System.out.println("The Wizard gets the help of a Knight and an Orc to defeat the dragon!");
                        if (rng <= mana) {
                            System.out.println("The wizard's spell deals damage to the dragon!");
                            int dmg = rand.nextInt(9) + 2; 
                            if (Dragon.activeDragon != null) {
                             Dragon.activeDragon.damageHealth(dmg);
                                System.out.println("The spell hits the Dragon for " + dmg + " damage! Dragon HP is now " + Dragon.activeDragon.getHealth());
                            } 
                            else {
                                System.out.println("But there is no dragon to hit.");
                         }
                        } else {
                            System.out.println("The dragon resists the wizard's spell and deals a blow to the wizard!");
                            health -= 2;
                        }
                    }
            }
        } catch (InterruptedException e) {
            System.err.println("Wizard's adventure was interrupted!");
        }
        System.out.println("The Wizard returns having mastered a new spell!");
    }
}
