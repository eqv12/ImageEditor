package ImageEditorFinal.operations;

import ImageEditorFinal.model.BMPImage;
import ImageEditorFinal.model.PixelMatrix;

import static ImageEditorFinal.utils.BitwiseUtils.writeLittleEndian;

public class Rotate90Clockwise implements ImageOperation{

    public BMPImage apply(BMPImage input){
        BMPImage output = new BMPImage(input);

        output.width = input.height;
        output.height = input.width;

        int newRowSize = output.width*3;
        if (newRowSize % 4 != 0){
            int rem = newRowSize % 4;
            newRowSize += (4 - rem);
        }
        output.rowSize = newRowSize;

        writeLittleEndian(output.width, output.dibHeader, 4);
        writeLittleEndian(output.height, output.dibHeader, 8);
        output.pixelMatrix = new PixelMatrix(input.pixelMatrix, newRowSize, "Rotate");

        return output;
    }
}
