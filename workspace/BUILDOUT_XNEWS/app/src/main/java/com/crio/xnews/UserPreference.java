package com.crio.xnews;

// TODO: CRIO_TASK_MODULE_PROJECT
// Refer users.json in the app/src/main/resources directory to declare the necessary variables.
// Utilize your IDE to generate getters and setters for these variables.
// Use your IDE to generate the toString method to create a string representation of the variables.

public class UserPreference {

      private String name;
      private String query;
      private String language;
      private String sortBy;
    public UserPreference(String name, String query, String language, String sortBy) {
        this.name = name;
        this.query = query;
        this.language = language;
        this.sortBy = sortBy;
    }
    public UserPreference(String name, String query, String language) {
        this.name = name;
        this.query = query;
        this.language = language;
    }
    public UserPreference(String name, String query) {
        this.name = name;
        this.query = query;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getQuery() {
        return query;
    }
    public void setQuery(String query) {
        this.query = query;
    }
    @Override
    public String toString() {
        return "UserPreference [language=" + language + ", name=" + name + ", query=" + query
                + ", sortBy=" + sortBy + "]";
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getSortBy() {
        return sortBy;
    }
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
    public UserPreference() {}

      
}
