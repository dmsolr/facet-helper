package cn.dmsolr.facet;

public class FacetModule {
	
	public static void main(String[] args) {
		String queryString = RangeFacet.newRangeFacet("rf", "order_time").start("NOW/DAY-1DAY").end("NOW").gap("+1HOUR")
				.facet(Stats.avg("dm", "amount"))
				.facet(Stats.sum("sum", "amount"))
				.toQueryString();
		System.out.println(queryString);
		
		
		FieldFacet pivot = JsonFacet.pivot("123", "abc", "3", "4", "5");
		System.out.println(pivot.toQueryString());
	}
}
