import static org.junit.Assert.*;
import org.junit.Test;

public class MainMenuTest 
{
    @Test
    public void testLeaderBoardButtonAction_Exists() {
        assertTrue(LeaderBoardFrame.exists("_TESZT"));
    }

    @Test
    public void testLeaderBoardButtonAction_NotExists() {
        assertFalse(LeaderBoardFrame.exists("nonExistingUser"));
    }

    @Test
    public void testLeaderBoardButtonAction_EmptyUser() {
        assertFalse(LeaderBoardFrame.exists(""));
    }

}
