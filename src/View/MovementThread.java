package View;



public class MovementThread extends Thread{

    private FirstPanel.ImagePanel imagePanel;

    public MovementThread(FirstPanel.ImagePanel imagePanel) {
        this.imagePanel = imagePanel;
    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(2);
                imagePanel.getAmbulance().move();
                imagePanel.repaint();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
