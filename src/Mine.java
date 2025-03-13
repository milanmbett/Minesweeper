import javax.swing.*;
import java.awt.*;


public class Mine extends Field 
{
    public Mine(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    @Override
    public void onLeftClick()
    {
        if(hasFlag==1)
        {
            return;
        }
        this.isVisible = true;
        this.reveal();
        GameFrame.lost();

    }
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
            GameFrame.setnumberOfGoodFlags(1);
        }
        else
        {  
            GameFrame.buttons[x][y].setIcon(null);
            GameFrame.setnumberOfGoodFlags(-1);
        }
        this.hasFlag = this.hasFlag *-1;

    }
    @Override
    public void reveal()
    {
        ImageIcon baseIcon = new ImageIcon("img/mine.png");
        Image scaledIcon = baseIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scdIcon = new ImageIcon(scaledIcon);
        GameFrame.buttons[x][y].setIcon(scdIcon);
    }
    @Override
    public String getType()
    {
        return "X";
    }

}
