import javax.swing.*;
import java.awt.*;

/**
 * Fun Kids Animation using Multithreading
 * Each object (Sun, Cloud, Car) moves independently in its own thread.
 */
public class KidsAnimation extends JFrame {

    public KidsAnimation() {
        setTitle("Fun Kids Animation with Multithreading");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        AnimationPanel panel = new AnimationPanel();
        add(panel);

        setVisible(true);

        // Start threads
        new Thread(panel.new SunThread()).start();
        new Thread(panel.new CloudThread()).start();
        new Thread(panel.new CarThread()).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KidsAnimation::new);
    }

    // ================== PANEL =====================
    class AnimationPanel extends JPanel {
        int sunX = 50, sunY = 50;
        int cloudX = 0, cloudY = 100;
        int carX = 0, carY = 350;

        public AnimationPanel() {
            setBackground(new Color(135, 206, 235)); // Sky blue
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Sun
            g.setColor(Color.YELLOW);
            g.fillOval(sunX, sunY, 80, 80);

            // Cloud
            g.setColor(Color.WHITE);
            g.fillOval(cloudX, cloudY, 100, 60);
            g.fillOval(cloudX + 30, cloudY - 20, 100, 60);
            g.fillOval(cloudX + 60, cloudY, 100, 60);

            // Car
            g.setColor(Color.RED);
            g.fillRect(carX, carY, 100, 50);
            g.setColor(Color.BLACK);
            g.fillOval(carX + 10, carY + 40, 20, 20);
            g.fillOval(carX + 70, carY + 40, 20, 20);
        }

        // ================== THREADS =====================
        class SunThread implements Runnable {
            public void run() {
                while (true) {
                    sunX += 1;
                    if (sunX > getWidth()) sunX = -100;
                    repaint();
                    try {
                        Thread.sleep(50); // Slowest
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        class CloudThread implements Runnable {
            public void run() {
                while (true) {
                    cloudX += 2;
                    if (cloudX > getWidth()) cloudX = -150;
                    repaint();
                    try {
                        Thread.sleep(40); // Medium speed
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        class CarThread implements Runnable {
            public void run() {
                while (true) {
                    carX += 3;
                    if (carX > getWidth()) carX = -200;
                    repaint();
                    try {
                        Thread.sleep(30); // Fastest
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
