import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author Nathan Leakeas
 * @version 11.13.2021
 */
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
    
    public void addNewQuizSubmission(Quiz q) {
        if (getQuiz(q) == null)
            addQuizSubmission(q);
    }
    
    public void overwriteQuizSubmission(Quiz q) {
        if (getQuiz(q) != null)
            addQuizSubmission(q);
    }

    /**
     * @return ArrayList of all quiz submissions
     */
    public ArrayList<Quiz> getQuizSubmissions() {
        return quizSubmissions;
    }
    
    /**
     * returns a string of all quiz names + attempt numbers
     *  in the form Name #AttemptNumber
     */
    public String[] getQuizIdentifiers() {
        String[] quizNames = new String[quizSubmissions.size()];
        for (int i = 0; i < quizNames.length; i++)
            quizNames[i] = quizSubmissions.get(i).getIdentifier();
        return quizNames;
    }

    public Quiz getQuiz(String quizName, int attemptNumber) {
        for (Quiz q: quizSubmissions)
            if (q.getName().equals(quizName) && q.getAttempt() == attemptNumber)
                return q;
        return null;
    }

    public Quiz getQuiz(Quiz q) {
        for (Quiz quizAttempt : quizSubmissions)
            if (q.getName().equals(quizAttempt.getName()) && q.getAttempt() == quizAttempt.getAttempt())
                return q;
        return null;
    }

    /**
     * Gets all quiz submissions with name
     * @param quizName name of Quiz
     * @return List of quiz submissions
     */
    public ArrayList<Quiz> getQuizSubmissionsByName(String quizName) {
        ArrayList<Quiz> toReturn = new ArrayList<>();

        for (Quiz q : quizSubmissions) {
            if (q.getName().equals(quizName)) {
                toReturn.add(q);
            }
        }

        return toReturn;
    }
}
