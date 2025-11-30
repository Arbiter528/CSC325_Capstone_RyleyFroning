import java.util.Random;

class Knight extends Thread implements GameCharacter {
    Random rand = new Random();
    int health = 10;
    int rng = rand.nextInt(15) + 1;
    int gamerng = rand.nextInt(4) + 1;
    int stamina = 10;
    @Override
    public void run() {
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
                        }
                }
                if (i == 3) {
                    System.out.println("Knight draws his sword, ready for battle!");
                        System.out.println("A goblin appears!");
                        if (rng <= stamina) {
                            System.out.println("Knight bravely defeats the goblin!");
                        } else {
                            System.out.println("Knight is wounded by the goblin but presses on!");
                            health -= 2;
                        }
                }
                if (i == 4) {
                    System.out.println("Knight draws his sword, ready for battle!");
                        System.out.println("A goblin appears!");
                        if (rng <= stamina) {
                            System.out.println("Knight bravely defeats the goblin!");
                        } else {
                            System.out.println("Knight is wounded by the goblin but presses on!");
                            health -= 2;
                        }
                }
                if (i == 5) {
                    System.out.println("Knight draws his sword, ready for battle!");
                        System.out.println("A goblin appears!");
                        if (rng <= stamina) {
                            System.out.println("Knight bravely defeats the goblin!");
                        } else {
                            System.out.println("Knight is wounded by the goblin but presses on!");
                            health -= 2;
                        }
                }
                Thread.sleep(rand.nextInt(1000) + 500);
            }
        } catch (InterruptedException e) {
            System.err.println("Knight's adventure was interrupted!");
        }
        if (health > 0) {
            System.out.println("The Knight returns victorious from his quest!");
        } else {
            System.out.println("The Knight has fallen in battle.");
        }
        
    }
}
