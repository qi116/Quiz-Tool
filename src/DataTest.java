public class DataTest {
    public static void main(String[] args) throws Exception {
        Student stud1 = new Student("user1", "Password");
        Teacher teach1 = new Teacher("user2", "Password");
        Student stud2 = new Student("user3", "Password");

        System.out.println("Password: " + stud1.getPassword());
    
        String[] ans = {"Yes", "No"};
        Question q1 = new Question("can you read this question?", ans);

        String[] ans2 = {"a", "b", "c", "d"};
        Question q2 = new Question("answer a, b, c, d", ans2);

        Question[] quLst = {q1, q2};
        Quiz quiz1 = new Quiz("quiz 1", quLst);

        Course cou1 = new Course("Course 1");
        cou1.addQuiz(quiz1);
        
        AccessData ad = new AccessData();
        
        //ad.addAccount(stud1);
        //ad.addAccount(teach1);
        //ad.addAccount(stud1);
        //ad.addCourse(cou1);

        //if (ad.usernameExists("user1"))
            //System.out.println("User 1 exists");
        //else
            //System.out.println("I'm an idiot");
        //try {
            //ad.addAccount(stud1);
        //} catch (Exception e) {
            //System.out.println("It works!");
        //}

        Account acc1 = ad.getAccountData("user1", "Password");
        teach1 = (Teacher) ad.getAccountData("user2", "Password");
        stud2 = (Student) ad.getAccountData("User2", "Password");

        System.out.println(ad.getAllCourseNames());
        cou1 = ad.getCourse("Course 1");


        
    }
}
