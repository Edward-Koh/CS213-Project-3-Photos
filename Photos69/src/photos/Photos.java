package photos;
import java.io.IOException;
import java.util.Scanner;
import photos.model.Album;
import photos.model.User;
import photos.model.UserManager;

// This is the main class that starts the app
public class Photos {

    public static void main(String[] args) {

        // load saved data or create new one
        UserManager manager = UserManager.load();

        // make sure admin and stock users exist
        manager.ensureDefaultUsers();

        // simple console menu (for testing only)
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n--- Photo App ---");
            System.out.println("1. List users");
            System.out.println("2. Add user");
            System.out.println("3. Delete user");
            System.out.println("4. Login");
            System.out.println("5. Exit");

            System.out.print("Choose: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                // show all users
                for (User u : manager.getUsers()) {
                    System.out.println(u.getUsername());
                }

            } else if (choice.equals("2")) {
                // add new user
                System.out.print("Enter username: ");
                String name = sc.nextLine();

                if (manager.addUser(name)) {
                    System.out.println("User added");
                } else {
                    System.out.println("User exists or invalid");
                }

            } else if (choice.equals("3")) {
                // delete user
                System.out.print("Enter username: ");
                String name = sc.nextLine();

                if (manager.removeUser(name)) {
                    System.out.println("User removed");
                } else {
                    System.out.println("User not found");
                }

            } else if (choice.equals("4")) {
                // login
                System.out.print("Enter username: ");
                String name = sc.nextLine();

                User user = manager.getUser(name);

                if (user == null) {
                    System.out.println("User not found");
                } else {
                    System.out.println("Logged in as " + user.getUsername());

                    // simple album view
                    for (Album a : user.getAlbums()) {
                        System.out.println("- " + a.getName() + " (" + a.getPhotoCount() + " photos)");
                    }
                }

            } else if (choice.equals("5")) {
                // save and exit
                try {
                    manager.save();
                    System.out.println("Saved. Goodbye!");
                } catch (IOException e) {
                    System.out.println("Error saving data");
                }
                break;

            } else {
                System.out.println("Invalid choice");
            }
        }

        sc.close();
    }
}