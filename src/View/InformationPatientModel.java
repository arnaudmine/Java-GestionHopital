package View;

import Model.QueryResultPatient;
import Utils.DateFormator;


import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class InformationPatientModel extends AbstractTableModel {

    private ArrayList<String> columnNames;

    private ArrayList<QueryResultPatient> content;

    public InformationPatientModel(ArrayList<QueryResultPatient> content) {

        columnNames = new ArrayList<>();
        columnNames.add("Numéro de chambre");
        columnNames.add("Nom du patient");
        columnNames.add("Prénom du patient");
        columnNames.add("Date d'arrivée du patient");
        columnNames.add("Nom de l'infirmier");
        columnNames.add("Prénom de l'infirmier");
        columnNames.add("Profil du patient");
        this.content = content;
    }

    public String getColumnName(int column){return columnNames.get(column);}

    @Override
    public int getRowCount() {
        return content.size();
    }

    public boolean isCellEditable(int row, int column){
        if(column == 6)
            return true;
        return false;
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        QueryResultPatient result = content.get(rowIndex);
        switch(columnIndex){
            case 0 :
                return result.getPatient().getBedroom();
            case 1 :
                return result.getPatient().getLastName();
            case 2 :
                return result.getPatient().getFirstName();
            case 3 :
                return DateFormator.fromCalendarToString(result.getPatient().getArrivalDate());
            case 4 :
                return result.getNurseLastName();
            case 5 :
                return result.getNurseFirstName();
            default: return null;
        }
    }

    public Class getColumnClass(int column){
        return getValueAt(0,column).getClass();
    }
}
