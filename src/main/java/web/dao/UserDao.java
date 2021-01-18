package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.User;
//добавил  extends JpaRepository<User, Integer>
public interface UserDao {
    User getUserByName(String name);
}
