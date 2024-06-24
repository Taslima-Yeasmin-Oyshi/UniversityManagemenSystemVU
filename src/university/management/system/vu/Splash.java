package university.management.system.vu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Splash extends JFrame implements Runnable {
    private JProgressBar progressBar;

    Splash() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);

        // Set the initial color to black
        progressBar.setForeground(Color.BLACK);

        add(progressBar, BorderLayout.SOUTH);

        ImageIcon originalImageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/university pic.jpg"));
        Image originalImage = originalImageIcon.getImage();
        ImageIcon scaledImageIcon = new ImageIcon(originalImage);

        JLabel backgroundLabel = new JLabel(scaledImageIcon);
        add(backgroundLabel, BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Image scaledImage = originalImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                scaledImageIcon.setImage(scaledImage);
                backgroundLabel.repaint();
            }
        });

        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            loadNextFrame();
        });
    }

    private void loadNextFrame() {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(3000); // Show the splash screen for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(70);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Dynamically set the color based on the progress value
                if (i == 0) {
                    progressBar.setForeground(Color.BLACK);
                } else {
                    progressBar.setForeground(new Color(192, 57, 43)); // Terracotta color
                }

                progressBar.setValue(i);
            }

            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                new Login();
            });
        });

        t.start();
    }

    public void run() {
        // Add any additional code if needed
    }

    public static void main(String[] args) {
        new Splash();
    }
}
