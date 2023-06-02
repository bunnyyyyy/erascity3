import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }

        });
    }

    public JFrame frame() {
        return frame;
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

       

        
        // SwingUtilities.invokeLater(() -> {
        //     KanyeHead kanyeHead = new KanyeHead(1); 
        
        //     kanyeHead.setVisible();

        //     Thread thread = new Thread(kanyeHead);
        //     thread.start();
        // });
    
    //    KanyeHead head = new KanyeHead(1);
    //    head.run();

    //    head.setVisible();



     

        


    

        // String filepath = "/Users/nmunjal/Downloads/erascity/snowmusic.wav";

        // Music musicObject = new Music();
        // musicObject.playMusic(filepath);
       
        // DressUp speakNow = new DressUp();
        
        // speakNow.displayInitial();
        // speakNow.setVisible();
        
        // speakNow.buttonsWork();
        // speakNow.playMusic();

        
    }
}