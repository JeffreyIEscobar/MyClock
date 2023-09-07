package myclock;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Clock {
    public static void main(String[] args) {
        JFrame frame = new JFrame("World Clocks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 200);
        frame.setLayout(new GridLayout(1, 4));

        ClockPanel sanFranciscoClock = new ClockPanel("America/Los_Angeles", "San Francisco");
        ClockPanel newYorkClock = new ClockPanel("America/New_York", "New York");
        ClockPanel londonClock = new ClockPanel("Europe/London", "London");
        ClockPanel seoulClock = new ClockPanel("Asia/Seoul", "Seoul");

        frame.add(sanFranciscoClock);
        frame.add(newYorkClock);
        frame.add(londonClock);
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
