package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Component
public class UserServices {

    @Autowired
    private UserRepository userRepository ;

    public void saveUser(User user){
        userRepository.save(user);}

    public List<User> get_all(){
        return userRepository.findAll();
    }
    public Optional<User> get_by_id(ObjectId id){
        return userRepository.findById(id);
    }
    public void delete_all(){
        userRepository.deleteAll();
    }
    public void delete_by_username(String userName) {

        User user = userRepository.findByUserName(userName);
        userRepository.delete(user);
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
    public User update_by_id(User xyz,ObjectId id) {
            userRepository.findById(id);
           xyz.setId(id);
           userRepository.save(xyz);
           return xyz;
        }
}
