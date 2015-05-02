package wdk.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import wolfieballdraftkit.WDK_PropertyType;
import static wdk.gui.WDK_GUI.CLASS_HEADING_LABEL;
/*import static wdk.gui.WDK_GUI.CLASS_PROMPT_LABEL;
import static wdk.gui.WDK_GUI.PRIMARY_STYLE_SHEET;*/
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.json.JsonArray;
import javax.json.JsonValue;
import properties_manager.PropertiesManager;
import wdk.controller.FileController;
import wdk.data.Hitter;
import wdk.data.Pitcher;
import wdk.gui.WDK_GUI;
import wdk.gui.UnfilledDialog;
import wdk.data.Player;
import static wolfieballdraftkit.WDK_StartupConstants.*;

/**
 *
 * @author Kenny Chen
 */
public class AddPlayerDialog extends Stage {
    // THIS IS THE OBJECT DATA BEHIND THIS UI
    //Lecture lectures;
    
    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridPane;
    Scene dialogScene;
    Button completeButton;
    Button cancelButton;
    Label headingLabel;
    Label firstNameLabel;
    TextField firstNameText;
    Label lastNameLabel;
    TextField lastNameText;
    Label proTeamLabel;
    ComboBox proTeamComboBox;
    String[] posList = {"C", "1B", "3B", "2B", "SS", "OF", "P"};
    CheckBox cBox;  CheckBox oneBox;  CheckBox threeBox;  CheckBox twoBox;  
    CheckBox ssBox;  CheckBox ofBox;  CheckBox pBox;
    // THIS IS FOR KEEPING TRACK OF WHICH BUTTON THE USER PRESSED
    String selection;
    
    // CONSTANTS FOR OUR UI
    
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    public static final String TITLE = "Add Player";
    
    /**
     * Initializes this dialog so that it can be used for either adding
     * new schedule items or editing existing ones.
     * 
     * @param primaryStage The owner of this modal dialog.
     * @throws java.io.IOException
     */
    public AddPlayerDialog(Stage primaryStage, WDK_GUI gui) throws IOException {       
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        
        // FIRST OUR CONTAINER
        VBox main = new VBox();
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 0, 20, 0));
        main.setPadding(new Insets(10, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        main.getChildren().add(gridPane);
        
        
        headingLabel = new Label("Player Details");
                
        firstNameLabel = new Label("First Name");
        firstNameText = new TextField();
        lastNameLabel = new Label("Last Name");
        lastNameText = new TextField();
        proTeamLabel = new Label("Pro Team");
        proTeamComboBox = new ComboBox();
        
        FileController a = new FileController();// POSSIBLY ADD SAVING        
        for (JsonValue s : a.loadProTeams().getJsonArray("TEAMS")) {
            proTeamComboBox.getItems().add(s.toString());
        }
        
        HBox boxBox = new HBox();
        
        cBox = initCheckBox(posList[0], boxBox);
        oneBox = initCheckBox(posList[1], boxBox);
        threeBox = initCheckBox(posList[2], boxBox);
        twoBox = initCheckBox(posList[3], boxBox);  
        ssBox = initCheckBox(posList[4], boxBox);  
        ofBox = initCheckBox(posList[5], boxBox);
        pBox = initCheckBox(posList[6], boxBox);
        
        main.getChildren().add(boxBox);
                     
        // AND FINALLY, THE BUTTONS
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        HBox endButtons = new HBox();
        endButtons.getChildren().add(completeButton);
        endButtons.getChildren().add(cancelButton);
        main.getChildren().add(endButtons);
        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            AddPlayerDialog.this.selection = sourceButton.getText();
            AddPlayerDialog.this.hide();
        };
        completeButton.setOnAction(e -> {
            CheckBox[] checkBoxList = {cBox, oneBox, threeBox, twoBox, ssBox, ofBox, pBox};
            boolean completed = false;
            String posStr = "";
            for(int i = 0; i < checkBoxList.length; i++)
                    if(checkBoxList[i].isSelected()){
                        posStr = posStr + posList[i] + "_";
                        completed = true;
                    }
            if(posStr.length() > 0)
                posStr = posStr.substring(0, posStr.length()-1);
            if((!completed || firstNameText.getText().equals("") || lastNameText.getText().equals("") || 
                    proTeamComboBox.getSelectionModel().getSelectedItem().toString().equals(""))){
                UnfilledDialog aa = new UnfilledDialog(primaryStage);
                aa.showAndWait();
            } else{
            if(pBox.isSelected()){
                Pitcher x = new Pitcher(firstNameText.getText(), lastNameText.getText(), 
                        proTeamComboBox.getSelectionModel().getSelectedItem().toString(), posStr);
                gui.pitcherList.add(x);
            }
            
            Hitter x = new Hitter(firstNameText.getText(), lastNameText.getText(), 
                        proTeamComboBox.getSelectionModel().getSelectedItem().toString(), posStr);
            gui.allHitterList.add(x);
            
            Player y = new Player(firstNameText.getText(), lastNameText.getText(), 
                        proTeamComboBox.getSelectionModel().getSelectedItem().toString(), posStr);
            gui.playerList.add(y);
            this.close();
            
            }});
                
        cancelButton.setOnAction(completeCancelHandler);
      
        // NOW LET'S ARRANGE THEM ALL AT ONCE
        gridPane.add(headingLabel, 0, 0, 1, 1);
        gridPane.add(firstNameLabel, 0, 1, 1, 1);
        gridPane.add(firstNameText, 1, 1, 1, 1);
        gridPane.add(lastNameLabel, 0, 2, 1, 1);
        gridPane.add(lastNameText, 1, 2, 1, 1);
        gridPane.add(proTeamLabel, 0, 3, 1, 1);
        gridPane.add(proTeamComboBox, 1, 3, 1, 1);
        
        
        // AND PUT THE GRID PANE IN THE WINDOW
        dialogScene = new Scene(main);
        this.setScene(dialogScene);
    }
    
    
    public String getSelection() {
        return selection;
    }
    
    public boolean wasCompleteSelected() {
        return selection.equals(COMPLETE);
    }
    
    public void showAddPlayerDialog() {
        // SET THE DIALOG TITLE
        setTitle(TITLE);
               
        // AND OPEN IT UP
        this.showAndWait();
    }
    
    private CheckBox initCheckBox(String label, Pane container){
        CheckBox cb = new CheckBox(label);
        cb.setPadding(new Insets(0, 5, 10, 0));
        container.getChildren().add(cb);
        return cb;
    }
}