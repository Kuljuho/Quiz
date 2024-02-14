package com.quiz.quiz;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private List<Player> players = new ArrayList<>();
    private List<Result> results = new ArrayList<>();
    private int nextPlayerID = 1;
    private Player currentPlayer;

    public Player addPlayer(String nickname) {
        Player newPlayer = new Player(nickname, nextPlayerID++, "none", false, 0);
        players.add(newPlayer);
        this.currentPlayer = newPlayer;
        System.out.println("Added player: " + nickname + ", total players: " + players.size());
        return newPlayer;
    }

    public Result createResultForActivePlayer(String resultDescription, int gameNumber) {
        if (players.isEmpty() || currentPlayer == null) {
            return null;
        }
        Result result = new Result(currentPlayer.getNickname(), gameNumber, resultDescription, currentPlayer.getScore());
        results.add(result);
        return result;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void updateScore(int score) {
        if (this.currentPlayer != null) {
            this.currentPlayer.setScore(this.currentPlayer.getScore() + score);
        }
    }

    public void updatePlayer(Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerID() == player.getPlayerID()) {
                players.set(i, player);
                break;
            }
        }
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void resetCurrentPlayer() {
        this.currentPlayer = null;
    }
}
