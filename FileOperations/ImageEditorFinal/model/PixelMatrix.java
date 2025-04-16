package ImageEditorFinal.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PixelMatrix {
    private Pixel[][] pixels;
    int height;
    int width;
    int rowSize;

    public Pixel getPixel(int i, int j) {
        if (i < 0 || i >= height || j < 0 || j >= width) {
            System.out.println("Out of bounds pixel index: (" + i + ", " + j + ")");
            return null;
        }
        return pixels[i][j];
    }


    public PixelMatrix(InputStream fis, int height, int width, int rowSize){
        this.height = height;
        this.width = width;
        this.rowSize = rowSize;

        pixels = new Pixel[height][width];

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                byte blue;
                byte green;
                byte red;

                try {
                    try {
                        blue = (byte) fis.read();
                    } catch (IOException e) {
                        throw new RuntimeException("Error reading blue: " + e.getMessage());
                    }

                    try {
                        green = (byte) fis.read();
                    } catch (IOException e) {
                        throw new RuntimeException("Error reading green: " + e.getMessage());
                    }

                    try {
                        red = (byte) fis.read();
                    } catch (IOException e) {
                        throw new RuntimeException("Error reading red: " + e.getMessage());
                    }
                    pixels[this.height - 1 - i][j] = new Pixel(blue, green, red);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }

            // consume padding pixels
            for (int x = 0; x < (rowSize - width * 3); x++) {
                try {
                    fis.read();
                } catch (IOException e) {
                    System.out.println("Error consuming padding cells: " + e.getMessage());
                }
            }
        }
    }

    PixelMatrix(PixelMatrix original, int rowSize){
        this.width = original.width;
        this.height = original.height;
        this.rowSize = rowSize;
        pixels = new Pixel[height][width];

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                pixels[i][j] = new Pixel(original.pixels[i][j]);
            }
        }
    }

    // constructor for cropping image
    public PixelMatrix(PixelMatrix original, int up, int left, int down, int right, int rowSize){
        this.width = original.width - left - right;
        this.height = original.height - up - down;
        this.rowSize = rowSize;

        pixels = new Pixel[height][width];
        // populate cropped image
        for (int i = up; i < (original.height-down); i++){
            for (int j = left; j < (original.width-right); j++){
                pixels[i-up][j-left] = new Pixel(original.pixels[i][j]);
            }
        }
    }

    // constructor for rotate 90 clockwise
//    public PixelMatrix(PixelMatrix original, int rowSize, String op){
//        this.width = original.height;
//        this.height = original.width;
//        this.rowSize = rowSize;
//        pixels = new Pixel[height][width];
//
//
//        // Rotate 90° clockwise: newPixel[i][j] = original[original.height - j - 1][i]
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                pixels[i][j] = new Pixel(original.pixels[original.height - j - 1][i]);
//            }
//        }
//    }

    public PixelMatrix(PixelMatrix original, int rowSize, String op) {
        if (op.equals("Rotate") || op.equals("RotateAnti")){
            this.width = original.height;
            this.height = original.width;
        }
        else {
            this.width = original.width;
            this.height = original.height;
        }
        this.rowSize = rowSize;
        pixels = new Pixel[height][width];

        if (op.equals("Rotate")) {
            // Rotate 90° clockwise: newPixel[i][j] = original[original.height - j - 1][i]
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    pixels[i][j] = new Pixel(original.pixels[original.height - j - 1][i]);
                }
            }
        } else if (op.equals("RotateAnti")) {
            // Rotate 90° anti-clockwise: newPixel[i][j] = original[j][original.width - i - 1]
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    pixels[i][j] = new Pixel(original.pixels[j][original.width - i - 1]);
                }
            }
        }
        else if (op.equals("FlipH")) {
            // Flip horizontally: mirror image along vertical axis
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    pixels[i][j] = new Pixel(original.pixels[i][width - j - 1]);
                }
            }
        }

        else if (op.equals("FlipV")) {
            // Flip horizontally: mirror image along vertical axis
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    pixels[i][j] = new Pixel(original.pixels[height - i - 1][j]);
                }
            }
        }
        else {
            throw new IllegalArgumentException("Unsupported operation: " + op);
        }
    }


}
