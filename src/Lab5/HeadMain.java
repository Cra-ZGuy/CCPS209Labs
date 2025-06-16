package Lab5;

import javax.swing.*;
import java.awt.*;

public class HeadMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Interactive Heads");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new GridLayout(2, 2));

        frame.add(new Head());
        frame.add(new Head());
        frame.add(new Head());
        frame.add(new Head());

        frame.setVisible(true);
    }
}
