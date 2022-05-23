package View;

import Controller.ApplicationController;
import Model.Patient;
import Model.QueryResultPatient;
import Utils.StringManager;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchPatientPanel extends JPanel {

    private Container mainContainer;
    public SearchPatientPanel(Container mainContainer) {
        this.mainContainer = mainContainer;
        setLayout(new BorderLayout());
        ResultPanel resultPanel = new ResultPanel();
        ResearchPanel researchPanel = new ResearchPanel(resultPanel);
        researchPanel.setPreferredSize(new Dimension(0,100));
        add(researchPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
    }

    public class ResearchPanel extends JPanel {
        private JLabel label;
        private JButton researchButton;
        private JComboBox headsDepartmentCombobox;
        private ApplicationController controller;
        private ResultPanel resultPanel;

        public ResearchPanel(ResultPanel resultPanel) {
            this.resultPanel = resultPanel;
            setLayout(new FlowLayout(FlowLayout.CENTER));
            label = new JLabel("Chefs de service");
            add(label);

            try {
                controller = new ApplicationController();

                // combobox
                headsDepartmentCombobox = new JComboBox();
                headsDepartmentCombobox.setMaximumRowCount(3);
                fillCombobox(controller.getAllHeadsDepartmentName());
                add(headsDepartmentCombobox);
            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

            researchButton = new JButton("Rechercher");
            researchButton.addActionListener(new SearchPatientListener());
            add(researchButton);

            setBackground(Color.LIGHT_GRAY);

        }

        public JComboBox getHeadsDepartmentCombobox() {
            return headsDepartmentCombobox;
        }

        public void fillCombobox(ArrayList<String> headsDepartmentNameList) {
            for(String headDepartment : headsDepartmentNameList) {
                headsDepartmentCombobox.addItem(headDepartment);
            }
        }

        public class SearchPatientListener implements ActionListener{

            public void actionPerformed(ActionEvent e) {
                try{
                    String fullName = getHeadsDepartmentCombobox().getSelectedItem().toString();
                    String id = StringManager.convertFullNameIntoHeadDptID(fullName);
                    ArrayList<QueryResultPatient> result = controller.getPatientByHeadDpt(id);

                    InformationPatientModel informationPatientModel = new InformationPatientModel(result);
                    resultPanel.getTable().setModel(informationPatientModel);
                    resultPanel.getTable().getColumn("Profil du patient").setCellRenderer(new ButtonRenderer());
                    resultPanel.getTable().getColumn("Profil du patient").setCellEditor(new ButtonEditor(new JCheckBox(), result));
                }
                catch(Exception exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }
            }
        }

        public class ButtonRenderer extends JButton implements TableCellRenderer {
            public ButtonRenderer() {
                setOpaque(true);
            }
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setText((value == null) ? "Profil" : value.toString());
                return this;
            }
        }

        public class ButtonEditor extends DefaultCellEditor {
            private ArrayList<QueryResultPatient> result;
            public ButtonEditor(JCheckBox checkbox, ArrayList<QueryResultPatient> result){
                super(checkbox);
                this.result = result;
            }

            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
                JButton button = new JButton("Profil");
                Patient patient = result.get(row).getPatient();
                button.addActionListener(new ChangePanelListener(new PatientPanel(mainContainer, patient), mainContainer));

                return button;
            }
        }
    }

    public class ResultPanel extends JPanel {
        private InformationPatientModel model;
        private JTable table;
        private JScrollPane scrollPane;
        private ApplicationController controller;

        public ResultPanel() {
            setLayout(new BorderLayout());

            ArrayList<QueryResultPatient> defaultList = new ArrayList<>();
            model = new InformationPatientModel(defaultList);
            table = new JTable(model);
            scrollPane = new JScrollPane(table);

            add(table.getTableHeader(), BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);

        }

        public JTable getTable() {
            return table;
        }



    }


}




