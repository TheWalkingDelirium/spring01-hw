package ru.otus.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.otus.domain.Question;

public class QuestionDaoImpl implements QuestionDao {
    private String filepath;

    public QuestionDaoImpl(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String currentLine;

            while((currentLine = br.readLine()) != null) {
                String[] questionContent = currentLine.split(",");
                Question q = new Question(questionContent[0],
                        Arrays.asList(questionContent[1], questionContent[2], questionContent[3], questionContent[4]),
                        questionContent[1]);
                questions.add(q);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
