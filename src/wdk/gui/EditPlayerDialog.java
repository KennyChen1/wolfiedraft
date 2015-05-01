package wdk.gui;

import java.io.File;
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
     */
    public EditPlayerDialog(Stage primaryStage, Player player, WDK_GUI gui) {       
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
        fantasyTeamComboBox.getItems().add("");
        fantasyTeamComboBox.getItems().addAll(gui.fantasyTeamList);
        positionComboBox = new ComboBox();
        positionComboBox.getItems().addAll(player.getPosition().split("_"));
        positionComboBox.setValue(player.getPosition().split("_")[0]);
        
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
      
        completeButton.setOnAction(e -> {
            try{
                Player x = gui.allPlayerTable.getSelectionModel().getSelectedItem();
                x.setContract(contractComboBox.getValue().toString());
                x.setTeamPosition(positionComboBox.getValue().toString());
                x.setSalary(Integer.parseInt(salaryTextBox.getText()));
                gui.draftTeams.get(gui.searchTeamName(fantasyTeamComboBox.getValue().toString())).getStartingLineup().add(x);
                gui.doDeletePlayer();
                this.close();
            } catch(Exception x){
                System.out.println(x.getMessage());
            }
            
            /*
            System.out.println(x.getContract());
            gui.playerList.get(gui.searchByName(x.getFirstName(), gui.playerList, x.getLastName()));
            System.out.println(gui.playerList.get(gui.searchByName(x.getFirstName(), gui.playerList, x.getLastName())).getContract());*/
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