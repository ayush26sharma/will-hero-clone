package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class weapon2 extends Weapon{

    private Location location;

    public weapon2(Pane gridpane) {
        this.location = Game.hero.getLocation();
        this.add(gridpane, location.getX_coordinate(), location.getY_coordinate());
        throwshuri();
    }

    public void add(Pane gridpane, double x, double y){
        this.image = new ImageView();
        Image im= new Image("assets/WeaponShuriken.png", 20, 20,false,false);
        this.image.setImage(im);
        this.image.setLayoutX(x);
        this.image.setLayoutY(y);
        gridpane.getChildren().add(image);
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }

    public void throwshuri(){
        AnimationTimer move = new AnimationTimer() {
            int distance = 0;
            @Override
            public void handle(long l) {
                if (distance<37830) {
                    distance += 20;
                    image.setLayoutX(image.getLayoutX() + 30);
                }
            }
        };
        move.start();
    }
}
