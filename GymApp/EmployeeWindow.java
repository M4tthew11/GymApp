import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeWindow extends JFrame {
    JButton addMember;
    JButton removeMember;
    JButton logout;
    JButton addActivity;
    JLabel name;



    public EmployeeWindow(String loginc, String fname, String fsurname){
        setTitle("GloboGym-employee");
        setSize(1000,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel panel = new JPanel();
        JPanel Eastbuttonpanel = new JPanel();
        Eastbuttonpanel.setLayout(new GridLayout(2,1));
        panel.setLayout(new BorderLayout());
        addMember = new JButton("Add new Member");
        removeMember = new JButton("Users List");
        addActivity = new JButton("Add new activity");
        logout = new JButton("logout");
        name = new JLabel("Login:");
        name.setText("Login: "+loginc);
        JMenuBar menuBar1 = new JMenuBar();
        JMenu usermenu = new JMenu(loginc);
        JMenuItem profileItem = new JMenuItem("Profil");
        profileItem.addActionListener(e -> showUserInfo(fname,fsurname,loginc));
        usermenu.add(profileItem);
        menuBar1.add(usermenu);
        setJMenuBar(menuBar1);
        Eastbuttonpanel.add(addMember);
        Eastbuttonpanel.add(addActivity);
        panel.add(logout,BorderLayout.SOUTH);
        panel.add(removeMember,BorderLayout.WEST);
        panel.add(name, BorderLayout.NORTH);
        add(panel);
        add(Eastbuttonpanel, BorderLayout.EAST);
        addMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register().setVisible(true);
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
    }
    private void showUserInfo(String nam, String sur, String login){
        String userInfo = "Name: "+ nam + "\n"+ "Surname: "+sur+"\n"+"Login: "+login;
        JOptionPane.showMessageDialog(this,userInfo,"Profil użytkownika",JOptionPane.INFORMATION_MESSAGE);
    }
}
