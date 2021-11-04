package src;

public class test {
    public static void main(String[] args) {
        Question q1 = new Question("What is your name?");
        String[] choices1 = {"Bill", "John"};
        q1.setChoices(choices1);

        Question q2 = new Question("What is your age?");
        String[] choices2 = {"3", "4"};
        q2.setChoices(choices2);

        Question[] quizList = {q1, q2};
        Quiz quiz = new Quiz("Quiz 1", quizList);
        quiz.answerQuestion(1, 1);
        //System.out.println(quiz.getQuestion(1));
        quiz.gradeQuestion(1, 100);
        System.out.println(quiz.toStringPostTake());


    }
}
