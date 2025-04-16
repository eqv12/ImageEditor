package ImageEditorFinal.operations;

//package ImageEditorFinal.operations;

import ImageEditorFinal.model.BMPImage;
import ImageEditorFinal.model.PixelMatrix;

import static ImageEditorFinal.utils.BitwiseUtils.writeLittleEndian;

public class FlipVertical implements ImageOperation {

    @Override
    public BMPImage apply(BMPImage input) {
        // Make a copy of the input BMP image
        BMPImage output = new BMPImage(input);

        // Dimensions remain the same
        output.width = input.width;
        output.height = input.height;
        output.rowSize = input.rowSize;

        // Update DIB header just to be safe
//        writeLittleEndian(output.width, output.dibHeader, 4);
//        writeLittleEndian(output.height, output.dibHeader, 8);

        // Apply flip horizontal via PixelMatrix constructor
        output.pixelMatrix = new PixelMatrix(input.pixelMatrix, input.rowSize, "FlipV");

        return output;
    }
}
