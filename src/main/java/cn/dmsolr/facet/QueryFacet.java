package cn.dmsolr.facet;

public class QueryFacet extends JsonFacet {
	private static final String QUERY = "query";
	
	private QueryFacet(String name) {
		super(name, QUERY, null);
	}
	
	public static QueryFacet newQueryFacet(String name) {
		return new QueryFacet(name);
	}

	/** query */
	public QueryFacet query(String query) {
		put(QUERY, query);
		return this;
	}
	
	@Override
	public Object params() {
		remove("field");
		return super.params();
	}
}
