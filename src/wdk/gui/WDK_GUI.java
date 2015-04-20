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
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javax.json.JsonObject;
import javax.json.JsonValue;
import properties_manager.PropertiesManager;
import wdk.data.Hitter;
import wdk.data.Pitcher;
import wdk.data.Player;

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
    Label courseHeadingLabel;
    
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
    
    ObservableList<Hitter> hittersList;
    ObservableList<Pitcher> pitcherList;
    ObservableList<Player> playerList;
    
    //THE TABLE COLUMNS
    TableColumn firstColumn;    TableColumn lastColumn;         TableColumn proTeamColumn;
    TableColumn positionColumn; TableColumn birthyearColumn;    TableColumn winsColumn;
    TableColumn savesColumn;    TableColumn strikeoutsColumn;   TableColumn earnedRunsAvgColumn;
    TableColumn whipColumn;     TableColumn estimValueColumn;   TableColumn notesColumn;
    TableColumn runsColumn;     TableColumn homerunsColumn;     TableColumn runsBattedInColumn;
    TableColumn battingAverageColumn;                           TableColumn runsPerWinsColumn;
    TableColumn homePerSaveColumn;                              TableColumn runsBattedInPerOutColumn;
    TableColumn stealsPerERAColumn;                             TableColumn avgPerWhipColumn;
    
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
        initFileToolbar();
        initBottomToolbar();
        initWindow(windowTitle);
        initAllPlayerTable();
        initHitterTable();
        initPitcherTable();
        initFantasyTeamsWorkspace();
        initAvailablePlayersWorkspace();
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
        topWorkspaceSplitPane = new SplitPane();
        fantasyTeamWorkspacePane = new VBox();
        
        courseHeadingLabel = initChildLabel(fantasyTeamWorkspacePane, WDK_PropertyType.FANTASY_TEAM_HEADING_LABEL, CLASS_HEADING_LABEL);
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
        utilityButton= initRadioButton(radioButtonRow, WDK_PropertyType.UTILITY_LABEL, group);
        pitcherButton= initRadioButton(radioButtonRow, WDK_PropertyType.PITCHER_LABEL, group);
 
        
        
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
    private void initPitcherTable() throws IOException  {
        VBox playersBox = new VBox();
        pitcherTable = new TableView();
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
        notesColumn = new TableColumn(COL_NOTES);
        
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
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
       
        pitcherList = FXCollections.observableArrayList();
        
        try {
            FileController a = new FileController();
            JsonArray list = a.loadPitchers(this).getJsonArray("Pitchers");
            
            for (int i = 0; i < list.size(); i++) {
                String fName = list.getJsonObject(i).getString("FIRST_NAME");
                String lName = list.getJsonObject(i).getString("LAST_NAME");
                String team = list.getJsonObject(i).getString("TEAM");
                int year = Integer.parseInt(list.getJsonObject(i).getString("YEAR_OF_BIRTH"));
                String note = list.getJsonObject(i).getString("NOTES");
                String nation = list.getJsonObject(i).getString("NATION_OF_BIRTH");
                double ip = Double.parseDouble(list.getJsonObject(i).getString("IP"));
                int earnedRuns = Integer.parseInt(list.getJsonObject(i).getString("ER"));
                int wins = Integer.parseInt(list.getJsonObject(i).getString("W"));
                int saves = Integer.parseInt(list.getJsonObject(i).getString("SV"));
                int hits = Integer.parseInt(list.getJsonObject(i).getString("H"));
                int balls = Integer.parseInt(list.getJsonObject(i).getString("BB"));
                int outs = Integer.parseInt(list.getJsonObject(i).getString("K")); 
                Pitcher b = new Pitcher(fName, lName, team, year, note, nation, ip, earnedRuns,
                    wins, saves, hits, balls, outs);
                pitcherList.add(b);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(WDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        pitcherTable.getColumns().addAll(firstColumn, lastColumn, proTeamColumn, positionColumn, 
                birthyearColumn, winsColumn, savesColumn, strikeoutsColumn, earnedRunsAvgColumn,
                    whipColumn, estimValueColumn, notesColumn);
        pitcherTable.setItems(pitcherList);
        
    }
    
    private void fillTable(String[] sel){
         try {
              hittersList = FXCollections.observableArrayList();
            FileController a = new FileController();
            JsonArray list = a.loadHitters(this).getJsonArray("Hitters");
            
            for (int i = 0; i < list.size(); i++) {
                String fName = list.getJsonObject(i).getString("FIRST_NAME");
                String lName = list.getJsonObject(i).getString("LAST_NAME");
                String team = list.getJsonObject(i).getString("TEAM");
                int year = Integer.parseInt(list.getJsonObject(i).getString("YEAR_OF_BIRTH"));
                String note = list.getJsonObject(i).getString("NOTES");
                String nation = list.getJsonObject(i).getString("NATION_OF_BIRTH");
                String positions = list.getJsonObject(i).getString("QP");
                int ab = Integer.parseInt(list.getJsonObject(i).getString("AB"));
                int hits = Integer.parseInt(list.getJsonObject(i).getString("H"));
                int runs = Integer.parseInt(list.getJsonObject(i).getString("R"));
                int homeRuns = Integer.parseInt(list.getJsonObject(i).getString("SB"));
                int runsBattedIn = Integer.parseInt(list.getJsonObject(i).getString("RBI"));
                int stolenBases = Integer.parseInt(list.getJsonObject(i).getString("SB"));
               
                boolean added = false;
                String[] split = positions.split("_");
                
                for(int y = 0; y < split.length; y++){
                    for(int z = 0; z < sel.length; z++){
                        
                    if(split[y].equals(sel[z])){
                        if(!added){
                            Hitter b = new Hitter(fName, lName, team, year, note, nation, positions,
                          ab, runs, hits, homeRuns, runsBattedIn, stolenBases);
                            hittersList.add(b);
                            added = true;
                        } else{
                            added = false;
                        }
                    }              
                    }
                }
                
                hitterTable.setItems(hittersList);                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(WDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void initHitterTable(){
        //VBox playersBox = new VBox();
        hitterTable = new TableView();
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
        notesColumn = new TableColumn(COL_NOTES);
        
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        proTeamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("positions"));
        birthyearColumn.setCellValueFactory(new PropertyValueFactory<>("birthYear"));
        runsColumn.setCellValueFactory(new PropertyValueFactory<>("runs"));
        homerunsColumn.setCellValueFactory(new PropertyValueFactory<>("homeRuns"));
        runsBattedInColumn.setCellValueFactory(new PropertyValueFactory<>("runsBattedIn"));
        battingAverageColumn.setCellValueFactory(new PropertyValueFactory<>("BA"));
        estimValueColumn.setCellValueFactory(new PropertyValueFactory<>("TEAM"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        
        hittersList = FXCollections.observableArrayList();
        
        try {
            FileController a = new FileController();
            JsonArray list = a.loadHitters(this).getJsonArray("Hitters");
            
            for (int i = 0; i < list.size(); i++) {
                String fName = list.getJsonObject(i).getString("FIRST_NAME");
                String lName = list.getJsonObject(i).getString("LAST_NAME");
                String team = list.getJsonObject(i).getString("TEAM");
                int year = Integer.parseInt(list.getJsonObject(i).getString("YEAR_OF_BIRTH"));
                String note = list.getJsonObject(i).getString("NOTES");
                String nation = list.getJsonObject(i).getString("NATION_OF_BIRTH");
                String positions = list.getJsonObject(i).getString("QP");
                int ab = Integer.parseInt(list.getJsonObject(i).getString("AB"));
                int hits = Integer.parseInt(list.getJsonObject(i).getString("H"));
                int runs = Integer.parseInt(list.getJsonObject(i).getString("R"));
                int homeRuns = Integer.parseInt(list.getJsonObject(i).getString("SB"));
                int runsBattedIn = Integer.parseInt(list.getJsonObject(i).getString("RBI"));
                int stolenBases = Integer.parseInt(list.getJsonObject(i).getString("SB"));
                Hitter b = new Hitter(fName, lName, team, year, note, nation, positions,
                    ab, runs, hits, homeRuns, runsBattedIn, stolenBases);
                hittersList.add(b);
                hitterTable.setItems(hittersList);                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(WDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        hitterTable.getColumns().addAll(firstColumn, lastColumn, proTeamColumn, positionColumn, 
                birthyearColumn, runsColumn, homerunsColumn, runsBattedInColumn, 
                    battingAverageColumn, estimValueColumn, notesColumn);
       
    }
    private void initAllPlayerTable(){
        //VBox playersBox = new VBox();
        allPlayerTable = new TableView();
        //playersBox.getChildren().add(allPlayerTable);
        
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
        notesColumn = new TableColumn(COL_NOTES);
        
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
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        
        playerList = FXCollections.observableArrayList();
        
        try {
            FileController a = new FileController();
            JsonArray list = a.loadHitters(this).getJsonArray("Hitters");
            
            for (int i = 0; i < list.size(); i++) {
                String fName = list.getJsonObject(i).getString("FIRST_NAME");
                String lName = list.getJsonObject(i).getString("LAST_NAME");
                String team = list.getJsonObject(i).getString("TEAM");
                int year = Integer.parseInt(list.getJsonObject(i).getString("YEAR_OF_BIRTH"));
                String note = list.getJsonObject(i).getString("NOTES");
                String positions = list.getJsonObject(i).getString("QP");
                int ab = Integer.parseInt(list.getJsonObject(i).getString("AB"));
                int hits = Integer.parseInt(list.getJsonObject(i).getString("H"));
                int runs = Integer.parseInt(list.getJsonObject(i).getString("R"));
                int homeRuns = Integer.parseInt(list.getJsonObject(i).getString("HR"));
                int runsBattedIn = Integer.parseInt(list.getJsonObject(i).getString("RBI"));
                double stolenBases = Integer.parseInt(list.getJsonObject(i).getString("SB"));
                
                double sb;
                if(ab == 0){
                    sb = -1;
                } else{
                    DecimalFormat df = new DecimalFormat("#.000"); 
                    sb = Double.parseDouble(df.format(1.0*hits/ab));
                }
                
                Player b = new Player(fName, lName, team, positions, year, note, 
                        runs, homeRuns, runsBattedIn, stolenBases, sb);
                playerList.add(b);
            }
            JsonArray pList = a.loadPitchers(this).getJsonArray("Pitchers");
            for (int i = 0; i < pList.size(); i++) {
                String fName = pList.getJsonObject(i).getString("FIRST_NAME");
                String lName = pList.getJsonObject(i).getString("LAST_NAME");
                String team = pList.getJsonObject(i).getString("TEAM");
                int year = Integer.parseInt(pList.getJsonObject(i).getString("YEAR_OF_BIRTH"));
                String note = pList.getJsonObject(i).getString("NOTES");
                double ip = Double.parseDouble(pList.getJsonObject(i).getString("IP"));
                int earnedRuns = Integer.parseInt(pList.getJsonObject(i).getString("ER"));
                int wins = Integer.parseInt(pList.getJsonObject(i).getString("W"));
                int saves = Integer.parseInt(pList.getJsonObject(i).getString("SV"));
                int hits = Integer.parseInt(pList.getJsonObject(i).getString("H"));
                int balls = Integer.parseInt(pList.getJsonObject(i).getString("BB"));
                int outs = Integer.parseInt(pList.getJsonObject(i).getString("K")); 
                
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
            
        } catch (IOException ex) {
            Logger.getLogger(WDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        allPlayerTable.getColumns().addAll(firstColumn, lastColumn, proTeamColumn, positionColumn, 
                birthyearColumn, runsPerWinsColumn, homePerSaveColumn, runsBattedInPerOutColumn, 
                    stealsPerERAColumn, avgPerWhipColumn, estimValueColumn, notesColumn);
     }
    
    private void replaceTables(TableView table){
        int x = availPlayersWorkspacePane.getChildren().size();
        availPlayersWorkspacePane.getChildren().remove(x-1);
        availPlayersWorkspacePane.getChildren().add(table);

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
                if(compareStrBeg(pitcherList.get(i).getFirstName(), search) || compareStrBeg(pitcherList.get(i).getLastName(), search)){
                    dummy.add(pitcherList.get(i));
                }
                pitcherTable.setItems(dummy);                
            }
        } else {
            ObservableList<Hitter> dummy = FXCollections.observableArrayList();
        
            for (int i = 0; i < hittersList.size(); i++) {
                if(compareStrBeg(hittersList.get(i).getFirstName(), search) || compareStrBeg(hittersList.get(i).getLastName(), search)){
                    dummy.add(hittersList.get(i));
                }
                hitterTable.setItems(dummy);                
            }
        }
    }
    
    private boolean compareStrBeg(String name, String search){
        if(name.length() >= search.length())
            return name.substring(0, search.length()).equalsIgnoreCase(search);
        else
            return false;
    }
    
    private void editNotes(){   
        
        if(allButton.isSelected()){
            Player asgn = allPlayerTable.getSelectionModel().getSelectedItem();
            NotesDialog a = new NotesDialog(primaryStage, asgn);
            a.showEditLectureDialog();
            allPlayerTable.getColumns().get(allPlayerTable.getColumns().size()-1).setVisible(false);
            allPlayerTable.getColumns().get(allPlayerTable.getColumns().size()-1).setVisible(true);
        } else if(pitcherButton.isSelected()){             
            Pitcher asgn = pitcherTable.getSelectionModel().getSelectedItem();
            NotesDialog a = new NotesDialog(primaryStage, asgn);
            a.showEditLectureDialog();
            pitcherTable.getColumns().get(pitcherTable.getColumns().size()-1).setVisible(false);
            pitcherTable.getColumns().get(pitcherTable.getColumns().size()-1).setVisible(true);
        } else {
            Hitter asgn = hitterTable.getSelectionModel().getSelectedItem();
            NotesDialog a = new NotesDialog(primaryStage, asgn);
            a.showEditLectureDialog();
            hitterTable.getColumns().get(hitterTable.getColumns().size()-1).setVisible(false);
            hitterTable.getColumns().get(hitterTable.getColumns().size()-1).setVisible(true);
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
        
        allButton.setOnAction(e -> {
            replaceTables(allPlayerTable); 
            autoSearchTable(searchBar.getText());
        });
        firstBButton.setOnAction(e -> {
            replaceTables(hitterTable);
            fillTable(new String[] {"1B"});autoSearchTable(searchBar.getText());
        });
        secBButton.setOnAction(e -> {
            replaceTables(hitterTable);
            fillTable(new String[] {"2B"});autoSearchTable(searchBar.getText());
        });
        thirdBButton.setOnAction(e -> {
            replaceTables(hitterTable);
            fillTable(new String[] {"3B"});autoSearchTable(searchBar.getText());
        });
        catcherButton.setOnAction(e -> {
            replaceTables(hitterTable);
            fillTable(new String[] {"C"});autoSearchTable(searchBar.getText());
        });
        cornInButton.setOnAction(e -> {
            replaceTables(hitterTable);
            fillTable(new String[] {"1B", "3B"});autoSearchTable(searchBar.getText());
        });
        midInButton.setOnAction(e -> {
            replaceTables(hitterTable);
            fillTable(new String[] {"2B", "SS"});autoSearchTable(searchBar.getText());
        });
        shortStopButton.setOnAction(e -> {
            replaceTables(hitterTable);
            fillTable(new String[] {"SS"});autoSearchTable(searchBar.getText());
        });
        outfielderButton.setOnAction(e -> {
            replaceTables(hitterTable);
            fillTable(new String[] {"OF"});
            autoSearchTable(searchBar.getText());
        });
        utilityButton.setOnAction(e -> {
            replaceTables(hitterTable);
            fillTable(new String[] {"1B", "2B", "3B", "SS", "OF", "C"});
            autoSearchTable(searchBar.getText());
        });
        pitcherButton.setOnAction(e -> {
            replaceTables(pitcherTable);
            autoSearchTable(searchBar.getText());
        });
        searchBar.setOnKeyReleased(e -> {
            autoSearchTable(searchBar.getText());
        });
        hitterTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                editNotes();
            }
        });
        allPlayerTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                editNotes();
            }
        });
        pitcherTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                editNotes();
            }
        });
    }
}
