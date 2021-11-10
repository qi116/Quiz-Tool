/**
 * @author Hawkins Peterson
 * @version 11.03.21
 * 
 * Anything that stores or gets data in our project
 */
public abstract class AccessData {
    /**
     * verifies the username and password of the user and then returns an account object corosponding to that user
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @return an account object corosponding to the username and password given 
     */
    public Account getAccountData(String username, String password) throws NullPointerException {
        try {
            Acount account = (Account) getObjectFromFile("accounts/" + username);
            if (account.getPassword().equals(password))
                return account;
            else
                throw new NullPointerException("No account with that username / password combination");
        } catch (FileNotFoundException e) {
            throw new NullPointerException("No account with that username / password combination");
        }
    }
    /**
     * saves a user object using the account name
     * @param account the account to be added
     */
    public void writeAccountData(Account account) {
        writeObjectToFile("accounts/" + account.getUsername(), account);
    }

    public Quiz getQuiz(String quizName) throws NullPointerException {
        try {
            return (Quiz) getObjectFromFile("quizes/" + quizName.replace(" ","-"));
        } catch (FileNotFoundException e) {
            throw new NullPointerException("A quiz with that name does not exist");
        }
    }
    /**
     * allows a teacher to create a quiz
     * @param quiz the quiz object (uses the toString method mainly)
     */
    public void addQuiz(Quiz quiz) throws FileAlreadyExistsException {
        try {
            new File("quizes/"+quiz.getName().replace(" ","-"));
            throw new Exception("File already exists");
        } catch (FileNotFoundException e) {
            writeObjectToFile("quizes/" + quiz.getName().replace(" ","-"), quiz);
        }
    }

    /**
     * Modifies an already existing quiz
     * @param quizName the name of the quiz
     * @param quiz the quiz object (uses the toString mainly)
     */
    public void modifyQuiz(String quizName, Quiz quiz) throws NullPointerException {
        try {
            new File("quizes/"+quiz.getName().replace(" ","-"));
            writeObjectToFile("quizes/" + quiz.getName().replace(" ","-"), quiz);
        } catch (FileNotFoundException e) {
            throw new Exception("File already exists");
        }
    }

    /**
     * Removes a quiz with name quizName
     * @param quizName the name of the quiz
     */ 
    public void removeQuiz(String quizName) throws NullPointerException {
        try {
            File f = new File("quizes/" + quiz.getName().replace(" ","-"));
            f.delete();
        } catch (FileNotFoundException e) {
            throw new NullPointerException("Quiz does not exist");
        }
    }

    private Object getObjectFromFile(String fileName) throws FileNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(new File("data/" + fileName + ".obj")));
            Object toReturn = ois.readObject();
            ois.close();
            return toReturn;
        } catch (IOException e) {
            System.out.println("error initalizing file output stream");
        }
    }
    
    private void writeObjectToFile(String fileName, Object o) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(new File("data/" + fileName + ".obj")));
            oos.writeObject(o);
            oos.close();
        } catch (IOException e) {
            System.out.println("error initalizing file output stream");
        }
    }
}