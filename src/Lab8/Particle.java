package Lab8;

import java.util.Random;

public class Particle {
    private static final Random rng = new Random();
    private static final double BUZZY = 0.7;

    private double x;
    private double y;
    private double heading;

    public Particle(int width, int height) {
        this.x = rng.nextDouble(width + 1);
        this.y = rng.nextDouble(height + 1);
        this.heading = Math.PI * 2 * rng.nextDouble();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void move() {
        this.x += Math.cos(this.heading);
        this.y += Math.sin(this.heading);
        this.heading += rng.nextGaussian() * BUZZY;
    }
}
