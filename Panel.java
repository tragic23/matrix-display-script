import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.*;

// 1. Create a custom panel class
class Panel extends JPanel {
    Random rand = new Random();
    
    private int xCoordinate = 0;
    private int yCoordinate = 0;
    private javax.swing.Timer timer;
    private int PanelWidth = 0;
    private int PanelHeight = 0;
    private int yMovement = 0;
    private int fontSize = 0;
    private java.util.List<MatrixObject> matrixArr = new ArrayList<>();
    
    public Panel(int w, int h) {
        setPanelSize(w,h);
        createList();
        setDoubleBuffered(true);
        setBackground(Color.BLACK);    // Timer fires an event every 10 milliseconds (approx. 100 FPS)
        
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {       
               // moves string down 3 px, and when off screen resets string and postion back at top
                for(MatrixObject tempO: matrixArr ){
                    //System.out.println(yMovement);
                    tempO.yCoordinate += yMovement;
                        if (tempO.yCoordinate > PanelHeight + (tempO.japaneseString.length() * fontSize) ) {
                            tempO.restartString();
                        } 
                }
                repaint(); // Redraw the panel with the new position
            }
        }; 
        timer = new javax.swing.Timer(16, taskPerformer);
        timer.start();
    } // end Panel constructor 

    private void createList() { // create the init columns of strings   
        for(int i = 0; i < this.PanelWidth / fontSize; i++){
            MatrixObject matrixS =  new MatrixObject( xCoordinate + (i * fontSize),  yCoordinate );
            matrixArr.add(matrixS);
        }
    } // end createList()
    
    public void setPanelSize(int w, int h){
        if(w != PanelWidth || h != PanelHeight){
            PanelWidth = w; 
            PanelHeight = h;
            yMovement = PanelHeight / 150;
            fontSize = 24;
            matrixArr.removeAll(matrixArr);
            createList();
        }
    } // end setPanelSize()
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D) g.create();

        // invert pannel on y axis to mirrir the glyphes
        AffineTransform transform = AffineTransform.getScaleInstance(-1, 1);
        transform.translate(-getWidth(), 0);
        g2.transform(transform);
        
        for(MatrixObject tempO: matrixArr ){
           
            drawMatrixString(g2, tempO);
        }
        g2.dispose();
    } // end paintComponent()

    private void drawMatrixString(Graphics2D g, MatrixObject object){
        // Set the text font (Font Name, Style, Size)
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontSize));
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int length = object.japaneseString.length();

        for(int i = 0; i < length; i++){
            // set fade
            float constant = 1f/ length;
            float opacity =  (i * constant);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

            // Set the text color
            if( i == length-1)
                g.setColor(Color.WHITE);
            else 
                g.setColor(Color.GREEN);
            // .5% of changing white char
            if (rand.nextDouble() < 0.005 && i == length -1)
                object.changeLastChar();
            // draw char
            g.drawString( object.japaneseString.substring(i , i +1 ), object.xCoordinate , object.yCoordinate + (i * fontSize));
        }      
    } // end drawStringMethod
} // end class


