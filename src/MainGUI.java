import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    JList<String> coursesTM;
    JScrollPane courseScrollTM;
    JButton addCourseTM;
    JButton openCourseTM;
    JButton deleteCourseTM;
    JButton gradeQuizTM;
    JList<String> submissionsSM;
    JScrollPane subScrollSM;
    JButton viewSubSM;
    JButton takeQuizSM;
    JTextField courseNameTAC;
    JButton addCourseTAC;
    JButton cancelTAC;
    JList<String> quizzesTCD;
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
    JList<String> studentsTStS;
    JScrollPane stuScrollTStS;
    JButton selectStuTStS;
    JButton courseMenuTStS;
    JList<String> subsTSuS;
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
    JList<String> courseListSTQ;
    JScrollPane cListScrollSTQ;
    JButton selectCourseSTQ;
    JButton cancelCourseSTQ;
    JList<String> quizListSTQ;
    JScrollPane qListScrollSTQ;
    JButton selectQuizSTQ;
    JButton changeCourseSTQ;
    JButton cancelQuizSTQ;
    JLabel questionSTQ;
    JList<String> answerOptionsSTQ;
    JButton nextQuestionSTQ;


    int numQuestions;
    int numAnswers;
    int questionTracker;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MainGUI());
    }

    public void run() {
        JFrame frame = new JFrame("Quiz Tool");
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
        teacherCreateQuiz.add(Box.createRigidArea(new Dimension(0, 5)));
        teacherCreateQuiz.add(createQuizTQC);

        teacherCreateQuestion.add(quesNameLabelTKC);
        teacherCreateQuestion.add(questionNameTKC);
        teacherCreateQuestion.add(numAnsLabelTKC);
        teacherCreateQuestion.add(numAnswersTKC);
        teacherCreateQuestion.add(Box.createRigidArea(new Dimension(0, 5)));
        teacherCreateQuestion.add(createQuesTKC);

        teacherStudentSelect.add(Box.createRigidArea(new Dimension(0, 50)));
        teacherStudentSelect.add(selectStuTStS);
        teacherStudentSelect.add(Box.createRigidArea(new Dimension(0, 10)));
        teacherStudentSelect.add(courseMenuTStS);
        listStudent.add(stuScrollTStS);

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
                } else if(usernameText.getText().equals("t")) {
                    frame.dispose();
                    content.remove(centerLogin);
                    content.setLayout(new BorderLayout());
                    content.add(teacherMain, BorderLayout.EAST);
                    content.add(courseList, BorderLayout.WEST);

                    frame.setSize(new Dimension(300, 250));
                    frame.setVisible(true);
                } else {
                    frame.dispose();
                    content.remove(centerLogin);
                    content.add(studentMain);
                    frame.setSize(new Dimension(300, 275));
                    frame.setVisible(true);
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
                } else if("Teacher".equals(roleSelect.getSelectedItem())) {
                    frame.dispose();
                    content.remove(centerCreate);
                    content.setLayout(new BorderLayout());
                    content.add(teacherMain, BorderLayout.EAST);
                    content.add(courseList, BorderLayout.WEST);

                    frame.setSize(new Dimension(300, 250));
                    frame.setVisible(true);
                } else {
                    frame.dispose();
                    content.remove(centerCreate);
                    content.add(studentMain);
                    frame.setSize(new Dimension(300, 275));
                    frame.setVisible(true);
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
            }
        });

        addCourseTAC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(teacherAddCourse);
                courseNameTAC.setText("");
                content.setLayout(new BorderLayout());
                content.add(teacherMain, BorderLayout.EAST);
                content.add(courseList, BorderLayout.WEST);

                frame.setSize(new Dimension(300, 250));
                frame.setVisible(true);
            }
        });

        cancelTAC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(teacherAddCourse);
                content.setLayout(new BorderLayout());
                content.add(teacherMain, BorderLayout.EAST);
                content.add(courseList, BorderLayout.WEST);

                frame.setSize(new Dimension(300, 250));
                frame.setVisible(true);
            }
        });

        openCourseTM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(teacherMain);
                content.remove(courseList);
                content.add(teacherCourseDisplay, BorderLayout.EAST);
                content.add(quizList, BorderLayout.WEST);

                frame.setVisible(true);
            }
        });

        exitCourseTCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(teacherCourseDisplay);
                content.remove(quizList);
                content.add(teacherMain, BorderLayout.EAST);
                content.add(courseList, BorderLayout.WEST);
                frame.setVisible(true);
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
                frame.setSize(new Dimension(300, 150));
                frame.setVisible(true);
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
                        questionTracker = 0;
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
                        numAnswers = Integer.parseInt(numAnswersTKC.getText());
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
                    frame.dispose();
                    content.remove(teacherSetAnswers);
                    teacherSetAnswers.removeAll();
                    questionTracker++;
                    if (questionTracker >= numQuestions) {
                        content.setLayout(new BorderLayout());
                        content.add(teacherCourseDisplay, BorderLayout.EAST);
                        content.add(quizList, BorderLayout.WEST);
                        frame.setSize(new Dimension(300, 250));
                    } else {
                        content.add(teacherCreateQuestion);
                        frame.setSize(new Dimension(300, 150));
                    }
                    frame.setVisible(true);
                }
            }
        });

        editQuizTCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(teacherCourseDisplay);
                content.remove(quizList);
                //gets the quiz, sets numQuestions and numAnswers
                numQuestions = 3;
                questionTracker = 0;
                numAnswers = 4;
                teacherEditQuiz.removeAll();
                ansLabelsTQE = new JLabel[numAnswers];
                answersTQE = new JTextField[numAnswers];
                teacherEditQuiz.add(quesLabelTQE);
                //auto-fill text boxes
                questionTQE.setText("Qestion");
                teacherEditQuiz.add(questionTQE);
                int heightVal = 115;
                for (int i = 0; i < numAnswers; i++) {
                    ansLabelsTQE[i] = new JLabel("Answer Choice " + (i + 1));
                    answersTQE[i] = new JTextField(40);
                    answersTQE[i].setMaximumSize(answersTQE[i].getPreferredSize());
                    answersTQE[i].setText("anser");
                    teacherEditQuiz.add(ansLabelsTQE[i]);
                    teacherEditQuiz.add(answersTQE[i]);
                    heightVal += 35;
                }
                teacherEditQuiz.add(setChoicesTQE);
                content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
                content.add(teacherEditQuiz);
                frame.setSize(new Dimension(300, heightVal));
                frame.setVisible(true);
            }
        });

        setChoicesTQE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(teacherEditQuiz);
                //sets numAnswers
                questionTracker++;
                if (questionTracker >= numQuestions) {
                    content.setLayout(new BorderLayout());
                    content.add(teacherCourseDisplay, BorderLayout.EAST);
                    content.add(quizList, BorderLayout.WEST);
                    frame.setSize(new Dimension(300, 250));
                } else {
                    numAnswers = 6;
                    teacherEditQuiz.removeAll();
                    ansLabelsTQE = new JLabel[numAnswers];
                    answersTQE = new JTextField[numAnswers];
                    teacherEditQuiz.add(quesLabelTQE);
                    //auto-fill text boxes
                    questionTQE.setText("Qestion");
                    teacherEditQuiz.add(questionTQE);
                    int heightVal = 115;
                    for (int i = 0; i < numAnswers; i++) {
                        ansLabelsTQE[i] = new JLabel("Answer Choice " + (i + 1));
                        answersTQE[i] = new JTextField(40);
                        answersTQE[i].setMaximumSize(answersTQE[i].getPreferredSize());
                        answersTQE[i].setText("anser");
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
                content.add(teacherStudentSelect, BorderLayout.EAST);
                content.add(listStudent, BorderLayout.WEST);
                frame.setVisible(true);
            }
        });

        selectStuTStS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(listStudent);
                content.remove(teacherStudentSelect);
                content.add(teacherSelectSubmission, BorderLayout.EAST);
                content.add(listSubmission, BorderLayout.WEST);
                frame.setVisible(true);
            }
        });

        stuMenuTSuS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(listSubmission);
                content.remove(teacherSelectSubmission);
                content.add(teacherStudentSelect, BorderLayout.EAST);
                content.add(listStudent, BorderLayout.WEST);
                frame.setVisible(true);
            }
        });

        courseMenuTSuS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(listSubmission);
                content.remove(teacherSelectSubmission);
                content.add(teacherMain, BorderLayout.EAST);
                content.add(courseList, BorderLayout.WEST);
                frame.setVisible(true);
            }
        });

        courseMenuTStS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(listStudent);
                content.remove(teacherStudentSelect);
                content.add(teacherMain, BorderLayout.EAST);
                content.add(courseList, BorderLayout.WEST);
                frame.setVisible(true);
            }
        });

        selectSubTSuS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //set number of questions in quiz
                numQuestions = 3;
                questionTracker = 0;
                //sets questions and answers
                questionTG.setText("actual question");
                answerTG.setText("actual answer");
                content.remove(listSubmission);
                content.remove(teacherSelectSubmission);
                content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
                content.add(teacherGrading);
                frame.setSize(new Dimension(300, 175));
                frame.setVisible(true);
            }
        });

        scoreQuesTG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                scoreTG.setText("");
                content.remove(teacherGrading);
                questionTracker++;
                if (questionTracker >= numQuestions) {
                    content.setLayout(new BorderLayout());
                    content.add(teacherMain, BorderLayout.EAST);
                    content.add(courseList, BorderLayout.WEST);
                    frame.setSize(new Dimension(300, 250));
                } else {
                    questionTG.setText("newQuestion");
                    answerTG.setText("newAns");
                    content.add(teacherGrading);
                }
                frame.setVisible(true);
            }
        });

        viewSubSM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(studentMain);
                questionTracker = 0;
                numQuestions = 3;
                questionSQD.setText("actual question");
                answerSQD.setText("actual answer");
                int score = 2;
                if (score == -1) {
                    scoreSQD.setText("No Score Entered");
                } else {
                    scoreSQD.setText("" + score);
                }
                content.add(studentQuizDisplay);
                frame.setSize(new Dimension(300, 225));
                frame.setVisible(true);
            }
        });

        exitSQD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(studentQuizDisplay);
                content.add(studentMain);
                frame.setSize(new Dimension(300, 275));
                frame.setVisible(true);
            }
        });

        nextQuesSQD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(studentQuizDisplay);
                questionTracker++;
                if (questionTracker >= numQuestions) {
                    content.add(studentMain);
                    frame.setSize(new Dimension(300, 275));

                } else {
                    questionSQD.setText("actualquestion2");
                    answerSQD.setText("actualanswer2");
                    int score = -1;
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
                content.add(studentSelectCourse);
                frame.setVisible(true);
            }
        });

        selectCourseSTQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(studentSelectCourse);
                content.add(studentSelectQuiz);
                frame.setSize(new Dimension(300, 300));
                frame.setVisible(true);
            }
        });

        cancelCourseSTQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(studentSelectCourse);
                content.add(studentMain);
                frame.setVisible(true);
            }
        });

        cancelQuizSTQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(studentSelectQuiz);
                content.add(studentMain);
                frame.setSize(new Dimension(300, 275));
                frame.setVisible(true);
            }
        });

        changeCourseSTQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(studentSelectQuiz);
                content.add(studentSelectCourse);
                frame.setSize(new Dimension(300, 275));
                frame.setVisible(true);
            }
        });

        selectQuizSTQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(studentSelectQuiz);
                questionTracker = 0;
                numQuestions = 5;
                String[] answers = {"answer1", "answer2", "answer3", "a", "a", "a", "a"};
                questionSTQ.setText("actualQuestion");
                answerOptionsSTQ.setListData(answers);
                int heightVal = 100;
                heightVal += 20 * answers.length;
                content.add(studentTakeQuiz);
                frame.setSize(new Dimension(300, heightVal));
                frame.setVisible(true);
            }
        });

        nextQuestionSTQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                content.remove(studentTakeQuiz);
                questionTracker++;
                if (questionTracker >= numQuestions) {
                    //send quiz
                    content.add(studentMain);
                    frame.setSize(new Dimension(300, 275));
                } else {
                    String[] answers = {"answer1", "answer2", "answer4"};
                    questionSTQ.setText("the actual question");
                    answerOptionsSTQ.setListData(answers);
                    int heightVal = 100;
                    heightVal += 20 * answers.length;
                    content.add(studentTakeQuiz);
                    frame.setSize(new Dimension(300, heightVal));
                }
                frame.setVisible(true);
            }
        });
    }
}