import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Dashboard extends JFrame implements ActionListener {

    String username;
    JButton startQuiz, viewHistory, leaderboard, logout;
    JComboBox<String> categoryBox;

    public Dashboard(String username) {
        this.username = username;
        setTitle("Quiz Dashboard - " + username);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        JLabel welcome = new JLabel("Welcome, " + username);
        welcome.setBounds(150, 30, 300, 30);
        welcome.setFont(new Font("Tahoma", Font.BOLD, 20));
        welcome.setForeground(new Color(30, 144, 255));
        add(welcome);

        JLabel categoryLabel = new JLabel("Select Category:");
        categoryLabel.setBounds(100, 90, 150, 30);
        add(categoryLabel);

        String[] categories = {"Java", "General Knowledge", "Math"};
        categoryBox = new JComboBox<>(categories);
        categoryBox.setBounds(250, 90, 150, 30);
        add(categoryBox);

        startQuiz = new JButton("Start Quiz");
        startQuiz.setBounds(150, 140, 180, 30);
        startQuiz.setBackground(new Color(30, 144, 255));
        startQuiz.setForeground(Color.WHITE);
        startQuiz.addActionListener(this);
        add(startQuiz);

        viewHistory = new JButton("View Score History");
        viewHistory.setBounds(150, 190, 180, 30);
        viewHistory.addActionListener(this);
        add(viewHistory);

        leaderboard = new JButton("Leaderboard");
        leaderboard.setBounds(150, 240, 180, 30);
        leaderboard.addActionListener(this);
        add(leaderboard);

        logout = new JButton("Logout");
        logout.setBounds(150, 290, 180, 30);
        logout.setBackground(Color.GRAY);
        logout.setForeground(Color.WHITE);
        logout.addActionListener(this);
        add(logout);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == startQuiz) {
            String selectedCategory = (String) categoryBox.getSelectedItem();
            JOptionPane.showMessageDialog(this, "Starting quiz in category: " + selectedCategory);

            // Load quiz window with category
            setVisible(false);
            new Quiz(username, selectedCategory);

        } else if (ae.getSource() == viewHistory) {
            showHistory();
        } else if (ae.getSource() == leaderboard) {
            showLeaderboard();
        } else if (ae.getSource() == logout) {
            setVisible(false);
            new Login();
        }
    }

    void showHistory() {
        try {
            File historyFile = new File("data/score_history.txt");
            if (!historyFile.exists()) {
                JOptionPane.showMessageDialog(this, "No history found.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(historyFile));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(username + " - ")) {
                    sb.append(line).append("\n");
                }
            }
            br.close();

            if (sb.length() == 0) {
                sb.append("No attempts yet.");
            }

            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Score History", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showLeaderboard() {
        try {
            File file = new File("data/score_history.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(this, "No scores yet.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder("Top Scores:\n\n");
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();

            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Leaderboard", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Dashboard("TestUser");
    }
}
