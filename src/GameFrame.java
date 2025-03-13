import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class GameFrame 
{
    private static int sizex;
    private static int sizey;
    private static int mines;
    public static JButton[][] buttons;
    private static int timeElapsed; 
    private static JFrame gameFrame;
    private static int numberOfGoodFlags = 0;
    public static boolean lost = false;
    public static boolean isFirstClick = true;
    public static Table t = new Table();
    private static String user;

    public static void startGame(int sx, int sy, int ms, String u) 
    {
        isFirstClick = true;
        numberOfGoodFlags = 0;
        sizex = sx;
        sizey = sy;
        mines = ms;
        timeElapsed = 0;
        lost = false;
        user = u;
        gameFrame = new JFrame("Aknakereső");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        JLabel timerLabel = new JLabel("Idő: 0");
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(timerLabel, BorderLayout.CENTER);
        Timer timer = new Timer(1000, e ->
        {
            timeElapsed++;
            timerLabel.setText("Idő: " + timeElapsed);
        });
        timer.start();
        gameFrame.add(topPanel, BorderLayout.NORTH);

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(sizex, sizey));
    
        buttons = new JButton[sizex][sizey];
    
        for (int i = 0; i < sizex; ++i) {
            for (int j = 0; j < sizey; ++j) {
                JButton b = new JButton("");
                b.setPreferredSize(new Dimension(120, 120));
                b.setHorizontalAlignment(JButton.CENTER);
                b.setVerticalAlignment(JButton.CENTER);
                b.setFocusPainted(false);
                b.setBackground(new Color(247, 246, 245));
    
                b.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) 
                    {
                        onClick(e);
                    }
                });
    
                gamePanel.add(b);
                buttons[i][j] = b;
            }
        }
        JScrollPane scrollPane = new JScrollPane(gamePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
        gameFrame.add(scrollPane);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
    public static void onClick(MouseEvent e)
    {
        JButton clickedButton = (JButton) e.getSource();
        int x = -1;
        int y = -1;
        for (int i = 0; i < sizex; i++) 
        {
            for (int j = 0; j < sizey; j++) 
            {
                if (buttons[i][j] == clickedButton) 
                {
                    x = i;
                    y = j;
                    
                    break;
                }
            }    
        }
        if(e.getButton() == e.BUTTON1)
        {
            if(isFirstClick)
            {
                Table.table = new Field[sizex][sizey];
                for(int i=0;i<sizex;++i)
                {
                    for(int j =0;j<sizey;++j)
                    {
                        Table.table[i][j] = new Field();
                    }
                }
                Table.table[x][y].onLeftClick();
                t = new Table(sizex, sizey, mines);
                t.createTable();
                isFirstClick = false;
                Table.table[x][y].reveal();
            }
            Table.table[x][y].onLeftClick();
        }
        else if(e.getButton() == e.BUTTON3)
        {
            Table.table[x][y].onRightClick();
        }
        if(numberOfGoodFlags == mines &&  mines!=0 && lost==false)
        {
            won();
        }
    }
    
    public static void lost() 
    {
        lost = true;
        save();
        for (int i = 0; i < sizex; ++i) 
        {
            for (int j = 0; j < sizey; ++j) 
            {
                if (!Table.table[i][j].isVisible && Table.table[i][j].hasFlag != 1) 
                {
                    Table.table[i][j].reveal();
                }
            }
        }
        JFrame lostFrame = new JFrame("Vesztettél");
        JPanel panel = new JPanel();
        JButton returnToMainMenuButton = new JButton("Vissza a főmenübe");
        JButton exitButton = new JButton("Kilépés");
        lostFrame.setResizable(false);
        returnToMainMenuButton.addActionListener(e -> 
        {
            lostFrame.dispose();
            gameFrame.dispose();
            SwingUtilities.invokeLater(() -> {MainMenu.create_MainMenu();});

        });
    
        exitButton.addActionListener(e -> 
        {
            System.exit(0);
        });
    
        panel.add(returnToMainMenuButton);
        panel.add(exitButton);
        lostFrame.add(panel);
    
        lostFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lostFrame.pack();
        lostFrame.setLocationRelativeTo(null);
        lostFrame.setVisible(true);
    }
    public static void won() 
    {
        save();
        for (int i = 0; i < sizex; ++i) 
        {
            for (int j = 0; j < sizey; ++j) 
            {
                if (!Table.table[i][j].isVisible && Table.table[i][j].hasFlag != 1) 
                {
                    Table.table[i][j].reveal();
                }
            }
        }
    JFrame wonFrame = new JFrame("Nyertél");
    JPanel panel = new JPanel();
    JButton backToMenuButton = new JButton("Vissza a főmenübe");
    JButton exitButton = new JButton("Kilépés");
    wonFrame.setResizable(false);

    backToMenuButton.addActionListener(e -> 
    {
        gameFrame.dispose();
        wonFrame.dispose();
        SwingUtilities.invokeLater(() -> {MainMenu.create_MainMenu();});
        
    });
    exitButton.addActionListener(e -> 
    {
    System.exit(0); 
        });

    panel.add(backToMenuButton);
    wonFrame.add(panel);
    panel.add(exitButton);

    wonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    wonFrame.pack();
    wonFrame.setLocationRelativeTo(null);
    wonFrame.setVisible(true);
}
    public static void setnumberOfGoodFlags(int x)
    {
        numberOfGoodFlags = numberOfGoodFlags + x;
    }
    public static int getSizex()
    {
        return sizex;
    }
    public static int getSizey()
    {
        return sizey;
    }
    public static void save()
    {
        User tmp;
        if(lost == false)
        {
            tmp = new User(sizex, sizey, mines, timeElapsed,true);
        }
        else
        {
            tmp = new User(sizex, sizey, mines, timeElapsed,false);
        }
        UserData data = new UserData();
        try
        {
            data = new UserData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("_LeaderBoards/"+user+".dat"));
            data.uR = (List<User>)ois.readObject();
            ois.close();
        }catch (Exception e)
        {

        }
        data.uR.add(tmp);
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("_LeaderBoards/"+user+".dat"));
            oos.writeObject(data.uR);
            oos.close();
        }catch(Exception ex)
        {

        }

    }
    public static int getNumberofGoodFlags()
    {
        return numberOfGoodFlags;
    } 
}