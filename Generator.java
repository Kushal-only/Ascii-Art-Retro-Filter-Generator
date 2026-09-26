import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Generator {
    public static void main(String[] args)throws Exception {
      Scanner sc = new Scanner(System.in);
        
        // User Input
        System.out.print("Enter image file name(jpg or png): ");
        String fileName = sc.nextLine();
        BufferedImage original = ImageIO.read(new File(fileName));

        // Shrink image to fit in the terminal
        int newWidth = 80;
        int newHeight = (int)(original.getHeight()*(80.0/original.getWidth())*0.55);
        BufferedImage smallImage = new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = smallImage.createGraphics();
        g.drawImage(original,0,0,newWidth,newHeight,null);
        g.dispose();

        // Character palette: darkest(space) to brightest(@)
        String chars = " .:=*#%@";

        // Loop through every pixel and print a character
        for(int y=0;y<smallImage.getHeight();y++) {
            for(int x=0;x<smallImage.getWidth();x++) {
                Color color = new Color(smallImage.getRGB(x,y)); // Read the pixel color
                int brightness = (color.getRed()+color.getGreen()+color.getBlue())/3;
                int index = (brightness*(chars.length()-1))/255;
                System.out.print(chars.charAt(index));
            }
            System.out.println();
        }
    }
}