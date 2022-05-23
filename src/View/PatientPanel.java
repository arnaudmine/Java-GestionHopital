package View;


import Business.PatientManager;
import Controller.ApplicationController;
import Model.*;
import Utils.Verification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PatientPanel extends JPanel{
    private Container mainContainer;
    private Patient patient;


    public PatientPanel(Container mainContainer, Patient patient){
        this.patient = patient;
        this.mainContainer = mainContainer;

        setLayout(new BorderLayout());
        add(new titlePatientPanel(patient), BorderLayout.NORTH);
        InformationPatientPanel informationPatientPanel = new InformationPatientPanel(patient);
        add(informationPatientPanel, BorderLayout.CENTER);
        add(new ButtonPatientPanel(mainContainer, informationPatientPanel), BorderLayout.SOUTH);
    }


    public class InformationPatientPanel extends JPanel {
        private Patient patient;
        private JLabel arrivalDateLabel, adressLabel, diseaseLabel, billLabel, nurseLabel, bedroomLabel, canLeaveLabel, emailLabel, insuranceLabel, serviceLabel, consultationLabel;
        private JTextField arrivalDateTextField, adressTextField, diseaseTextField, billTextField, serviceTextField, emailTextField, insuranceTextField;
        private JComboBox nurseCB, bedroomCB;
        private JButton addConsultationButton;
        private RadioButtonPatientPanel radioButtonPatientPanel;
        private ApplicationController controller;


        public InformationPatientPanel(Patient patient){
            setLayout(new GridLayout(11,2,15,20));
            this.patient = patient;

            try {
                controller = new ApplicationController();

                //date
                arrivalDateLabel = new JLabel("Date d'arrivée : ");
                arrivalDateLabel.setHorizontalAlignment(4);
                add(arrivalDateLabel);

                SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
                String dateFormated = fmt.format(patient.getArrivalDate().getTime());
                arrivalDateTextField = new JTextField(dateFormated);
                arrivalDateTextField.setEditable(false);
                add(arrivalDateTextField);

                //address
                adressLabel = new JLabel("Adresse du patient : ");
                adressLabel.setHorizontalAlignment(4);
                add(adressLabel);

                adressTextField = new JTextField(patient.getAddress());

                add(adressTextField);

                //disease(s)
                diseaseLabel = new JLabel("Maladie(s) du patient : ");
                diseaseLabel.setHorizontalAlignment(4);
                add(diseaseLabel);

                diseaseTextField = new JTextField(patient.getDisease());
                diseaseTextField.setEditable(false);
                add(diseaseTextField);

                //bill
                billLabel = new JLabel("Facture actuelle du patient : ");
                billLabel.setHorizontalAlignment(4);
                add(billLabel);

                billTextField = new JTextField(String.valueOf(patient.getBill()));
                billTextField.setEditable(false);
                add(billTextField);

                //service
                serviceLabel = new JLabel("Service : ");
                serviceLabel.setHorizontalAlignment(4);
                add(serviceLabel);

                serviceTextField = new JTextField(controller.getServiceByNurse(getNurseId()));
                serviceTextField.setEditable(false);
                add(serviceTextField);

                //Nurse
                nurseLabel = new JLabel("Infirmier(e) : ");
                nurseLabel.setHorizontalAlignment(4);
                add(nurseLabel);

                nurseCB = new JComboBox();
                nurseCB.setMaximumRowCount(3);
                add(nurseCB);

                //bedroom
                bedroomLabel = new JLabel("Numéro de la Chambre : ");
                bedroomLabel.setHorizontalAlignment(4);
                add(bedroomLabel);

                bedroomCB = new JComboBox();
                bedroomCB.setMaximumRowCount(3);
                add(bedroomCB);

                //canLeave
                canLeaveLabel = new JLabel("Peut quitter l'hôpital");
                canLeaveLabel.setHorizontalAlignment(4);
                add(canLeaveLabel);

                radioButtonPatientPanel = new RadioButtonPatientPanel(patient);
                add(radioButtonPatientPanel);

                //email
                emailLabel = new JLabel("E-mail du patient : ");
                emailLabel.setHorizontalAlignment(4);
                add(emailLabel);
                emailTextField = new JTextField(patient.getEmail());
                add(emailTextField);

                //InsuranceCompany
                insuranceLabel = new JLabel("Assurance médicale du patient : ");
                insuranceLabel.setHorizontalAlignment(4);
                add(insuranceLabel);

                insuranceTextField = new JTextField(patient.getInsuranceCompany());
                add(insuranceTextField);

                //consultation
                consultationLabel = new JLabel("Consultation : ");
                consultationLabel.setHorizontalAlignment(4);
                add(consultationLabel);

                addConsultationButton = new JButton("Ajouter une consultation");
                addConsultationButton.addActionListener(new ChangePanelListener(new AddConsultationPanel(mainContainer, patient),mainContainer));
                add(addConsultationButton);

                fillComboboxes();
            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        public void fillComboboxes() {
            try {
                String resultService = getServiceTextField().getText();
                ArrayList<QueryResultNurse> resultNurses = controller.getNurseByService(resultService);
                ArrayList<QueryResultBedroom> resultBedroom = controller.getBedroomByService(resultService);
                fillNurseCombobox(resultNurses);
                nurseCB.setSelectedItem(controller.getNurseById(getNurseId()));
                fillBedroomCombobox(resultBedroom);
                bedroomCB.setSelectedItem(patient.getBedroom());
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null,exception.getMessage());
            }
        }
        public RadioButtonPatientPanel getRadioButtonPatientPanel() {
            return radioButtonPatientPanel;
        }

        public String getNurseId() {
            return patient.getNurse();
        }


        public JTextField getServiceTextField() {
            return serviceTextField;
        }

        public void fillNurseCombobox(ArrayList<QueryResultNurse> nursesList) {
            nurseCB.removeAllItems();
            for(QueryResultNurse nurse : nursesList) {
                nurseCB.addItem(nurse.getNurseFirstName() + " " + nurse.getNurseLastName() + " ("+getServiceTextField().getText()+")");
            }
        }

        public void fillBedroomCombobox(ArrayList<QueryResultBedroom> bedroomsList) {
            bedroomCB.removeAllItems();
            for(QueryResultBedroom bedroom : bedroomsList) {
                bedroomCB.addItem(bedroom.getBedroomNumber());
            }
        }

        public JTextField getAddressTextField() {
            return adressTextField;
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

    public class titlePatientPanel extends JPanel{

        private Patient patient;
        private JLabel title;

        public titlePatientPanel(Patient patient){
            this.patient = patient;
            setBackground(Color.LIGHT_GRAY);
            setLayout(new FlowLayout());

            title = new JLabel("Patient : " + patient.getLastName() + " " + patient.getFirstName() + " (ID : " + patient.getId() +")");
            title.setFont(new Font(Font.DIALOG,Font.BOLD,20));
            add(title);
        }
    }

    public class RadioButtonPatientPanel extends JPanel {
        private JRadioButton canLeave, cannotLeave;
        private ButtonGroup buttonGroup;

        public RadioButtonPatientPanel(Patient patient){
            setLayout(new FlowLayout(FlowLayout.LEFT));
            canLeave = new JRadioButton("Oui", patient.getCanLeave());
            cannotLeave = new JRadioButton("Non", !patient.getCanLeave());
            add(canLeave);
            add(cannotLeave);
            buttonGroup = new ButtonGroup();
            buttonGroup.add(canLeave);
            buttonGroup.add(cannotLeave);
        }

        public JRadioButton getCanLeave() {
            return canLeave;
        }
    }

    public class ButtonPatientPanel extends JPanel {

        private JButton saveChanges, deleteButton;
        private Container mainContainer;
        private InformationPatientPanel patientForm;

        public ButtonPatientPanel(Container mainContainer, InformationPatientPanel patientForm) {
            this.patientForm = patientForm;
            setBackground(Color.LIGHT_GRAY);
            this.mainContainer = mainContainer;

            setLayout(new FlowLayout());
            saveChanges = new JButton("Sauvegarder");
            saveChanges.addActionListener(new UpdatePatientListener());
            add(saveChanges);

            deleteButton = new JButton("Supprimer le patient");
            deleteButton.addActionListener(new DeletePatientListener());
            add(deleteButton);
            add(new BackButton(mainContainer));
        }

        public class DeletePatientListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                try {
                    ApplicationController controller = new ApplicationController();
                    if (patient.getCanLeave()) {
                        controller.deletePatient(patient.getId());

                        PatientManager patientManager = new PatientManager();
                        Double finalPrice = patientManager.getFinalBill(patient);
                        JOptionPane.showMessageDialog(null,
                                "Le patient :\n" + patient.getLastName() + " " + patient.getFirstName() +
                                "\nÀ été supprimé avec succès! " +
                                "\nSa facture finale est de : " + finalPrice + " euros",
                                "Patient supprimé", JOptionPane.INFORMATION_MESSAGE);

                        //retour à la page de recherche apres suppression
                        mainContainer.removeAll();
                        mainContainer.add(new SearchPatientPanel(mainContainer));
                        mainContainer.revalidate();
                        mainContainer.repaint();

                    } else {
                        JOptionPane.showMessageDialog(null, "Le patient n'est pas autorisé à partir !");
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        }

        public class UpdatePatientListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                String address = patientForm.getAddressTextField().getText();
                String service = patientForm.getServiceTextField().getText();
                String nurseId = Utils.StringManager.convertFullNameIntoNurseId(patientForm.getNurseCB().getSelectedItem().toString());
                String bedroom = patientForm.getBedroomCB().getSelectedItem().toString();
                Boolean canLeave = patientForm.getRadioButtonPatientPanel().getCanLeave().isSelected();
                String insurance = "";
                String email = "";

                if (!patientForm.getEmailTextField().getText().equals("")) {
                    email = patientForm.getEmailTextField().getText();
                }
                if (!patientForm.getInsuranceTextField().getText().equals("")) {
                    insurance = patientForm.getInsuranceTextField().getText();
                }

                try {
                    ApplicationController controller = new ApplicationController();
                    UpdatePatientModel updatePatientModel = Verification.createUpdatePatient(patient.getId(), address, service, nurseId, bedroom, canLeave, insurance, email);
                    controller.updatePatient(updatePatientModel);

                    patient.setAddress(address);
                    patient.setCanLeave(canLeave);
                    JOptionPane.showMessageDialog(null, "Patient modifié avec succès !", "Modification(s) enregistrée(s)", JOptionPane.INFORMATION_MESSAGE);


                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur(s)", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}