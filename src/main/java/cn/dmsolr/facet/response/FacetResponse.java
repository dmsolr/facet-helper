package cn.dmsolr.facet.response;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.response.SolrResponseBase;
import org.apache.solr.common.util.NamedList;

@SuppressWarnings("unchecked")
public class FacetResponse extends SolrResponseBase {
	private static final long serialVersionUID = -7400234187034610444L;
	private NamedList<Object> _facets = null;
	private int count = -1;

	public FacetResponse(SolrClient client) {
	}
	
	@Override
	public void setResponse(NamedList<Object> response) {
		super.setResponse(response);
		Object facets = response.get("facets");
		if (facets != null)
			_facets = (NamedList<Object>) facets;
		count = (Integer)_facets.get("count");
	}
	
	public Buckets getBuckets(String name) {
		Object object = _facets.get(name);
		if (object == null) {
			return null;
		}
		return new Buckets((NamedList<Object>)object);
	}
	
	public int getCount() {
		return count;
	}
}
