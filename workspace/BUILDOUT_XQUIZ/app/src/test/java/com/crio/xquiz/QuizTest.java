package com.crio.xquiz;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class QuizTest {

    // TODO: WARNING!!!
    //  DO NOT MODIFY ANY FILES IN THE TESTS/ ASSESSMENTS UNLESS ASKED TO.
    //  Any modifications in this file may result in Assessment failure!

    @Test
    @DisplayName("Test for checking the functionality of Quiz constructor if quizName is null")
    public void testQuiz_IfQuizNameIsNull(){
        //arrange
        final PrintStream standardOut = System.out;
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String quizName = null;

        //act
        new Quiz(quizName);

        //assert
        assertEquals( "Quiz Name cannot be null or empty!", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Test for checking the functionality of addQuestion if the question is null")
    public void testAddQuestion_IfQuestionIsNull(){
        //arrange
        final PrintStream standardOut = System.out;
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String quizName = "General Knowledge Quiz";
        Quiz quiz = new Quiz(quizName);

        //act
        quiz.addQuestion(null);

        //assert
        assertEquals( "Question cannot be null!", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Test for checking the functionality of addQuestion if a correct question is added")
    public void testAddQuestion_IfQuestionIsCorrect(){
        //arrange
        String quizName = "General Knowledge Quiz";
        Quiz quiz = new Quiz(quizName);
        String text1 = "What is the capital of France?";
        List<String> choices1 = new ArrayList<>();
        choices1.add("Paris");
        choices1.add("Bareilly");
        choices1.add("Amsterdam");
        choices1.add("Rome");
        Question q1 = new Question(text1, choices1, "Paris");

        String text2 = "What is the capital of Germany?";
        List<String> choices2 = new ArrayList<>();
        choices2.add("Paris");
        choices2.add("Berlin");
        choices2.add("Amsterdam");
        choices2.add("Rome");
        Question q2 = new Question(text2, choices2, "Berlin");

        //act and assert
        quiz.addQuestion(q1);
        assertEquals( 1, quiz.getQuestions().size());
        assertEquals("What is the capital of France?", quiz.getQuestions().get(0).getQuestionText());

        quiz.addQuestion(q2);
        assertEquals(2, quiz.getQuestions().size());
        assertEquals("What is the capital of Germany?", quiz.getQuestions().get(1).getQuestionText());

    }

    @Test
    @DisplayName("Test for checking the functionality of the revealAnswerKey method if the output is printed in the expected format.")
    public void testRevealAnswerKey(){
        //arrange
        final PrintStream standardOut = System.out;
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String quizName = "General Knowledge Quiz";
        Quiz quiz = new Quiz(quizName);
        String text1 = "What is the capital of France?";
        List<String> choices1 = new ArrayList<>();
        choices1.add("Paris");
        choices1.add("Bareilly");
        choices1.add("Amsterdam");
        choices1.add("Rome");
        Question q1 = new Question(text1, choices1, "Paris");
        quiz.addQuestion(q1);

        //act
        quiz.revealAnswerKey();

        //assert
        assertEquals("Question no. 1 : What is the capital of France?\nAnswer no. 1 : Paris"
                , outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Test for checking the functionality of getQuestions() method if the method returns correct list of questions")
    public void testGetQuestions(){
        //arrange
        String quizName = "General Knowledge Quiz";
        Quiz quiz = new Quiz(quizName);
        String text1 = "What is the capital of France?";
        List<String> choices1 = new ArrayList<>();
        choices1.add("Paris");
        choices1.add("Bareilly");
        choices1.add("Amsterdam");
        choices1.add("Rome");
        Question q1 = new Question(text1, choices1, "Paris");
        quiz.addQuestion(q1);

        List<Question> list = new ArrayList<>();
        list.add(q1);

        //act and assert
        assertIterableEquals(list, quiz.getQuestions());
    }

    @Test
    @DisplayName("Test for checking the functionality of getFinalScore() method if it returns the correct final score")
    public void testGetFinalScore(){
        //arrange
        String quizName = "General Knowledge Quiz";
        Quiz quiz = new Quiz(quizName);
        String text1 = "What is the capital of France?";
        List<String> choices1 = new ArrayList<>();
        choices1.add("Paris");
        choices1.add("Bareilly");
        choices1.add("Amsterdam");
        choices1.add("Rome");
        Question q1 = new Question(text1, choices1, "Paris");
        quiz.addQuestion(q1);

        //act and assert
        String mockUserAnswer = "1";
        InputStream in = new ByteArrayInputStream(mockUserAnswer.getBytes());
        System.setIn(in);
        assertEquals(0, quiz.getFinalScore());
        quiz.attemptQuiz();
        assertEquals(1, quiz.getFinalScore());
    }

    @Test
    @DisplayName("Test for checking the functionality of getQuizName() method if the method returns correct quizName")
    public void testGetQuizName(){
        //arrange
        String quizName = "General Knowledge Quiz";
        Quiz quiz = new Quiz(quizName);

        //act and assert
        assertEquals("General Knowledge Quiz", quiz.getQuizName());
    }
}

