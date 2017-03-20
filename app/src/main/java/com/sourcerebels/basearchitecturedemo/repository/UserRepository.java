package com.sourcerebels.basearchitecturedemo.repository;

import com.sourcerebels.basearchitecture.Repository;
import com.sourcerebels.basearchitecturedemo.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UserRepository implements Repository<User, Long> {

    private List<User> users;

    @Inject
    public UserRepository() {
        users = new ArrayList<>();
        users.add(new User(1, "Steven Spielberg"));
        users.add(new User(2, "Quentin Tarantino"));
        users.add(new User(3, "Stanley Kubrick"));
        users.add(new User(4, "Martin Scorsese"));
        users.add(new User(5, "James Cameron"));
        users.add(new User(6, "Christopher Nolan"));
        users.add(new User(7, "Tim Burton"));
        users.add(new User(8, "Francis Ford Coppola"));
        users.add(new User(9, "Alfred Hitchcok"));
        users.add(new User(10, "Clint Eastwood"));
        users.add(new User(11, "Charles Chaplin"));
        users.add(new User(12, "Peter Jackson"));
        users.add(new User(13, "David Fincher"));
        users.add(new User(14, "Ridley Scott"));
        users.add(new User(15, "Woody Allen"));
        users.add(new User(16, "Roman Polanski"));
        users.add(new User(17, "George Lucas"));
        users.add(new User(18, "Luis Buñuel"));
        users.add(new User(19, "Brian De Palma"));
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User save(User user) {
        users.add(0, user);
        return user;
    }
}
