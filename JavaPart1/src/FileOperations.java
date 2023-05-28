import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileOperations {
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
        /*Scanner input1 = new Scanner(System.in).useDelimiter("\n");
        String k = input1.next();
        System.out.println("You typed : "+k);
        Player p = new Player("Sakib Sobaha","Bangladesh",21,1.85,"Chelsea","Goalkeeper",10,25000);
        System.out.println(p);

        playerList.add(p);
        writeToFile(playerList);*/
        //List<Club> clubList = new ArrayList();
        /*for(Player p : playerList){
            //System.out.println(p.getName()+","+p.getCountry()+","+p.getAge()+","+p.getHeight()+","+p.getClub()+","+p.getPosition()+","+p.getNumber()+","+p.getSalary());
            System.out.println(p);
        }*/
        /*for(Player p : playerList){
            Player t = p;
            Club c1 = new Club(p.getClub());
            clubList.add(c1);
        }
        for(int i=0; i<clubList.size(); ++i){
            for(int j = i; j<clubList.size(); ++j){
                if(clubList.get(i).getclubName().equals(clubList.get(j).getclubName())){
                    //System.out.println("HERE");
                    //System.out.println(clubList.get(i)+", "+clubList.get(j));
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
        for(Club c : clubList){
            c.printPlayers();
        }
        System.out.println(clubList.get(0).Listsize());
        System.out.println("Name of Clubs");
        System.out.println(clubList);
        for(Club c : clubList){
            System.out.println("Number of players from club "+c.getclubName()+" : "+c.playerNumbers(c,playerList));
        }*/
        /*for(Club c : clubList){
            c.printPlayers();
        }*/
        /*List<Country>countryList = new ArrayList();
        for(Player p : playerList){
            Country c = new Country(p.getCountry());
            countryList.add(c);
        }
        System.out.println(countryList);
        for(int i=0; i<countryList.size()-1; ++i){
            for(int j=i+1; j<countryList.size(); ++j){
                if(countryList.get(i).getCountryName().equals(countryList.get(j).getCountryName())){
                    countryList.remove(j);
                    //countryList.remove(i);
                }
            }
        }

        System.out.println(countryList);
        int playercount;
        for(Country c : countryList){
            playercount = 0;
            for(Player p: playerList){
                if(p.getCountry().equals(c.getCountryName())){
                    playercount++;

                }
            }
            System.out.println("Number of players in country "+c.getCountryName()+" : "+playercount);
        }*/
        /*List<Age>ageList = new ArrayList();

        for(Player p : playerList){
            Age a = new Age(p.getAge());
            ageList.add(a);
        }
        System.out.println(ageList);
        for(int i=0; i<ageList.size() - 1; ++i){
            for(int j =0; j<ageList.size();++j){
                if(ageList.get(j).getAge() == ageList.get(i).getAge()){
                    ageList.remove(j);
                }
            }
        }
        System.out.println(ageList);
*/

    }
}
