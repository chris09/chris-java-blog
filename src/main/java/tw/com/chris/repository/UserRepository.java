package tw.com.chris.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.chris.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
