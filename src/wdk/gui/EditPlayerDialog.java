package wdk.gui;

import java.io.File;
import java.text.DecimalFormat;
import javafx.collections.ObservableList;
/*import static wdk.gui.WDK_GUI.CLASS_PROMPT_LABEL;
import static wdk.gui.WDK_GUI.PRIMARY_STYLE_SHEET;*/
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import wdk.data.Player;
import wdk.data.Hitter;
import wdk.data.Pitcher;
import wdk.gui.ErrorDialog;
import static wolfieballdraftkit.WDK_StartupConstants.*;

/**
 *
 * @author Kenny Chen
 */
public class EditPlayerDialog extends Stage {
    // THIS IS THE OBJECT DATA BEHIND THIS UI
    //Lecture lectures;
    
    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
    Label nameLabel;
    Label positionLabel;
    Label fantasyTeamLabel;
    Label positionComboLabel;
    Label contractComboLabel;
    ComboBox fantasyTeamComboBox;
    ComboBox positionComboBox;
    ComboBox contractComboBox;
    Label salaryComboLabel;
    TextField salaryTextBox;
    Button completeButton;
    Button cancelButton;
    
    // THIS IS FOR KEEPING TRACK OF WHICH BUTTON THE USER PRESSED
    String selection;
    
    // CONSTANTS FOR OUR UI
    
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    
    /**
     * Initializes this dialog so that it can be used for either adding
     * new schedule items or editing existing ones.
     * 
     * @param primaryStage The owner of this modal dialog.
     * @param player
     * @param gui
     * @param availPlayer
     */
    public EditPlayerDialog(Stage primaryStage, Player player, WDK_GUI gui, boolean availPlayer) {       
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        
        // FIRST OUR CONTAINER
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        // PUT THE HEADING IN THE GRID, NOTE THAT THE TEXT WILL DEPEND
        // ON WHETHER WE'RE ADDING OR EDITING
        headingLabel = new Label("Player Details ");
        ImageView playerImg = new ImageView();
        String playerImgPath = PATH_IMAGES + "players/"+
                player.getLastName() + player.getFirstName() +".jpg";
        ImageView playerFlag = new ImageView();
        File f = new File(System.getProperty("user.dir") + "/" + playerImgPath);
                
        if(f.exists()){
            playerImg.setImage(new Image("file:" + playerImgPath));
        }else{
            playerImg.setImage(new Image("file:" + PATH_IMAGES + "players/AAA_PhotoMissing.jpg"));
        }
        
        playerFlag.setImage(new Image("file:" + PATH_IMAGES + "flags/USA.png"));
        
        nameLabel = new Label(player.getFirstName() + " " + player.getLastName());
        positionLabel = new Label(player.getPosition());
        fantasyTeamLabel = new Label("Fantasy Team: ");
        positionComboLabel = new Label("Position: ");
        contractComboLabel = new Label("Contract: ");
        salaryComboLabel = new Label("Salary: ");
        
        fantasyTeamComboBox = new ComboBox();
        fantasyTeamComboBox.getItems().add("Free Agent");
        fantasyTeamComboBox.setValue("Free Agent");
        fantasyTeamComboBox.getItems().addAll(gui.fantasyTeamList);
        positionComboBox = new ComboBox();
        positionComboBox.setValue("");
        //positionComboBox.getItems().addAll(player.getPosition().split("_"));
        //positionComboBox.setValue(player.getPosition().split("_")[0]);
        
        contractComboBox = new ComboBox();
        contractComboBox.getItems().addAll("S2", "S1", "X");
        contractComboBox.setValue("S2");
        
        salaryTextBox = new TextField();
                salaryTextBox.textProperty().addListener((observable, oldValue, newValue) -> {
            //curPitcher.setNotes(newValue);
        });
                     
        // AND FINALLY, THE BUTTONS
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        
        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            EditPlayerDialog.this.selection = sourceButton.getText();
            EditPlayerDialog.this.hide();
        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);
      
