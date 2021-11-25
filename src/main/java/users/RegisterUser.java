package users;


import java.util.Scanner;

public class RegisterUser {

    private static final Scanner scanner = new Scanner(System.in);

    public Users signUp(boolean adminCheck, boolean loginCheck) {
        boolean adminAnswer = false;
        System.out.println("pls enter a username: ");
        String username = scanner.nextLine();
        System.out.println("pls enter your password:(8 characters or higher)");
        String password = scanner.nextLine();
        if (adminCheck) {
            System.out.println("do you want to create an admin?(y or n) ");
            String answer = scanner.nextLine().toLowerCase();
            if (answer.equals("y")) {
                adminAnswer = true;
            }
        }else if (loginCheck) {
            System.out.println("you are logged in and not a admin so you cant sign up");
        }
        return new Users(username, password, adminAnswer);
    }
}
