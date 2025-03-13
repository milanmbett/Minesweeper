import javax.swing.*;
import java.awt.*;



public class MainMenu 
{
    private static String user;
    public static void create_MainMenu()
    {
        JFrame mainMenuFrame = new JFrame("Aknakereső");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setSize(700, 400);

        JButton startGameButton = new JButton("Játék indítása");
        

        JButton exitButton = new JButton("Kilépés");
        exitButton.addActionListener(e->{ System.exit(0); });

        JButton leaderBoardButton = new JButton("Ranglista");
        

        JLabel sizexLabel = new JLabel("Pálya x mérete:");
        JTextField sizexField = new JTextField(2);
        sizexField.setText("10");

        JLabel sizeyLabel = new JLabel("Pálya y mérete:");
        JTextField sizeyField = new JTextField(2);
        sizeyField.setText("10");

        JLabel minesLabel = new JLabel("Aknák száma: ");
        JTextField minesField = new JTextField(2);
        minesField.setText("10");


        JLabel userLaber = new JLabel("Felhasználó:");
        JTextField userField = new JTextField(8);

        
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        mainMenuPanel.add(userLaber, gbc);  
        ++gbc.gridx;
        mainMenuPanel.add(userField, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);


        mainMenuPanel.add(startGameButton,gbc);
        gbc.gridx = 0;
        ++gbc.gridy;
        mainMenuPanel.add(sizexLabel, gbc);
        ++gbc.gridx;
        mainMenuPanel.add(sizexField , gbc);
        ++gbc.gridx;
        mainMenuPanel.add(sizeyLabel , gbc);
        ++gbc.gridx;
        mainMenuPanel.add(sizeyField, gbc);
        ++gbc.gridx;
        mainMenuPanel.add(minesLabel,gbc);
        ++gbc.gridx;
        mainMenuPanel.add(minesField, gbc);

        
        gbc.gridx = 2;
        ++gbc.gridy;
        mainMenuPanel.add(leaderBoardButton,gbc);
        ++gbc.gridy;
        mainMenuPanel.add(exitButton,gbc);
        

        mainMenuFrame.add(mainMenuPanel);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setVisible(true);

        startGameButton.addActionListener(e -> 
        {
            try 
            {
                int sx = Integer.parseInt(sizexField.getText());
                int sy = Integer.parseInt(sizeyField.getText());
                int ms = Integer.parseInt(minesField.getText());
                if (sx >= 1 && sy >= 1 && ms >= 1 && !userField.getText().equals("") && (sx*sy>ms)) 
                {
                    user = userField.getText();
                    GameFrame.startGame(sx, sy, ms, user);
                    mainMenuFrame.setVisible(false);
                } 
                else if(sx*sy<ms)
                {
                    JOptionPane.showMessageDialog(mainMenuFrame, "Na ezt most hogy képzelted el?");
                }
                else if(sx*sy==ms)
                {
                    JOptionPane.showMessageDialog(mainMenuFrame, "Na azért ez túl könnyű lenne!");
                }
                else
                {
                    JOptionPane.showMessageDialog(mainMenuFrame, "A mezőknek nagyobbnak kell lennie mint 1 és az aknák számának pozitív egész számnak kell lennie!\nValamint adjon meg egy felhasználót!");
                }
            } catch (NumberFormatException ex) 
            {
                JOptionPane.showMessageDialog(mainMenuFrame, "Kérlek csak egész számokat adj meg.");
            }
        });
        leaderBoardButton.addActionListener(
                e->{

                    if(!userField.getText().equals("") && LeaderBoardFrame.exists(userField.getText()))
                    {
                        LeaderBoardFrame lbf = new LeaderBoardFrame(userField.getText());
                        lbf.setVisible(true);
                    }
                    else if(userField.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(mainMenuFrame, "Kérlek adj meg egy felhasználót!");
                    }
                    else if(!LeaderBoardFrame.exists(userField.getText()))
                    {
                        JOptionPane.showMessageDialog(mainMenuFrame, "Ez a felhasználó még nem játszott!");
                    }
                });
            
        

    }
}
