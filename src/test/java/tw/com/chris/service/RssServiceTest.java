package tw.com.chris.service;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tw.com.chris.entity.Item;
import tw.com.chris.exception.RssException;

public class RssServiceTest {
	
	private RssService rssService;
	

	@Before
	public void setUp() throws Exception {
		rssService = new RssService();
	}

	@Test
	public void testGetItemsFile() throws RssException {
		
		List<Item> items = rssService.getItems(new File("test-rss/javavids.xml"));
		assertEquals(10, items.size());
		Item firstItem = items.get(0);
		assertEquals("Spring web app tutorial 51: Spring Profiles and PostgreSQL DB", firstItem.getTitle());
		assertEquals("24 04 2014 21:27:49", new SimpleDateFormat("dd MM yyyy HH:mm:ss").format(firstItem.getPublishedDate()));
	}

}
