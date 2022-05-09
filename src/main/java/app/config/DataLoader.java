package app.config;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;


@Component
public class DataLoader{

    @Autowired
    private UserService userService;

    @PostConstruct
    public void fillDataBase() {

        User user1  = new User();

        user1.setName("Alex");
        user1.setSurName("Zotov");
        user1.setEmail("zotov@mail.ru");
        user1.setAge(23);
        userService.save(user1);


        User user2 = new User();
        user2.setName("Ivan");
        user2.setSurName("Petrov");
        user2.setEmail("petrov@mail.ru");
        user2.setAge(32);

        userService.save(user2);

    }
}

