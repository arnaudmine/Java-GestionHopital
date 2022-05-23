package View;

import Controller.ApplicationController;
import Model.Patient;
import Model.QueryResultBedroom;
import Model.QueryResultNurse;
import Utils.Verification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreatePatientPanel extends JPanel {

    private Container mainContainer;

    public CreatePatientPanel(Container mainContainer){

        this.mainContainer = mainContainer;

        setLayout(new BorderLayout());
        add(new TitlePatientPanel(), BorderLayout.NORTH);
        FormPatientPanel formPatientPanel = new FormPatientPanel();
        add(formPatientPanel, BorderLayout.CENTER);
        add(new ButtonPatientPanel(formPatientPanel), BorderLayout.SOUTH);

    }

    public class TitlePatientPanel extends JPanel{
        private JLabel title;

        public TitlePatientPanel(){
            setBackground(Color.LIGHT_GRAY);
            setLayout(new FlowLayout());

            title = new JLabel("Création d'un nouveau patient : ");
            title.setFont(new Font(Font.DIALOG,Font.BOLD,20));
            add(title);
        }
    }
    public class FormPatientPanel extends JPanel {
        private JLabel arrivalDateLabel, adressLabel,lastNameLabel,firstNameLabel, diseaseLabel, billLabel, nurseLabel, bedroomLabel, emailLabel, insuranceLabel, serviceLabel;
        private JTextField arrivalDateTextField, adressTextField, lastNameTextField, firstNameTextField, diseaseTextField, billTextField, emailTextField, insuranceTextField;
        private JComboBox nurseCB, bedroomCB, serviceCB;
        private ApplicationController controller;

        public FormPatientPanel() {
            setLayout(new GridLayout(10, 2, 15, 30));

            try {
                controller = new ApplicationController();

                //adress
                adressLabel = new JLabel("Adresse du patient : ");
                adressLabel.setHorizontalAlignment(4);
                add(adressLabel);
                adressTextField = new JTextField();
                add(adressTextField);

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
                lastNameLabel = new JLabel("Nom du patient : ");
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

                //disease(s)
                diseaseLabel = new JLabel("Maladie(s) du patient : ");
                diseaseLabel.setHorizontalAlignment(4);
                add(diseaseLabel);

                diseaseTextField = new JTextField();
                add(diseaseTextField);

                //service
                serviceLabel = new JLabel("Service : ");
                serviceLabel.setHorizontalAlignment(4);
                add(serviceLabel);

                serviceCB = new JComboBox();
                serviceCB.setMaximumRowCount(3);
                fillServiceCombobox(controller.getAllServices());
                serviceCB.addActionListener(new SearchNurseListener());
                add(serviceCB);

                //Nurse
                nurseLabel = new JLabel("Infirmier(e) : ");
                nurseLabel.setHorizontalAlignment(4);
                add(nurseLabel);

                nurseCB = new JComboBox();
                nurseCB.setMaximumRowCount(5);
                add(nurseCB);

                //bedroom
                bedroomLabel = new JLabel("Numéro de la Chambre : ");
                bedroomLabel.setHorizontalAlignment(4);
                add(bedroomLabel);

                bedroomCB = new JComboBox();
                bedroomCB.setMaximumRowCount(3);
                add(bedroomCB);

                //email
                emailLabel = new JLabel("E-mail du patient : ");
                emailLabel.setHorizontalAlignment(4);
                add(emailLabel);
                emailTextField = new JTextField();
                add(emailTextField);

                //InsuranceCompany
                insuranceLabel = new JLabel("Assurance médicale du patient : ");
                insuranceLabel.setHorizontalAlignment(4);
                add(insuranceLabel);

                insuranceTextField = new JTextField();
                add(insuranceTextField);
                fillComboboxes();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        public class SearchNurseListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                fillComboboxes();
            }
        }

        public void fillComboboxes(){
            try {
                String resultService = getServiceCB().getSelectedItem().toString();
                ArrayList<QueryResultNurse> resultNurses = controller.getNurseByService(resultService);
                ArrayList<QueryResultBedroom> resultBedroom = controller.getBedroomByService(resultService);
                fillNurseCombobox(resultNurses);
                fillBedroomCombobox(resultBedroom);
            }catch(Exception exception){
                JOptionPane.showMessageDialog(null,exception.getMessage());
            }
        }

        public JComboBox getServiceCB() {
            return serviceCB;
        }

        public void fillNurseCombobox(ArrayList<QueryResultNurse> nursesList) {
            nurseCB.removeAllItems();
            for(QueryResultNurse nurse : nursesList) {
                nurseCB.addItem(nurse.getNurseFirstName() + " " + nurse.getNurseLastName() + " ("+getServiceCB().getSelectedItem().toString()+")");
            }
        }

        public void fillBedroomCombobox(ArrayList<QueryResultBedroom> bedroomsList) {
            bedroomCB.removeAllItems();
            for(QueryResultBedroom bedroom : bedroomsList) {
                bedroomCB.addItem(bedroom.getBedroomNumber());
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

        public JTextField getAdressTextField() {
            return adressTextField;
        }

        public JTextField getDiseaseTextField() {
            return diseaseTextField;
        }

        public JComboBox getNurseCB() {
            return nurseCB;
        }

        public JComboBox getBedroomCB() {
            return bedroomCB;
        }

        public JTextField getEmailTextField() {
            return emailTextField;
        }

        public JTextField getInsuranceTextField() {
            return insuranceTextField;
        }
    }
    public class ButtonPatientPanel extends JPanel {

        private FormPatientPanel formPatientPanel;

        public ButtonPatientPanel(FormPatientPanel formPatientPanel){
            this.formPatientPanel = formPatientPanel;
            setBackground(Color.lightGray);
            setLayout(new FlowLayout());
            JButton addPatientButton = new JButton("Ajouter le patient");
            addPatientButton.addActionListener(new AddPatientListener());
            add(addPatientButton);

            JButton resetButton = new JButton("Réinitialiser");
            resetButton.addActionListener(new ResetFormListener());
            add(resetButton);
            add(new BackButton(mainContainer));
        }

        public class ResetFormListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                mainContainer.removeAll();
                mainContainer.add(new CreatePatientPanel(mainContainer));
                mainContainer.revalidate();
                mainContainer.repaint();
            }
        }
        public class AddPatientListener implements ActionListener{

            public void actionPerformed(ActionEvent e) {

                String lastName = formPatientPanel.getLastNameTextField().getText();
                String firstName = formPatientPanel.getFirstNameTextField().getText();

                String address = formPatientPanel.getAdressTextField().getText();
                String disease = formPatientPanel.getDiseaseTextField().getText();
                String bedroom = formPatientPanel.getBedroomCB().getSelectedItem().toString();
                String insurance = "";
                String email = "";

                if(!formPatientPanel.getEmailTextField().getText().equals("")) {
                    email = formPatientPanel.getEmailTextField().getText();
                }
                if(!formPatientPanel.getInsuranceTextField().getText().equals("")){
                    insurance = formPatientPanel.getInsuranceTextField().getText();
                }

                String nurseInfo = formPatientPanel.getNurseCB().getSelectedItem().toString();
                try {
                    ApplicationController controller = new ApplicationController();
                    Patient patient = Verification.createPatient(lastName, firstName, address,disease, bedroom, email, insurance, nurseInfo);
                    controller.addPatient(patient);

                    JOptionPane.showMessageDialog(null,"Patient créé avec succès !","Patient ajoutée", JOptionPane.INFORMATION_MESSAGE);


                    //retour à la page du patient apres ajout
                    mainContainer.removeAll();
                    mainContainer.add(new SearchPatientPanel(mainContainer));
                    mainContainer.revalidate();
                    mainContainer.repaint();
                }
                catch(Exception exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur(s)", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}