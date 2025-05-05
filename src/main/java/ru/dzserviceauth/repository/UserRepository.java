package ru.dzserviceauth.repository;

import org.springframework.stereotype.Repository;
import ru.dzserviceauth.auth.Authorities;

import java.util.*;

@Repository
public class UserRepository {
    private final Map<String, String> userPasswordMap = new HashMap<>();
    private final Map<String, List<Authorities>> userAuthoritiesMap = new HashMap<>();

    public UserRepository() {
        userPasswordMap.put("admin", "admin");
        userPasswordMap.put("user1", "user1");
        userPasswordMap.put("user2", "user2");

        userAuthoritiesMap.put("admin", List.of(Authorities.DELETE, Authorities.READ, Authorities.WRITE));
        userAuthoritiesMap.put("user1", List.of(Authorities.READ, Authorities.WRITE));
        userAuthoritiesMap.put("user2", Collections.singletonList(Authorities.WRITE));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        if (!userPasswordMap.containsKey(user)) {
            return Collections.emptyList();
        }

        if (!userPasswordMap.get(user).equals(password)) {
            return Collections.emptyList();
        }

        return userAuthoritiesMap.getOrDefault(user, Collections.emptyList());
    }
}
