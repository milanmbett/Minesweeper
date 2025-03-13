import java.io.Serializable;

public class User implements Serializable
{
    private int x;
    private int y;
    private int m;
    private int t;
    private boolean nyert;

    public int getX()
    {
        return x;
    }
    public void setX(int a)
    {
        x = a;
    }
    public int getY()
    {
        return y;
    }
    public void setY(int a)
    {
        y = a;
    }
    public int getM()
    {
        return m;
    }
    public void setM(int a)
    {
        m = a;
    }
    public int getT()
    {
        return t;
    }
    public void setT(int a)
    {
        t = a;
    }
    public boolean didNyert()
    {
        return nyert;
    }
    public void setNyert(int a)
    {
        if(a==0)
        {
            nyert = false;
        }
        else
        {
            nyert = true;
        }
    }
    public User(int x,int y,int m,int t, boolean n)
    {
        this.x = x;
        this.y = y;
        this.m = m;
        this.t = t;
        nyert = n;
    }
}
