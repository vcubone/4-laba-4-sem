import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;
/**
 * JImageDisplay
 */
public class JImageDisplay extends JComponent
{
    private BufferedImage image;
    public JImageDisplay(int width, int height)
    {
        image = new BufferedImage(width, height, 1);//TYPE_INT_RGB = 1
        Dimension dim = new Dimension(width, height);
        setPreferredSize(dim);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    public void clearImage()
    {
        for (int i = 0; i < image.getWidth();i++)
        {
            for (int j = 0; j < image.getHeight();j++)
            {
                image.setRGB(i, j, 0);
            }
        }
    }

    public void drawPixel(int x, int y, int rgbColor)
    {
        image.setRGB(x, y, rgbColor);
    }
}
