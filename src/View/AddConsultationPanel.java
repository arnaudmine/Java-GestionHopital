package View;

import Controller.ApplicationController;
import Model.Consultation;
import Model.Patient;
import Utils.Verification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;


public class AddConsultationPanel extends JPanel {

    private Container mainContainer;
    private Patient patient;

    public AddConsultationPanel(Container mainContainer, Patient patient) {
        setLayout(new BorderLayout());
        this.patient = patient;
        this.mainContainer = mainContainer;
        add(new TitlePanel(), BorderLayout.NORTH);

        FormConsultationPanel formPanel = new FormConsultationPanel();
        add(formPanel, BorderLayout.CENTER);
        add(new ButtonConsultationPanel(formPanel), BorderLayout.SOUTH);
    }

    public class TitlePanel extends JPanel{

        private JLabel title;

        public TitlePanel() {
            setBackground(Color.LIGHT_GRAY);
            setLayout(new FlowLayout());
            title = new JLabel("Ajouter une consultation à  " + patient.getLastName() + " " + patient.getFirstName());
            title.setFont(new Font(Font.DIALOG,Font.BOLD,20));
            add(title);
        }
    }
    public class FormConsultationPanel extends JPanel{

        private JLabel patientLabel, dateLabel, typeLabel, expertLabel, priceLabel, noteLabel;

        private JTextField patientField, priceField, typeField;

        private JTextArea noteArea;

        private JSpinner dateSpinner;

        private JComboBox expertCB;

        private ApplicationController controller;


        public FormConsultationPanel() {
            try {
                setLayout(new GridLayout(6, 2, 15, 40));
                this.controller = new ApplicationController();

                //patient
                patientLabel = new JLabel("Patient : ");
                patientLabel.setHorizontalAlignment(4);
                add(patientLabel);

                patientField = new JTextField(patient.getLastName() + " " + patient.getFirstName());
                patientField.setEditable(false);
                add(patientField);

                //date
                dateLabel = new JLabel("Date : ");
                dateLabel.setHorizontalAlignment(4);
                add(dateLabel);

                dateSpinner = new JSpinner(new SpinnerDateModel());
                JSpinner.DateEditor editorDate2 = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
                dateSpinner.setEditor(editorDate2);
                add(dateSpinner);

                //type
                typeLabel = new JLabel("Type de consultation");
                typeLabel.setHorizontalAlignment(4);
                add(typeLabel);

                typeField = new JTextField();
                //fillTypeCombobox;
                add(typeField);

                //expert
                expertLabel = new JLabel("Expert : ");
                expertLabel.setHorizontalAlignment(4);
                add(expertLabel);

                expertCB = new JComboBox();
                fillExpertCB(controller.getAllExperts());
                add(expertCB);

                //price
                priceLabel = new JLabel("Prix");
                priceLabel.setHorizontalAlignment(4);
                add(priceLabel);

                priceField = new JTextField();
                add(priceField);

                //note
                noteLabel = new JLabel("Note : ");
                noteLabel.setHorizontalAlignment(4);
                add(noteLabel);

                noteArea = new JTextArea();
                add(noteArea);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        public void fillExpertCB(ArrayList<String> expertList) {
            for(String expert : expertList) {
                expertCB.addItem(expert);
            }
        }
        public JSpinner getDateSpinner() {
            return dateSpinner;
        }
        public JTextField getTypeTextField() {
            return typeField;
        }

        public JComboBox getExpertCB() {
            return expertCB;
        }

        public JTextField getPriceField() {
            return priceField;
        }

        public JTextArea getNoteArea() {
            return noteArea;
        }
    }


    public class ButtonConsultationPanel extends JPanel{

        private FormConsultationPanel formConsultationPanel;
        private JButton saveButton;

        private JButton backButton;

        public ButtonConsultationPanel(FormConsultationPanel formConsultationPanel){
            this.formConsultationPanel = formConsultationPanel;

            setBackground(Color.lightGray);
            setLayout(new FlowLayout());

            saveButton = new JButton("Ajouter la consultation");
            saveButton.addActionListener(new ConsultationListener());
            backButton = new JButton("Retour au profil");
            backButton.addActionListener(new BackButtonListener());

            add(saveButton);
            add(backButton);
            add(new BackButton(mainContainer));
        }

        public class ConsultationListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String patientFullName = formConsultationPanel.patientField.getText();
                String type = formConsultationPanel.getTypeTextField().getText();
                String expert = formConsultationPanel.getExpertCB().getSelectedItem().toString();
                String price = formConsultationPanel.getPriceField().getText();
                Date date = (Date) formConsultationPanel.getDateSpinner().getValue();
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(date);

                String idPatient = Utils.StringManager.convertFullNameIntoPatientId(patientFullName);
                String idExpert = Utils.StringManager.convertFullNameIntoExpertId(expert);
                String note = "";
                if(!formConsultationPanel.getNoteArea().getText().equals("")) {
                    note = formConsultationPanel.getNoteArea().getText();
                }
                try {
                    ApplicationController controller = new ApplicationController();

                    Consultation consultation = Verification.createConsultation(type,calendar,price,note,idPatient,idExpert);
                    controller.addConsultation(consultation);

                    JOptionPane.showMessageDialog(null,"Consultation ajoutée avec succès !","Consultation ajoutée", JOptionPane.INFORMATION_MESSAGE);
                    patient.setBill(patient.getBill()+consultation.getPrice());

                    //retour à la page du patient après ajout
                    mainContainer.removeAll();
                    mainContainer.add(new PatientPanel(mainContainer, patient));
                    mainContainer.revalidate();
                    mainContainer.repaint();

                }
                catch(Exception exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }
            }
        }

        public class BackButtonListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                mainContainer.removeAll();
                mainContainer.add(new PatientPanel(mainContainer, patient));
                mainContainer.revalidate();
                mainContainer.repaint();
            }
        }



    }
}