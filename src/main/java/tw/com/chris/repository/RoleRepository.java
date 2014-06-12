package tw.com.chris.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.chris.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
