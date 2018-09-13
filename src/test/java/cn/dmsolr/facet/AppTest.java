package cn.dmsolr.facet;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import com.google.common.collect.Lists;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	public static void main(String[] args) throws SolrServerException, IOException {
		CloudSolrClient client = new CloudSolrClient.Builder(Lists.newArrayList("test03.loc:2181,test04.loc:2181,test05.loc:2181"), Optional.of("/dmsolr"))
				.build();
		client.setDefaultCollection("daming");
		
		Random random = new Random();
		for (String plat : new String[] {"android", "ios", "wap"}) {
			for (String ver : new String[] {"1.0.0", "1.0.1", "1.0.2", "1.1.2", "1.2.1", "2.0.0", "2.0.1", "2.3.0", "2.5.0", "3.1.0", "3.1.1", "3.4.1"}) {
				for (int pch=0; pch<100; pch++) {
					String chl = "chl_" + (pch + 1000 + "").substring(1);
					List<SolrInputDocument> list = Lists.newArrayList();
					for(int dc=0; dc<1000; dc++) {
						int user = random.nextInt(1000);
						String device = UUID.randomUUID().toString();
						
						long amount = random.nextInt(100000);
						
						SolrInputDocument doc = new SolrInputDocument();
						doc.addField("plat", plat);
						doc.addField("version", ver);
						doc.addField("channel", chl);
						doc.addField("user_id", user);
						doc.addField("device_id", device);
						doc.addField("amount", amount);
						
						doc.addField("id", plat + "-" + chl + "-" + (1000+dc) + "-" + user);
						list.add(doc);
					}
					UpdateResponse add = client.add(list);
					QueryResponse query = client.query(null);
					
					System.out.println(add);
				}
			}
		}
		client.commit();
	}
}
