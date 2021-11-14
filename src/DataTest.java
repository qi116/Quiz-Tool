import javax.swing.filechooser.FileSystemView;
import java.io.*;
public class DataTest {
    public static void main(String[] args) throws Exception {
        Student stud1 = new Student("user1", "Password");
        Teacher teach1 = new Teacher("user2", "Password");
        Student stud2 = new Student("user3", "Password");
    
        String[] ans = {"Yes", "No"};
        Question q1 = new Question("can you read this question?", ans);

        String[] ans2 = {"a", "b", "c", "d"};
        Question q2 = new Question("answer a, b, c, d", ans2);

        Question[] questionLst = {q1, q2};
        Quiz quiz1 = new Quiz("quiz 1", questionLst);

        Course cou1 = new Course("Course 1");
        cou1.addQuiz(quiz1);
        
        AccessData ad = new AccessData();


        //should not throw error

        AccessData.addAccount(stud1);
        AccessData.addAccount(teach1);
        AccessData.addAccount(stud2);
        AccessData.addCourse(cou1);

        System.out.println("added accounts / users");

        try {
            AccessData.addAccount(stud1);
            System.out.println("I'm an idiot my code is broken"); // should not be printed
        } catch (Exception e) {
            System.out.println("Does not allow you to recreate an account");
        }

        //shouldn't throw error
        System.out.println("Saved account 1");
        AccessData.writeAccountData(stud1);

        Account acc1 = AccessData.getAccountData("user1", "Password");
        teach1 = (Teacher) AccessData.getAccountData("user2", "Password");
        stud2 = (Student) AccessData.getAccountData("User3", "Password");
        System.out.println("Recieved all accounts");

        System.out.println("Course 1: " + AccessData.getAllCourseNames()[0]);
        System.out.println("Student accounts:");
        String[] accs = AccessData.getAllUsernames();
        for (String i : accs)
            System.out.println(i);

        cou1 = AccessData.getCourse("Course 1");
        System.out.println("Retrieved course 1");

        AccessData.modifyCourse(cou1.getName(), cou1);
        System.out.println("Saved Course 1");

        System.out.println("Make sure to delete the accounts and courses manually because Intelij doesn't like to");
    }
}
