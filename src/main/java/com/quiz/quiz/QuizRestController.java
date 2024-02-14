package com.quiz.quiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizRestController {

    private final GameTimer gameTimer = new GameTimer();

    @Autowired
    private PlayerService playerService;

    @Autowired
    private QuestionService questionService;

    // Kotisivu
    @GetMapping("/")
    public String homepage() {
        return "Tervetuloa Tietovisaan! Vieraile /info ohjeita varten.";
    }

    // Infosivu
    @GetMapping("/info")
    public String info() { 
        return "Näin peli toimii:\n\n" +
        "- Aloita peli vierailemalla osoitteessa '/start'. Tämä käynnistää kellon ja ensimmäinen kysymys esitetään.\n" +
        "- Vastaa kysymyksiin lähettämällä POST-pyyntö osoitteeseen '/submitAnswer' kysymyksen id:llä ja valitsemallasi vaihtoehdolla.\n" +
        "- Saat lisää kysymyksiä osoitteessa '/question/id'" +
        "- Voit tarkistaa jäljellä olevan ajan vierailemalla osoitteessa '/time'.\n" +
        "- Voit lopettaa pelin milloin tahansa vierailemalla osoitteessa '/stop'. \n\n" +
        "- Jokainen oikein vastattu kysymys lisää pisteitä pistemäärääsi.";
    }

    // Pelaaja rekisteröityy asettamalla nimimerkin. ID, pelihistoria ja status asetetaan automaattisesti.
    @PostMapping("/register")
    public Player registerPlayer(@RequestBody Player player) {
        System.out.println("Registering player: " + player.getNickname());
        return playerService.addPlayer(player.getNickname());
    }

    // Rekisteröityneet pelaajat listattuna.
    @GetMapping("/players")
    public List<Player> getPlayers() {
        return playerService.getPlayers();
    }

    // Peli käynnistyy tästä. Ensimmäinen kysymys tulee ruutuun. Myös sekuntikello alkaa juoksemaan konsolissa.
    @GetMapping("/start")
    public ResponseEntity<QuestionA> startGame() {
        gameTimer.startGame();
        Question question = questionService.getQuestionById(1);
        if (question != null) {
            QuestionA questionA = new QuestionA(question.getId(), question.getQuestionText(), question.getOptions());
            return new ResponseEntity<>(questionA, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Seuraavat kysymykset tulee id:n avulla QuestionService -luokasta
    @GetMapping("/question/{id}")
    public ResponseEntity<QuestionA> getQuestion(@PathVariable int id) {
    Question question = questionService.getQuestionById(id);
    if (question != null) {
        QuestionA questionA = new QuestionA(question.getId(), question.getQuestionText(), question.getOptions());
        return new ResponseEntity<>(questionA, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

    // Tällä vastataan kysymyksiin, vaatii kysymyksen id:n sekä vastauksen indeksin. Konsolissa näkyy oliko vastaus oikein vai väärin.
    // Kun kaikkiin kysymyksiin on vastattu oikein, kello pysähtyy ja pistemäärä tulee ruutuun.
    @PostMapping("/submitAnswer")
    public ResponseEntity<String> submitAnswer(@RequestBody Answer answer) {
    boolean isCorrect = questionService.checkAnswer(answer.getQuestionId(), answer.getSelectedOptionIndex());
    Player currentPlayer = playerService.getCurrentPlayer();

    if (isCorrect) {
        currentPlayer.setScore(currentPlayer.getScore() + 1);
        playerService.updatePlayer(currentPlayer);

        if (answer.getQuestionId() == 7) {
            gameTimer.stopGame();
            return new ResponseEntity<>("Correct answer! You've completed the quiz with a score of " + currentPlayer.getScore() + ".", HttpStatus.OK);
        }

        return new ResponseEntity<>("Correct answer!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Incorrect answer. Try again!", HttpStatus.BAD_REQUEST);
        }
    }

    // Tällä näkee kesken pelin kuluneen ajan.
    @GetMapping("/time")
    public String getTime() {
        return gameTimer.getElapsedTime();
    }

    // Peli päättyy, kello pysähtyy.
    @GetMapping("/stop")
    public String stopGame() {
        gameTimer.stopGame();
        return "Game has stopped.";
    }
    }
