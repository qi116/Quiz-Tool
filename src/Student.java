import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Student extends Account implements Serializable {
    private ArrayList<Quiz> quizSubmissions;

    public Student(String username, String password) {
        super(username, password);
        this.quizSubmissions = new ArrayList<Quiz>();
    }

    /**
     * Adds quiz to submission list
     * @param q
     */
    public void addQuizSubmission(Quiz q) {
        quizSubmissions.add(q);
    }

    /**
     * @return ArrayList of all quiz submissions
     */
    public ArrayList<Quiz> getQuizSubmissions() {
        return quizSubmissions;
    }

    /**
     * Gets all quiz submissions with name
     * @param quizName name of Quiz
     * @return List of quiz submissions
     */
    public ArrayList<Quiz> getQuizSubmissionByName(String quizName) {
        ArrayList<Quiz> toReturn = new ArrayList<>();

        for (Quiz q : quizSubmissions) {
            if (q.getName().equals(quizName)) {
                toReturn.add(q);
            }
        }

        return toReturn;
    }
}