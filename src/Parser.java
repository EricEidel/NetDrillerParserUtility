import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/***
 * 
 * 	@author Arkady (Eric) Eidelberg ( ninuson123@gmail.com )
 *	@author Samuel Wong ( sawong@ucalgary.ca )
 *
 *	This is a utility to produce data sets for the NetDriller program based on specific requirements.
 *	These requirements are outlined under the print_instructions method.
 *	This is the work of our assignment 2 for the CPSC 572/672 in the University of Calgary for the Winter 2017 Semester.
 *	Course instructor: Dr. Rokne
 *
 */
public class Parser 
{
	public static int COAUTHORED_PAPERS_MODE = 1;
	public static int TILTES_AND_KEYWORDS_MODE = 2;
	public static int AUTHORS_AND_KEYWORDS_MODE = 3;
	
	/***
	 * @param args[0] - <MODE_PARAMETER>, an integer with the value of 1, 2 or 3
	 * @param args[1] - <INPUT_FILE> The input file, must follow the JSON schema described in print_instructions.
	 */
	public static void main(String[] args)
	{
		// Check that both a mode parameter and a valid input file were provided.
		if (!(args.length == 2) && !(args.length == 3))
		{
			System.out.println("Wrong usage of the Parser utility.");
			print_instructions_and_exit_program();
		}
		
		// Check that the mode parameter was provided and is valid, set the mode
		int mode = -1;
		try
		{
			mode = Integer.parseInt(args[0]);
		}
		catch (Exception e)
		{
			System.out.println("Invalide operation mode, please indicate with a single digit as the first parameter which mode to run the Parser utility. Please follow provided instructions.");
			print_instructions_and_exit_program();
		}
		
		// Check that the input file provided is actually a good file and can be parsed as JSON.
		String file_path = args[1];
		String json = parse_input_file(file_path);
		if (json.trim().isEmpty())
		{
			System.out.println("The input file was empty. Please follow provided instructions.");
			print_instructions_and_exit_program();
		}
		
		// Check if the output file name parameter was provided
		String output_name = "output.csv";
		if (args.length == 3)
		{
			if (check_path(output_name))
			{
				output_name = args[2];
			}
			else
			{
				System.out.println("The provided output file name bad. Please review usage instructions and provide a proper name.");
				print_instructions_and_exit_program();
			}
		}
		
		// Test the JSON file to see the content is properly parsed JSON and contains the proper fields required from the input
		JSONParser parser = new JSONParser();
		List<Paper> papers = new ArrayList<Paper>(); 
		try 
		{
			// This is the outermost JSON array that contains a list of papers
			JSONArray array = (JSONArray) parser.parse(json);
			
			for (Object obj : array)
			{
				JSONObject json_obj = (JSONObject)obj;
				try
				{
					Paper temp = new Paper(json_obj);
					papers.add(temp);
				}
				catch (Exception e)
				{
					System.out.println("Was unable to parse parts of the input JSON. Please follow instructions.");
					System.out.println("Error at: " + json_obj.toJSONString());
					print_instructions_and_exit_program();
				}
			}
		} 
		catch (ParseException e) 
		{
			System.out.println("The input file contained inproper JSON. Please follow provided instructions.");
			print_instructions_and_exit_program();
		}
		
		System.out.println("The file " + file_path + " was successfuly parsed!");
		
		// Try to open the output file
		File output_file = new File(output_name);
		
		if (mode == COAUTHORED_PAPERS_MODE)
		{
			// Do the co-authored papers mode
			System.out.println("Creating a dataset based on co-authored papers: " + output_name);
			do_co_authors(output_file);
		}
		else if (mode == TILTES_AND_KEYWORDS_MODE)
		{
			// Do the titles and keywords mode
			System.out.println("Creating a dataset based on titles and keywords: " + output_name);
			do_titles_and_keywords(output_file);
		}
		else if (mode == AUTHORS_AND_KEYWORDS_MODE)
		{
			// Do the authors and keywords mode
			System.out.println("Creating a dataset based on authors and titles: " + output_name);
			do_authors_and_keywords(output_file);
		}
		else
		{
			System.out.println("Invalide operation mode, please indicate with a single digit as the first parameter which mode to run the Parser utility. Please follow provided instructions.");
			print_instructions_and_exit_program();
		}
	}
	
