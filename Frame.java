import javax.swing.JFrame;

public class Frame extends JFrame{

   int width = 1800;
   int height = 800;

   public Frame() {
      Panel panel = new Panel(width, height);
      add(panel);
      setSize(width, height);
      setTitle("Matrix Display");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLocationRelativeTo(null);

 }
}
