package com.example.demo.entities;

public class Members extends BaseEntity{

    private String memberName;
    private Long crioCoins;
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public Long getCrioCoins() {
        return crioCoins;
    }
    public void setCrioCoins(Long crioCoins) {
        this.crioCoins = crioCoins;
    }
    public Members(Long id, String memberName, Long crioCoins) {
        super(id);
        this.memberName = memberName;
        this.crioCoins = crioCoins;
    }
    public Members(String memberName, Long crioCoins) {
        this.memberName = memberName;
        this.crioCoins = crioCoins;
    }
  
    
}
