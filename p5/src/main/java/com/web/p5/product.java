package com.web.p5;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class product {
    @Id
    public String id;
    public Integer number;
    public String name;
    public Integer count;
    
    @CreationTimestamp
    public LocalDateTime date;
}
