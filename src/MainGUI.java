import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class MainGUI extends JComponent implements Runnable {
    JLabel usernameLabel;
    JLabel usernameLabelC;
    JLabel passwordLabel;
    JLabel passwordLabelC;
    JLabel roleLabel;
    JTextField usernameText;
    JTextField usernameTextC;
    JPasswordField passwordText;
    JPasswordField passwordTextC;
    JComboBox<String> roleSelect;
    JButton loginButton;
    JButton createButton;
    JButton switchToLoginButton;
    JButton switchToCreateButton;
    static JList<String> coursesTM;
    JScrollPane courseScrollTM;
    JButton addCourseTM;
    JButton openCourseTM;
    JButton deleteCourseTM;
    JButton gradeQuizTM;
    static JList<String> submissionsSM;
    JScrollPane subScrollSM;
    JButton viewSubSM;
    JButton takeQuizSM;
    JTextField courseNameTAC;
    JButton addCourseTAC;
    JButton cancelTAC;
    static JList<String> quizzesTCD;
    JScrollPane quizScrollTCD;
    JButton addQuizTCD;
    JButton editQuizTCD;
    JButton deleteQuizTCD;
    JButton exitCourseTCD;
    JLabel qNameLabelTQC;
    JLabel numQuesLabelTQC;
    JTextField quizNameTQC;
    JTextField numQuestionsTQC;
    JButton createQuizTQC;
    JLabel quesNameLabelTKC;
    JLabel numAnsLabelTKC;
    JTextField questionNameTKC;
    JTextField numAnswersTKC;
    JButton createQuesTKC;
    JLabel[] ansLabelsTAC;
    JTextField[] answersTAC;
    JButton setAnswersTAC;
    JLabel quesLabelTQE;
    JTextField questionTQE;
    JLabel[] ansLabelsTQE;
    JTextField[] answersTQE;
    JButton setChoicesTQE;
    JLabel questionTG;
    JLabel ansPromptTG;
    JLabel answerTG;
    JLabel scorePromptTG;
    JTextField scoreTG;
    JButton scoreQuesTG;
    static JList<String> studentsTStS;
    JScrollPane stuScrollTStS;
    JButton selectStuTStS;
    JButton courseMenuTStS;
    static JList<String> subsTSuS;
    JScrollPane subScrollTSuS;
    JButton selectSubTSuS;
    JButton stuMenuTSuS;
    JButton courseMenuTSuS;
    JLabel questionSQD;
    JLabel ansPromptSQD;
    JLabel answerSQD;
    JLabel scorePromptSQD;
    JLabel scoreSQD;
    JButton nextQuesSQD;
    JButton exitSQD;
    static JList<String> courseListSTQ;
    JScrollPane cListScrollSTQ;
    JButton selectCourseSTQ;
    JButton cancelCourseSTQ;
    static JList<String> quizListSTQ;
    JScrollPane qListScrollSTQ;
    JButton selectQuizSTQ;
    JButton changeCourseSTQ;
    JButton cancelQuizSTQ;
    JLabel questionSTQ;
    JList<String> answerOptionsSTQ;
    JButton nextQuestionSTQ;
    JCheckBox quizRandomizedTQC;
    JCheckBox questionRandomizedTKC;



    int numQuestions;
    int numAnswers;
    int questionTracker;

    static int currentLocation = 0;

    static JFrame frame;

    static String currentCourse = "";
    static Quiz currentQuiz;
    static String currentQuizName;
    static Question[] currentQuestions;
    static String currentQuesPrompt;
    static String[] currentAnswers;
    static Quiz[] currentSubmissions;
    static String currentStudent;
    static boolean randomizeQuiz;
    static boolean randomizeQuestion;

    static Client c;
    static Calendar calendar;


    public static void main(String[] args) throws InterruptedException {
        SwingUtilities.invokeLater(new MainGUI());
        try {
            c = new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        calendar = Calendar.getInstance();
        frame = new JFrame("Quiz Tool");
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        frame.setSize(275, 210);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        //login elements
        usernameLabel = new JLabel("Username:");
        usernameText = new JTextField(12);
        usernameText.setMaximumSize(usernameText.getPreferredSize());
        passwordLabel = new JLabel("Password:");
        passwordText = new JPasswordField(12);
        passwordText.setMaximumSize(passwordText.getPreferredSize());
        usernameLabelC = new JLabel("Username:");
        usernameTextC = new JTextField(12);
        usernameTextC.setMaximumSize(usernameTextC.getPreferredSize());
        passwordLabelC = new JLabel("Password:");
        passwordTextC = new JPasswordField(12);
        passwordTextC.setMaximumSize(passwordTextC.getPreferredSize());
        loginButton = new JButton("Login");
        createButton = new JButton("Create Account");
        switchToLoginButton = new JButton("Switch to Login");
        switchToCreateButton = new JButton("Create New Account");
        roleLabel = new JLabel("I am a: ");
        String[] roleOptions = {"Select a role...", "Teacher", "Student"};
        roleSelect = new JComboBox<>(roleOptions);
        roleSelect.setMaximumSize(roleSelect.getPreferredSize());

        //teacher elements
        String[] exampleList = {"Item One", "Item Two", "Item Also Two", "Wait Where Is Three", "Three"};
        //truncate list items so it all fits nicely
        coursesTM = new JList<>(exampleList);
        courseScrollTM = new JScrollPane(coursesTM);
        courseScrollTM.setMaximumSize(new Dimension(150, 300));
        addCourseTM = new JButton("Add Course");
        openCourseTM = new JButton("Open Course");
        deleteCourseTM = new JButton("Delete Course");
        gradeQuizTM = new JButton("Grade Quiz");
        courseNameTAC = new JTextField(10);
        courseNameTAC.setMaximumSize(courseNameTAC.getPreferredSize());
        addCourseTAC = new JButton("Add Course");
        cancelTAC = new JButton("Cancel");
        quizzesTCD = new JList<>(exampleList);
        quizScrollTCD = new JScrollPane(quizzesTCD);
        quizScrollTCD.setMaximumSize(new Dimension(150, 300));
        addQuizTCD = new JButton("Add Quiz");
        editQuizTCD = new JButton("Edit Quiz");
        deleteQuizTCD = new JButton("Delete Quiz");
        exitCourseTCD = new JButton("Course Menu");
        qNameLabelTQC = new JLabel("Quiz Name:");
        quizNameTQC = new JTextField(40);
        quizNameTQC.setMaximumSize(quizNameTQC.getPreferredSize());
        numQuesLabelTQC = new JLabel("Number of Questions:");
        numQuestionsTQC = new JTextField(5);
        numQuestionsTQC.setMaximumSize(numQuestionsTQC.getPreferredSize());
        createQuizTQC = new JButton("Create Quiz");
        quesNameLabelTKC = new JLabel("Question Name:");
        questionNameTKC = new JTextField(40);
        questionNameTKC.setMaximumSize(questionNameTKC.getPreferredSize());
        numAnsLabelTKC = new JLabel("Number of Answers:");
        numAnswersTKC = new JTextField(5);
        numAnswersTKC.setMaximumSize(numAnswersTKC.getPreferredSize());
        createQuesTKC = new JButton("Create Question");
        setAnswersTAC = new JButton("Set Answers");
        quesLabelTQE = new JLabel("Question:");
        questionTQE = new JTextField(40);
        questionTQE.setMaximumSize(questionTQE.getPreferredSize());
        setChoicesTQE = new JButton("Set Choices");
        questionTG = new JLabel("Question");
        ansPromptTG = new JLabel("Student Answer:");
        answerTG = new JLabel("Answer");
        scorePromptTG = new JLabel("Score:");
        scoreTG = new JTextField(5);
        scoreTG.setMaximumSize(scoreTG.getPreferredSize());
        scoreQuesTG = new JButton("Score Question");
        studentsTStS = new JList<>(exampleList);
        stuScrollTStS = new JScrollPane(studentsTStS);
        stuScrollTStS.setMaximumSize(new Dimension(150, 300));
        selectStuTStS = new JButton("Select Student");
        courseMenuTStS = new JButton("Course Menu");
        subsTSuS = new JList<>(exampleList);
        subScrollTSuS = new JScrollPane(subsTSuS);
        subScrollTSuS.setMaximumSize(new Dimension(150, 300));
        selectSubTSuS = new JButton("Select Quiz");
        stuMenuTSuS = new JButton("Change Student");
        courseMenuTSuS = new JButton("Course Menu");
        quizRandomizedTQC = new JCheckBox("Randomize Question Order");
        questionRandomizedTKC = new JCheckBox("Randomize Answer Order");


        //student elements
        submissionsSM = new JList<>(exampleList);
        subScrollSM = new JScrollPane(submissionsSM);
        subScrollSM.setMaximumSize(new Dimension(500, 150));
        viewSubSM = new JButton("View Submission");
        takeQuizSM = new JButton("Take Quiz");
        questionSQD = new JLabel("question");
        ansPromptSQD = new JLabel("Your Answer:");
        answerSQD = new JLabel("answer");
        scorePromptSQD = new JLabel("Your Score:");
        scoreSQD = new JLabel("No Score Entered");
        nextQuesSQD = new JButton("Next Question");
        exitSQD = new JButton("Exit");
        courseListSTQ = new JList<>(exampleList);
        cListScrollSTQ = new JScrollPane(courseListSTQ);
        cListScrollSTQ.setMaximumSize(new Dimension(500, 150));
        selectCourseSTQ = new JButton("Select Course");
        cancelCourseSTQ = new JButton("Cancel");
        quizListSTQ = new JList<>(exampleList);
        qListScrollSTQ = new JScrollPane(quizListSTQ);
        qListScrollSTQ.setMaximumSize(new Dimension(500, 150));
        selectQuizSTQ = new JButton("Select Quiz");
        changeCourseSTQ = new JButton("Change Course");
        cancelQuizSTQ = new JButton("Cancel");
        questionSTQ = new JLabel("question");
        answerOptionsSTQ = new JList<>(exampleList);
        nextQuestionSTQ = new JButton("Next Question");

        JPanel centerLogin = new JPanel();
        centerLogin.setLayout(new BoxLayout(centerLogin, BoxLayout.PAGE_AXIS));
        JPanel centerCreate = new JPanel();
        centerCreate.setLayout(new BoxLayout(centerCreate, BoxLayout.PAGE_AXIS));

        JPanel courseList = new JPanel();
        courseList.setLayout(new BoxLayout(courseList, BoxLayout.PAGE_AXIS));
        JPanel teacherMain = new JPanel();
        teacherMain.setLayout(new BoxLayout(teacherMain, BoxLayout.PAGE_AXIS));
        JPanel quizList = new JPanel();
        quizList.setLayout(new BoxLayout(quizList, BoxLayout.PAGE_AXIS));
        JPanel teacherCourseDisplay = new JPanel();
        teacherCourseDisplay.setLayout(new BoxLayout(teacherCourseDisplay, BoxLayout.PAGE_AXIS));
        JPanel teacherAddCourse = new JPanel();
        teacherAddCourse.setLayout(new BoxLayout(teacherAddCourse, BoxLayout.PAGE_AXIS));
        JPanel teacherCreateQuiz = new JPanel();
        teacherCreateQuiz.setLayout(new BoxLayout(teacherCreateQuiz, BoxLayout.PAGE_AXIS));
        JPanel teacherCreateQuestion = new JPanel();
        teacherCreateQuestion.setLayout(new BoxLayout(teacherCreateQuestion, BoxLayout.PAGE_AXIS));
        JPanel teacherSetAnswers = new JPanel();
        teacherSetAnswers.setLayout(new BoxLayout(teacherSetAnswers, BoxLayout.PAGE_AXIS));
        JPanel teacherEditQuiz = new JPanel();
        teacherEditQuiz.setLayout(new BoxLayout(teacherEditQuiz, BoxLayout.PAGE_AXIS));
        JPanel teacherGrading = new JPanel();
        teacherGrading.setLayout(new BoxLayout(teacherGrading, BoxLayout.PAGE_AXIS));
        JPanel teacherStudentSelect = new JPanel();
        teacherStudentSelect.setLayout(new BoxLayout(teacherStudentSelect, BoxLayout.PAGE_AXIS));
        JPanel listStudent = new JPanel();
        listStudent.setLayout(new BoxLayout(listStudent, BoxLayout.PAGE_AXIS));
        JPanel teacherSelectSubmission = new JPanel();
        teacherSelectSubmission.setLayout(new BoxLayout(teacherSelectSubmission, BoxLayout.PAGE_AXIS));
        JPanel listSubmission = new JPanel();
        listSubmission.setLayout(new BoxLayout(listSubmission, BoxLayout.PAGE_AXIS));

        JPanel studentMain = new JPanel();
        studentMain.setLayout(new BoxLayout(studentMain, BoxLayout.PAGE_AXIS));
        JPanel studentQuizDisplay = new JPanel();
        studentQuizDisplay.setLayout(new BoxLayout(studentQuizDisplay, BoxLayout.PAGE_AXIS));
        JPanel studentSelectCourse = new JPanel();
        studentSelectCourse.setLayout(new BoxLayout(studentSelectCourse, BoxLayout.PAGE_AXIS));
        JPanel studentSelectQuiz = new JPanel();
        studentSelectQuiz.setLayout(new BoxLayout(studentSelectQuiz, BoxLayout.PAGE_AXIS));
        JPanel studentTakeQuiz = new JPanel();
        studentTakeQuiz.setLayout(new BoxLayout(studentTakeQuiz, BoxLayout.PAGE_AXIS));

        centerLogin.add(Box.createRigidArea(new Dimension(0, 5)));
        centerLogin.add(usernameLabel);
        usernameText.setAlignmentX(0f);
        centerLogin.add(usernameText);
        centerLogin.add(passwordLabel);
        passwordText.setAlignmentX(0f);
        centerLogin.add(passwordText);
        centerLogin.add(Box.createRigidArea(new Dimension(0, 10)));
        centerLogin.add(loginButton);
        centerLogin.add(Box.createRigidArea(new Dimension(0, 20)));
        centerLogin.setAlignmentX(0.5f);
        centerLogin.add(switchToCreateButton);

        centerCreate.setAlignmentX(0.5f);
        centerCreate.add(usernameLabelC);
        usernameTextC.setAlignmentX(0f);
        centerCreate.add(usernameTextC);
        centerCreate.add(passwordLabelC);
        passwordTextC.setAlignmentX(0f);
        centerCreate.add(passwordTextC);
        centerCreate.add(roleLabel);
        roleSelect.setAlignmentX(0f);
        centerCreate.add(roleSelect);
        centerCreate.add(Box.createRigidArea(new Dimension(0, 10)));
        centerCreate.add(createButton);
        centerCreate.add(Box.createRigidArea(new Dimension(0, 20)));
        centerCreate.add(switchToLoginButton);


        courseScrollTM.setAlignmentX(0f);
        courseList.add(courseScrollTM);
        courseList.setAlignmentX(Component.LEFT_ALIGNMENT);

        teacherMain.add(Box.createRigidArea(new Dimension(0, 30)));
        teacherMain.add(addCourseTM);
        teacherMain.add(Box.createRigidArea(new Dimension(0, 5)));
        teacherMain.add(openCourseTM);
        teacherMain.add(Box.createRigidArea(new Dimension(0, 5)));
        teacherMain.add(deleteCourseTM);
        teacherMain.add(Box.createRigidArea(new Dimension(0, 35)));
        teacherMain.add(gradeQuizTM);

        quizScrollTCD.setAlignmentX(0f);
        quizList.add(quizScrollTCD);
        quizList.setAlignmentX(Component.LEFT_ALIGNMENT);

        teacherCourseDisplay.add(Box.createRigidArea(new Dimension(0, 30)));
        teacherCourseDisplay.add(addQuizTCD);
        teacherCourseDisplay.add(Box.createRigidArea(new Dimension(0, 5)));
        teacherCourseDisplay.add(editQuizTCD);
        teacherCourseDisplay.add(Box.createRigidArea(new Dimension(0, 5)));
        teacherCourseDisplay.add(deleteQuizTCD);
        teacherCourseDisplay.add(Box.createRigidArea(new Dimension(0, 35)));
        teacherCourseDisplay.add(exitCourseTCD);

        teacherAddCourse.setAlignmentX(0.5f);
        teacherAddCourse.add(Box.createRigidArea(new Dimension(0, 15)));
        courseNameTAC.setAlignmentX(0f);
        teacherAddCourse.add(courseNameTAC);
        teacherAddCourse.add(addCourseTAC);
        teacherAddCourse.add(Box.createRigidArea(new Dimension(0, 10)));
        teacherAddCourse.add(cancelTAC);

        teacherCreateQuiz.add(qNameLabelTQC);
        teacherCreateQuiz.add(quizNameTQC);
        teacherCreateQuiz.add(numQuesLabelTQC);
        teacherCreateQuiz.add(numQuestionsTQC);
        teacherCreateQuiz.add(quizRandomizedTQC);
        teacherCreateQuiz.add(Box.createRigidArea(new Dimension(0, 5)));
        teacherCreateQuiz.add(createQuizTQC);

        teacherCreateQuestion.add(quesNameLabelTKC);
        teacherCreateQuestion.add(questionNameTKC);
        teacherCreateQuestion.add(numAnsLabelTKC);
        teacherCreateQuestion.add(numAnswersTKC);
        teacherCreateQuestion.add(questionRandomizedTKC);
        teacherCreateQuestion.add(Box.createRigidArea(new Dimension(0, 5)));
        teacherCreateQuestion.add(createQuesTKC);

        teacherStudentSelect.add(Box.createRigidArea(new Dimension(0, 50)));
        teacherStudentSelect.add(selectStuTStS);
        teacherStudentSelect.add(Box.createRigidArea(new Dimension(0, 10)));
        teacherStudentSelect.add(courseMenuTStS);
        listStudent.add(stuScrollTStS);

        subScrollTSuS.setAlignmentX(0f);
        teacherSelectSubmission.add(Box.createRigidArea(new Dimension(0, 50)));
        teacherSelectSubmission.add(selectSubTSuS);
        teacherSelectSubmission.add(Box.createRigidArea(new Dimension(0, 10)));
        teacherSelectSubmission.add(stuMenuTSuS);
        teacherSelectSubmission.add(Box.createRigidArea(new Dimension(0, 5)));
        teacherSelectSubmission.add(courseMenuTSuS);
        listSubmission.add(subScrollTSuS);

        teacherGrading.add(questionTG);
        teacherGrading.add(Box.createRigidArea(new Dimension(0, 10)));
        teacherGrading.add(ansPromptTG);
        teacherGrading.add(answerTG);
        teacherGrading.add(Box.createRigidArea(new Dimension(0, 10)));
        teacherGrading.add(scorePromptTG);
        teacherGrading.add(scoreTG);
        teacherGrading.add(scoreQuesTG);

        studentMain.add(subScrollSM);
        studentMain.add(Box.createRigidArea(new Dimension(0, 10)));
        studentMain.add(viewSubSM);
        studentMain.add(Box.createRigidArea(new Dimension(0, 10)));
        studentMain.add(takeQuizSM);

        studentQuizDisplay.setAlignmentX(0.05f);
        studentQuizDisplay.add(questionSQD);
        studentQuizDisplay.add(Box.createRigidArea(new Dimension(0, 10)));
        studentQuizDisplay.add(ansPromptSQD);
        studentQuizDisplay.add(answerSQD);
        studentQuizDisplay.add(Box.createRigidArea(new Dimension(0, 10)));
        studentQuizDisplay.add(scorePromptSQD);
        studentQuizDisplay.add(scoreSQD);
        studentQuizDisplay.add(Box.createRigidArea(new Dimension(0, 10)));
        studentQuizDisplay.add(nextQuesSQD);
        studentQuizDisplay.add(Box.createRigidArea(new Dimension(0, 5)));
        studentQuizDisplay.add(exitSQD);

        studentSelectCourse.add(cListScrollSTQ);
        studentSelectCourse.add(Box.createRigidArea(new Dimension(0, 10)));
        studentSelectCourse.add(selectCourseSTQ);
        studentSelectCourse.add(Box.createRigidArea(new Dimension(0, 10)));
        studentSelectCourse.add(cancelCourseSTQ);

        studentSelectQuiz.add(qListScrollSTQ);
        studentSelectQuiz.add(Box.createRigidArea(new Dimension(0, 10)));
        studentSelectQuiz.add(selectQuizSTQ);
        studentSelectQuiz.add(Box.createRigidArea(new Dimension(0, 10)));
        studentSelectQuiz.add(changeCourseSTQ);
        studentSelectQuiz.add(Box.createRigidArea(new Dimension(0, 10)));
        studentSelectQuiz.add(cancelQuizSTQ);

        studentTakeQuiz.add(questionSTQ);
        studentTakeQuiz.add(Box.createRigidArea(new Dimension(0, 10)));
        studentTakeQuiz.add(answerOptionsSTQ);
        studentTakeQuiz.add(Box.createRigidArea(new Dimension(0, 10)));
        studentTakeQuiz.add(nextQuestionSTQ);



        content.add(centerLogin);
        frame.setSize(275, 210);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameText.getText().equals("") || new String(passwordText.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean[] loginResult = c.login(usernameText.getText(), new String(passwordText.getPassword()));
                    if (!loginResult[0]) {
                        JOptionPane.showMessageDialog(null, "Invalid login",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (loginResult[1]) {
                        frame.dispose();
                        content.remove(centerLogin);
                        content.setLayout(new BorderLayout());
                        coursesTM.setListData(c.getCourses());
                        content.add(teacherMain, BorderLayout.EAST);
                        content.add(courseList, BorderLayout.WEST);

                        frame.setSize(new Dimension(300, 250));
                        frame.setVisible(true);
                        currentLocation = 1;
                    } else {
                        frame.dispose();
                        content.remove(centerLogin);
                        currentStudent = usernameText.getText();
                        currentSubmissions = c.getSubmissions(currentStudent);
                        if (currentSubmissions == null) {
                            submissionsSM.setListData(new String[0]);
                        } else {
                            String[] subLabel = new String[currentSubmissions.length];
                            for (int i = 0; i < currentSubmissions.length; i++) {
                                subLabel[i] = currentSubmissions[i].getName();
                                subLabel[i] += ":" + currentSubmissions[i].getTimeStamp();
                            }
                            submissionsSM.setListData(subLabel);
                        }
                        content.add(studentMain);
                        frame.setSize(new Dimension(300, 275));
                        frame.setVisible(true);
                        currentLocation = 2;
                    }
                }
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameTextC.getText().equals("") || new String(passwordTextC.getPassword()).equals("") ||
                        "Select a role...".equals(roleSelect.getSelectedItem())) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean isTeacher = "Teacher".equals(roleSelect.getSelectedItem());
                    boolean createAcctSuccess = c.createAccount(usernameTextC.getText(), new
                            String(passwordTextC.getPassword()), isTeacher);
                    if (!createAcctSuccess) {
                        JOptionPane.showMessageDialog(null, "Account creation failed: username " +
                                        "in use or invalid character(s) used", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (isTeacher) {
                        frame.dispose();
                        content.remove(centerCreate);
                        content.setLayout(new BorderLayout());
                        coursesTM.setListData(c.getCourses());
                        content.add(teacherMain, BorderLayout.EAST);
                        content.add(courseList, BorderLayout.WEST);
                        frame.setSize(new Dimension(300, 250));
                        frame.setVisible(true);
                        currentLocation = 1;
                    } else {
                        frame.dispose();
                        content.remove(centerCreate);
                        currentStudent = usernameTextC.getText();
                        currentSubmissions = c.getSubmissions(currentStudent);
                        if (currentSubmissions == null) {
                            submissionsSM.setListData(new String[0]);
                        } else {
                            String[] subLabel = new String[currentSubmissions.length];
                            for (int i = 0; i < currentSubmissions.length; i++) {
                                subLabel[i] = currentSubmissions[i].getName();
                                subLabel[i] += ":" + currentSubmissions[i].getTimeStamp();
                            }
                            submissionsSM.setListData(subLabel);
                        }
                        content.add(studentMain);
                        frame.setSize(new Dimension(300, 275));
                        frame.setVisible(true);
                        currentLocation = 2;
                    }
                }
            }
        });

        switchToCreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(centerLogin);
                frame.setSize(new Dimension(275, 250));
                content.add(centerCreate);
                frame.setVisible(true);
            }
        });

        switchToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(centerCreate);
                frame.setSize(new Dimension(275, 210));
                content.add(centerLogin);
                frame.setVisible(true);
            }
        });

        addCourseTM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(teacherMain);
                content.remove(courseList);
                content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
                frame.setSize(new Dimension(275, 150));
                content.add(teacherAddCourse);
                frame.setVisible(true);
                currentLocation = 0;
            }
        });

        addCourseTAC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (c.addCourse(courseNameTAC.getText())){
                    frame.dispose();
                    content.remove(teacherAddCourse);
                    courseNameTAC.setText("");
                    content.setLayout(new BorderLayout());
                    coursesTM.setListData(c.getCourses());
                    content.add(teacherMain, BorderLayout.EAST);
                    content.add(courseList, BorderLayout.WEST);

                    frame.setSize(new Dimension(300, 250));
                    frame.setVisible(true);
                    currentLocation = 1;
                } else {
                    JOptionPane.showMessageDialog(null, "Course already exists",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelTAC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(teacherAddCourse);
                content.setLayout(new BorderLayout());
                coursesTM.setListData(c.getCourses());
                content.add(teacherMain, BorderLayout.EAST);
                content.add(courseList, BorderLayout.WEST);

                frame.setSize(new Dimension(300, 250));
                frame.setVisible(true);
                currentLocation = 1;
            }
        });

        openCourseTM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (coursesTM.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a course",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                frame.dispose();
                content.remove(teacherMain);
                content.remove(courseList);
                currentCourse = coursesTM.getSelectedValue();
                quizzesTCD.setListData(c.getQuizzes(currentCourse));
                content.add(teacherCourseDisplay, BorderLayout.EAST);
                content.add(quizList, BorderLayout.WEST);

                frame.setVisible(true);
                currentLocation = 3;
            }
        });

        exitCourseTCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(teacherCourseDisplay);
                content.remove(quizList);
                currentCourse = "";
                coursesTM.setListData(c.getCourses());
                content.add(teacherMain, BorderLayout.EAST);
                content.add(courseList, BorderLayout.WEST);
                frame.setVisible(true);
                currentLocation = 1;
            }
        });

        addQuizTCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(teacherCourseDisplay);
                content.remove(quizList);
                content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
                content.add(teacherCreateQuiz);
                frame.setSize(new Dimension(300, 180));
                frame.setVisible(true);
                currentLocation = 0;
            }
        });

        createQuizTQC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quizNameTQC.getText().equals("") || numQuestionsTQC.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        numQuestions = Integer.parseInt(numQuestionsTQC.getText());
                        currentQuestions = new Question[numQuestions];
                        questionTracker = 0;
                        currentQuizName = quizNameTQC.getText();
                        randomizeQuiz = quizRandomizedTQC.isSelected();
                        frame.dispose();
                        content.remove(teacherCreateQuiz);
                        quizNameTQC.setText("");
                        numQuestionsTQC.setText("");
                        content.add(teacherCreateQuestion);
                        frame.setVisible(true);
                    } catch (NumberFormatException err) {
                        JOptionPane.showMessageDialog(null, "Please enter a number",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        createQuesTKC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (questionNameTKC.getText().equals("") || numAnswersTKC.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        currentQuesPrompt = questionNameTKC.getText();
                        numAnswers = Integer.parseInt(numAnswersTKC.getText());
                        currentAnswers = new String[numAnswers];
                        randomizeQuestion = questionRandomizedTKC.isSelected();
                        frame.dispose();
                        content.remove(teacherCreateQuestion);
                        questionNameTKC.setText("");
                        numAnswersTKC.setText("");
                        ansLabelsTAC = new JLabel[numAnswers];
                        answersTAC = new JTextField[numAnswers];
                        int heightVal = 80;
                        for (int i = 0; i < numAnswers; i++) {
                            ansLabelsTAC[i] = new JLabel("Answer Choice " + (i + 1));
                            answersTAC[i] = new JTextField(40);
                            answersTAC[i].setMaximumSize(answersTAC[i].getPreferredSize());
                            teacherSetAnswers.add(ansLabelsTAC[i]);
                            teacherSetAnswers.add(answersTAC[i]);
                            heightVal += 35;
                        }
                        teacherSetAnswers.add(setAnswersTAC);
                        content.add(teacherSetAnswers);
                        frame.setSize(new Dimension(300, heightVal));
                        frame.setVisible(true);
                    } catch (NumberFormatException err) {
                        JOptionPane.showMessageDialog(null, "Please enter a number",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        setAnswersTAC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean emptyText = false;
                for (int i = 0; i < numAnswers; i++) {
                    if (answersTAC[i].getText().equals("")) {
                        emptyText = true;
                        break;
                    }
                }
                if (emptyText) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    for (int i = 0; i < numAnswers; i++) {
                        currentAnswers[i] = answersTAC[i].getText();
                    }
                    currentQuestions[questionTracker] = new Question(currentQuesPrompt, currentAnswers);
                    currentQuestions[questionTracker].setIsRandomized(randomizeQuestion);
                    frame.dispose();
                    content.remove(teacherSetAnswers);
                    teacherSetAnswers.removeAll();
                    questionTracker++;
                    if (questionTracker >= numQuestions) {
                        currentQuiz = new Quiz(currentQuizName, currentQuestions);
                        currentQuiz.setIsRandomized(randomizeQuiz);
                        if (!c.addQuiz(currentCourse, currentQuiz)) {
                            JOptionPane.showMessageDialog(null, "Quiz creation failed",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        content.setLayout(new BorderLayout());
                        quizzesTCD.setListData(c.getQuizzes(currentCourse));
                        content.add(teacherCourseDisplay, BorderLayout.EAST);
                        content.add(quizList, BorderLayout.WEST);
                        currentLocation = 3;
                        frame.setSize(new Dimension(300, 250));
                    } else {
                        content.add(teacherCreateQuestion);
                        frame.setSize(new Dimension(300, 180));
                    }
                    frame.setVisible(true);
                }
            }
        });

        editQuizTCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quizzesTCD.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a quiz",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                frame.dispose();
                content.remove(teacherCourseDisplay);
                content.remove(quizList);
                //gets the quiz, sets numQuestions and numAnswers
                currentQuiz = c.getQuiz(currentCourse, quizzesTCD.getSelectedValue());
                numQuestions = currentQuiz.getLength();
                questionTracker = 0;
                currentQuestions = new Question[numQuestions];
                currentQuestions[questionTracker] = currentQuiz.getQuestion(questionTracker + 1);
                currentAnswers = currentQuestions[questionTracker].getChoices();
                numAnswers = currentAnswers.length;
                teacherEditQuiz.removeAll();
                ansLabelsTQE = new JLabel[numAnswers];
                answersTQE = new JTextField[numAnswers];
                teacherEditQuiz.add(quesLabelTQE);
                //auto-fill text boxes
                questionTQE.setText(currentQuestions[questionTracker].getQuestion());
                teacherEditQuiz.add(questionTQE);
                int heightVal = 115;
                for (int i = 0; i < numAnswers; i++) {
                    ansLabelsTQE[i] = new JLabel("Answer Choice " + (i + 1));
                    answersTQE[i] = new JTextField(40);
                    answersTQE[i].setMaximumSize(answersTQE[i].getPreferredSize());
                    answersTQE[i].setText(currentAnswers[i]);
                    teacherEditQuiz.add(ansLabelsTQE[i]);
                    teacherEditQuiz.add(answersTQE[i]);
                    heightVal += 35;
                }
                teacherEditQuiz.add(setChoicesTQE);
                content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
                content.add(teacherEditQuiz);
                frame.setSize(new Dimension(300, heightVal));
                frame.setVisible(true);
                currentLocation = 0;
            }
        });

        setChoicesTQE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(teacherEditQuiz);
                for (int i = 0; i < numAnswers; i++) {
                    currentAnswers[i] = answersTQE[i].getText();
                }
                currentQuestions[questionTracker].setChoices(currentAnswers);
                currentQuestions[questionTracker].setQuestion(questionTQE.getText());
                currentQuiz.setQuestion(questionTracker, currentQuestions[questionTracker]);
                questionTracker++;
                if (questionTracker >= numQuestions) {
                    c.modifyQuiz(currentCourse, currentQuiz);
                    content.setLayout(new BorderLayout());
                    quizzesTCD.setListData(c.getQuizzes(currentCourse));
                    content.add(teacherCourseDisplay, BorderLayout.EAST);
                    content.add(quizList, BorderLayout.WEST);
                    currentLocation = 3;
                    frame.setSize(new Dimension(300, 250));
                } else {
                    currentQuestions[questionTracker] = currentQuiz.getQuestion(questionTracker + 1);
                    currentAnswers = currentQuestions[questionTracker].getChoices();
                    numAnswers = currentAnswers.length;
                    teacherEditQuiz.removeAll();
                    ansLabelsTQE = new JLabel[numAnswers];
                    answersTQE = new JTextField[numAnswers];
                    teacherEditQuiz.add(quesLabelTQE);
                    //auto-fill text boxes
                    questionTQE.setText(currentQuestions[questionTracker].getQuestion());
                    teacherEditQuiz.add(questionTQE);
                    int heightVal = 115;
                    for (int i = 0; i < numAnswers; i++) {
                        ansLabelsTQE[i] = new JLabel("Answer Choice " + (i + 1));
                        answersTQE[i] = new JTextField(40);
                        answersTQE[i].setMaximumSize(answersTQE[i].getPreferredSize());
                        answersTQE[i].setText(currentAnswers[i]);
                        teacherEditQuiz.add(ansLabelsTQE[i]);
                        teacherEditQuiz.add(answersTQE[i]);
                        heightVal += 35;
                    }
                    teacherEditQuiz.add(setChoicesTQE);
                    content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
                    content.add(teacherEditQuiz);
                    frame.setSize(new Dimension(300, heightVal));
                }
                frame.setVisible(true);
            }
        });

        gradeQuizTM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(courseList);
                content.remove(teacherMain);
                studentsTStS.setListData(c.getStudents());
                content.add(teacherStudentSelect, BorderLayout.EAST);
                content.add(listStudent, BorderLayout.WEST);
                frame.setVisible(true);
                currentLocation = 4;
            }
        });

        selectStuTStS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (studentsTStS.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a student",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                currentStudent = studentsTStS.getSelectedValue();
                frame.dispose();
                content.remove(listStudent);
                content.remove(teacherStudentSelect);
                currentSubmissions = c.getSubmissions(currentStudent);
                if (currentSubmissions == null) {
                    subsTSuS.setListData(new String[0]);
                } else {
                    String[] subLabel = new String[currentSubmissions.length];
                    for (int i = 0; i < currentSubmissions.length; i++) {
                        subLabel[i] = currentSubmissions[i].getName();
                        subLabel[i] += ":" + currentSubmissions[i].getTimeStamp();
                    }
                    subsTSuS.setListData(subLabel);
                }
                content.add(teacherSelectSubmission, BorderLayout.EAST);
                content.add(listSubmission, BorderLayout.WEST);
                frame.setVisible(true);
                currentLocation = 5;
            }
        });

        stuMenuTSuS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(listSubmission);
                content.remove(teacherSelectSubmission);
                studentsTStS.setListData(c.getStudents());
                content.add(teacherStudentSelect, BorderLayout.EAST);
                content.add(listStudent, BorderLayout.WEST);
                frame.setVisible(true);
                currentLocation = 4;
            }
        });

        courseMenuTSuS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(listSubmission);
                content.remove(teacherSelectSubmission);
                coursesTM.setListData(c.getCourses());
                content.add(teacherMain, BorderLayout.EAST);
                content.add(courseList, BorderLayout.WEST);
                frame.setVisible(true);
                currentLocation = 1;
            }
        });

        courseMenuTStS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(listStudent);
                content.remove(teacherStudentSelect);
                coursesTM.setListData(c.getCourses());
                content.add(teacherMain, BorderLayout.EAST);
                content.add(courseList, BorderLayout.WEST);
                frame.setVisible(true);
                currentLocation = 1;
            }
        });

        selectSubTSuS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (subsTSuS.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a submission",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                frame.dispose();
                //set number of questions in quiz
                currentQuiz = currentSubmissions[subsTSuS.getSelectedIndex()];
                numQuestions = currentQuiz.getLength();
                questionTracker = 0;
                //sets questions and answers
                questionTG.setText(currentQuiz.getQuestion(questionTracker + 1).getQuestion());
                answerTG.setText(currentQuiz.getQuestion(questionTracker + 1).getOriginalChoices()
                        [currentQuiz.getQuestion(questionTracker + 1).getStudentAnswer()]);
                content.remove(listSubmission);
                content.remove(teacherSelectSubmission);
                content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
                content.add(teacherGrading);
                frame.setSize(new Dimension(300, 175));
                frame.setVisible(true);
                currentLocation = 0;
            }
        });

        scoreQuesTG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    currentQuiz.getQuestion(questionTracker + 1).setGrade(Integer.parseInt(scoreTG.getText()));
                    scoreTG.setText("");
                    content.remove(teacherGrading);
                    questionTracker++;
                    if (questionTracker >= numQuestions) {
                        c.submitQuiz(currentStudent, currentQuiz);
                        content.setLayout(new BorderLayout());
                        coursesTM.setListData(c.getCourses());
                        content.add(teacherMain, BorderLayout.EAST);
                        content.add(courseList, BorderLayout.WEST);
                        currentLocation = 1;
                        frame.setSize(new Dimension(300, 250));
                    } else {
                        questionTG.setText(currentQuiz.getQuestion(questionTracker + 1).getQuestion());
                        answerTG.setText(currentQuiz.getQuestion(questionTracker + 1).getOriginalChoices()
                                [currentQuiz.getQuestion(questionTracker + 1).getStudentAnswer()]);
                        content.add(teacherGrading);
                    }
                    frame.setVisible(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a number",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        viewSubSM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (submissionsSM.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a submission",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                frame.dispose();
                content.remove(studentMain);
                currentQuiz = c.getSubmissions(currentStudent)[submissionsSM.getSelectedIndex()];
                questionTracker = 0;
                numQuestions = currentQuiz.getLength();
                questionSQD.setText(currentQuiz.getQuestion(questionTracker + 1).getQuestion());
                answerSQD.setText(currentQuiz.getQuestion(questionTracker + 1).getOriginalChoices()
                        [currentQuiz.getQuestion(questionTracker + 1).getStudentAnswer()]);
                int score = currentQuiz.getQuestion(questionTracker + 1).getGrade();
                if (score == -1) {
                    scoreSQD.setText("No Score Entered");
                } else {
                    scoreSQD.setText("" + score);
                }
                content.add(studentQuizDisplay);
                frame.setSize(new Dimension(300, 225));
                frame.setVisible(true);
                currentLocation = 0;
            }
        });

        exitSQD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(studentQuizDisplay);
                currentSubmissions = c.getSubmissions(currentStudent);
                if (currentSubmissions == null) {
                    submissionsSM.setListData(new String[0]);
                } else {
                    String[] subLabel = new String[currentSubmissions.length];
                    for (int i = 0; i < currentSubmissions.length; i++) {
                        subLabel[i] = currentSubmissions[i].getName();
                        subLabel[i] += ":" + currentSubmissions[i].getTimeStamp();
                    }
                    submissionsSM.setListData(subLabel);
                }
                content.add(studentMain);
                frame.setSize(new Dimension(300, 275));
                frame.setVisible(true);
                currentLocation = 2;
            }
        });

        nextQuesSQD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(studentQuizDisplay);
                questionTracker++;
                if (questionTracker >= numQuestions) {
                    currentSubmissions = c.getSubmissions(currentStudent);
                    if (currentSubmissions == null) {
                        submissionsSM.setListData(new String[0]);
                    } else {
                        String[] subLabel = new String[currentSubmissions.length];
                        for (int i = 0; i < currentSubmissions.length; i++) {
                            subLabel[i] = currentSubmissions[i].getName();
                            subLabel[i] += ":" + currentSubmissions[i].getTimeStamp();
                        }
                        submissionsSM.setListData(subLabel);
                    }
                    content.add(studentMain);
                    currentLocation = 2;
                    frame.setSize(new Dimension(300, 275));

                } else {
                    questionSQD.setText(currentQuiz.getQuestion(questionTracker + 1).getQuestion());
                    answerSQD.setText(currentQuiz.getQuestion(questionTracker + 1).getOriginalChoices()
                            [currentQuiz.getQuestion(questionTracker + 1).getStudentAnswer()]);
                    int score = currentQuiz.getQuestion(questionTracker + 1).getGrade();
                    if (score == -1) {
                        scoreSQD.setText("No Score Entered");
                    } else {
                        scoreSQD.setText("" + score);
                    }
                    content.add(studentQuizDisplay);
                }
                frame.setVisible(true);
            }
        });

        takeQuizSM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(studentMain);
                courseListSTQ.setListData(c.getCourses());
                content.add(studentSelectCourse);
                frame.setVisible(true);
                currentLocation = 6;
            }
        });

        selectCourseSTQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (courseListSTQ.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a course",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                frame.dispose();
                content.remove(studentSelectCourse);
                quizListSTQ.setListData(c.getQuizzes(courseListSTQ.getSelectedValue()));
                content.add(studentSelectQuiz);
                frame.setSize(new Dimension(300, 300));
                frame.setVisible(true);
                currentLocation = 7;
            }
        });

        cancelCourseSTQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(studentSelectCourse);
                currentSubmissions = c.getSubmissions(currentStudent);
                if (currentSubmissions == null) {
                    submissionsSM.setListData(new String[0]);
                } else {
                    String[] subLabel = new String[currentSubmissions.length];
                    for (int i = 0; i < currentSubmissions.length; i++) {
                        subLabel[i] = currentSubmissions[i].getName();
                        subLabel[i] += ":" + currentSubmissions[i].getTimeStamp();
                    }
                    submissionsSM.setListData(subLabel);
                }
                content.add(studentMain);
                frame.setVisible(true);
                currentLocation = 2;
            }
        });

        cancelQuizSTQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(studentSelectQuiz);
                currentSubmissions = c.getSubmissions(currentStudent);
                if (currentSubmissions == null) {
                    submissionsSM.setListData(new String[0]);
                } else {
                    String[] subLabel = new String[currentSubmissions.length];
                    for (int i = 0; i < currentSubmissions.length; i++) {
                        subLabel[i] = currentSubmissions[i].getName();
                        subLabel[i] += ":" + currentSubmissions[i].getTimeStamp();
                    }
                    submissionsSM.setListData(subLabel);
                }
                content.add(studentMain);
                frame.setSize(new Dimension(300, 275));
                frame.setVisible(true);
                currentLocation = 2;
            }
        });

        changeCourseSTQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(studentSelectQuiz);
                courseListSTQ.setListData(c.getCourses());
                content.add(studentSelectCourse);
                frame.setSize(new Dimension(300, 275));
                frame.setVisible(true);
                currentLocation = 6;
            }
        });

        selectQuizSTQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quizListSTQ.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a quiz",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                frame.dispose();
                currentQuiz = c.getQuiz(courseListSTQ.getSelectedValue(), quizListSTQ.getSelectedValue());
                if (currentQuiz.isRandomized()) {
                    currentQuiz.randomize();
                }
                content.remove(studentSelectQuiz);
                questionTracker = 0;
                numQuestions = currentQuiz.getLength();
                if (currentQuiz.getQuestion(questionTracker + 1).isRandomized()) {
                    currentQuiz.getQuestion(questionTracker + 1).randomizeChoices();
                }
                String[] answers = currentQuiz.getQuestion(questionTracker + 1).getChoices();
                questionSTQ.setText(currentQuiz.getQuestion(questionTracker + 1).getQuestion());
                answerOptionsSTQ.setListData(answers);
                int heightVal = 100;
                heightVal += 20 * answers.length;
                content.add(studentTakeQuiz);
                frame.setSize(new Dimension(300, heightVal));
                frame.setVisible(true);
                currentLocation = 0;
            }
        });

        nextQuestionSTQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (answerOptionsSTQ.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Please select an answer",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                frame.dispose();
                currentQuiz.getQuestion(questionTracker + 1).setStudentAnswer(answerOptionsSTQ.getSelectedIndex());
                content.remove(studentTakeQuiz);
                questionTracker++;
                if (questionTracker >= numQuestions) {
                    currentQuiz.setTimeStamp(calendar.get(Calendar.YEAR) + "-" +
                            String.format("%02d", calendar.get(Calendar.MONTH)) + "-" +
                            String.format("%02d", calendar.get(Calendar.DATE)) + " " +
                            String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY)) + ":" +
                            String.format("%02d", calendar.get(Calendar.MINUTE)) + ":" +
                            String.format("%02d", calendar.get(Calendar.SECOND)));
                    c.submitQuiz(currentStudent, currentQuiz);
                    currentSubmissions = c.getSubmissions(currentStudent);
                    if (currentSubmissions == null) {
                        submissionsSM.setListData(new String[0]);
                    } else {
                        String[] subLabel = new String[currentSubmissions.length];
                        for (int i = 0; i < currentSubmissions.length; i++) {
                            subLabel[i] = currentSubmissions[i].getName();
                            subLabel[i] += ":" + currentSubmissions[i].getTimeStamp();
                        }
                        submissionsSM.setListData(subLabel);
                    }
                    content.add(studentMain);
                    currentLocation = 2;
                    frame.setSize(new Dimension(300, 275));
                } else {
                    if (currentQuiz.getQuestion(questionTracker + 1).isRandomized()) {
                        currentQuiz.getQuestion(questionTracker + 1).randomizeChoices();
                    }
                    String[] answers = currentQuiz.getQuestion(questionTracker + 1).getChoices();
                    questionSTQ.setText(currentQuiz.getQuestion(questionTracker + 1).getQuestion());
                    answerOptionsSTQ.setListData(answers);
                    int heightVal = 100;
                    heightVal += 20 * answers.length;
                    content.add(studentTakeQuiz);
                    frame.setSize(new Dimension(300, heightVal));
                }
                frame.setVisible(true);
            }
        });

        deleteCourseTM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (coursesTM.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a course",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (c.removeCourse(coursesTM.getSelectedValue())) {
                    coursesTM.setListData(c.getCourses());
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Course deletion failed",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteQuizTCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quizzesTCD.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a quiz",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (c.removeQuiz(currentCourse, quizzesTCD.getSelectedValue())) {
                    quizzesTCD.setListData(c.getCourses());
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Quiz deletion failed",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                c.closeClient();

            }
        });

    }

    public static void update() {
        SwingUtilities.invokeLater(new Thread(() -> {
            switch (currentLocation) {
                case 1:
                    coursesTM.setListData(c.getCourses());
                    break;
                case 2:
                    currentSubmissions = c.getSubmissions(currentStudent);
                    if (currentSubmissions == null) {
                        submissionsSM.setListData(new String[0]);
                    } else {
                        String[] subLabel = new String[currentSubmissions.length];
                        for (int i = 0; i < currentSubmissions.length; i++) {
                            subLabel[i] = currentSubmissions[i].getName();
                            subLabel[i] += ":" + currentSubmissions[i].getTimeStamp();
                        }
                        submissionsSM.setListData(subLabel);
                    }
                    break;
                case 3:
                    quizzesTCD.setListData(c.getQuizzes(currentCourse));
                    break;
                case 4:
                    studentsTStS.setListData(c.getStudents());
                    break;
                case 5:
                    currentSubmissions = c.getSubmissions(studentsTStS.getSelectedValue());
                    if (currentSubmissions == null) {
                        subsTSuS.setListData(new String[0]);
                    } else {
                        String[] subsLabel = new String[currentSubmissions.length];
                        for (int i = 0; i < currentSubmissions.length; i++) {
                            subsLabel[i] = currentSubmissions[i].getName();
                            subsLabel[i] += ":" + currentSubmissions[i].getTimeStamp();
                        }
                        subsTSuS.setListData(subsLabel);
                    }
                    break;
                case 6:
                    courseListSTQ.setListData(c.getCourses());
                    break;
                case 7:
                    quizListSTQ.setListData(c.getQuizzes(courseListSTQ.getSelectedValue()));
                    break;
            }
            frame.repaint();
        }));
    }
}