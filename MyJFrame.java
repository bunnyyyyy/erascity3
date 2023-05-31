import javax.swing.*;
import java.awt.*;

public class MyJFrame {

    private JFrame frame;
    


    public MyJFrame()
    {
        frame = new JFrame("Eras City"); 
        frame.setBounds(10,10,1000, 800);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
        Color lightPurple = new Color(243, 225, 255);
        frame.getContentPane().setBackground(lightPurple);

    }



    public void setVisible() {
        frame.setVisible(true);
    }

    public void add(JLabel label, int x, int y, int width, int height) {

        label.setBounds(x, y, width, height);
        label.setVisible(true);
        frame.getContentPane().add(label);
        

    }
    public void add(JButton button, int x, int y, int width, int height) {

        button.setBounds(x, y, width, height);
        button.setVisible(true);
        frame.getContentPane().add(button);

    }

    public void move(JLabel label, int x, int y) {
        label.setBounds(x, y, 50, 50);
    }



    

    public static void main(String[] args) {

        // String filepath = "/Users/nmunjal/Downloads/erascity/snowmusic.wav";

        // Music musicObject = new Music();
        // musicObject.playMusic(filepath);
       
        DressUp speakNow = new DressUp();
        speakNow.displayInitial();
        speakNow.setVisible();
        speakNow.buttonsWork();
        speakNow.playMusic();

        
    }
}