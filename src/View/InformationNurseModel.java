package View;

import Model.QueryResultNurse;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class InformationNurseModel extends AbstractTableModel {

    private ArrayList<String> columnNames;

    private ArrayList<QueryResultNurse> content;

    public InformationNurseModel(ArrayList<QueryResultNurse> content){

        columnNames = new ArrayList<>();
        columnNames.add("Infirmier");
        columnNames.add("Service");
        columnNames.add("Chef du Service");
        this.content = content;
    }

    public String getColumnName(int column){return columnNames.get(column);}

    public int getRowCount() {
        return content.size();
    }

    public int getColumnCount() {
        return columnNames.size();
    }


    public Object getValueAt(int rowIndex, int columnIndex) {
        QueryResultNurse result = content.get(rowIndex);
        switch(columnIndex){
            case 0:
                return result.getNurseLastName() + " " +result.getNurseFirstName() +" (ID : " + result.getId()+")";
            case 1:
                return result.getService();
            case 2:
                return result.getSupervisor();
            default: return null;
        }
    }

    public Class getColumnClass(int column){
        return getValueAt(0,column).getClass();
    }
}