package mike.businesscards.service;

import mike.businesscards.model.User;

import java.util.List;

public interface  UserService {

    public void addUser(User user);

    public List<User> listAll();

    public void removeUser(Integer id);

    public boolean findUserById(Integer id);

    public User getUserById(Integer id);

    public boolean findUserByEmailAndPassword(String email, String password);

    public int getIdByEmailAndPassword(String email, String password);

    public boolean findUserByEmail(String email);

    public User getUserByEmail(String email);



}