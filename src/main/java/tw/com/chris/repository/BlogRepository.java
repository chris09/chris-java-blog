package tw.com.chris.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.chris.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

}
