package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

abstract class Chest extends GameComponents{

    private int quantity;
    private float jumpHeight;

    public Chest(Location loc, double l, double w, Pane gridpane) {
        super(loc, l, w, gridpane,"assets/ChestClosed.png");
    }


    public int getQuantity() {
        return quantity;
    }

    public float getJumpHeight() {
        return jumpHeight;
    }


}
