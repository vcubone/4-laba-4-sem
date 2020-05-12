import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator
{
    public static final int MAX_ITERATIONS = 2000;
    
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }

    public int numIterations(double x, double y)
    {
         //z = x+yi
	    double	a = x;
        double	b = y;
        double  nexta;
        double  nextb;

        for (int i = 0; i < MAX_ITERATIONS; i ++)
        {
            nexta = x+a*a-b*b;
            nextb = y+a*b*2;
            a = nexta;
            b = nextb;
            if (a*a+b*b > 4)
            return i;

        }
        return (-1);

        
    
    }
}