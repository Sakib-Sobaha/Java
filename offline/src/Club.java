public class Club {
    private int id;
    private String name;
    private Player[] players;
    // add your code here

    // you are not allowed to write any other constructor
    public Club() {
        this.players = new Player[11];
    }

    public double getSalary() {
        double total = 0;
        int playerCount = 5;
        for (int i = 0; i < playerCount; i++) {
            total += players[i].getSalary();
        }
        return total;
    }
    public int getId()
    {
        return id;
    }
    public String getName(){
        return name;
    }

    // add your code here
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void addPlayer(Player[]  player){
        players Player[] Player[] player1 = player;

    }

    public Club getMaxSalaryPlayer() {
    }
}