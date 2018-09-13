## Facet-Helper

拒绝麻烦，**让你的`Solr Facet Module`更好用**

先来看一个示例
```java
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
```

嗯，就是这么方便。开心就你试试吧