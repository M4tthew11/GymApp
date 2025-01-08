import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

enum Role {
    EMPLOYEE,
    MANAGER,
    MEMBER

}
public class Register extends JFrame {
    private JTextField name1;
    private JTextField Famname;

    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JLabel statusLabel;

    public Register() {
        setTitle("Register");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2));
        name1 = new JTextField();
        Famname = new JTextField();
        loginField = new JTextField();
        passwordField = new JPasswordField();
        registerButton = new JButton("Register");
        statusLabel = new JLabel();
        panel.add(new JLabel("Name:"));
        panel.add(name1);
        panel.add(new JLabel("Surname: "));
        panel.add(Famname);
        panel.add(new JLabel("Login:"));
        panel.add(loginField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(statusLabel);
        panel.add(registerButton);

        add(panel);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String password = new String(passwordField.getPassword());
                Role role = Role.MEMBER;
                String name = name1.getText();
                String surname = Famname.getText();
                User newUser = new User(name, surname,login,password,role);
                if (password.length()<5){
                    statusLabel.setText("Hasło musi mieć sonajmniej 5 znaków");
                }
                else {
                    try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Users.txt", true)))) {
                        out.println(newUser.getLogin() + "," + newUser.getPassword() + "," + role + "," + newUser.getName() + "," + newUser.getSurname());
                        out.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }


            }
        });
    }
}


