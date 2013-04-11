package dk.dbc.searcher;

//http stuff
import java.net.HttpURLConnection;
import java.net.URL;

//IO and String stuff
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//util stuff
import java.util.HashMap;

/**
 * handles crafting from elements found in bibcraft.
 * The getCraftResult method takes the elements for the crafting attempt and 
 * makes a search against bib dk and returns a random result that fulfills the 
 * creterias defined by the elements
 * @author lvh
 */

public class CraftSearcher{

    /**
     * Transforms a map of elements into a serach result.
     * based on the elements in argument the method searches the opensearch 
     * service for a random result that fulfills the criterias from the elements 
     * @param elements a (@link HashMap) with the elements and their values  
     * @return String The result of the search/craft. not treated in any way yet 
     */
    public String getCraftResult( HashMap< String, String > elements ){
        
        //String query = buildQuery( elements );

        try{

            URL bibUrl = new URL( buildQuery( elements ) );
            HttpURLConnection connection = (HttpURLConnection)bibUrl.openConnection();
            connection.setRequestMethod( "GET" );
            connection.addRequestProperty ( "Content-type", "text/xml; charset=utf-8" ); 
          
            connection.setDoOutput( true );

            if (connection.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ connection.getResponseCode());
		}
 
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(connection.getInputStream())));
 
		StringBuilder sb = new StringBuilder();
                String line = "";
                while ( ( line = br.readLine() ) != null ) {
                  sb.append( line );
                }
                br.close();
                connection.disconnect();
                return sb.toString();
 
	  } catch( Exception e ){
              System.out.println( "damn: " + e.toString() );
              return "error";
        }
       
    }
        
    /**
     * Builds a rest url to search with right now just a hardcoded one...
     * @param elements a {@link HashMap} containing the elements search with
     * @return a String representation of the REST search 
     */
    private String buildQuery( HashMap<String, String> elements){
return "http://guesstimate.dbc.dk/~fvs/OpenLibrary/OpenBibdk/trunk/?action=search&query=phrase.subject%3Deventyr%20and%20term.type%3Dbog&agency=100200&profile=test&sort=random&start=1&stepValue=1&repository=solr4";

    }
}