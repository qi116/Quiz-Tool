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
        super(username, password, false);
        this.quizSubmissions = new ArrayList<Quiz>();
    }

    /**
     * Adds quiz to submission list
     * @param q
     */
    public void addQuizSubmission(Quiz q) {
        quizSubmissions.add(q);
    }
    
    public boolean addNewQuizSubmission(Quiz q) {
        if (getQuiz(q) == null) {
            addQuizSubmission(q);
            return true;
        }
        System.out.println("found quiz with that name");
        return false;
    }
    
    public boolean overwriteQuizSubmission(Quiz q) {
        Quiz qToRemove = getQuiz(q);
        if (qToRemove != null) {
            quizSubmissions.remove(qToRemove);
            addQuizSubmission(q);
            return true;
        }
        return false;
    }

    /**
     * @return ArrayList of all quiz submissions
     */
    public Quiz[] getQuizSubmissions() {
        Quiz[] toReturn = new Quiz[quizSubmissions.size()];
        for (int i = 0; i < toReturn.length; i++)
            toReturn[i] = quizSubmissions.get(i);
        return toReturn;
    }
    
    /**
     * returns a string of all quiz names + attempt numbers
     *  in the form Name #AttemptNumber
     */
    public String[] getQuizIdentifiers() {
        String[] quizNames = new String[quizSubmissions.size()];
        for (int i = 0; i < quizNames.length; i++) {
            quizNames[i] = quizSubmissions.get(i).getIdentifier();
        }
        return quizNames;
    }

    public Quiz getQuiz(Quiz q) {
        for (Quiz qA : quizSubmissions) {
            if (q.getIdentifier().equals(qA.getIdentifier()))
                return qA;
        }
        return null;
    }

    public Quiz getQuiz(String quizIdentifier) {
        for (Quiz qA : quizSubmissions) {
            if (quizIdentifier.equals(qA.getIdentifier()))
                return qA;
        }
        return null;
    }
    
    /**
     * Gets all quiz submissions with name
     * @param quizName name of Quiz
     * @return List of quiz submissions
     */
    public ArrayList<Quiz> getQuizSubmissionsByName(String quizIdentifier) {
        ArrayList<Quiz> toReturn = new ArrayList<>();

        for (Quiz q : quizSubmissions) {
            if (q.getIdentifier().equals(quizIdentifier)) {
                System.out.println("quiz identifier" + q.getIdentifier());
                toReturn.add(q);
            }
        }

        return toReturn;
    }
}
