package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Service.JournalEntryService;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/pls")
public class JournalEntryControllerV2 {


    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.get_all();
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
       return true;
    }

    @GetMapping("id/{myid}")
    public Optional<JournalEntry> getbyId(@PathVariable ObjectId myid){
        return journalEntryService.get_by_id(myid);
    }

    @DeleteMapping
    public boolean delete_all(){
        journalEntryService.delete_all();
        return true;
    }

    @DeleteMapping("id/{myid}")
    public Boolean deletebyId(@PathVariable ObjectId myid){
        journalEntryService.delete_by_id(myid);
       return true;
    }

    @PutMapping("id/{myid}")
    public JournalEntry putbyid(@PathVariable ObjectId myid,@RequestBody JournalEntry myEntry){
        return journalEntryService.update_by_id(myEntry,myid);
    }
}
