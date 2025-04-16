package ImageEditorFinal.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class BMPImage extends Image {
    public static final int DIB_HEADER_SIZE = 40;
    public static final int BMP_HEADER_SIZE = 14;

//    private FileInputStream fis;
    public byte[] bmpHeader = new byte[BMP_HEADER_SIZE];
    public byte[] dibHeader = new byte[DIB_HEADER_SIZE];
    public byte[] extraData;

    public int pixelOffset;

    public BMPImage(){
//        System.out.println(Arrays.toString(extraData));
//        System.out.println(Arrays.toString(bmpHeader));
//        System.out.println(Arrays.toString(dibHeader));
    };

    public BMPImage(BMPImage original){
        super(original);
        bmpHeader = original.bmpHeader.clone();
        dibHeader = original.dibHeader.clone();
        extraData = original.extraData.clone();
        pixelOffset = original.pixelOffset;
    }
}

//    void applyFilter(String filterName){
//        Pixel pixelsCopy[][] = new Pixel[height][width];
//        clone(pixelsCopy);
//        for (int i = 0; i < height; i++){
//            for (int j = 0; j < width; j++){
//                switch (filterName){
//                    case "grayscale" -> grayscale(pixelsCopy[i][j]);
//                    case "sepia" -> sepia(pixelsCopy[i][j]);
//                    case "vintage" -> vintage(pixelsCopy[i][j]);
//                }
//            }
//        }
//        createOutput(filterName, pixelsCopy, bmpHeader, dibHeader, height, width, rowSize);
//    }
//
//    private void grayscale(Pixel p){
//        int red = p.r & 255;
//        int green = p.g & 255;
//        int blue = p.b & 255;
//
//        int gray = (int) ((red* 0.299) + (green * 0.587) + (blue * 0.114));
//        p.r = p.g = p.b = (byte) gray;
//
//    }
//
//    private void sepia(Pixel p){
//        int red = p.r & 255;
//        int green = p.g & 255;
//        int blue = p.b & 255;
//
//        int tr = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
//        int tg = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
//        int tb = (int)(0.272 * red + 0.534 * green + 0.131 * blue);
//
//        // Clamp values to 255 before converting to byte because sepia values can be greater than 255 (wrap around
//        // error in byte)
//        p.r = (byte) Math.min(255, tr);
//        p.g = (byte) Math.min(255, tg);
//        p.b = (byte) Math.min(255, tb);
//    }
//
//    private void vintage(Pixel p) {
//        int red = p.r & 255;
//        int green = p.g & 255;
//        int blue = p.b & 255;
//
//        // Step 1: Fade colors slightly (simulate photo aging)
//        red = (int)(red * 0.9);
//        green = (int)(green * 0.85);
//        blue = (int)(blue * 0.8);
//
//        // Step 2: Add a warm tint
//        red = Math.min(255, red + 20);
//        green = Math.min(255, green + 10);
//        blue = Math.max(0, blue - 10);
//
//        // Assign back
//        p.r = (byte) red;
//        p.g = (byte) green;
//        p.b = (byte) blue;
//    }
//
//    void cropImage(int up, int left, int down, int right){
//        if ((height - up) <= 0 || ((height - down) <= 0) || (height - up - down) <= 0)
//            throw new IllegalArgumentException("Vertical crop exceeds permissible value");
//
//        if ((width - left) <= 0 || ((width - right) <= 0) || (width - left - right) <= 0)
//            throw new IllegalArgumentException("Horizontal crop exceeds permissible value");
//
//        int bfSize = readLittleEndian(bmpHeader, 2);
//        int biWidth = readLittleEndian(dibHeader, 4);
//        int biHeight = readLittleEndian(dibHeader, 8);
//        int biSizeImage = readLittleEndian(dibHeader, 20);
//
//        biWidth -= (left + right);
//        biHeight -= (up + down);
//
//        int newRowSize = biWidth*3;
//        if (newRowSize % 4 != 0){
//            int rem = newRowSize % 4;
//            newRowSize += (4 - rem);
//        }
//
//        bfSize -= (rowSize*height);
//        bfSize += (newRowSize*biHeight);
//        biSizeImage = newRowSize * biHeight;
//
//        byte bmpHeaderCopy[] = Arrays.copyOf(bmpHeader, bmpHeader.length);
//        byte dibHeaderCopy[] = Arrays.copyOf(dibHeader, dibHeader.length);
//
//        writeLittleEndian(bfSize, bmpHeaderCopy, 2);
//        writeLittleEndian(biWidth, dibHeaderCopy, 4);
//        writeLittleEndian(biHeight, dibHeaderCopy, 8);
//        writeLittleEndian(biSizeImage, dibHeaderCopy, 20);
//
//        Pixel pixelsCopy[][] = new Pixel[height - up - down][width - left - right];
//        // populate cropped image
//        for (int i = up; i < (height-down); i++){
//            for (int j = left; j < (width-right); j++){
//                pixelsCopy[i-up][j-left] = new Pixel(pixels[i][j]);
//            }
//        }
//
//        createOutput("cropped", pixelsCopy, bmpHeaderCopy, dibHeaderCopy, biHeight, biWidth, newRowSize);
//    }
//
//    void rotate90(){
//        int biWidth = readLittleEndian(dibHeader, 4);
//        int biHeight = readLittleEndian(dibHeader, 8);
//
//        int tmp = biWidth;
//        biWidth = biHeight;
//        biHeight = tmp;
//
//        Pixel pixelsRotated[][] = new Pixel[biHeight][biWidth];
//
//        for (int i = 0; i < biHeight; i++){
//            for (int j = 0; j < biWidth; j++){
//                pixelsRotated[i][j] = new Pixel(pixels[j][i]);
//            }
//        }
//
//        byte dibHeaderCopy[] = Arrays.copyOf(dibHeader, dibHeader.length);
//
//        writeLittleEndian(biWidth, dibHeaderCopy, 4);
//        writeLittleEndian(biHeight, dibHeaderCopy, 8);
//
//        int newRowSize = biWidth*3;
//        if (newRowSize % 4 != 0){
//            int rem = newRowSize % 4;
//            newRowSize += (4 - rem);
//        }
//
//        createOutput("rotated90", pixelsRotated, bmpHeader, dibHeaderCopy, biHeight, biWidth, newRowSize);
//    }





