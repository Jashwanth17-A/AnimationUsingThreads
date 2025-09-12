import javax.swing.*;
import java.awt.*;
import java.util.Random;

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
            g.fillOval(cloudX + 40, cloudY - 20, 100, 60);
            g.fillOval(cloudX + 80, cloudY, 100, 60);

            // Grass
            g.setColor(Color.GREEN);
            g.fillRect(0, getHeight() - 100, getWidth(), 100);

            // Car
            g.setColor(Color.RED);
            g.fillRect(carX, carY, 120, 50);
            g.setColor(Color.BLACK);
            g.fillOval(carX + 10, carY + 40, 30, 30);
            g.fillOval(carX + 80, carY + 40, 30, 30);
        }

        // ================= THREADS =================
        class SunThread implements Runnable {
            public void run() {
                try {
                    while (true) {
                        sunX += 2;
                        if (sunX > getWidth()) sunX = -80;
                        repaint();
                        Thread.sleep(50);
                    }
                } catch (InterruptedException ignored) {}
            }
        }

        class CloudThread implements Runnable {
            public void run() {
                try {
                    while (true) {
                        cloudX += 1;
                        if (cloudX > getWidth()) cloudX = -200;
                        repaint();
                        Thread.sleep(80);
                    }
                } catch (InterruptedException ignored) {}
            }
        }

        class CarThread implements Runnable {
            public void run() {
                try {
                    while (true) {
                        carX += 5;
                        if (carX > getWidth()) carX = -150;
                        repaint();
                        Thread.sleep(40);
                    }
                } catch (InterruptedException ignored) {}
            }
        }
    }
}