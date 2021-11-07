import java.util.*;

public class QuizTool {
    public static void main(String[] args) {
        System.out.println("Welcome to the Learning Management System Quiz Tool!");
        //system starts by loading necessary data from files
        Scanner scan = new Scanner(System.in);
        String username = "";
        String password = "";
        int menuChoice = 0;
        boolean menuActive = true;
        //system then initiates login process
        while (menuActive) {
            System.out.println("1. Login\n2. Create Account\n3. Exit");
            menuChoice = Integer.parseInt(scan.nextLine()); //not using nextInt because nextLine causes shenanigans
            switch (menuChoice) {
                case 1:
                    while (menuActive) {
                        System.out.println("Enter your username:");
                        username = scan.nextLine();
                        System.out.println("Enter your password:");
                        password = scan.nextLine();
                        //runs a login process here. if successful, menuActive false.
                        //if failure, print fail message and loop.
                    }
                    break;
                case 2:
                    System.out.println("Enter your username:");
                    username = scan.nextLine();
                    System.out.println("Enter your password:");
                    password = scan.nextLine();
                    System.out.println("Are you a teacher? [Y/N]:");
                    String teacherSelection = scan.nextLine();
                    boolean isTeacher = teacherSelection.equalsIgnoreCase("Y");
                    //creates account using given information
                    menuActive = false;
                    break;
                case 3:
                    System.out.println("Closing Quiz Tool");
                    return; //no data can be updated within this timespan, so files are not saved in this case
                default:
                    System.out.println("Please choose a valid menu option");
                    break;
            }
        }


    }
}