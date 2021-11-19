public QuizAccessor extends CourseAccessor {
    public static final String folder = "data/courses";
    public static final String fileType = ".obj";

    public QuizAccessor() {
        super(folder, fileType);
    }

    public Quiz get(String courseName, String quizName) throws NullPointerException {
        return super.get(courseName).getQuiz(quizName);
    }

    public void save(String courseName, Quiz quiz) {
        super.get(courseName.replace(" ", "-")).removeQuiz(quiz);
        super.modifyQuiz(course);
    }
}
