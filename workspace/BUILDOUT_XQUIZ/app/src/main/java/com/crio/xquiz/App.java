
package com.crio.xquiz;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args){

        Quiz quiz1 = new Quiz("General Knowledge Test");
        String text1 = "What was the original name of the Java language?";
        List<String> choices1 = new ArrayList<>();
        choices1.add("*7");
        choices1.add("Duke");
        choices1.add("Oak");
        choices1.add("Gosling");
        Question first = new Question(text1, choices1, "Oak");
        quiz1.addQuestion(first);

        String text2 = "In which country was the Java language born?";
        List<String> choices2 = new ArrayList<>();
        choices2.add("Australia");
        choices2.add("Canada");
        choices2.add("Denmark");
        choices2.add("USA");
        Question second = new Question(text2, choices2, "Canada");
        quiz1.addQuestion(second);

        quiz1.attemptQuiz();
        System.out.println("Your final score: " + quiz1.getFinalScore());

        System.out.println("List of all the answers: ");
        quiz1.revealAnswerKey();

    }
}

