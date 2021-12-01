/**
 * an accessor for accounts
 *
 * @author Hawkins Peterson
 * @version 11.30.21
 */
public class AccountAccessor extends ServerDataAccessor {
    private static final String folderPrefix = "data/accounts";
    private static final String fileType = ".obj";
    private ServerDataAccessor dataAccessor;
    public AccountAccessor() {
        super(folderPrefix, fileType);
    }

    /**
     * gets a new AccountAccessor
     *
     * @param username the username of the account you want to access
     * @param password the password of the account you want to access
     * @return the account you want to access (null if an error was thrown)
     */
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

    /**
     * given an Account object adds it to the data
     * 
     * @param account the account to be added to data
     */
    public void add(Account account) throws FileAlreadyExistsException {
        super.addData(account, account.getUsername());
    }

    /**
     * given an Account object, saves it so long as it doesn't exist
     * 
     * @param account the account to be saved
     */
    public void modify(Account account) throws NullPointerException {
        super.modifyData(account, account.getUsername());
    }
}
