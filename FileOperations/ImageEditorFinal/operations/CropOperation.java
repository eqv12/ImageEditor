package ImageEditorFinal.operations;

//import ImageEditorFinal.model.Pixel;
import ImageEditorFinal.model.BMPImage;
import ImageEditorFinal.model.PixelMatrix;

import java.util.Arrays;

import static ImageEditorFinal.utils.BitwiseUtils.readLittleEndian;
import static ImageEditorFinal.utils.BitwiseUtils.writeLittleEndian;


public class CropOperation implements ImageOperation {
    int up;
    int left;
    int down;
    int right;
    
    public CropOperation(int up, int left, int down, int right){
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }
    public BMPImage apply(BMPImage input){
        if ((input.height - up) <= 0 || ((input.height - down) <= 0) || (input.height - up - down) <= 0)
            throw new IllegalArgumentException("Vertical crop exceeds permissible value");

        if ((input.width - left) <= 0 || ((input.width - right) <= 0) || (input.width - left - right) <= 0)
            throw new IllegalArgumentException("Horizontal crop exceeds permissible value");

        int bfSize = readLittleEndian(input.bmpHeader, 2);
        int biWidth = readLittleEndian(input.dibHeader, 4);
        int biHeight = readLittleEndian(input.dibHeader, 8);
        int biSizeImage = readLittleEndian(input.dibHeader, 20);

        biWidth -= (left + right);
        biHeight -= (up + down);

        int newRowSize = biWidth*3;
        if (newRowSize % 4 != 0){
            int rem = newRowSize % 4;
            newRowSize += (4 - rem);
        }

        BMPImage output = new BMPImage(input);
        bfSize -= (input.rowSize*input.height);
        bfSize += (newRowSize*biHeight);
        biSizeImage = newRowSize * biHeight;

        output.rowSize = newRowSize;
        output.width = biWidth;
        output.height = biHeight;

        writeLittleEndian(bfSize, output.bmpHeader, 2);
        writeLittleEndian(biWidth, output.dibHeader, 4);
        writeLittleEndian(biHeight, output.dibHeader, 8);
        writeLittleEndian(biSizeImage, output.dibHeader, 20);

        output.pixelMatrix = new PixelMatrix(input.pixelMatrix, this.up, this.down, this.left, this.right, output.rowSize);

        return output;
    }

}
