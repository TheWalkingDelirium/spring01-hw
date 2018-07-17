package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.otus.service.QuizService;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizService quizService = context.getBean(QuizService.class);
        quizService.testUser();
    }
}
