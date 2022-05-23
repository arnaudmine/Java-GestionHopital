package View;

import Model.QueryResultConsultation;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class InformationConsultationModel extends AbstractTableModel {

    private ArrayList<String> columnNames;

    private ArrayList<QueryResultConsultation> content;

    public InformationConsultationModel(ArrayList<QueryResultConsultation> content) {

        columnNames = new ArrayList<>();
        columnNames.add("Date");
        columnNames.add("Prix");
        columnNames.add("Nom du patient");
        columnNames.add("Prénom du patient");
        columnNames.add("Nom de l'expert");
        columnNames.add("Spécialisation");
        this.content = content;
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public int getRowCount() {
        return content.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        QueryResultConsultation result = content.get(rowIndex);
        switch(columnIndex){
            case 0 :
                return result.getDateConsultation();
            case 1 :
                return result.getPriceConsultation();
            case 2 :
                return result.getPatientLastName();
            case 3 :
                return result.getPatientFirstName();
            case 4 :
                return result.getExpertLastName();
            case 5 :
                return result.getExpertSpecialisation();
            default: return null;
        }
    }

    public Class getColumnClass(int column){
        Class c;
        switch (column){
            case 0 :
                c = GregorianCalendar.class;
                break;
            case 1 :
                c = Double.class;
                break;
            default :
                c = String.class;
                break;
        }
        return c;
    }
}
