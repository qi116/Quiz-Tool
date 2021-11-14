# Group 109 Project 4 CS 180

## Compile Instructions
Our project can be compiled by using the cd command to navigate to the src directory.
From there, run `javac *.java` to compile the java files to .class files.

## Run Instructions
The main class of our project is QuizTool. To run the project, execute `java QuizTool`
after compiling.


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
* Account Test
    * Tests Student and Teacher method implementations