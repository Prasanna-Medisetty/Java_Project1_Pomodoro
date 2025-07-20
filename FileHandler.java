package Java_Project1;

import java.io.*;

public class FileHandler implements DataStorage {

    @Override
    public void saveUserProfile(UserProfile user) {
        // Ensure 'resources' directory exists before saving
        File resourcesDir = new File("resources");
        if (!resourcesDir.exists()) {
            if (resourcesDir.mkdir()) {
                System.out.println("📁 'resources' folder created for saving profiles.");
            } else {
                System.out.println("⚠️ Failed to create 'resources' folder. Saving may fail.");
            }
        }

        String filename = "resources" + File.separator + user.getUserName() + ".dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(user);
            System.out.println("✅ User profile saved successfully.");
        } catch (IOException e) {
            System.out.println("⚠️ Error saving user profile: " + e.getMessage());
        }
    }

    @Override
    public UserProfile loadUserProfile(String username) {
        String filename = "resources" + File.separator + username + ".dat";
        File file = new File(filename);
        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (UserProfile) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Error loading user profile: " + e.getMessage());
            return null;
        }
    }
}
