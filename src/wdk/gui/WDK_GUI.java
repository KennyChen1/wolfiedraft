/*
 * Kenny Chen
 */
package wdk.gui;

import static wolfieballdraftkit.WDK_StartupConstants.*;
import wolfieballdraftkit.WDK_PropertyType;
import wdk.controller.FileController;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.json.JsonArray;
import properties_manager.PropertiesManager;
import wdk.data.FantasyTeam;
import wdk.data.Hitter;
import wdk.data.Pitcher;
import wdk.data.Player;
import static wolfieballdraftkit.WDK_PropertyType.*;

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
    VBox fantasyTeamWorkspacePane;
    Label fantasyTeamHeadingLabel;
    
    //FOR THE PLAYER HOME WORKSPACE
    SplitPane availPlayersSplitPane;
    VBox availPlayersWorkspacePane;
    Label availPlayersHeadingLabel;
    Label searchLabel;
    Button addButton;
    Button removeButton;
    TextField searchBar;
    RadioButton allButton;    RadioButton firstBButton;    RadioButton secBButton;
    RadioButton thirdBButton;    RadioButton catcherButton;    RadioButton cornInButton;
    RadioButton midInButton;    RadioButton shortStopButton;    RadioButton outfielderButton;
    RadioButton utilityButton;    RadioButton pitcherButton;
    TableView<Pitcher> pitcherTable;
    TableView<Hitter> hitterTable;
    TableView<Player> allPlayerTable;
    TableView<Player> startingLineupTable;
    TableView<Player> taxiPlayersTable;
    
    ObservableList<Hitter> tempHitterList;
    ObservableList<Pitcher> pitcherList;
    ObservableList<Player> playerList;
    ObservableList<Hitter> allHitterList;
    ObservableList<String> fantasyTeamList;
    ObservableList<Hitter> dummyPitcherList;
    ObservableList<FantasyTeam> draftTeams;
    ObservableList<Player> startingLineup;
    ObservableList<Player> taxiPlayers;
    
    //THE TABLE COLUMNS
    TableColumn firstColumn;    TableColumn lastColumn;         TableColumn proTeamColumn;
    TableColumn positionColumn; TableColumn birthyearColumn;    TableColumn winsColumn;
    TableColumn savesColumn;    TableColumn strikeoutsColumn;   TableColumn earnedRunsAvgColumn;
    TableColumn whipColumn;     TableColumn estimValueColumn;   
    TableColumn notesAllColumn; TableColumn notesHitColumn;     TableColumn notesPitchColumn;
    TableColumn runsColumn;     TableColumn homerunsColumn;     TableColumn runsBattedInColumn;
    TableColumn battingAverageColumn;                           TableColumn runsOrWinsColumn;
    TableColumn homePerSaveColumn;                              TableColumn runsBattedInPerOutColumn;
    TableColumn stealsPerERAColumn;                             TableColumn avgPerWhipColumn;
    
    TableColumn teamPositionColumnF; 
    TableColumn firstColumnF;    TableColumn lastColumnF;         TableColumn proTeamColumnF;
    TableColumn positionColumnF; TableColumn runsOrWinsColumnF;  TableColumn homePerSaveColumnF;       
    TableColumn runsBattedInPerOutColumnF;   TableColumn stealsPerERAColumnF; 
    TableColumn avgPerWhipColumnF;      TableColumn estimValueColumnF;
    TableColumn contractColumnF;        TableColumn salaryColumnF;
    
    // AND TABLE COLUMNS
    static final String COL_FIRST = "First";
    static final String COL_LAST = "Last";
    static final String COL_PROTEAM = "Pro Team";
    static final String COL_POS = "Position";
    static final String COL_BIRTH = "Year of Birth";
    static final String COL_WINS = "Wins";
    static final String COL_SAVES = "Saves";
    static final String COL_STRIKEOUTS = "K";
    static final String COL_ERA = "ERA";
    static final String COL_WHIP = "WHIP";// walks + hits / innings pitched 
    static final String COL_VALUE = "Estimated Value";
    static final String COL_NOTES = "Notes";
    static final String COL_RUNS = "R";
    static final String COL_HOMERUNS = "HR";
    static final String COL_RUNS_BATTED_IN = "RBI";
    static final String COL_BATTING_AVERAGE = "BA";
    static final String COL_RPW = "R/W";
    static final String COL_HRPSV = "HR/SV";
    static final String COL_RBIPK = "RBI/K";
    static final String COL_SBPERA = "SB/ERA";
    static final String COL_BAPWHIP = "BA/WHIP";
    
    //FANTASY TEAM PANE
    Label draftNameLabel;
    Label startingLineupLabel;
    Label taxiPlayerLabel;
    TextField draftNameTextField;
    Button draftAddButtion;
    Button draftRemoveButtion;
    Button draftEditButtion;
    Label fantasyTeamLabel;
    ComboBox fantasyTeamComboBox;
    
    SplitPane fantasyStandingSplitPane;
    VBox fantasyStandingWorkspacePane;        
    Label fantasyStandingHeadingLabel;
    
    SplitPane draftSummarySplitPane;
    VBox draftSummaryWorkspacePane;        
    Label draftSummaryHeadingLabel;
    
    
    //MLB TEAMS VARIABLES
    SplitPane mlbTeamsSplitPane;
    VBox mlbTeamsWorkspacePane;        
    Label mlbTeamsHeadingLabel;
    ComboBox mlbTeamsComboBox;
    ObservableList<Player> mlbPlayers;
    ObservableList<String> mlbTeamsList;
    TableView<Player> mlbTable;
    
    //FANTASY STANDING VARIABLES
    TableView<FantasyTeam> standingsTable;
    TableColumn teamNameColumn;
    TableColumn playersNeededColumn;
    TableColumn moolahLeftColumn;
    TableColumn moolahPerColumn;
    TableColumn rColumn;
    TableColumn hrColumn;
    TableColumn rbiColumn;
    TableColumn sbColumn;
    TableColumn baColumn;
    TableColumn wColumn;
    TableColumn svColumn;
    TableColumn kColumn;
    TableColumn eraColumn;
    TableColumn whipAvgColumn;
    TableColumn totalPtsColumn;
    
    //DRUFT SUMMARAY
    Button selectPlayer;
    Button playButton;
    Button pauseButton;
    TableView<Player> summaryTable;
    TableColumn pickColumn;
    TableColumn firstPickColumn;
    TableColumn lastPickColumn;
    TableColumn summaryFantasyColumn;
    TableColumn summaryContractColumn;
    TableColumn summarySalaryColumn;
    ObservableList<Player> draftSummaryList;
    ObservableList<Player> summaryStartingList;
    ObservableList<Player> summaryTaxiList;
    Timeline timeline;

    
    FileController a = new FileController();
    JsonArray pitchersList;
    JsonArray hitterList;
    
    
    public WDK_GUI(Stage initPrimaryStage) throws IOException {
        primaryStage = initPrimaryStage;
        pitchersList  = a.loadPitchers().getJsonArray("Pitchers");
        hitterList = a.loadHitters().getJsonArray("Hitters");
        
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
         
        pitcherList = FXCollections.observableArrayList();
        fantasyTeamList = FXCollections.observableArrayList();
        draftTeams = FXCollections.observableArrayList();
        startingLineup = FXCollections.observableArrayList();
         
        initFileToolbar();
        initBottomToolbar();
        initWindow(windowTitle);
        initAllPlayerTable();
        initHitterTable();
        initPitcherTable();
        
            initDraftWorkspace();
        initFantasyTeamsWorkspace();
        initAvailablePlayersWorkspace();
        initFantasyStandingWorkspace();
        initMLBTeamsWorkspace();
        initEventHandlers();}
    
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
        fantasyTeamWorkspacePane = new VBox();
        HBox dummyPane = new HBox();
        HBox textPane = new HBox();

        fantasyTeamHeadingLabel = initChildLabel(fantasyTeamWorkspacePane, FANTASY_TEAM_HEADING_LABEL, "");
        draftNameLabel = initChildLabel(textPane, DRAFT_NAME_LABEL, "");
        draftNameTextField = initGridTextField(textPane, 20, "", true);
        draftAddButtion = initChildButton(dummyPane, WDK_PropertyType.ADD_ICON, WDK_PropertyType.ADD_FANTASY_TOOLTIP, false);
        draftRemoveButtion = initChildButton(dummyPane, WDK_PropertyType.MINUS_ICON, WDK_PropertyType.REMOVE_FANTASY_TOOLTIP, false);
        draftEditButtion = initChildButton(dummyPane, WDK_PropertyType.EDIT_ICON, WDK_PropertyType.EDIT_FANTASY_TOOLTIP, false);
        fantasyTeamLabel = initChildLabel(dummyPane, FANTASY_COMBO_LABEL, "");
        fantasyTeamComboBox = new ComboBox(fantasyTeamList);
        
        fantasyTeamWorkspacePane.getChildren().add(textPane);
        fantasyTeamWorkspacePane.getChildren().add(dummyPane);
        dummyPane.getChildren().add(fantasyTeamComboBox);
        
        VBox startPane = new VBox();
        VBox taxiPane = new VBox();
        startingLineupTable = new TableView();
        //startingLineupTabl
        taxiPlayersTable = new TableView();
        startingLineupLabel = initChildLabel(startPane, STARTING_LINEUP_LABEL, "");
        taxiPlayerLabel = initChildLabel(taxiPane, TAXI_SQUAD_LABEL, "");
        
        startPane.getChildren().add(startingLineupTable);
        fantasyTeamWorkspacePane.getChildren().add(startPane);
        taxiPane.getChildren().add(taxiPlayersTable);
        fantasyTeamWorkspacePane.getChildren().add(taxiPane);
        
        teamPositionColumnF = new TableColumn("Position");    
        firstColumnF = new TableColumn(COL_FIRST);    
        lastColumnF = new TableColumn(COL_LAST);
        proTeamColumnF = new TableColumn(COL_PROTEAM);
        positionColumnF = new TableColumn(COL_POS);
        runsOrWinsColumnF = new TableColumn(COL_RPW);
        homePerSaveColumnF = new TableColumn(COL_HRPSV);
        runsBattedInPerOutColumnF = new TableColumn(COL_RBIPK);
        stealsPerERAColumnF = new TableColumn(COL_SBPERA);
        avgPerWhipColumnF = new TableColumn(COL_BAPWHIP);
        estimValueColumnF = new TableColumn(COL_VALUE);        
        contractColumnF = new TableColumn("Contract");        
        salaryColumnF = new TableColumn("Salary");        

        teamPositionColumnF.setSortable(false);
        teamPositionColumnF.setCellValueFactory(new PropertyValueFactory<>("teamPosition"));
        firstColumnF.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstColumnF.setSortable(false);
        lastColumnF.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastColumnF.setSortable(false);
        proTeamColumnF.setCellValueFactory(new PropertyValueFactory<>("team"));
        proTeamColumnF.setSortable(false);
        positionColumnF.setCellValueFactory(new PropertyValueFactory<>("position"));
        positionColumnF.setSortable(false);
        
        runsOrWinsColumnF.setCellValueFactory(new PropertyValueFactory<>("RW"));
        runsOrWinsColumnF.setSortable(false);
        homePerSaveColumnF.setCellValueFactory(new PropertyValueFactory<>("HRSV"));
        homePerSaveColumnF.setSortable(false);
        runsBattedInPerOutColumnF.setCellValueFactory(new PropertyValueFactory<>("RBIK"));
        runsBattedInPerOutColumnF.setSortable(false);
        stealsPerERAColumnF.setCellValueFactory(new PropertyValueFactory<>("SBERA"));
        stealsPerERAColumnF.setSortable(false);
        avgPerWhipColumnF.setCellValueFactory(new PropertyValueFactory<>("BAWHIP"));
        avgPerWhipColumnF.setSortable(false);
        estimValueColumnF.setCellValueFactory(new PropertyValueFactory<>("eta"));
        estimValueColumnF.setSortable(false);
        contractColumnF.setCellValueFactory(new PropertyValueFactory<>("contract"));
        contractColumnF.setSortable(false);
        salaryColumnF.setCellValueFactory(new PropertyValueFactory<>("salary"));
        salaryColumnF.setSortable(false);
        
        taxiPlayersTable.getColumns().addAll(firstColumnF, lastColumnF, proTeamColumnF, positionColumnF, 
                runsOrWinsColumnF, homePerSaveColumnF, runsBattedInPerOutColumnF, 
                    stealsPerERAColumnF, avgPerWhipColumnF, estimValueColumnF);
        startingLineupTable.getColumns().addAll(teamPositionColumnF, firstColumnF, lastColumnF, 
                proTeamColumnF, positionColumnF, runsOrWinsColumnF, homePerSaveColumnF, 
                runsBattedInPerOutColumnF, stealsPerERAColumnF, avgPerWhipColumnF, 
                    estimValueColumnF, contractColumnF, salaryColumnF);
        
        
    }
    
    private void initAvailablePlayersWorkspace() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        availPlayersWorkspacePane = new VBox();
        
        availPlayersHeadingLabel = initChildLabel(availPlayersWorkspacePane, WDK_PropertyType.AVAILABLE_PLAYERS_HEADING_LABEL, CLASS_HEADING_LABEL);
        
        HBox bar = new HBox();
        addButton = initChildButton(bar, WDK_PropertyType.ADD_ICON, WDK_PropertyType.ADD_TOOLTIP, false);
        removeButton = initChildButton(bar, WDK_PropertyType.MINUS_ICON, WDK_PropertyType.REMOVE_TOOLTIP, false);
        availPlayersHeadingLabel = initChildLabel(bar, WDK_PropertyType.SEARCH_HEADING_LABEL, CLASS_HEADING_LABEL);
        searchBar = initGridTextField(bar, 20, "", true);
        
        HBox radioButtonRow = new HBox();
        final ToggleGroup group = new ToggleGroup();
        allButton = initRadioButton(radioButtonRow, WDK_PropertyType.ALL_LABEL, group);
        firstBButton = initRadioButton(radioButtonRow, WDK_PropertyType.FIRST_BASEMAN_LABEL, group);
        secBButton = initRadioButton(radioButtonRow, WDK_PropertyType.SECOND_BASEMAN_LABEL, group);
        thirdBButton = initRadioButton(radioButtonRow, WDK_PropertyType.THIRD_BASEMAN_LABEL, group);
        catcherButton = initRadioButton(radioButtonRow, WDK_PropertyType.CATCHER_LABEL, group);
        cornInButton = initRadioButton(radioButtonRow, WDK_PropertyType.CORNER_INFIELDER_LABEL, group);
        midInButton = initRadioButton(radioButtonRow, WDK_PropertyType.MID_INFIELD_LABEL, group);
        shortStopButton = initRadioButton(radioButtonRow, WDK_PropertyType.SHORTSTOP_LABEL, group);
        outfielderButton = initRadioButton(radioButtonRow, WDK_PropertyType.OUTFIELDER_LABEL, group);
        utilityButton = initRadioButton(radioButtonRow, WDK_PropertyType.UTILITY_LABEL, group);
        pitcherButton = initRadioButton(radioButtonRow, WDK_PropertyType.PITCHER_LABEL, group);
         
        allButton.setSelected(true);
        
        availPlayersWorkspacePane.getChildren().add(bar);
        availPlayersWorkspacePane.getChildren().add(radioButtonRow);
        availPlayersWorkspacePane.getChildren().add(allPlayerTable);
    }
    private void initFantasyStandingWorkspace() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        fantasyStandingWorkspacePane = new VBox();
        fantasyStandingHeadingLabel = initChildLabel(fantasyStandingWorkspacePane, WDK_PropertyType.FANTASY_STANDING_HEADING_LABEL, CLASS_HEADING_LABEL);
        
        standingsTable = new TableView();
        
        teamNameColumn = new TableColumn("Team Name");
        playersNeededColumn = new TableColumn("Players Needed");
        moolahLeftColumn = new TableColumn("$ Left");
        moolahPerColumn = new TableColumn("$ PP");
        rColumn = new TableColumn(COL_RUNS);
        hrColumn = new TableColumn(COL_HOMERUNS);
        rbiColumn = new TableColumn(COL_RUNS_BATTED_IN);
        sbColumn = new TableColumn("SB");
        baColumn = new TableColumn(COL_BATTING_AVERAGE);
        wColumn = new TableColumn("W");
        svColumn = new TableColumn("SV");
        kColumn = new TableColumn(COL_STRIKEOUTS);
        eraColumn = new TableColumn(COL_ERA);
        whipAvgColumn = new TableColumn(COL_WHIP);
        totalPtsColumn = new TableColumn("Total Points");
        
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("fantasyTeamName"));
        playersNeededColumn.setCellValueFactory(new PropertyValueFactory<>("playersRequired"));
        moolahLeftColumn.setCellValueFactory(new PropertyValueFactory<>("funding"));
        moolahPerColumn.setCellValueFactory(new PropertyValueFactory<>("avgFundingPerPlayer"));
        rColumn.setCellValueFactory(new PropertyValueFactory<>("runs"));
        hrColumn.setCellValueFactory(new PropertyValueFactory<>("homeRuns"));
        rbiColumn.setCellValueFactory(new PropertyValueFactory<>("RBI"));
        sbColumn.setCellValueFactory(new PropertyValueFactory<>("stolenBases"));
        baColumn.setCellValueFactory(new PropertyValueFactory<>("battingAverage"));
        wColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        svColumn.setCellValueFactory(new PropertyValueFactory<>("saves"));
        kColumn.setCellValueFactory(new PropertyValueFactory<>("strikeouts"));
        eraColumn.setCellValueFactory(new PropertyValueFactory<>("ERA"));
        whipAvgColumn.setCellValueFactory(new PropertyValueFactory<>("WHIP"));
        totalPtsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));
        
        standingsTable.setItems(draftTeams);
        
        standingsTable.getColumns().addAll(teamNameColumn, playersNeededColumn, 
                moolahLeftColumn, moolahPerColumn, rColumn, hrColumn, rbiColumn,
                    sbColumn, baColumn, wColumn, svColumn, kColumn, eraColumn, 
                        whipAvgColumn, totalPtsColumn);
        
        fantasyStandingWorkspacePane.getChildren().add(standingsTable);
    }
    private void initDraftWorkspace() {
        draftSummaryWorkspacePane = new VBox();        
        
        draftSummaryHeadingLabel = initChildLabel(draftSummaryWorkspacePane, WDK_PropertyType.DRAFT_SUMMARY_HEADING_LABEL, CLASS_HEADING_LABEL);
                
        HBox row = new HBox();
        selectPlayer = this.initChildButton(row, STAR_ICON, ADD_ICON, false);
        playButton = this.initChildButton(row, PLAY_ICON, ADD_ICON, false);
        pauseButton = this.initChildButton(row, PAUSE_ICON, ADD_ICON, false);        
        draftSummaryWorkspacePane.getChildren().add(row);
        
        //draftSummaryList
        summaryTable = new TableView();
        pickColumn = new TableColumn("Pick #");
        firstPickColumn = new TableColumn(COL_FIRST);
        lastPickColumn = new TableColumn(COL_LAST);
        summaryFantasyColumn = new TableColumn("Team");
        summaryContractColumn = new TableColumn("Contract");
        summarySalaryColumn = new TableColumn("Salary");
        
        draftSummaryList = FXCollections.observableArrayList();
        summaryStartingList = FXCollections.observableArrayList();
        summaryTaxiList = FXCollections.observableArrayList();
        
        pickColumn.setCellValueFactory(new Callback<CellDataFeatures<Player, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(CellDataFeatures<Player, String> param) {
            return new ReadOnlyObjectWrapper(summaryTable.getItems().indexOf(param.getValue()) + 1 + "");
        }
        });   
        pickColumn.setSortable(false);
        
        
        //pickColumn = new TableColumn("Pick #");
        firstPickColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastPickColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        summaryFantasyColumn.setCellValueFactory(new PropertyValueFactory<>("fantasyTeam"));
        summaryContractColumn.setCellValueFactory(new PropertyValueFactory<>("contract"));
        summarySalaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        
        summaryTable.getColumns().addAll(pickColumn, firstPickColumn, lastPickColumn,
               summaryFantasyColumn, summaryContractColumn, summarySalaryColumn);
        summaryTable.setItems(draftSummaryList);
        draftSummaryWorkspacePane.getChildren().add(summaryTable);
    }
    private void initMLBTeamsWorkspace() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        mlbTeamsWorkspacePane = new VBox();        
        mlbTeamsHeadingLabel = initChildLabel(mlbTeamsWorkspacePane, WDK_PropertyType.MLB_TEAMS_HEADING_LABEL, CLASS_HEADING_LABEL);
        mlbTeamsList = FXCollections.observableArrayList("ATL", "AZ", "CHC", "CIN", "COL", "LAD", "MIA", 
                "MIL", "NYM", "PHI", "PIT", "SD", "SF", "STL", "WSH");
        
        HBox sndRow = new HBox();
        mlbTeamsComboBox = new ComboBox(mlbTeamsList);
        mlbTeamsHeadingLabel = initLabel(WDK_PropertyType.MLB_TEAMS_COMBOBOX_LABEL, CLASS_HEADING_LABEL);
        
        sndRow.getChildren().add(mlbTeamsHeadingLabel);
        sndRow.getChildren().add(mlbTeamsComboBox);

        mlbTeamsWorkspacePane.getChildren().add(sndRow);
        
        VBox mlbBox = new VBox();
        mlbTable = new TableView();
        mlbBox.getChildren().add(mlbTable);
        
        mlbTeamsWorkspacePane.getChildren().add(mlbBox);
        TableColumn mlbFirstColumn = new TableColumn(COL_FIRST);    
        TableColumn mlbLastColumn = new TableColumn(COL_LAST);
        TableColumn mlbPositionColumn = new TableColumn(COL_POS);
        
        mlbFirstColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        mlbLastColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        mlbPositionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        
        mlbTable.getColumns().addAll(mlbFirstColumn, mlbLastColumn, mlbPositionColumn);
    }
    
    private void initPitcherTable() throws IOException  {
        VBox playersBox = new VBox();
        pitcherTable = new TableView();
        pitcherTable.setEditable(true);
        
        playersBox.getChildren().add(pitcherTable);
        
        //SET UP THE TABLE COLUMNS        
        firstColumn = new TableColumn(COL_FIRST);    
        lastColumn = new TableColumn(COL_LAST);
        proTeamColumn = new TableColumn(COL_PROTEAM);
        positionColumn = new TableColumn(COL_POS);
        birthyearColumn = new TableColumn(COL_BIRTH);
        winsColumn = new TableColumn(COL_WINS);
        savesColumn = new TableColumn(COL_SAVES);
        strikeoutsColumn = new TableColumn(COL_STRIKEOUTS);
        earnedRunsAvgColumn = new TableColumn(COL_ERA);
        whipColumn = new TableColumn(COL_WHIP);
        estimValueColumn = new TableColumn(COL_VALUE);
        notesPitchColumn = new TableColumn(COL_NOTES);
        
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        proTeamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        birthyearColumn.setCellValueFactory(new PropertyValueFactory<>("birthYear"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        savesColumn.setCellValueFactory(new PropertyValueFactory<>("saves"));
        strikeoutsColumn.setCellValueFactory(new PropertyValueFactory<>("strikeOuts"));
        earnedRunsAvgColumn.setCellValueFactory(new PropertyValueFactory<>("ERA"));
        whipColumn.setCellValueFactory(new PropertyValueFactory<>("WHIP"));
        estimValueColumn.setCellValueFactory(new PropertyValueFactory<>("TEAM"));
        notesPitchColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        notesPitchColumn.setCellFactory(TextFieldTableCell.<String>forTableColumn());
       
        
            for (int i = 0; i < pitchersList.size(); i++) {
                String fName = pitchersList.getJsonObject(i).getString("FIRST_NAME");
                String lName = pitchersList.getJsonObject(i).getString("LAST_NAME");
                String team = pitchersList.getJsonObject(i).getString("TEAM");
                int year = Integer.parseInt(pitchersList.getJsonObject(i).getString("YEAR_OF_BIRTH"));
                String note = pitchersList.getJsonObject(i).getString("NOTES");
                String nation = pitchersList.getJsonObject(i).getString("NATION_OF_BIRTH");
                double ip = Double.parseDouble(pitchersList.getJsonObject(i).getString("IP"));
                int earnedRuns = Integer.parseInt(pitchersList.getJsonObject(i).getString("ER"));
                int wins = Integer.parseInt(pitchersList.getJsonObject(i).getString("W"));
                int saves = Integer.parseInt(pitchersList.getJsonObject(i).getString("SV"));
                int hits = Integer.parseInt(pitchersList.getJsonObject(i).getString("H"));
                int balls = Integer.parseInt(pitchersList.getJsonObject(i).getString("BB"));
                int outs = Integer.parseInt(pitchersList.getJsonObject(i).getString("K")); 
                
                Pitcher b = new Pitcher(fName, lName, team, year, note, nation, ip, earnedRuns,
                    wins, saves, hits, balls, outs);
                pitcherList.add(b);
            }
            
        pitcherTable.getColumns().addAll(firstColumn, lastColumn, proTeamColumn, positionColumn, 
                birthyearColumn, winsColumn, savesColumn, strikeoutsColumn, earnedRunsAvgColumn,
                    whipColumn, estimValueColumn, notesPitchColumn);
        pitcherTable.setItems(pitcherList);
        
    }
    private void initHitterTable(){
        //VBox playersBox = new VBox();
        hitterTable = new TableView();
        hitterTable.setEditable(true);
        //playersBox.getChildren().add(pitcherTable);
        
        //SET UP THE TABLE COLUMNS        
        firstColumn = new TableColumn(COL_FIRST);    
        lastColumn = new TableColumn(COL_LAST);
        proTeamColumn = new TableColumn(COL_PROTEAM);
        positionColumn = new TableColumn(COL_POS);
        birthyearColumn = new TableColumn(COL_BIRTH);
        runsColumn = new TableColumn(COL_RUNS);
        homerunsColumn = new TableColumn(COL_HOMERUNS);
        runsBattedInColumn = new TableColumn(COL_RUNS_BATTED_IN);
        battingAverageColumn = new TableColumn(COL_BATTING_AVERAGE);
        estimValueColumn = new TableColumn(COL_VALUE);
        notesHitColumn = new TableColumn(COL_NOTES);
                
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        proTeamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        birthyearColumn.setCellValueFactory(new PropertyValueFactory<>("birthYear"));
        runsColumn.setCellValueFactory(new PropertyValueFactory<>("runs"));
        homerunsColumn.setCellValueFactory(new PropertyValueFactory<>("homeRuns"));
        runsBattedInColumn.setCellValueFactory(new PropertyValueFactory<>("runsBattedIn"));
        battingAverageColumn.setCellValueFactory(new PropertyValueFactory<>("BA"));
        estimValueColumn.setCellValueFactory(new PropertyValueFactory<>("TEAM"));
        notesHitColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        notesHitColumn.setCellFactory(TextFieldTableCell.<String>forTableColumn());

        allHitterList = FXCollections.observableArrayList();
                   
        for (int i = 0; i < hitterList.size(); i++) {
            String fName = hitterList.getJsonObject(i).getString("FIRST_NAME");
            String lName = hitterList.getJsonObject(i).getString("LAST_NAME");
            String team = hitterList.getJsonObject(i).getString("TEAM");
            int year = Integer.parseInt(hitterList.getJsonObject(i).getString("YEAR_OF_BIRTH"));
            String note = hitterList.getJsonObject(i).getString("NOTES");
            String nation = hitterList.getJsonObject(i).getString("NATION_OF_BIRTH");
            String positions = hitterList.getJsonObject(i).getString("QP");
            positions = addPosition(positions);
            int ab = Integer.parseInt(hitterList.getJsonObject(i).getString("AB"));
            int hits = Integer.parseInt(hitterList.getJsonObject(i).getString("H"));
            int runs = Integer.parseInt(hitterList.getJsonObject(i).getString("R"));
            int homeRuns = Integer.parseInt(hitterList.getJsonObject(i).getString("HR"));
            int runsBattedIn = Integer.parseInt(hitterList.getJsonObject(i).getString("RBI"));
            int stolenBases = Integer.parseInt(hitterList.getJsonObject(i).getString("SB"));
            Hitter b = new Hitter(fName, lName, team, year, note, nation, positions,
                ab, runs, hits, homeRuns, runsBattedIn, stolenBases);
            allHitterList.add(b);
                          
        }
        hitterTable.setItems(allHitterList);  
        
        hitterTable.getColumns().addAll(firstColumn, lastColumn, proTeamColumn, positionColumn, 
                birthyearColumn, runsColumn, homerunsColumn, runsBattedInColumn, 
                    battingAverageColumn, estimValueColumn, notesHitColumn);
       
    }
    private void initAllPlayerTable(){
        allPlayerTable = new TableView();
        allPlayerTable.setEditable(true);
        
        //SET UP THE TABLE COLUMNS        
        firstColumn = new TableColumn(COL_FIRST);    
        lastColumn = new TableColumn(COL_LAST);
        proTeamColumn = new TableColumn(COL_PROTEAM);
        positionColumn = new TableColumn(COL_POS);
        birthyearColumn = new TableColumn(COL_BIRTH);
        runsOrWinsColumn = new TableColumn(COL_RPW);
        homePerSaveColumn = new TableColumn(COL_HRPSV);
        runsBattedInPerOutColumn = new TableColumn(COL_RBIPK);
        stealsPerERAColumn = new TableColumn(COL_SBPERA);
        avgPerWhipColumn = new TableColumn(COL_BAPWHIP);
        estimValueColumn = new TableColumn(COL_VALUE);
        notesAllColumn = new TableColumn(COL_NOTES);
        
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        proTeamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        birthyearColumn.setCellValueFactory(new PropertyValueFactory<>("birthYear"));
        
        runsOrWinsColumn.setCellValueFactory(new PropertyValueFactory<>("RW"));
        homePerSaveColumn.setCellValueFactory(new PropertyValueFactory<>("HRSV"));
        runsBattedInPerOutColumn.setCellValueFactory(new PropertyValueFactory<>("RBIK"));
        stealsPerERAColumn.setCellValueFactory(new PropertyValueFactory<>("SBERA"));
        avgPerWhipColumn.setCellValueFactory(new PropertyValueFactory<>("BAWHIP"));
        estimValueColumn.setCellValueFactory(new PropertyValueFactory<>("eta"));
        
        notesAllColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        notesAllColumn.setCellFactory(TextFieldTableCell.<String>forTableColumn());
        
        playerList = FXCollections.observableArrayList();
        
        for (int i = 0; i < hitterList.size(); i++) {
            String fName = hitterList.getJsonObject(i).getString("FIRST_NAME");
            String lName = hitterList.getJsonObject(i).getString("LAST_NAME");
            String team = hitterList.getJsonObject(i).getString("TEAM");
            int year = Integer.parseInt(hitterList.getJsonObject(i).getString("YEAR_OF_BIRTH"));
            String note = hitterList.getJsonObject(i).getString("NOTES");
            String positions = hitterList.getJsonObject(i).getString("QP");
            positions = addPosition(positions);
            int ab = Integer.parseInt(hitterList.getJsonObject(i).getString("AB"));
            int hits = Integer.parseInt(hitterList.getJsonObject(i).getString("H"));
            int runs = Integer.parseInt(hitterList.getJsonObject(i).getString("R"));
            int homeRuns = Integer.parseInt(hitterList.getJsonObject(i).getString("HR"));
            int runsBattedIn = Integer.parseInt(hitterList.getJsonObject(i).getString("RBI"));
            double stolenBases = Integer.parseInt(hitterList.getJsonObject(i).getString("SB"));
                
            double sb;
            if(ab == 0){
                sb = -1;
            } else{
                DecimalFormat df = new DecimalFormat("#.000"); 
                sb = Double.parseDouble(df.format(1.0*hits/ab));
            }

            Player b = new Player(fName, lName, team, positions, year, note, 
                    runs, homeRuns, runsBattedIn, stolenBases, sb, ab, hits);
            playerList.add(b);
        }
            
        for (int i = 0; i < pitchersList.size(); i++) {
            String fName = pitchersList.getJsonObject(i).getString("FIRST_NAME");
            String lName = pitchersList.getJsonObject(i).getString("LAST_NAME");
            String team = pitchersList.getJsonObject(i).getString("TEAM");
            int year = Integer.parseInt(pitchersList.getJsonObject(i).getString("YEAR_OF_BIRTH"));
            String note = pitchersList.getJsonObject(i).getString("NOTES");
            double ip = Double.parseDouble(pitchersList.getJsonObject(i).getString("IP"));
            int earnedRuns = Integer.parseInt(pitchersList.getJsonObject(i).getString("ER"));
            int wins = Integer.parseInt(pitchersList.getJsonObject(i).getString("W"));
            int saves = Integer.parseInt(pitchersList.getJsonObject(i).getString("SV"));
            int hits = Integer.parseInt(pitchersList.getJsonObject(i).getString("H"));
            int balls = Integer.parseInt(pitchersList.getJsonObject(i).getString("BB"));
            int outs = Integer.parseInt(pitchersList.getJsonObject(i).getString("K")); 

            double era, whip;

            if(ip == 0){
                era = -1; whip = -1;
            } else{
                DecimalFormat df = new DecimalFormat("#.000"); 
                era = Double.parseDouble(df.format(earnedRuns*9/ip));
                whip = Double.parseDouble(df.format((balls + hits)/ip));
            }

            Player c = new Player(fName, lName, team, "P", year, note, 
                    wins, saves, outs, era, whip);
            playerList.add(c);
        }
        
        allPlayerTable.setItems(playerList);  
          
        allPlayerTable.getColumns().addAll(firstColumn, lastColumn, proTeamColumn, positionColumn, 
                birthyearColumn, runsOrWinsColumn, homePerSaveColumn, runsBattedInPerOutColumn, 
                    stealsPerERAColumn, avgPerWhipColumn, estimValueColumn, notesAllColumn);
     }
    
    private String[] getTableSelection(){
        String[] selections = {""};
        if(firstBButton.isSelected())
            selections = new String[]{"1B"};
        if(secBButton.isSelected())
            selections = new String[]{"2B"};
        if(thirdBButton.isSelected())
            selections = new String[]{"3B"};
        if(catcherButton.isSelected())
            selections = new String[]{"C"};
        if(cornInButton.isSelected())
            selections = new String[]{"1B", "3B"};
        if(midInButton.isSelected())
            selections = new String[]{"2B", "SS"};
        if(shortStopButton.isSelected())
            selections = new String[]{"SS"};
        if(outfielderButton.isSelected())
            selections = new String[]{"OF"};
        
        return selections;
    }
    private void fillTable(String[] sel){
            
            tempHitterList = FXCollections.observableArrayList();            
            for (int i = 0; i < allHitterList.size(); i++) {
                boolean added = false;
                String[] split = allHitterList.get(i).getPosition().split("_");
                
                for(int y = 0; y < split.length; y++){
                    for(int z = 0; z < sel.length; z++){                        
                    if(split[y].equals(sel[z])){
                        if(!added){                           
                            tempHitterList.add(allHitterList.get(i));
                            added = true;
                        } else{
                            added = false;
                        }
                    }              
                    }
                }
            }
    }
    
    public void replaceTables(String table){
        int x = availPlayersWorkspacePane.getChildren().size();
        availPlayersWorkspacePane.getChildren().remove(x-1);
        if(table.equals("allPlayerTable"))
            availPlayersWorkspacePane.getChildren().add(allPlayerTable);
        if(table.equals("hitterTable"))
            availPlayersWorkspacePane.getChildren().add(hitterTable);
        if(table.equals("pitcherTable"))
            availPlayersWorkspacePane.getChildren().add(pitcherTable);
}
    
    private void fillSelectedHitterTable(){
        replaceTables("hitterTable");
        fillTable(getTableSelection());
        autoSearchTable(searchBar.getText());
    }
    
    private String addPosition(String pos){
        String a = pos;
            if(a.contains("1B") || a.contains("3B"))
                a = a + "_" + "CI";
            if(a.contains("2B") || a.contains("SS"))
                a = a + "_" + "MI";
            if(!a.contains("P"))
                a = a + "_" + "U";
        return a;
    }
    
    private void autoSearchTable(String search){
        if(allButton.isSelected()){
            ObservableList<Player> dummy = FXCollections.observableArrayList();
        
            for (int i = 0; i < playerList.size(); i++) {
                if(compareStrBeg(playerList.get(i).getFirstName(), search) || compareStrBeg(playerList.get(i).getLastName(), search)){
                    dummy.add(playerList.get(i));
                }
                allPlayerTable.setItems(dummy);                
            }
        } else if(pitcherButton.isSelected()){            
            ObservableList<Pitcher> dummy = FXCollections.observableArrayList();
            
            for (int i = 0; i < pitcherList.size(); i++) {
                if((compareStrBeg(pitcherList.get(i).getFirstName(), search) || compareStrBeg(pitcherList.get(i).getLastName(), search))){
                    dummy.add(pitcherList.get(i));
                }
               pitcherTable.setItems(dummy);    
            }
        } else {
            ObservableList<Hitter> dummy = FXCollections.observableArrayList();
        
            for (int i = 0; i < tempHitterList.size(); i++) {
                if(compareStrBeg(tempHitterList.get(i).getFirstName(), search) || compareStrBeg(tempHitterList.get(i).getLastName(), search)){
                    dummy.add(tempHitterList.get(i));
                }
                hitterTable.setItems(dummy);    
            }
        }
    }
    
    public int searchTeamName(String search){
        int x = -1;
        for(int i = 0; i < draftTeams.size(); i++){
            if(draftTeams.get(i).getFantasyTeamName().equals(search))
                return i;
        }
        return x;
    }
    
    public Player pitcherToPlayer(Pitcher p){
        double era, whip;
        if(p.getIP() == 0){
                p.setERA(-1); p.setWHIP(-1);
            } else{
                DecimalFormat df = new DecimalFormat("#.000"); 
                p.setERA(Double.parseDouble(df.format(p.getEarnedRuns()*9/p.getIP())));
                p.setWHIP(Double.parseDouble(df.format((p.getBasesOnballs() + p.getHits())/p.getIP())));
            }

        
            Player c = new Player(p.getFirstName(), p.getLastName(), p.getTeam(), "P", p.getBirthYear(),
                    p.getNotes(), p.getWins(), p.getSaves(), p.getStrikeOuts(), 
                        p.getERA(), p.getWHIP(), p.getIP(), p.getBasesOnballs(), p.getEarnedRuns(), p.getHits());
            return c;
    }
    public Pitcher playerToPitcher(Player p){
        Pitcher x = new Pitcher(p.getFirstName(), p.getLastName(), p.getTeam(), p.getBirthYear(),
           p.getNotes(), p.getBirthNation(), p.getIP(), p.getEarnedRuns(), p.getRW(), p.getHRSV(),
            p.getHits(), (int)p.getWalks(), p.getRBIK());
        
        return x;
    }
    void sortFantasyTables(String search){
        ObservableList<Player> lel = FXCollections.observableArrayList();
        if(searchTeamName(search) != -1){//start of if
        lel.addAll(draftTeams.get(searchTeamName(search)).getStartingLineup());
        draftTeams.get(searchTeamName(search)).getStartingLineup().removeAll(lel);
        
        for(int i = 0; i < lel.size(); i++){
            if(lel.get(i).getTeamPosition().equals("C")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(lel.get(i));            
                lel.remove(i);     i--;
            }
        }for(int i = 0; i < lel.size(); i++){
            if(lel.get(i).getTeamPosition().equals("1B")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(lel.get(i));            
                lel.remove(i);   i--;
            }
        }for(int i = 0; i < lel.size(); i++){
            if(lel.get(i).getTeamPosition().equals("3B")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(lel.get(i));            
                lel.remove(i);   i--;
            }
        }for(int i = 0; i < lel.size(); i++){
            if(lel.get(i).getTeamPosition().equals("CI")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(lel.get(i));            
                lel.remove(i);   i--;
            }
        }for(int i = 0; i < lel.size(); i++){
            if(lel.get(i).getTeamPosition().equals("2B")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(lel.get(i));            
                lel.remove(i);   i--;
            }
        }for(int i = 0; i < lel.size(); i++){
            if(lel.get(i).getTeamPosition().equals("SS")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(lel.get(i));            
                lel.remove(i);   i--;
            }
        }for(int i = 0; i < lel.size(); i++){
            if(lel.get(i).getTeamPosition().equals("MI")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(lel.get(i));            
                lel.remove(i);   i--;
            }
        }for(int i = 0; i < lel.size(); i++){
            if(lel.get(i).getTeamPosition().equals("OF")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(lel.get(i));            
                lel.remove(i);   i--;
            }
        }for(int i = 0; i < lel.size(); i++){
            if(lel.get(i).getTeamPosition().equals("U")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(lel.get(i));            
                lel.remove(i);   i--;
            }
        }
        for(int i = 0; i < lel.size(); i++){
            if(lel.get(i).getTeamPosition().equals("P")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(lel.get(i));            
                lel.remove(i);   i--;
            }
        }
        }//end of if
    }
    
    private boolean compareStrBeg(String name, String search){
        if(name.length() >= search.length())
            return name.substring(0, search.length()).equalsIgnoreCase(search);
        else
            return false;
    }
    
    public int searchByName(ObservableList<Pitcher> list, String fName, String lName){
        int pos = -1;        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getFirstName().equals(fName) && list.get(i).getLastName().equals(lName)){
                return i;             
            }
        }
        return pos;
    }
    public int searchByName(String fName, String lName, ObservableList<Hitter> list){
        int pos = -1;        
        for(int i = 0; i < list.size(); i++)
            if(list.get(i).getFirstName().equals(fName) && list.get(i).getLastName().equals(lName))
                   return i;                         
        return pos;
    }
    public int searchByName(String fName, ObservableList<Player> list, String lName){
        int pos = -1;        
        for(int i = 0; i < list.size(); i++)
            if(list.get(i).getFirstName().equals(fName) && list.get(i).getLastName().equals(lName))
                   return i;                         
        return pos;
    }
    
    public void doDeletePlayer(){           
            if(allButton.isSelected()){  
                Player x = allPlayerTable.getSelectionModel().getSelectedItem();
                int y = searchByName(x.getFirstName(), playerList, x.getLastName());
                playerList.remove(y);
                autoSearchTable(searchBar.getText());

                int z = searchByName(pitcherList, x.getFirstName(), x.getLastName());
                if(x.getPosition().contains("P") && z != -1){
                    pitcherList.remove(z);
                    
                }
                
            } else if(pitcherButton.isSelected()){                
                pitcherList.remove(searchByName(pitcherList, pitcherTable.getSelectionModel().getSelectedItem().getFirstName(), pitcherTable.getSelectionModel().getSelectedItem().getLastName()));
                autoSearchTable(searchBar.getText());             
            } else{
                allHitterList.remove(searchByName(hitterTable.getSelectionModel().getSelectedItem().getFirstName(), hitterTable.getSelectionModel().getSelectedItem().getLastName(), allHitterList));
                fillTable(getTableSelection());
                autoSearchTable(searchBar.getText());
            }
    }
    
    public void doDeletePlayer(Player player){         
                Player x = player;
                int y = searchByName(x.getFirstName(), playerList, x.getLastName());
                playerList.remove(y);

                int z = searchByName(pitcherList, x.getFirstName(), x.getLastName());
                
                if(x.getPosition().contains("P") && z != -1){
                    pitcherList.remove(z);
                    
                
            }
    }
    
    public void asgnRndPlayer(){//get rndTeamPosition;
    String x = getRndPos();
    Player rnd;    
    if(!getRndPos().equals("")){
        boolean yy = true;
        do{
            yy = true;
            rnd = playerList.get((int)(Math.random()*playerList.size()));
            if(getRndPos().substring(1).equals("C")){
                String[] p = rnd.getPosition().split("_");
                for(int i = 0; i < p.length; i++){
                    if(p[i].equals("C"))
                        yy = false;
                }
            } else{
                yy = false;
            }
        }while(!rnd.getPosition().contains(getRndPos().substring(1)) || yy);
        rnd.setTeamPosition(getRndPos().substring(1));
        rnd.setSalary(1);
        rnd.setContract("S2");
        rnd.setFantasyTeam(draftTeams.get(Integer.parseInt(x.substring(0, 1))).getFantasyTeamName());
        
        draftTeams.get(Integer.parseInt(x.substring(0, 1))).setFunding(1);
        draftTeams.get(Integer.parseInt(getRndPos().substring(0, 1))).getStartingLineup().add(rnd);
           
        sortFantasyTables(draftTeams.get(Integer.parseInt(x.substring(0, 1))).getFantasyTeamName());
        
        summaryStartingList.add(rnd);
        draftSummaryList.removeAll(draftSummaryList);
        draftSummaryList.addAll(summaryStartingList);
        draftSummaryList.addAll(summaryTaxiList);
        try{
            doDeletePlayer(rnd);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    //System.out.println(playerList.get(pos).getFirstName()+playerList.get(pos).getLastName());
    }
    public String getRndPos(){
        if(draftTeams.size()>0){
            for(int i = 0; i < draftTeams.size(); i++){
                ObservableList<Player> b = draftTeams.get(i).getStartingLineup();
                if((draftTeams.get(i).getStartingLineup().size() < 23))
                    
                    
                    if(b.size() < 2 || !(b.size() >= 2 && (b.get(1).getTeamPosition().equals("C")))){
                        return i+"C";
                    }if(b.size() < 3 || !(b.size() >= 3 && (b.get(2).getTeamPosition().equals("1B"))))
                        return i+"1B";
                    if(b.size() < 4 || !(b.size() >= 4 && (b.get(3).getTeamPosition().equals("3B"))))
                        return i+"3B";
                    if(b.size() < 5 || !(b.size() >= 5 && (b.get(4).getTeamPosition().equals("CI"))))
                        return i+"CI";
                    if(b.size() < 6 || !(b.size() >= 6 && b.get(5).getTeamPosition().equals("2B")))
                        return i+"2B";
                    if(b.size() < 7 || !(b.size() >= 7 && b.get(6).getTeamPosition().equals("SS")))
                        return i+"SS";
                    if(b.size() < 8 || !(b.size() >= 8 && b.get(7).getTeamPosition().equals("MI")))
                        return i+"MI";
                    if(b.size() < 13 || !(b.size() >= 13 && b.get(12).getTeamPosition().equals("OF")))
                        return i+"OF";
                    if(b.size() < 14 || !(b.size() >= 14 && b.get(13).getTeamPosition().equals("U")))
                        return i+"U";
                    if(b.size() < 23 || !(b.size() >= 23 && b.get(22).getTeamPosition().equals("P")))
                        return i+"P";                    
                }
            }
        
        return "";
    }
    private void assignPoints(){
        if(draftTeams.size() == 0) {
        } else{
            Collections.sort(draftTeams, FantasyTeam.getRunsComparator());
            for(int i = 0; i < draftTeams.size(); i++){
                draftTeams.get(i).setTotalPts(draftTeams.size()-i);
            }
            Collections.sort(draftTeams, FantasyTeam.getHomeRunsComparator());
            for(int i = 0; i < draftTeams.size(); i++){
                draftTeams.get(i).setTotalPts(draftTeams.get(i).getTotalPoints() + draftTeams.size()-i);
            }
            Collections.sort(draftTeams, FantasyTeam.getRBIComparator());
            for(int i = 0; i < draftTeams.size(); i++){
                draftTeams.get(i).setTotalPts(draftTeams.get(i).getTotalPoints() + draftTeams.size()-i);
            }
            Collections.sort(draftTeams, FantasyTeam.getSBComparator());
            for(int i = 0; i < draftTeams.size(); i++){
                draftTeams.get(i).setTotalPts(draftTeams.get(i).getTotalPoints() + draftTeams.size()-i);
            }
            Collections.sort(draftTeams, FantasyTeam.getBAComparator());
            for(int i = 0; i < draftTeams.size(); i++){
                draftTeams.get(i).setTotalPts(draftTeams.get(i).getTotalPoints() + draftTeams.size()-i);
            }
            Collections.sort(draftTeams, FantasyTeam.getWinsComparator());
            for(int i = 0; i < draftTeams.size(); i++){
                draftTeams.get(i).setTotalPts(draftTeams.get(i).getTotalPoints() + draftTeams.size()-i);
            }
            Collections.sort(draftTeams, FantasyTeam.getSavesComparator());
            for(int i = 0; i < draftTeams.size(); i++){
                draftTeams.get(i).setTotalPts(draftTeams.get(i).getTotalPoints() + draftTeams.size()-i);
            }
            Collections.sort(draftTeams, FantasyTeam.getStrikeoutsComparator());
            for(int i = 0; i < draftTeams.size(); i++){
                draftTeams.get(i).setTotalPts(draftTeams.get(i).getTotalPoints() + draftTeams.size()-i);
            }
            Collections.sort(draftTeams, FantasyTeam.getERAComparator());
            for(int i = 0; i < draftTeams.size(); i++){
                draftTeams.get(i).setTotalPts(draftTeams.get(i).getTotalPoints() + draftTeams.size()-i);
            }
            Collections.sort(draftTeams, FantasyTeam.getWHIPComparator());
            for(int i = 0; i < draftTeams.size(); i++){
                draftTeams.get(i).setTotalPts(draftTeams.get(i).getTotalPoints() + draftTeams.size()-i);
            }
            
            ObservableList<FantasyTeam> a = FXCollections.observableArrayList();
            a.addAll(draftTeams);
            draftTeams.removeAll(draftTeams);
            draftTeams.addAll(a);
        }
    }
    
    private void initEventHandlers() throws IOException {
        newDraftButton.setOnAction(e -> {
            NewDraftDialog a = new NewDraftDialog(primaryStage);
            a.showDialog();
            wdkPane.setCenter(fantasyTeamWorkspacePane);
        });
        homeButton.setOnAction(e -> {
         wdkPane.setCenter(availPlayersWorkspacePane);
        });
        fantasyStandingButton.setOnAction(e -> {
            wdkPane.setCenter(fantasyStandingWorkspacePane);
            assignPoints();
        });
        fantasyTeamsButton.setOnAction(e -> {
            wdkPane.setCenter(fantasyTeamWorkspacePane);
            
        });        
        draftButton.setOnAction(e -> {
            wdkPane.setCenter(draftSummaryWorkspacePane);

        });
        MLBTeamButton.setOnAction(e -> {
            wdkPane.setCenter(mlbTeamsWorkspacePane);
            
        });
        
        playButton.setOnAction(e ->{
            timeline = new Timeline(new KeyFrame(
                Duration.millis(500),
                ae -> asgnRndPlayer()));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
        });
        pauseButton.setOnAction(e ->{
            timeline.stop();
        });
        
        draftAddButtion.setOnAction(e -> {
            AddFantasyTeamDialog a = new AddFantasyTeamDialog(primaryStage, this, "Add Fantasy Team");
            a.showDialog();
        });
        draftRemoveButtion.setOnAction(e -> {
            try{
                ObservableList<Player> pla = draftTeams.get(searchTeamName(fantasyTeamComboBox.getValue().toString())).getStartingLineup();
                    playerList.addAll(pla);
                    for(int i = 0; i < pla.size(); i++){
                        if(!pla.get(i).getPosition().contains("P")){
                            Hitter zz = new Hitter(pla.get(i).getFirstName(), pla.get(i).getLastName(), pla.get(i).getTeam(), 
                              pla.get(i).getBirthYear(), pla.get(i).getNotes(), pla.get(i).getBirthNation(), pla.get(i).getPosition(), 
                                    pla.get(i).getAB(), pla.get(i).getRW(), pla.get(i).getHits(), pla.get(i).getHRSV(), pla.get(i).getRBIK(), 
                                    (int)(pla.get(i).getSBERA()));
                            allHitterList.add(zz);
                        } else{
                            Pitcher xy = playerToPitcher(pla.get(i));
                            pitcherList.add(xy);
                        }
                        int idx = -1;
                        for(int z = 0; z < draftSummaryList.size(); z++){
                            if(draftSummaryList.get(z).getFirstName().equals(pla.get(i).getFirstName())
                                    && draftSummaryList.get(z).getLastName().equals(pla.get(i).getLastName())){
                                idx = z; 
                            }
                        }
                        if(idx < summaryStartingList.size())
                            summaryStartingList.remove(idx);
                        else
                            summaryTaxiList.remove(idx-summaryStartingList.size());
                        draftSummaryList.removeAll(draftSummaryList);
                        draftSummaryList.addAll(summaryStartingList);
                        draftSummaryList.addAll(summaryTaxiList);

                    }   
                        pla.removeAll(pla);
                        draftTeams.remove(searchTeamName(fantasyTeamComboBox.getValue().toString()));
                        
                        for(int i = 0; i < this.fantasyTeamList.size(); i++){
                            if(fantasyTeamList.get(i).equals(fantasyTeamComboBox.getValue())){                                
                                fantasyTeamList.remove(i);                                   
                                break;
                            }
                        }
                        fantasyTeamComboBox.setValue(null);
                        
            } catch(NullPointerException ex){
                System.out.println(ex.getMessage());
            }
        });
        
        draftEditButtion.setOnAction(e -> {
            AddFantasyTeamDialog a = new AddFantasyTeamDialog(primaryStage, this, "Edit Fantasy Team");
            a.showDialog();
        });
          
        addButton.setOnAction(e -> {
            try {
                AddPlayerDialog a = new AddPlayerDialog(primaryStage, this);
                a.showAddPlayerDialog();
            } catch (IOException ex) {
                Logger.getLogger(WDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        selectPlayer.setOnAction(e ->{
          asgnRndPlayer();  
        });
        
        fantasyTeamComboBox.setOnAction(e ->{
            try{
            int x = searchTeamName(fantasyTeamComboBox.getValue().toString());
            try{
                startingLineupTable.setItems(draftTeams.get(x).getStartingLineup());
            } catch(Exception z){
                System.out.println(z.getMessage());
            }
            sortFantasyTables(fantasyTeamComboBox.getValue().toString());
        }catch(NullPointerException as){
                as.getMessage();    
        }});
        
        mlbTeamsComboBox.setOnAction(e -> {
            mlbPlayers = FXCollections.observableArrayList();
            for (Player playerList1 : playerList) {
                if (playerList1.getTeam().equals(mlbTeamsComboBox.getValue())) {
                    mlbPlayers.add(playerList1);
                    Collections.sort(mlbPlayers, playerList1);
                }
            }
            
            
            mlbTable.setItems(mlbPlayers);
        });
        
        
        allButton.setOnAction(e -> {
            replaceTables("allPlayerTable"); 
            autoSearchTable(searchBar.getText());
        });
        firstBButton.setOnAction(e -> {
            fillSelectedHitterTable();
        });
        secBButton.setOnAction(e -> {
            fillSelectedHitterTable();
        });
        thirdBButton.setOnAction(e -> {
            fillSelectedHitterTable();
        });
        catcherButton.setOnAction(e -> {
            fillSelectedHitterTable();
        });
        cornInButton.setOnAction(e -> {
            fillSelectedHitterTable();
        });
        midInButton.setOnAction(e -> {
            fillSelectedHitterTable();
        });
        shortStopButton.setOnAction(e -> {
            fillSelectedHitterTable();
        });
        outfielderButton.setOnAction(e -> {
            fillSelectedHitterTable();
        });
        utilityButton.setOnAction(e -> {
            fillSelectedHitterTable();
        });
        pitcherButton.setOnAction(e -> {
            replaceTables("pitcherTable");
            autoSearchTable(searchBar.getText());
        });
        searchBar.setOnKeyReleased(e -> {
            autoSearchTable(searchBar.getText());
        });
        
        allPlayerTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Player b = allPlayerTable.getSelectionModel().getSelectedItem();
                EditPlayerDialog x = new EditPlayerDialog(primaryStage, b, this, true);
                x.showEditPlayerDialog();
            }
        });
        hitterTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                EditPlayerDialog x = new EditPlayerDialog(primaryStage, hitterTable.getSelectionModel().getSelectedItem(), this, true);
                x.showEditPlayerDialog();
            }
        });
        pitcherTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                EditPlayerDialog x = new EditPlayerDialog(primaryStage, pitcherTable.getSelectionModel().getSelectedItem(), this, true);
                x.showEditPlayerDialog();
            }
        });
        startingLineupTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                EditPlayerDialog x = new EditPlayerDialog(primaryStage, startingLineupTable.getSelectionModel().getSelectedItem(), this, false);
                x.showEditPlayerDialog();
            }
        });
        
        removeButton.setOnAction(e -> {
            doDeletePlayer();
        });
        
        //MAKE IT SO IT EDITS THE THE OTHER TABLE AS WELLS
        notesAllColumn.setOnEditCommit(
            new EventHandler<CellEditEvent<Player, String>>() {
        @Override
        public void handle(CellEditEvent<Player, String> t) {
            t.getTableView().getItems().get(t.getTablePosition().getRow()).setNotes(t.getNewValue());
            
        }});
        notesHitColumn.setOnEditCommit(
            new EventHandler<CellEditEvent<Player, String>>() {
        @Override
        public void handle(CellEditEvent<Player, String> t) {
            (t.getTableView().getItems().get(t.getTablePosition().getRow())).setNotes(t.getNewValue());                    
        }});
        notesPitchColumn.setOnEditCommit(
            new EventHandler<CellEditEvent<Player, String>>() {
        @Override
        public void handle(CellEditEvent<Player, String> t) {
            (t.getTableView().getItems().get(t.getTablePosition().getRow())).setNotes(t.getNewValue());                    
        }});
    }
}
