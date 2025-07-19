package Java_Project1;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int estimatedPomodoros;
    private int completedPomodoros;
    private long totalTimeSpent; // in milliseconds

    public Task(String name, int estimatedPomodoros) {
        this.name = name;
        this.estimatedPomodoros = estimatedPomodoros;
        this.completedPomodoros = 0;
        this.totalTimeSpent = 0;
    }

    public void markPomodoroComplete(long duration) {
        completedPomodoros++;
        totalTimeSpent += duration;
    }

    public boolean isComplete() {
        return completedPomodoros >= estimatedPomodoros;
    }

    public String getName() {
        return name;
    }

    public int getEstimatedPomodoros() {
        return estimatedPomodoros;
    }

    public int getCompletedPomodoros() {
        return completedPomodoros;
    }

    public long getTotalTimeSpent() {
        return totalTimeSpent;
    }

    @Override
    public String toString() {
        return "\u001B[36mTask:\u001B[0m " + name + " | \u001B[35mCompleted:\u001B[0m " + completedPomodoros + "/" + estimatedPomodoros +
                " | \u001B[33mTime Spent:\u001B[0m " + (totalTimeSpent / 60000) + " min";
    }
}
