package myclock;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Clock {
    public static void main(String[] args) {
        JFrame frame = new JFrame("World Clocks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 600); // Adjust the frame size for vertical layout
        frame.setLayout(new GridLayout(5, 1, 0, 5)); // 5 rows, 1 column, 10px vertical gap

        ClockPanel sanFranciscoClock = new ClockPanel("America/Los_Angeles", "San Francisco, California, U.S.A.");
        ClockPanel newYorkClock = new ClockPanel("America/New_York", "New York, New York, U.S.A.");
        ClockPanel londonClock = new ClockPanel("Europe/London", "London, United Kingdom");
        ClockPanel frankfurtClock = new ClockPanel("Europe/Berlin", "Frankfurt, Germany");
        ClockPanel seoulClock = new ClockPanel("Asia/Seoul", "Seoul, South Korea");

        frame.add(sanFranciscoClock);
        frame.add(newYorkClock);
        frame.add(londonClock);
        frame.add(frankfurtClock);
        frame.add(seoulClock);

        frame.setVisible(true);
    }
}

class ClockPanel extends JPanel {
    private JLabel label;

    public ClockPanel(String timeZoneID, String cityName) {
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneID);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(timeZone);
        String time = sdf.format(Calendar.getInstance(timeZone).getTime());

        label = new JLabel(cityName + ": " + time);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentTime = sdf.format(Calendar.getInstance(timeZone).getTime());
                label.setText(cityName + ": " + currentTime);
            }
        });
        timer.start();
    }
}
