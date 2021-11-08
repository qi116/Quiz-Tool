import java.util.ArrayList;

public class Student extends Account{
    private ArrayList<Quiz> quizSubmissions;

    public Student(String username) {
        super(username);
        this.quizSubmissions = new ArrayList<Quiz>();
    }

    public addQuizSubmission(Quiz q) {
        quizSubmissions.add(q);
    }

    public ArrayList<Quiz> getQuizSubmissions() {
        return quizSubmissions;
    }
}
