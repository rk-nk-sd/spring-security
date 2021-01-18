package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;
//добавил  extends JpaRepository<User, Integer>
@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User getUserByName(String name);
}
