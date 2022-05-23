package View;

import Model.HeadDepartment;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllHeadsDepartmentModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<HeadDepartment> contents;

    public AllHeadsDepartmentModel(ArrayList<HeadDepartment> headsDepartment) {
        columnNames = new ArrayList<>();
        columnNames.add("Id");
        columnNames.add("Last name");
        columnNames.add("First name");
        contents = headsDepartment;
    }

    public int getColumnCount() {
        return columnNames.size();
    }

    public int getRowCount() {
        return contents.size();
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public Object getValueAt(int row, int column) {
        HeadDepartment headDepartment = contents.get(row);
        switch(column) {
            case 0 : return headDepartment.getId();
            case 1 : return headDepartment.getLastName();
            case 2 : return headDepartment.getFirstName();
            default : return null;
        }
    }

    public Class getColumnClass(int column) {
        Class c;
        switch(column) {
            case 0 : c = String.class;
            case 1 : c = String.class;
            case 2 : c = String.class;
            default : c = String.class;
        }

        return c;
    }
}
