package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends JButton {

    private Container mainContainer;

    public BackButton(Container mainContainer){
        this.mainContainer = mainContainer;
        setText("Retour à la page d'accueil");
        addActionListener(new BackButtonListener());

    }
    public class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainContainer.removeAll();
            mainContainer.add(new FirstPanel(900));
            mainContainer.revalidate();
            mainContainer.repaint();
        }
    }
}
