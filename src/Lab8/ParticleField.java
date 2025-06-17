package Lab8;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleField extends JPanel {
    private final Random rng = new Random();
    private boolean running = true;
    private final List<Particle> particles = new ArrayList<Particle>();

    public ParticleField(int n, int width, int height) {
        this.setPreferredSize(new Dimension(width, height));

        for (int i = 0; i < n; i++) {
            particles.add(new Particle(width, height));
        }

        Thread thread = new Thread() {
            public void run() {
                while (running) {
                    try {
                        //noinspection BusyWait
                        Thread.sleep(20);
                    } catch (InterruptedException _) {
                    }

                    for (Particle particle : particles) {
                        particle.move();
                    }

                    repaint();
                }
            }
        };

        thread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256)));

        for (Particle particle : particles) {
            g.fillRect((int) particle.getX(), (int) particle.getY(), 3, 3);
        }

        String msg = "I LOVE LSD";
        g.setFont(new Font("SansSerif", Font.BOLD, 72));
        FontMetrics fm = g.getFontMetrics();

        int x = (getWidth() - fm.stringWidth(msg)) / 2;
        int y = (getHeight() + fm.getAscent()) / 2;

        for (char c : msg.toCharArray()) {
            g.setColor(new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256)));
            g.drawString(String.valueOf(c), x, y);
            x += fm.charWidth(c);
        }
    }

    public void terminate() {
        running = false;
    }
}
