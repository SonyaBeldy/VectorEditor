package com.example.vectoreditor.model;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class CursorImage {

    public static ImageCursor N_ROTATE;
    public static ImageCursor NE_ROTATE;
    public static ImageCursor E_ROTATE;
    public static ImageCursor SE_ROTATE;
    public static ImageCursor S_ROTATE;
    public static ImageCursor SW_ROTATE;
    public static ImageCursor W_ROTATE;
    public static ImageCursor NW_ROTATE;

    public static ArrayList<ImageCursor> cursors = new ArrayList<>();

    static {
        Image image = new Image(String.valueOf(CursorImage.class.getResource("/com/example/vectoreditor/images/icons/N_ROTATE.png")));
        N_ROTATE = new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2);
        cursors.add(N_ROTATE);

        image = new Image(String.valueOf(CursorImage.class.getResource("/com/example/vectoreditor/images/icons/NW_ROTATE.png")));
        NW_ROTATE = new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2);
        cursors.add(NW_ROTATE);

        image = new Image(String.valueOf(CursorImage.class.getResource("/com/example/vectoreditor/images/icons/W_ROTATE.png")));
        W_ROTATE = new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2);
        cursors.add(W_ROTATE);

        image = new Image(String.valueOf(CursorImage.class.getResource("/com/example/vectoreditor/images/icons/SW_ROTATE.png")));
        SW_ROTATE = new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2);
        cursors.add(SW_ROTATE);

        image = new Image(String.valueOf(CursorImage.class.getResource("/com/example/vectoreditor/images/icons/S_ROTATE.png")));
        S_ROTATE = new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2);
        cursors.add(S_ROTATE);

        image = new Image(String.valueOf(CursorImage.class.getResource("/com/example/vectoreditor/images/icons/SE_ROTATE.png")));
        SE_ROTATE = new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2);
        cursors.add(SE_ROTATE);

        image = new Image(String.valueOf(CursorImage.class.getResource("/com/example/vectoreditor/images/icons/E_ROTATE.png")));
        E_ROTATE = new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2);
        cursors.add(E_ROTATE);

        image = new Image(String.valueOf(CursorImage.class.getResource("/com/example/vectoreditor/images/icons/NE_ROTATE.png")));
        NE_ROTATE = new ImageCursor(image, image.getWidth() / 2, image.getHeight() / 2);
        cursors.add(NE_ROTATE);
    }

    void setCursor(){

    }

    public static ImageCursor rotateCursor(int ind, double angle){
        double a = 0;
        if (angle > 0) {
            while (a < angle) {
                a += (2 * Math.PI)/cursors.size();
                ind++;
            }
        }
        if (angle < 0) {
            while (a < Math.PI + angle) {
                a += Math.PI/cursors.size();
            }
        }
        return cursors.get(ind % cursors.size());
    }

    void moveCursor(){

    }
}
