package main.java.memoranda.gym;

import java.util.Date;
import java.util.Scanner;

public class ConsoleGymApp {

    private Gym gym;


    /**
     * CLI will be replaced by the UI.
     * @param args
     */
    public static void main(String[] args) {
        new ConsoleGymApp().run();
    }

    public void run(){
        this.gym = new Gym();
        Scanner scanner = new Scanner(System.in);

        //login loop
        while(true){

            this.user_print_loginMenu();

            String input = scanner.next();

            switch(input){
                case "1":
                {
                    System.out.println("adminLogin");

                    System.out.print("Email: ");
                    String email = scanner.next();
                    System.out.print("Password: ");
                    String password = scanner.next();

                    gym.adminLogin(email,password);
                    break;
                }
                case "2":
                {
                    System.out.println("userLogin");

                    System.out.print("Email: ");
                    String email = scanner.next();
                    System.out.print("Password: ");
                    String password = scanner.next();

                    gym.userLogin(email,password);
                    break;
                }
                case "3":
                {
                    return;
                }
            }

            if(gym.isLoggedIn)
                break;
        }

        //main gym loop
        while(true){
            if(gym.isAdmin==true){
                admin_menu();
            }
            else if(gym.user.isTrainer()){
                trainer_menu();
            }
            else{
                user_menu();
            }
        }

    }


    //////////////////////////////////////////////////////Test printing

    void user_print_loginMenu(){
        System.out.println("GymMenu: - default admin is admin/admin");
        System.out.println("1) adminLogin");
        System.out.println("2) userLogin");
        System.out.println("3) exit");
    }

    void admin_menu(){


        System.out.println("AdminMenu:");
        System.out.println("1) createClass");

        Scanner s = new Scanner(System.in);

        switch(s.next()){
            case "1":
            {
                console_createClass();
                break;
            }
        }
        //TODO

    }

    void trainer_menu(){
        System.out.println("TrainerMenu: - default admin is admin/admin");
        System.out.println("1) ");
        //TODO

    }

    void user_menu(){
        System.out.println("UserMenu: - default admin is admin/admin");
        System.out.println("1) ");
        //TODO
    }

    Belt console_getBelt(){
        Scanner s = new Scanner(System.in);

        System.out.println("SelectBelt");
        System.out.println("1) white");
        System.out.println("2) yellow");
        System.out.println("3) orange");
        System.out.println("4) purple");
        System.out.println("5) blue");
        System.out.println("6) blue_stripe");
        System.out.println("7) green");
        System.out.println("8) green_stripe");
        System.out.println("9) brown1");
        System.out.println("10) brown2");
        System.out.println("11) brown3");
        System.out.println("12) black1");
        System.out.println("13) black2");
        System.out.println("14) black3");

        switch(s.next()){
            case "1":
            {
                return new Belt(Belt.Rank.white);
            }
            case "2":
            {
                return new Belt(Belt.Rank.yellow);
            }
            case "3":
            {
                return new Belt(Belt.Rank.orange);
            }
            case "4":
            {
                return new Belt(Belt.Rank.purple);
            }
            case "5":
            {
                return new Belt(Belt.Rank.blue);
            }
            case "6":
            {
                return new Belt(Belt.Rank.white);
            }
            case "7":
            {
                return new Belt(Belt.Rank.green);
            }
            case "8":
            {
                return new Belt(Belt.Rank.green_stripe);
            }
            case "9":
            {
                return new Belt(Belt.Rank.brown1);
            }
            case "10":
            {
                return new Belt(Belt.Rank.brown3);
            }
            case "11":
            {
                return new Belt(Belt.Rank.black1);
            }
            case "12":
            {
                return new Belt(Belt.Rank.black2);
            }
            case "13":
            {
                return new Belt(Belt.Rank.black3);
            }
        }

        return null;
    }

    //////////////////////////////////////////////////////End Test printing


    void console_createClass(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("ClassType");
        System.out.println("1) private");
        System.out.println("2) public");

        int classType = scanner.nextInt();

        System.out.print("UnixTime: ");
        String ms = scanner.next();
        System.out.print("Duration: ");
        String duration = scanner.next();
        System.out.print("RoomNumber: ");
        String roomNumber = scanner.next();

        Integer rn = Integer.parseInt(roomNumber);
        Integer dur = Integer.parseInt(duration);
        Long st = Long.parseLong(ms);
        Belt beltReq = console_getBelt();

        gym.admin_createClass(classType,st,dur,rn,beltReq);
    }





}
