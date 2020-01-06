package com.umsign.jpa;

import com.umsign.jpa.model.Gender;
import com.umsign.jpa.model.User;
import com.umsign.jpa.model.UserProfile;
import com.umsign.jpa.repository.UserProfileRepository;
import com.umsign.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;

@SpringBootApplication
public class JpaOneToOneDemoApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaOneToOneDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userProfileRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();

        User user = new User("Rajeev", "Singh", "rajeev@callicoder.com", "MY_SUPER_SECRET_PASSWORD");

        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1992, 7, 21);

        UserProfile userProfile = new UserProfile("+91-8197882053", Gender.MALE, dateOfBirth.getTime(), "747", "2nd Cross", "Golf View Road, Kodihalli", "Banagalore", "Karnataka", "India", "560008");

        user.setUserProfile(userProfile);

        userProfile.setUser(user);

        userRepository.save(user);
    }
}
