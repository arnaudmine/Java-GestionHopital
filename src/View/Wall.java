package View;

import java.awt.*;


public class Wall {
    private Rectangle rectangle;
    public Wall(int x, int y, int width, int height){
        rectangle = new Rectangle(x, y ,width , height);

    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height); //dessine les paroies du rectangle
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height); //rempli le rectangle
    }
    public boolean collision(Ambulance ambulance){
           return rectangle.intersects(ambulance.getHitbox());
    }
}