package ru.otus.service;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;

public class QuizServiceImpl implements QuizService {
    private static final String NAME_PATTERN = "[A-Z]*[a-z]*";
    private final QuestionDao questionDao;
    private List<Question> questions;
    private String name;
    private String surname;
    private int score;

    public QuizServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void testUser() {
        Random r = new Random();
        questions = questionDao.getQuestions();

        System.out.println("Stop!");

        System.out.println("What is your name: ");
        String qq;
        Scanner scanner = new Scanner(System.in);

        do {
            name = scanner.nextLine();
        }
        while (!validateName(name));

        System.out.println("What is your surname: ");

        do {
            surname = scanner.nextLine();
        }
        while (!validateName(surname));

        System.out.println("Who would cross the Bridge of Death must answer me these questions five, 'ere the other side he see.");

        for(Question q: questions) {
            System.out.println(q.getQuestion());
            System.out.println("Please provide the answer [1-4]");

            String[] options = (String[]) q.getOptions().toArray();

            // options shuffle
            for (int i = 0; i < 2; i++) {
                int index1 = r.nextInt(4);
                int index2 = r.nextInt(4);
                String t = options[index1];
                options[index1] = options[index2];
                options[index2] = t;
            }

            for (int i = 0; i < 4; i++) {
                System.out.println(i + ": " + options[i]);
            }

            int choice = 0;

            do {
                System.out.print("Your answer: ");
                String choiceS = scanner.nextLine();
                choice = Integer.parseInt(choiceS);
            } while (choice < 0 || choice > 3);

            if (options[choice].equals(q.getCorrectAnswer())) {
                System.out.println("correct");
                score++;
            } else {
                System.out.println("incorrect");
            }
        }

        System.out.println("User: " + name + surname + ", result: " + score);
    }

    private boolean validateName(String name) {
        return !name.isEmpty() && name.matches(NAME_PATTERN);
    }
}
