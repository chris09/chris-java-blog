package tw.com.chris.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.chris.entity.Blog;
import tw.com.chris.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	List<Blog> findByUser(User user);
	
}
