import org.junit.Test;

import static org.junit.Assert.*;

import javax.swing.JButton;
public class GameFrameTest 
{
    @Test
    public void testStartGame()
    {
        GameFrame.startGame(10, 10, 10, "test");
        assertNotNull(GameFrame.buttons);
        assertEquals(10, GameFrame.buttons.length);
        assertEquals(10, GameFrame.buttons[0].length);
    }
    @Test
    public void testGameFrameWon() 
    {
        
        Table.table = new Field[10][10];
        for (int i = 0; i < GameFrame.getSizex(); ++i) 
        {
            for (int j = 0; j < GameFrame.getSizey(); ++j) 
            {
                Table.table[i][j] = new Field();
            }
        }
        for (int i = 0; i < GameFrame.getSizex(); ++i) 
        {
            for (int j = 0; j < GameFrame.getSizey(); ++j) {

                Table.table[i][j].isVisible = true;
                if (!(Table.table[i][j] instanceof Mine)) 
                {
                    Table.table[i][j].reveal();
                }
            }
        }
        GameFrame.won();
        assertFalse(GameFrame.lost);
    }
    @Test
    public void testMineWithGoodFlag()
    {
        GameFrame.setnumberOfGoodFlags(0);
        assertEquals(0, GameFrame.getNumberofGoodFlags());
        GameFrame.t.table = new Field[2][2];
        Mine newMine = new Mine(0, 0);
        
        GameFrame.t.table[0][0] = newMine;
        GameFrame.t.table[0][1] = new NotMine(0, 1);
        GameFrame.t.table[1][0] = new NotMine(1, 0);
        GameFrame.t.table[1][1] = new NotMine(1, 1);

        GameFrame.buttons = new JButton[2][2];
        GameFrame.buttons[0][0] = new JButton();
        GameFrame.buttons[0][1] = new JButton();
        GameFrame.buttons[1][0] = new JButton();
        GameFrame.buttons[1][1] = new JButton();


        GameFrame.t.table[0][0].onRightClick();
        assertEquals(1, GameFrame.getNumberofGoodFlags());
        GameFrame.t.table[1][1].onRightClick();
        assertEquals(1, GameFrame.getNumberofGoodFlags());
    }
}
