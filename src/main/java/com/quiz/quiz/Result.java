package com.quiz.quiz;

public class Result {
    private String playerName;
    private int gameNumber;
    private String result;
    private int score; 

    public Result(String playerName, int gameNumber, String result, int score) {
        this.playerName = playerName;
        this.gameNumber = gameNumber;
        this.result = result;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
