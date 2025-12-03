import java.util.Random;
public interface  Rng {
    Random rand = new Random();
    int rng = rand.nextInt(15) + 1;
}
