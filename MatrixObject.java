import java.util.Random;

public class MatrixObject{
    Random rand = new Random();
    public int xCoordinate = 0;
    public int yCoordinate = 0;
    public String japaneseString = "";
    public int whiteSpace = 0;
    // string length range
    final int MAX = 20;
    final int MIN = 8;
    
    public MatrixObject(int x,  int y){
        int randomNum = rand.nextInt((MAX - MIN) + 1) + MIN;
        japaneseString = genRandString(randomNum);
        whiteSpace = rand.nextInt((MAX - MIN) + 1) + MIN;
        xCoordinate = x;
        yCoordinate = y-5*(whiteSpace *24);
    } // end MatrixObject constructor 

    public void changeLastChar(){
        japaneseString = japaneseString.substring(0, japaneseString.length() -1) + genRandString(1);
    } // end changeLastChar

    public void restartString(){
        int randomNum = rand.nextInt((MAX - MIN) + 1) + MIN;
        japaneseString = genRandString(randomNum);
        yCoordinate = 0-randomNum*24;
    } // end restartString()

    private String genRandString(int size){
        Random rand = new Random();
        String generateJapanesString = "";
        
        // Katakana Unicode range: 0x30A1 to 0x30F6
        int MIN = 0x30A1;
        int MAX = 0x30F6;
        
        for(int i = 0; i < size; i++){
            char randomKatakana = (char) (rand.nextInt((MAX - MIN) + 1) + MIN);
            generateJapanesString =  "" + randomKatakana + generateJapanesString;
        }
        return generateJapanesString;
    } // end genRandString()
} // end class