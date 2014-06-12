package tw.com.chris.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.chris.entity.Blog;
import tw.com.chris.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	List<Item> findByBlog(Blog blog);
	
	
}
