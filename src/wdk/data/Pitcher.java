package wdk.data;

import java.text.DecimalFormat;

public class Pitcher extends Player{
    
    int earnedRuns;
    int wins;
    int saves;
    int hits;
    int basesOnballs;
    int strikeOuts;
    double ERA;
    double WHIP;
    
    public Pitcher(String firstName, String lastName, String team, int birthYear,
           String notes, String birthNation, double IP, int earnedRuns, int wins, int saves,
            int hits, int basesOnballs, int strikeOuts) {
        super(firstName, lastName, team, birthYear, notes, birthNation, IP);
        
        this.earnedRuns = earnedRuns;
        this.wins = wins;
        this.saves = saves;
        this.position = "P";
        this.hits = hits;
        this.basesOnballs = basesOnballs;
        this.strikeOuts = strikeOuts;
        if(IP == 0)
            this.ERA = 0;
        else{
            DecimalFormat df = new DecimalFormat("#.00"); 
            this.ERA = Double.parseDouble(df.format(earnedRuns*9/IP));
            this.WHIP = Double.parseDouble(df.format((basesOnballs + hits)/IP));
        }
        
    }
    
    public Pitcher(String firstName, String lastName, String team, String position){
        super(firstName, lastName, team, position);
    }
    
    
        
    public double getERA(){   
        return ERA;
    }
    public void setERA(double ERA){
        this.ERA = ERA;
    }
    
    public double getWHIP(){   
        return WHIP;
    }
    public void setWHIP(double WHIP){
        this.WHIP = WHIP;
    }
    
    public int getEarnedRuns(){   
        return earnedRuns;
    }
    public void setEarnedRuns(int earnedRuns){
        this.earnedRuns = earnedRuns;
    }
    
    public String getPosition(){   
        return position;
    }
    public void setPosition(String position){
        this.position = position;
    }
    
    public int getWins(){   
        return wins;
    }
    public void setWins(int wins){
        this.wins = wins;
    }
    
    public int getHits(){   
        return hits;
    }
    public void setHits(int hits){
        this.hits = hits;
    }
    
    public int getSaves(){   
        return saves;
    }
    public void setSaves(int saves){
        this.saves = saves;
    }
    
    public int getBasesOnballs(){   
        return basesOnballs;
    }
    public void setBasesOnballs(int basesOnballs){
        this.basesOnballs = basesOnballs;
    }
    
    public int getStrikeOuts(){   
        return strikeOuts;
    }
    public void setStrikeOuts(int strikeOuts){
        this.strikeOuts = strikeOuts;
    }
}