        fantasyTeamComboBox.setOnAction(e ->{
            fillComboBox(player, gui);
        });
        completeButton.setOnAction(e -> {
            try{Player x = null;
                if(availPlayer){//from free agent to team  
                    if(this.fantasyTeamComboBox.getValue().toString().equals("Free Agent")){
                    } else{
                    if(gui.allButton.isSelected())
                        x = gui.allPlayerTable.getSelectionModel().getSelectedItem();
                    else if(gui.pitcherButton.isSelected()){
                        x = gui.pitcherToPlayer(gui.pitcherTable.getSelectionModel().getSelectedItem());
                    } else{
                        Hitter y = gui.hitterTable.getSelectionModel().getSelectedItem();                        
                        double sb;
                        if(y.getAB() == 0){
                            sb = -1;
                        } else{
                            DecimalFormat df = new DecimalFormat("#.000"); 
                            sb = Double.parseDouble(df.format(1.0*y.getHits()/y.getAB()));
                        }
                        x = new Player(y.getFirstName(), y.getLastName(), y.getTeam(), y.getPositions(), 
                           y.getBirthYear(), y.getNotes(), y.getRuns(), y.getHomeRuns(), 
                               y.getRunsBattedIn(), y.getStolenBases(), sb, y.getAB(), y.getHits());
                    }
                    
                    if((gui.draftTeams.get(gui.searchTeamName(this.fantasyTeamComboBox.getValue().toString())).getFunding()
                            - Integer.parseInt(salaryTextBox.getText())) < 0)
                        throw new IllegalArgumentException();
                    if(Integer.parseInt(salaryTextBox.getText()) < 0)
                        throw new NumberFormatException();

                    x.setContract(contractComboBox.getValue().toString());
                    x.setTeamPosition(positionComboBox.getValue().toString());
                    x.setSalary(Integer.parseInt(salaryTextBox.getText()));
                    x.setFantasyTeam(this.fantasyTeamComboBox.getValue().toString());
                    gui.draftTeams.get(gui.searchTeamName(this.fantasyTeamComboBox.getValue().toString())).getStartingLineup().add(x);
                    gui.doDeletePlayer();
                    
                    gui.draftTeams.get(gui.searchTeamName(this.fantasyTeamComboBox.getValue().toString())).setFunding(Integer.parseInt(salaryTextBox.getText()));
                }    
            } else{
                    x = gui.startingLineupTable.getSelectionModel().getSelectedItem();
                    if(this.fantasyTeamComboBox.getValue().toString().equals("Free Agent")){//BACK TO FREE AGENT
                        gui.playerList.add(x);
                        if(!x.getPosition().contains("P")){
                            Hitter zz = new Hitter(x.getFirstName(), x.getLastName(), x.getTeam(), x.getBirthYear(),
                               x.getNotes(), x.getBirthNation(), x.getPosition(), x.getAB(), x.getRW(), x.getHits(), 
                                x.getHRSV(), x.getRBIK(), (int)(x.getSBERA()));
                            gui.allHitterList.add(zz);
                        } else{
                            Pitcher xy = gui.playerToPitcher(x);
                            gui.pitcherList.add(xy);
                        }
                        
                        ObservableList<Player> a = gui.draftTeams.get(gui.searchTeamName(gui.fantasyTeamComboBox.getValue().toString())).getStartingLineup();
                        for(int i = 0; i < a.size(); i++){
                            if(a.get(i).getFirstName().equals(x.getFirstName()) && a.get(i).getLastName().equals(x.getLastName()))
                                a.remove(i);
                        }
                        
                    gui.draftTeams.get(gui.searchTeamName(x.getFantasyTeam()))
                            .setFunding(Integer.parseInt("-" + x.getSalary()));

                    } else{// moving between teams
                        if(x.getFantasyTeam().equals(this.fantasyTeamComboBox.getValue())){
                            if(gui.draftTeams.get(gui.searchTeamName(x.getFantasyTeam())).getFunding() + x.getSalary()
                                    - Integer.parseInt(this.salaryTextBox.getText()) < 0){
                                throw new IllegalArgumentException();
                            } else {
                                gui.draftTeams.get(gui.searchTeamName(x.getFantasyTeam()))
                                    .setFunding(Integer.parseInt("-" + x.getSalary()));
                            }
                        } else if((gui.draftTeams.get(gui.searchTeamName(this.fantasyTeamComboBox.getValue().toString())).getFunding()
                            - Integer.parseInt(salaryTextBox.getText())) < 0){
                            throw new IllegalArgumentException();
                        } else{
                            gui.draftTeams.get(gui.searchTeamName(x.getFantasyTeam()))
                            .setFunding(Integer.parseInt("-"+x.getSalary()));
                            gui.draftTeams.get(gui.searchTeamName(this.fantasyTeamComboBox.getValue().toString()))
                                    .setFunding(Integer.parseInt(salaryTextBox.getText()));
                        }
                        
                        
                        x.setContract(contractComboBox.getValue().toString());
                        x.setTeamPosition(positionComboBox.getValue().toString());
                        x.setSalary(Integer.parseInt(salaryTextBox.getText()));
                        x.setFantasyTeam(fantasyTeamComboBox.getValue().toString());

                        ObservableList<Player> old = gui.draftTeams.get(gui.searchTeamName(gui.fantasyTeamComboBox.getValue().toString())).getStartingLineup();
                        
                         
                        for(int i = 0; i < old.size(); i++){
                            if(old.get(i).getFirstName().equals(x.getFirstName()) && old.get(i).getLastName().equals(x.getLastName()))
                                old.remove(i);
                        }
                        ObservableList<Player> b = gui.draftTeams.get(gui.searchTeamName(this.fantasyTeamComboBox.getValue().toString())).getStartingLineup();
                        b.add(x);
                        
                    }
            }
                gui.sortFantasyTables(this.fantasyTeamComboBox.getValue().toString());
                this.close();
            } catch(NumberFormatException x){
                ErrorDialog xy = new ErrorDialog(primaryStage, "Please input a valid number!");
                xy.showAndWait();
            } catch(IllegalArgumentException x){
                ErrorDialog xy = new ErrorDialog(primaryStage, "Not enough funding");
                xy.showAndWait();                
            }
        });
        
        
        
