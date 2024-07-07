package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdHandler {
    
    private List<AdData> allAdsList;
    private List<Meeting> allMeetings;
    private Map<Integer, Seller> sellerMap;
    private List<String> typesList;

    public AdHandler() {
        // create empty Ads list
        this.allAdsList = new ArrayList<AdData>();
        // create empty Meetings list
        this.allMeetings = new ArrayList<Meeting>();

        // add some sellers & Ad types
        // will be fetched from database in next version
        this.sellerMap = new HashMap<>();
        this.sellerMap.put(0, new Seller("Fact Incorps"));
        this.sellerMap.put(1, new Seller("Radix LLC"));
        this.sellerMap.put(2, new Seller("13to19"));
        
        // String[] typesArray = {"toy", "vehicle", "furniture", "clothes"};
        // this.typesList = Arrays.asList(typesArray);
        this.typesList = readAdTypes("src/main/resources/types.txt");
    }

    public void addNewAd(String description, String type, 
        int sellerID) throws FileNotFoundException {
        Seller seller = sellerMap.get(sellerID);

        // limit size of Ad description to 50 characters
        if (description.length() > 50) {
            throw new RuntimeException("\nDescription length should be less than 50");
        }

        if (typesList.contains(type) == false) {
            String msg = "\nInvalid Ad type entered. Supported are " 
                + this.typesList.toString();
            throw new RuntimeException(msg);
        }

        AdData adData = new AdData(description, type, seller, LocalDateTime.now());

        // List<AdData> list = new ArrayList<>();
        // list.add(adData);
        // this.allAdsList = list;

         this.allAdsList.add(adData);
    }

    public void addNewMeeting(int adId, String meetingTime, String customerName) {

        LocalDateTime localDateTime = LocalDateTime.parse(meetingTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        LocalTime localTime = localDateTime.toLocalTime();

        if (isValidMeetingTime(localTime)) {
            Meeting meeting = new Meeting(adId, localDateTime, customerName);
            allMeetings.add(meeting);
        } else {
            throw new RuntimeException("\nMeeting time doesn't fall within meeting hours available.");
        }
    }

    // public boolean isValidMeetingTime(LocalTime timeToCheck) {
    //     // * - For meetings hours: 10AM - 12PM, 2PM-3PM, 6PM-8PM

    //     if (timeToCheck.isAfter(LocalTime.of(10, 0)) && timeToCheck.isBefore(LocalTime.of(12, 0))) {
    //         return true;
    //     } else if (timeToCheck.isAfter(LocalTime.of(14, 0)) && timeToCheck.isBefore(LocalTime.of(15, 0))) {
    //         return true;
    //     } else if (timeToCheck.isAfter(LocalTime.of(18, 0)) && timeToCheck.isBefore(LocalTime.of(20, 0))) {
    //         return true;
    //     }

    //     return false;
    // }
    

    // TODO: To be uncommented in M2
    // Comment out isValidMeetingTime, and rename below method to isValidMeetingTime()
    
    public boolean isValidMeetingTime(LocalTime timeToCheck) {
        // * - For meetings hours: 10AM - 12PM, 2PM-3PM, 6PM-8PM
        return isBetween(timeToCheck, LocalTime.of(10, 0), LocalTime.of(12, 0))
            || isBetween(timeToCheck, LocalTime.of(14, 0), LocalTime.of(15, 0))
            || isBetween(timeToCheck, LocalTime.of(18, 0), LocalTime.of(20, 0));
    
    }

    public boolean isBetween(LocalTime timeToCheck, LocalTime startTime, LocalTime endTime) {
        return (timeToCheck.isAfter(startTime)) && timeToCheck.isBefore(endTime);
    }


    // TODO - Code to be checked in M5 - Don't use system specific code
    /*
    public void addNewAdInputLocalDateTime(String description, String type, int sellerID, LocalDateTime localDateTime) throws FileNotFoundException {
        Seller seller = sellerMap.get(sellerID);

        // limit size of Ad description to 50 characters
        if (description.length() > 50) {
            throw new RuntimeException("\nDescription length should be less thn 50");
        }

        File typesFile = new File("src/main/resources/types.txt");

        Scanner scanner = new Scanner(typesFile);
        boolean isValidTypeFlag = false;

        while(scanner.hasNext()) {
            String typeRead = scanner.next().trim();
            if (typeRead.equals(type)) {
                isValidTypeFlag = true;
                break;
            }
        }
        scanner.close();

        if (isValidTypeFlag == false) {
            String msg = "\nInvalid Ad type entered. Supported are " 
                + this.typesList.toString();
            throw new RuntimeException(msg);
        }

        // System time used
        AdData adData = new AdData(description, type, seller, localDateTime);

        this.allAdsList.add(adData);
    }
    */


    
    // TODO: Code to be checked in Milestone 5 - Writing Testable code

    // public void addNewAdRefactored(String description, String type, 
    //     int sellerID, LocalDateTime date) throws FileNotFoundException {
    //     Seller seller = sellerMap.get(sellerID);

    //     validateDescription(description);
    //     validateType(type);

    //     AdData adData = new AdData(description, type, seller, date);
    //     allAdsList.add(adData);
        
    // }

    // private void validateDescription(String description) {
    //     if (description.length() > 50) {
    //         throw new RuntimeException("\nDescription length should be less than 50");
    //     }
    // }

    // private void validateType(String type) {
    //     if (typesList.contains(type) == false) {
    //         String msg = "\nInvalid Ad type entered. Supported are " 
    //             + this.typesList.toString();
    //         throw new RuntimeException(msg);
    //     }
    // }
    
    private List<String> readAdTypes(String filePath) {
        List<String> list = new ArrayList<>();

        File typesFile = new File(filePath);

        try {
            Scanner scanner = new Scanner(typesFile);
        
            while(scanner.hasNext()) {
                String typeRead = scanner.next().trim();
                list.add(typeRead);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<AdData> getAllAds() {
        return this.allAdsList;
    }

    public List<AdData> getAdsBySeller(int ID) {
        List<AdData> adsListBySeller = new ArrayList<>();

        for (AdData adData : allAdsList) {
            if (adData.getSeller().getID() == ID) {
                adsListBySeller.add(adData);
            }
        }

        return adsListBySeller;
    }

    public List<AdData> getAdsByType(String type) {
        List<AdData> adsListByType = new ArrayList<>();

        for (AdData adData : allAdsList) {
            if (type.equals(adData.getType())) {
                adsListByType.add(adData);
            }
        }

        return adsListByType;
    }

    public List<Meeting> getAllMeetings() {
        return allMeetings;
    }

    public void setAllMeetings(List<Meeting> allMeetings) {
        this.allMeetings = allMeetings;
    }

    public List<AdData> getAllAdsList() {
        return allAdsList;
    }

    public void setAllAdsList(List<AdData> allAdsList) {
        this.allAdsList = allAdsList;
    }

    public Map<Integer, Seller> getsellerMap() {
        return sellerMap;
    }

    public void setsellerMap(Map<Integer, Seller> sellerMap) {
        this.sellerMap = sellerMap;
    }

}