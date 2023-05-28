import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;



public class Main {
    private static final String INPUT_FILE_NAME = "players.txt";
    private static final String OUTPUT_FILE_NAME = "playersout.txt";

    public static List<Player> readFromFile() throws Exception{
        List<Player> playerList = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while(true){
            String line = br.readLine();
            if(line == null)
                break;
            String[] tokens = line.split(",");
            Player p = new Player();
            p.setName(tokens[0]);
            p.setCountry(tokens[1]);
            p.setAge(Integer.parseInt(tokens[2]));
            p.setHeight(Double.parseDouble(tokens[3]));
            p.setClub(tokens[4]);
            p.setPosition(tokens[5]);
            p.setNumber(Integer.parseInt(tokens[6]));
            p.setSalary(Double.parseDouble(tokens[7]));
            playerList.add(p);
        }
        br.close();
        return playerList;
    }
    public static void writeToFile(List<Player> playerList) throws Exception{
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for(Player p : playerList){
            bw.write(p.getName()+","+p.getCountry()+","+p.getAge()+","+p.getHeight()+","+p.getClub()+","+p.getPosition()+","+p.getNumber()+","+p.getSalary());
            bw.write("\n");
        }
        bw.close();
    }

    public static void main(String[] args) throws Exception{
        List<Player> playerList = readFromFile();
        List<Club> clubList = new ArrayList();
        List<Country>countryList = new ArrayList();
       /* List<Age>ageList = new ArrayList();

        for(Player p : playerList){
            Age a = new Age(p.getAge());
            ageList.add(a);
        }
        for(int i=0; i<ageList.size() - 1; ++i){
            for(int j =0; j<ageList.size();++j){
                if(ageList.get(j).getAge() == ageList.get(i).getAge()){
                    ageList.remove(j);
                }
            }
        }*/


        for(Player p : playerList){
            Player t = p;
            Club c1 = new Club(p.getClub());
            clubList.add(c1);
        }
        for(int i=0; i<clubList.size(); ++i){
            for(int j = i; j<clubList.size(); ++j){
                if(clubList.get(j).getclubName().equals(clubList.get(i).getclubName())){
                    clubList.remove(j);
                    clubList.remove(i);
                }

            }
        }
        for(Club c : clubList){
            for(Player p : playerList){
                if(p.getClub().equals(c.getclubName())){
                    c.addPlayer(p);
                }
            }
        }
        for(Player p : playerList){
            Country c = new Country(p.getCountry());
            countryList.add(c);
        }
        for(int i=0; i<countryList.size()-1; ++i){
            for(int j=i+1; j<countryList.size(); ++j){
                if(countryList.get(i).getCountryName().equals(countryList.get(j).getCountryName())){
                    countryList.remove(j);
                }
            }
        }

        Scanner input = new Scanner(System.in).useDelimiter("\n");
        boolean run = true;
        boolean backto = false;
        boolean again = false;
        int option,suboption1,suboption2,suboption3,suboption4;
        String playerName,clubName,country,position;
        String searchplayerName,searchclubName,searchcountry,searchposition;
        int age,number;
        int searchage,searchnumber,playercount;
        int maximumage;
        double height,salary,yearlysalary = 0.0;
        double searchheight,searchsalary1,searchsalary2;
        double maximumheight,maximumsalary;
        while(run){
            boolean chosen_six = false;
            boolean chosen_five = false;
            if(run  || backto) {
                System.out.println("Main Menu:");
                System.out.println("(1) Search Players");
                System.out.println("(2) Search Clubs");
                System.out.println("(3) Add Player");
                System.out.println("(4) Exit System");
                //option = input.nextInt();
            }
            option = input.nextInt();
            if(option == 4){
                run = false;
                backto = false;
                System.exit(0);
            }
            else if (option == 1 && !chosen_six) {

                do {
                    System.out.println("Player Searching Options:");
                    System.out.println("(1) By Player Name");
                    System.out.println("(2) By Club and Country");
                    System.out.println("(3) By Position");
                    System.out.println("(4) By Salary Range");
                    System.out.println("(5) Country-wise player count");
                    System.out.println("(6) Back to Main Menu");

                    suboption1 = input.nextInt();
                    if (suboption1 == 6) {
                        chosen_six = true;
                        if (chosen_six) {
                            backto = true;
                        }
                    }
                    else if (suboption1 == 1 && !chosen_six) {
                        searchplayerName = input.next();
                        boolean isfound = false;
                        for (Player p : playerList) {
                            Player t = p;
                            if (t.getName().equals(searchplayerName)) {
                                //System.out.println(t.getName()+", "+t.getCountry()+", "+t.getAge()+", "+t.getHeight()+", "+t.getClub()+", "+t.getPosition()+", "+t.getNumber()+", "+t.getSalary());
                                System.out.println(t);
                                isfound = true;

                                break;
                            }
                        }
                        if (!isfound) {
                            System.out.println("No such player with this name");
                            backto = false;
                        }
                    }
                    else if (suboption1 == 2 && !chosen_six) {
                        searchcountry = input.next();
                        searchclubName = input.next();
                        boolean isfound = false;
                        for (Player p : playerList) {
                            Player t = p;
                            if (searchcountry.equals(t.getCountry())) {
                                isfound = true;
                                if (searchclubName == "ANY") {
                                    System.out.println(t);
                                }
                                else if (searchclubName.equals(t.getClub())) {
                                    System.out.println(t);
                                }
                            }
                        }
                        if (!isfound) {
                            System.out.println("No such palyer with this country and club");
                        }
                    }
                    else if (suboption1 == 3 && !chosen_six) {
                        searchposition = input.next();
                        boolean isfound = false;
                        for (Player p : playerList) {
                            Player t = p;
                            if (searchposition.equals(t.getPosition())) {
                                isfound = true;
                                System.out.println(t);
                                //break;
                            }
                        }
                        if (!isfound) {
                            System.out.println("No such player with this position");
                        }
                    }
                    else if (suboption1 == 4 && !chosen_six) {
                        searchsalary1 = input.nextDouble();
                        searchsalary2 = input.nextDouble();
                        boolean isfound = false;
                        for (Player p : playerList) {
                            if (p.getSalary() >= searchsalary1 && p.getSalary() <= searchsalary2) {
                                System.out.println(p);
                                isfound = true;
                            }
                        }
                        if (!isfound) {
                            System.out.println("No such player with this weekly salary range");
                        }
                    }
                    else if (suboption1 == 5 && !chosen_six) {
                        for (Country c : countryList) {
                            playercount = 0;
                            for (Player p : playerList) {
                                if (p.getCountry().equals(c.getCountryName())) {
                                    playercount++;

                                }
                            }
                            System.out.println("Number of players in country " + c.getCountryName() + " : " + playercount);
                        }
                    }
                    else{
                        System.out.println("Not a valid suboption");
                    }
                } while (!chosen_six);
            }
            else if (option == 2 && !chosen_five) {
                do {
                    System.out.println("Club Searching Options:");
                    System.out.println("(1) Player(s) with the maximum slary of a club");
                    System.out.println("(2) Player(s) with the maximum age of a club");
                    System.out.println("(3) Player(s) with the maximum height of a club");
                    System.out.println("(4) Total yearly salary of a club");
                    System.out.println("(5) Back to Main Menu");
                    suboption2 = input.nextInt();
                    if (suboption2 == 5) {
                        backto = true;
                        chosen_five = true;
                    }
                    else if (suboption2 == 1) {
                        searchclubName = input.next();
                        boolean isfound = false;
                        for (Club c : clubList) {
                            if (c.getclubName().equals(searchclubName)) {
                                isfound = true;
                                maximumsalary = c.getMaximumSalary();
                                System.out.println("Information of the player having maximum salary : ");
                                System.out.println(c.hasMaximumSalary(maximumsalary));
                                break;
                            }
                        }
                        if (!isfound) {
                            System.out.println("No such club with this name");
                        }

                    }
                    else if (suboption2 == 2) {
                        searchclubName = input.next();
                        boolean isfound = false;
                        for (Club c : clubList) {
                            if (c.getclubName().equals(searchclubName)) {
                                isfound = true;
                                maximumage = c.getMaximumAge();
                                System.out.println("Information of the player having maximum age : ");
                                System.out.println(c.hasMaximumAge(maximumage));
                                break;
                            }
                        }
                        if (!isfound) {
                            System.out.println("No such club with this name.");
                        }
                    }
                    else if (suboption2 == 3) {
                        searchclubName = input.next();
                        boolean isfound = false;
                        for (Club c : clubList) {
                            if (c.getclubName().equals(searchclubName)) {
                                isfound = true;
                                maximumheight = c.getMaximumHeight();
                                System.out.println("Information of the player having maximum age : ");
                                System.out.println(c.hasMaximumHeight(maximumheight));
                                break;
                            }
                        }
                        if (!isfound) {
                            System.out.println("No such club with this name");
                        }
                    }
                    else if (suboption2 == 4) {
                        searchclubName = input.next();
                        boolean isfound = false;
                        //Club k = new Club();
                        for (Club c : clubList) {
                            if (c.getclubName().equals(searchclubName)) {
                                isfound = true;
                                for (int i = 0; i < c.Listsize(); ++i) {
                                    yearlysalary += c.playerList.get(i).getYearlysalary();
                                }
                                System.out.println("Total yearly salary of all players of this club : " + yearlysalary);
                                break;
                            }
                        }
                        if (!isfound) {
                            System.out.println("No such club with this name.");
                        }
                    }
                    else {
                        System.out.println("Not a valid sub-option");
                    }
                } while (!chosen_five);
            }
            else if (option == 3){
                int playerinclub = 0;
                boolean isfound = false;
                playerName = input.next();
                country = input.next();
                age = input.nextInt();
                height = input.nextDouble();
                clubName = input.next();
                position = input.next();
                number = input.nextInt();
                salary = input.nextDouble();
                Player p = new Player(playerName,country,age,height,clubName,position,number,salary);
                for(Club c: clubList){
                    if(c.getclubName().equals(clubName)){
                        playerinclub = c.playerNumbers(c,playerList);
                        isfound = true;
                        break;
                    }
                }
                if(isfound && playerinclub < 7){
                    playerList.add(p);
                    writeToFile(playerList);
                    backto = true;
                }
                else{
                    System.out.println("There is no such club");
                    backto = true;
                }

            }
            else{
                System.out.println("Not a valid input......");
                backto = true;
            }
        }




    }
}