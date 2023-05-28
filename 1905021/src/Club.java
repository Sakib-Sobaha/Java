public class Club {
    private int id;
    private String name;
    private Player[] players;
    // add your code here
    private int playerCount = Player.length;

    // you are not allowed to write any other constructor
    public Club() {
        this.players = new Player[11];
    }

    public double getSalary() {
        double total = 0;
        for (int i = 0; i < playerCount; i++) {
            total += players[i].getSalary();
        }
        return total;
    }

    public void setName(String s) {
        name = s;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        String name = this.name;
        return name;
    }
    public void addPlayer(Player player){
        players[playerCount++] = player;
    }
    public Player getMaxSalaryPlayer(){
        int  maxSlaryplayer = 0;
        for(int i=0;i<playerCount;++i){
            if(players[i].getSalary() > maxSlaryplayer){
                maxSlaryplayer = i ;
            }
        }
        return players[maxSlaryplayer];
    }


    // add your code here
}