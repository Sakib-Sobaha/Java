import java.util.ArrayList;
import java.util.List;
public class Club {
    private int playerNumber;
    private String clubName;
    public List<Player>playerList = new ArrayList();
    private double maximumSalary;
    private int maximumAge;
    private double maximumHeight;

    public Club(){
        //playerList = new ArrayList<Player>(7);

    }

    public Club(String clubName){
        this.clubName = clubName;
    }
    public void setclubName(String clubName){
        this.clubName = clubName;
    }
    public String getclubName(){
        return this.clubName;
    }
    public void addPlayer(Player p){
        this.playerList.add(p);
    }
    public void printPlayers(){
        for(Player p : playerList){
            System.out.println(p);
        }
    }
    public int Listsize(){
        return this.playerList.size();
     }
    public int playerNumbers(Club c,List<Player>p){
        playerNumber = 0;
        for(Player p1 : p){
            Player t = p1;
            if(t.getClub().equals(c.getclubName())){
                playerNumber++;
            }
        }
        return playerNumber;
    }
    public double getMaximumSalary(){
        maximumSalary = playerList.get(0).getSalary();
        for(Player p : playerList){
            if(p.getSalary() > maximumSalary){
                maximumSalary = p.getSalary();
            }
        }
        return maximumSalary;
    }
    public Player hasMaximumSalary(double maximumSalary){
        Player t = new Player();
        for(Player p : playerList){
            if(p.getSalary() == (maximumSalary)){
                t = p;
                break;
            }
        }
        return t;
    }
    public int getMaximumAge(){
        maximumAge = playerList.get(0).getAge();
        for(Player p : playerList){
            if(p.getAge() > maximumAge){
                maximumAge = p.getAge();
            }
        }
        return maximumAge;
    }
    public Player hasMaximumAge(int maximumAge){
        Player t = new Player();
        for(Player p : playerList){
            if(p.getAge() == (maximumAge)){
                t = p;
                break;
            }
        }
        return t;
    }
    public double getMaximumHeight(){
        maximumHeight = playerList.get(0).getHeight();
        for(Player p : playerList){
            if(p.getHeight() > maximumHeight){
                maximumHeight = p.getHeight();
            }
        }
        return maximumHeight;
    }
    public Player hasMaximumHeight(double maximumHeight){
        Player t = new Player();
        for(Player p : playerList){
            if(p.getHeight() == maximumHeight){
                t = p;
                break;
            }
        }
        return t;
    }
    @Override
    public String toString(){
        getClass().getSimpleName();
        return this.clubName;
    }
}
