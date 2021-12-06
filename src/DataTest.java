import java.io.*;

public class DataTest {
    public static void main(String[] args) {
        TestingHandler th = new TestingHandler();
        Message request;
        Message response;

        //adding student 1 to storage
        Student student1 = new Student("student1", "Password");
        request = new Message(Message.requestType.MODIFY, Message.dataType.ACCOUNT, student1);
        response = th.processRequest(request);
        System.out.println((boolean) response.content ? 
                "added student 1 to data (using a modify request)" : "failed to add student 1 to storage");
        
        //making sure you can't overwrite accounts
        request = new Message(Message.requestType.ADD, Message.dataType.ACCOUNT, student1);
        response = th.processRequest(request);
        System.out.println(!((boolean) response.content) ? 
                "successfully failed to overwrite student 1" : "overwrote student 1");
        
        //adding student 2 to storage  
        Student student2 = new Student("student2", "Password");
        request = new Message(Message.requestType.ADD, Message.dataType.ACCOUNT, student2);
        response = th.processRequest(request);
        System.out.println((boolean) response.content ? 
                "added student 2 to data" : "failed to add student 2 to storage");

        //Logging in to student 2
        request = new Message(Message.requestType.LOGIN, Message.dataType.ACCOUNT, "student2", "Password");
        response = th.processRequest(request);
        System.out.println((boolean) response.content ?
                "got student 2 out of storage" : "failed to retrieve student 2 from storage");

        //adding a teacher to storage
        Teacher teacher = new Teacher("teacher", "Password");
        request = new Message(Message.requestType.ADD, Message.dataType.ACCOUNT, teacher);
        response = th.processRequest(request);
        System.out.println((boolean) response.content ? 
                "added a teacher to data" : "failed to add a teacher to storage");

        //listing student courses
        request = new Message(Message.requestType.LIST, Message.dataType.ACCOUNT, null);
        System.out.println("Listing all students:");
        response = th.processRequest(request);
        String[] content = (String[]) response.content;
        if (content != null)
            for (String i : content)
                System.out.println(i);
        else
            System.out.println("error, recieved null list\n");
        System.out.print("\n");


        //adding a new Course
        request = new Message(Message.requestType.ADD, Message.dataType.COURSE, "course1");
        response = th.processRequest(request);
        System.out.println((boolean) response.content ? 
                "added new course" : "Failed to add new course");
        
        //adding a 2nd course via modify
        request = new Message(Message.requestType.MODIFY, Message.dataType.COURSE, "course2");
        response = th.processRequest(request);
        System.out.println((boolean) response.content ?
                "added new course using modify command" : "failed to add new course using modify command");
        
        //remvoing 2nd course
        request = new Message(Message.requestType.REMOVE, Message.dataType.COURSE, "course2");
        response = th.processRequest(request);
        System.out.println((boolean) response.content ?
                "removed 2nd course" : "failed to remove second course ");

        //Listing courses
        System.out.println("Listing courses:");
        request = new Message(Message.requestType.LIST, Message.dataType.COURSE, "student1");
        response = th.processRequest(request);
        String[] courseNames = (String[]) response.content;
        if (courseNames != null)
            for (String i : courseNames)
                System.out.println(i);
        System.out.print("\n");

        //adding quizzes
        System.out.println("Start of quizzes");


        String[] answers = {"a", "b", "c", "d"};
        Question q1 = new Question("answer 'a'", answers);
        Question q2 = new Question("answer 'c'", answers);

        Question[] questions = {q1, q2};
        Quiz quiz = new Quiz("first quiz", questions);
    
        request = new Message(Message.requestType.ADD, Message.dataType.QUIZ, "course1", quiz);
        response = th.processRequest(request);
        System.out.println((boolean) response.content ? "added quiz to course 1" : "failed to add quiz to course 1");
        
        //adding 2nd quiz
        Quiz quiz2 = new Quiz("second quiz", questions);
        
        request = new Message(Message.requestType.MODIFY, Message.dataType.QUIZ, "course1", quiz2);
        response = th.processRequest(request);
        System.out.println((boolean) response.content ? 
                "added quiz 2 to course1 via Modify " : "failed to add quiz to course1 via modify");
        
        //overwriting quiz 2
        q2 = new Question("answer 'd'", answers);
        questions[1] = q2;
        quiz2 = new Quiz("second quiz", questions);
        request = new Message(Message.requestType.ADD, Message.dataType.QUIZ, "course1", quiz2);
        response = th.processRequest(request);
        
        System.out.println((boolean) response.content ? 
                "overwote quiz 2" : "failed to overwrite quiz 2");
        
        //deleting quiz 2
        request = new Message(Message.requestType.REMOVE, Message.dataType.QUIZ, "course1", "second quiz");
        response = th.processRequest(request);
        System.out.println((boolean) response.content ?
                "deleted quiz 2" : "failed to delete quiz 2");
        
        //listing quizzes
        request = new Message(Message.requestType.LIST, Message.dataType.QUIZ, "course1");
        response = th.processRequest(request);
        System.out.println("Listing quizzes:");
        String[] quizzes = (String[]) response.content;
        for(String i : quizzes)
            System.out.println(i);
        System.out.print("\n");
        
        q1.setStudentAnswer(0);
        q2.setStudentAnswer(0);

        quiz.setAttempt(1);
        request = new Message(Message.requestType.ADD, Message.dataType.SUBMISSION, "student1", quiz);
        response = th.processRequest(request);
        System.out.println((boolean) response.content ? 
                "failed to prevent a teacher from adding a new quiz attempt to a student's acccount" :
                "succesfully prevented a teacher from adding a new quiz to a student's account");


        request = new Message(Message.requestType.LOGIN, Message.dataType.ACCOUNT, "student1", "Password");
        response = th.processRequest(request);
        System.out.println((boolean) response.content ? "Logged in as a student" : "failed to log in as a student");

        quiz.setAttempt(1);
        request = new Message(Message.requestType.ADD, Message.dataType.SUBMISSION, "student1", quiz);
        response = th.processRequest(request);
        System.out.println((boolean) response.content ? 
                "added new quiz to a student's acccount" :
                "failed to add new quiz to student's account");

        q1.setGrade(1);
        q2.setGrade(0);

        request = new Message(Message.requestType.LOGIN, Message.dataType.ACCOUNT, "teacher", "Password");
        response = th.processRequest(request);
        System.out.println((boolean) response.content ? 
                "Logged back into teacher account" : "Failed to log into teacher account");
        
        request = new Message(Message.requestType.MODIFY, Message.dataType.SUBMISSION, "student1", quiz);
        response = th.processRequest(request);
        System.out.println((boolean) response.content ? 
                "Modified quiz attempt" : "Failed to modify quiz attempt");

        request = new Message(Message.requestType.LIST, Message.dataType.SUBMISSION, "student1");
        response = th.processRequest(request);

        System.out.println("Listing quiz attempts");
        content = (String[]) response.content;
        for (String i : content)
            System.out.println(i);

        //last lines of the code
        try {
            new File("data/accounts/teacher.obj").delete();
            new File("data/accounts/student1.obj").delete();
            new File("data/accounts/student2.obj").delete();
            new File("data/courses/course1.obj").delete();
            new File("data/courses/course2.obj").delete();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println("\ndeleted all accounts so that you can rerun this program without false errors appearing");
    }
}