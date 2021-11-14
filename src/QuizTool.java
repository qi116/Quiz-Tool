import java.util.*;
/**
 * Main class to run the Quiz Tool
 *
 * @author Peter Olsen
 * @version 2021-11-13
 */
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
        Calendar calendar = Calendar.getInstance();
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
                    try {
                        AccessData.addAccount(account);
                    } catch (FileAlreadyExistsException e) {
                        e.printStackTrace();
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
                        String[] allCourses = AccessData.getAllCourseNames();
                        for (int i = 0; i < allCourses.length; i++) {
                            System.out.println(allCourses[i]);
                        }
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
                                System.out.println("1. Create New Quiz\n2. Remove Quiz\n3. Modify Quiz\n" +
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
                                        AccessData.modifyCourse(currentCourse, selectedCourse);
                                        System.out.println("Quiz created!");
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
                                        AccessData.modifyCourse(currentCourse, selectedCourse);
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
                                                selectedQuiz.setQuestion(questionNum,
                                                        new Question(questionName, choices));

                                            }
                                        }
                                        AccessData.modifyCourse(currentCourse, selectedCourse);
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
                        try {
                            AccessData.addCourse(((Teacher) account).createCourse(currentCourse));
                        } catch (FileAlreadyExistsException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "3":
                        System.out.println("Enter the course name:");
                        currentCourse = scan.nextLine();
                        ((Teacher) account).removeCourse(currentCourse);
                        AccessData.removeCourse(currentCourse);
                        break;
                    case "4":
                        String[] allUsernames = AccessData.getAllUsernames();
                        for (int i = 0; i < allUsernames.length; i++) {
                            System.out.println(allUsernames[i]);
                        }
                        System.out.println("Select a Student:");
                        String studentName = scan.nextLine();
                        if (AccessData.usernameExists(studentName)) {
                            Account studentTemp = AccessData.getAccountData(studentName);
                            if (studentTemp instanceof Student) {
                                Student student = (Student) studentTemp;
                                ArrayList<Quiz> submissions = student.getQuizSubmissions();
                                for (Quiz q : submissions) {
                                    System.out.println(q.getName());
                                }
                                System.out.println("Select A Quiz:");
                                String quizName = scan.nextLine();
                                ArrayList<Quiz> submissionsForQuiz = student.getQuizSubmissionByName(quizName);
                                for (Quiz q : submissionsForQuiz) {
                                    System.out.println(q.getTimeStamp());
                                }
                                System.out.println("Select a Timestamp");
                                String timestamp = scan.nextLine();
                                Quiz gradeQuiz = null;
                                for (Quiz q : submissionsForQuiz) {
                                    if (q.getTimeStamp().equals(timestamp)) {
                                        gradeQuiz = q;
                                        break;
                                    }
                                }
                                if (gradeQuiz == null) {
                                    System.out.println("Invalid Timestamp");
                                    break;
                                } else {
                                    for (int i = 1; i <= gradeQuiz.getLength(); i++) {
                                        System.out.println(gradeQuiz.getQuestion(i));
                                        System.out.println("Student Answer: " + gradeQuiz.getQuestion(i).getChoices()
                                                [gradeQuiz.getQuestion(i).getStudentAnswer()]);
                                        System.out.println("Enter the grade for this question: ");
                                        gradeQuiz.getQuestion(i).setGrade(Integer.parseInt(scan.nextLine()));
                                    }
                                }
                            } else {
                                System.out.println("Account is not a student");
                            }
                        } else {
                            System.out.println("Invalid Selection");
                        }

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
                        String[] allCourses = AccessData.getAllCourseNames();
                        for (int i = 0; i < allCourses.length; i++) {
                            System.out.println(allCourses[i]);
                        }
                        System.out.println("Select a course:");
                        try {
                            currentCourse = scan.nextLine();
                            selectedCourse = AccessData.getCourse(currentCourse);
                            System.out.println("Select a quiz:");
                            currentQuiz = scan.nextLine();
                            selectedQuiz = AccessData.getQuiz(currentCourse, currentQuiz);
                            for (int i = 1; i <= selectedQuiz.getLength(); i++) {
                                Question currentQuestion = selectedQuiz.getQuestion(i);
                                System.out.println(currentQuestion);
                                char studentAnswer = scan.nextLine().charAt(0);
                                currentQuestion.setStudentAnswer(studentAnswer - 65);
                            }
                            String date = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH);
                            date += "-" + calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.HOUR_OF_DAY);
                            date += ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
                            selectedQuiz.setTimeStamp(date);
                            ((Student) account).addQuizSubmission(selectedQuiz);
                            System.out.println("Quiz Submitted!");

                        } catch (NullPointerException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        break;
                    case "2":
                        //implement grade viewing system
                        ArrayList<Quiz> submissions = ((Student) account).getQuizSubmissions();
                        for (Quiz q : submissions) {
                            System.out.println(q.toStringPostTake());
                        }
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

//import java.util.*;
//
//public class QuizTool {
//    public static void main(String[] args) {
//        System.out.println("Welcome to the Learning Management System Quiz Tool!");
//        //system starts by loading necessary data from files
//        Scanner scan = new Scanner(System.in);
//        String username = "";
//        String password = "";
//        String menuChoice = "";
//        boolean menuActive = true;
//        Account account;
//        //system then initiates login process
//        while (menuActive) {
//            System.out.println("1. Login\n2. Create Account\n3. Exit");
//            menuChoice = scan.nextLine(); //not using nextInt because nextLine causes shenanigans
//            switch (menuChoice) {
//                case "1":
//                    while (menuActive) {
//                        System.out.println("Enter your username:");
//                        username = scan.nextLine();
//                        System.out.println("Enter your password:");
//                        password = scan.nextLine();
//                        //runs a login process here. if successful, menuActive false.
//                        try {
//                            account = getAccountData(username, password);
//                            menuActive = false;
//                        } catch (NullPointerException) {
//                            System.out.println("Invalid login");
//                        }
//                        //if failure, print fail message and loop.
//                    }
//                    break;
//                case "2":
//                    System.out.println("Enter your username:");
//                    username = scan.nextLine();
//                    System.out.println("Enter your password:");
//                    password = scan.nextLine();
//                    System.out.println("Are you a teacher? [Y/N]:");
//                    String teacherSelection = scan.nextLine();
//                    boolean isTeacher = teacherSelection.equalsIgnoreCase("Y");
//                    //checks to make sure the username doesn't already exist
//                    //creates account using given information
//                    if (isTeacher) {
//                        account = new Teacher(username, password);
//                    } else {
//                        account = new Student(username, password);
//                    }
//                    menuActive = false;
//                    break;
//                case "3":
//                    System.out.println("Closing Quiz Tool");
//                    return; //no data can be updated within this timespan, so files are not saved in this case
//                default:
//                    System.out.println("Please choose a valid menu option");
//                    break;
//            }
//        }
//        menuActive = true;
//        if (account instanceof Teacher) { //checks if the account is a teacher
//            while (menuActive) {
//                System.out.println("1. Add Quiz\n2. Remove Quiz\n3. Modify Quiz\n4. Grade Quiz\n5. Exit");
//                menuChoice = scan.nextLine();
//                switch (menuChoice) {
//                    case "1":
//                        //implement add quiz system
//                        break;
//                    case "2":
//                        //implement remove quiz system
//                        break;
//                    case "3":
//                        //implement modify quiz system
//                        break;
//                    case "4":
//                        //allow teacher to select a student, implement grade quiz system
//                        break;
//                    case "5":
//                        System.out.println("Closing Quiz Tool");
//                        //take all currently stored data and write it back into files
//                        return;
//                    default:
//                        System.out.println("Please choose a valid menu option");
//                        break;
//                }
//            }
//        } else { //assumes the account is a student
//            while (menuActive) {
//                System.out.println("1. Take Quiz\n2. View Grades\n3. Exit");
//                menuChoice = scan.nextLine();
//                switch (menuChoice) {
//                    case "1":
//                        //implement take quiz system
//                        break;
//                    case "2":
//                        //implement grade viewing system
//                        break;
//                    case "3":
//                        System.out.println("Closing Quiz Tool");
//                        //take all currently stored data and write it back into files
//                        return;
//                    default:
//                        System.out.println("Please choose a valid menu option");
//                        break;
//                }
//            }
//        }
//
//
//    }
//}

