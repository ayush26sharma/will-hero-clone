package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import java.io.Serializable;
import java.util.ArrayList;

import static sample.Main.*;

public class Game implements Serializable {

    private Player Currentplayer;
    private long GameScore = 0;
    static FloatingIsland boss;
    private ArrayList<Player> PlayerList;
    private ArrayList<FloatingIsland> IslandArray = new ArrayList<>();
    private ArrayList<coinChest> CoinChestArray = new ArrayList<>();
    private ArrayList<WeaponChest> WeaponChestArray = new ArrayList<>();
    private ArrayList<Orc> OrcsArray = new ArrayList<>();
    private ArrayList<TNT> TntArray = new ArrayList<>();
    private ArrayList<Coin> CoinArray = new ArrayList<>();
    private BossOrc boSSOrc;
    transient private ArrayList<Text> pausemenuButtons = new ArrayList<>();
    transient private final ImageView menu = new ImageView();
    transient static Will hero;
    transient static Pane pain;
    transient private Group group;
    private int location=0;
    transient private Label loc=new Label("Location: "+Integer.toString(location));
    transient private Label coinCounter=new Label(": 0");
    transient private Scene scene;
    transient private Label label=new Label("");

    public Game(Player currentplayer) {
        Currentplayer = currentplayer;
        GameScore = 0;
        pain = new Pane();
//        PlayerList = playerList;
        IslandArray = new ArrayList<>();
        CoinChestArray = new ArrayList<>();
        WeaponChestArray = new ArrayList<>();
        OrcsArray = new ArrayList<>();
        TntArray = new ArrayList<>();
        CoinArray = new ArrayList<>();
        this.pausemenuButtons = new ArrayList<>();
        this.group = CreatePauseGameMenu();
    }

//    public Game(Player currentplayer) {
//        Currentplayer = currentplayer;
//    }

    public ArrayList<FloatingIsland> getIslandArray() {
        return IslandArray;
    }

    public ArrayList<coinChest> getCoinChestArray() {
        return CoinChestArray;
    }

    public ArrayList<WeaponChest> getWeaponChestArray() {
        return WeaponChestArray;
    }

    public ArrayList<Orc> getOrcsArray() {
        return OrcsArray;
    }

    public ArrayList<TNT> getTntArray() {
        return TntArray;
    }

    public ArrayList<Coin> getCoinArray() {
        return CoinArray;
    }



