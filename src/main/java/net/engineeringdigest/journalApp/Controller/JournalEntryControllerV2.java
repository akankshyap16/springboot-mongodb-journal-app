package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Service.JournalEntryService;
import net.engineeringdigest.journalApp.Service.UserServices;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {


    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserServices userservices;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAll(@PathVariable String userName) {
        User user = userservices.findByUserName(userName);
        List<JournalEntry> list = user.getJournalEntries();
        if(list!=null && !list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
    return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);}

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName) {
        try {
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<?> getbyId(@PathVariable ObjectId myid) {
        Optional<JournalEntry> entry = journalEntryService.get_by_id(myid);
        if (entry.isPresent()) {
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("name/{userName}")
    public ResponseEntity<?> deleteAll(@PathVariable String userName) {
        journalEntryService.delete_all(userName);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @DeleteMapping("id/{userName}/{myid}")
    public ResponseEntity<?> deletebyId(@PathVariable ObjectId myid,@PathVariable String userName) {
        Optional<JournalEntry> entry = journalEntryService.get_by_id(myid);
        if (entry.isPresent()) {
            journalEntryService.delete_by_id(myid,userName);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

   @PutMapping("id/{userName}/{myid}")
    public ResponseEntity<?> putbyid(@PathVariable ObjectId myid,@PathVariable String userName, @RequestBody JournalEntry myEntry) {
        JournalEntry old = journalEntryService.get_by_id(myid).orElse(null);
        if(old != null){
            old.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
            old.setContent(myEntry.getContent() != null && !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
