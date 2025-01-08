import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MemberWindow extends JFrame {
    JButton logout;
    JLabel list_lessons;
    JLabel name;
    JButton show_calendar;
    JComboBox<String> activitiesCB;
    JMenuBar menuBar;
    JButton add_activity;
    JLabel act_list;
    String payment = "Status: nieopłacono";
    JLabel managerinfo;


    public MemberWindow(ArrayList<String> activities, String loginc, String fname, String fsurname) {
        setTitle("GloboGym-member");
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.GREEN);
        setResizable(false);
        JPanel panel = new JPanel();
        JPanel center_panel = new JPanel();
        panel.setLayout(new BorderLayout());
        center_panel.setLayout(new GridLayout(3,4));
        activitiesCB = new JComboBox<>(activities.toArray(new String[0]));
        show_calendar = new JButton("Show calendar");
        add_activity = new JButton("Add this activity");
        list_lessons = new JLabel("Added Activities:");
        logout = new JButton("logout");
        name = new JLabel("Login:");
        managerinfo = new JLabel();
        activitiesCB.setSize(200, 200);
        show_calendar.setSize(200, 200);
        add_activity.setSize(200, 200);
        list_lessons.setSize(200, 200);
        JMenuBar menuBar2 = new JMenuBar();
        JMenuBar menuBar1 = new JMenuBar();
        JMenu usermenu = new JMenu(loginc);
        JMenu acc_status = new JMenu("Account status");
        JMenuItem Payment = new JMenuItem("Payments");
        JMenuItem profileItem = new JMenuItem("Profil");
        Payment.addActionListener(e -> showPaymentInfo());
        profileItem.addActionListener(e -> showUserInfo(fname, fsurname, loginc));
        acc_status.add(Payment);
        usermenu.add(profileItem);
        menuBar1.add(usermenu);
        menuBar1.add(acc_status);
        setJMenuBar(menuBar1);
        name.setText("Login: " + loginc);
        center_panel.add(list_lessons);
        center_panel.add(activitiesCB);
        center_panel.add(add_activity);
        center_panel.add(show_calendar);
        center_panel.add(managerinfo);
        //center_panel.add(select_activity);
        panel.add(logout, BorderLayout.SOUTH);
        panel.add(name, BorderLayout.NORTH);
        panel.setAlignmentX(30);
        panel.setAlignmentY(30);
        add(panel,BorderLayout.SOUTH);
        add(center_panel);


        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });
        add_activity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAct = (String) activitiesCB.getSelectedItem();
                list_lessons.setText(list_lessons.getText() + "     " + selectedAct);
                activitiesCB.removeItem(selectedAct);
            }
        });
        show_calendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CalendarTable();
            }
        });
        try (BufferedReader in = new BufferedReader(new FileReader("ManagerMessage.txt"))) {
            String line;
            while ((line = in.readLine()) != null) {
                managerinfo.setText(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showUserInfo(String nam, String sur, String login) {
        String userInfo = "Name: " + nam + "\n" + "Surname: " + sur + "\n" + "Login: " + login;
        JOptionPane.showMessageDialog(this, userInfo, "Profil użytkownika", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showPaymentInfo() {
        JButton pay = new JButton("Zapłać");
        JFrame paymentFrame = new JFrame("Payment status");
        JLabel label = new JLabel(payment);
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(pay);
        panel.setLayout(new GridLayout(2, 1));
        paymentFrame.add(panel);
        paymentFrame.setVisible(true);
        paymentFrame.setSize(100, 100);
        paymentFrame.setResizable(false);
        paymentFrame.setLocationRelativeTo(null);

        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payment = "Status: opłacono";
                label.setText(payment);
            }
        });
    }
}

