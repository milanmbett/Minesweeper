import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class LeaderBoardFrame extends JFrame
{
    public UserData data;
    public static boolean exists(String user)
    {
        File f = new File("_LeaderBoards/"+user+".dat");
        if(f.exists())
        {
            return true;
        }
        return false;
    }
    private void init()
    {
        this.setLayout(new BorderLayout());

        JTable table = new JTable(data);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane,BorderLayout.CENTER);

        table.setRowSorter(new TableRowSorter<>(table.getModel()));

    }
    @SuppressWarnings("unchecked")
    public LeaderBoardFrame(String userName)
    {
        super("Felhasználó rekordjai");
        try
        {
            data = new UserData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("_LeaderBoards/"+userName+".dat"));
            data.uR = (List<User>)ois.readObject();
            ois.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                try
                {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("_LeaderBoards/"+userName+".dat"));
                    oos.writeObject(data.uR);
                    oos.close();
                }catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        setMinimumSize(new Dimension(525,250));
        init();
    }   
}
