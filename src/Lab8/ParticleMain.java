package Lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ParticleMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Particles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int particleCount = 2000;
        int width = 800;
        int height = 800;

        frame.setSize(width, height);

        ParticleField pf = new ParticleField(particleCount, width, height);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                pf.terminate();
                frame.dispose();
            }
        });

        frame.add(pf);
        frame.pack();
        frame.setVisible(true);
    }
}
