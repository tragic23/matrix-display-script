
import javax.swing.*;
import java.awt.*;
import java.util.*;

import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



// 1. Create a custom panel class
class Panel extends JPanel {
    
    private int xCoordinate = 0;
    private int yCoordinate = 0;
    private javax.swing.Timer timer;
    String randomEmoticon = new String(Character.toChars(0x1F63B));
    Random rand = new Random();
    String matrixString = "";
    int max = 15;
    int min = 8;
    
   
    int PanelWidth = 1800;
    int PanelHeight = 800;
    int[] randomYStart = new int[PanelWidth / 22];


    

    
    java.util.List<MatrixObject> matrixArr = new ArrayList<>();
    
    public Panel( int w, int h) {
        
        PanelWidth = w;
        PanelHeight = h;
        




        for(int i = 0; i < randomYStart.length; i++ ){
            randomYStart[i] = rand.nextInt((max - min) + 1) + min;
        }
        
        createList();
        
        setBackground(Color.BLACK);    // Timer fires an event every 10 milliseconds (approx. 100 FPS)
        
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                
                int i = 0;
                for(MatrixObject tempO: matrixArr ){
                    
                    tempO.yCoordinate += 10;
                        if (tempO.yCoordinate > PanelHeight + (tempO.japaneseString.length() * 24) )  
                            tempO.yCoordinate = 0-  2*(randomYStart[i] *24); 
                        
                    i++;
                
                     
                }
                
                
                repaint(); // Redraw the panel with the new position
            }
        };
        timer = new javax.swing.Timer(100, taskPerformer);
        timer.start();
    }


    private void createList() {
        String tempS = "";
        
        for(int i = 0; i < PanelWidth / 22; i++){
            int randomNum = rand.nextInt((max - min) + 1) + min;
            tempS = genRandString(randomNum);
            MatrixObject matrixS =  new MatrixObject(tempS, xCoordinate + (i * 22),  yCoordinate -  2*(randomYStart[i] *24));
            matrixArr.add(matrixS);

        }
     
    }
    @Override
    protected void paintComponent(Graphics g) {
        // Always call the superclass method first
        super.paintComponent(g); 
        
        for(MatrixObject tempO: matrixArr ){
           
            drawMatrixString(g, tempO);
        }
            
        
        
       
            

    }

    private void drawMatrixString(Graphics g, MatrixObject object){
         
        // Set the text color
        g.setColor(Color.GREEN);
        // Set the text font (Font Name, Style, Size)
        g.setFont(new Font("Arial", Font.BOLD, 24));
        
        int length = object.japaneseString.length();
        
        for(int i = 0; i < length; i++){
            System.out.println(object.xCoordinate);
            g.drawString( object.japaneseString.substring(i , i +1 ), object.xCoordinate , object.yCoordinate + (i * 24));
            if( i == length -2){
                g.setColor(Color.WHITE);
            }
        }      
    }

    private String genRandString(int size){
        Random rand = new Random();
        String generateJapanesString = "";
        
        // Hiragana Unicode range: 0x3041 to 0x3093
        int min = 0x3041;
        int max = 0x3093;
        
        for(int i = 0; i < size; i++){
            char randomHiragana = (char) (rand.nextInt((max - min) + 1) + min);


            generateJapanesString =  "" + randomHiragana + generateJapanesString;
        }
        return generateJapanesString;
    }
}


