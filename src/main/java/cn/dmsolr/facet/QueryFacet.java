package cn.dmsolr.facet;

public class QueryFacet extends JsonFacet {
	private static final String QUERY = "query";
	
	private QueryFacet(String name, String query) {
		super(name, QUERY, null);
		query(query);
	}
	
	public static QueryFacet newQueryFacet(String name) {
		return new QueryFacet(name, null);
	}
			
	public static QueryFacet newQueryFacet(String name, String query) {
		return new QueryFacet(name, query);
	}

	/** query */
	public QueryFacet query(String query) {
		if (query != null)
			put(QUERY, query);
		return this;
	}
	
	@Override
	public Object params() {
		remove("field");
		return super.params();
	}
}
