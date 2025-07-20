package Java_Project1;

import java.util.Scanner;

public class Main {

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    public static final String RED = "\u001B[31m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileHandler fileHandler = new FileHandler();

        System.out.println(CYAN + "👋 Welcome to FocusQuest! Please enter your username :" + RESET);
        String nameofuser;
        while (true) {
            nameofuser = scanner.nextLine().trim();
            if (!nameofuser.isEmpty() && !nameofuser.matches("\\d+")) {
                break;
            } else {
                System.out.println(RED + "⚠️ Invalid username. It should not be empty or contain only digits. Please try again:" + RESET);
            }
        }

        UserProfile loadedUser = fileHandler.loadUserProfile(nameofuser);
        UserProfile user = null;

        if (loadedUser != null) {
            System.out.println(PURPLE + "✨ A previous user profile was found for '" + loadedUser.getUserName() + "'." + RESET);
            System.out.print(YELLOW + "Do you want to continue as this user? (Y/N): " + RESET);
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("y")) {
                user = loadedUser;
                System.out.println(GREEN + "😊 Welcome back to FocusQuest " + user.getUserName() + "! 😊" + RESET);
            }
        }

        if (user == null) {
            user = new UserProfile(nameofuser);
            System.out.println(GREEN + "😊 Welcome to FocusQuest " + nameofuser + ", your personal productivity companion! 😊" + RESET);
            System.out.println(PURPLE + "🚶‍➡️ \"Small steps every day lead to big results. Stay focused!\" 💵💰" + RESET);
        }

        PomodoroManager pm = new PomodoroManager();
        boolean run = true;

        while (run) {
            System.out.println(YELLOW + "\n===== 📋 FocusQuest Menu =====" + RESET);
            System.out.println(CYAN + "1️⃣ View Profile" + RESET);
            System.out.println(CYAN + "2️⃣ View Tasks" + RESET);
            System.out.println(CYAN + "3️⃣ Add Task" + RESET);
            System.out.println(CYAN + "4️⃣ Start Pomodoro" + RESET);
            System.out.println(CYAN + "5️⃣ Remove Task" + RESET);
            System.out.println(CYAN + "6️⃣ Save & Quit" + RESET);
            System.out.print(YELLOW + "👉 Choose an option: " + RESET);

            String input = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(RED + "⚠️ Invalid input. Please enter a valid number." + RESET);
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println(PURPLE + "\n👤 Your Profile:" + RESET);
                    System.out.println(user);
                    break;
                case 2:
                    System.out.println(PURPLE + "\n📝 Your Tasks:" + RESET);
                    user.viewTasks();
                    break;
                case 3:
                    String taskname;
                    while (true) {
                        System.out.print(CYAN + "✏️ Enter task name: " + RESET);
                        taskname = scanner.nextLine().trim();
                        if (!taskname.isEmpty() && !taskname.matches("\\d+")) {
                            break;
                        } else {
                            System.out.println(RED + "⚠️ Invalid task name. It should not be empty or contain only digits." + RESET);
                        }
                    }
                    System.out.print(CYAN + "⏱️ Enter estimated Pomodoros: " + RESET);
                    int estpomodoros;
                    try {
                        estpomodoros = Integer.parseInt(scanner.nextLine());
                        Task newTask = new Task(taskname, estpomodoros);
                        user.addTask(newTask);
                    } catch (NumberFormatException e) {
                        System.out.println(RED + "⚠️ Please enter a valid number for Pomodoros." + RESET);
                    }
                    break;
                case 4:
                    if (user.getTasks().isEmpty()) {
                        System.out.println(RED + "⚠️ No tasks available. Please add a task first." + RESET);
                    } else {
                        user.viewTasks();
                        System.out.print(CYAN + "🔹 Select task number to start Pomodoro: " + RESET);
                        try {
                            int taskIndex = Integer.parseInt(scanner.nextLine()) - 1;
                            if (taskIndex >= 0 && taskIndex < user.getTasks().size()) {
                                System.out.print(CYAN + "⏳ Enter Pomodoro duration with unit (e.g., 25m, 1500s, 1h): " + RESET);
                                String durationInput = scanner.nextLine().trim().toLowerCase();
                                int durationInSeconds = 0;
                                if (durationInput.endsWith("s")) {
                                    durationInSeconds = Integer.parseInt(durationInput.replace("s", ""));
                                } else if (durationInput.endsWith("m")) {
                                    durationInSeconds = Integer.parseInt(durationInput.replace("m", "")) * 60;
                                } else if (durationInput.endsWith("h")) {
                                    durationInSeconds = Integer.parseInt(durationInput.replace("h", "")) * 3600;
                                } else {
                                    System.out.println(RED + "⚠️ Invalid format. Use 's', 'm', or 'h'." + RESET);
                                    continue;
                                }
                                pm.startPomodoro(user.getTasks().get(taskIndex), user, durationInSeconds);
                            } else {
                                System.out.println(RED + "⚠️ Invalid task selection." + RESET);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(RED + "⚠️ Invalid input. Please enter a valid number." + RESET);
                        }
                    }
                    break;
                case 5:
                    if (user.getTasks().isEmpty()) {
                        System.out.println(RED + "⚠️ No tasks to remove." + RESET);
                    } else {
                        user.viewTasks();
                        System.out.print(CYAN + "🔹 Enter task number to remove: " + RESET);
                        try {
                            int removeIndex = Integer.parseInt(scanner.nextLine()) - 1;
                            if (removeIndex >= 0 && removeIndex < user.getTasks().size()) {
                                String removedTask = user.getTasks().get(removeIndex).getName();
                                user.getTasks().remove(removeIndex);
                                System.out.println(GREEN + "✅ Task '" + removedTask + "' removed successfully." + RESET);
                            } else {
                                System.out.println(RED + "⚠️ Invalid task selection." + RESET);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(RED + "⚠️ Invalid input. Please enter a valid number." + RESET);
                        }
                    }
                    break;
                case 6:
                    fileHandler.saveUserProfile(user);
                    System.out.println(GREEN + "👋 Goodbye! Your progress has been saved. Thank you for using FocusQuest." + RESET);
                    run = false;
                    break;
                default:
                    System.out.println(RED + "❌ Invalid option. Please choose again." + RESET);
            }
        }
        scanner.close();
    }
}
