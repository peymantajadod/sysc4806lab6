package com.example.lab4.controller;

import com.example.lab4.AddressBook;
import com.example.lab4.AddressBookRepository;
import com.example.lab4.BuddyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressbookController {

    private final AddressBookRepository addressBookRepository;

    /**
     * POST http://localhost:8080/addressbook/
     * Create an addressbook
     * @return the addressbook
     */
    @PostMapping(value = "/addressbook")
    public AddressBook createAddressbook(){
        AddressBook aBook = new AddressBook();
        return addressBookRepository.save(aBook);
    }

    /**
     * Get the addressbook with id
     * @param id
     * @return the addressbook
     */
    @GetMapping("/addressbook/{id}")
    public AddressBook getAddressbooks(@PathVariable Integer id){
        return addressBookRepository.findById(id).orElse(null);
    }

    /**
     * GET http://localhost:8080/addressbook/
     * Get all the addressbooks available
     * @return
     */
    @GetMapping("/addressbook/")
    public Iterable<AddressBook> getAddressbooks(){
        return addressBookRepository.findAll();
    }

    /**
     * POST http://localhost:8080/addressbook/1/buddyinfo
     * + passing a json body i.e. {
     *     "name":"John",
     *     "address":"600 Church",
     *     "number":"6400000000",
     *     "age":22
     * }
     * it will add buddy John to addresbook with id 1
     * add a buddy to a specific addressbook
     * @param id of the addressbook
     * @param buddy the buddy to add
     * @return the addressbook with the added buddy
     */
    @PostMapping(path = "/addressbook/{id}/buddyinfo",consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddressBook addBuddyinfo(@PathVariable Integer id, @RequestBody BuddyInfo buddy){
        AddressBook aBook = addressBookRepository.findById(id).orElse(null);

        if(aBook != null){
            aBook.addBuddy(buddy);
            return addressBookRepository.save(aBook);
        }
        return null;
    }

    /**
     * Remove a buddy from addresbook
     * @param id of the addressbook
     * @param buddy the buddy to be removed
     * @return the addresbook
     */
    @DeleteMapping(path = "/addressbook/{id}/buddyinfo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddressBook removeBuddy(@PathVariable Integer id, @RequestBody BuddyInfo buddy){
        AddressBook aBook = addressBookRepository.findById(id).orElse(null);
        if(aBook != null){
            aBook.removeBuddy(buddy);
            return addressBookRepository.save(aBook);
        }

        return null;
    }



}
