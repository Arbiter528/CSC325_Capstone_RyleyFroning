import java.util.Random;

public class Wizard extends Thread implements GameCharacter {
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
                    }
            }
        } catch (InterruptedException e) {
            System.err.println("Wizard's adventure was interrupted!");
        }
        System.out.println("The Wizard returns having mastered a new spell!");
    }
}
