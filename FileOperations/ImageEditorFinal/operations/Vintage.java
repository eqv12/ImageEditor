package ImageEditorFinal.operations;

import ImageEditorFinal.model.BMPImage;
import ImageEditorFinal.model.Pixel;

public class Vintage {
    public BMPImage apply(BMPImage input){
        BMPImage output = new BMPImage(input);
        for (int i = 0; i < input.height; i++){
            for (int j = 0; j < input.width; j++){
                Pixel p = output.pixelMatrix.getPixel(i,j);

                int red = p.r & 255;
                int green = p.g & 255;
                int blue = p.b & 255;

                // Step 1: Fade colors slightly (simulate photo aging)
                red = (int)(red * 0.9);
                green = (int)(green * 0.85);
                blue = (int)(blue * 0.8);

                // Step 2: Add a warm tint
                red = Math.min(255, red + 20);
                green = Math.min(255, green + 10);
                blue = Math.max(0, blue - 10);

                // Assign back
                p.r = (byte) red;
                p.g = (byte) green;
                p.b = (byte) blue;
            }
        }
        return output;
    }
}
