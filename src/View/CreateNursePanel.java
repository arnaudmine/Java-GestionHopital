package View;

import Controller.ApplicationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreateNursePanel extends JPanel {

    private Container mainContainer;

    public CreateNursePanel(Container mainContainer) {

        setLayout(new BorderLayout());
        this.mainContainer = mainContainer;

        add(new TitleNursePanel(), BorderLayout.NORTH);
        FormNursePanel formNursePanel = new FormNursePanel();

        add(formNursePanel,BorderLayout.CENTER);
        add(new ButtonNursePanel(formNursePanel), BorderLayout.SOUTH);
    }


    public class TitleNursePanel extends JPanel{
        private JLabel title;
        public TitleNursePanel(){
            setBackground(Color.LIGHT_GRAY);
            setLayout(new FlowLayout());
            title = new JLabel("Création d'un nouveau infirmier : ");
            title.setFont(new Font(Font.DIALOG,Font.BOLD,20));
            add(title);
        }
    }

    public class FormNursePanel extends JPanel {
        private JLabel arrivalDateLabel, lastNameLabel,firstNameLabel, serviceLabel;
        private JTextField arrivalDateTextField,lastNameTextField, firstNameTextField;
        private JComboBox serviceCB;
        private ApplicationController controller;

        public FormNursePanel(){
            setLayout(new GridLayout(4,2,15,70));

            try {
                controller = new ApplicationController();

                //date
                arrivalDateLabel = new JLabel("Date d'arrivée : ");
                arrivalDateLabel.setHorizontalAlignment(4);
                add(arrivalDateLabel);

                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();

                SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
                String dateFormated = fmt.format(date.getTime());
                arrivalDateTextField = new JTextField(dateFormated);
                arrivalDateTextField.setEditable(false);
                add(arrivalDateTextField);

                //LastName
                lastNameLabel = new JLabel("Nom : ");
                lastNameLabel.setHorizontalAlignment(4);
                add(lastNameLabel);

                lastNameTextField = new JTextField();
                add(lastNameTextField);

                //firstName
                firstNameLabel = new JLabel("Prénom : ");
                firstNameLabel.setHorizontalAlignment(4);
                add(firstNameLabel);

                firstNameTextField = new JTextField();
                add(firstNameTextField);

                //service
                serviceLabel = new JLabel("Service: ");
                serviceLabel.setHorizontalAlignment(4);
                add(serviceLabel);

                serviceCB = new JComboBox();
                serviceCB.setMaximumRowCount(3);
                fillServiceCombobox(controller.getAllServices());
                add(serviceCB);
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        public void fillServiceCombobox(ArrayList<String> servicesList) {
            for(String service : servicesList) {
                serviceCB.addItem(service);
            }
        }

        public JTextField getLastNameTextField() {
            return lastNameTextField;
        }

        public JTextField getFirstNameTextField() {
            return firstNameTextField;
        }

        public JComboBox getServiceCB() {
            return serviceCB;
        }
    }

    public class ButtonNursePanel extends JPanel {

        private FormNursePanel formNursePanel;

        public ButtonNursePanel(FormNursePanel formNursePanel) {
            setBackground(Color.LIGHT_GRAY);
            this.formNursePanel = formNursePanel;

            setLayout(new FlowLayout());

            JButton addNurseButton = new JButton("Ajouter l'infirmier");
            addNurseButton.addActionListener(new NurseListener());
            add(addNurseButton);

            add(new BackButton(mainContainer));
        }

        public class NurseListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lastName = formNursePanel.getLastNameTextField().getText();
                String firstName = formNursePanel.getFirstNameTextField().getText();
                //String service = formNursePanel.getServiceCB().getSelectedItem().toString();

                try{
                    ApplicationController controller = new ApplicationController();
                    JOptionPane.showMessageDialog(null, "Essaye de remplire la combobox ce soir comme ça \nje ferai l'ajout en BD de l'infirmier en rentrant", "Erreur(s)", JOptionPane.ERROR_MESSAGE);
                    //FERAI LAJOUT DANS BD CE SOIR SI TU AS REMPLI LA COMBOBOX
                }
                catch(Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur(s)", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }


}