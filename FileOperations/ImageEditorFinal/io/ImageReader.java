package ImageEditorFinal.io;

import ImageEditorFinal.model.BMPImage;
import ImageEditorFinal.model.PixelMatrix;
import ImageEditorFinal.utils.BMPHeaderUtils;
import ImageEditorFinal.utils.BitwiseUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ImageReader {
    InputStream fis;
    BufferedInputStream fis2;

    public ImageReader(String path) {
        try {
            fis = new BufferedInputStream(new FileInputStream(path));
        } catch (IOException e){
            System.out.println("Could not create file input stream for " + path + ": " + e.getMessage());
        }
    }

    public BMPImage createBMP(){
        BMPImage bmp = new BMPImage();
        BMPHeaderUtils.constructHeader(fis, bmp.bmpHeader, bmp.BMP_HEADER_SIZE);
        BMPHeaderUtils.constructHeader(fis, bmp.dibHeader, bmp.DIB_HEADER_SIZE);
        readMetaData(bmp);
        bmp.extraData = BMPHeaderUtils.consumeExtraBytes(fis, bmp.pixelOffset);
        bmp.pixelMatrix = new PixelMatrix(fis, bmp.height, bmp.width, bmp.rowSize);
//        System.out.println(Arrays.toString(bmp.extraData));
//        System.out.println(Arrays.toString(bmp.bmpHeader));
//        System.out.println(Arrays.toString(bmp.dibHeader));
        return bmp;
    }

    public void readMetaData(BMPImage bmp){
        bmp.pixelOffset = BitwiseUtils.readLittleEndian(bmp.bmpHeader, 10);
        bmp.width = BitwiseUtils.readLittleEndian(bmp.dibHeader, 4);
        bmp.height = BitwiseUtils.readLittleEndian(bmp.dibHeader, 8);
        bmp.rowSize = bmp.width*3;
        if (bmp.rowSize % 4 != 0){
            int rem = bmp.rowSize % 4;
            bmp.rowSize += (4-rem);
        }
    }
}
