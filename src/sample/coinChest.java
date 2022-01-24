package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class coinChest extends Chest{
    boolean executed = false;

    public coinChest(Location loc, double l, double w, Pane gridpane) {
        super(loc, l, w, gridpane);
    }

    public void move(){
        AnimationTimer move = new AnimationTimer() {
            int distance = 0;
            @Override
            public void handle(long l) {
                if (distance < 130) {
                    distance += 7;
                    image.setLayoutX(image.getLayoutX() - 10);
                }
                if (!executed) {

                    if (Game.isCollided(image, Game.hero.getImage())) {
                        image.setImage(new Image("assets/ChestOpen.png", 65, 40, false, false));
                           System.out.println("collision");

                        executed = true;
                        Game.hero.setCoinsCollected(Game.hero.getCoinsCollected() + 20);
                    }
                }
            }
        };
        move.start();
    }

}
