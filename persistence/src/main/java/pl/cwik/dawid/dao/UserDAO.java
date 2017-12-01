package pl.cwik.dawid.dao;

import pl.cwik.dawid.model.User;

import java.util.List;

/**
 * Created by Dawid on 03.11.2017.
 */
public interface UserDAO {

    void save(User user);
    List<User> findAll();

}
