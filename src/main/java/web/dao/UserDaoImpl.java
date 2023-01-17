package web.dao;

import org.springframework.stereotype.Repository;
import web.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void add(User user){
        entityManager.persist(user);
    }

    public List<User> getAllUsers(){
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    public void removeUser(User user) {
        entityManager.remove(user);
    }

    public void removeUserById(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    public void removeAllUsers(){
        entityManager.createQuery("DELETE from User ")
                .executeUpdate();
    }

    public void update(int id, User updatedUser){
        User userToBeUpdated = getUserById(id);
        if(updatedUser.getName() != null) userToBeUpdated.setName(updatedUser.getName());
        if(updatedUser.getSurname() != null) userToBeUpdated.setSurname(updatedUser.getSurname());
        if(updatedUser.getAge() > 0) userToBeUpdated.setAge(updatedUser.getAge());
        if(updatedUser.getLogin() != null) userToBeUpdated.setLogin(updatedUser.getLogin());
    }
}