package com.crio.xquiz;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for Question class")
public class QuestionTest {

    // TODO: WARNING!!!
    //  DO NOT MODIFY ANY FILES IN THE TESTS/ ASSESSMENTS UNLESS ASKED TO.
    //  Any modifications in this file may result in Assessment failure!

    @Test
    @DisplayName("Test for checking the functionality of Question constructor if the question text is null")
    public void testQuestion_IfQuestionTextIsNull(){
        //arrange
        final PrintStream standardOut = System.out;
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String text = null;
        List<String> choices = new ArrayList<>();
        choices.add("Paris");
        choices.add("Bareilly");
        choices.add("Amsterdam");
        choices.add("Rome");

        //act
        new Question(text, choices, "Paris");

        //assert
        assertEquals( "Question text cannot be null or empty!", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Test for checking the functionality of Question constructor if the Choices list is empty")
    public void testQuestion_IfChoicesIsEmpty(){
        //arrange and act
        final PrintStream standardOut = System.out;
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String text = "What is the capital of France?";
        List<String> choices = new ArrayList<>();

        //act
        new Question(text, choices, "Paris");

        //assert
        assertEquals( "Choices cannot be null or empty!", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }
    @Test
    @DisplayName("Test for checking the functionality of Question constructor if the answer is an empty string")
    public void testQuestion_IfAnswerIsEmpty() {
        //arrange
        final PrintStream standardOut = System.out;
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String text = "What is the capital of France?";
        List<String> choices = new ArrayList<>();
        choices.add("Paris");
        choices.add("Bareilly");
        choices.add("Amsterdam");
        choices.add("Rome");

        //act
        new Question(text, choices, "");

        //assert
        assertEquals( "Answer cannot be null or empty!", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Test for checking the functionality of Question constructor if the answer is null")
    public void testQuestion_IfAnswerIsNull() {
        //arrange
        final PrintStream standardOut = System.out;
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String text = "What is the capital of France?";
        List<String> choices = new ArrayList<>();
        choices.add("Paris");
        choices.add("Bareilly");
        choices.add("Amsterdam");
        choices.add("Rome");

        //act
        new Question(text, choices, null);

        //assert
        assertEquals( "Answer cannot be null or empty!", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }
    @Test
    @DisplayName("Test for checking the functionality of Question constructor if the answer is not present in the choices")
    public void testQuestion_IfAnswerNotPresentInChoices() {
        //arrange
        final PrintStream standardOut = System.out;
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String text = "What is the capital of France?";
        List<String> choices = new ArrayList<>();
        choices.add("Washington DC");
        choices.add("Bareilly");
        choices.add("Amsterdam");
        choices.add("Rome");

        //act
        new Question(text, choices, "Paris");

        //assert
        assertEquals( "Answer is not present among the choices!", outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Test for checking the functionality of checkAnswer if the UserResponse is correct")
    public void testCheckAnswer_CorrectAnswer(){
        //arrange
        String text = "What is the capital of France?";
        List<String> choices = new ArrayList<>();
        choices.add("Paris");
        choices.add("Bareilly");
        choices.add("Amsterdam");
        choices.add("Rome");
        Question q = new Question(text, choices, "Paris");

        //act and assert
        assertEquals(true, q.checkAnswer("Paris"));
    }

    @Test
    @DisplayName("Test for checking the functionality of checkAnswer if the UserResponse is incorrect")
    public void testCheckAnswer_IncorrectAnswer(){
        //arrange
        String text = "What is the capital of France?";
        List<String> choices = new ArrayList<>();
        choices.add("Paris");
        choices.add("Bareilly");
        choices.add("Amsterdam");
        choices.add("Rome");
        Question q = new Question(text, choices, "Paris");

        //act and assert
        assertEquals(false, q.checkAnswer("Rome"));
    }

    @Test
    @DisplayName("Test for checking the functionality of getChoices() method if it returns the correct list of choices")
    public void testGetChoices(){
        //arrange
        String text = "What is the capital of France?";
        List<String> choices = new ArrayList<>();
        choices.add("Paris");
        choices.add("Bareilly");
        choices.add("Amsterdam");
        choices.add("Rome");
        Question q = new Question(text, choices, "Paris");

        List<String> list = new ArrayList<>();
        list.add("Paris");
        list.add("Bareilly");
        list.add("Amsterdam");
        list.add("Rome");

        //act and assert
        assertEquals(list, q.getChoices());
    }

    @Test
    @DisplayName("Test for checking the functionality of getAnswer() method if it returns the correct answer of a question")
    public void testGetAnswer(){
        //arrange
        String text = "What is the capital of France?";
        List<String> choices = new ArrayList<>();
        choices.add("Paris");
        choices.add("Bareilly");
        choices.add("Amsterdam");
        choices.add("Rome");
        Question q = new Question(text, choices, "Paris");

        //act and assert
        assertEquals("Paris", q.getAnswer());
    }

    @Test
    @DisplayName("Test for checking the functionality of getQuestionText() method if it returns the correct question text")
    public void testGetQuestionText(){
        //arrange
        String text = "What is the capital of France?";
        List<String> choices = new ArrayList<>();
        choices.add("Paris");
        choices.add("Bareilly");
        choices.add("Amsterdam");
        choices.add("Rome");
        Question q = new Question(text, choices, "Paris");

        //act and assert
        assertEquals("What is the capital of France?", q.getQuestionText());
    }
}

