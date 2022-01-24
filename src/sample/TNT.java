package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class TNT extends GameComponents{

    public TNT(Location loc, double l, double w, Pane gridpane, String path) {
        super(loc, l, w, gridpane, path);
    }

    public void explodes(){
        Game.hero.dies();
    }

    public void move(){
        AnimationTimer move = new AnimationTimer() {
            int distance = 0;
            @Override
            public void handle(long l) {
                if (distance<130) {
                    distance += 7;
                    image.setLayoutX(image.getLayoutX() - 10);
                }
                if(Game.isCollided(image, Game.hero.getImage())){
                    image.setImage(new Image("images/explosion.png",250,250,false, false));
                    image.setLayoutX(image.getLayoutX()-10);
                    image.setLayoutY(image.getLayoutY()-10);
                    explodes();
                }
            }
        };
        move.start();
    }
}
