# NetDrillerParserUtility
This is a utility written for the 672 class with Dr. Rokne.
# Usage with the proper command line parameters:
java -jar Parser.jar <MODE_PARAMETER> <INPUT_FILE_NAME> [Optional: <OUTPUT_FILE_NAME> - defaults to output.csv]

# Mode parameters:
1 - will produce a dataset of authors with relationship based on number of coauthored papers from the input file
2 - will produce a dataset of keywords from titles of papers where the relationship between keywords is based on their co-occurrence in same title.
3 - will produce a dataset for weighted two-mode network between authors and keywords

# Example of the input JSON file:
[
	{
		"authors":["Hui, C.","Wallace, W.","Magdon-Ismail, M."],
		"title":"Diffusion of Actionable Information in Social Networks",
		"venue":"Sringer",
		"year":"2014",
		"keywords":["Information propagation",
					"Large-scale network",
					"Social relationships"]
	},		
	{
		"authors":["Nedialkov, N."],
		"title":"Interval Tools for ODEs and DAEs",
		"venue":"IEEE",
		"year":"2006",
		"keywords":["software packages",
					"interval tools",
					"validated solver"]
	},
	{
		"authors":[	"Ratschek, H.",
					"Rokne, J."],
		"title":"Interval Methods",
		"venue":"Sringer",
		"year":"1995",
		"keywords":["Global unconstrained optimization",
					"global constrained optimization",
					"interval tools",
					"interval Newton method",
					"interval Gauss-Seidel method",
					"bisections",
					"accelerating devices",
					"termination criteria"]
	},
	{
		"authors":[	"Rokne, J.",
					"Lancaster, P."],
		"title":"Complex interval arithmetic",
		"venue":"Communications of the ACM",
		"year":"1971",
		"keywords":["Global unconstrained optimization",
					"global constrained optimization",
					"interval tools",
					"interval Newton method",
					"interval Gauss-Seidel method",
					"bisections",
					"accelerating devices",
					"termination criteria"]
	}
]

# Full usage example:
java -jar Parser.jar 1 input.json
java -jar Parser.jar 1 input.json spacial_output_name.csv
