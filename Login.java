// Removed package declaration

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JButton rules, back;
    JTextField tfname;
    JCheckBox darkMode;
    JLabel welcomeMsg;
    JLabel profileImage;
    JButton viewHistory;

    public Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpeg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 600, 500);
        add(image);

        JLabel heading = new JLabel("Simple Minds");
        heading.setBounds(750, 60, 300, 45);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);

        JLabel name = new JLabel("Enter your name");
        name.setBounds(810, 150, 300, 20);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        name.setForeground(new Color(30, 144, 254));
        add(name);

        tfname = new JTextField();
        tfname.setBounds(735, 180, 300, 25);
        tfname.setFont(new Font("Times New Roman", Font.BOLD, 20));
        tfname.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                welcomeMsg.setText("Welcome, " + tfname.getText());
            }
        });
        add(tfname);

        welcomeMsg = new JLabel("Welcome, ");
        welcomeMsg.setBounds(735, 210, 300, 25);
        welcomeMsg.setFont(new Font("Tahoma", Font.ITALIC, 16));
        welcomeMsg.setForeground(Color.GRAY);
        add(welcomeMsg);

        rules = new JButton("Rules");
        rules.setBounds(735, 270, 120, 25);
        rules.setBackground(new Color(30, 144, 254));
        rules.setForeground(Color.WHITE);
        rules.addActionListener(this);
        add(rules);

        back = new JButton("Back");
        back.setBounds(915, 270, 120, 25);
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        darkMode = new JCheckBox("Dark Mode");
        darkMode.setBounds(735, 310, 120, 25);
        darkMode.setBackground(Color.WHITE);
        darkMode.addActionListener(this);
        add(darkMode);

        viewHistory = new JButton("View Score History");
        viewHistory.setBounds(735, 350, 300, 25);
        viewHistory.setBackground(new Color(100, 100, 255));
        viewHistory.setForeground(Color.WHITE);
        viewHistory.addActionListener(this);
        add(viewHistory);

        profileImage = new JLabel();
        profileImage.setBounds(1050, 50, 100, 100);
        ImageIcon defaultIcon = new ImageIcon(ClassLoader.getSystemResource("icons/profile.png"));
        Image scaledImage = defaultIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        profileImage.setIcon(new ImageIcon(scaledImage));
        add(profileImage);

        setSize(1200, 500);
        setLocation(200, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == rules) {
            String name = tfname.getText().trim();
            if (name.equals("")) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty.");
            } else {
                try {
                    File history = new File("score_history.txt");
                    if (!history.exists()) history.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setVisible(false);
                new Rules(name);
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
        } else if (ae.getSource() == darkMode) {
            boolean isDark = darkMode.isSelected();
            Color bg = isDark ? Color.DARK_GRAY : Color.WHITE;
            Color fg = isDark ? Color.LIGHT_GRAY : new Color(30, 144, 254);
            getContentPane().setBackground(bg);
            for (Component comp : getContentPane().getComponents()) {
                if (comp instanceof JLabel || comp instanceof JTextField || comp instanceof JCheckBox || comp instanceof JButton) {
                    comp.setForeground(fg);
                    comp.setBackground(bg);
                }
            }
        } else if (ae.getSource() == viewHistory) {
            try {
                File history = new File("score_history.txt");
                if (history.exists()) {
                    BufferedReader br = new BufferedReader(new FileReader(history));
                    JTextArea textArea = new JTextArea();
                    textArea.read(br, null);
                    textArea.setEditable(false);
                    br.close();
                    JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Score History", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No history found.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
