package project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdData {
    
    private static int count = 0;

    private int ID;
    private Seller seller;
    private String type;
    private String description;
    private LocalDateTime postedOn;

    
    public AdData(String description, String type, Seller seller, LocalDateTime postedOn) {
        this.ID = count++;
        this.description = description;
        this.type = type;
        this.seller = seller;
        this.postedOn = postedOn;
    }
    

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }

    @Override
    public String toString() {
        String postDateTime = postedOn.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        return "AdData [ID=" + ID + ", postedOn=" + postDateTime + ", seller=" + seller
                + ", type=" + type + ", description=" + description + "]\n";
    }


    

}