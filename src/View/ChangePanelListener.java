package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePanelListener implements ActionListener {

    private JPanel newPanel;
    private Container mainContainer;

    public ChangePanelListener(JPanel newPanel, Container mainContainer){
        this.newPanel = newPanel;
        this.mainContainer = mainContainer;
    }


    public void actionPerformed(ActionEvent e) {
        mainContainer.removeAll();
        mainContainer.add(newPanel);
        mainContainer.repaint();
        mainContainer.revalidate();
    }
}
