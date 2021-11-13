import java.util.*;

public class QuizTool {
    public static void main(String[] args) {
        System.out.println("Welcome to the Learning Management System Quiz Tool!");
        //system starts by loading necessary data from files
        Scanner scan = new Scanner(System.in);
        String username = "";
        String password = "";
        String menuChoice = "";
        boolean menuActive = true;
        Account account;
        //system then initiates login process
        while (menuActive) {
            System.out.println("1. Login\n2. Create Account\n3. Exit");
            menuChoice = scan.nextLine(); //not using nextInt because nextLine causes shenanigans
            switch (menuChoice) {
                case "1":
                    while (menuActive) {
                        System.out.println("Enter your username:");
                        username = scan.nextLine();
                        System.out.println("Enter your password:");
                        password = scan.nextLine();
                        //runs a login process here. if successful, menuActive false.
                        try {
                            account = getAccountData(username, password);
                            menuActive = false;
                        } catch (NullPointerException) {
                            System.out.println("Invalid login");
                        }
                        //if failure, print fail message and loop.
                    }
                    break;
                case "2":
                    System.out.println("Enter your username:");
                    username = scan.nextLine();
                    System.out.println("Enter your password:");
                    password = scan.nextLine();
                    System.out.println("Are you a teacher? [Y/N]:");
                    String teacherSelection = scan.nextLine();
                    boolean isTeacher = teacherSelection.equalsIgnoreCase("Y");
                    //checks to make sure the username doesn't already exist
                    //creates account using given information
                    if (isTeacher) {
                        account = new Teacher(username, password);
                    } else {
                        account = new Student(username, password);
                    }
                    menuActive = false;
                    break;
                case "3":
                    System.out.println("Closing Quiz Tool");
                    return; //no data can be updated within this timespan, so files are not saved in this case
                default:
                    System.out.println("Please choose a valid menu option");
                    break;
            }
        }
        menuActive = true;
        if (account instanceof Teacher) { //checks if the account is a teacher
            while (menuActive) {
                System.out.println("1. Add Quiz\n2. Remove Quiz\n3. Modify Quiz\n4. Grade Quiz\n5. Exit");
                menuChoice = scan.nextLine();
                switch (menuChoice) {
                    case "1":
                        //implement add quiz system
                        break;
                    case "2":
                        //implement remove quiz system
                        break;
                    case "3":
                        //implement modify quiz system
                        break;
                    case "4":
                        //allow teacher to select a student, implement grade quiz system
                        break;
                    case "5":
                        System.out.println("Closing Quiz Tool");
                        //take all currently stored data and write it back into files
                        return;
                    default:
                        System.out.println("Please choose a valid menu option");
                        break;
                }
            }
        } else { //assumes the account is a student
            while (menuActive) {
                System.out.prntln("1. Take Quiz\n2. View Grades\n3. Exit");
                menuChoice = scan.nextLine();
                switch (menuChoice) {
                    case "1":
                        //implement take quiz system
                        break;
                    case "2":
                        //implement grade viewing system
                        break;
                    case "3":
                        System.out.println("Closing Quiz Tool");
                        //take all currently stored data and write it back into files
                        return;
                    default:
                        System.out.println("Please choose a valid menu option");
                        break;
                }
            }
        }
    }
}
