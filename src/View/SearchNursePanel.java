package View;

import Controller.ApplicationController;
import Model.Patient;
import Model.QueryResultNurse;
import Model.QueryResultPatient;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class SearchNursePanel extends JPanel {

    private Container mainContainer;
    public SearchNursePanel(Container mainContainer){
        this.mainContainer = mainContainer;
        setLayout(new BorderLayout());
        ResultPanel resultPanel = new ResultPanel();
        ResearchPanel researchPanel = new ResearchPanel(resultPanel);
        researchPanel.setPreferredSize(new Dimension(0,100));

        add(researchPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
    }

    public class ResearchPanel extends JPanel {
        private JLabel labelDate1;
        private JLabel labelDate2;
        private JSpinner date1Spinner;
        private JSpinner date2Spinner;
        private JButton researchButton;
        private ResultPanel resultPanel;

        public ResearchPanel(ResultPanel resultPanel) {
            this.resultPanel = resultPanel;
            setLayout(new FlowLayout(FlowLayout.CENTER));

            labelDate1 = new JLabel("Date 1");
            add(labelDate1);

            date1Spinner = new JSpinner(new SpinnerDateModel());
            JSpinner.DateEditor editorDate1 = new JSpinner.DateEditor(date1Spinner, "dd/MM/yyy");
            date1Spinner.setEditor(editorDate1);
            add(date1Spinner);

            labelDate2 = new JLabel("Date 2");
            add(labelDate2);

            date2Spinner = new JSpinner(new SpinnerDateModel());
            JSpinner.DateEditor editorDate2 = new JSpinner.DateEditor(date2Spinner, "dd/MM/yyyy");
            date2Spinner.setEditor(editorDate2);
            add(date2Spinner);

            researchButton = new JButton("Rechercher");
            researchButton.addActionListener(new AddResearchNurseListener());

            setBackground(Color.LIGHT_GRAY);
            add(researchButton);
        }

        public class AddResearchNurseListener implements ActionListener{

            public void actionPerformed(ActionEvent e) {
                try{
                    ApplicationController controller = new ApplicationController();
                    Date date1 = (Date)date1Spinner.getValue();
                    Date date2 = (Date)date2Spinner.getValue();

                    GregorianCalendar calendar1 = new GregorianCalendar();
                    calendar1.setTime(date1);
                    GregorianCalendar calendar2 = new GregorianCalendar();
                    calendar2.setTime(date2);

                    ArrayList<QueryResultNurse> result = controller.getNurseByDate(calendar1, calendar2);

                    InformationNurseModel informationNurseModel= new InformationNurseModel(result);
                    resultPanel.getTable().setModel(informationNurseModel);
                }
                catch(Exception exception){
                    JOptionPane.showMessageDialog(null,exception.getMessage());
                }
            }
        }
    }


    public class ResultPanel extends JPanel {
        private JTable table;
        public ResultPanel() {
            setLayout(new BorderLayout());
            ArrayList<QueryResultNurse> defaultList = new ArrayList<>();
            InformationNurseModel model = new InformationNurseModel(defaultList);
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);

            add(table.getTableHeader(), BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);
        }
        public JTable getTable() {
            return table;
        }
    }
}
