package com.crio.starter.data;

public class Meme {

    private Long id;
    private String name;
    private String url;
    private String caption;
   
    public Meme(String name, String url, String caption) {
        this.name = name;
        this.url = url;
        this.caption = caption;
    }
    public Meme() {}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public Meme(Long id, String name, String url, String caption) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.caption = caption;
    }
    @Override
    public String toString() {
        return "Meme [caption=" + caption + ", id=" + id + ", name=" + name + ", url=" + url
                + "]";
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    
}
