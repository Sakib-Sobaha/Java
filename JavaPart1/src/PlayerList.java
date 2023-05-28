import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerList {
    public static void main(String[] args) {
        List<Player> playerList = new ArrayList();
        System.out.println(playerList.size());

        Player p1 = new Player("DaviddeGea","Spain",30,1.92,"ManchesterUnited","Goalkeeper",1,375000.0);
        Player p2 = new Player ("Harry","England",28,1.94,"ManchesterUnited","Midfielder",6,29000.0);
        Player p3 = new Player("Paul Pogba","France",28,1.91,"Manchester United","Midfielder",6,290000.0);

        playerList.add(p1);
        playerList.add(p2);
        playerList.add(p3);

        System.out.println(playerList.size());

        for(Player p : playerList){
            System.out.println(p.getName()+", "+p.getCountry()+", "+p.getAge()+", "+p.getHeight()+", "+p.getClub()+", "+p.getPosition()+", "+p.getNumber()+", "+p.getSalary());

        }
        for(int i=0; i < playerList.size(); ++i){
            Player p = playerList.get(i);
            //System.out.println(p.getName()+", "+p.getCountry()+", "+p.getAge()+", "+p.getHeight()+", "+p.getClub()+", "+p.getPosition()+", "+p.getNumber()+", "+p.getSalary());
            System.out.println(p);

        }

        Scanner input = new Scanner(System.in);

        String searchName = input.next();
        int searchIndex = -1;
        for(int i=0 ;i < playerList.size(); ++i){
            Player p = playerList.get(i);
            if(p.getName().equals(searchName)){
                searchIndex = i;
                break;
            }
        }
       /* if(Scanner.hasNextInt()){
            int age = Scanner.nextInt();
        }*/
        if(searchIndex != -1){
            playerList.remove(searchIndex);
        }

        for(Player p : playerList){
            System.out.println(p.getName()+", "+p.getCountry()+", "+p.getAge()+", "+p.getHeight()+", "+p.getClub()+", "+p.getPosition()+", "+p.getNumber()+", "+p.getSalary());
        }
    }
}
