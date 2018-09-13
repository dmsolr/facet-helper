package cn.dmsolr.facet;

import java.io.IOException;
import java.util.Optional;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;

import com.google.common.collect.Lists;

import cn.dmsolr.facet.response.Buckets;
import cn.dmsolr.facet.response.FacetResponse;

public class Sample {
	public static void main(String[] args) throws SolrServerException, IOException {
		CloudSolrClient client = new CloudSolrClient.Builder(Lists.newArrayList("test03.loc:2181"), Optional.of("/dmsolr")).build();

		FieldFacet facet = JsonFacet.newFieldFacet("dm", "plat");
		facet.facet(JsonFacet.newFieldFacet("ver", "version"));
		facet.facet(Stats.avg("avg_amount", "amount"));

		FacetRequest req = new FacetRequest();
		req.facetQuery(facet);

		// 推荐你这样做吧
		FacetResponse process = req.process(client,"facetHelper");

		// 当然你可以这样
		// NamedList<Object> namedList = req.request(req, "facetHelper");
		// FacetResponse response = new FacetResponse(namedList);
		// Buckets buckets = response.getBuckets("dm");

		Buckets buckets = process.getBuckets("dm");
		System.out.println(process.getCount());
		buckets.forEachRemaining(bucket -> {
			System.out.println(bucket.getVal() + ":" + bucket.getCount() + "\t" + bucket.getDouble("avg_amount") + "\t" + bucket.getBuckets("ver"));
		});
	}
}
