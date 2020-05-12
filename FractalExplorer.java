import java.awt.Color;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;


public class FractalExplorer 
{
    
    private int razmEcr;
    private JImageDisplay disp;
    private FractalGenerator gener;
    private Rectangle2D.Double twod;

    public  FractalExplorer(int size)
    {
        this.razmEcr = size;
        this.gener = new Mandelbrot();
        this.twod = new Rectangle2D.Double();
        gener.getInitialRange(this.twod);
        
    }

    public void createAndShowGUI()
    {
        JFrame frame = new JFrame("Fractal Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton button = new JButton("Reset Display");
        
        ButtonEventListener butev = new ButtonEventListener();
        MouseEventListener mouev = new MouseEventListener();
        
        button.addActionListener(butev);
        disp = new JImageDisplay(razmEcr, razmEcr);
        disp.addMouseListener(mouev);
        

        frame.setLayout(new java.awt.BorderLayout());
        frame.add(disp, java.awt.BorderLayout.CENTER);
        frame.add(button, java.awt.BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    class ButtonEventListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            gener.getInitialRange(twod);
            drawFractal();
        }
    }

    public class MouseEventListener extends MouseAdapter {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            double xCoord = FractalGenerator.getCoord(twod.x, twod.x + twod.width, razmEcr, e.getX());
            double yCoord = FractalGenerator.getCoord(twod.y, twod.y + twod.width, razmEcr, e.getY());
            gener.recenterAndZoomRange(twod, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }


    public void drawFractal()
    {
        for (int x = 0; x < razmEcr; x++)
        {
            for (int y = 0; y < razmEcr; y++)
            {
                double xCoord = FractalGenerator.getCoord(twod.x, twod.x + twod.width,razmEcr, x);
                double yCoord = FractalGenerator.getCoord(twod.y, twod.y + twod.width,razmEcr, y);
                double num = gener.numIterations(xCoord,yCoord);
                if (num != -1)
                {
                    float hue = 0.7f + (float) num / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    disp.drawPixel(x, y, rgbColor);
                }
                else
                {
                    disp.drawPixel(x, y, 0);
                }

            }
        }
        disp.repaint();
    }

    public static void main(String[] args) 
    {
        FractalExplorer frac = new FractalExplorer(1000)    ;
        frac.createAndShowGUI();
        frac.drawFractal();
    }
}