package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.Serializable;

abstract public class Weapon implements Serializable {
    protected int damage;
    protected int level;
    protected int range;
    protected transient ImageView image;

//    public Weapon(Location loc, double l, double w, Pane gridpane, String path) {
//        super(loc, l, w, gridpane, path);
//    }

    public int getDamage() {
        return damage;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void upgrade(){

    }
}
