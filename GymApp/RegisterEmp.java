import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class RegisterEmp extends JFrame {
    private JTextField loginField;
    private JPasswordField passwordField;
    private JComboBox<Role> roleBox;
    private JButton registerButton;
    private JTextField name1;
    private JTextField Famname;


    public RegisterEmp() {
        setTitle("Register Employee");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,2));
        name1 = new JTextField();
        Famname = new JTextField();
        loginField = new JTextField();
        passwordField = new JPasswordField();
        registerButton = new JButton("Register Emp");
        roleBox = new JComboBox<>();
        roleBox.addItem(Role.MANAGER);
        roleBox.addItem(Role.EMPLOYEE);

        panel.add(new JLabel("Name:"));
        panel.add(name1);
        panel.add(new JLabel("Surname: "));
        panel.add(Famname);

        panel.add(new JLabel("Login:"));
        panel.add(loginField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Role"));
        panel.add(roleBox);
        panel.add(new JLabel());
        panel.add(registerButton);

        add(panel);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String password = new String(passwordField.getPassword());
                Role role = (Role) roleBox.getSelectedItem();
                String name = name1.getText();
                String surname = Famname.getText();

                User newUser = new User(name, surname,login,password,role);
                try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Users.txt",true)))){
                    out.println(newUser.getLogin()+","+newUser.getPassword()+","+ newUser.getRole().name());
                    out.close();
                }catch (IOException exception) {
                    exception.printStackTrace();
                }


            }
        });
    }
}


