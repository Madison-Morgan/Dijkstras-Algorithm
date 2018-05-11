// CSI2110 Fall 2017 Assignment 4: Paris Metro: Riding Paris Subway Network
// ==========================================================================
//   Madison Morgan
//   mmorg031@uottawa.ca
//   8287926
// ==========================================================================
//   Description: This class will perform functionalities on the 
//      paris subway network, computing the shortest paths distance and path.
//    
// ==========================================================================
//   References:    
//      [2] https://stackoverflow.com/questions/17480022/java-
//          find-shortest-path-between-2-points-in-a-distance-weighted-map
//      [3] https://github.com/vaishnav/Dijkstra/blob/master/Dijkstra.java
// ** also alters Vertex class mindistance and previous instance variables
// ==========================================================================


import java.util.*;

public class Dijkstra
{

  /**
   * Calculates the shortest path from the source vertex
   * to all reachable vertices using Dijkstra's algorithm
   * Works like this:
   *    1) Takes the unvisited vertex with the minimum weight
   *    2) visits all its neighbors
   *    3) updates the distance for all the neighbors (in queue)
   * And repeats this process untill all connected nodes are visited
   */    
  // SEE REFERENCES: adapted from [2] and [3]
    public static void computePaths(Vertex source, ParisMetro p, int broken)
    {
        //set the source vertex distance to 0 (as distance source->source=0)
        source.minDistance=0;
        //init queue , add source to keep track of nodes visited (cloud)
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);
        
        //keep track of total vertices tracked so as not to go in circles
        List<Integer> l=new LinkedList<Integer>();

    // while the cloud is not empty
    while (!vertexQueue.isEmpty()) {
        // take the head as u and...
        Vertex u = vertexQueue.poll();
        //(add position of u to the tracking list)
        l.add(new Integer(u.pos));


            // ...Visit each edge exiting u
            Iterator<Edge> it=(p.subwayNetwork.getEdges(u.pos)).iterator();
            while (it.hasNext())
            {

                Edge e= it.next();
                Vertex v = e.dest;
                Double weight = e.weight;
                // calculate the distance from u to the node v
                Double distanceThroughU = u.minDistance + weight;
                
                // if the distance is less then the min. distance, and not already visited, and  neither
                // the vertice and edge are broken
                if (distanceThroughU < v.minDistance && !l.contains(v.pos) && v.pos!=broken && !e.broken) {
                    //remove the node form the queue and update the distance and prev. value
                    vertexQueue.remove(v);
                    //update the distance
                    v.minDistance=distanceThroughU;
                    //update the path visited till now 
                    v.previous = u;
                    //reenter the node with the new distance
                    vertexQueue.add(v);

                }
            }

        }
    }


  /**
   * Calculates the shortest path from the source vertex to the target
   */    
  // SEE REFERENCES: adapted from [2] and [3]
    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        //we iterate through the target, to the previous vertice, to the previous
        // etc. and track it in a list (path)
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);

        //reverse the path so travel is from source to target and return it
        Collections.reverse(path);
        return path;
    }
}