    public void startNewGame(){
        scene = new Scene(pain, 1200, 670);
        Image poster = new Image("sample/bg.jpeg");
        BackgroundSize size = new BackgroundSize(1200, 670, false, false, false, false);
        BackgroundImage bg = new BackgroundImage(poster, BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background background = new Background(bg);
        ImageView image = new ImageView();
        Image im= new Image("assets/Coin.png", 25, 25,false,false);
        image.setImage(im);
        image.setX(1100);
        image.setY(56);
        loc.setLayoutX(512);
        loc.setLayoutY(50);
        loc.setFont(Font.font("Arial", FontWeight.LIGHT,30));
        loc.setTextFill(Color.web("#FFFFFF"));
        coinCounter.setLayoutX(1130);
        coinCounter.setLayoutY(50);
        coinCounter.setFont(Font.font("Arial", FontWeight.LIGHT,30));
        coinCounter.setTextFill(Color.web("#FFFFFF"));
        pain.getChildren().addAll(image,loc,coinCounter);
        addObjects();
//        for (FloatingIsland is: IslandArray) {
//            if (isCollided(hero.getImage(),is.getImage()))
//                //hero.jump(75,300,1);
//        }
        play(scene);
        pain.setBackground(background);
        Main.window.setScene(scene);
        Main.window.show();
    }

    public static boolean isCollided(ImageView image1, ImageView image2) {
        return image1.getBoundsInParent().intersects(image2.getBoundsInParent());
    }

    public void addObjects(){
        int lx=100;
        BossOrc.setbOssOrc(null);
        String path;
        HashMap<Integer,String> paths=new HashMap<>();
        pain.getChildren().add(label);
        paths.put(1,"assets/Islands1.png");
        paths.put(2,"assets/Islands2.png");
        paths.put(3,"assets/Islands3.png");
        paths.put(4,"assets/Islands4.png");
        paths.put(5,"assets/Islands5.png");
        paths.put(6,"assets/Islands6.png");
        paths.put(7,"assets/Islands7.png");
        paths.put(8,"assets/Islands8.png");
        Random rand = new Random();
        int y = 400;
        for(int i=0;i<43;i++) {
            int l = 125;
            int w = 230;

            if (i > 2) {
                int x = rand.nextInt(8) + 1;
                path = paths.get(x);
                boolean orc = false;
                boolean chest = false;
                boolean tnt = false;
                int trues = 0;

                if (Math.random() > 0.8) {
                    orc = true;
                    trues++;
                }
                if (Math.random() > 0.5) {
                    chest = true;
                    trues++;
                }
                if (Math.random() > 0.9) {
                    trues++;
                    tnt = true;
                }

                FloatingIsland island = new FloatingIsland(new Location(lx, y), l, w, pain, orc, chest, tnt, path);
                IslandArray.add(island);
                if (trues == 0 && Math.random() > 0.3) {
                    addCoin(lx + 40, y - 30, 4);
                }
                else if (Math.random() > 0.4 && trues == 1) {
                    if (orc) {
                        addCoin(lx + 70, y - 30, 3);
                    }
                    else if (tnt) {
                        addCoin(lx + 130, y - 30, 3);
                    }
                    else {
                        addCoin(lx + 40, y - 30, 3);
                    }
                }
            }
            else{
                FloatingIsland island = new FloatingIsland(new Location(lx, y), l, w, pain, false, false, false, "assets/Islands3.png");
                IslandArray.add(island);
            }


            int m = rand.nextInt(450) + 300;
            lx += m;

        }
        boss = new FloatingIsland(new Location(lx,y),700,1500,pain,false,false,false, "assets/Islands2.png");
        boSSOrc = BossOrc.getInstance();
        boss.setOrc(boSSOrc);
        boSSOrc.setIsland(boss);
        IslandArray.add(boss);
        hero = new Will(new Location(IslandArray.get(0).getLocation().getX_coordinate(),IslandArray.get(0).getLocation().getY_coordinate()-70), 80, 70, pain, Currentplayer);
    }

    public void addCoin(int lx, int ly,int l){
        for(int i=0;i<l;i++){
            Coin coin = new Coin(new Location(lx + 30*i, ly), 20, 20, pain);
            getCoinArray().add(coin);
        }
    }

    public void play(Scene scene) {
        for (FloatingIsland is: IslandArray) {
            if (is.getOrc()!=null) is.getOrc().jump(50,400,1);
            if (is.getMysteryChest()!=null) is.getMysteryChest().jump(25,200,1);
        }
        potion Potion = new potion(new Location(IslandArray.get(2).getLocation().getX_coordinate()+20,IslandArray.get(2).getLocation().getY_coordinate()-50), 50,50,pain);
        hero.jump(110,500,1);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                if(keyEvent.getCode()== KeyCode.SPACE){
                    location++;
                    //boss.move();
                    Potion.move();
                    loc.setText("Location: "+Integer.toString(location));
                    coinCounter.setText(": "+Integer.toString(hero.getCoinsCollected()));
                    for (FloatingIsland is: IslandArray) {
                        is.move();
                        if(is.getOrc()!=null){
                            is.getOrc().move();
                            is.getOrc().fall();

                        }
                        if(is.getTnt()!=null){
                            is.getTnt().move();
                        }
                        if(is.getMysteryChest()!=null){
                            is.getMysteryChest().move();
                        }
                    }
                    for (Coin co: CoinArray){
                        co.move();
                    }
                    if (hero.getCurrentWeapon() instanceof weapon2){
                        weapon2 shuriken = new weapon2(pain);
                        weapon2 shuriken2 = new weapon2(pain);
                    }
                }
                if(keyEvent.getCode()== KeyCode.ESCAPE){
                    label.setLayoutX(35);
                    label.setLayoutY(35);
                    label.setFont(Font.font("Arial", FontWeight.LIGHT,20));
                    label.toBack();
                    label.setTextFill(Color.web("#FFFFFF"));
                    System.out.println("jgh");
                    PauseGame(group);
                    pausemenuButtons.get(0).setOnMouseClicked(e -> ResumeGame());
                    pausemenuButtons.get(1).setOnMouseClicked(e -> {
                        try {
                            SaveGame();
                            label.setText("Game Saved");

                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });
                    pausemenuButtons.get(2).setOnMouseClicked(e -> RestartGame());
                    pausemenuButtons.get(3).setOnMouseClicked(e -> {
                        try {
                            ExitGame();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });
                }
            }
        });
    }

