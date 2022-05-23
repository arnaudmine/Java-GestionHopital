package View;

import Controller.ApplicationController;
import Model.QueryResultConsultation;
import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchConsultationPanel extends JPanel {
    public SearchConsultationPanel(){
        setLayout(new BorderLayout());
        ResultPanel resultPanel = new ResultPanel();
        ResearchPanel researchPanel = new ResearchPanel(resultPanel);

        researchPanel.setPreferredSize(new Dimension(0,100));
        add(researchPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
    }

    public class ResearchPanel extends JPanel{

        private JLabel typeLabel;
        private JComboBox consultationsCombobox;
        private ApplicationController controller;
        private JButton searchButton;
        private ResultPanel resultPanel;
        public ResearchPanel(ResultPanel resultPanel){
            this.resultPanel = resultPanel;

            //label
            setLayout(new FlowLayout(FlowLayout.CENTER));
            typeLabel = new JLabel("Type de consultation : ");
            add(typeLabel);

            try {
                controller = new ApplicationController();

                // combobox
                consultationsCombobox = new JComboBox();
                consultationsCombobox.setMaximumRowCount(3);
                fillCombobox(controller.getAllConsultationType());
                add(consultationsCombobox);
            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

            //button recherche
            searchButton = new JButton("Rechercher");
            searchButton.addActionListener(new AddSearchConsultationListener());
            add(searchButton);


            setBackground(Color.LIGHT_GRAY);
        }
        public JComboBox getConsultationsCombobox() {
            return consultationsCombobox;
        }
        public void fillCombobox(ArrayList<String> consultationsList) {
            for(String consultation : consultationsList) {
                consultationsCombobox.addItem(consultation);
            }
        }
        public class AddSearchConsultationListener implements ActionListener{

            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<QueryResultConsultation> result = controller.getConsultationByType(getConsultationsCombobox().getSelectedItem().toString());
                    InformationConsultationModel informationConsultationModel = new InformationConsultationModel(result);
                    resultPanel.getTable().setModel(informationConsultationModel);
                }
                catch(Exception exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }
            }
        }
    }
    public class ResultPanel extends JPanel {
        private InformationConsultationModel model;
        private JTable table;
        private JScrollPane scrollPane;
        private ApplicationController controller;

        public ResultPanel() {
            setLayout(new BorderLayout());

            try {
                controller = new ApplicationController();

                ArrayList<QueryResultConsultation> defaultList = controller.getConsultationByType("radio du coeur");
                model = new InformationConsultationModel(defaultList);
                table = new JTable(model);
                scrollPane = new JScrollPane(table);

                add(table.getTableHeader(), BorderLayout.NORTH);
                add(scrollPane, BorderLayout.CENTER);
            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        public JTable getTable() {
            return table;
        }
    }
}