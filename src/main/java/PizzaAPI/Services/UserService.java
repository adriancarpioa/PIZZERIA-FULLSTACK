package PizzaAPI.Services;

import PizzaAPI.Models.UserModel;
import PizzaAPI.Repositories.IUserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    IUserRepository userRepository;
    
    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }
    
    public UserModel saveUser(UserModel user){
        return userRepository.save(user);
    }
    
    public Optional<UserModel> getById(Long id){
        return userRepository.findById(id);
    }
    
    public UserModel updateUserById(UserModel request, Long id){
        UserModel user = userRepository.findById(id).get();
        
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        
        return user;
    }
    
    public Boolean deleteUser(Long id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
