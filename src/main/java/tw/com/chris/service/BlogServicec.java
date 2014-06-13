package tw.com.chris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import tw.com.chris.entity.Blog;
import tw.com.chris.entity.Item;
import tw.com.chris.entity.User;
import tw.com.chris.exception.RssException;
import tw.com.chris.repository.BlogRepository;
import tw.com.chris.repository.ItemRepository;
import tw.com.chris.repository.UserRepository;

@Service
public class BlogServicec {
	
	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RssService rssService;

	@Autowired
	private ItemRepository itemRepository;

	public void saveItems(Blog blog) {
		try {
			List<Item> items = rssService.getItems(blog.getUrl());
			for (Item item : items) {
				Item savedItem = itemRepository.findByBlogAndLink(blog, item.getLink());
				if(savedItem == null) {
					item.setBlog(blog);
					itemRepository.save(item);
				}
			}
		} catch (RssException e) {
			e.printStackTrace();
		}
	}
	
	@Scheduled(fixedDelay=36000000)//1H
	public void reloadBlogs(){
		List<Blog> blogs = blogRepository.findAll();
		for(Blog blog : blogs){
			saveItems(blog);
		}
	}

	public void save(Blog blog, String name) {
		User user = userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
		saveItems(blog);
	}

	@PreAuthorize("#blog.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("blog") Blog blog) {
		blogRepository.delete(blog);
	}

	public Blog findOne(int id) {
		return blogRepository.findOne(id);
	}

	
}
