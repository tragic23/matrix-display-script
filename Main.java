import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 1. Create a custom panel class
class CustomTextPanel extends JPanel {
    
    private int xCoordinate = 400;
    private int yCoordinate = 400;
    private javax.swing.Timer timer;
    @Override
    protected void paintComponent(Graphics g) {
        // Always call the superclass method first
        super.paintComponent(g); 
        
       
        // Set the text color
        g.setColor(Color.GREEN);
        
        // Set the text font (Font Name, Style, Size)
        g.setFont(new Font("Arial", Font.BOLD, 24));
        
        // Draw the string: "Text", X-coordinate, Y-coordinate
        for(int i = 0; i < 150; i+=26){
            g.drawString("人", xCoordinate, yCoordinate +i);
            if( i > 96){
                g.setColor(Color.WHITE);
            }
        }
            
                
    }


    public CustomTextPanel() {
    

    setBackground(Color.BLACK);    // Timer fires an event every 10 milliseconds (approx. 100 FPS)
    ActionListener taskPerformer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            yCoordinate += 20; // Move 2 pixels to the left            // Reset position if text moves completely off-screen to the left
            
            if (yCoordinate > 800) { 
                yCoordinate = 0; 
                xCoordinate += 100; // should be 24
                if(xCoordinate > 1800){
                    xCoordinate = -150;
                }
            }
            
            
          

            
            repaint(); // Redraw the panel with the new position
        }
    };
    timer = new Timer(100, taskPerformer);
    timer.start();
    
    }
 
}

// 2. Set up the window frame to display the panel
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Java Swing drawString Example");
        CustomTextPanel panel = new CustomTextPanel();
        
        frame.add(panel);
        frame.setSize(1800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
    }
}
