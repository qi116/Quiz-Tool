import java.util.ArrayList;

public class Teacher extends Account{
    public ArrayList<Quiz> quizzes;

    public Teacher(String username) {
        super(username);
        this.quizzes = new ArrayList<Quiz>();

    }
}
