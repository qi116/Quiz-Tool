public abstract class AccessData {
    //accounts
    public Account getAccountData(String fileName);
    public void saveAccountData(String fileName,boolean isStudent, Account account);

    //quizzes
    public void addQuiz(String fileName, Quiz quiz);
    public void modifyQuiz(String fileName, Quiz quiz);
    public void removeQuiz(String fileName);

    public void saveTakenQuiz(String username, String quizName, char[] answers);
}
