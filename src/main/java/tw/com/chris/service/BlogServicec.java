package tw.com.chris.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.chris.entity.Blog;
import tw.com.chris.entity.User;
import tw.com.chris.repository.BlogRepository;
import tw.com.chris.repository.UserRepository;

@Service
public class BlogServicec {
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private UserRepository userRepository;

	public void save(Blog blog, String name) {
		User user = userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
	}

	public void delete(int id) {
		blogRepository.delete(id);
	}

	
	
}
