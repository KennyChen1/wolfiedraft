/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wolfieballdraftkit;

import static wolfieballdraftkit.WDK_PropertyType.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import wdk.gui.WDK_GUI;
import static wolfieballdraftkit.WDK_StartupConstants.*;
import xml_utilities.InvalidXMLFileFormatException;

/**
 *
 * @author Kenny1
 */
public class WolfieballDraftKit extends Application {
    
    WDK_GUI gui;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       
        boolean success = loadProperties();

        if (success) {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String appTitle = props.getProperty(PROP_APP_TITLE);
            
            try {
                // INITIALIZE THE USER INTERFACE COMPONENTS
                gui = new WDK_GUI(primaryStage);
                gui.initGUI(appTitle); 
                
            }
            catch(Exception ioe) {
                //eH = ErrorHandler.getErrorHandler();
                //eH.handlePropertiesFileError();
            }
        }

    }

    /**
     * Loads this application's properties file, which has a number of settings
     * for initializing the user interface.
     * 
     * @return true if the properties file was loaded successfully, false otherwise.
     */
    /*public boolean loadProperties() {
        try {
            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
            props.loadProperties(PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            return true;
       } catch (InvalidXMLFileFormatException ixmlffe) {
            // SOMETHING WENT WRONG INITIALIZING THE XML FILE
            ErrorHandler eH = ErrorHandler.getErrorHandler();
            eH.handlePropertiesFileError();
            return false;
        }        
    }*/
    
    public boolean loadProperties() {
        try {
            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
            props.loadProperties(PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            return true;
       } catch (InvalidXMLFileFormatException ixmlffe) {
            // SOMETHING WENT WRONG INITIALIZING THE XML FILE
            //ErrorHandler eH = ErrorHandler.getErrorHandler();
            //eH.handlePropertiesFileError();
            return false;
        }        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
