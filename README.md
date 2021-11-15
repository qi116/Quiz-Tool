# Group 109 Project 4 CS 180
Nathan Leakeas, Peter Olsen, Brian Qi, Hawkins Peterson

We have moved the source files outside of the repository for convenience. 
Our git repository has been cloned into Vocarem, however. It can be found in the folder `cs180project4`.



## Compile Instructions
Our project can be compiled by running the command `javac QuizTool.java`.

## Run Instructions
The main class of our project is QuizTool. To run the project, execute `java QuizTool`
after compiling.

We have different classes dedicated to testing parts of our project.
* `AccountTest.java` tests Student, Teacher, and Account
* `DataTest.java` test AccessData
* `QuizToolTest.java` tests output of QuizTool and uses `TestUtil.java` (uses JUnit)
* `RunTest.java` tests output of Quiz, Question, and Course (Uses JUnit)

`AccountTest.java` and `DataTest.java` can be run and executed by compiling them
with `javac` and running them with `java`. For the tests the utilize JUnit,
we are unsure how to run them on vocarem because JUnit is not installed.


## Submission Responsibility
* Submission to Vocarem - Nathan Leakeas
* Report Submission to BrightSpace - Peter Olsen


## Documentation of Each Class
* Teacher - Nathan Leakeas 
    * Stores list of owned Courses
    * Add Courses
    * Create Courses
    * Remove Courses
    * Add Quizzes to Courses
* Student - Nathan Leakeas
    * Stores list of quiz submissions
    * Add quiz to list of submitted quizzes
    * Get list of quiz submissions by Name
* Account - Nathan Leakeas
    * abstract parent class of Student and Teacher
    * Stores username and password
    * Can check if instance is student or teacher
    * Get username or password
* Account Test - Nathan Leakeas
    * Tests Student and Teacher method implementations
* Quiz - Brian Qi
   * Stores list of questions
   * Question order randomization functionality
   * Stores attempt number
   * Holds instances of Question
* Question - Brian Qi
   * Stores question string
   * Stores list of choices
   * Choice order randomization functionality 
   * Stores grade
* Course - Brian Qi
   * Stores course name
   * Holds instances of Quiz
* QuizTool - Peter Olsen
  * User Interface for project
  * Login/Account Creation Menu
  * Teacher Course Modification, Quiz Modification, and Quiz Grading
  * Student Quiz Taking and Submission Viewing
  * References following classes
    * Course
    * Quiz
    * Question
    * Student
    * Teacher
    * Account
    * AccessData
* QuizToolTest - Peter Olsen
  * Tests QuizTool class implementation
* AccessData - Hawkins Peterson
  * Allows saving of accounts and courses
  * Allows getting accounts and courses
  * Allows user to get list of students
  * Allows user to get list of courses
  * Used by QuizTool main class
  * References following classes
      * Course
      * Quiz
      * Question
      * Student
      * Teacher
      * Account
