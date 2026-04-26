import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShowMenuJFrame extends JFrame {

    public ShowMenuJFrame(String imagePath) {

        Image image;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.err.println("Could not load image: " + imagePath);
            image = null;
        }

        final Image finalImage = image;

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalImage != null) {
                    int imgW = finalImage.getWidth(null);
                    int imgH = finalImage.getHeight(null);
                    int panelW = getWidth();
                    int panelH = getHeight();

                    double scale = Math.min((double) panelW / imgW, (double) panelH / imgH);
                    int scaledW = (int) (imgW * scale);
                    int scaledH = (int) (imgH * scale);

                    // Center the image
                    int x = (panelW - scaledW) / 2;
                    int y = (panelH - scaledH) / 2;

                    g.drawImage(finalImage, x, y, scaledW, scaledH, this);
                }
            }
        };

        this.getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int frameHeight = 600;
        if (image != null) {
            int imgW = image.getWidth(null);
            int imgH = image.getHeight(null);
            int frameWidth = (int) ((double) imgW / imgH * frameHeight);
            setSize(frameWidth, frameHeight);
        } else {
            setSize(600, frameHeight);
        }

        setVisible(true);
    }
}