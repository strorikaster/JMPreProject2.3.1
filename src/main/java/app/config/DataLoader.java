package app.config;

//import app.model.Role;
import app.model.User;
//import app.repository.UserRepo;
//import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataLoader{

    private UserService userService;
   // private RoleService roleService;

    public DataLoader (UserService userService/*, RoleService roleService*/) {
        this.userService = userService;
      ///  this.roleService = roleService;
    }

    @PostConstruct
    public void fillDataBase() {

//        Role roleAdmin = new Role();
//        roleAdmin.setName("Admin");
//        roleService.addRole(roleAdmin);
//
//        Role roleUser = new Role();
//        roleUser.setName("USER");
//        roleService.addRole(roleUser);

        User user1  = new User();
        //er1.setId(1L);
        user1.setName("Alex");
        //user1.setPassword("root");
        //user1.setFirstName("Alexey");
        user1.setSurName("Zotov");
        user1.setEmail("zotov@mail.ru");
        user1.setAge(23);
        // user1.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        userService.save(user1);


        User user2 = new User();
        //user2.setId(2L);
        user2.setName("Ivan");
        //user2.setPassword("root");
        //user2.setFirstName("Ivan");
        user2.setSurName("Petrov");
        user2.setEmail("petrov@mail.ru");
        //user2.setRoles(Set.of (roleService.getRoleByName("ADMIN"), roleService.getRoleByName("USER")));
        user2.setAge(32);

        userService.save(user2);

    }
}

