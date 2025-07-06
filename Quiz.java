// Quiz.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Quiz extends JFrame implements ActionListener {

    String questions[][] = new String[10][5];
    String answers[][] = new String[10][2];
    String useranswers[][] = new String[10][1];

    JLabel qno, question, timerLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton next, submit, lifeline;

    int timer = 15;
    int count = 0;
    int score = 0;
    Timer quizTimer;

    String name, category;

    public Quiz(String name, String category) {
        this.name = name;
        this.category = category;

        setBounds(50, 0, 1440, 850);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quiz.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1440, 392);
        add(image);

        qno = new JLabel();
        qno.setBounds(100, 450, 50, 30);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(qno);

        question = new JLabel();
        question.setBounds(150, 450, 900, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(question);

        timerLabel = new JLabel("Time left - 15 seconds");
        timerLabel.setBounds(1100, 500, 300, 30);
        timerLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        timerLabel.setForeground(Color.RED);
        add(timerLabel);

        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt4 = new JRadioButton();

        JRadioButton[] options = {opt1, opt2, opt3, opt4};
        int y = 520;
        for (JRadioButton opt : options) {
            opt.setBounds(170, y, 700, 30);
            opt.setFont(new Font("Dialog", Font.PLAIN, 20));
            opt.setBackground(Color.WHITE);
            add(opt);
            y += 40;
        }

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        next = new JButton("Next");
        next.setBounds(1100, 550, 200, 40);
        next.setFont(new Font("Tahoma", Font.PLAIN, 22));
        next.setBackground(new Color(30, 144, 255));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        lifeline = new JButton("50-50 Lifeline");
        lifeline.setBounds(1100, 630, 200, 40);
        lifeline.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lifeline.setBackground(new Color(30, 144, 255));
        lifeline.setForeground(Color.WHITE);
        lifeline.addActionListener(this);
        add(lifeline);

        submit = new JButton("Submit");
        submit.setBounds(1100, 710, 200, 40);
        submit.setFont(new Font("Tahoma", Font.PLAIN, 22));
        submit.setBackground(new Color(30, 144, 255));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);

        setupQuestions(category);
        start(count);
        startTimer();

        setVisible(true);
    }

    void setupQuestions(String category) {
        if (category.equalsIgnoreCase("Java")) {
            String[][] javaQs = {
                {"Which is used to find and fix bugs in Java programs?", "JVM", "JDB", "JDK", "JRE", "JDB"},
                {"What is the return type of hashCode()?", "int", "Object", "long", "void", "int"},
                {"Which package has Random class?", "java.util", "java.lang", "java.awt", "java.io", "java.util"},
                {"An interface with no methods is?", "Runnable", "Abstract", "Marker", "CharSequence", "Marker"},
                {"String using 'new' is stored in?", "Stack", "String pool", "Heap", "Code Segment", "Heap"},
                {"Which is a marker interface?", "Runnable", "Remote", "Readable", "Result", "Remote"},
                {"Keyword to use package features?", "import", "package", "extends", "export", "import"},
                {"In Java, JAR stands for?", "Java Archive Runner", "Java Archive", "Java Application Resource", "Java App Runner", "Java Archive"},
                {"Which is mutable in Java?", "StringBuilder", "Short", "Byte", "String", "StringBuilder"},
                {"What leads to Java's portability?", "Bytecode in JVM", "Applet", "Exception handling", "Dynamic binding", "Bytecode in JVM"}
            };
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 5; j++) questions[i][j] = javaQs[i][j];
                answers[i][1] = javaQs[i][5];
            }
        } else if (category.equalsIgnoreCase("General Knowledge")) {
            String[][] gk = {
                {"Who is known as the Father of the Nation (India)?", "Subhash Chandra Bose", "Jawaharlal Nehru", "Mahatma Gandhi", "B. R. Ambedkar", "Mahatma Gandhi"},
                {"Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Saturn", "Mars"},
                {"What is the capital of France?", "Madrid", "Berlin", "Paris", "Rome", "Paris"},
                {"Which festival is known as the festival of lights?", "Holi", "Diwali", "Eid", "Navratri", "Diwali"},
                {"Who wrote the Indian National Anthem?", "Bankim Chandra", "Tagore", "Premchand", "Naidu", "Tagore"},
                {"Which is the largest ocean on Earth?", "Indian", "Pacific", "Arctic", "Atlantic", "Pacific"},
                {"Which gas do plants absorb?", "Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen", "Carbon Dioxide"},
                {"Taj Mahal is located in?", "Delhi", "Mumbai", "Agra", "Jaipur", "Agra"},
                {"Which country is called the Land of Rising Sun?", "China", "Thailand", "Japan", "Korea", "Japan"},
                {"What is the full form of GDP?", "Gross Domestic Product", "Global Data Position", "Graph Development Plan", "Gross Data Production", "Gross Domestic Product"}
            };
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 5; j++) questions[i][j] = gk[i][j];
                answers[i][1] = gk[i][5];
            }
        } else if (category.equalsIgnoreCase("Math")) {
            String[][] math = {
                {"What is 12 + 8?", "20", "22", "19", "21", "20"},
                {"What is 15 - 7?", "9", "8", "7", "6", "8"},
                {"What is 9 x 3?", "27", "26", "24", "30", "27"},
                {"What is 20 ÷ 4?", "6", "4", "5", "3", "5"},
                {"What is the square of 5?", "15", "20", "10", "25", "25"},
                {"What is the cube of 2?", "8", "6", "4", "12", "8"},
                {"What is 100 - 55?", "40", "45", "50", "60", "45"},
                {"What is 7 x 6?", "42", "36", "48", "40", "42"},
                {"What is 10 + 15 - 5?", "20", "25", "15", "18", "20"},
                {"What is 144 ÷ 12?", "12", "10", "14", "13", "12"}
            };
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 5; j++) questions[i][j] = math[i][j];
                answers[i][1] = math[i][5];
            }
        }
    }

    void start(int count) {
        qno.setText((count + 1) + ". ");
        question.setText(questions[count][0]);
        opt1.setText(questions[count][1]);
        opt1.setActionCommand(questions[count][1]);
        opt2.setText(questions[count][2]);
        opt2.setActionCommand(questions[count][2]);
        opt3.setText(questions[count][3]);
        opt3.setActionCommand(questions[count][3]);
        opt4.setText(questions[count][4]);
        opt4.setActionCommand(questions[count][4]);
        groupoptions.clearSelection();
    }

    void startTimer() {
        quizTimer = new Timer(1000, e -> {
            timer--;
            timerLabel.setText("Time left - " + timer + " seconds");
            if (timer <= 0) {
                quizTimer.stop();
                handleAnswer();
                if (count == 9) {
                    showScore();
                } else {
                    count++;
                    start(count);
                    timer = 15;
                    startTimer();
                }
                if (count == 8) {
                    next.setEnabled(false);
                    submit.setEnabled(true);
                }
            }
        });
        quizTimer.start();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            quizTimer.stop();
            handleAnswer();
            if (count == 8) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }
            count++;
            start(count);
            timer = 15;
            startTimer();
        } else if (ae.getSource() == lifeline) {
            if (count == 2 || count == 4 || count == 6 || count == 8 || count == 9) {
                opt2.setEnabled(false);
                opt3.setEnabled(false);
            } else {
                opt1.setEnabled(false);
                opt4.setEnabled(false);
            }
            lifeline.setEnabled(false);
        } else if (ae.getSource() == submit) {
            quizTimer.stop();
            handleAnswer();
            showScore();
        }
    }

    void handleAnswer() {
        if (groupoptions.getSelection() == null) {
            useranswers[count][0] = "";
        } else {
            useranswers[count][0] = groupoptions.getSelection().getActionCommand();
        }
    }

    void showScore() {
        for (int i = 0; i < useranswers.length; i++) {
            if (useranswers[i][0] != null && useranswers[i][0].equals(answers[i][1])) {
                score += 10;
            }
        }
        setVisible(false);
        new Score(name, score);
    }

    public static void main(String[] args) {
        new Quiz("User", "Java");
    }
}
