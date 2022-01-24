package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class WeaponChest extends Chest{
    private String weapon;
    boolean executed = false;

    public WeaponChest(Location loc, double l, double w, Pane gridpane) {
        super(loc, l, w, gridpane);
        double a = Math.random();
        if (a >0.01) {
            weapon = "sword";
        }
        else{
            weapon = "shuriken";
        }
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

                        executed = true;
                        Game.hero.setImmunity(false);

                        if (weapon.equals("sword")) {
                            Game.hero.getImage().setImage(new Image("images/heroSword.png", 120, 120, false, false));
                            Game.hero.getImage().setY(image.getY() - 20);
                            Game.hero.getImage().setX(image.getX() - 20);
                            weapon1 sword = new weapon1();
                            Game.hero.setHasWeapon(true);
                            Game.hero.setCurrentWeapon(sword);
                        } else {
                            Game.hero.getImage().setImage(new Image("images/heroshuri.png", 120, 120, false, false));
                            Game.hero.getImage().setY(image.getY() - 20);
                            Game.hero.getImage().setX(image.getX() - 20);
                            weapon2 shuriken = new weapon2(Game.pain);
                            Game.hero.setHasWeapon(true);
                            Game.hero.setCurrentWeapon(shuriken);
                        }

                        System.out.println("collision");
                    }
                }
            }
        };
        move.start();
    }

}
