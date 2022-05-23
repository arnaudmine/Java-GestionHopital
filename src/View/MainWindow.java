package View;

import Controller.ApplicationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

    private Container mainContainer;
    private JMenuBar menuBar;
    private JMenu consultationMenu, patientMenu, nurseMenu, applicationMenu;
    private JMenuItem searchConsultationItem, searchPatientItem, createPatientItem, modifyPatientItem, searchNurseItem, createNurseItem, exitItem, firstPageItem;

    public MainWindow() {
        super("AGH");
        setSize(900,700);
        setLocationRelativeTo(null);
        mainContainer = this.getContentPane();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    ApplicationController controller = new ApplicationController();
                    controller.disconnect();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });

        //MenuBar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Consultations
        consultationMenu = new JMenu("Consultations");
        consultationMenu.setMnemonic('c');
        menuBar.add(consultationMenu);

        searchConsultationItem = new JMenuItem("Rechercher");
        searchConsultationItem.addActionListener(new SearchConsultationListener());
        consultationMenu.add(searchConsultationItem);

        //Patient
        patientMenu = new JMenu("Patient");
        patientMenu.setMnemonic('p');
        menuBar.add(patientMenu);

        searchPatientItem = new JMenuItem("Rechercher");
        searchPatientItem.addActionListener(new SearchPatientListener());
        createPatientItem = new JMenuItem("Ajouter un patient");
        createPatientItem.addActionListener(new ChangePanelListener(new CreatePatientPanel(mainContainer), mainContainer));

        patientMenu.add(searchPatientItem);
        patientMenu.add(createPatientItem);


        //Nurse
        nurseMenu = new JMenu("Infirmier");
        nurseMenu.setMnemonic('i');
        menuBar.add(nurseMenu);

        searchNurseItem = new JMenuItem("Rechercher");
        searchNurseItem.addActionListener(new ChangePanelListener(new SearchNursePanel(mainContainer), mainContainer));
        createNurseItem = new JMenuItem("Ajout infirmier(e)");
        createNurseItem.addActionListener(new ChangePanelListener(new CreateNursePanel(mainContainer), mainContainer));
        nurseMenu.add(searchNurseItem);
        nurseMenu.add(createNurseItem);

        //Application
        applicationMenu = new JMenu("Application");
        applicationMenu.setMnemonic('a');
        menuBar.add(applicationMenu);

        exitItem = new JMenuItem("Fermer l'application");
        exitItem.addActionListener(new ExitListener());
        applicationMenu.add(exitItem);

        firstPageItem = new JMenuItem("Page d'accueil");
        firstPageItem.addActionListener(new ChangePanelListener(new FirstPanel(this.getSize().getWidth()), mainContainer));
        applicationMenu.add(firstPageItem);


        mainContainer.add(new FirstPanel(this.getSize().getWidth()));
        this.setVisible(true);
    }
    public class SearchConsultationListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SearchConsultationPanel searchConsultationPanel = new SearchConsultationPanel();
            mainContainer.removeAll();
            mainContainer.add(searchConsultationPanel);
            mainContainer.repaint();
            mainContainer.revalidate();
        }
    }
    public class SearchPatientListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            SearchPatientPanel searchPatientPanel= new SearchPatientPanel(mainContainer);
            mainContainer.removeAll();
            mainContainer.add(searchPatientPanel);
            mainContainer.repaint();
            mainContainer.revalidate();
        }
    }
    public class ExitListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {
                ApplicationController controller = new ApplicationController();
                controller.disconnect();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        }
    }
}