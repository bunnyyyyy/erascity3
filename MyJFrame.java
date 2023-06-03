import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Creates a JFrame and allows for adding items onto it.
 * @author Sanya Badhe
 * @version 6/2/23
 *
 */
public class MyJFrame {

    private JFrame frame;
    


    /**
     * Initializes a JFrame and customizes it.
     */
    public MyJFrame()
    {
        frame = new JFrame("Eras City"); 
        frame.setBounds(10,10,1000, 800);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
        Color lightPurple = new Color(243, 225, 255);
        frame.getContentPane().setBackground(lightPurple);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Returns the JFrame.
     * @return a JFrame
     */
    public JFrame frame() {
        return frame;
    }



    /**
     * Sets the frame to visible.
     */
    public void setVisible() {
        frame.setVisible(true);
    }


    /**
     * Adds a label to the frame.
     * 
     * @param label label to be added
     * @param x x-coordinate
     * @param y y-coordinatee
     * @param width width of label
     * @param height height of label
     */
    public void add(JLabel label, int x, int y, int width, int height) {

        label.setBounds(x, y, width, height);
        label.setVisible(true);
        frame.getContentPane().add(label);
        

    }

    /**
     * Adds a button to the frame.
     * 
     * @param button button to be added
     * @param x x-coordinate
     * @param y y-coordinatee
     * @param width width of button
     * @param height height of button
     */
    public void add(JButton button, int x, int y, int width, int height) {

        button.setBounds(x, y, width, height);
        button.setVisible(true);
        frame.getContentPane().add(button);

    }

    /**
     * Moves a label to a new position.
     * 
     * @param label label to be moveed
     * @param x new x-coord
     * @param y new y-coord
     */
    public void move(JLabel label, int x, int y) {
        label.setBounds(x, y, 50, 50);
    }

    

   



  
}