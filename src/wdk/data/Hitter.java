package wdk.data;

import java.text.DecimalFormat;

public class Hitter extends Player{
    
    int AB;
    int runs;
    int hits;
    int homeRuns;
    int runsBattedIn;
    int stolenBases;
    double BA;
    
    
    public Hitter(String firstName, String lastName, String team, int birthYear,
           String notes, String birthNation, String position, int AB, int runs, int hits, 
            int homeRuns, int runsBattedIn, int stolenBases) {
        super(firstName, lastName, team, birthYear, notes, birthNation);
        super.position = position;        
        this.AB = AB;
        this.runs = runs;
        this.hits = hits;
        this.homeRuns = homeRuns;
        this.runsBattedIn = runsBattedIn;
        this.stolenBases = stolenBases;
                
        if(AB == 0)
            this.BA = 0;
        else{
            DecimalFormat df = new DecimalFormat("#.000"); 
            this.BA = Double.parseDouble(df.format(1.0*hits/AB));
        }
    }
    
    public Hitter(String firstName, String lastName, String team, String position){
        super(firstName, lastName, team, position);
    }
    
    public String getPositions(){   
        return position;
    }
    public void setPositions(String position){
        this.position = position;
    }
    
    public int getAB(){   
        return AB;
    }
    public void set(int AB){
        this.AB = AB;
    }
    
    public double getBA(){   
        return BA;
    }
    public void set(double BA){
        this.BA = BA;
    }
        
    public int getRuns(){   
        return runs;
    }
    public void setRuns(int runs){
        this.runs = runs;
    }
    
    public int getHits(){   
        return hits;
    }
    public void setHits(int hits){
        this.hits = hits;
    }
    
    public int getHomeRuns(){   
        return homeRuns;
    }
    public void setHomeRuns(int homeRuns){
        this.homeRuns = homeRuns;
    }
    
    public int getRunsBattedIn(){   
        return runsBattedIn;
    }
    public void setRunsBattedIn(int runsBattedIn){
        this.runsBattedIn = runsBattedIn;
    }
    
    public int getStolenBases(){   
        return stolenBases;
    }
    public void setStolenBases(int stolenBases){
        this.stolenBases = stolenBases;
    }
}