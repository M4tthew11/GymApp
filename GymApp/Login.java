import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Login extends JFrame {
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton confirmButton;
    private JLabel label;
    private ArrayList<String> activitiesl;

    public Login() {
        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        activitiesl = new ArrayList<>();
        loginField = new JTextField();
        passwordField = new JPasswordField();
        confirmButton = new JButton("Confirm");
        label = new JLabel();
        label.setForeground(Color.RED);
        panel.add(new Label("Login: "));
        panel.add(loginField);
        panel.add(new Label("Password: "));
        panel.add(passwordField);
        panel.add(confirmButton);
        panel.add(label);
        add(panel);
        try (BufferedReader reader = new BufferedReader(new FileReader("Activities.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                activitiesl.add(line);
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String password = new String(passwordField.getPassword());
                try (BufferedReader in = new BufferedReader(new FileReader("Users.txt"))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length == 5) {
                            String fileLogin = parts[0];
                            String filePass = parts[1];
                            Role fileRole = Role.valueOf(parts[2]);
                            String filename = parts[3];
                            String filesurname = parts[4];
                            if (login.equals(fileLogin) && password.equals(filePass) && fileRole == Role.MEMBER) {
                                String logincor = login;
                                new MemberWindow(activitiesl,logincor, filename, filesurname).setVisible(true);
                                dispose();
                            }
                            if (login.equals(fileLogin) && password.equals(filePass) && fileRole == Role.EMPLOYEE){
                                String logincor = login;
                                new EmployeeWindow(logincor, filename, filesurname).setVisible(true);
                                dispose();
                            }
                            if (login.equals(fileLogin) && password.equals(filePass) && fileRole == Role.MANAGER){
                                String logincor = login;
                                new ManagerWindow(logincor).setVisible(true);
                                dispose();
                            }
                            else {
                                label.setText("Wrong login or password");
                            }
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
