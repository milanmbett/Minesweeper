import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class UserTest 
{
    private User user;
    @Before
    public void SetUp()
    {
        user = new User(1, 2, 3, 4, true); 
    }
    @Test
    public void testUserGettersAndSetters() 
    {
        user.setX(5);
        user.setY(6);
        user.setM(7);
        user.setT(8);
        user.setNyert(0);

        assertEquals(5, user.getX());
        assertEquals(6, user.getY());
        assertEquals(7, user.getM());
        assertEquals(8, user.getT());
        assertFalse(user.didNyert());
    }
}
