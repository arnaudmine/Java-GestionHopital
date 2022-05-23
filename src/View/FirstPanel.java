package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FirstPanel extends JPanel {

    private double FrameWidth;

    private ImagePanel imagePanel;
    public FirstPanel(double FrameWidth){
        this.FrameWidth = FrameWidth;
        setLayout(new BorderLayout());
        TitlePanel titlePanel = new TitlePanel();

        Ambulance ambulance = new Ambulance(10,0,1, this);
        imagePanel = new ImagePanel(ambulance, this);
        imagePanel.setPreferredSize(new Dimension(0,100));
        add(titlePanel, BorderLayout.NORTH);
        add(imagePanel,BorderLayout.SOUTH);
    }

    public double getFrameWidth() {
        return FrameWidth;
    }
    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    public void paintComponent(Graphics g) {
        ImageIcon imageIcon = new ImageIcon("src/View/.background.jpg");
        Image image = imageIcon.getImage();
        g.drawImage(image,0,0,this.getSize().width, this.getSize().height, null);
    }

    public class TitlePanel extends JPanel{

        public TitlePanel(){
            setBackground(Color.WHITE);
            setLayout(new FlowLayout());

            Font titleFont = new Font(Font.DIALOG,Font.BOLD|Font.ITALIC,20);
            JLabel title = new JLabel("AGH - Application de Gestion Hospitalière");
            title.setFont(titleFont);
            add(title);
        }
    }
    public class ImagePanel extends JPanel {
        private Ambulance ambulance;
        private FirstPanel firstPanel;
        private ArrayList<Wall> verticalArray = new ArrayList<>();
        private java.util.List<Wall> verticalWalls = Collections.synchronizedList(verticalArray);


        public ImagePanel(Ambulance ambulance, FirstPanel firstPanel)  {
            this.ambulance = ambulance;
            this.firstPanel = firstPanel;
            int wallThickness = 1;
            verticalWalls.add(new Wall(0,0,wallThickness,100));
            verticalWalls.add(new Wall((int)firstPanel.getFrameWidth()-20,0,wallThickness,100));


            MovementThread movementThread = new MovementThread(this);
            movementThread.start();
        }

        public Ambulance getAmbulance() {
            return ambulance;
        }

        public List<Wall> getVerticalWalls() {
            return verticalWalls;
        }

        public void paint(Graphics g){
            ambulance.draw(g);
            for (Wall w : verticalWalls)
                w.draw(g);
            firstPanel.repaint();
        }
    }
}
