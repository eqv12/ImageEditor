package ImageEditorFinal.io;

import ImageEditorFinal.model.BMPImage;
import ImageEditorFinal.model.Pixel;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageWriter {
    OutputStream fos;

    public ImageWriter(String bmpFileName){
        try {
            fos = new BufferedOutputStream(new FileOutputStream(bmpFileName+".bmp"));
        } catch (IOException e){
            System.out.println("Could not create file output stream: " + e.getMessage());
        }
    }

    public void createOutput(BMPImage bmp){
        try {
            // write bmp header
            for (int i = 0; i < bmp.BMP_HEADER_SIZE; i++) {
                fos.write(bmp.bmpHeader[i]);
            }
            // write dib header
            for (int i = 0; i < bmp.DIB_HEADER_SIZE; i++) {
                fos.write(bmp.dibHeader[i]);
            }
            // write extra data
            for (int i = 0; i < bmp.extraData.length; i++) {
                fos.write(bmp.extraData[i]);
            }
            // write pixels
            for (int i = bmp.height-1; i >= 0; i--) {
                for (int j = 0; j < bmp.width; j++){
                    Pixel p = bmp.pixelMatrix.getPixel(i, j);
                    fos.write(p.b);
                    fos.write(p.g);
                    fos.write(p.r);
                }
                for (int x = bmp.width*3; x < bmp.rowSize; x++){
                    fos.write(0);
                }
            }
        }
        catch (IOException e){
            System.out.println("Error creating output file: " +
                    "" + e.getMessage());
        }
        finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    System.out.println("Failed to close stream: " + e.getMessage());
                }
            }
        }
    }
}
