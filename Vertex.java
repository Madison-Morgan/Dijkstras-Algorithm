// CSI2110 Fall 2017 Assignment 4: Paris Metro: Riding Paris Subway Network
// ==========================================================================
//   Madison Morgan
//	 mmorg031@uottawa.ca
//	 8287926
// ==========================================================================
//	 Description: This class will represent a station, or a vertex in the
//		graph. It contains constructor, compare, and string representation
//		methods.
// ==========================================================================


import java.util.LinkedList;
import java.util.List;

public class Vertex implements Comparable<Vertex>{

	//holds the station name of the station
	public String stationName;
	//holds the position of the station in the graph datastructure
	public int pos;
	//represents outgoing edges, where this vertex is the source
	public List<Edge> edgeList;
	//represents incoming edges, where this vertex is the destination
	public List<Edge> incomingEdgeList;
	//for 2ii)&iii) - this represents the minimum distance of the vertex form the
	//source vertex
	public double minDistance;
	//for 2ii)&iii) - this represents the previous vertex directed along a path
	// from the source to this vertex
	public Vertex previous;


  /**
   * Creates empty vertex object
   */	
	public Vertex(){
		pos=-1;
	}


  /**
   * Creates vertex object, with given station name and position,
   * initially, the lists are init to empty linked lists
   * and the minimum distance to positive infinity to be used later for 2ii)&iii)
   */	
	public Vertex(String stationNames, int poss){
		stationName=stationNames;
		pos=poss;
		edgeList=new LinkedList<Edge>();
		incomingEdgeList= new LinkedList<Edge>();
		minDistance= Double.POSITIVE_INFINITY;
	}


  /**
   * implements the vertex to compare the minimum distance from
   * one vertex to another
   */	
	public int compareTo(Vertex other){
		return Double.compare(minDistance, other.minDistance);
	}


  /**
   * Method to provide string representation
   */	
	public String toString(){
		return String.valueOf(pos);
	}


	/// NOTE: There are no setter or getter methods 
	// as this will not be changed within the algorithms
	// and it will reduce the amount of function-calls to access the
	// instance variables (why they are public and not private)
	// for the purposes of this lab, providing easier readability and simplicity
	// **alternatively instance variables could have been made private with
	// setter and getter methods to obtain them.

}