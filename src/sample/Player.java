package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private String PlayerName;
    private Game Currentgame;
    private long PlayerHighScore;
    private Will currentHero;
    private int currentScore;
    private int totalCoins;

    private final ArrayList<Game> gamesList = new ArrayList<>();

    public Game getGame() {
        return Currentgame;
    }

    public void setGame(Game game) {
        this.Currentgame = game;
    }

    public long getPlayerHighScore() {
        return PlayerHighScore;
    }

    public void setPlayerHighScore(long playerHighScore) {
        PlayerHighScore = playerHighScore;
    }

    public Will getCurrentHero() {
        return currentHero;
    }

    public void setCurrentHero(Will currentHero) {
        this.currentHero = currentHero;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public ArrayList<Game> getGamesList() {
        return gamesList;
    }

    public Player(String playerName) {
        PlayerName = playerName;
    }

    public void ResumeGame(){

    }

    public void PauseGame(){

    }

    public void RestartGame(){

    }

    public void SaveGame(){

    }

    public void loadGame(){

    }

    public void exitGame(){

    }

    public void StartGame(){

    }

    public void newGame(){

    }

    public void resurrectHero(){

    }

    public int getTotalCoins(){
        return totalCoins;
    }

    public void setTotalCoins(int totalCoins1){
        totalCoins=totalCoins1;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }
}
