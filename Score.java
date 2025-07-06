// Score.java

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Score extends JFrame implements ActionListener {
    String name;

    Score(String name, int score) {
        this.name = name;

        setBounds(400, 150, 750, 550);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Score image
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/score.png"));
        Image img = icon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBounds(20, 200, 300, 250);
        add(imageLabel);

        // Save score to score_history.txt
       // Ensure the data directory exists
File dataDir = new File("data");
if (!dataDir.exists()) {
    dataDir.mkdirs();
}

// Save score to score_history.txt
try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/score_history.txt", true))) {
    writer.write(name + "," + score);
    writer.newLine();
} catch (IOException e) {
    e.printStackTrace();
}


        // Update leaderboard.txt
        updateLeaderboard(name, score);

        JLabel heading = new JLabel("Thank you " + name + " for playing Quiz Master Pro");
        heading.setBounds(45, 30, 700, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 26));
        add(heading);

        JLabel lblscore = new JLabel("Your score is " + score);
        lblscore.setBounds(350, 200, 300, 30);
        lblscore.setFont(new Font("Tahoma", Font.PLAIN, 26));
        add(lblscore);

        JButton submit = new JButton("Go to Dashboard");
        submit.setBounds(380, 270, 180, 30);
        submit.setBackground(new Color(30, 144, 255));
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Tahoma", Font.PLAIN, 16));
        submit.addActionListener(this);
        add(submit);

        setVisible(true);
    }

    private void updateLeaderboard(String name, int score) {
        File file = new File("data/leaderboard.txt");
        List<String[]> entries = new ArrayList<>();

        // Read existing leaderboard
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    entries.add(parts);
                }
            }
        } catch (IOException e) {
            // File will be created if not present
        }

        entries.add(new String[]{name, String.valueOf(score)});
        entries.sort((a, b) -> Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1])));

        // Keep only top 10
        if (entries.size() > 10) entries = entries.subList(0, 10);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String[] entry : entries) {
                writer.write(entry[0] + "," + entry[1]);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Dashboard(name); // ✅ Go to Dashboard instead of Login
    }
}
