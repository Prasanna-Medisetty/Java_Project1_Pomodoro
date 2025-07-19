package Java_Project1;

public interface DataStorage {
    void saveUserProfile(UserProfile user);
    UserProfile loadUserProfile(String userName);
}
