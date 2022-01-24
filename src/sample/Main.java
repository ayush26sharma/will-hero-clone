package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Main extends Application implements Serializable{
    transient static Stage window;
    private static Scene scene;
    private Scene scene2;
    private Scene scene3;
    
    transient private ArrayList<ImageView> mainscreenbuttons;
    transient static ArrayList<Game> savedGamesList;
    transient static ArrayList<Player> playersList;
    private final static ArrayList<String> playerNames = new ArrayList<>();
    private final static ArrayList<String> savedPlayertxt=new ArrayList<>();


//    public void startNew() throws IOException {
//        System.out.println("asad");

    public Main() {
        this.mainscreenbuttons = new ArrayList<>();
        savedGamesList = new ArrayList<>();
        playersList = new ArrayList<>();
    }
//        Game g = new Game();
//        g.startNewGame();
//        System.out.println("aa");
//
//    }
    public static ArrayList<Game> getSavedGamesList() {
    return savedGamesList;
}

    public void addImage(Pane gridpane, double x, double y, String path, int width, int length){
        ImageView image = new ImageView();
        Image im= new Image(path, width, length,false,false);
        image.setImage(im);
        image.setX(x);
        image.setY(y);
        gridpane.getChildren().add(image);
        mainscreenbuttons.add(image);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(5);
        window = primaryStage;
        window.setTitle("Will Hero");
        window.setResizable(false);
        Pane pain = new Pane();
        scene = new Scene(pain, 1200, 670);
        Image poster = new Image("images/poster.jpg");
        BackgroundSize size = new BackgroundSize(1200, 670, false, false, false, false);
        BackgroundImage bg = new BackgroundImage(poster, BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background background = new Background(bg);
        addImage(pain, 120, 500,"images/newG.png", 250, 50);
        addImage(pain, 480, 500,"images/load.png", 250, 50);
        addImage(pain, 840, 500,"images/exit.png", 250, 50);
        pain.setBackground(background);
        Image icon = new Image("ab.jpg");
        window.getIcons().add(icon);
        System.out.println("hii");
        mainscreenbuttons.get(0).setOnMouseClicked(e->{
            System.out.println("hi");
            EnterPlayerName();
        });
        mainscreenbuttons.get(1).setOnMouseClicked(e->{
            Load();
        });
        mainscreenbuttons.get(2).setOnMouseClicked(e -> Platform.exit());
        System.out.println("ksdb");
        window.setScene(scene);
        window.show();
    }

    private void Load() {
        Image poster = new Image("images/loadingpage1.png");
        BackgroundSize size = new BackgroundSize(1200, 670, false, false, false, false);
        BackgroundImage bg = new BackgroundImage(poster, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background background = new Background(bg);
        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        Image image = new Image("images/backArrow1.png", 35, 35, false, false);
        ImageView back = new ImageView(image);
        grid.getChildren().add(back);
        back.setX(30);
        back.setY(40);
        HashMap<Label, String> playerLabels = new HashMap<>();
        for (int i = 0; i < savedPlayertxt.size(); i++) {
            String name = savedPlayertxt.get(i);
            String txt = ".txt";
            Label l = new Label((i + 1) + ". " + name);
            l.setFont(Font.font("Arial", FontWeight.BLACK, 25));
            playerLabels.put(l, name);
            grid.add(l, 20, 12 + 2 * i);
        }
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("d1");
                Iterator<Map.Entry<Label, String>> hi=playerLabels.entrySet().iterator();
                while(hi.hasNext()){
                    Map.Entry element=(Map.Entry)hi.next();
                    Label le=(Label)element.getKey();
                    le.setOnMouseClicked((e -> {
                        System.out.println("d2");
                        try {
                            deserialize((String)element.getValue());
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }));
                }
            }
        });
        back.setOnMouseClicked((e -> {
            try {
                window.setScene(scene);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));
        grid.setBackground(background);
        scene3 = new Scene(grid, 1200, 670);
        Main.window.setScene(scene3);
        Main.window.show();
    }

    public void EnterPlayerName(){
        Image poster = new Image("images/namebg.jpg");
        BackgroundSize size = new BackgroundSize(1200, 670, false, false, false, false);
        BackgroundImage bg = new BackgroundImage(poster, BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        Background background = new Background(bg);
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        Label lplayer= new Label("Enter Player Name:");
        Label rlabel=new Label();
        lplayer.setFont(Font.font("Arial", FontWeight.LIGHT,30));
        rlabel.setFont(Font.font("Arial", FontWeight.LIGHT,20));
        grid.add(lplayer,0,1);
        TextField getName=new TextField();
        getName.setFont(Font.font("Arial", FontWeight.LIGHT,30));
        grid.add(getName,1,1);
        grid.add(rlabel,1,2);

        Image image= new Image("images/submitButton.png");
        ImageView submit = new ImageView(image);
        grid.add(submit,1,3);

        Image image2= new Image("images/backButton1.png");
        ImageView back = new ImageView(image2);
        grid.add(back,0,3);

        submit.setOnMouseClicked((e->{
            if (getName.getText().isEmpty()){
                rlabel.setText("Player name must be entered");
            }
            else if(Arrays.asList(savedPlayertxt).contains(getName.getText().trim())){
                rlabel.setText("Player name already exists");
            }
            else{
                Player currentPlayer = new Player(getName.getText());
                Game game = new Game(currentPlayer);
                currentPlayer.setGame(game);
                savedGamesList.add(game);
                game.startNewGame();
            }
            System.out.println(savedPlayertxt);
        }));

        back.setOnMouseClicked((e->{
            try {
                window.setScene(scene);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));

        scene2 = new Scene(grid, 1200, 670);
        grid.setBackground(background);
        Main.window.setScene(scene2);
        Main.window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void serialize(Game game) throws IOException {
        ObjectOutputStream out=null;
        String player=game.getCurrentplayer().getPlayerName()+".txt";
        try{
            out=new ObjectOutputStream(new FileOutputStream(player));
            out.writeObject(game);
            if(!savedPlayertxt.contains(game.getCurrentplayer().getPlayerName())) {
                savedPlayertxt.add(game.getCurrentplayer().getPlayerName());
            }
            System.out.println("serialised succesful");

        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (Exception e){
//            throw new RuntimeException("Serialization Error. Path to bad objects: "+out.getStack(),e);
//        }
        finally{
            out.close();
        }
    }

    public static void deserialize(String player) throws IOException{
        ObjectInputStream in=null;
        String playername=player+".txt";
        try{
            in=new ObjectInputStream(new FileInputStream(playername));
            System.out.println("Deserialized "+player);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            in.close();
        }
    }

    public static void SaveGameMain() throws IOException {
        Game currentGame=savedGamesList.get(savedGamesList.size()-1);
        serialize(currentGame);

    }

    public static void loadGameMain(String name) throws IOException{
        deserialize(name);
    }

    public static void ExitGameMain(){
        Game.pain.getChildren().remove(Game.pain.getChildren().get(Game.pain.getChildren().size()-1));
        Game.pain.getChildren().get(Game.pain.getChildren().size()-1).toBack();
        window.setScene(scene);
    }

}

