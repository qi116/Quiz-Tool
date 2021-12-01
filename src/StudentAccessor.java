import java.util.*;
/**
 * an accessor ment specifically for students (and using only GET and GET_STRING_LIST)
 *
 * @author Hawkins Peterson
 * @version 11.30.21
 */
public class StudentAccessor extends ServerDataAccessor {
    private final static String folder = "data/accounts/"; //folder prefix of file
    private final static String fileType = ".obj"; //the filetype of the file
    
    /**
     * constructs a student accessor
     */
    public StudentAccessor() {
        super(folder, fileType);
    }

    /**
     * gets a student account given that it exists and is a student account
     *
     * @param username the username of the student account to be fetched
     * @return the Student account with the username username
     */
    public Student get(String username) throws NullPointerException {
        AccountAccessor accountAccessor = new AccountAccessor();
        Account account = (Account) accountAccessor.get(username);
        if (account instanceof Student) {
            return (Student) account;
        } else {
            throw new NullPointerException("a student class with that username does not exist");
        }
    }

    /**
     * Returns a list of all student usernames
     * @return a list of all student usernames
     */
    public String[] getList() {
        Object[] ol = super.getListVerbose();
        ArrayList<Account> aal = new ArrayList<Account>();
        for (int i = 0; i < ol.length; i++) 
            if (ol[i] instanceof Student)
                aal.add((Student) ol[i]);
        String[] usernameList = new String[aal.size()];
        for (int i = 0; i < aal.size(); i++)
            usernameList[i] = aal.get(i).getUsername();

        return usernameList;
    }
} 
