package ImageEditorFinal.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static ImageEditorFinal.model.BMPImage.BMP_HEADER_SIZE;
import static ImageEditorFinal.model.BMPImage.DIB_HEADER_SIZE;

public class BMPHeaderUtils {
    public static void constructHeader(InputStream fis, byte header[], int count){
        for (int i = 0; i < count; i++){
            try {
                header[i] = (byte) fis.read();
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static byte[] consumeExtraBytes(InputStream fis, int pixelOffset){
        int extraLen = pixelOffset - (BMP_HEADER_SIZE + DIB_HEADER_SIZE);
        byte extraData[] = new byte[extraLen];

        for (int i = 0; i < extraLen; i++){
            try {
                extraData[i] = (byte) fis.read();
            }
            catch (IOException e){
                System.out.println("Error consuming extra bytes: " + e.getMessage());
            }
        }
        return extraData;
    }

}
