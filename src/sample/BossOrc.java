package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BossOrc extends Orc{
    private Weapon weapon;
    private int attack = 0;
    private static BossOrc bOssOrc = null;
    private transient ImageView won = new ImageView();

    public static BossOrc getbOssOrc() {
        return bOssOrc;
    }

    public static void setbOssOrc(BossOrc bOssOrc) {
        BossOrc.bOssOrc = bOssOrc;
    }

    private boolean dead = false;
    public static BossOrc getInstance(){
        if (bOssOrc == null){
            System.out.println("newboss");
            bOssOrc = new BossOrc(new Location(Game.boss.getLocation().getX_coordinate()+50, Game.boss.getLocation().getY_coordinate()-310), 310,310,Game.pain);
        }
        return bOssOrc;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    private BossOrc(Location loc, double l, double w, Pane gridpane) {
        super(loc, l, w, gridpane, "assets/OrcBoss.png");
    }

    public void spawn(){

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
                    if(attack<4) {
                        attack++;
                        if (distance1 < 100) {
                            distance1 += 5;
                            image.setLayoutX(image.getLayoutX() + 300);
                        }
                    }
                    else{
                        Game.pain.getChildren().remove(image);
                        won.setImage(new Image("sample/won1.jpeg",1200,670,false,false));
                        dead = true;
                        if(!Game.pain.getChildren().contains(won)) {
                            Game.pain.getChildren().add(won);
                            Game.pain.getChildren().get(Game.pain.getChildren().size()-1).setOnMouseClicked(e -> {
                                try {
                                    Game.pain.getChildren().remove(Game.pain.getChildren().size()-1);
                                    Game.ExitGame();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            });
                        }
                    }
                }
            }
        };
        move.start();
    }

    public void attack(){

    }

}
