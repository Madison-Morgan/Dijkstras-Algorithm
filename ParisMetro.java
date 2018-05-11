// CSI2110 Fall 2017 Assignment 4: Paris Metro: Riding Paris Subway Network
// ==========================================================================
//   Madison Morgan
//	 mmorg031@uottawa.ca
//	 8287926
// ==========================================================================
//	 Description: This class will represent the paris subway network, in 
//		a graph representation implemented as an adjacency list
// ==========================================================================
//	 References:	
//		[1] WeightedGraph.java. CSI 2110: Lab 10 Shortest Paths. 
//				Virtual Campus (2017).
//
// ==========================================================================


import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;



public class ParisMetro{

	// representation of subway network, represented as an adjacency list
	public AdjacencyList subwayNetwork;

	
  /**
   * Creates subway network by calling readMetro
   * and intializing all vertices/edges from the given txt file
   * for the purposes of this lab, this is hardcoded as indicated by lucia
   */
	public ParisMetro() throws Exception,IOException{
		subwayNetwork=readMetro("metro.txt");
	}


  /**
   * Reads from text file and initializes all stations to vertices
   * and edges to paths form station to station and returns
   * the graph repr.
   */
	//see references, this was adapted from [1]
	public static AdjacencyList readMetro(String fileName) throws Exception,IOException{

		// object to read file line by line
		BufferedReader graphFile = new BufferedReader(new FileReader(fileName));
		String line;

		
		//read first line, store into instance variables
		// as total number of vertices and edges
		line=graphFile.readLine();
		StringTokenizer firstLine= new StringTokenizer(line);
		int numVertices=Integer.parseInt(firstLine.nextToken());
		int numEdges=Integer.parseInt(firstLine.nextToken());

		// init. graph
		AdjacencyList sNetwork=new AdjacencyList(numVertices,numEdges);

		
		//read all vertices into graph with station name and number
		while (!( (line=graphFile.readLine()).equals("$")  ) ) {
			StringTokenizer st = new StringTokenizer(line);
			//first token repr. position of vertex/station
			int pos=Integer.valueOf(st.nextToken());
			//remaing tokens are converted to string,
			// to represent station name
			String sname=new String();
			while(st.hasMoreTokens()){
				sname=sname+" "+st.nextToken();
			}
			//init vertex and add into graph in correct place
			Vertex v=  new Vertex(sname,pos);		
			sNetwork.setVertice(pos,v);
		}


		//insert all edges into the graph, with correct destination and
		// source vertices with correct weight
		while ((line = graphFile.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			//get source, dest, weight from each token
			Integer source = new Integer(st.nextToken());
			Integer dest = new Integer(st.nextToken());
			Double weight = new Double(st.nextToken());
			// weight is -1. then it is a walking path which as specified 
			// by the assignment 4 doc. is to be constant 90 sec.
			if(weight==-1){
				weight=new Double(90);
			}
			
			//initialize outgoing edge
			Vertex dv= sNetwork.getVertice(dest);
			Edge e = new Edge(weight,dv);
			sNetwork.setEdge(source,e);

			//intialize incoming edge
			Vertex sv= sNetwork.getVertice(source);
			Edge eo = new Edge(weight, sv);
			sNetwork.setIncomingEdges(dest,eo);
		}
		
		return sNetwork; 

	}






//--------------------------- algorithms for Q2-------------------------------------//

//---------------------------------------Q2) i)----------------------------------//

  /**
   * recursive method called by recursion to identify all stations on the path
   */
	public void identifyAllStations(int pos, List<Integer> viewed){
		//track the vertice used, so algo does not circle
		// if there are bidirectional paths form station
		viewed.add(new Integer(pos));
		//iterate through all outgoing edges of vertice/station
		// designated by pos
		List<Edge> lst=subwayNetwork.getEdges(pos);
		Iterator<Edge> it=lst.iterator();

		while(it.hasNext()){
			Edge e=it.next();
			//identify all outgoing edges of the source, and all their 
			// outgoing edges and print.. disregard walking edges
			if(!(viewed.contains(e.dest.pos)) && e.weight!=90){
				identifyAllStations(e.dest.pos, viewed);
			}

		}
	}

  /**
   * method to initiate recursion to identify all stations on a path indicated 
   *	by the vertice/station as designated by pos
   */
	public void identifyAllStations(int pos){
		//iterate through the outgoing edges/paths of the vertice/station
		List<Edge> lst=subwayNetwork.getEdges(pos);
		Iterator<Edge> it=lst.iterator();
		//create a list to track the vertices visited, so not to visit them twice
		List<Integer> viewed= new LinkedList<Integer>();
		viewed.add(new Integer(pos));	
		//create a list to print path also



		while(it.hasNext()){
			Edge e=it.next();
			//identify all outgoing edges of the source, and all their 
			// outgoing edges and print.. disregard walking edges
				if(!(viewed.contains(e.dest.pos)) && e.weight!=90){
				identifyAllStations(e.dest.pos, viewed);
			}
		}

		System.out.println(viewed);
	}	






//---------------------------------------Q2) iii)----------------------------------//

  /**
   * method to break edges/paths that are incoming/outgoing from vertice
   * v, a station not in use designated by N3 in command line
   */
	public void breakEdges(Vertex v){

		List<Edge> incoming= v.incomingEdgeList;
		List<Edge> outgoing= v.edgeList;

		Iterator<Edge>it1=incoming.iterator();
		Iterator<Edge>it2=outgoing.iterator();

		// iterate through incoming edges and set broken
		// to true, so they may not be used
		while(it1.hasNext()){
			it1.next().broken=true;
		}
		// iterate through outgoing edges and set broken
		// to true, so they may not be used
		while(it2.hasNext()){
			it2.next().broken=true;
		}
	}





//---------------------------direct User repsonse to algo----------------------------//


	public static void main(String[] args) throws Exception, IOException{
		//init paris metro object
		ParisMetro p= new ParisMetro();

		//use different algo and function depending on user input
		if (args.length==3){
			// if three numbers present break args[2] station
			Vertex v=p.subwayNetwork.getVertice(Integer.parseInt(args[2]));
			p.breakEdges(v);
			// compute shortest path from args[0] to args[1] stations
			// first call compute paths to compute all shortest path distances from
			// source vertice to all other reachable vertices using Dijkstra's algo
			Dijkstra.computePaths(p.subwayNetwork.getVertice(Integer.parseInt(args[0])),p, Integer.parseInt(args[2]) );
			System.out.println("Distance to "+args[1]+" : "+p.subwayNetwork.getVertice(Integer.parseInt(args[1])).minDistance);
			//then call this method to print out path
			List<Vertex> path= Dijkstra.getShortestPathTo(p.subwayNetwork.getVertice(Integer.parseInt(args[1])));
			System.out.println("Path: "+path);
		}

		else if (args.length==2){
			// compute shortest path from args[0] to args[1] stations
			// first call compute paths to compute all shortest path distances from
			// source vertice to all other reachable vertices using Dijkstra's algo
			Dijkstra.computePaths(p.subwayNetwork.getVertice(Integer.parseInt(args[0])),p, -1);
			System.out.println("Distance to "+args[1]+" : "+p.subwayNetwork.getVertice(Integer.parseInt(args[1])).minDistance);
			//then call this method to print out path
			List<Vertex> path= Dijkstra.getShortestPathTo(p.subwayNetwork.getVertice(Integer.parseInt(args[1])));
			System.out.println("Path: "+path);
		}

		else if (args.length==1){
			//if one number present identify all stations on the path indic. by args[0] station
			p.identifyAllStations(Integer.parseInt(args[0]));
		}

	}
}