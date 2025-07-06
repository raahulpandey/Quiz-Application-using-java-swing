// Rules.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Rules extends JFrame implements ActionListener {

    String name;
    boolean fromLogin;
    JButton next, back;

    public Rules(String name, boolean fromLogin) {
        this.name = name;
        this.fromLogin = fromLogin;

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Welcome " + name + " to Quiz Master Pro");
        heading.setBounds(50, 20, 700, 30);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 28));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);

        JLabel rules = new JLabel();
        rules.setBounds(20, 90, 700, 350);
        rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rules.setText(
                "<html>" +
                        "1. Answer all questions sincerely." + "<br><br>" +
                        "2. All questions are compulsory." + "<br><br>" +
                        "3. Don’t panic if others are answering faster." + "<br><br>" +
                        "4. The quiz is timed per question." + "<br><br>" +
                        "5. Best of luck!" +
                        "<html>");
        add(rules);

        back = new JButton("Back");
        back.setBounds(250, 500, 100, 30);
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        next = new JButton("Next");
        next.setBounds(400, 500, 100, 30);
        next.setBackground(new Color(30, 144, 254));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        setSize(800, 650);
        setLocation(350, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            setVisible(false);
            new Dashboard(name); // Show dashboard after rules if from login
        } else {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Rules("User", true);
    }
}
