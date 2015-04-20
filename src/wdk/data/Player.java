package wdk.data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Player {

    // THESE COURSE DETAILS DESCRIBE WHAT'S REQUIRED BY
    // THE COURSE SITE PAGES

    String firstName;    
    String lastName;
    String team;
    int birthYear;
    String notes;
    String birthNation;
    String position;
    int RW;
    int HRSV;
    int RBIK;
    double SBERA;
    double BAWHIP;

    
    public Player(String firstName, String lastName, String team, int birthYear,
           String notes, String birthNation) {
        
        this.firstName = firstName;        
        this.lastName = lastName;
        this.team = team;
        this.birthYear = birthYear;
        this.notes = notes;
        this.birthNation = birthNation;
    }

    public Player(String firstName, String lastName, String team, String position,
            int birthYear, String notes, int RW, int HRSV, 
                int RBIK, double SBERA, double BAWHIP) {
        
        this.firstName = firstName;        
        this.lastName = lastName;
        this.team = team;
        this.position = position;
        this.birthYear = birthYear;
        this.notes = notes;
        this.RW = RW;
        this.HRSV = HRSV;
        this.RBIK = RBIK;
        this.SBERA = SBERA;
        this.BAWHIP = BAWHIP;
        
    }
    // BELOW ARE ALL THE ACCESSOR METHODS FOR A COURSE
    // AND THE MUTATOR METHODS. NOTE THAT WE'LL NEED TO CALL
    // THESE AS USERS INPUT VALUES IN THE GUI
  
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public int getRW() {
        return RW;
    }
    public void setRunsWins(int RW) {
        this.RW = RW;
    }
    
    public int getHRSV() {
        return HRSV;
    }
    public void setHRSV(int HRSV) {
        this.HRSV = HRSV;
    }
    
    public double getBAWHIP() {
        return BAWHIP;
    }
    public void setBAWHIP(double BAWHIP) {
        this.BAWHIP = BAWHIP;
    }
        
    public double getSBERA() {
        return SBERA;
    }
    public void setSBERA(int SBERA) {
        this.SBERA = SBERA;
    }
    
    public int getRBIK() {
        return RBIK;
    }
    public void setRBIK(int RBIK) {
        this.RBIK = RBIK;
    }        
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getNotes() {
        return notes;
    }
    
    public String getPosition(){   
        return position;
    }
    public void setPosition(String position){
        this.position = position;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBirthNation() {
        return birthNation;
    }

    public void setBirthNation(String birthNation) {
        this.birthNation = birthNation;
    }
/*
    public LocalDate getStartingMonday() {
        return startingMonday;
    }

    public void setStartingMonday(LocalDate startingMonday) {
        this.startingMonday = startingMonday;
    }

    public LocalDate getEndingFriday() {
        return endingFriday;
    }

    public void setEndingFriday(LocalDate endingFriday) {
        this.endingFriday = endingFriday;
    }

    public void setScheduleDates(LocalDate initStartingMonday, LocalDate initEndingFriday) {
        setStartingMonday(initStartingMonday);
        setEndingFriday(initEndingFriday);
    }

    public void addPage(CoursePage pageToAdd) {
        pages.add(pageToAdd);
    }

    public List<CoursePage> getPages() {
        return pages;
    }

    public void selectPage(CoursePage coursePage) {
        if (!pages.contains(coursePage)) {
            pages.add(coursePage);
        }
    }

    public void unselectPage(CoursePage coursePage) {
        if (pages.contains(coursePage)) {
            pages.remove(coursePage);
        }
    }

    public List<DayOfWeek> getLectureDays() {
        return lectureDays;
    }

    // BELOW ARE ADDITIONAL METHODS FOR UPDATING A COURSE
    public void selectLectureDay(DayOfWeek dayOfWeek) {
        if (!lectureDays.contains(dayOfWeek)) {
            lectureDays.add(dayOfWeek);
        } else {
            lectureDays.remove(dayOfWeek);
        }
    }

    public void selectLectureDay(DayOfWeek dayOfWeek, boolean isSelected) {
        if (isSelected) {
            if (!lectureDays.contains(dayOfWeek)) {
                lectureDays.add(dayOfWeek);
            }
        } else {
            lectureDays.remove(dayOfWeek);
        }
    }

    public void clearPages() {
        pages.clear();
    }

    public void clearLectureDays() {
        lectureDays.clear();
    }

    public void clearScheduleItems() {
        scheduleItems.clear();
    }

    public void clearLectures() {
        lectures.clear();
    }

    public void clearHWs() {
        assignments.clear();
    }

    public void addLectureDay(DayOfWeek dayOfWeek) {
        lectureDays.add(dayOfWeek);
    }

    public boolean hasLectureDay(DayOfWeek dayOfWeek) {
        return lectureDays.contains(dayOfWeek);
    }

    public void addScheduleItem(ScheduleItem si) {
        scheduleItems.add(si);
    }
    
    //SORTS THE SCHEDULE ITEMS TABLE
    public void sortScheduleItem(){
        Collections.sort(scheduleItems);
    }

    public ObservableList<ScheduleItem> getScheduleItems() {
        return scheduleItems;
    }

    public void removeScheduleItem(ScheduleItem itemToRemove) {
        scheduleItems.remove(itemToRemove);
    }

    public void addLecture(Lecture lol) {
        lectures.add(lol);
    }

    public ObservableList<Lecture> getLectures() {
        return lectures;
    }

    public void removeLecture(Lecture lectureToRemove) {
        lectures.remove(lectureToRemove);
    }

    public void addAssignment(Assignment a) {
        assignments.add(a);
    }
    
    //SORTS THE ASSIGNMENT TABLE - new
    public void sortAssignment() {
        Collections.sort(assignments);
    }

    public ObservableList<Assignment> getAssignments() {
        return assignments;
    }

    public void removeAssignment(Assignment assignmentToRemove) {
        assignments.remove(assignmentToRemove);
    }

    public HashMap<LocalDate, ScheduleItem> getScheduleItemMappings() {
        // GET THE SCHEDULE ITEM DATES FOR QUICK LOOKUP
        HashMap<LocalDate, ScheduleItem> holidayDates = new HashMap();
        for (ScheduleItem scheduleItem : scheduleItems) {
            holidayDates.put(scheduleItem.getDate(), scheduleItem);
        }
        return holidayDates;
    }*/
}
