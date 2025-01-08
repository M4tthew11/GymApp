import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ManagerWindow extends JFrame {
    JButton addMember;
    JButton removeMember;
    JButton addEmployee;
    JButton logout;
    JLabel name;
    JButton addActivity;
    JLabel photo;
    JTextField managermess;
    JButton confirmmess;


    public ManagerWindow(String loginc){
        setTitle("GloboGym-manager");
        setSize(1000,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel EastbuttonPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel topPanel = new JPanel();
        westPanel.setLayout(new GridLayout(2,1));
        EastbuttonPanel.setLayout(new GridLayout(2,1));
        topPanel.setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        addMember = new JButton("Add new Member");
        addEmployee = new JButton("Add new Employee");
        removeMember = new JButton("Users List");
        addActivity = new JButton("AddActivity");
        managermess = new JTextField("Bywa, że najlepszą motywacją są nasze porażki");
        confirmmess = new JButton("Confirm");
        logout = new JButton("logout");
        ImageIcon imageIcon = new ImageIcon("src/silownia.jpg");
        Image image = imageIcon.getImage();
        Image newimage = image.getScaledInstance(800,900,Image.SCALE_SMOOTH);
        imageIcon =new ImageIcon(newimage);
        photo = new JLabel(imageIcon);
        name = new JLabel("Login:");
        name.setText("Login: "+loginc);
        photo.setHorizontalAlignment(JLabel.CENTER);
        photo.setVerticalAlignment(JLabel.CENTER);
        EastbuttonPanel.add(addMember);
        EastbuttonPanel.add(addEmployee);
        westPanel.add(removeMember);
        westPanel.add(addActivity);
        topPanel.add(managermess);
        topPanel.add(confirmmess);
        panel.add(logout,BorderLayout.SOUTH);
        topPanel.add(name);
        panel.add(photo,BorderLayout.CENTER);
        add(EastbuttonPanel,BorderLayout.EAST);
        add(westPanel,BorderLayout.WEST);
        add(topPanel,BorderLayout.NORTH);
        getContentPane().add(panel);

        addMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register().setVisible(true);
            }
        });
        addEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterEmp().setVisible(true);
            }
        });
        removeMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Remove().setVisible(true);
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });
        addActivity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddActivity();
            }
        });
        confirmmess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ManagerMessage.txt", true)))) {
                    out.println(managermess.getText());
                    out.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}
