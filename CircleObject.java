import java.awt.Color;
import java.awt.Graphics2D;

public class CircleObject extends CollisionObject {
    private int radius;
    private Color color;

    public CircleObject(double x, double y, int radius, Color color) {
        super(x, y);
        this.radius = radius;
        this.color = color;
    }

    public int getRadius() {
        return radius;
    }

    public int getDiameter() {
        return 2 * radius;
    }

    @Override
    public boolean collidesWith(CollisionObject circle) {
        if (circle instanceof CircleObject) {
            CircleObject c = (CircleObject) circle;
            return (Math.pow(this.getX() - c.getX(), 2) + Math.pow(this.getY() - c.getY(), 2) < Math.pow(radius + c.radius, 2));
        } else {
            return false;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval((int) this.getX() - radius, (int) this.getY() - radius, radius * 2, radius * 2);
    }

    @Override
    public double ComputeDistance(double CameraX, double CameraY) {
        return Math.sqrt(Math.pow(this.getX() - CameraX, 2) + Math.pow(this.getY() - CameraY, 2)) - radius;
    }

}