import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.Color;

public class RectangleObject extends CollisionObject {
    private double width;
    private double height;
    private Color color;
    
    public RectangleObject(double x, double y, double width, double height, Color color) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public boolean collidesWith(CollisionObject other) {
        if (other instanceof RectangleObject) {
            RectangleObject r = (RectangleObject) other;
            return (this.getX() < r.getX() + r.width && this.getX() + width > r.getX() && this.getY() < r.getY() + r.height && this.getY() + height > r.getY());
        } else {
            return false;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect((int) this.getX(), (int) this.getY(), (int) width, (int) height);
    }

    @Override
    public double ComputeDistance(double CameraX, double CameraY) {
        double distance = Double.POSITIVE_INFINITY;

        Point2D[] vertices = getVertices();
        for (int i = 0; i < vertices.length; i++) {
            Line2D segment = new Line2D.Double(vertices[i], vertices[(i + 1) % vertices.length]);
            double segmentDistance = segment.ptSegDist(CameraX, CameraY);
            distance = Math.min(distance, segmentDistance);
        }
        return distance;
    }

    private Point2D[] getVertices() {
        Point2D[] vertices = new Point2D[4];
        vertices[0] = new Point2D.Double(this.getX(), this.getY());
        vertices[1] = new Point2D.Double(this.getX() + width, this.getY());
        vertices[2] = new Point2D.Double(this.getX() + width, this.getY() + height);
        vertices[3] = new Point2D.Double(this.getX(), this.getY() + height);
        return vertices;
    }
}