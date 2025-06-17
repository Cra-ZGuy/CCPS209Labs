package Lab9;

import javax.swing.*;

public class LissajousMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Lissajous");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Lissajous(600));
        frame.pack();
        frame.setVisible(true);
    }
}

