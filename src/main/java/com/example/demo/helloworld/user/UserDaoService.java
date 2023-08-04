package com.example.demo.helloworld.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;
    static {
        users.add(new User(1,"heesu1", new Date(),"pass1", "000501-111111"));
        users.add(new User(2,"heesu2", new Date(),"pass2", "000502-111111"));
        users.add(new User(3,"heesu3", new Date(),"pass3", "000503-111111"));
    }
    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if (user.getId() == null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
        for(User user : users){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }

    public User deleteUserId(int id){
       Iterator<User> iterator = users.iterator();

       while (iterator.hasNext()){
           User user = iterator.next();

           if(user.getId()==id){
               iterator.remove();
               return user;
           }
       }
       return null;
    }
}
