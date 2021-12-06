import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;   
import java.io.IOException;
import java.io.BufferedWriter;

   /**
    * creates a randomly generated node file and a randomly generated edge file. however the user needs to set boundaries so the graph is valid.
    */
public class GraphGen{
  int nodeNum;
  int edgeNum;
  int minWeight;
  int maxWeight;
  int initInfected;

   /**
    * given parameters, this constructor writes to 2 files, one full of nodes, and the other full of edges.
    * @param nodeNum: int of how many nodes should be in the graph
    * @param edgeNum: int of how many edges should be in the graph
    * @param minWeight: int of the minimum weight of an edge
    * @param maxWeight: int of the maximum weight of an edge
    * @param initInfected: int how how many nodes are initially infected
    * @contructor
    */
  public GraphGen(int nodeNum, int edgeNum, int minWeight, int maxWeight, int initInfected){
    this.nodeNum = nodeNum;
    this.edgeNum = edgeNum;
    this.minWeight = minWeight;
    this.maxWeight = maxWeight + 1;
    this.initInfected = initInfected;

    try {
      File file = new File("autoGenNode.txt");
      File file2 = new File("autoGenEdge.txt");

      if(!file.exists()){
    	  file.createNewFile();
    	}
      if (!file2.exists()){
        file2.createNewFile();
      }

      FileWriter fw = new FileWriter(file, false);
      BufferedWriter myWriter = new BufferedWriter(fw);

      for (int i = 0; i < nodeNum; i ++){
        if (initInfected > 0){
          String currNode = "node" + i + ",1";
          myWriter.write(currNode);
          myWriter.newLine();
          initInfected = initInfected - 1;
        }
        else{
          String currNode = "node" + i + ",0";
          myWriter.write(currNode);
          myWriter.newLine();
        }
      }

      myWriter.close();

      FileWriter fw2 = new FileWriter(file2, false);
      BufferedWriter myWriter2 = new BufferedWriter(fw2);
      Random rand = new Random();

      for (int i = 0; i < edgeNum; i ++){
        int randNum = rand.nextInt(maxWeight - minWeight) + minWeight; 
        int edge1 = rand.nextInt(nodeNum);
        int edge2 = rand.nextInt(nodeNum);
        while (edge1 == edge2){
          edge2 = rand.nextInt(nodeNum);
        }
        String currEdge = randNum + ",node"+ edge1 + ",node" + edge2; 
        myWriter2.write(currEdge);
        myWriter2.newLine();
      }

      myWriter2.close();
      } 
    catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

   /**
    * Main function, reads in command line arguments and creates a graph.
    * @param args[]: String args[]
    * @void
    */
    public static void main(String[] args) {
      int nodeNum = Integer.parseInt(args[0]);
      int edgeNum = Integer.parseInt(args[1]);
      int minWeight = Integer.parseInt(args[2]);
      int maxWeight = Integer.parseInt(args[3]);
      int initInfected = Integer.parseInt(args[4]);

      GraphGen graph = new GraphGen(nodeNum, edgeNum, minWeight, maxWeight, initInfected);
    }

}