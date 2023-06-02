import javax.sound.sampled.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;




public class SoundInputGUI extends MyJFrame {
    private boolean isRecording = false;
    private ByteArrayOutputStream outputStream;
    private byte[] audioData;
    private JButton startButton, stopButton;
    private JLabel recordingLabel;
    private final int MAX_BYTE_ARRAY_SIZE = 500 * 1024;

    
    public SoundInputGUI() {
        
        super();
       
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        recordingLabel = new JLabel();

        startButton.setFont(new Font("Arial", Font.BOLD, 25));
        stopButton.setFont(new Font("Arial", Font.BOLD, 25));
        recordingLabel.setFont(new Font("Arial", Font.BOLD, 25));

    }
    
    
    private void createGUI() {
        
        super.add(startButton, 60, 70, 100, 50);
        super.add(stopButton, 180, 70, 100, 50);
        super.add(recordingLabel, 175, 150, 600, 50);

        
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isRecording) {
                    startRecording();
                    recordingLabel.setText("Recording...");
                }
            }
        });
        
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isRecording) {
                    stopRecording();
                    recordingLabel.setText("Recording stopped.");
                    audioData = outputStream.toByteArray();
                    try {
                        SongDetection.getSong(Base64.getEncoder().encodeToString(audioData));
                    } 
                    catch (IOException e1) {
                        
                        e1.printStackTrace();
                    }

                }
            }
        });
    }
    
    private void startRecording() {
        try {
            AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
            
            isRecording = true;
            outputStream = new ByteArrayOutputStream();
            
            new Thread(new Runnable() {
                public void run() {
                    byte[] buffer = new byte[4096];
                    int totalBytesRead = 0;
                    while (isRecording) {
                        int bytesRead = line.read(buffer, 0, buffer.length);
                        outputStream.write(buffer, 0, bytesRead);
                        totalBytesRead += bytesRead;

                        // checks if byte array size exceeds the limit????!!
                        if (totalBytesRead >= MAX_BYTE_ARRAY_SIZE - 5) {
                            stopRecording();
                            recordingLabel.setText("Recording stopped. Max length taken.");
                            audioData = outputStream.toByteArray();
                            try {
                                SongDetection.getSong(Base64.getEncoder().encodeToString(audioData));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            break;
                        }
                    }
                }
            }).start();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    private void stopRecording() {
        isRecording = false;
    }
    



    public static void main(String[] args) {
        SoundInputGUI soundInputGUI = new SoundInputGUI();
        soundInputGUI.createGUI();
        soundInputGUI.setVisible();


    }
}
