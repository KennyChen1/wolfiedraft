/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import static wolfieballdraftkit.WDK_StartupConstants.*;
import wolfieballdraftkit.WDK_PropertyType;
import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Kenny
 */
public class WDK_GUI {
    
        static final String CLASS_HEADING_LABEL = "heading_label";

    
     // WE'LL ORGANIZE OUR WORKSPACE COMPONENTS USING A BORDER PANE
    BorderPane workspacePane;
    boolean workspaceActivated;
    
    // THIS IS THE APPLICATION WINDOW
    Stage primaryStage;
    
    // THIS IS THE STAGE'S SCENE GRAPH
    Scene primaryScene;
    
    // WE'LL PUT THE WORKSPACE INSIDE A SCROLL PANE
    ScrollPane workspaceScrollPane;
    
    // THIS PANE ORGANIZES THE BIG PICTURE CONTAINERS FOR THE
    // APPLICATION GUI
    BorderPane wdkPane;
    
    // THIS IS THE TOP TOOLBAR AND ITS CONTROLS
    FlowPane fileToolbarPane;
    Button newDraftButton;
    Button loadDraftButton;
    Button saveDraftButton;
    Button exportSiteButton;
    Button exitButton;

    // THIS IS THE BOTTOM TOOLBAR
    FlowPane bottomToolbarPane;
    Button homeButton    ;
    Button fantasyTeamsButton;
    Button fantasyStandingButton;
    Button draftButton;
    Button MLBTeamButton;

    // WORKSPACES
    SplitPane topWorkspaceSplitPane;
    VBox topWorkspacePane;
    Label courseHeadingLabel;
    
    SplitPane availPlayersSplitPane;
    VBox availPlayersWorkspacePane;
    Label availPlayersHeadingLabel;
    Label searchLabel;
    Button addButton;
    Button removeButton;
    TextField searchBar;
    RadioButton button1;    RadioButton button2;    RadioButton button3;
    RadioButton button4;    RadioButton button5;    RadioButton button6;
    RadioButton button7;    RadioButton button8;    RadioButton button9;
    RadioButton button10;    RadioButton button11;
    
    SplitPane fantasyStandingSplitPane;
    VBox fantasyStandingWorkspacePane;        
    Label fantasyStandingHeadingLabel;
    
    SplitPane draftSummarySplitPane;
    VBox draftSummaryWorkspacePane;        
    Label draftSummaryHeadingLabel;
    
    SplitPane mlbTeamsSplitPane;
    VBox mlbTeamsWorkspacePane;        
    Label mlbTeamsHeadingLabel;
    
    public WDK_GUI(Stage initPrimaryStage) {
        primaryStage = initPrimaryStage;
    }
    
    /**
     * Accessor method for the window (i.e. stage).
     *
     * @return The window (i.e. Stage) used by this UI.
     */
    public Stage getWindow() {
        return primaryStage;
    }
    
    public void activateWorkspace() {
        if (!workspaceActivated) {
            // PUT THE WORKSPACE IN THE GUI
            wdkPane.setCenter(workspaceScrollPane);
            workspaceActivated = true;
        }
    }
    
     public void initGUI(String windowTitle) throws IOException {

        // AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
          initFileToolbar();initBottomToolbar();
        initWindow(windowTitle);
        initEventHandlers();
                      initAvailablePlayersWorkspace();

    }
    
