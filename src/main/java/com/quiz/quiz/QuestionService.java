package com.quiz.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private List<Question> questions = new ArrayList<>();

    public QuestionService() {
        questions.add(new Question(1, "Mikä on maailman väkirikkain maa?", Arrays.asList("Yhdysvallat", "Intia", "Kiina", "Indonesia"), 2));
        questions.add(new Question(2, "Mikä planeetta on aurinkokunnassamme tunnettu 'punaisena planeettana'?", Arrays.asList("Venus", "Mars", "Jupiter", "Saturnus"), 1));
        questions.add(new Question(3, "Mikä kaupunki tunnetaan valojen kaupunkina?", Arrays.asList("New York", "Pariisi", "Tokio", "Las Vegas"), 1));
        questions.add(new Question(4, "Mikä on jaksollisen järjestelmän ensimmäinen alkuaine?", Arrays.asList("Happi", "Vety", "Helium", "Litium"), 1));
        questions.add(new Question(5, "Kuka kirjoitti näytelmän 'Romeo ja Julia'?", Arrays.asList("William Shakespeare", "Charles Dickens", "Leo Tolstoi", "Mark Twain"), 0));
        questions.add(new Question(6, "Mikä on Japanin pääkaupunki?", Arrays.asList("Kyoto", "Osaka", "Tokio", "Hiroshima"), 2));
        questions.add(new Question(7, "Minä vuonna Titanic upposi?", Arrays.asList("1912", "1905", "1898", "1923"), 0));
    }

    public Question getQuestionById(int id) {
        return questions.stream().filter(question -> question.getId() == id).findFirst().orElse(null);
    }

    public boolean checkAnswer(int questionId, int selectedOptionIndex) {
        Question question = getQuestionById(questionId);
        if (question != null && question.getCorrectAnswerIndex() == selectedOptionIndex) {
            return true;
        }
        return false;
    }
}
