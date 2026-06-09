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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping
    public List<User> get_all_user() {
        return userServices.get_all();
    }

    @PostMapping
    public void Create_user(@RequestBody User user) {
        userServices.saveUser(user);
    }

    /*@DeleteMapping
    public boolean delete_user(){
        userServices.delete_all();
        return true;
    }*/
    @PutMapping("/{userName}")
    public ResponseEntity<?> update_user(@RequestBody User user, @PathVariable String userName) {
        User userInDb = userServices.findByUserName(userName);
        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userServices.saveUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{userName}")
    public void deletebyusername(@PathVariable String userName) {
        userServices.delete_by_username(userName);
    }

}