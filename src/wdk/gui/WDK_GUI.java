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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
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
    
    static final String PRIMARY_STYLE_SHEET = PATH_CSS + "wdk_style.css";
    static final String CLASS_BORDERED_PANE = "bordered_pane";
    static final String CLASS_SUBJECT_PANE = "subject_pane";
    static final String CLASS_HEADING_LABEL = "heading_label";
    static final String CLASS_SUBHEADING_LABEL = "subheading_label";
    static final String CLASS_PROMPT_LABEL = "prompt_label";
    static final String EMPTY_TEXT = "";
    static final int LARGE_TEXT_FIELD_LENGTH = 20;
    static final int SMALL_TEXT_FIELD_LENGTH = 5;
    
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
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    // WORKSPACES
    VBox fantasyTeamsPane;
    Label courseHeadingLabel;
    SplitPane fantasyTeamsSplitPane;    
    
    GridPane availPlayersWorkspacePane;
    Label availPlayersHeadingLabel;
    SplitPane availPlayersSplitPane;
    Button addPlayersButton;
    Button removePlayersButton;
    Label searchLabel;
    
    VBox draftSumWorkspacePane;
    Label draftSumHeadingLabel;
    SplitPane draftSumSplitPane;
    
    SplitPane mlbTeamSplitPane = new SplitPane();
    VBox mlbTeamWorkspacePane = new VBox();        
    Label mlbTeamHeadingLabel;
    
    SplitPane fantasyStandingSplitPane;
    VBox fantasyStandingWorkspacePane;
    Label fantasyStandingHeadingLabel;
    
    int x = 0;
    int y = (int)(Math.random()*50);
    
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
        
        //TO START THE DIFFERENT WINDOWS
        initFantasyStandingWorkspace();
        initFantasyTeamsWorkspace();
        initAvailablePlayersWorkspace();
        initDraftSumWorkspace();
        initMLBTeamWorkspace();
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
        button1 = initChildButton(bottomToolbarPane, WDK_PropertyType.PLAYER_HOME_ICON, WDK_PropertyType.PLAYERS_TOOLTIP, false);
        button2 = initChildButton(bottomToolbarPane, WDK_PropertyType.FANTASY_TEAMS_ICON, WDK_PropertyType.FANTASY_TEAMS_TOOLTIP, false);
        button3 = initChildButton(bottomToolbarPane, WDK_PropertyType.FANTASY_STANDING_ICON, WDK_PropertyType.FANTASY_STANDING_TOOLTIP, false);
        button4 = initChildButton(bottomToolbarPane, WDK_PropertyType.DRAFT_ICON, WDK_PropertyType.DRAFT_TOOLTIP, false);
        button5 = initChildButton(bottomToolbarPane, WDK_PropertyType.MLB_TEAM_ICON, WDK_PropertyType.MLB_TEAM_TOOLTIP, false);
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
    private TextField initGridTextField(GridPane container, int size, String initText, boolean editable, int col, int row, int colSpan, int rowSpan) {
        TextField tf = new TextField();
        tf.setPrefColumnCount(size);
        tf.setText(initText);
        tf.setEditable(editable);
        container.add(tf, col, row, colSpan, rowSpan);
        return tf;
    }
    private Label initGridLabel(GridPane container, WDK_PropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
        Label label = initLabel(labelProperty, styleClass);
        container.add(label, col, row, colSpan, rowSpan);
        return label;
    }
     
    // INIT THE WORKSPACES
    private void initFantasyTeamsWorkspace() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        fantasyTeamsSplitPane = new SplitPane();        
        fantasyTeamsPane = new VBox();        
        courseHeadingLabel = initChildLabel(fantasyTeamsPane, WDK_PropertyType.FANTASY_TEAM_HEADING_LABEL, CLASS_HEADING_LABEL);
        }
    private void initAvailablePlayersWorkspace() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        availPlayersSplitPane = new SplitPane();
        availPlayersWorkspacePane = new GridPane();        
        availPlayersHeadingLabel = initGridLabel(availPlayersWorkspacePane, WDK_PropertyType.AVAILABLE_PLAYERS_HEADING_LABEL, CLASS_HEADING_LABEL, 1, 1, 1, 1);
        
        Pane secondPane = new HBox();
        addPlayersButton = initChildButton(secondPane, WDK_PropertyType.ADD_ICON, WDK_PropertyType.ADD_TOOLTIP, false);
        removePlayersButton = initChildButton(secondPane, WDK_PropertyType.MINUS_ICON, WDK_PropertyType.REMOVE_TOOLTIP, false);
        //searchLabel = initLabel(WDK_PropertyType.SEARCH_HEADING_LABEL, CLASS_SUBHEADING_LABEL);
        
        availPlayersWorkspacePane.add(secondPane, 1, 2);
        }
    private void initFantasyStandingWorkspace() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        fantasyStandingSplitPane = new SplitPane();
        fantasyStandingWorkspacePane = new VBox();        
        fantasyStandingHeadingLabel = initChildLabel(fantasyStandingWorkspacePane, WDK_PropertyType.FANTASY_STANDING_HEADING_LABEL, CLASS_HEADING_LABEL);
    }
    private void initDraftSumWorkspace() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        draftSumSplitPane = new SplitPane();
        draftSumWorkspacePane = new VBox();        
        draftSumHeadingLabel = initChildLabel(draftSumWorkspacePane, WDK_PropertyType.FANTASY_STANDING_HEADING_LABEL, CLASS_HEADING_LABEL);
    }
    private void initMLBTeamWorkspace() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        //mlbTeamSplitPane = new SplitPane();
        mlbTeamWorkspacePane = new VBox();        
        mlbTeamHeadingLabel = initChildLabel(mlbTeamWorkspacePane, WDK_PropertyType.MLB_TEAM_HEADING_LABEL, CLASS_HEADING_LABEL);
    }
    
    
    private void initEventHandlers() throws IOException {
        newDraftButton.setOnAction(e -> {
            wdkPane.setCenter(fantasyTeamsPane);
        });
        button2.setOnAction(e -> {
            wdkPane.setCenter(availPlayersWorkspacePane);
        });
        button3.setOnAction(e -> {
            wdkPane.setCenter(availPlayersWorkspacePane);
        });
        button4.setOnAction(e -> {
            wdkPane.setCenter(draftSumWorkspacePane);});
        button5.setOnAction(e -> {
            wdkPane.setCenter(mlbTeamWorkspacePane);});
    }
}
