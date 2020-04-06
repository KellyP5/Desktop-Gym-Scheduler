package main.java.memoranda.gym;

import java.util.ArrayList;
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

        createDummyData();//creates dummy data for testing

        //main gym loop
        while(true){
            menu();
        }

    }

    void createDummyData(){
        //private class starting 28APR2020 @ 0900 for white belts in room 2
        gym.createClass(1,1588086000000l,60,1,new Belt(Belt.Rank.white));

        //group class starting 28APR2020 @ 0800 for white belts in room 1
        gym.createClass(2,1588086000000l,60,2,new Belt(Belt.Rank.white));

        //create adminAccount
        gym.createAdmin("boss@gym.com", "b","1234");

        //create trainers
        gym.createTrainer("joe@gym.com","Joe","1234", new Belt(Belt.Rank.black3));
        gym.createTrainer("jill@gym.com","jill","1234",new Belt(Belt.Rank.black3));

        gym.assignTrainingRank("jill@gym.com",new Belt(Belt.Rank.white));

        //create customers
        gym.createCustomer("user1@gmail.com","user1","1234");
        gym.createCustomer("user2@gmail.com","user2","1234");
        gym.createCustomer("user3@gmail.com","user3","1234");
        gym.createCustomer("user4@gmail.com","user4","1234");


        //assign users and trainers to a public class
        gym.userBookClass("user1@gmail.com", new Date(1588086000000l),2);
        gym.userBookClass("user2@gmail.com", new Date(1588086000000l),2);
        gym.assignClassroomTrainer("joe@gym.com", new Date(1588086000000l),2);

        //assign users and trainers to a private class, should only allow 1  user and 1 trainer
        gym.userBookClass("user3@gmail.com", new Date(1588086000000l),1);
        gym.userBookClass("user4@gmail.com", new Date(1588086000000l),1);
        gym.assignClassroomTrainer("jill@gym.com", new Date(1588086000000l),1);

        printClasses();

    }


    //////////////////////////////////////////////////////Test printing

    void menu(){


        System.out.println("Menu:");
        System.out.println("0) login");
        System.out.println("1) printClasses");
        System.out.println("2) createClass");
        System.out.println("3) createCustomer");
        System.out.println("4) createTrainer");
        System.out.println("5) createAdmin");
        System.out.println("6) assignClassroomTrainer");
        System.out.println("7) changeUserBelt");
        System.out.println("8) changeUserTrainingBelt");
        System.out.println("9) userBookClass");


        Scanner s = new Scanner(System.in);

        switch(s.next()){
            case "0":{
                login();
            }
            case "1":
            {
                printClasses();
                break;
            }
            case "2":
            {
                createClass();
                break;
            }
            case "3":
            {
                createCustomer();
                break;
            }
            case "4":
            {
                createTrainer();
                break;
            }
            case "5":
            {
                createAdmin();
                break;
            }
            case "6":
            {
                assignClassroomTrainer();
                break;
            }
            case "7":
            {
                changeUserBelt();
                break;
            }
            case "8":
            {
                changeTrainerTrainingBelt();
                break;
            }
            case "9":
            {
                userBookClass();
                break;
            }
        }

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


    void printClasses(){
        ArrayList<Class> classes = gym.getClasses();
        System.out.println("StartTime\tRoomNumber\tTrainerName");
        for(int i = 0;i< classes.size();i++){
            System.out.print(classes.get(i).getTime());
            System.out.print('\t');
            System.out.print(classes.get(i).getRoomNumber());
            System.out.print('\t');
            if(classes.get(i).hasTrainer()){
                System.out.print(classes.get(i).getTrainer().getName());
            }
            else{
                System.out.print("NO TRAINER");
            }
            System.out.println("\nUserName\tEmail");
            ArrayList<User> users = classes.get(i).getUsers();
            for(int j = 0;j< users.size();j++){
                System.out.print(users.get(j).getName());
                System.out.print('\t');
                System.out.println(users.get(j).getEmail());
            }
            System.out.println();

        }

    }

    void login(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("login");
        System.out.print("Email:");
        String email = scanner.next();
        System.out.print("Password:");
        String pw = scanner.next();

        if(gym.userLogin(email,pw)){
            System.out.println("Account found!");
        }

        if(gym.user instanceof Admin){
            System.out.println("Logged in as an Admin.");
        }
        else if(gym.user instanceof Trainer){
            System.out.println("Logged in as an Trainer.");
        }
        else if(gym.user instanceof Customer){
            System.out.println("Logged in as an Customer.");
        }
    }

    void createClass(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("createClass");

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

        gym.createClass(classType,st,dur,rn,beltReq);
    }

    void createTrainer(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("createTrainer");
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("UserName: ");
        String un = scanner.next();
        System.out.print("Password: ");
        String pw = scanner.next();
        Belt br = console_getBelt();
        gym.createTrainer(email,un,pw,br);
    }

    void createCustomer(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("createTrainer");
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("UserName: ");
        String un = scanner.next();
        System.out.print("Password: ");
        String pw = scanner.next();
        gym.createCustomer(email,un,pw);
    }

    void createAdmin(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("createAdmin");
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("UserName: ");
        String un = scanner.next();
        System.out.print("Password: ");
        String pw = scanner.next();
        gym.createAdmin(email,un,pw);
    }

    void assignClassroomTrainer(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("assignClassroomTrainer");
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("ClassStartTime: ");
        String ms = scanner.next();
        System.out.print("RoomNumber: ");
        String rn = scanner.next();

        Long st = Long.parseLong(ms);
        Integer roomNumber = Integer.parseInt(rn);

        gym.assignClassroomTrainer(email,new Date(st),roomNumber);
    }

    void changeUserBelt(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("changeUserBelt");
        System.out.print("Email: ");
        String email = scanner.next();
        Belt belt = console_getBelt();

        gym.changeUserBelt(email,belt);
    }

    void changeTrainerTrainingBelt(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("changeUserTrainingBelt");
        System.out.print("Email: ");
        String email = scanner.next();
        Belt belt = console_getBelt();

        gym.changeUserTrainingBelt(email,belt);
    }

    void userBookClass(){
        Scanner scanner = new Scanner(System.in);

        //        gym.userBookClass("user3@gmail.com", new Date(1588086000000l),1);

        System.out.println("userBookClass");
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("RoomNumber: ");
        String rn = scanner.next();
        System.out.print("ClassStartTime: ");
        String ms = scanner.next();
        long st = Long.parseLong(ms);
        gym.userBookClass(email,new Date(st),Integer.parseInt(rn));
    }

}
