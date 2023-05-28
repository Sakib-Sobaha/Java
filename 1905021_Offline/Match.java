import java.util.Random;

public class Match{
    private final int matchNo;
    private Club homeTeam;
    private Club awayTeam;
    private boolean isPlayed;
    private int homeTeamScore;
    private int awayTeamScore;
    // add your variables here, if required
    private int homeTeamPoints;
    private int awayTeamPoints;

    // you are not allowed to write any more constructors
    public Match(int matchNo, Club homeTeam, Club awayTeam){
        this.matchNo = matchNo;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        isPlayed = false;
    }

    public void play(){
        Random random = new Random();
        homeTeamScore = random.nextInt(10);
        awayTeamScore = random.nextInt(10);
        isPlayed = true;
        // you can add your code here if required
        // a team gets 2 points for winning and 0 point for losing
        // both teams get 1 point each in case of a draw
        if(homeTeamScore > awayTeamScore){
            homeTeamPoints+=2;
            homeTeam.setPoints(homeTeamPoints);
        }
        else if(homeTeamScore < awayTeamScore){
            awayTeamPoints+=2;
            awayTeam.setPoints(awayTeamPoints);
        }
        else{
            homeTeamPoints+=1;
            awayTeamPoints+=1;
            homeTeam.setPoints(homeTeamPoints);
            awayTeam.setPoints(awayTeamPoints);

        }
    }

    public void showResult(){
        if (!isPlayed){
            System.out.println("Match " + matchNo + " between " + homeTeam.getName() + " and " + awayTeam.getName() + " is yet to be played.");
            return;
        }
        // exactly one of the following three print statements should be executed
        // add condition to check if the match is drawn, the home team won or the away team won
        if(homeTeamScore == awayTeamScore) {
            System.out.println("Match drawn. " + homeTeam.getName() + " " + homeTeamScore + "-" + awayTeamScore + " " + awayTeam.getName());
        }
        else if(homeTeamScore > awayTeamScore) {
            System.out.println(homeTeam.getName() + " wins. " + homeTeam.getName() + " " + homeTeamScore + "-" + awayTeamScore + " " + awayTeam.getName());
        }
        else {
            System.out.println(awayTeam.getName() + " wins. " + homeTeam.getName() + " " + homeTeamScore + "-" + awayTeamScore + " " + awayTeam.getName());
        }
    }

    // add your methods here, if required
}
