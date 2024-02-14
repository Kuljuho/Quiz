package com.quiz.quiz;

public class Player {
    private String nickname;
    private int playerID;
    private String gameHistory;
    private boolean currentGame;
    private int score;

    public Player() {

    }

    public Player(String nickname, int playerID, String gameHistory, boolean currentGame, int score) {
        this.nickname = nickname;
        this.playerID = playerID;
        this.gameHistory = gameHistory;
        this.currentGame = currentGame;
        this.score = score;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPlayerID() {
        return this.playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getGameHistory() {
        return this.gameHistory;
    }

    public void setGameHistory(String gameHistory) {
        this.gameHistory = gameHistory;
    }

    public boolean isCurrentGame() {
        return this.currentGame;
    }

    public void setCurrentGame(boolean currentGame) {
        this.currentGame = currentGame;
    }

    public boolean getCurrentGame() {
        return this.currentGame;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
