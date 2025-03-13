import java.awt.*;

public class NotMine extends Field
{
    int numberofminesnearby;
    public NotMine(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.numberofminesnearby = 0;
    }
    
    @Override
    public void onLeftClick()
    {
        if(hasFlag==1 && isVisible == true)
        {
            return;
        }
        this.reveal();
    }
    @Override
    public void reveal()
    {   
        GameFrame.buttons[x][y].setText(Table.table[x][y].getType());
        GameFrame.buttons[x][y].setFont(new Font("Arial",Font.BOLD,40));
        switch(Table.table[x][y].getType())
        {
            case "0":
                GameFrame.buttons[x][y].setText(null);
                GameFrame.buttons[x][y].setBackground(new Color(207, 202, 200));
                onEmptyClick(x,y);
                break;
            case "1":
                GameFrame.buttons[x][y].setForeground(new Color(66, 135, 245));
                break;
            case "2":
                GameFrame.buttons[x][y].setForeground(Color.green);
                break;
            case "3":
                GameFrame.buttons[x][y].setForeground(Color.red);
                break;
            case "4":
                GameFrame.buttons[x][y].setForeground(new Color(3, 48, 252));
                break;                                
            case "5":
                GameFrame.buttons[x][y].setForeground(new Color(89, 4, 11));
                break;                              
            case "6":
                GameFrame.buttons[x][y].setForeground(Color.cyan);
                break;                               
            case "7":
                GameFrame.buttons[x][y].setForeground(Color.black);
                break;                                   
            case "8":
                GameFrame.buttons[x][y].setForeground(Color.darkGray);
                break;                             
        }
                this.isVisible = true;
    }
    public void calculate()
    {
        for(int i =-1;i<=1;++i)
        {
            for(int j = -1;j<= 1; ++j)
            {
                int tmpx = x + i;
                int tmpy = y + j;

                if(tmpx>=0 && tmpx<Table.table.length && tmpy>=0 && tmpy< Table.table[0].length)
                {
                    if(Table.table[tmpx][tmpy].getType().equals("X"))
                    {
                        ++numberofminesnearby;
                    }
                }
            }
        }
    }
    public void onEmptyClick(int x, int y) {
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };
        isVisible = true;
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
    
            if (isValidPosition(newX, newY) && !Table.table[newX][newY].isVisible) {
                Table.table[newX][newY].reveal();
    
                if (Table.table[newX][newY].getType().equals("0")) {
                    onEmptyClick(newX, newY);
                }
            }
        }
    }
    
    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < GameFrame.getSizex() && y >= 0 && y < GameFrame.getSizey();
    }
    @Override
    public String getType()
    {
        return Integer.toString(numberofminesnearby);        
    }
}
