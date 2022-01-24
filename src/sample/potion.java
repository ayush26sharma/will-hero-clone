package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class potion extends GameComponents{
    public potion(Location loc, double l, double w, Pane gridpane) {
        super(loc, l, w, gridpane, "assets/unnamed.png" );
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
                    Game.pain.getChildren().remove(image);
                    Game.hero.getImage().setImage(new Image("images/hero.png", 120, 120, false, false));
                    Game.hero.getImage().setY(image.getY() - 35);
//                    Game.hero.getImage().setX(image.getX() - 35);
//                    Game.hero.getImage().setX(Game.hero.getImage().);
//                    Game.hero.getImage().setLayoutY(Game.hero.getImage().getLayoutY());
                    Game.hero.setImmunity(true);
                    //   System.out.println("collision");
                }
            }
        };
        move.start();
    }

}
