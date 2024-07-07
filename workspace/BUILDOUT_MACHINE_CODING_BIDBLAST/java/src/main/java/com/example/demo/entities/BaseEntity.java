package com.example.demo.entities;

public abstract class BaseEntity {
    Long id;

    public BaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseEntity() {}
    

    
}