    public void ResumeGame(){
        //unblur bg
        //resume animation
        TranslateTransition trans = new TranslateTransition();
        group.toFront();
        trans.setNode(group);
        trans.setByY(700);
        trans.setRate(3);
        trans.setCycleCount(1);
        trans.play();
        group.toFront();

    }

    public Group CreatePauseGameMenu(){
        Image im= new Image("images/PAUSED (1).png", 400, 550,false,false);
        Text resume = new Text("Resume Game");
        Text save = new Text("Save Progress");
        Text restart = new Text("Restart Game");
        Text mainMenu = new Text("Main Menu");
        pausemenuButtons.add(resume);
        pausemenuButtons.add(save);
        pausemenuButtons.add(restart);
        pausemenuButtons.add(mainMenu);
        resume.setFont(Font.font("Arial", FontWeight.LIGHT,20));
        save.setFont(Font.font("Arial", FontWeight.LIGHT,20));
        restart.setFont(Font.font("Arial", FontWeight.LIGHT,20));
        mainMenu.setFont(Font.font("Arial", FontWeight.LIGHT,20));
        menu.setImage(im);
        pain.getChildren().addAll(menu, resume, save, mainMenu, restart);
        menu.toBack();
        restart.toBack();
        resume.toBack();
        save.toBack();
        mainMenu.toBack();
        Group group = new Group(menu,restart,resume,save,mainMenu);
        pain.getChildren().add(group);
        group.setLayoutX(400);
        group.setLayoutY(760);
        resume.setX(resume.getX()+145);
        resume.setY(resume.getY()+210);
        save.setX(save.getX()+145);
        save.setY(save.getY()+300);
        restart.setX(restart.getX()+145);
        restart.setY(restart.getY()+390);
        mainMenu.setX(mainMenu.getX()+155);
        mainMenu.setY(mainMenu.getY()+480);
        group.toBack();
        return group;
    }

    public void PauseGame(Group gp){
        // blur bg
        // stop animation
        System.out.println("23");
        TranslateTransition trans = new TranslateTransition();
        gp.toFront();
        trans.setNode(gp);
        trans.setByY(-700);
        trans.setRate(3);
        trans.setCycleCount(1);
        trans.play();
        gp.toFront();
    }

    public void RestartGame(){
        setGameHighScore(0);
        getCurrentplayer().setTotalCoins(0);
        location=0;
        loc.setText("Location: "+Integer.toString(location));
        coinCounter.setText(": "+Integer.toString(hero.getCoinsCollected()));
        IslandArray = new ArrayList<FloatingIsland>();
        CoinChestArray = new ArrayList<coinChest>();
        WeaponChestArray = new ArrayList<WeaponChest>();
        OrcsArray = new ArrayList<Orc>();
        TntArray = new ArrayList<TNT>();
        CoinArray = new ArrayList<Coin>();
        pain = new Pane();
        pausemenuButtons = new ArrayList<>();
        group = CreatePauseGameMenu();
        startNewGame();
    }

    public void SaveGame() throws IOException {
        if (Main.savedGamesList.contains(this)) {
            Main.savedGamesList.remove(this);
            Main.savedGamesList.add(this);
        }
        if (Currentplayer.getGamesList().contains(this)) {
            Currentplayer.getGamesList().remove(this);
            Currentplayer.getGamesList().add(this);
        }
        SaveGameMain();
    }

    public void LoadGame() throws IOException {
        loadGameMain(Currentplayer.getPlayerName());
    }

    public static void ExitGame() throws Exception {
        ExitGameMain();
    }

    public long getGameHighScore(){
        return GameScore;
    }

    public void setGameHighScore(long gameHighScore){
        GameScore=gameHighScore;
    }

    public void useCoins(){
    }

    public void won(){

    }

    public void lost(){

    }

    public static void addImage(Pane gridpane, double x, double y, String path, int width, int length){
        ImageView image = new ImageView();
        Image im= new Image(path, width, length,false,false);
        image.setImage(im);
        image.setX(x);
        image.setY(y);
        gridpane.getChildren().add(image);
    }

    public Player getCurrentplayer() {
        return Currentplayer;
    }

//    public void moveGroup(Group group){
//        AnimationTimer move = new AnimationTimer() {
//            int distance = 0;
//            @Override
//            public void handle(long l) {
//                //System.out.println(3);
//                if (distance<90) {
//                    distance += 7;
//                    group.setLayoutX(group.getLayoutX() - 1);
//                    System.out.println(group.getChildren().get(0).getLayoutX());
//                }
//            }
//        };
//        move.start();
//    }



}
