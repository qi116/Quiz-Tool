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

        ad.addAccount(stud1);
        ad.addAccount(teach1);
        ad.addAccount(stud2);
        ad.addCourse(cou1);

        System.out.println("added accounts / users");

        try {
            ad.addAccount(stud1);
            System.out.println("I'm an idiot my code is broken"); // should not be printed
        } catch (Exception e) {
            System.out.println("Does not allow you to recreate an account");
        }

        //shouldn't throw error
        System.out.println("Saved account 1");
        ad.writeAccountData(stud1);

        Account acc1 = ad.getAccountData("user1", "Password");
        teach1 = (Teacher) ad.getAccountData("user2", "Password");
        stud2 = (Student) ad.getAccountData("User3", "Password");
        System.out.println("Recieved all accounts");

        System.out.println("Course 1: " + ad.getAllCourseNames()[0]);
        System.out.println("Student accounts:");
        String[] accs = ad.getAllUsernames();
        for (String i : accs)
            System.out.println(i);

        cou1 = ad.getCourse("Course 1");
        System.out.println("Retrieved course 1");

        ad.modifyCourse(cou1.getName(), cou1);
        System.out.println("Saved Course 1");

        File f = new File("data/accounts/user1.obj");
        if (!f.delete())
            System.out.println("failed to delete user1");
        f = new File("data/accounts/user2.obj");
        f.deleteOnExit();

        f = new File("data/accounts/user3.obj");
        f.delete();

        System.out.println("Deleted all files so that it can be rerun");
    }
}
