package com.crio.springdatabyte.dto;



public class Stats {

  public int numUsers;

  @Override
  public String toString() {
    return "Stats{" +
        "numUsers=" + numUsers +
        '}';
  }

  public Stats(int numUsers) {
    this.numUsers = numUsers;
  }
  public Stats() {}

  public int getNumUsers() {
    return numUsers;
  }

  public void setNumUsers(int numUsers) {
    this.numUsers = numUsers;
  }

}
