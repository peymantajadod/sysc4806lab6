package com.example.lab4;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class AddressBook{

    @OneToMany(cascade = CascadeType.ALL)
    List<BuddyInfo> buddies;
    @Id
    @GeneratedValue
    private Integer id;

    public AddressBook() {
        buddies = new ArrayList<>();
    }

    public void addBuddy(BuddyInfo buddy) {
        if (buddy != null)
            buddies.add(buddy);
    }


    public int size() {
        return buddies.size();
    }

    public void clear() {
        buddies = new ArrayList<>();
    }

    public boolean removeBuddy(BuddyInfo buddyInfo) {
        this.buddies.remove(buddyInfo);

        return true;
    }


}
