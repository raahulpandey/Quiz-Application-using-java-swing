//package quiz.application;
import java.awt.*;
import javax.swing.*;
public class Login extends JFrame {
    Login() {
        getContentPane().setBackground(Color.white);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.jpeg"));
        setSize(1200,500);
        setLocation(200, 150);
        setVisible(true);

    }
    public static void main(String[] args) {
        new Login();
        
    }

}