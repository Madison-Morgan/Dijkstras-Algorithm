// CSI2110 Fall 2017 Assignment 4: Paris Metro: Riding Paris Subway Network
// ==========================================================================
//   Madison Morgan
//   mmorg031@uottawa.ca
//   8287926
// ==========================================================================
//   Description: This class will provide a representation of the whole
//      subway network, in the form of a graph. This graph is implemented
//      by an adjacency list.
//      
//      The vertices in this graph represent stations and the edges represent
//      a travel path form one consecutive station to another
//  
//      This class provides methods to set and get vertices/edges. getter methods
//      to obtain the number of vertices and edges. And a method
//      to provide a string representation.
// ==========================================================================


import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class AdjacencyList{
	
    //This array will hold all vertices, vertices will hold edge-lists to
    // keep track of edges
	public Vertex[] adjacencyList;
    //indicates number of vertices of adjacency list
	public int numVert;
    //indicates number of edges of adjacency list
	public int numEdge;



  /**
   * Creates Adjacency list, and init instance variables
   */
	public AdjacencyList(int numVertices, int numEdges) 
    {
    	numEdge=numEdges;
    	numVert=numVertices;
        adjacencyList = new Vertex[numVert];
    }



  /**
   * sets an edge from vertice designated by pos, to vertice
   * defined by the edge
   */ 
    public void setEdge(int pos, Edge insert){
        List<Edge> sls = (adjacencyList[pos]).edgeList;
        sls.add(insert);
    }



  /**
   * sets an incoming edge from vertice designated by edge,
   * to vertice designated by pos
   */
    public void setIncomingEdges(int pos, Edge insert){
        List<Edge> sls= (adjacencyList[pos]).incomingEdgeList;
        sls.add(insert);
    }


  /**
   * gets edge-list of outgoing edges designated
   * by vertice designated by to
   */ 
    public List<Edge> getEdges(int to){
        return (adjacencyList[to]).edgeList;
    }


  /**
   * sets vertice to v , in adjacency list designated by position pos
   */ 
    public void setVertice(int pos, Vertex v){
    	adjacencyList[pos]=v;
    }


  /**
   * gets vertice in list designated by position pos
   */ 
    public Vertex getVertice(int pos){
    	return adjacencyList[pos];
    }


//------------------Getter Methods for tracking-------------------------///

  /**
   * gets number of edges in list
   */ 
    public int NumEdges(){
    	return numEdge;
    }

  /**
   * gets number of vertices in list
   */ 
    public int NumVertices(){
    	return numVert;
    }
        
//------------Printing Method-------------------------------------------///

  /**
   * gets string representation of list
   */ 
    public String toString(){

    	String s=new String();
    	for(int i=0; i<numVert; i++){
    		s=s+" "+i+":"+ (adjacencyList[i]).toString();
    	}

    	return s;
    }
}