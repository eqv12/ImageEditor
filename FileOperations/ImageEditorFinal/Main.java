package ImageEditorFinal;

import ImageEditorFinal.io.ImageReader;
import ImageEditorFinal.operations.Pipeline;
import ImageEditorFinal.view.cli.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter image file path: ");
//        String path = scanner.nextLine();

        Menu menu = new Menu("cat.bmp");
        menu.displayMenu();
    }
}
