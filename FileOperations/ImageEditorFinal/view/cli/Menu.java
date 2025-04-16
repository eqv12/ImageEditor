package ImageEditorFinal.view.cli;

import ImageEditorFinal.io.ImageReader;
import ImageEditorFinal.io.ImageWriter;
import ImageEditorFinal.model.BMPImage;
import ImageEditorFinal.operations.*;

import java.util.*;

public class Menu {
    private Pipeline pipeline;
    private ImageReader imgReader;
    private BMPImage start;

    public Menu(String path){
        ImageReader imageReader = new ImageReader(path);
        start = imageReader.createBMP();
        pipeline = new Pipeline(start);
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Image Editor Menu:");
            System.out.println("1. Apply Grayscale\t\t2. Apply Sepia\t\t\t\t3. Apply Vintage");
            System.out.println("4. Crop Image\t\t\t5. Rotate 90 clockwise\t\t6. Rotate 90 anticlockwise");
            System.out.println("7. Flip horizontal\t\t8. Flip vertical\t\t\t9. Undo");
            System.out.println("10. Redo\t\t\t\t11. Save Image\t\t\t\t12. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    applyGrayscale();
                    break;
                case 2:
                    applySepia();
                    break;
                case 3:
                    applyVintage();
                    break;
                case 4:
                    cropImage();
                    break;
                case 5:
                    rotate90Clockwise();
                    break;
                case 6:
                    rotate90AntiClockwise();
                    break;
                case 7:
                    flip("Horizontal");
                    break;
                case 8:
                    flip("Vertical");
                    break;
                case 9:
                    undo();
                    break;
                case 10:
                    redo();
                    break;
                case 11:
                    save();
                    break;
                case 12:
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    void applyGrayscale(){
        Grayscale grayscale = new Grayscale();
        BMPImage next = grayscale.apply(pipeline.getCurrentImage());
        pipeline.add(next);
    }
    void applySepia(){
        Sepia sepia = new Sepia();
        BMPImage next = sepia.apply(pipeline.getCurrentImage());
        pipeline.add(next);
    }
    void applyVintage(){
        Vintage vintage = new Vintage();
        BMPImage next = vintage.apply(pipeline.getCurrentImage());
        pipeline.add(next);
    }
    void cropImage(){
        Scanner s = new Scanner(System.in);

        System.out.println("Enter top border crop value: ");
        int up = s.nextInt();
        System.out.println("Enter bottom border crop value: ");
        int down = s.nextInt();
        System.out.println("Enter left border crop value: ");
        int left = s.nextInt();
        System.out.println("Enter right border crop value: ");
        int right = s.nextInt();

        CropOperation crop = new CropOperation(up, left, down, right);
        BMPImage next = crop.apply(pipeline.getCurrentImage());
        pipeline.add(next);
    }

    void rotate90Clockwise(){
        Rotate90Clockwise rot90cw = new Rotate90Clockwise();
        BMPImage next = rot90cw.apply(pipeline.getCurrentImage());
        pipeline.add(next);
    }

    void rotate90AntiClockwise(){
        Rotate90Anticlockwise rot90acw = new Rotate90Anticlockwise();
        BMPImage next = rot90acw.apply(pipeline.getCurrentImage());
        pipeline.add(next);
    }

    void flip(String orientation){
        if (orientation.equals("Horizontal")) {
            FlipHoriz fh = new FlipHoriz();
            BMPImage next = fh.apply(pipeline.getCurrentImage());
            pipeline.add(next);
        }

        else if (orientation.equals("Vertical")) {
            FlipHoriz fv = new FlipHoriz();
            BMPImage next = fv.apply(pipeline.getCurrentImage());
            pipeline.add(next);
        }
    }

    void undo(){
        pipeline.undo();
    }
    void redo(){
        pipeline.redo();
    }
    void save(){
        ImageWriter writer = new ImageWriter("output");
        writer.createOutput(pipeline.getCurrentImage());
    }
}
