package dom.regulation;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.isis.applib.annotation.Action;

public class RESTclientTest {

	// From https://docs.jboss.org/resteasy/docs/3.0-beta-3/userguide/html/RESTEasy_Client_Framework.html
	
	@Action
	public String getTest() {
	 
		/**
		 * Conceptually, the steps required to submit a request are the following:
    1)obtain an Client instance
    2)create a WebTarget pointing at a Web resource
    3)build a request
    4)submit a request to directly retrieve a response or get a prepared Invocation for later submission
		 */
		
		
//	 Client client = ClientBuilder.newBuilder().build();
	 
	// Create a client instance:
	Client client = ClientBuilder.newClient();
	
	// Create a WebTarget:
	 WebTarget target = client.target("192.168.33.10:9000");
	 
	 // Build a request:
     Response response = target.request().get();
     String value = response.readEntity(String.class);
     response.close();  // You should close connections!
	
     return value;
	}
 
}
