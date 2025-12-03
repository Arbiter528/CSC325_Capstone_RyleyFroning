public class Main {
    public static void main(String[] args) {
        Knight knight = new Knight();
        Wizard wizard = new Wizard();
        Orc orc = new Orc();
        Dragon dragon = new Dragon();

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