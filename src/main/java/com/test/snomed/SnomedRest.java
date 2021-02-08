
package com.test.snomed;

import java.net.URLEncoder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.json.JSONObject;

public class SnomedRest {

  /** The base url. */
  private final String baseUrl = "https://browser.ihtsdotools.org/snowstorm/snomed-ct";

  /** The edition. */
  private final String edition = "MAIN";

  /** The version. */
  private final String version = "2019-07-31";

  /**
   * Instantiates an empty {@link SnomedRest}.
   */
  public SnomedRest() {

  }


  public String getConceptsByString(String query) throws Exception {

    validateNotEmpty(query, "query");
    final Client client = ClientBuilder.newClient();
    String url = baseUrl + "/browser/" + edition + "/" + version+ "/concepts?term="+ URLEncoder.encode(query == null ? "" : query, "UTF-8").replaceAll("\\+", "%20") + "&activeFilter=true&offset=0&limit=50";
    final WebTarget target = client.target(url);
    
    System.out.println(url);

    final Response response = target.request(MediaType.APPLICATION_JSON).get();
    final String resultString = response.readEntity(String.class);
    if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {	
    JSONObject json = new JSONObject(resultString);
    int total =	 json.getInt("total");
    
    return String.valueOf(total);
 
    } else {
      throw new Exception(response.toString());
    }
}
  
  
  
  public String getConceptById(String query) throws Exception {

	    validateNotEmpty(query, "query");
	    final Client client = ClientBuilder.newClient();
	    String url = baseUrl + "/browser/" + edition + "/" + version+ "/concepts/"+query;
	    final WebTarget target = client.target(url);
	    
	    System.out.println(url);

	    final Response response = target.request(MediaType.APPLICATION_JSON).get();
	    final String resultString = response.readEntity(String.class);
	    if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {	
	    JSONObject json = new JSONObject(resultString);
	
	    String term = json.getJSONObject("fsn").getString("term");
	    
	    return term;
	 
	    } else {
	      throw new Exception(response.toString());
	    }
	}

  
  public String getDescriptionById(String query) throws Exception {

	    validateNotEmpty(query, "query");
	    final Client client = ClientBuilder.newClient();
	    String url = baseUrl + "/" + edition + "/" + version + "/descriptions/" + query;
	    final WebTarget target = client.target(url);
	    
	    System.out.println(url);

	    final Response response = target.request(MediaType.APPLICATION_JSON).get();
	    final String resultString = response.readEntity(String.class);
	    if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {	
	    JSONObject json = new JSONObject(resultString);
	    String term = json.getString("term");
	    return term;
	 
	    } else {
	      throw new Exception(response.toString());
	    }
	}
  
  
  public String getDescriptionsByStringFromProcedure(String searchTerm, String semanticTag) throws Exception {

	    validateNotEmpty(searchTerm, "query");
	    final Client client = ClientBuilder.newClient();
	    
	    String url = baseUrl + "/browser/" + edition + "/" + version+ "/descriptions?term="+ URLEncoder.encode(searchTerm == null ? "" : searchTerm, "UTF-8").replaceAll("\\+", "%20") + "&conceptActive=true&semanticTag=" + URLEncoder.encode(semanticTag == null ? "" : semanticTag, "UTF-8").replaceAll("\\+", "%20")+ "&groupByConcept=false&searchMode=STANDARD&offset=0&limit=50";
	    final WebTarget target = client.target(url);
	    
	    System.out.println(url);

	    final Response response = target.request(MediaType.APPLICATION_JSON).get();
	    final String resultString = response.readEntity(String.class);
	    if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {	
	    JSONObject json = new JSONObject(resultString);
	    
	    int total =	 json.getInt("totalElements");
	    
	    return String.valueOf(total);
	    
	 
	    } else {
	      throw new Exception(response.toString());
	    }
	}
  


  /**
   * Validate not empty.
   *
   * @param parameter the parameter
   * @param parameterName the parameter name
   */
  @SuppressWarnings("static-method")
  protected void validateNotEmpty(String parameter, String parameterName) {
    if (parameter == null) {
      throw new IllegalArgumentException("Parameter " + parameterName
          + " must not be null.");
    }
    if (parameter.isEmpty()) {
      throw new IllegalArgumentException("Parameter " + parameterName
          + " must not be empty.");
    }
  }

  /**
   * Validate not empty.
   *
   * @param parameter the parameter
   * @param parameterName the parameter name
   */
  @SuppressWarnings("static-method")
  protected void validateNotEmpty(Long parameter, String parameterName) {
    if (parameter == null) {
      throw new IllegalArgumentException("Parameter " + parameterName
          + " must not be null.");
    }
  }

  
}
