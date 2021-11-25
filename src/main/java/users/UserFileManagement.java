package users;

import exceptions.InvalidPassword;
import exceptions.InvalidUsername;
import exceptions.PasswordTooShort;
import exceptions.UsernameAlreadyExists;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserFileManagement{
    private static final Scanner scanner = new Scanner(System.in);
    private static Map<String, Users> usersMap = new HashMap<>();
    RegisterUser registerUser = new RegisterUser();
    String fileAddress = "/home/matin/Desktop/users.txt";
    String line = "";


    public void getDataFromTXT() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileAddress));

        while((line = br.readLine()) != null){
            String[] values = line.split(",");
            Users user = new Users(values[0], values[1], Boolean.parseBoolean(values[2]));
            usersMap.put(user.getUsername(), user);
        }
    }

    public void cleanTxtFile() throws IOException {
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(fileAddress));
        bw1.close();
    }

    public void listDataInToFile() throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileAddress, true));
        for(String userName : usersMap.keySet()){
            line = usersMap.get(userName).getUsername() +","+usersMap.get(userName).getPassword()+","+usersMap.get(userName).isAdmin+",false";
            bw.write(line+"\n");
        }
        bw.close();
    }

    public void signUp()  throws UsernameAlreadyExists, PasswordTooShort, IOException {
        Users user = registerUser.signUp(checkForAdmin(), checkForUserLogin());
        try {
            if (checkForUsername(user.getUsername())) {
                throw new UsernameAlreadyExists("this username is taken");
            } else if (checkForPassword(user.getPassword())) {
                throw new PasswordTooShort("password is too short");
            } else {
                usersMap.put(user.getUsername(), user);
            }
        } catch (UsernameAlreadyExists | PasswordTooShort e) {
            System.out.println(e.getMessage());
        }
    }

    public void login() throws InvalidPassword, InvalidUsername, IOException {
        System.out.println("pls enter a username: ");
        String username = scanner.nextLine();
        System.out.println("pls enter your password: ");
        String password = scanner.nextLine();
        try{
            boolean isntUser = true;
            for(String userName: usersMap.keySet()){
                if(userName.equals(username)){
                    if(usersMap.get(username).getPassword().equals(password)){
                        usersMap.get(username).setLoggedIn(true);
                        isntUser = false;
                        System.out.println("you are now logged in");

                    }else{
                        throw new InvalidPassword("wrong password");
                    }
                }
            }if(isntUser){throw new InvalidUsername("wrong username");}
        }catch (InvalidUsername|InvalidPassword e){
            System.out.println(e.getMessage());
        }
    }

    public boolean checkForUserLogin() {
        for (String username : usersMap.keySet()) {
            if (usersMap.get(username).isLoggedIn()) {
                return true;
            }
        }
        return false;

    }

    public boolean checkForAdmin() {
        for (String username : usersMap.keySet()) {
            if (checkForUserLogin()) {
                if (usersMap.get(username).isAdmin()) {
                    return true;
                }
            }
        }
        return false;

    }

    private boolean checkForUsername(String username) {
        for (String userName : usersMap.keySet()) {
            if (userName.equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkForPassword(String password) {
        return password.length() <= 8;
    }

    public Users findUser(String Username, String password){
        Users user = new Users();
        for(String userName : usersMap.keySet()){
            if(userName.equals(Username)){
                if(usersMap.get(userName).getPassword().equals(password)){
                    user = usersMap.get(userName);
                }
            }
        }
        return user;
    }
}
