# Group 109 Project 4 CS 180
Nathan Leakeas, Peter Olsen, Brian Qi, Hawkins Peterson

We have moved the source files outside of the repository for convenience. 
Our git repository has been cloned into Vocarem, however. It can be found in the folder `cs180project4`.



## Compile Instructions
Our project can be compiled by running the command `javac MainGui.java` and `javac Server.java`.

## Run Instructions
The main classes of our project are `Server` and `MainGui`. First,
run the server with `java Server`. Then, run `java MainGui`.

##Tests
Manual tests and outcomes are recored in `Tests.md`

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