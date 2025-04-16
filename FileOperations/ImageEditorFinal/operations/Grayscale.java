package ImageEditorFinal.operations;

import ImageEditorFinal.model.BMPImage;
import ImageEditorFinal.model.Pixel;

public class Grayscale implements ImageOperation{
    public BMPImage apply(BMPImage input){
        BMPImage output = new BMPImage(input);
        for (int i = 0; i < input.height; i++){
            for (int j = 0; j < input.width; j++){
                Pixel p = output.pixelMatrix.getPixel(i,j);

                int red = p.r & 255;
                int green = p.g & 255;
                int blue = p.b & 255;

                int gray = (int) ((red* 0.299) + (green * 0.587) + (blue * 0.114));
                p.r = p.g = p.b = (byte) gray;
            }
        }
        return output;
    }
}
