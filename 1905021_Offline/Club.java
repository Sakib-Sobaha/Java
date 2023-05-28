public class Club {
    private int Id;
    private String name;
    private int points;

    public Club(){
        this.Id = 0;
        this.name = null;
    }

    public Club(Club c) {
        setId(c.Id);
        setName(c.name);
        setPoints(c.points);

    }

    public void setId(int Id){
        this.Id = Id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setPoints(int points){
        this.points += points;
    }
    public int getPoints(){
        return this.points;
    }


}
