# Group 109 Project 5 CS 180
Nathan Leakeas, Peter Olsen, Brian Qi, Hawkins Peterson

We have moved the source files outside of the repository for convenience. 
Our git repository has been cloned into Vocarem, however. It can be found in the folder `cs180project4`.



## Compile Instructions
Our project can be compiled by running the command `javac MainGui.java` and `javac Server.java`.

## Run Instructions
The main classes of our project are `Server` and `MainGui`. First,
run the server with `java Server`. Then, run `java MainGui`.

## Operation
To faciliate testing, some dummy content has already been added to the application.
Two dummy courses, `course 1`, and `course 2`, exist. Inside `course 1`,
a dummy quiz exists. Two dummy acounts also exist, `teacher`, and `student`,
each with password `pass`.

## Tests
Manual tests and outcomes are recored in `Tests.md`
`DataTest.java` tests the storage capabilities of the server 

## Submission Responsibility
* Submission to Vocarem - Nathan Leakeas
* Report Submission to BrightSpace - Peter Olsen
* Presentation submission - Peter Olsen


## Documentation of Each Class
* Server - Nathan Leakeas
  * Entry point for server
  * Creates threads for each connection
* ServerThread - Nathan Leakeas
  * Created per connection
  * handles each connection's requests
* ServerRefreshThread - Nathan Leakeas
  * sends update messages to all clients
  * can be started in any ServerThread
* Message - Nathan Leakeas and Hawkins Peterson
  * Used to establish a protocol for communication between client and server
  * requestType enum - told the server what do do with the data contained
  * dataType enum - what kind of data stored inside the message
  or to be returned
    * content - any kind of object, with type specified in dataType enum
* MainGUI - Peter Olsen
  * Displays visual user interface
  * Handles user input and selections
  * Uses Client program to interact with the server
* Client - Brian Qi
  * Creates client-side connection with server
  * Handles sending requests to server
  * Handles information sent back from server
* Update - Brian Qi
  * Creates thread to wait for update message
  * Indicates to GUI program that an update has been made 
* ServerDataAccessor - Hawkins Peterson
  * Accesses data from storage (ie pushing and pulling accounts / courses from storage)
* ServerDataHandler - Hawkins Peterson
  * Interpretes a message sent from the client using a switch statment 
  * Holds methods using ServerDataAccessor to store, get, and list, accounts, courses, quizzes and quiz submissions (along with other functionality)

