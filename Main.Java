public class Main {
    public static void main(String[] args) {
        Knight knight = new Knight();
        Wizard wizard = new Wizard();
        Orc orc = new Orc();

        knight.start();
        wizard.start();
        orc.start();

        try {
            knight.join();
            wizard.join();
            orc.join();
        } catch (InterruptedException e) {
            System.err.println("The adventure was interrupted!");
        }

        System.out.println("All characters have completed their adventures!");
    }

}