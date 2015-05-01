package wolfieballdraftkit;

/**
 * These are properties that are to be loaded from properties.xml. They
 * will provide custom labels and other UI details for our Draft Site Builder
 * application. The reason for doing this is to swap out UI text and icons
 * easily without having to touch our code. It also allows for language
 * independence.
 * 
 * @author Richard McKenna
 */
public enum WDK_PropertyType {
        // LOADED FROM properties.xml
        PROP_APP_TITLE,
        
        // APPLICATION ICONS
        NEW_DRAFT_ICON,
        LOAD_DRAFT_ICON,
        SAVE_DRAFT_ICON,
        VIEW_SCHEDULE_ICON,
        EXPORT_PAGE_ICON,
        DELETE_ICON,
        EXIT_ICON,
        ADD_ICON,
        MINUS_ICON,
        EDIT_ICON,
        
        // APPLICATION TOOLTIPS FOR BUTTONS
        NEW_DRAFT_TOOLTIP,
        LOAD_DRAFT_TOOLTIP,
        SAVE_DRAFT_TOOLTIP,
        VIEW_SCHEDULE_TOOLTIP,
        EXPORT_PAGE_TOOLTIP,
        DELETE_TOOLTIP,
        EXIT_TOOLTIP,
        ADD_ITEM_TOOLTIP,
        REMOVE_ITEM_TOOLTIP,
        ADD_TOOLTIP,
        REMOVE_TOOLTIP,
        ADD_FANTASY_TOOLTIP,
        REMOVE_FANTASY_TOOLTIP,
        EDIT_FANTASY_TOOLTIP,

        // FOR THE BOTTOM DRAFT BUTTOMS
        PLAYER_HOME_ICON,
        FANTASY_TEAMS_ICON,
        FANTASY_STANDING_ICON,
        DRAFT_ICON,
        MLB_TEAM_ICON,
        
        // FOR THE BOTTOM BUTTON TOOLTIPS
        PLAYERS_TOOLTIP,
        FANTASY_TEAMS_TOOLTIP,
        FANTASY_STANDING_TOOLTIP,
        DRAFT_TOOLTIP,
        MLB_TEAM_TOOLTIP,
        
        // FOR DRAFT EDIT WORKSPACE
        FANTASY_TEAM_HEADING_LABEL,
        FANTASY_STANDING_HEADING_LABEL,
        AVAILABLE_PLAYERS_HEADING_LABEL,
        DRAFT_SUMMARY_HEADING_LABEL,
        MLB_TEAMS_HEADING_LABEL,
        SEARCH_HEADING_LABEL,
        FANTASY_COMBO_LABEL,
        DRAFT_NAME_LABEL,
        STARTING_LINEUP_LABEL,
        TAXI_SQUAD_LABEL,
        
        //RADIO BUTTONS LABEL
        ALL_LABEL,
	FIRST_BASEMAN_LABEL,
        SECOND_BASEMAN_LABEL,
        THIRD_BASEMAN_LABEL,
	CATCHER_LABEL,
	CORNER_INFIELDER_LABEL,
	MID_INFIELD_LABEL,
        SHORTSTOP_LABEL,
	OUTFIELDER_LABEL,
	UTILITY_LABEL,
        PITCHER_LABEL,
       
        // PAGE CHECKBOX LABELS
        INDEX_CHECKBOX_LABEL,
        SYLLABUS_CHECKBOX_LABEL,
        SCHEDULE_CHECKBOX_LABEL,
        HWS_CHECKBOX_LABEL,
        PROJECTS_CHECKBOX_LABEL,
                
        // FOR SCHEDULE EDITING
        SCHEDULE_HEADING_LABEL,
        DATE_BOUNDARIES_LABEL,
        STARTING_MONDAY_LABEL,
        ENDING_FRIDAY_LABEL,
        LECTURE_DAY_SELECT_LABEL,
        
        // ERROR DIALOG MESSAGES
        START_DATE_AFTER_END_DATE_ERROR_MESSAGE,
        START_DATE_NOT_A_MONDAY_ERROR_MESSAGE,
        END_DATE_NOT_A_FRIDAY_ERROR_MESSAGE,
        ILLEGAL_DATE_MESSAGE,
        
        // AND VERIFICATION MESSAGES
        NEW_DRAFT_CREATED_MESSAGE,
        DRAFT_LOADED_MESSAGE,
        DRAFT_SAVED_MESSAGE,
        SITE_EXPORTED_MESSAGE,
        SAVE_UNSAVED_WORK_MESSAGE,
        REMOVE_ITEM_MESSAGE
}
