package View;

import javax.swing.*;
import java.awt.*;

public class Ambulance {

    private int x;
    private int y;
    private int scale;
    private int w;
    private int h;
    private int delta;
    private Rectangle hitbox;
    private ImageIcon imageIcon;
    private ImageIcon imageIconReverse;
    private Image image;
    private boolean imageIsReverse = false;
    private FirstPanel firstPanel;

    public Ambulance(int x , int y, int scale, FirstPanel firstPanel) {
        this.firstPanel = firstPanel;
        this.x = x;
        this.y = y;
        this.scale = scale;
        w = 100*scale;
        h = 100*scale;
        this.delta = 1;
        hitbox = new Rectangle(x,y,w,h);

        imageIcon = new ImageIcon("ambulance.jpg");
        imageIconReverse = new ImageIcon("ambulanceReverse.jpg");
        image = imageIcon.getImage();

    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void draw(Graphics g){
        g.drawImage(image, x,y,w,h,null);
    }

    public void move(){
        for(Wall w : firstPanel.getImagePanel().getVerticalWalls()){
            if(w.collision(this)){
                delta = -delta;
                if(imageIsReverse){
                    image = imageIcon.getImage();
                }
                else{
                    image = imageIconReverse.getImage();
                }
                imageIsReverse = imageIsReverse ? false: true;
            }
        }
        x += delta; //mouvement image
        hitbox.x +=delta; //mouvement hitbox
    }
}
