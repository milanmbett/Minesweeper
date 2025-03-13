import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class UserDataTest 
{
    private UserData userData;    
    @Before
    public void SetUp()
    {
        userData = new UserData();
        userData.addRecord(1, 2, 3, 4, true);
    }
    @Test
    public void testUserDataGetValueAt() 
    {
        Object valueAt0 = userData.getValueAt(0, 0);
        Object valueAt1 = userData.getValueAt(0, 1);
        Object valueAt2 = userData.getValueAt(0, 2);
        Object valueAt3 = userData.getValueAt(0, 3);
        Object valueAt4 = userData.getValueAt(0, 4);

        assertEquals(1, valueAt0);
        assertEquals(2, valueAt1);
        assertEquals(3, valueAt2);
        assertEquals(4, valueAt3);
        assertEquals(true, valueAt4);
    }
    @Test
    public void testAddRecord() 
    {
        UserData userData = new UserData();
        userData.addRecord(10, 10, 5, 30, true);

        assertEquals(1, userData.getRowCount());

        assertEquals(10, userData.getValueAt(0, 0));
        assertEquals(10, userData.getValueAt(0, 1));
        assertEquals(5, userData.getValueAt(0, 2));
        assertEquals(30, userData.getValueAt(0, 3));
        assertEquals(true, userData.getValueAt(0, 4));
    }
}