	/***
	 * 
	 * @param output_file - This is the file to which the resulting dataset will be written in a csv foramt.
	 */
	private static void do_co_authors(File output_file)
	{
		try
		{
			
		}
		catch (Exception e)
		{
			System.out.println("Was not able to write to output file. Please check the file can be created!");
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	/***
	 * 
	 * @param output_file - This is the file to which the resulting dataset will be written in a csv foramt.
	 */
	private static void do_titles_and_keywords(File output_file)
	{
		try
		{
			
		}
		catch (Exception e)
		{
			System.out.println("Was not able to write to output file. Please check the file can be created!");
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	/***
	 * 
	 * @param output_file - This is the file to which the resulting dataset will be written in a csv foramt.
	 */
	private static void do_authors_and_keywords(File output_file)
	{
		try
		{
			
		}
		catch (Exception e)
		{
			System.out.println("Was not able to write to output file. Please check the file can be created!");
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	
	/***
	 * 
	 * @param file_path - the full path to the input file
	 * @return a string that includes the entire content from the input file
	 */
	public static String parse_input_file(String file_path)
	{
		String json = "";
		try 
		{
			File f = new File(file_path);
			json = new String(Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8);
			
		} 
		catch (FileNotFoundException e) 
		{
			// File not found
			System.out.println("The indicated file was not found. Please make sure the file " + file_path + " exists at the specified path.");
			print_instructions_and_exit_program();
		}
		catch (Exception e) 
		{
			// File not found
			System.out.println("There was an error raeding and/or closing the file. Make sure the file " + file_path + " exists and that the proper permissions are given to it.");
			print_instructions_and_exit_program();
		}
		
		return json;
	}
	
	/***
	 * This method prints out the instructions of the program
	 */
	public static void print_instructions_and_exit_program()
	{
		System.out.println("Please user the proper command line parameters:");
		System.out.println("java -jar Parser.jar <MODE_PARAMETER> <INPUT_FILE_NAME> [Optional: <OUTPUT_FILE_NAME> - defaults to output.csv]");
		System.out.println("Mode parameters:");
		System.out.println("1 - will produce a dataset of authors with relationship based on number of coauthored papers from the input file");
		System.out.println("2 - will produce a dataset of keywords from titles of papers where the relationship between keywords is based on their co-occurrence in same title.");
		System.out.println("3 - will produce a dataset for weighted two-mode network between authors and keywords");
		System.out.println("Input file has to be a in JSON format that adheres to the following schema:");
		System.out.println("[ { \"authors\": [\"name_1\", \"name_2\"], \"title\": \"title_of_article\", \"venue\": \"name_of_venue\", \"year\": 1988, \"keywords\": [\"keyword1\", \"keyword2\"] } , <ADDITIONAL ARTICLES FOLLOWING THE SAME JSON SCHEMA> ]");
		System.out.println("Full usage example:");
		System.out.println("java -jar Parser.jar 1 input.json");
		System.out.println("java -jar Parser.jar 1 input.json spacial_output_name.csv");
		System.exit(0);
	}
	
    /**
     * * Checks if a string is a valid path.
     * Null safe.
     * Based on this stack overflow question: 
     * http://stackoverflow.com/questions/468789/is-there-a-way-in-java-to-determine-if-a-path-is-valid-without-attempting-to-cre
     *  
     * Calling examples:
     *    path("c:/test");      //returns true
     *    path("c:/te:t");      //returns false
     *    path("c:/te?t");      //returns false
     *    path("c/te*t");       //returns false
     *    path("good.txt");     //returns true
     *    path("not|good.txt"); //returns false
     *    path("not:good.txt"); //returns false
     */
    public static boolean check_path(String path) {

        try 
        {
            Paths.get(path);

        }
        catch (InvalidPathException |  NullPointerException ex) 
        {
            return false;
        }

        return true;
    }
}
