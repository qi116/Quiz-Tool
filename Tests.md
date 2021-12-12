Note: Before all tests, launch the server via `java Server` after compiling

## Test 1: Log In
1. Launch Application
2. User enters username into username text box
3. User enters password into password text box
4. User selects “Log In” button

Expected result: Server checks username and password, client enters homepage

Test status: Passed


## Test 2: Account Creation
1. Launch Application
2. User selects "create new account" button
3. User enters desired username into the username text box
4. User enters desired password into the password text box
5. User selects desired role from the "I am..." dropdown menu
6. User selects the "Create Account" button

Expected result: Server creates new account, client enters homepage
of role selected

Test status: Passed

## Test 3: Create Course
1. Launch Application
2. Login to or create new teacher account
3. Select create course button
4. Enter course name into popup
5. Press “Create Course”

Expected result: Server creates new course, client shows course in course list

Test status: Passed

## Test 4: Delete Course
1. Launch Application
2. Login to or create new teacher account
3. Select a course from the course list
4. Select “Delete Course”

Expected result: Server deletes course from course list, client shows course list without deleted course

Test status: Passed

## Test 5: Real Time Update
1. Launch Application on one device
2. Log into/create account as Teacher
3. Launch Application on second device
4. Log into/create account as Teacher
5. Create Course on second device

Expected result: First client sees the new course in their course list without clicking any buttons

Test status: Passed


## Test 6: Create Quiz
1. Launch application
2. Log into or create account as Teacher
3. Create or select existing course
4. Open course
5. Select “Add Quiz” button
6. Enter desired quiz name into Quiz Name box
7. Enter desired number of questions into the Number of Questions box
8. Select Randomize Question Order if desired
9. For each question, enter desired Question Name, Number of Answers, and whether or not to randomize answer order

Expected result: Client sees quiz appear in quiz list

Test status: passed

## Test 7: Edit Quiz
1. Launch Application
2. Log in/create account as Teacher
3. Open course with quiz in it
4. Select quiz to be edited
5. Select edit quiz button
6. For each question, modify desired attributes such as question title or answer choice content
7. Select the set choices button for each question

Expected result: Quiz content is changed

Test status: Passed


## Test 8: Delete Quiz
1. Launch Application
2. Log in/create account as Teacher
3. Open a course
4. Click on an existing quiz
5. Select “Delete Course”

Expected result: Client sees quiz list with selected quiz removed

Test status: Passed

## Test 9: Take Quiz
1. Launch Application
2. Log in/create account as Student
3. Select “TakeQuiz”
4. Select a course from the course list
5. Select “Select Course”
6. Select a quiz from the quiz list
7. Select “Select Quiz”
8. Select answer choice and “Next Question” for each question

Expected result: Student takes quiz and submits to server. Submission shows up in submissions list

Test Status: Passed

## Test 10: Grade Quiz
1. Launch Application
2. Log in/create account as Teacher
3. Select “Grade Quiz”
4. Select a student
5. Select a submission
6. Enter a number grade for each answer

Expected result: Client is taken back to the main course display screen; no error messages pop up

Test status: Passed

## Test 11: View Submission
1. Launch Application
2. Log in to student account
3. Select submission from submissions list
4. Select “View Submission”
5. Select “Next Question” until all returning to submissions list

Expected result: Client is taken to their submission, which shows each question in the quiz along with the chosen answer and the given grade.

Test status: Passed

## Test 12: Take Randomized Quiz
1. Launch Application
2. Log in/create account as Teacher
3. Select a course from the course list
4. Select “Open Course”
5. Select “Create Quiz”
6. When entering questions, tick the randomize checkbox for all cases. Number each question and answer in order
7. Relaunch Application
8. Log in/create account as Student
9. Select “TakeQuiz”
10. Select a course from the course list
11. Select “Select Course”
12. Select the quiz from the quiz list
13. Select “Select Quiz”
14. Select answer choice and “Next Question” for each question

Expected result: All questions and answers appear, but they appear in a randomized order

Test Result: Passed

## Test 13: Create an already created account
1. Launch application
2. Create an account
3. Try to sign up with the same account

Expected result: Error is returned, saying you can’t overwrite an existing account

Test Status: Passed

