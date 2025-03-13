import javax.swing.ImageIcon;
import java.awt.*;

public class Field 
{
    public int x;
    public int y;
    public int hasFlag = -1;
    public boolean isVisible = false; 

    public void onRightClick()
    {
        if(isVisible==true)
        {
            return;
        }
        if(hasFlag== -1)
        {
            ImageIcon baseIcon = new ImageIcon("img/flag-icon.png");
            Image scaledIcon = baseIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            ImageIcon scdIcon = new ImageIcon(scaledIcon);
            GameFrame.buttons[x][y].setIcon(scdIcon);
        }
        else
        {  
            GameFrame.buttons[x][y].setIcon(null);
        }
        this.hasFlag = this.hasFlag *-1;

    }
    public void onLeftClick()
    {
        if(hasFlag==1 || isVisible == true)
        {
            return;
        }
        isVisible = true;
    }
    public void reveal()
    {
    }
    public void onEmptyClick(int x, int y)
    {

    }
    public String getType()
    {
        return "F";
    }
}
