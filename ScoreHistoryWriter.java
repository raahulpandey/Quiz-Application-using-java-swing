// ScoreHistoryWriter.java
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class ScoreHistoryWriter extends JFrame {

    public ScoreHistoryWriter(String name) {
        setTitle("Score History - " + name);
        setBounds(500, 200, 600, 400);
        setLayout(new BorderLayout());

        JTextArea historyArea = new JTextArea();
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        historyArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(historyArea);
        add(scrollPane, BorderLayout.CENTER);

        JLabel heading = new JLabel("Score History for " + name, SwingConstants.CENTER);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading, BorderLayout.NORTH);

        boolean hasHistory = false;
        File file = new File("data/score_history.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().startsWith(name.toLowerCase() + ",")) {
                    historyArea.append(line + "\n");
                    hasHistory = true;
                }
            }
        } catch (IOException e) {
            historyArea.setText("Unable to read score history.");
            e.printStackTrace();
        }

        if (!hasHistory) {
            historyArea.setText("No attempts yet for " + name);
        }

        JButton back = new JButton("Back");
        back.addActionListener(e -> setVisible(false));
        add(back, BorderLayout.SOUTH);

        setVisible(true);
    }
}
