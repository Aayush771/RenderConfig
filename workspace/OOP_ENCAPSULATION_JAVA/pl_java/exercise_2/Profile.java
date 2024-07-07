package pl_java.exercise_2;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

public class Profile {

/*
 * Attributes
 */
  private  String name;
  private  String countryCode;
  private  String phoneNo;
  private  URI profilePhotoUrl;
  private  String about;
  private LocalDateTime lastSeen;


/*
 * Constructors
 */


/*
 * Getters
 */


public String getName() {
        return name;
}

public Profile(String name, String countryCode, String phoneNo, URI profilePhotoUrl, String about,
                LocalDateTime lastSeen) {
        this.name = name;
        this.countryCode = countryCode;
        this.phoneNo = phoneNo;
        this.profilePhotoUrl = profilePhotoUrl;
        this.about = about;
        this.lastSeen = lastSeen;
}

public Profile(String name, String countryCode, String phoneNo, URI profilePhotoUrl) {
        setName(name);
        setPhoneNo(countryCode, phoneNo);
        setProfilePhotoUrl(profilePhotoUrl);
        this.lastSeen = LocalDateTime.now(); 
}

public String getCountryCode() {
        return countryCode;
}

public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
}

public String getPhoneNo() {
        return phoneNo;
}

public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
}

public String getProfilePhotoUrl() {
        return profilePhotoUrl;
}

public String getAbout() {
        return about;
}

public LocalDateTime getLastSeen() {
        return this.lastSeen;
}

public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
}

/*
 * Setters
 */
    public void setName(String name){
            this.name = name;
    }

    public void setPhoneNo(String countryCode, String phoneNo){
            this.countryCode = countryCode;
            this.phoneNo = phoneNo;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl)throws URISyntaxException{
        this.profilePhotoUrl = new URI(profilePhotoUrl);
    }

    public void setAbout(String about){
            this.about = about;
    }


/*
 * Methods
 */
    public void updateLastSeen(LocalDateTime lastSeen){
            this.lastSeen = lastSeen;
    }
}