package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

public class Coin extends GameComponents{
    boolean executed = false;

    public Coin(Location loc, double l, double w, Pane gridpane) {
        super(loc, l, w, gridpane, "assets/Coin.png");
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
                if (!executed) {
                    if (Game.isCollided(image, Game.hero.getImage())) {
                        Game.pain.getChildren().remove(image);

                        executed = true;
                        Game.hero.setCoinsCollected(Game.hero.getCoinsCollected() + 20);
                        //   System.out.println("collision");
                    }
                }
            }
        };
        move.start();
    }
}
