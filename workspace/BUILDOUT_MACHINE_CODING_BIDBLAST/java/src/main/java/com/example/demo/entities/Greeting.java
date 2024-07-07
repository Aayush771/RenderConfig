package com.example.demo.entities;

public class Greeting {
    private Long id;
    private String message;

    public Greeting(String message) {
        this.id = null;
        this.message = message;
    }

    public Greeting(Long id, Greeting other){
        this.id = id;
        this.message = other.message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }   

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Greeting other = (Greeting) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Greeting [id=" + id + "]";
    }

}
