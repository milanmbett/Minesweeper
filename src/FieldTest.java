import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;


public class FieldTest 
{
    @Before
    public void setUp() 
    {
        GameFrame.startGame(10, 10, 10, "tesztUser");
    }
    @Test
    public void testOnRightClick()
    {
        Field field = new Field();
        field.x = 0;
        field.y = 0;
        field.hasFlag = -1;
        field.isVisible = false;

        field.onRightClick();
        assertEquals(1,field.hasFlag);

        field.onRightClick();
        assertEquals(-1,field.hasFlag);

        field.isVisible = true;
        field.onRightClick();
        assertEquals(-1,field.hasFlag);
    }
    @Test
    public void testOnLeftClick() 
    {
        Field field = new Field();
        field.x = 0;
        field.y = 0;
        field.hasFlag = -1;
        field.isVisible = false;

        field.onLeftClick();
        assertTrue(field.isVisible);

        field.hasFlag = 1;
        field.isVisible = false;
        field.onLeftClick();
        assertFalse(field.isVisible);

        field.hasFlag = -1;
        field.isVisible = true;
        field.onLeftClick();
        assertTrue(field.isVisible);
    }    
}
