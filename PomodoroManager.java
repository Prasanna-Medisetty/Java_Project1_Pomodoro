package Java_Project1;

public class PomodoroManager {
    private static final int XP_PER_POMODORO = 20; // Experience points per Pomodoro session

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String PURPLE = "\u001B[35m";
    private static final String RED = "\u001B[31m";

    public void startPomodoro(Task task, UserProfile user, int durationInSeconds) {
        System.out.println(PURPLE + "\n🍅 Starting Pomodoro for task: " + task.getName() + RESET);
        System.out.println(YELLOW + "⏳ Stay focused! Timer starting now..." + RESET);

        for (int remaining = durationInSeconds; remaining > 0; remaining--) {
            int minutes = remaining / 60;
            int seconds = remaining % 60;
            System.out.printf("\r" + CYAN + "⏳ Time Left: %02d:%02d " + RESET, minutes, seconds);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(RED + "\n⚠️ Timer interrupted." + RESET);
                break;
            }
        }

        System.out.println(GREEN + "\n👍 Pomodoro session completed for task: " + task.getName() + RESET);

        // Play beep alarm after completion
        try {
            for (int i = 0; i < 3; i++) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(RED + "Beep sequence interrupted." + RESET);
        }

        // ASCII confetti celebration
        System.out.println(PURPLE + "\n🎉🎉🎉 Congratulations! 🎉🎉🎉" + RESET);
        String[] celeb = {"💥 🎊 🎈 FocusQuest Celebration 🎈 🎊 💥"};
        for (String line : celeb) {
            System.out.println(YELLOW + line + RESET);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // Ignore interruption during celebration
            }
        }
        System.out.println(CYAN + "🚀 Ready for your next focus session!\n" + RESET);

        // Mark Pomodoro complete and give XP
        task.markPomodoroComplete(durationInSeconds * 1000L);
        user.addXP(XP_PER_POMODORO);
    }
}
