package wdk.data;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FantasyTeam{
    
    String fantasyTeamName;
    ObservableList<Player> startingLineup = FXCollections.observableArrayList();;
    ObservableList<Player> taxiRoster = FXCollections.observableArrayList();
    int funding = 260;
    int playersRequired = 23;
    //aggregate data
    int avgFundingPerPlayer;
    int runs;
    int homeRuns;
    int RBI;
    int stolenBases;
    double battingAverage;
    int wins;
    int saves;
    int strikeouts;
    double ERA;
    double WHIP;
    int totalPoints;
    
    public FantasyTeam(String teamName) {
        this.fantasyTeamName = teamName;
    }
    
    public String getFantasyTeamName(){
        return fantasyTeamName;
    }
    public void setFantasyTeamName(String newTeamName){
        this.fantasyTeamName = newTeamName;
    }
    
    public int getFunding(){
        return funding;
    }
    public void setFunding(int moolahSpent){
        this.funding = this.funding - moolahSpent;
    }
    
    public int getRuns(){
        int x = 0;
        for (Player startingLineup1 : startingLineup) {
            if (!startingLineup1.getPosition().equals("P"))
                x += startingLineup1.getRW();  
        }
        for (Player startingLineup1 : taxiRoster) {
            if (!startingLineup1.getPosition().equals("P"))
                x += startingLineup1.getRW();  
        }
        return x;
    }
    
    public int getHomeRuns(){
        int x = 0;
        for (Player startingLineup1 : startingLineup) {
            if (!startingLineup1.getPosition().equals("P"))
                x += startingLineup1.getHRSV();  
        }
        for (Player startingLineup1 : taxiRoster) {
            if (!startingLineup1.getPosition().equals("P"))
                x += startingLineup1.getHRSV();  
        }
        return x;
    }
    
    public int getRBI(){
        int x = 0;
        for (Player startingLineup1 : startingLineup) {
            if (!startingLineup1.getPosition().equals("P"))
                x += startingLineup1.getRBIK();  
        }
        for (Player startingLineup1 : taxiRoster) {
            if (!startingLineup1.getPosition().equals("P"))
                x += startingLineup1.getRBIK();  
        }
        return x;
    }
    
    public int getStolenBases(){
        int x = 0;
        for (Player startingLineup1 : startingLineup) {
            if (!startingLineup1.getPosition().equals("P"))
                x += startingLineup1.getSBERA();  
        }
        for (Player startingLineup1 : taxiRoster) {
            if (!startingLineup1.getPosition().equals("P"))
                x += startingLineup1.getSBERA();  
        }
        return x;
    }
    
    public double getBattingAverage(){
        if(startingLineup.size() + taxiRoster.size() == 0)
            return -1;
        double x = 0.0;
        int z = 0;
        for (Player startingLineup1 : startingLineup) {
            if (!startingLineup1.getPosition().equals("P") && startingLineup1.getBAWHIP() != -1)
                x += startingLineup1.getBAWHIP();       z++;
        }
        for (Player startingLineup1 : taxiRoster) {
            if (!startingLineup1.getPosition().equals("P") && startingLineup1.getBAWHIP() != -1)
                x += startingLineup1.getBAWHIP();       z++;
        }
        DecimalFormat df = new DecimalFormat("#.00"); 
        return Double.parseDouble(df.format(x/z));
    }
    
    public int getWins(){
        if(startingLineup.size() + taxiRoster.size() == 0)
            return -1;
        int x = 0;
        for (Player startingLineup1 : startingLineup) {
            if (startingLineup1.getPosition().equals("P"))
                x += startingLineup1.getRW();  
        }
        for (Player startingLineup1 : taxiRoster) {
            if (startingLineup1.getPosition().equals("P"))
                x += startingLineup1.getRW();  
        }
        return x;
    }
    
    public int getSaves(){
        if(startingLineup.size() + taxiRoster.size() == 0)
            return -1;
        int x = 0;
        for (Player startingLineup1 : startingLineup) {
            if (startingLineup1.getPosition().equals("P"))
                x += startingLineup1.getHRSV();  
        }
        for (Player startingLineup1 : taxiRoster) {
            if (!startingLineup1.getPosition().equals("P"))
                x += startingLineup1.getHRSV();  
        }
        return x;
    }
    
    public int getStrikeouts(){
        if(startingLineup.size() + taxiRoster.size() == 0)
            return -1;
        int x = 0;
        for (Player startingLineup1 : startingLineup) {
            if (startingLineup1.getPosition().equals("P"))
                x += startingLineup1.getRBIK();  
        }
        for (Player startingLineup1 : taxiRoster) {
            if (startingLineup1.getPosition().equals("P"))
                x += startingLineup1.getRBIK();  
        }
        return x;
    }
    
    public double getERA(){
        if(startingLineup.size() + taxiRoster.size() == 0)
            return -1;
        double x = 0.0;
        int z = 0;
        for (Player startingLineup1 : startingLineup) {
            if (startingLineup1.getPosition().equals("P") && startingLineup1.getSBERA() != -1)
                x += startingLineup1.getSBERA();  z++;
        }
        for (Player startingLineup1 : taxiRoster) {
            if (startingLineup1.getPosition().equals("P") && startingLineup1.getSBERA() != -1)
                x += startingLineup1.getSBERA();  z++;
        }
        DecimalFormat df = new DecimalFormat("#.00"); 
        return Double.parseDouble(df.format(x/z));
    }
    
    public double getWHIP(){
        if(startingLineup.size() + taxiRoster.size() == 0)
            return -1;
        double x = 0.0;
        int z = 0;
        for (Player startingLineup1 : startingLineup) {
            if (startingLineup1.getPosition().equals("P") && startingLineup1.getBAWHIP() != -1)
                x += startingLineup1.getBAWHIP();  z++;
        }
        for (Player startingLineup1 : taxiRoster) {
            if (startingLineup1.getPosition().equals("P") && startingLineup1.getBAWHIP() != -1)
                x += startingLineup1.getBAWHIP();  z++;
        }
        DecimalFormat df = new DecimalFormat("#.000"); 
        return Double.parseDouble(df.format(x/z));
    }
    
    public int getTotalPoints(){
        return this.totalPoints;
    }
    public void setTotalPts(int newPts){
        this.totalPoints = newPts;
    }
    
    public int getPlayersRequired(){
        return this.playersRequired - startingLineup.size() - taxiRoster.size();
    }
    
    public int getAvgFundingPerPlayer(){
        return (int)Math.round(1.0*funding/playersRequired);
    }
    
    public ObservableList<Player> getStartingLineup(){
        return startingLineup;
    }
    public void setStartingLineup(ObservableList<Player> newLineup){
        startingLineup = FXCollections.observableArrayList();
        startingLineup.addAll(newLineup);
    }
    
    public ObservableList<Player> getTaxiRoster(){
        return startingLineup;
    }
    public void setTaxiRoster(ObservableList<Player> newLineup){
        taxiRoster = FXCollections.observableArrayList();
        taxiRoster.addAll(newLineup);
    }
    
    public static Comparator<FantasyTeam> getRunsComparator() {
        return (FantasyTeam o1, FantasyTeam o2) -> o2.getRuns()-o1.getRuns();
    }
    public static Comparator<FantasyTeam> getHomeRunsComparator() {
        return (FantasyTeam o1, FantasyTeam o2) -> o2.getHomeRuns()-o1.getHomeRuns();
    }
    public static Comparator<FantasyTeam> getRBIComparator() {
        return (FantasyTeam o1, FantasyTeam o2) -> o2.getRBI()-o1.getRBI();
    }
    public static Comparator<FantasyTeam> getSBComparator() {
        return (FantasyTeam o1, FantasyTeam o2) -> o2.getStolenBases()-o1.getStolenBases();
    }
    public static Comparator<FantasyTeam> getBAComparator() {
        return new Comparator<FantasyTeam>() {
            @Override
            public int compare(FantasyTeam o1, FantasyTeam o2) {
                if(o1.getBattingAverage() > o2.getBattingAverage())
                    return 1;
                if(o1.getBattingAverage() < o2.getBattingAverage())
                    return -1;
                return 0;
            }
        };
    }
    public static Comparator<FantasyTeam> getWinsComparator() {
        return (FantasyTeam o1, FantasyTeam o2) -> o2.getWins()-o1.getStolenBases();
    }
    public static Comparator<FantasyTeam> getSavesComparator() {
        return (FantasyTeam o1, FantasyTeam o2) -> o2.getSaves()-o1.getSaves();
    }
    public static Comparator<FantasyTeam> getStrikeoutsComparator() {
        return (FantasyTeam o1, FantasyTeam o2) -> o2.getStrikeouts()-o1.getStrikeouts();
    }
    public static Comparator<FantasyTeam> getERAComparator() {
        return new Comparator<FantasyTeam>() {
            @Override
            public int compare(FantasyTeam o1, FantasyTeam o2) {
                if(o1.getERA() > o2.getERA())
                    return 1;
                if(o1.getERA() < o2.getERA())
                    return -1;
                return 0;
            }
        };
    }
    public static Comparator<FantasyTeam> getWHIPComparator() {
        return new Comparator<FantasyTeam>() {
            @Override
            public int compare(FantasyTeam o1, FantasyTeam o2) {
                if(o1.getWHIP() > o2.getWHIP())
                    return 1;
                if(o1.getWHIP() < o2.getWHIP())
                    return -1;
                return 0;
            }
        };
    }
}