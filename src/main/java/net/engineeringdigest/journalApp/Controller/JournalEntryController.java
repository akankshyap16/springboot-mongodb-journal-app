package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/pls")
public class JournalEntryController {

    private Map<ObjectId,JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(),myEntry);
        return true;
    }
    @GetMapping("id/{myid}")
    public JournalEntry getbyId(@PathVariable ObjectId myid){
        return journalEntries.get(myid);
    }
    @DeleteMapping("id/{myid}")
    public Boolean deletebyId(@PathVariable ObjectId myid){
        journalEntries.remove(myid);
        return true;
    }
    @PutMapping("id/{myid}")
    public JournalEntry putbyid(@PathVariable ObjectId myid,@RequestBody JournalEntry myEntry){
        return journalEntries.put(myid,myEntry);
    }
}
