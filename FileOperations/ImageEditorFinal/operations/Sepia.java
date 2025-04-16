package ImageEditorFinal.operations;

import ImageEditorFinal.model.BMPImage;
import ImageEditorFinal.model.Pixel;

public class Sepia implements ImageOperation{
    public BMPImage apply(BMPImage input){
        BMPImage output = new BMPImage(input);
        for (int i = 0; i < input.height; i++){
            for (int j = 0; j < input.width; j++){
                Pixel p = output.pixelMatrix.getPixel(i,j);

                int red = p.r & 255;
                int green = p.g & 255;
                int blue = p.b & 255;

                int tr = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
                int tg = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
                int tb = (int)(0.272 * red + 0.534 * green + 0.131 * blue);

                // Clamp values to 255 before converting to byte because sepia values can be greater than 255 (wrap around
                // error in byte)
                p.r = (byte) Math.min(255, tr);
                p.g = (byte) Math.min(255, tg);
                p.b = (byte) Math.min(255, tb);
            }
        }
        return output;
    }
}
