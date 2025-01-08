import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Remove extends JFrame {
    private JButton removeButton;
    private JComboBox<String> listofusers;
    String selectedUser;

    public Remove() {
        setTitle("Users List");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,2));
        List<String> logins = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("Users.txt"))) {
            String line;
            while ((line = in.readLine()) != null) {
                logins.add(line);
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
        listofusers = new JComboBox<>(logins.toArray(new String[0]));

        removeButton = new JButton("Remove");


        panel.add(new Label());
        panel.add(listofusers);
        panel.add(new JLabel());
        panel.add(removeButton);

        add(panel);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedUser = (String) listofusers.getSelectedItem();
                deleteUser(selectedUser);
                listofusers.removeItem(selectedUser);
            }
        });

    }
    public void deleteUser(String login) {
        File inputFile = new File("Users.txt");
        File tempFile = new File("temp.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String cuurentLine;
            while ((cuurentLine = reader.readLine()) != null) {
                String[] parts = cuurentLine.trim().split(",");
                if (parts.length == 3) {
                    String fileLogin = parts[0];
                    if (fileLogin.equals(login)) continue;
                }
                writer.write(cuurentLine + System.getProperty("line.separator"));
            }
                writer.close();
                reader.close();
                if (!inputFile.delete()) {
                    System.out.println("Nie można usunąć pliku");
                    return;
                }
                if (!tempFile.renameTo(inputFile))
                    System.out.println("Nie można zmienić nazwy pliku");


        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}