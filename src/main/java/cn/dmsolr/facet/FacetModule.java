package cn.dmsolr.facet;

public class FacetModule {
	
	public static void main(String[] args) {
		String queryString = RangeFacet.newRangeFacet("rf", "order_time").start("NOW/DAY-1DAY").end("NOW").gap("+1HOUR")
				.facet(Stats.avg("dm", "amount"))
				.toQueryString();
		System.out.println(queryString);
	}
}
