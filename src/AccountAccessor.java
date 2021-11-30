import java.util.*;

public class AccountAccessor extends ServerDataAccessor {
    private static final String folder = "data/accounts";
    private static final String fileType = ".obj";
    private ServerDataAccessor dataAccessor;
    public AccountAccessor() {
        super(folder, fileType);
    }

    public Account get(String username, String password) throws NullPointerException {
        try {
            Account account = (Account) super.get(username);
            if (password.equals(account.getPassword()))
                return account;
            else
                throw new NullPointerException("an account with that username / password combination was not found");
        } catch (NullPointerException npe) {
            throw new NullPointerException("an account with that username / password combination was not found");
        }
    }

    public Student get(String username) throws NullPointerException {
        try {
            Account student = (Account) super.get(username);
            if (student instanceof Student)
                return (Student) student;
            else 
                throw new NullPointerException("a student account with that username was not found");
        } catch (Exception e) {
            throw new NullPointerException("a student account with that username was not found");
        }
    }

    public boolean checkExists(String username) {
        return super.checkExists(username);
    }

    public String[] getStudentList() {
        Object[] ol = super.getListVerbose();
        ArrayList<Account> aal = new ArrayList<Account>();
        for (int i = 0; i < ol.length; i++)
            if (aal.get(i) instanceof Student)
                aal.add((Account) ol[i]);
        String[] usernameList = new String[aal.size()];
        for (int i = 0; i < aal.size(); i++)
            usernameList[i] = aal.get(i).getUsername();

        return usernameList;
    }

    public void add(Account account) throws FileAlreadyExistsException {
        super.addData(account, account.getUsername());
    }

    public void modify(Account account) throws NullPointerException {
        super.modifyData(account, account.getUsername());
    }
}