        // NOW LET'S ARRANGE THEM ALL AT ONCE
        gridPane.add(headingLabel, 0, 0, 1, 1);
        gridPane.add(playerImg, 0, 1, 1, 3);
        gridPane.add(playerFlag, 1, 1, 1, 1);
        gridPane.add(nameLabel, 1, 2, 1, 1);
        gridPane.add(positionLabel, 1, 3, 1, 1);
        gridPane.add(fantasyTeamLabel, 0, 4, 1, 1);
        gridPane.add(positionComboLabel, 0, 5, 1, 1);
        gridPane.add(contractComboLabel, 0, 6, 1, 1);        
        gridPane.add(salaryComboLabel, 0, 7, 1, 1);
        gridPane.add(fantasyTeamComboBox, 1, 4, 2, 1);
        gridPane.add(positionComboBox, 1, 5, 2, 1);
        gridPane.add(contractComboBox, 1, 6, 2, 1);
        gridPane.add(salaryTextBox, 1, 7, 1, 1);
        gridPane.add(completeButton, 0, 8, 1, 1);
        gridPane.add(cancelButton, 1, 8, 1, 1);

        // AND PUT THE GRID PANE IN THE WINDOW
        dialogScene = new Scene(gridPane);
        this.setScene(dialogScene);
    }
    
    private void fillComboBox(Player player, WDK_GUI gui){
        if(!fantasyTeamComboBox.getValue().toString().equals("Free Agent")){
        ObservableList<Player> x = gui.draftTeams.get(gui.searchTeamName(this.fantasyTeamComboBox.getValue().toString())).getStartingLineup();
        if(x.size() < 23){
            String[] pos = player.getPosition().split("_");
           
            for(int j = 0; j < pos.length; j++){
            int count = 0;
                for(int i = 0; i < x.size(); i++){
                    if(x.get(i).getTeamPosition().equals(pos[j])){
                        count++;
                    }
                }
                
                if(count < 1 && pos[j].equals("U"))
                    positionComboBox.getItems().add("U");
                if(count < 2 && pos[j].equals("C"))
                    positionComboBox.getItems().add("C");
                if(count < 1 && pos[j].equals("1B"))
                    positionComboBox.getItems().add("1B");
                if(count < 1 && pos[j].equals("CI"))
                    positionComboBox.getItems().add("CI");
                if(count < 1 && pos[j].equals("3B"))
                    positionComboBox.getItems().add("3B");
                if(count < 1 && pos[j].equals("2B"))
                    positionComboBox.getItems().add("2B");
                if(count < 1 && pos[j].equals("MI"))
                    positionComboBox.getItems().add("MI");
                if(count < 1 && pos[j].equals("SS"))
                    positionComboBox.getItems().add("SS");
                if(count < 5 && pos[j].equals("OF"))
                    positionComboBox.getItems().add("OF");
                if(count < 9 && pos[j].equals("P"))
                    positionComboBox.getItems().add("P");
            }
        }}
    }
    
    public String getSelection() {
        return selection;
    }
    
    public boolean wasCompleteSelected() {
        return selection.equals(COMPLETE);
    }
    
    public void showEditPlayerDialog() {
        // SET THE DIALOG TITLE
        setTitle("Edit Player");
               
        // AND OPEN IT UP
        this.showAndWait();
    }
}