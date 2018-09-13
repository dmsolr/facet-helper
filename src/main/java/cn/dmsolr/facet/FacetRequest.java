package cn.dmsolr.facet;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;

import cn.dmsolr.facet.response.FacetResponse;

public class FacetRequest extends SolrRequest<FacetResponse> {
//	private static final Logger logger = LoggerFactory.getLogger(FacetRequest.class);
	private static final long serialVersionUID = 9039905330333393302L;

	private final ModifiableSolrParams params = new ModifiableSolrParams();
	
	public FacetRequest() {
		super(METHOD.GET, "/select");
		params.set(CommonParams.ROWS, 0);
	}

	public FacetRequest facetQuery(JsonFacet jFacet) {
		params.add("json.facet", jFacet.toQueryString());
		return this;
	}

	public FacetRequest setQuery(String query) {
		params.set(CommonParams.Q, query);
		return this;
	}
	
	public FacetRequest addFilterQuery(String filter) {
		params.add(CommonParams.FQ, filter);
		return this;
	}
	
	@Override
	public SolrParams getParams() {
		return params;
	}
	
	public FacetRequest set(String name, int val) {
		params.set(name, val);
		return this;
	}
	
	public FacetRequest set(String name, boolean val) {
		params.set(name, val);
		return this;
	}
	
	public FacetRequest set(String name, String val) {
		params.set(name, val);
		return this;
	}
	
	public FacetRequest set(String name, String... val) {
		params.set(name, val);
		return this;
	}
	
	public FacetRequest add(String name, String... val) {
		params.add(name, val);
		return this;
	}
	
	@Override
	public String getPath() {
		String qt = params.get(CommonParams.QT);
		if (qt == null) {
			qt = super.getPath();
		}
		if (qt != null && qt.startsWith("/")) {
			return qt;
		}
		return "/select";
	}
	
	@Override
	protected FacetResponse createResponse(SolrClient client) {
		return new FacetResponse(client);
	}
}
