import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Paper 
{
	/***
	 * 
	 * 	@author Arkady (Eric) Eidelberg ( ninuson123@gmail.com )
	 *	@author Samuel Wong ( sawong@ucalgary.ca )
	 *
	 *	This is a container class for each paper in the input class
	 */
	
	public ArrayList<String> authors;
	public String title;
	public String venue;
	public String year;
	public ArrayList<String> key_words;
	
	public Paper(JSONObject j_obj) throws Exception
	{
		// Init collections
		this.authors = new ArrayList<String>();
		this.key_words = new ArrayList<String>();
		
		// Init single properties
		this.title = (String) j_obj.get("title");
		this.venue = (String) j_obj.get("venue");
		this.year = (String) j_obj.get("year");
		
		// Populate collections
		JSONArray authors_arr = (JSONArray) j_obj.get("authors");
		for (Object author_obj : authors_arr)
		{
			String author = (String)author_obj;
			this.authors.add(author);
		}
		
		JSONArray key_wrods_arr = (JSONArray) j_obj.get("keywords");
		for (Object key_word_obj : key_wrods_arr)
		{
			String key_word = (String)key_word_obj;
			this.key_words.add(key_word);
		}
	}

}
