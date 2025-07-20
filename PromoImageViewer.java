package Java_Project1;

import javax.swing.*;
import java.awt.*;

public class PromoImageViewer {
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("🚀 FocusQuest - Pomodoro Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Load image
        ImageIcon imageIcon = new ImageIcon("resources/FocusQuest.png");

        // Scale image to fit screen
        Image scaledImage = imageIcon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);

        // Display image in label
        JLabel label = new JLabel(imageIcon);
        frame.add(label, BorderLayout.CENTER);

        // Add a "Start FocusQuest" button to proceed to your console demo
        JButton proceedButton = new JButton("🚀 Start FocusQuest Console Demo");
        proceedButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        proceedButton.addActionListener(e -> {
            frame.dispose();
            // Here, you can call your Main.main(new String[]{});
//            System.out.println("Launching FocusQuest console...");
            Main.main(new String[]{});

        });
        frame.add(proceedButton, BorderLayout.SOUTH);

        // Pack and show
        frame.pack();
        frame.setLocationRelativeTo(null); // center on screen
        frame.setVisible(true);
    }
}
