package com.example.lab4;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class BuddyInfo {

    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private String number;
    @NonNull
    private int age;

    @Id
    @GeneratedValue
    private Integer id;


}
