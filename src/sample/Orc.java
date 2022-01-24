package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

abstract class Orc extends GameComponents{

    private float size;
    private int health;
    private int coinsRewarded;
    private FloatingIsland island;
    private float jumpHeight = 0;
    private String message;
    private int speed;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public float getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(float jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public Orc(Location loc, double l, double w, Pane gridpane, String path) {
        super(loc, l, w, gridpane, path);
    }


    public float getSize() {
        return size;
    }

    public int getHealth() {
        return health;
    }

    public int getCoinsRewarded() {
        return coinsRewarded;
    }

    public FloatingIsland getIsland() {
        return island;
    }

    public String getMessage() {
        return message;
    }

    public void setIsland(FloatingIsland island) {
        this.island = island;
    }

    public void dies(){
        Game.pain.getChildren().remove(image);
    }

    public void move(){
        AnimationTimer move = new AnimationTimer() {
            int distance = 0;
            int distance1 = 0;
            @Override
            public void handle(long l) {
                if (distance<130) {
                    distance += 7;
                    image.setLayoutX(image.getLayoutX() - 10);
                }
                if(Game.isCollided(image, Game.hero.getImage())) {
                    if (Game.hero.isHasWeapon() || Game.hero.hasImmunity()) {
                        dies();
                        //   System.out.println("collision");
                    }
                    else {
                        if (Game.hero.getImage().getLayoutY()+Game.hero.getLength() <= image.getLayoutY()){
                            dies();
                        }
                        else if (image.getLayoutY()+Game.hero.getLength() <= Game.hero.getImage().getLayoutY()){
                            Game.hero.dies();
                        }
                        else {
                            if (distance1 < 250) {
                                distance1 += 5;
                                image.setLayoutX(image.getLayoutX() + 300);
                            }
                        }
                    }
                }
            }
        };
        move.start();
    }
    public void fall(){
        TranslateTransition trans = new TranslateTransition();
        trans.setNode(image);
        trans.setDuration(Duration.INDEFINITE);
        trans.setByY(50);
        trans.setRate(2);
        if (image.getLayoutX()>island.getWidth()+island.getImage().getLayoutX()){
            trans.play();
        }
    }
}
