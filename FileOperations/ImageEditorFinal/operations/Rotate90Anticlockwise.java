package ImageEditorFinal.operations;

import ImageEditorFinal.model.BMPImage;
import ImageEditorFinal.model.PixelMatrix;

import static ImageEditorFinal.utils.BitwiseUtils.writeLittleEndian;

public class Rotate90Anticlockwise implements ImageOperation {

    public BMPImage apply(BMPImage input) {
        BMPImage output = new BMPImage(input);

        // Width and height are swapped after 90° rotation
        output.width = input.height;
        output.height = input.width;

        // Recalculate row size with padding
        int newRowSize = output.width * 3;
        if (newRowSize % 4 != 0) {
            int rem = newRowSize % 4;
            newRowSize += (4 - rem);
        }
        output.rowSize = newRowSize;

        // Update DIB header
        writeLittleEndian(output.width, output.dibHeader, 4);
        writeLittleEndian(output.height, output.dibHeader, 8);

        // Construct rotated pixel matrix
        output.pixelMatrix = new PixelMatrix(input.pixelMatrix, newRowSize, "RotateAnti");

        return output;
    }
}
