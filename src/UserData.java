import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UserData extends AbstractTableModel
{
    List<User> uR = new ArrayList<User>();

    @Override
    public int getRowCount()
    {
        return uR.size();
    }
    @Override
    public int getColumnCount()
    {
        return 5;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        User u = uR.get(rowIndex);
        switch (columnIndex) 
        {
            case 0:
                return u.getX();
            case 1:
                return u.getY();
            case 2:
                return u.getM();
            case 3: 
                return u.getT();
            default:
                return u.didNyert();
        }
    }
    @Override
    public String getColumnName(int column)
    {
        switch (column) {
            case 0:
                return "X";
            case 1:
                return "Y";
            case 2:
                return "Aknák";
            case 3:
                return "Idő";
            default:
                return "Nyert?";
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        switch (columnIndex) 
        {
            case 0:
            case 1:
            case 2:
            case 3:
                return Integer.class;
            default:
                return Boolean.class;
        }
    }
    public void addRecord(int x,int y,int m,int t,boolean n)
    {
        uR.add(new User(x,y,m,t,n));
    }
}
