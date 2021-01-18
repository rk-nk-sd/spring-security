package web.dao;
//
//import web.dao.UserDao;
//import web.model.Role;
//import web.model.User;
//import org.springframework.stereotype.Repository;
//
//import java.util.Collections;
//import java.util.Map;
//
//@Repository
//public class UserDaoImpl implements UserDao {
//
////    private final Map<String, User> userMap = Collections.singletonMap("test",
////            new User(1L, "test", "test", Collections.singleton(new Role(1L, "ROLE_USER")))); // name - уникальное значение, выступает в качестве ключа Map
//
//    private final Map<String, User> userMap = Collections.singletonMap("test",
//            new User(1L, "test", "$2y$12$ksfXXkP7hpj22iMbb06i1OKfKdldtWMSl6x.Xn6xQpuhRKEMqBGhu", Collections.singleton(new Role(1L, "ROLE_USER")))); // name - уникальное значение, выступает в качестве ключа Map
//
//
//    @Override
//    public User getUserByName(String name) {
//        if (!userMap.containsKey(name)) {
//            return null;
//        }
//
//        return userMap.get(name);
//    }
//}
//
public class UserDaoImpl {
    //Больше не нужен - позднее удалить.
}