public class League {
    private Match[] matches;
    private  Club[] clubs;
    private Club[] clubs1;
    private int matchCount;
    private int clubCount;
    private String name;
    // add your variables here, if required
    public int removedClubidx;
    private boolean leaguefinished;

    public League() {
        // assume a league can have at most 5 clubs, add code for initialization accordingly
        clubCount = 0;
        matchCount = 0;
        this.clubs = new Club[10];
        this.matches = new Match[20];
        this.clubs1 = new Club[10];
        this.leaguefinished = false;
        //removed = false;
    }
    public void setName(String name){
        this.name = name;
    }
    public void addClub(Club c){
        clubs[clubCount++] = new Club(c);
    }
    public void printLeagueInfo(){
        System.out.println("League : " + name);
        printClubs();
    }


    public void printClubs(){
        System.out.println("Clubs:");
        // print the name of the clubs of this league, each one on a line
        for(int i=0;i<clubCount;++i){
            System.out.println(clubs[i].getName());

        }
    }

    public void scheduleMatches(){
        matchCount = (clubCount*(clubCount-1));
        matches = new Match[matchCount];
        int matchNo = 0;
        for (int i=0; i<clubCount; i++){
            for (int j=0; j<clubCount; j++){
                // check the constructor of the Match class and add your code here
                // note that there will be two matches between club A and club B
                // in the first match, club A will play as the home team and in the second match, as the away team
                if(j == i) {
                    continue;
                }
                matches[matchNo] = new Match(matchNo,clubs[i],clubs[j]);
                //matches[matchNo].play();
                matchNo++;
            }
        }
    }

    public void simulateMatches(){
        scheduleMatches();
        for (int i=0; i<matchCount; i++){
            matches[i].play();
        }
        this.leaguefinished = true;
    }
    public void printMatches(){
        System.out.println("Matches:");
        for(int i=0; i<matchCount;++i){
            matches[i].showResult();
        }
    }

    public void showStandings(){
        // sort the clubs in descending order of points
        // note that, the sequence in which clubs were added to the league, should be unchanged
        // check the given sample output for clarification
        // (carefully observe the output of showStandings() followed by printLeagueInfo() method calls
        // you can use additional arrays if needed
        //this.leaguefinished = true;
        System.out.println("Sl. - Club - Points");
        //Club[] clubs1;
        //clubs1 = clubs;
        // print the clubs in descending order of points
        for(int i=0; i<clubCount; ++i){
            clubs1[i] = clubs[i];
        }
        for(int i=0; i<clubCount-1;++i){
            for(int j=i+1; j<clubCount;++j){
                if(clubs1[i].getPoints() < clubs1[j].getPoints()){
                    Club temp = new Club();
                    temp = clubs1[i];
                    clubs1[i] = clubs1[j];
                    clubs1[j] = temp;
                }
            }
        }
        for(int i=0; i<clubCount;++i){
            System.out.println(i+1+"."+" "+clubs1[i].getName()+" "+clubs1[i].getPoints());
        }
        updatePoints();

    }

    // add your methods here, if required
    public void removeClub(Club c){
        //int idx = 0;
        for(int i=0;i<clubCount;++i){
            if (c == clubs[i]){
                removedClubidx = i;
                break;
            }
        }
        for(int i=removedClubidx; i<clubCount - 1; ++i) {
            this.clubs[i] = this.clubs[i + 1];
        }
        clubCount-=1;

    }

    public void updatePoints(){
        int k = 0;
        if(this.leaguefinished) {
            for (int i = 0; i < clubCount; ++i) {
                this.clubs[i].updatePoints(k);

            }
        }
    }

}

