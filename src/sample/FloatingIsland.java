package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class FloatingIsland extends GameComponents{
    private float width;
    private float length;
    private Chest mysteryChest;
    private Orc oRc;
    private TNT tnt;
    private Group group;
    public void setWidth(float width) {
        this.width = width;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void setMysteryChest(Chest mysteryChest) {
        this.mysteryChest = mysteryChest;
    }

    public void setOrc(Orc orc) {
        this.oRc = orc;
    }

    public void setTnt(TNT tnt) {
        this.tnt = tnt;
    }

    public FloatingIsland(Location loc, double l, double w, Pane gridpane, boolean orc, boolean chest, boolean tnt, String path) {
        super(loc, l, w, gridpane, path);
        double x = Math.random();
        double y = Math.random();
        if (orc){
            if(x>0.5){
                Location loc1 = new Location(loc.getX_coordinate()+10,loc.getY_coordinate()-50);
                this.oRc = new GreenOrcs(loc1,50,50,gridpane);
                oRc.setIsland(this);
//                orcJump();
//                oRc.fall();
                //oRc.jump(50,400,1);
            }
            else{
                Location loc1 = new Location(loc.getX_coordinate()+10,loc.getY_coordinate()-50);
                this.oRc = new RedOrcs(loc1,50,50,gridpane);
                //oRc.jump(50,400,1);
                oRc.setIsland(this);
//                orcJump();
//                oRc.fall();

            }
        }
        if (chest){
            if(y>0.5){
                Location loc1 = new Location(loc.getX_coordinate()+140, loc.getY_coordinate()-40);
                this.mysteryChest = new coinChest(loc1,40,65,gridpane);
                //mysteryChest.jump(25,250,1);
            }
            else{
                Location loc1 = new Location(loc.getX_coordinate()+140, loc.getY_coordinate()-40);
                this.mysteryChest = new WeaponChest(loc1,40,65,gridpane);
                //mysteryChest.jump(25,250,1);
            }
        }
        if (tnt){
            Location loc1 = new Location(loc.getX_coordinate()+80, loc.getY_coordinate()-30);
            this.tnt = new TNT(loc1,30,36,gridpane,"assets/TNT.png");

        }
    }

    public Chest getMysteryChest() {
        return mysteryChest;
    }

    public Orc getOrc() {
        return oRc;
    }

    public TNT getTnt() {
        return tnt;
    }

    public void jump(){
//        AnimationTimer jump = new AnimationTimer() {
//            int distance = 0;
//            @Override
//            public void handle(long l) {
//                if (oRc!=null){
//                    System.out.println("hi");
//                    if(isCollided(oRc.getImage(), image)){
//                        System.out.println("yes");
//                        if (distance < 540) {
//                            distance += 7;
//                            oRc.getImage().setLayoutY(image.getLayoutY() - 3);
//                        }
//                    }
//                    else if (!isCollided(oRc.getImage(), image) && distance>=140){
//                        image.setLayoutY(image.getLayoutY() + 3);
//                    }
//                }
//            }
//        };
    }
    public void orcJump(){
        AnimationTimer move = new AnimationTimer() {
            @Override
            public void handle(long l) {
//                if (distance<130) {
//                    distance += 7;
//                    image.setLayoutX(image.getLayoutX() - 10);
//                }
                if(Game.isCollided(image, oRc.getImage())){
                    if (oRc.getJumpHeight()<130) {
                        oRc.setSpeed(1);
                        oRc.setJumpHeight(oRc.getJumpHeight()+7);
                        oRc.getImage().setLayoutY(oRc.getImage().getLayoutY() - 10);
                    }
                    else {
                        oRc.setSpeed(0);
                    }
//                    Game.hero.setCoinsCollected(Game.hero.getCoinsCollected()+20);
                    //   System.out.println("collision");
                }
            }
        };
        move.start();
    }
}
