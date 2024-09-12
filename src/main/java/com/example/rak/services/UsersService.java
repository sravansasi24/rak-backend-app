package com.example.rak.services;

import com.example.rak.modal.Users;
import com.example.rak.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    public Users createUser(Users user) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    public Optional<Users> updateUser(Long id, Users updatedUser) {
        return usersRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return usersRepository.save(user);
        });
    }

    public Optional<Users> updateUserPassword(Long id, String newPassword) {
        return usersRepository.findById(id).map(user -> {
            user.setPassword(passwordEncoder.encode(newPassword));
            return usersRepository.save(user);
        });
    }

    public boolean deleteUserById(Long id) {
        Optional<Users> user = usersRepository.findById(id);
        if (user.isPresent()) {
            usersRepository.deleteById(id);  // Delete the user from the repository
            return true;
        }
        return false;
    }
}