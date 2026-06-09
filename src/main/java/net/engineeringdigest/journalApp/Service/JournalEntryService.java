package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository ;

    @Autowired
    private UserServices userservices;

    public void saveEntry(JournalEntry journalEntry,String userName){
        User user = userservices.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userservices.saveUser(user);
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> get_all(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> get_by_id(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    public void delete_all(String userName){
        User user = userservices.findByUserName(userName);
        if (user != null) {
            journalEntryRepository.deleteAll(user.getJournalEntries());
            user.getJournalEntries().clear();
            userservices.saveUser(user);
        }
    }
    public void delete_by_id(ObjectId id,String userName) {
        User user = userservices.findByUserName(userName);
        user.getJournalEntries().removeIf(x->x.getId().equals(id));
        userservices.saveUser(user);
        journalEntryRepository.deleteById(id);
    }
}
