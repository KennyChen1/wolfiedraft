package wdk.data;

import java.util.Comparator;


public class Player implements Comparator{

    // THESE COURSE DETAILS DESCRIBE WHAT'S REQUIRED BY
    // THE COURSE SITE PAGES

    String firstName;    
    String lastName;
    String team;
    int birthYear;
    String notes;
    String birthNation;
    String position;
    String teamPosition;
    String contract;
    String fantasyTeam;
    int salary;
    int earnedRuns;
    int RW;
    int ab;
    int hits;
    int HRSV;
    int RBIK;
    int walks;
    double SBERA;
    double BAWHIP;
    double IP;

     public Player(String firstName, String lastName, String team, int birthYear,
           String notes, String birthNation) {
        
        this.firstName = firstName;        
        this.lastName = lastName;
        this.team = team;
        this.birthYear = birthYear;
        this.notes = notes;
        this.birthNation = birthNation;
    }
    
    public Player(String firstName, String lastName, String team, int birthYear,
           String notes, String birthNation, double IP) {
        
        this.firstName = firstName;        
        this.lastName = lastName;
        this.team = team;
        this.birthYear = birthYear;
        this.notes = notes;
        this.birthNation = birthNation;
        this.IP = IP;
    }
    
    public Player(String firstName, String lastName, String team, String position,
            int birthYear, String notes, int RW, int HRSV, 
                int RBIK, double SBERA, double BAWHIP, int ab, int walks) {
        
        this.firstName = firstName;        
        this.lastName = lastName;
        this.team = team;
        this.position = position;
        this.birthYear = birthYear;
        this.notes = notes;
        this.RW = RW;
        this.HRSV = HRSV;
        this.RBIK = RBIK;
        this.SBERA = SBERA;
        this.BAWHIP = BAWHIP;
        this.ab = ab;
        this.hits = hits;   
        this.IP = IP;
        this.walks = walks;
    }
    
    public Player(String firstName, String lastName, String team, String position,
            int birthYear, String notes, int RW, int HRSV, 
                int RBIK, double SBERA, double BAWHIP, double IP, int walks, int earnedRuns, int hits) {
        
        this.firstName = firstName;        
        this.lastName = lastName;
        this.team = team;
        this.position = position;
        this.birthYear = birthYear;
        this.notes = notes;
        this.RW = RW;
        this.HRSV = HRSV;
        this.RBIK = RBIK;
        this.SBERA = SBERA;
        this.BAWHIP = BAWHIP;  
        this.IP = IP;
        this.walks = walks;
        this.earnedRuns = earnedRuns;
        this.hits = hits;
    }
    
    public Player(String firstName, String lastName, String team, String position,
            int birthYear, String notes, int RW, int HRSV, 
                int RBIK, double SBERA, double BAWHIP) {
        
        this.firstName = firstName;        
        this.lastName = lastName;
        this.team = team;
        this.position = position;
        this.birthYear = birthYear;
        this.notes = notes;
        this.RW = RW;
        this.HRSV = HRSV;
        this.RBIK = RBIK;
        this.SBERA = SBERA;
        this.BAWHIP = BAWHIP;  
    }
    
    public Player(String firstName, String lastName, String team, String position){
        this.firstName = firstName;        
        this.lastName = lastName;
        this.team = team;
        this.position = position;
    }
    
    
    
    // BELOW ARE ALL THE ACCESSOR METHODS FOR A COURSE
    // AND THE MUTATOR METHODS. NOTE THAT WE'LL NEED TO CALL
    // THESE AS USERS INPUT VALUES IN THE GUI
  
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public int getRW() {
        return RW;
    }
    public void setRunsWins(int RW) {
        this.RW = RW;
    }
    
    public int getHRSV() {
        return HRSV;
    }
    public void setHRSV(int HRSV) {
        this.HRSV = HRSV;
    }
    
    public double getBAWHIP() {
        return BAWHIP;
    }
    public void setBAWHIP(double BAWHIP) {
        this.BAWHIP = BAWHIP;
    }
        
    public double getSBERA() {
        return SBERA;
    }
    public void setSBERA(int SBERA) {
        this.SBERA = SBERA;
    }
    
    public int getRBIK() {
        return RBIK;
    }
    public void setRBIK(int RBIK) {
        this.RBIK = RBIK;
    }        
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getNotes() {
        return notes;
    }
    
    public String getPosition(){   
        return position;
    }
    public void setPosition(String position){
        this.position = position;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBirthNation() {
        return birthNation;
    }

    public void setBirthNation(String birthNation) {
        this.birthNation = birthNation;
    }
    
    public String getTeamPosition() {
        return teamPosition;
    }
    public void setTeamPosition(String teamPosition) {
        this.teamPosition = teamPosition;
    }
    
    public String getContract() {
        return contract;
    }
    public void setContract(String contract) {
        this.contract = contract;
    }
    
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    public String getFantasyTeam() {
        return fantasyTeam;
    }
    public void setFantasyTeam(String fantasyTeam) {
        this.fantasyTeam = fantasyTeam;
    }
    
    public int getAB() {
        return ab;
    }
    public void setAB(int ab) {
        this.ab = ab;
    }
    
    public int getHits() {
        return hits;
    }
    public void setFantasyTeam(int hits) {
        this.hits = hits;
    }
    
    public double getIP() {
        return IP;
    }
    public void setFirstName(double IP) {
        this.IP = IP;
    }
    
    public double getWalks() {
        return walks;
    }
    public void setFirstName(int walks) {
        this.walks = walks;
    }
    
    public int getEarnedRuns(){   
        return earnedRuns;
    }
    public void setEarnedRuns(int earnedRuns){
        this.earnedRuns = earnedRuns;
    }
    
    @Override
    public int compare(Object o1, Object o2) {
        Player playerF = (Player)o1;
        Player playerS = (Player)o2;
         if(playerF.getLastName().compareTo(playerS.getLastName()) == 0)
            return playerF.getFirstName().compareTo(playerS.getFirstName());
        return playerF.getLastName().compareTo(playerS.getLastName());
    }

   
}
