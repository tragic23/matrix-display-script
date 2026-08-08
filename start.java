import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BasicSwingApp {
    public static void main(String[] args) {
        // Run GUI code on the Event Dispatch Thread (EDT) for thread safety
        SwingUtilities.invokeLater(() -> {
            // 1. Create the main window frame
            JFrame frame = new JFrame("My First Swing GUI");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // 2. Create a panel container to hold components
            JPanel panel = new JPanel();

            // 3. Create a button component
            JButton button = new JButton("Click Me!");
            
            // 4. Add an action listener to handle click events
            button.addActionListener(e -> System.out.println("Button was clicked!"));

            // 5. Assemble and display the UI
            panel.add(button);
            frame.add(panel);
            frame.setLocationRelativeTo(null); // Center window on screen
            frame.setVisible(true);
        });
    }
}