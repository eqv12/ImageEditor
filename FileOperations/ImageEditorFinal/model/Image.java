package ImageEditorFinal.model;

import java.io.FileInputStream;
import java.io.IOException;

public abstract class Image {
    public PixelMatrix pixelMatrix;
    public int height;
    public int width;
    public int rowSize;

    void calcRowSize(){
        rowSize = width*3;
        if (rowSize % 4 != 0){
            int rem = rowSize % 4;
            rowSize += (4-rem);
        }
    }

    Image (){

    }

    Image(Image original){
        this.width = original.width;
        this.height = original.height;
        calcRowSize();
        this.pixelMatrix = new PixelMatrix(original.pixelMatrix, rowSize);
    }

}
