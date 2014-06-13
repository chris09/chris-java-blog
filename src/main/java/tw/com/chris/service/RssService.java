package tw.com.chris.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

import javax.naming.spi.ObjectFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;

import org.springframework.stereotype.Service;

import tw.com.chris.entity.Item;
import tw.com.chris.rss.TRss;
import tw.com.chris.rss.TRssChannel;
import tw.com.chris.rss.TRssItem;
import tw.com.exception.RssException;

@Service
public class RssService {

	public List<Item> getItems(Source source) throws RssException {
		ArrayList<Item> list = new ArrayList<Item>();
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			unmarshaller.unmarshal(source, TRss.class);
			JAXBElement<TRss> jaxbElement = unmarshaller.unmarshal(source,
					TRss.class);
			TRss rss = jaxbElement.getValue();
			List<TRssChannel> channels = rss.getChannel();
			for (TRssChannel channel : channels) {
				List<TRssItem> items = channel.getItem();
				for(TRssItem rssItem : items){
					Item item = new Item();
					item.setTitle(rssItem.getTitle());
					item.setDecription(rssItem.getDescription());
					item.setLink(rssItem.getLink());
					try {
						Date pubDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).parse(rssItem.getPubDate());
						item.setPublishedDate(pubDate);
						list.add(item);
					} catch (ParseException e) {
						throw new RssException(e);
					}
				}
			}
		} catch (JAXBException e) {
			throw new RssException(e);
		}
		return null;
	}

}
