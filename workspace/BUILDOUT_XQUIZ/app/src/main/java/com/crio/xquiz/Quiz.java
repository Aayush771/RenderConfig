package com.crio.xquiz;
 
import java.util.*;

public class Quiz{
    private String quizName;
    private List<Question> questions;
    private int finalScore;

    // TODO: Implement Quiz class constructor : public Quiz(String quizName);
        // Validate: If quizName is null or empty, 
                // print “Quiz Name cannot be null or empty!”
                // Note: The statement to be printed should be exactly same.
        // else
                // initialise the quizName with the string taken as input in this constructor
                // initialise the questions to a new and empty arraylist
                // initialise the finalScore to Zero
        

    //TODO: Create the getter method : public String getQuizName(), which returns the quizname

    //TODO: Create the getter method :public List<Question> getQuestions(), which returns the questions list

    //TODO: Create the getter method : public int getFinalScore(), which returns the final score

    //TODO: Create addQuestion() method:  public void addQuestion(Question question)
        // Validate: If question is Null, print “Question cannot be null!”.
         // If question is not null then add the question to list of questions
         public Quiz(String quizName) {
            if(quizName == null || quizName.length() == 0) System.out.println("Quiz Name cannot be null or empty!");
            else {
                this.quizName = quizName;
                this.questions = new ArrayList<>();
                this.finalScore = 0;
            }
            
        }
        

    public String getQuizName() {
            return quizName;
        }


        public void setQuizName(String quizName) {
            this.quizName = quizName;
        }


        public List<Question> getQuestions() {
            return questions;
        }


        public void setQuestions(List<Question> questions) {
            this.questions = questions;
        }


        public int getFinalScore() {
            return finalScore;
        }


        public void setFinalScore(int finalScore) {
            this.finalScore = finalScore;
        }
        public void addQuestion(Question question){
            if(question == null) System.out.println("Question cannot be null!");
            else this.questions.add(question);
        }

    public void attemptQuiz(){
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<questions.size();++i){ //Iterates over the list of Questions
            questions.get(i).display(); //Displays the question along with the list of choices
            int opt = sc.nextInt(); //Takes in the user input and stores it in integer variable `opt`
            if(opt<1 || opt>questions.get(i).getChoices().size()){ 
                //checks if the choice provided by user does not lie in the range of the list of choices
                System.out.println("Please choose a number between 1 and " + questions.get(i).getChoices().size());
                // asks the user to choose a number between given range
                i--; // the value of i is decremented so that the previous question comes again in the loop and is printed
            } else if (questions.get(i).checkAnswer(questions.get(i).getChoices().get(opt-1))){
                //else if option lies within the range, then it is checked whether the provided answer is correct
                // if provided answer is correct then the finalScore value is incremented by 1
                finalScore++;
            }
        }
        sc.close(); //after all the questions are attempted Scanner class instance is closed.
    }
    
 

    public void revealAnswerKey(){
        // TODO: add loop for printing all the questions along with answers in the questions list.
        List<Question> ques = getQuestions();
        for(int i = 0; i<ques.size();i++)
        {
            System.out.println("Question no. " + (i+1) + " : " + questions.get(i).getQuestionText() +
                    "\nAnswer no. " + (i+1) + " : " + questions.get(i).getAnswer());
        }
    }
 

}

