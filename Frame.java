import javax.swing.JFrame;
import java.awt.event.*;

public class Frame extends JFrame {
   int width = 800;
   int height = 400;

   public Frame() {
      Panel panel = new Panel(width, height);
      add(panel);
      setSize(width, height);
      addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int newWidth = panel.getWidth() ;  // Half of window width
                int newHeight = panel.getHeight() ; // Half of window height
          
          
                panel.setPanelSize(newWidth, newHeight);
                panel.revalidate();
            }
      });
      setTitle("Matrix Display");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      
   }


}
