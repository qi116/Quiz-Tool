import java.util.ArrayList;

public class Student extends Account{
    private ArrayList<Quiz> quizSubmissions;

    public Student(String username, String password) {
        super(username, password);
        this.quizSubmissions = new ArrayList<Quiz>();
    }

    public void addQuizSubmission(Quiz q) {
        quizSubmissions.add(q);
    }

    public ArrayList<Quiz> getQuizSubmissions() {
        return quizSubmissions;
    }
}
