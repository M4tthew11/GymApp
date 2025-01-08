import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;

public class CalendarTable {
    public CalendarTable(){
        JFrame frame = new JFrame("Kalendarz");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        AbstractTableModel model = new AbstractTableModel() {
            String[] days = { "Pon", "Wt", "Śr", "Czw", "Pt", "Sob", "Nd" };
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int month = Calendar.getInstance().get(Calendar.MONTH);
            Calendar calendar = new GregorianCalendar(year, month, 1);

            @Override
            public int getRowCount() {
                return 6;
            }

            @Override
            public int getColumnCount() {
                return 7;
            }

            @Override
            public String getColumnName(int column) {
                return days[column];
            }

            @Override
            public Object getValueAt(int row, int col) {
                int dayOfMonth = (col + row * 7) - (calendar.get(Calendar.DAY_OF_WEEK) - 2) + 1;
                if (dayOfMonth <= 0 || dayOfMonth > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    return "";
                } else {
                return Integer.toString(dayOfMonth);
                }
            }
        };
        JTable table = new JTable(model);
        table.setCellSelectionEnabled(true);

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }
}
