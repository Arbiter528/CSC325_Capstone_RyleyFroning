import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        // Create a barrier for 4 threads
        CyclicBarrier barrier = new CyclicBarrier(4);
        
        Knight knight = new Knight(barrier);
        Wizard wizard = new Wizard(barrier);
        Orc orc = new Orc(barrier);
        Dragon dragon = new Dragon(barrier);

        // Register the active dragon so characters can interact with it
        Dragon.activeDragon = dragon;

        knight.start();
        wizard.start();
        orc.start();
        dragon.start();

        try {
            knight.join();
            wizard.join();
            orc.join();
            dragon.join();
        } catch (InterruptedException e) {
            System.err.println("The adventure was interrupted!");
        }

        System.out.println("All characters have completed their adventures!");
    }

}