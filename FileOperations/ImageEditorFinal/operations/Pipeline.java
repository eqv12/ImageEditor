package ImageEditorFinal.operations;

import ImageEditorFinal.model.BMPImage;

import java.util.ArrayList;
import java.util.List;

public class Pipeline {
    private List<BMPImage> imageHistory = new ArrayList<>();  // List to hold image states
    private int currentIndex = -1;  // Pointer to the current image

    public Pipeline(BMPImage start) {
        imageHistory.add(start);
        currentIndex++;
    }

    public void undo(){
        if (checkUndoPossible()){
            currentIndex--;
        }
    }

    public void redo(){
        if (checkRedoPossible())
            currentIndex++;
    }

    public boolean checkUndoPossible() {
        return (currentIndex > -1);
    }
    public boolean checkRedoPossible() {
        return (currentIndex != imageHistory.size() - 1);
    }

    public void add(BMPImage next){
        if (currentIndex < imageHistory.size() - 1) {
            imageHistory.subList(currentIndex + 1, imageHistory.size()).clear();
        }
        imageHistory.add(next);
        currentIndex++;
    }

    public BMPImage getCurrentImage(){
        return imageHistory.get(currentIndex);
    }
}
