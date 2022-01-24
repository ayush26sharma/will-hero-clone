package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.Serializable;

public class GameComponents implements Collision, Serializable {
    private Location location;
    private double width;
    private double length;
    private String path;
    transient protected ImageView image;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }



    //public void setCollided(boolean collided) {
//        isCollided = collided;
//    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public  ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public GameComponents(Location loc, double l, double w, Pane gridpane, String path){
        this.location = loc;
        this.length = l;
        this.width = w;
        this.path = path;
        this.add(gridpane, loc.getX_coordinate(), loc.getY_coordinate());
    }

    public void add(Pane gridpane, double x, double y){
        this.image = new ImageView();
        Image im= new Image(path, width, length,false,false);
        this.image.setImage(im);
        this.image.setLayoutX(x);
        this.image.setLayoutY(y);
        gridpane.getChildren().add(image);
    }

    @Override
    public void ifCollides(Object o1,Object o2){
        //this.isCollided = true;

    }

    public void move() {
        AnimationTimer move = new AnimationTimer() {
            int distance = 0;
            @Override
            public void handle(long l) {
                if (distance<130) {
                    distance += 7;
                    image.setLayoutX(image.getLayoutX() - 10);
                }
            }
        };
        move.start();
    }

    public void stop() {
        AnimationTimer pause = new AnimationTimer() {
            //            int distance = 0;
            @Override
            public void handle(long l) {
//                if (distance<90) {
//                    distance += 7;
                //image.setLayoutX(image.getLayoutX() + 7);
//                }
            }
        };
        pause.stop();
    }

//    public void jump() {
////        AnimationTimer jump = new AnimationTimer() {
////            int distance = 0;
////            int fall = 0;
////            @Override
////            public void handle(long l) {
////                if (distance < 140) {
////                    distance += 7;
////                    image.setLayoutY(image.getLayoutY() - 3);
////                }
////                if (distance >= 140 && fall < 140) {
////                    fall += 7;
////                    image.setLayoutY(image.getLayoutY() + 3);
////                }
////            }
////        };
////        jump.start();
//        TranslateTransition trans = new TranslateTransition();
//        trans.setNode(image);
//        trans.setDuration(Duration.millis(300));
//        trans.setByY(-50);
//        trans.setRate(2);
//        trans.setAutoReverse(true);
//        trans.setCycleCount(TranslateTransition.INDEFINITE);
//        trans.play();
//
//    }


    public void jump(int jumpHeight, int duration, int rate){
        TranslateTransition trans = new TranslateTransition();
        trans.setNode(image);
        trans.setDuration(Duration.millis(duration));
        trans.setByY(-jumpHeight);
        trans.setRate(rate);
        trans.setAutoReverse(true);
        trans.setCycleCount(TranslateTransition.INDEFINITE);
        trans.play();
    }

    @Override
    public boolean hasCollided(Object o1,Object o2){
        //return isCollided;
        return true;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
