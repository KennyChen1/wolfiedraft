package wolfieballdraftkit;

/**
 * This class stores all the constants used by the Draft Site Builder application 
 * at startup, which means before the user interface is even loaded. This mostly 
 * means how to find files for initializing the application, like properties.xml.
 * 
 * @author Richard McKenna
 */
public class WDK_StartupConstants
{
    // WE NEED THESE CONSTANTS JUST TO GET STARTED
    // LOADING SETTINGS FROM OUR XML FILES
    public static final String PROPERTIES_FILE_NAME = "properties.xml";
    public static final String PROPERTIES_SCHEMA_FILE_NAME = "properties_schema.xsd";    
    public static final String PATH_DATA = "./data/";
    public static final String PATH_HITTERS = "./data/Hitters.json";
    public static final String PATH_PITCHERS = "./data/Pitchers.json";
    public static final String PATH_PRO_TEAM = "./data/teams.json";
    public static final String PATH_DRAFTS = PATH_DATA + "drafts/";
    public static final String PATH_IMAGES = "images/";
    public static final String PATH_CSS = "wdk/css";
    public static final String PATH_SITES = "sites/";
    public static final String PATH_BASE = PATH_SITES + "base/";
    public static final String PATH_EMPTY = ".";

    // THESE ARE THE DATA FILES WE WILL LOAD AT STARTUP
    public static final String JSON_FILE_PATH_SUBJECTS = PATH_DATA + "subjects.json";
    public static final String JSON_FILE_PATH_LAST_INSTRUCTOR = PATH_DATA + "last_instructor.json";
    
    // ERRO MESSAGE ASSOCIATED WITH PROPERTIES FILE LOADING ERRORS
    public static String PROPERTIES_FILE_ERROR_MESSAGE = "Error Loading properties.xml";

    // ERROR DIALOG CONTROL
    public static String CLOSE_BUTTON_LABEL = "Close";
}