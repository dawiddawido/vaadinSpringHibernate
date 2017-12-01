package pl.cwik.dawid.services.impl;

import org.springframework.beans.factory.annotation.Required;
import pl.cwik.dawid.dao.UserDAO;
import pl.cwik.dawid.model.User;
import pl.cwik.dawid.services.TestService;

import java.util.List;

/**
 * Created by Dawid on 20.11.2017.
 */
public class TestServiceImpl implements TestService {

//    @Autowired
    private UserDAO userDAO;


//    public TestService(){
//        System.out.println("Core start!");
//        List<User> userList = userDAO.findAll();
//        System.out.println("Count userList: " + userList.size());
//    }

    @Override
    public void test(){
        System.out.println("test method");
        List<User> userList = userDAO.findAll();
        System.out.println("Count userList: " + userList.size());
    }

    @Required
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
