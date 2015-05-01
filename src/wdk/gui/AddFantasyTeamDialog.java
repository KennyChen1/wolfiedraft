package wdk.gui;

import java.io.File;
import wolfieballdraftkit.WDK_PropertyType;
import static wdk.gui.WDK_GUI.CLASS_HEADING_LABEL;
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
import properties_manager.PropertiesManager;
import wdk.data.FantasyTeam;
import wdk.data.Pitcher;
import wdk.data.Player;
import static wolfieballdraftkit.WDK_PropertyType.*;
import static wolfieballdraftkit.WDK_StartupConstants.*;

/**
 *
 * @author Kenny Chen
 */
public class AddFantasyTeamDialog extends Stage {
    // THIS IS THE OBJECT DATA BEHIND THIS UI
    //Lecture lectures;
    
    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
    Label fantasyNameLabel;
    TextField fantasyNameTextField;
    Button completeButton;
    Button cancelButton;
    String header;
    
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
    public AddFantasyTeamDialog(Stage primaryStage, WDK_GUI gui, String title) {       
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        this.header = title;
        // FIRST OUR CONTAINER
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        // PUT THE HEADING IN THE GRID, NOTE THAT THE TEXT WILL DEPEND
        // ON WHETHER WE'RE ADDING OR EDITING
        headingLabel = new Label(title);
        
        
        /*salaryTextBox = new TextField();
                salaryTextBox.textProperty().addListener((observable, oldValue, newValue) -> {
            //curPitcher.setNotes(newValue);
        });*/
                     
        // AND FINALLY, THE BUTTONS
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        fantasyNameLabel = new Label("Fantasy Team Name");
        fantasyNameTextField = new TextField();
        
        
        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            AddFantasyTeamDialog.this.selection = sourceButton.getText();
            AddFantasyTeamDialog.this.hide();
        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);
      
        gridPane.add(headingLabel, 0, 0);
        gridPane.add(fantasyNameLabel, 0, 1);
        gridPane.add(fantasyNameTextField, 1, 1);
        gridPane.add(completeButton, 0, 2);
        gridPane.add(cancelButton, 1, 2);

        completeButton.setOnAction(e -> {
            try{
            if(fantasyNameTextField.getText().equals("")){
                UnfilledDialog a = new UnfilledDialog(primaryStage);
                a.showDialog();
            } else if(title.equals("Add Fantasy Team")){
                if(gui.searchTeamName(fantasyNameTextField.getText()) == -1){// MAKE ERROR DIALOG FOR NON UNIQUE NAME
                    gui.fantasyTeamList.add(fantasyNameTextField.getText());
                    gui.draftTeams.add(new FantasyTeam(fantasyNameTextField.getText()));
                    
                }
                this.close();
            } else if(title.equals("Edit Fantasy Team")){                
                int x = gui.fantasyTeamList.indexOf(gui.fantasyTeamComboBox.getSelectionModel().getSelectedItem());
                gui.fantasyTeamList.set(x, fantasyNameTextField.getText());                
                gui.draftTeams.get(gui.searchTeamName(gui.fantasyTeamComboBox.getValue().toString())).setFantasyTeamName(fantasyNameTextField.getText());
                gui.fantasyTeamComboBox.setValue(fantasyNameTextField.getText());
                this.close();
            }} catch(Exception x){
                UnfilledDialog a = new UnfilledDialog(primaryStage);
                a.showDialog();
            }
            
        });
        
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
    
    public void showDialog() {
        // SET THE DIALOG TITLE
        setTitle(header);
               
        // AND OPEN IT UP
        this.showAndWait();
    }
}