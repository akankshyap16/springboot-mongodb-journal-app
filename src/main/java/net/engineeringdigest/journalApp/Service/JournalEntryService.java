package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository ;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> get_all(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> get_by_id(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    public void delete_all(){
        journalEntryRepository.deleteAll();
    }
    public void delete_by_id(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }
    public JournalEntry update_by_id(JournalEntry xyz,ObjectId id) {
            journalEntryRepository.findById(id).orElse(null);
           xyz.setId(id);
           journalEntryRepository.save(xyz);
           return xyz;
        }
}
