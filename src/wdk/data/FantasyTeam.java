package wdk.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FantasyTeam{
    
    String fantasyTeamName;
    ObservableList<Player> startingLineup = FXCollections.observableArrayList();
    ObservableList<Player> taxiRoster = FXCollections.observableArrayList();
    
    public FantasyTeam(String teamName) {
        this.fantasyTeamName = teamName;
    }
    
    public String getFantasyTeamName(){
        return fantasyTeamName;
    }
    public void setFantasyTeamName(String newTeamName){
        this.fantasyTeamName = newTeamName;
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
}