public class QuizAccessor {
    private static final String folder = "data/courses";
    private static final String fileType = ".obj";
    private ServerDataAccessor serverDataAccessor;

    public QuizAccessor() {
        this.serverDataAccessor = new ServerDataAccessor(folder, fileType);
    }

    public Quiz get(String courseName, String quizName) throws NullPointerException {
        return ((Course) super.get(courseName)).getQuiz(quizName);
    }

    public void save(String courseName, Quiz quiz) {
        super.get(courseName.replace(" ", "-")).removeQuiz(quiz);
        super.modifyQuiz(course);
    }
}
