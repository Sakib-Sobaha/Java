import java.util.ArrayList;
import java.util.List;

public class Country {
    private String countryName;
    private List<Player> playerList = new ArrayList();
    public int playercount;
    public Country(){

    }
    Country(String countryName){
        this.countryName = countryName;
    }
    public void setCountryName(String countryName){
        this.countryName = countryName;
    }
    public String getCountryName(){
        return this.countryName;
    }
    public int playersincountry(Country c){
        playercount = 0;
        for(Player p : playerList){
            if(c.getCountryName().equals(p.getCountry())){
                playercount++;
            }
        }
        return playercount;
    }
    @Override
    public String toString(){
        getClass().getSimpleName();
        return this.countryName;
    }
}
