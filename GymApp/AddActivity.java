import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class AddActivity{
    private JTextField activityname;
    private JTextField roomnum;
    private JButton AddButton;
    private JLabel statusLabel;
    private  JFrame f;
    private JButton showCal;

    public AddActivity() {
        f = new JFrame();
        f.setTitle("Add Activity");
        f.setSize(300, 200);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        activityname = new JTextField();
        roomnum = new JTextField();
        AddButton = new JButton("Add this activity");
        statusLabel = new JLabel();
        showCal = new JButton("Show Calendar");

        panel.add(new JLabel("Name"));
        panel.add(activityname);
        panel.add(new JLabel("Room"));
        panel.add(roomnum);
        panel.add(statusLabel);
        panel.add(AddButton);
        panel.add(showCal);

        f.add(panel);
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newA = activityname.getText();
                String newR = roomnum.getText();
                if (newA != null && newR != null){
                    try {
                        if (!addAandR(newA,newR)){
                            statusLabel.setText("Ta sala jest już zajeta");
                        }else {
                            statusLabel.setText("Dodano aktywność");
                        }
                    }catch (IOException ex){
                        JOptionPane.showMessageDialog(f,"Błąd przy próbie dodania");
                    }
                }

            }
        });
        showCal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CalendarTable();
            }
        });

    }
    private boolean addAandR(String A, String R)throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader("Activities.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                if (line.contains(R)){
                    return false;
                }
            }
        }
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Activities.txt",true)))){
            writer.println(A+", "+R);
        }
        return true;
    }
}