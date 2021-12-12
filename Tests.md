Note: Before all tests, launch the server via `java Server` after compiling

## Test 1: Log in
Steps:
1. Launch Application
2. User enters username into username text box
3. User enters password into password text box
4. User selects log in button

Expected result: Server checks username and password, client enters homepage

Test status: Passed


## Test 2: Account Creation
Steps:
1. Launch Application
2. User selects "create new account" button
3. User enters desired username into the username text box
4. User enters desired password into the password text box
5. User selects desired role from the "I am..." dropdown menu
6. User selects the "create account" button

Expected result: Server creates new account, client enters homepage
of role selected

Test status: Passed

## Test 3: Create course
Steps:
1. Launch Application
2. Login to or create new teacher account
3. Select create course button
4. Enter course name into popup
5. Press OK

Expected result: Server creates new course, client shows course in courselist

Test status: Passed
