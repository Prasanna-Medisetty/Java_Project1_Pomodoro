package Java_Project1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserProfile implements Serializable {
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String PURPLE = "\u001B[35m";
    private static final String RED = "\u001B[31m";

    private String userName;
    private int xp;
    private int level;
    private List<Task> tasks;

    public UserProfile(String userName) {
        this.userName = userName;
        this.xp = 0;
        this.level = 1;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(GREEN + "✅ Task '" + task.getName() + "' added successfully." + RESET);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            String removedTask = tasks.get(index).getName();
            tasks.remove(index);
            System.out.println(GREEN + "🗑️ Task '" + removedTask + "' removed successfully." + RESET);
        } else {
            System.out.println(RED + "⚠️ Invalid task selection. Cannot remove." + RESET);
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println(RED + "⚠️ No tasks available." + RESET);
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(YELLOW + (i + 1) + ". " + tasks.get(i) + RESET);
            }
        }
    }

    public void addXP(int xpGained) {
        xp += xpGained;
        System.out.println(YELLOW + xpGained + " XP gained!" + RESET);
        while (xp >= level * 100) {
            levelUp();
        }
    }

    private void levelUp() {
        xp -= level * 100;
        level++;
        System.out.println(PURPLE + "🎉 Congratulations! You've reached Level " + level + "!" + RESET);
    }

    public String getUserName() {
        return userName;
    }

    public int getXp() {
        return xp;
    }

    public int getLevel() {
        return level;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        return PURPLE + "👤 User: " + userName + " | ⭐ Level: " + level + " | ⚡ XP: " + xp + RESET;
    }
}
