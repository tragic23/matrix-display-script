import java.util.Random;

public class RandomJapaneseLetter {
    public static void main(String[] args) {
        Random rand = new Random();
        
        // Hiragana Unicode range: 0x3041 to 0x3093
        int min = 0x3041;
        int max = 0x3093;
        
        char randomHiragana = (char) (rand.nextInt((max - min) + 1) + min);
        
        System.out.println("Random Japanese Letter: " + randomHiragana);
    }
}