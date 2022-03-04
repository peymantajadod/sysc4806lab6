package com.example.lab4.controller;

import com.example.lab4.AddressBook;
import com.example.lab4.AddressBookRepository;
import com.example.lab4.BuddyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class GUIController {
    private final AddressBookRepository addressBookRepository;

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("/addressbook")
    public String getAddressbooks(Model model){
        model.addAttribute("addressbook", new AddressBook());
        return "main";
    }

    @PostMapping(value = "/addressbook")
    public String createAddressbook(@ModelAttribute AddressBook addressBook, Model model){
        addressBookRepository.save(addressBook);
        model.addAttribute("id", addressBook.getId());
        model.addAttribute("buddies", addressBook.getBuddies());
        model.addAttribute("buddyInfo", new BuddyInfo());

        return "buddies";
    }


    /**
     * Acess the webpage using URL http://localhost:8080/addressbookGUI/1
     * 1 is the id of the addressbook already saved in the H2 database however you can use
     * POST REST API to add more addressbook
     * Show the addressbook with id
     * @param id
     * @return the addressbook
     */
    @GetMapping("/addressbookgui/{id}")
    public String getAddressbooks(@PathVariable Integer id,Model model){

        AddressBook aBook = addressBookRepository.findById(id).orElse(null);
        if(aBook != null) {
            model.addAttribute("id", id);
            model.addAttribute("buddies", aBook.getBuddies());
            model.addAttribute("buddyInfo", new BuddyInfo());
        }
        return "buddies";
    }

    @PostMapping(value = "/addressbookgui/{id}")
    public String createBuddy(@PathVariable Integer id, @ModelAttribute BuddyInfo buddy, Model model){
        AddressBook aBook = addressBookRepository.findById(id).orElse(null);

        if(aBook != null){
            aBook.addBuddy(buddy);
            addressBookRepository.save(aBook);
            model.addAttribute("id", id);
            model.addAttribute("buddies", aBook.getBuddies());
            model.addAttribute("buddyInfo", new BuddyInfo());
        }


        return "buddies";
    }


}
