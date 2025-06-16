package Lab5;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;

public class Head extends JPanel {
    private boolean mouseInside = false;

    public Head() {
        this.setPreferredSize(new Dimension(500, 500));
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Head head = (Head) e.getSource();
                head.mouseInside = true;
                head.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Head head = (Head) e.getSource();
                head.mouseInside = false;
                head.repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw head
        g2.setPaint(new Color(255, 200, 175));
        g2.fill(new Ellipse2D.Double(50, 50, 400, 400));

        g2.setStroke(new BasicStroke(2));
        g2.setPaint(new Color(205, 150, 125));
        g2.draw(new Ellipse2D.Double(50, 50, 400, 400));

        // Draw smile
        g2.setStroke(new BasicStroke(15));
        g2.drawArc(180, 300, 140, 100, 180, 180);
        g2.setStroke(new BasicStroke(5));
        g2.setColor(new Color(255, 150, 125));
        g2.drawArc(180, 300, 140, 100, 180, 180);

        // Draw hair
        int faceCenter = 250;
        int faceRadius = 210;
        int spacing = 4;

        g2.setStroke(new BasicStroke(2));

        Random rnd = new Random(1L);

        for (int y = faceCenter - faceRadius; y <= faceCenter + faceRadius; y += spacing) {
            for (int x = faceCenter - faceRadius; x <= faceCenter + faceRadius; x += spacing) {
                int dx = x - faceCenter;
                int dy = y - faceCenter;

                if (dx * dx + dy * dy > faceRadius * faceRadius) continue;

                // Create hairline curve (parabola-like arch)
                double normalizedX = (double) dx / faceRadius;
                double archLimit = Math.pow(normalizedX, 2) * 0.5 + 0.5;
                int archYLimit = (int) (faceCenter - faceRadius + faceRadius * archLimit);

                if (y > archYLimit) continue;

                float brownHue = 0.08F + rnd.nextFloat() * 0.02F;
                float saturation = 0.6F + rnd.nextFloat() * 0.2F;
                float brightness = 0.3F + rnd.nextFloat() * 0.4F;

                g2.setColor(Color.getHSBColor(brownHue, saturation, brightness));
                g2.drawLine(x, y, (int) (x + 10 * rnd.nextFloat() + 10), (int) (y + 10 * rnd.nextFloat() + 10));
            }
        }

        // Draw eyes
        g2.setStroke(new BasicStroke(5));
        g2.setPaint(new Color(205, 150, 125));

        if (this.mouseInside) {
            g2.drawOval(115, 190, 120, 120);
            g2.drawOval(265, 190, 120, 120);

            g2.setColor(Color.WHITE);
            g2.fillOval(115, 190, 120, 120);
            g2.fillOval(265, 190, 120, 120);

            g2.setColor(Color.BLACK);
            g2.fillOval(155, 230, 40, 40);
            g2.fillOval(305, 230, 40, 40);
        } else {
            g2.drawLine(115, 250, 235, 250);
            g2.drawLine(265, 250, 385, 250);
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Head");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(new FlowLayout());
        f.add(new Head());
        f.pack();
        f.setVisible(true);
    }
}