     private void initWindow(String windowTitle) {
        // SET THE WINDOW TITLE
        primaryStage.setTitle(windowTitle);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        // ADD THE TOOLBAR ONLY, NOTE THAT THE WORKSPACE
        // HAS BEEN CONSTRUCTED, BUT WON'T BE ADDED UNTIL
        // THE USER STARTS EDITING A DRAFT
        wdkPane = new BorderPane();
        wdkPane.setTop(fileToolbarPane);
                wdkPane.setBottom(bottomToolbarPane);

        primaryScene = new Scene(wdkPane);

        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
        // WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
        //primaryScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
     
     private void initFileToolbar() {
        fileToolbarPane = new FlowPane();

        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
        // START AS ENABLED (false), WHILE OTHERS DISABLED (true)
        newDraftButton = initChildButton(fileToolbarPane, WDK_PropertyType.NEW_DRAFT_ICON, WDK_PropertyType.NEW_DRAFT_TOOLTIP, false);
        loadDraftButton = initChildButton(fileToolbarPane, WDK_PropertyType.LOAD_DRAFT_ICON, WDK_PropertyType.LOAD_DRAFT_TOOLTIP, false);
        saveDraftButton = initChildButton(fileToolbarPane, WDK_PropertyType.SAVE_DRAFT_ICON, WDK_PropertyType.SAVE_DRAFT_TOOLTIP, true);
        exportSiteButton = initChildButton(fileToolbarPane, WDK_PropertyType.EXPORT_PAGE_ICON, WDK_PropertyType.EXPORT_PAGE_TOOLTIP, true);
        exitButton = initChildButton(fileToolbarPane, WDK_PropertyType.EXIT_ICON, WDK_PropertyType.EXIT_TOOLTIP, false);
    }
     
     private void initBottomToolbar() {
        bottomToolbarPane = new FlowPane();
                
        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
        // START AS ENABLED (false), WHILE OTHERS DISABLED (true)
        homeButton = initChildButton(bottomToolbarPane, WDK_PropertyType.PLAYER_HOME_ICON, WDK_PropertyType.PLAYERS_TOOLTIP, false);
        fantasyTeamsButton = initChildButton(bottomToolbarPane, WDK_PropertyType.FANTASY_TEAMS_ICON, WDK_PropertyType.FANTASY_TEAMS_TOOLTIP, false);
        fantasyStandingButton = initChildButton(bottomToolbarPane, WDK_PropertyType.FANTASY_STANDING_ICON, WDK_PropertyType.FANTASY_STANDING_TOOLTIP, false);
        draftButton = initChildButton(bottomToolbarPane, WDK_PropertyType.DRAFT_ICON, WDK_PropertyType.DRAFT_TOOLTIP, false);
        MLBTeamButton = initChildButton(bottomToolbarPane, WDK_PropertyType.MLB_TEAM_ICON, WDK_PropertyType.MLB_TEAM_TOOLTIP, false);

    }
     
      // INIT A BUTTON AND ADD IT TO A CONTAINER IN A TOOLBAR
    private Button initChildButton(Pane toolbar, WDK_PropertyType icon, WDK_PropertyType tooltip, boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imagePath = "file:" + PATH_IMAGES + props.getProperty(icon.toString());
        Image buttonImage = new Image(imagePath);
        Button button = new Button();
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
        button.setTooltip(buttonTooltip);
        toolbar.getChildren().add(button);
        return button;
    }
    
    private Label initLabel(WDK_PropertyType labelProperty, String styleClass) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelText = props.getProperty(labelProperty);
        Label label = new Label(labelText);
        label.getStyleClass().add(styleClass);
        return label;
    }
    private Label initChildLabel(Pane container, WDK_PropertyType labelProperty, String styleClass) {
        Label label = initLabel(labelProperty, styleClass);
        container.getChildren().add(label);
        return label;
    }
    private TextField initGridTextField(Pane container, int size, String initText, boolean editable) {
        TextField tf = new TextField();
        tf.setPrefColumnCount(size);
        tf.setText(initText);
        tf.setEditable(editable);
        container.getChildren().add(tf);
        return tf;
    }
    private RadioButton initRadioButton(Pane container, WDK_PropertyType labelProperty, ToggleGroup group){
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelText = props.getProperty(labelProperty);
        
        RadioButton bt = new RadioButton(labelText);
        container.getChildren().add(bt);
        bt.setToggleGroup(group);
        
        return bt;
    }
    
    private void initFantasyTeamsWorkspace() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        topWorkspaceSplitPane = new SplitPane();
        topWorkspacePane = new VBox();
        
