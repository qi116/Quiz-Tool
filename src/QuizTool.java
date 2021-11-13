import java.util.*;

public class QuizTool {
    public static void main(String[] args) {
        System.out.println("Welcome to the Learning Management System Quiz Tool!");
        //system starts by loading necessary data from files
        Scanner scan = new Scanner(System.in);
        String username = "";
        String password = "";
        String menuChoice = "";
        String currentCourse = "";
        String currentQuiz = "";
        boolean menuActive = true;
        Account account = null;
        Course selectedCourse = null;
        Quiz selectedQuiz = null;
        ArrayList<Course> courseList;
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
                            account = AccessData.getAccountData(username, password);
                            menuActive = false;
                        } catch (NullPointerException e) {
                            System.out.println("Invalid login");
                        }
                        //if failure, print fail message and loop.
                    }
                    break;
                case "2":
                    System.out.println("Enter your username:");
                    username = scan.nextLine();
                    //checks to make sure the username doesn't already exist
                    if (AccessData.usernameExists(username)) {
                        System.out.println("That username is already taken.");
                        break;
                    }
                    System.out.println("Enter your password:");
                    password = scan.nextLine();
                    System.out.println("Are you a teacher? [Y/N]:");
                    String teacherSelection = scan.nextLine();
                    boolean isTeacher = teacherSelection.equalsIgnoreCase("Y");
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
            courseList = ((Teacher) account).getCourses();
            while (menuActive) {
                System.out.println("1. Select Course\n2. Add Course\n3. Remove Course\n4. Grade Student Submission" +
                        "\n5. Exit");
                menuChoice = scan.nextLine();
                switch (menuChoice) {
                    case "1":
                        System.out.println("Enter the course name:");
                        currentCourse = scan.nextLine();
                        for (Course c : courseList) {
                            if (c.getName().equals(currentCourse)) {
                                selectedCourse = c;
                            }
                        }
                        if (selectedCourse == null) {
                            System.out.println("Invalid Course Name");
                        } else {
                            while (menuActive) {
                                System.out.println("1. Create New Quiz\n2. Remove Quiz\n3. Modify Quiz" +
                                        "4. Close Quiz Menu");
                                menuChoice = scan.nextLine();
                                switch (menuChoice) {
                                    case "1":
                                        System.out.println("Enter the quiz name:");
                                        currentQuiz = scan.nextLine();
                                        System.out.println("How many questions will be in the quiz?:");
                                        int quizQuestions = Integer.parseInt(scan.nextLine());
                                        Question[] quiz = new Question[quizQuestions];
                                        for (int i = 0; i < quizQuestions; i++) {
                                            System.out.println("Enter the question:");
                                            String questionName = scan.nextLine();
                                            System.out.println("How many answer choices will this question have?:");
                                            int numChoices = Integer.parseInt(scan.nextLine());
                                            String[] choices = new String[numChoices];
                                            for (int j = 0; j < numChoices; j++) {
                                                System.out.println("Enter answer choice " + (j + 1) + ":");
                                                choices[j] = scan.nextLine();
                                            }
                                            quiz[i] = new Question(questionName, choices);
                                        }
                                        selectedCourse.addQuiz(new Quiz(currentQuiz, quiz));
                                        break;
                                    case "2":
                                        System.out.println("Enter the quiz name:");
                                        currentQuiz = scan.nextLine();
                                        selectedQuiz = selectedCourse.getQuiz(currentQuiz);
                                        if (selectedQuiz == null) {
                                            System.out.println("Invalid Quiz Name");
                                        } else {
                                            selectedCourse.removeQuiz(selectedQuiz);
                                        }
                                        break;
                                    case "3":
                                        System.out.println("Enter the quiz name:");
                                        currentQuiz = scan.nextLine();
                                        selectedQuiz = selectedCourse.getQuiz(currentQuiz);
                                        if (selectedQuiz == null) {
                                            System.out.println("Invalid Quiz Name");
                                        } else {
                                            System.out.println("Which question number would you like to modify?:");
                                            int questionNum = Integer.parseInt(scan.nextLine());
                                            Question modifyQuestion = selectedQuiz.getQuestion(questionNum);
                                            if (modifyQuestion == null) {
                                                System.out.println("Invalid Question Number");
                                            } else {
                                                System.out.println("Current Question:\n" + modifyQuestion);
                                                System.out.println("Enter the new question:");
                                                String questionName = scan.nextLine();
                                                System.out.println("How many answer choices will this question have?:");
                                                int numChoices = Integer.parseInt(scan.nextLine());
                                                String[] choices = new String[numChoices];
                                                for (int j = 0; j < numChoices; j++) {
                                                    System.out.println("Enter answer choice " + (j + 1) + ":");
                                                    choices[j] = scan.nextLine();
                                                }
                                                // = new Question(questionName, choices);
                                                //TODO: question constructor with all parameters, quiz method to replace a question

                                            }
                                        }
                                        break;
                                    case "4":
                                        menuActive = false;
                                        break;
                                    default:
                                        System.out.println("Please choose a valid menu option");
                                        break;
                                }
                            }
                            menuActive = true;
                        }
                        break;
                    case "2":
                        System.out.println("Enter the course name:");
                        currentCourse = scan.nextLine();
                        ((Teacher) account).createCourse(currentCourse);
                        break;
                    case "3":
                        System.out.println("Enter the course name:");
                        currentCourse = scan.nextLine();
                        ((Teacher) account).removeCourse(currentCourse);
                        break;
                    case "4":
                        //TODO: STUDENT GRADING ZONE
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
        } else if (account instanceof Student) { //assumes the account is a student
            while (menuActive) {
                System.out.println("1. Take Quiz\n2. View Grades\n3. Exit");
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