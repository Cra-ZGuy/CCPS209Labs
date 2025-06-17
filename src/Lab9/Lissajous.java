package Lab9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Lissajous extends JPanel {
    private final int size;
    private final JTextField aField, bField, deltaField;

    public Lissajous(int size) {
        this.size = size;
        this.setPreferredSize(new Dimension(size, size));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        aField = new JTextField("6     ");
        bField = new JTextField("5     ");
        deltaField = new JTextField("0.5     ");

        this.add(new JLabel("a:"));
        this.add(aField);
        this.add(new JLabel("b:"));
        this.add(bField);
        this.add(new JLabel("delta:"));
        this.add(deltaField);

        ActionListener listener = new ParamChangeListener();
        aField.addActionListener(listener);
        bField.addActionListener(listener);
        deltaField.addActionListener(listener);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        double a, b, delta;
        try {
            a = Double.parseDouble(aField.getText().trim());
            b = Double.parseDouble(bField.getText().trim());
            delta = Double.parseDouble(deltaField.getText().trim());
        } catch (NumberFormatException e) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));

        Path2D.Double path = getADouble(a, b, delta);

        g2.draw(path);
    }

    private Path2D.Double getADouble(double a, double b, double delta) {
        int centerX = size / 2;
        int centerY = size / 2;
        int radius = 2 * size / 5;
        int steps = 3000;

        double maxT = (a / gcd((int) a, (int) b)) * b;

        Path2D.Double path = new Path2D.Double();

        double t = 0;
        double x = centerX + radius * Math.sin(a * t * Math.PI + delta);
        double y = centerY + radius * Math.sin(b * t * Math.PI);

        path.moveTo(x, y);

        for (int i = 1; i <= steps; i++) {
            t = i * maxT / steps;
            x = centerX + radius * Math.sin(a * t * Math.PI + delta);
            y = centerY + radius * Math.sin(b * t * Math.PI);
            path.lineTo(x, y);
        }

        return path;
    }

    private class ParamChangeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }
}
