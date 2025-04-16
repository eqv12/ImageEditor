package ImageEditorFinal.model;

public class Pixel {
    public byte r;
    public byte g;
    public byte b;

    Pixel(byte b, byte g, byte r){
        this.b = b;
        this.g = g;
        this.r = r;
    }

    // copy constructor
    Pixel(Pixel original){
        this.b = original.b;
        this.g = original.g;
        this.r = original.r;
    }
}