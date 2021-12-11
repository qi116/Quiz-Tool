/**
 * @author Nathan Leakeas
 * @version 11/14/2021
 *
 * Tests all classes derived from Account class
 */
public class AccountTest {
    public static void main(String[] args) {
        Account studentAccount = new Student("user", "password");
        Account teacherAccount = new Teacher("teach", "password");



        assert (studentAccount.isStudent()) : "isStudent() failure";
        assert (teacherAccount.isTeacher()) : "isTeacher() failure";

        Student student1 = (Student) studentAccount;
        Teacher teacher1 = (Teacher) teacherAccount;

        Question[] questions = new Question[1];
        questions[0] = new Question("Test Question");
        Quiz testQuiz = new Quiz("Test Quiz", questions);
        Course testCourse = new Course("Test Course");

        teacher1.addCourse(testCourse);
        teacher1.createCourse("Test Course 2");
        assert (teacher1.courses.get(0) == testCourse) : "addCourse() failure";
        assert (teacher1.courses.get(1) == testCourse) : "createCourse() failure";
        assert (teacher1.getCourses().get(0) == testCourse) : "getCourses() failure";
        teacher1.removeCourse("Test Course 2");
        assert (teacher1.getCourses().size() == 1) : "removeCourse(String) failure";
        teacher1.removeCourse(testCourse);
        assert (teacher1.getCourses().size() == 0) : "removeCourse(Course)";
        teacher1.addCourse(testCourse);
        teacher1.addQuizToCourse(testQuiz, "Test Course");
        assert (teacher1.getCourses().get(0).getQuiz("Test Quiz") == testQuiz) : "addQuizToCourse() failure";
        System.out.println("Teacher Tests Complete");

        student1.addQuizSubmission(testQuiz);
        //assert (student1.getQuizSubmissions().get(0) == testQuiz) : "addQuizSubmission() failure";
        assert (student1.getQuizSubmissionsByName("Test Quiz").get(0) == testQuiz) : "getQuizSubmissionsByName(" +
                ") failure";
        System.out.println("Student Tests Complete");






    }
}