        courseHeadingLabel = initChildLabel(topWorkspacePane, WDK_PropertyType.FANTASY_TEAM_HEADING_LABEL, CLASS_HEADING_LABEL);
        wdkPane.setCenter(topWorkspacePane);
    }
    
    private void initAvailablePlayersWorkspace() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        availPlayersWorkspacePane = new VBox();
        
        availPlayersHeadingLabel = initChildLabel(availPlayersWorkspacePane, WDK_PropertyType.AVAILABLE_PLAYERS_HEADING_LABEL, CLASS_HEADING_LABEL);
        
        HBox bar = new HBox();
        addButton = initChildButton(bar, WDK_PropertyType.ADD_ICON, WDK_PropertyType.ADD_LECTURE_TOOLTIP, true);
        removeButton = initChildButton(bar, WDK_PropertyType.MINUS_ICON, WDK_PropertyType.REMOVE_LECTURE_TOOLTIP, true);
        availPlayersHeadingLabel = initChildLabel(bar, WDK_PropertyType.SEARCH_HEADING_LABEL, CLASS_HEADING_LABEL);
        searchBar = initGridTextField(bar, 20, "", true);
        
        HBox bar1 = new HBox();
        final ToggleGroup group = new ToggleGroup();
        button1 = initRadioButton(bar1, WDK_PropertyType.ALL_LABEL, group);
        button2 = initRadioButton(bar1, WDK_PropertyType.FIRST_BASEMAN_LABEL, group);
        button3 = initRadioButton(bar1, WDK_PropertyType.SECOND_BASEMAN_LABEL, group);
        button4 = initRadioButton(bar1, WDK_PropertyType.THIRD_BASEMAN_LABEL, group);
        button5 = initRadioButton(bar1, WDK_PropertyType.CATCHER_LABEL, group);
        button6 = initRadioButton(bar1, WDK_PropertyType.CORNER_INFIELDER_LABEL, group);
        button7 = initRadioButton(bar1, WDK_PropertyType.MID_INFIELD_LABEL, group);
        button8 = initRadioButton(bar1, WDK_PropertyType.SHORTSTOP_LABEL, group);
        button9 = initRadioButton(bar1, WDK_PropertyType.OUTFIELDER_LABEL, group);
        button10= initRadioButton(bar1, WDK_PropertyType.UTILITY_LABEL, group);
        button11= initRadioButton(bar1, WDK_PropertyType.PITCHER_LABEL, group);

        
               
        
        availPlayersWorkspacePane.getChildren().add(bar);
        availPlayersWorkspacePane.getChildren().add(bar1);
        /*bar.getChildren().add(addButton);
        bar.getChildren().add(removeButton);*/
        
        //wdkPane.setCenter(availPlayersWorkspacePane);
        
        
    }
    private void initFantasyStandingWorkspace() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        fantasyStandingSplitPane = new SplitPane();
        fantasyStandingWorkspacePane = new VBox();        
        fantasyStandingHeadingLabel = initChildLabel(fantasyStandingWorkspacePane, WDK_PropertyType.FANTASY_STANDING_HEADING_LABEL, CLASS_HEADING_LABEL);
        wdkPane.setCenter(fantasyStandingWorkspacePane);
    }
    private void initDraftWorkspace() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        draftSummarySplitPane = new SplitPane();
        draftSummaryWorkspacePane = new VBox();        
        draftSummaryHeadingLabel = initChildLabel(draftSummaryWorkspacePane, WDK_PropertyType.DRAFT_SUMMARY_HEADING_LABEL, CLASS_HEADING_LABEL);
        wdkPane.setCenter(draftSummaryWorkspacePane);
    }
    private void initMLBTeamsWorkspace() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        mlbTeamsSplitPane = new SplitPane();
        mlbTeamsWorkspacePane = new VBox();        
        mlbTeamsHeadingLabel = initChildLabel(mlbTeamsWorkspacePane, WDK_PropertyType.MLB_TEAMS_HEADING_LABEL, CLASS_HEADING_LABEL);
        wdkPane.setCenter(mlbTeamsWorkspacePane);
    }
    
    private void initEventHandlers() throws IOException {
        homeButton.setOnAction(e -> {
         wdkPane.setCenter(availPlayersWorkspacePane);
        });
        fantasyStandingButton.setOnAction(e -> {
            initFantasyStandingWorkspace();
        });
        fantasyTeamsButton.setOnAction(e -> {
            initFantasyTeamsWorkspace();
        });        
        draftButton.setOnAction(e -> {
            initDraftWorkspace();
        });
        MLBTeamButton.setOnAction(e -> {
            initMLBTeamsWorkspace();
        });
    }
}
