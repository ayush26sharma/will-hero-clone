package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Will extends GameComponents{

    private Player currentPlayer;
    private ArrayList<Weapon> WeaponList;
    private int CoinsCollected;
    private int position;
    private int jumpHeight;
    private Helmet helmet;
    private boolean immunity;
    private int size;
    private boolean resurrected;
    private Weapon currentWeapon;
    private boolean hasWeapon;
    private FloatingIsland island;

    public boolean isHasWeapon() {
        return hasWeapon;
    }

    public void setHasWeapon(boolean hasWeapon) {
        this.hasWeapon = hasWeapon;
    }

    public Will(Location loc, double l, double w, Pane gridpane, Player player) {
        super(loc, l, w, gridpane, "images/hero.png");
        this.currentPlayer = player;
        setIsland();
        this.helmet = new Helmet();
    }

    public ArrayList<Weapon> getWeaponList() {
        return WeaponList;
    }

    public int getCoinsCollected() {
        return CoinsCollected;
    }

    public int getPosition() {
        return position;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public Helmet getHelmet() {
        return helmet;
    }

    public int getSize() {
        return size;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public FloatingIsland getIsland() {
        return island;
    }

    public boolean hasImmunity(){
        return immunity;
    }

    public boolean hasWeapon(){
        return currentWeapon==null;
    }

    public void setWeapons(Weapon w1) {
        WeaponList.add(w1);
    }

    public void setCoinsCollected(int coinsCollected) {
        CoinsCollected = coinsCollected;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public void setHelmet(Helmet helmet) {
        this.helmet = helmet;
    }

    public void setWeaponList(ArrayList<Weapon> weaponList) {
        WeaponList = weaponList;
    }

    public boolean isImmunity() {
        return immunity;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setImmunity(boolean immunity) {
        this.immunity = immunity;
    }

    public void setResurrected(boolean resurrected) {
        this.resurrected = resurrected;
    }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public void setIsland() {
        for (FloatingIsland is: currentPlayer.getGame().getIslandArray()) {
            if(Game.isCollided(image, is.getImage())){
                this.island = is;
                System.out.println("yes");
                break;
            }
        }
    }

    public void move(){
//        AnimationTimer move = new AnimationTimer() {
//            int distance = 0;
//            @Override
//            public void handle(long l) {
//                if (distance<130) {
//                    distance += 7;
//                    image.setLayoutX(image.getLayoutX() - 10);
//                }
//
//            }
//        };
//        move.start();
        TranslateTransition Will = new TranslateTransition();
        Will.setNode(image);
        Will.setDuration(Duration.millis(75));
        Will.setByX(150);
        Will.play();
    }


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


    public boolean isResurrected() {
        return resurrected;
    }

    public void spawn(){

    }

    public void dies(){
//        if (resurrected){
//            Main.ExitGameMain();
//        }
//        else if (){
//            if (){
//                Main.ExitGameMain();
//            }
//        }

    }

//    public void jump() {
////        AnimationTimer jump = new AnimationTimer() {
////            int distance = 0;
////            @Override
////            public void handle(long l) {
////
////                if (isCollided(image, island.getImage())) {
////                    if (distance < 540) {
////                        distance += 7;
////                        image.setLayoutY(image.getLayoutY() - 3);
////                    }
////                }
////                else if (!isCollided(image, island.getImage()) && distance>=140){
////                    image.setLayoutY(image.getLayoutY() + 3);
////                }
////            }
////        };
////        jump.start();
////    }
//
//        TranslateTransition hero = new TranslateTransition();
//        hero.setNode(image);
//        hero.setByY(50);
//        hero.setDuration(Duration.millis(300));
//        hero.play();
//    }
}
