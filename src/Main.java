import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Main {
    private static void ImageProces(String img, int gambarKe){
        try {
            // baca file gambar
            File input = new File(img);
            //simpan ke dalam buffer
            BufferedImage image = ImageIO.read(input);

            //ambil tinggi dan lebar dari gambar
            int width = image.getWidth(null);
            int height = image.getHeight(null);

            //mulai proses negatif image
            negatif(image ,width, height, gambarKe);
        }catch (IOException io){
            JOptionPane.showMessageDialog(null, "Gagal Load Gambar\n"+io);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error \n"+e);
        }
    }

    private static void negatif(BufferedImage image, int width, int height, int gambarKe){
        try{
            //looping pixel satu persatu dari atas ke bawah, kiri ke kanan
            for(int x = 0; x < width; x++){
                for (int y = 0; y < height; y++){
                    //ambil value dari channel rgb di koordinat x,y
                    Color rgb = new Color(image.getRGB(x, y));

                    //pecah array rgb, dimasukkan ke variable masing-masing
                    int r = (int)rgb.getRed();
                    int g = (int)rgb.getGreen();
                    int b = (int)rgb.getBlue();

                    // buat warna baru untuk pixel tersebut. Proses negatif image
                    Color newColor = new Color(255-r,255-g,255-b);

                    // set warna baru tersebut
                    image.setRGB(x, y, newColor.getRGB());
                }
            }

            // buat wadah file baru dengan nama Negative.jpg
            File output = new File("OutputImage/Negative"+gambarKe+".jpg");

            // hasil perubahan gambar dibuat di file output tersebut
            ImageIO.write(image,"jpg",output);

            // proses selesai
            System.out.println("Negative Image "+gambarKe+" processing Success");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void main(String[] args) {
//        for (int jumlah = 1; jumlah <= 6; jumlah++){
//            ImageProces("ImageSet/input"+jumlah+".jpg", jumlah);
//        }
            ImageProces("ImageSet/input"+7+".png", 7);
    }
}
