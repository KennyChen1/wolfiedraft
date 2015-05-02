/*
 * Kenny Chen
 */
package wdk.gui;

import static wolfieballdraftkit.WDK_StartupConstants.*;
import wolfieballdraftkit.WDK_PropertyType;
import wdk.controller.FileController;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    TableColumn battingAverageColumn;                           TableColumn runsPerWinsColumn;
    TableColumn homePerSaveColumn;                              TableColumn runsBattedInPerOutColumn;
    TableColumn stealsPerERAColumn;                             TableColumn avgPerWhipColumn;
    
    TableColumn teamPositionColumnF; 
    TableColumn firstColumnF;    TableColumn lastColumnF;         TableColumn proTeamColumnF;
    TableColumn positionColumnF; TableColumn runsPerWinsColumnF;  TableColumn homePerSaveColumnF;       
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
    
    SplitPane mlbTeamsSplitPane;
    VBox mlbTeamsWorkspacePane;        
    Label mlbTeamsHeadingLabel;
    
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
        initFantasyTeamsWorkspace();
        initAvailablePlayersWorkspace();
        initFantasyStandingWorkspace();
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
        runsPerWinsColumnF = new TableColumn(COL_RPW);
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
        
        runsPerWinsColumnF.setCellValueFactory(new PropertyValueFactory<>("RW"));
        runsPerWinsColumnF.setSortable(false);
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
                runsPerWinsColumnF, homePerSaveColumnF, runsBattedInPerOutColumnF, 
                    stealsPerERAColumnF, avgPerWhipColumnF, estimValueColumnF);
        startingLineupTable.getColumns().addAll(teamPositionColumnF, firstColumnF, lastColumnF, 
                proTeamColumnF, positionColumnF, runsPerWinsColumnF, homePerSaveColumnF, 
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
        fantasyStandingSplitPane = new SplitPane();
        fantasyStandingWorkspacePane = new VBox();        
        fantasyStandingHeadingLabel = initChildLabel(fantasyStandingWorkspacePane, WDK_PropertyType.FANTASY_STANDING_HEADING_LABEL, CLASS_HEADING_LABEL);
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
                    wins, saves, hits,balls, outs);
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
        runsPerWinsColumn = new TableColumn(COL_RPW);
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
        
        runsPerWinsColumn.setCellValueFactory(new PropertyValueFactory<>("RW"));
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
                birthyearColumn, runsPerWinsColumn, homePerSaveColumn, runsBattedInPerOutColumn, 
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
    private void sortFantasyTables(String search){
        ObservableList<Player> a = FXCollections.observableArrayList();
        a.addAll(draftTeams.get(searchTeamName(search)).getStartingLineup());
        draftTeams.get(searchTeamName(search)).getStartingLineup().removeAll(a);
        for(int i = 0; i < a.size(); i++){
            if(a.get(i).getTeamPosition().equals("C")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(a.get(i));            
                a.remove(i);     i--;
            }
        }for(int i = 0; i < a.size(); i++){
            if(a.get(i).getTeamPosition().equals("1B")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(a.get(i));            
                a.remove(i);   i--;
            }
        }for(int i = 0; i < a.size(); i++){
            if(a.get(i).getTeamPosition().equals("3B")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(a.get(i));            
                a.remove(i);   i--;
            }
        }for(int i = 0; i < a.size(); i++){
            if(a.get(i).getTeamPosition().equals("CI")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(a.get(i));            
                a.remove(i);   i--;
            }
        }for(int i = 0; i < a.size(); i++){
            if(a.get(i).getTeamPosition().equals("2B")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(a.get(i));            
                a.remove(i);   i--;
            }
        }for(int i = 0; i < a.size(); i++){
            if(a.get(i).getTeamPosition().equals("SS")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(a.get(i));            
                a.remove(i);   i--;
            }
        }for(int i = 0; i < a.size(); i++){
            if(a.get(i).getTeamPosition().equals("MI")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(a.get(i));            
                a.remove(i);   i--;
            }
        }for(int i = 0; i < a.size(); i++){
            if(a.get(i).getTeamPosition().equals("OF")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(a.get(i));            
                a.remove(i);   i--;
            }
        }for(int i = 0; i < a.size(); i++){
            if(a.get(i).getTeamPosition().equals("U")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(a.get(i));            
                a.remove(i);   i--;
            }
        }
        for(int i = 0; i < a.size(); i++){
            if(a.get(i).getTeamPosition().equals("P")){
                draftTeams.get(searchTeamName(search)).getStartingLineup().add(a.get(i));            
                a.remove(i);   i--;
            }
        }
        
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
                if(x.getPosition().contains("P"))
                    pitcherList.remove(z);
                pitcherList.remove(searchByName(pitcherList, x.getFirstName(), x.getLastName()));
                
            } else if(pitcherButton.isSelected()){                
                pitcherList.remove(searchByName(pitcherList, pitcherTable.getSelectionModel().getSelectedItem().getFirstName(), pitcherTable.getSelectionModel().getSelectedItem().getLastName()));
                autoSearchTable(searchBar.getText());             
            } else{
                allHitterList.remove(searchByName(hitterTable.getSelectionModel().getSelectedItem().getFirstName(), hitterTable.getSelectionModel().getSelectedItem().getLastName(), allHitterList));
                fillTable(getTableSelection());
                autoSearchTable(searchBar.getText());
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
            wdkPane.setCenter(fantasyTeamWorkspacePane);
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
        
        draftAddButtion.setOnAction(e -> {
            AddFantasyTeamDialog a = new AddFantasyTeamDialog(primaryStage, this, "Add Fantasy Team");
            a.showDialog();
        });
        draftRemoveButtion.setOnAction(e -> {
            try{
                fantasyTeamList.remove(fantasyTeamComboBox.getSelectionModel().getSelectedItem().toString());
            }catch(Exception x){
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
        
        fantasyTeamComboBox.setOnAction(e ->{            
            int x = searchTeamName(fantasyTeamComboBox.getValue().toString());
            startingLineupTable.setItems(draftTeams.get(x).getStartingLineup());
            sortFantasyTables(fantasyTeamComboBox.getValue().toString());
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
