package sample;

import java.io.Serializable;
import java.util.WeakHashMap;

public class Helmet implements Serializable {
    private String name;
    private Weapon weapon1;
    private Weapon weapon2;

    public String getName() {
        return name;
    }

    public Weapon getWeapon1() {
        return weapon1;
    }

    public Weapon getWeapon2() {
        return weapon2;
    }

    public void equipHelmet(){

    }
}
