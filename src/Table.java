import java.util.Random;



public class Table 
{
    private int sizex;
    private int sizey;
    private int numberofmines;
    private int numberofnotmines;
    public static Field[][] table;

    public Table()
    {
        sizex = 10;
        numberofmines = 10;
        numberofnotmines = sizex*sizey - numberofmines;
    }
    public Table(int sx,int sy ,int nm)
    {
        this.sizex = sx;
        this.sizey = sy;
        this.numberofmines = nm;
        this.numberofnotmines = this.sizex*this.sizey - this.numberofmines;
    }
    
    
    public void createTable()
    {
        Random r = new Random();

        for(int i=0;i<numberofmines;++i)
        {
            boolean successfull = false;
            while(!successfull)
            {
                int x = r.nextInt(sizex);
                int y = r.nextInt(sizey);


                if(table[x][y].getType().equals("F") && !table[x][y].isVisible)
                {
                    Mine m = new Mine(x,y);
                    table[x][y] = m;
                    successfull = true;
                }
            }
        }
        for(int i=0;i<sizex;++i)
        {
            for(int j =0;j<sizey;++j)
            {
                if( table[i][j]==null || !table[i][j].getType().equals("X"))
                {
                    NotMine n = new NotMine(i, j);
                    table[i][j] = n;
                }
            }
        }
        calculate();
    }
    public void calculate()
    {
        for(int i=0;i<sizex;++i)
        {
            for(int j =0;j<sizey;++j)
            {
                if(table[i][j] instanceof NotMine)
                {
                    NotMine m = (NotMine)table[i][j];
                    m.calculate();
                }
            }
        }
    } 
    public int getSizex()
    {
        return this.sizex;
    }
    public int getSizey()
    {
        return this.sizey;
    }
    public int getNumberofMines()
    {
        return this.numberofmines;
    }
    public int getNumberofNotMines()
    {
        return numberofnotmines;
    }


}