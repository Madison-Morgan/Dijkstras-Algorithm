// CSI2110 Fall 2017 Assignment 4: Paris Metro: Riding Paris Subway Network
// ==========================================================================
//   Madison Morgan
//	 mmorg031@uottawa.ca
//	 8287926
// ==========================================================================
//	 Description: This class implements a representation of a path
//		from one station to another, as an edge is how it represents.
//		Contains two constructors and a string conversion method.
// ==========================================================================


public class Edge{
	
	//holds the weight of the destination vertex
	public Double weight;
	//holds the destination vertex as designated by the source
	public Vertex dest;
	//for 2iii) - true if path from source to destination is broken, 
	// false if not broken
	public boolean broken;


  /**
   * Creates empty edge object
   */	
	public Edge(){
		dest=null;
		weight= new Double(0);
		broken=false;
	}

  /**
   * Creates edge object with defined weight and destination vertex
   * Initially, the path will not be broken
   */
	public Edge(Double weights, Vertex nodes){
		weight=weights;
		dest=nodes;
		broken=false;
	}


  /**
   * Method to provide string representation
   */
	public String toString(){
		return "weight:"+weight.toString()+" dest:"+dest.toString();
	}




	/// NOTE: There are no setter or getter methods 
	// as this will not be changed within the algorithms
	// and it will reduce the amount of function-calls to access the
	// instance variables (why they are public and not private)
	// for the purposes of this lab, providing easier readability and simplicity
	// **alternatively instance variables could have been made private with
	// setter and getter methods to obtain them.
}