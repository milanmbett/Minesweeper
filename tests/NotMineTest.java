import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class NotMineTest 
{

    @Test
    public void testCalculate() 
    {

        Table.table = new Field[3][3];
        NotMine checker1 = new NotMine(1, 1);
        NotMine checker2 = new NotMine(0, 0);

        Table.table[0][0] = checker2;
        Table.table[0][1] = new Mine(0, 1);
        Table.table[0][2] = new NotMine(0, 2);
        Table.table[1][0] = new Mine(1, 0); 
        Table.table[1][1] = checker1;
        Table.table[1][2] = new Mine(1, 2);
        Table.table[2][0] = new NotMine(2, 0); 
        Table.table[2][1] = new Mine(2, 1); 
        Table.table[2][2] = new NotMine(2, 2);

        checker1.calculate();
        checker2.calculate();
        

        assertEquals("4",Table.table[1][1].getType());
        assertEquals("2",Table.table[0][0].getType());
    }
}
