import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

  /**
  * The graphmaker class is the primary class of this program. It reads in the node and edge file and then updates and prints out the graph information to the terminal.
  */
public class GraphMaker{
  //a hashmap stores all the nodes in the graph.
  static HashMap<String, Node> nodes = new HashMap<String, Node>();
  //infected nodes keeps track of infected nodes so they're in one place when I'm looking for them.
  static ArrayList<Node> infectedNodes = new ArrayList<Node>();
  static String ppe; 
  
   /**
    * loads in the map for future use.
    * @param nodeFile: String represetation of the file of nodes.
    * @param edgeFile: String represetation of the file of edges.
    * @param ppe: a String representing whether ppe was used or not.
    * @contructor
    */
  public GraphMaker(String nodeFile, String edgeFile, String ppe){
    this.ppe = ppe;
    File inputFile = new File(nodeFile);
    Scanner scanner = null;
    try {
      scanner = new Scanner(inputFile);
    } 
    catch (FileNotFoundException e) {
        System.err.println(e);
        System.exit(1);
    }
    //parse each line of the node file
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      ArrayList<String> currNode = new ArrayList<>(Arrays.asList(line.split(",")));

       String name = currNode.get(0);
       int num = Integer.parseInt(currNode.get(1));
       boolean infected = false;
       if (num == 1){
         infected = true;
       }

        Node newNode = new Node(name, infected);
        nodes.put(name, newNode);
      }
      scanner.close();  

    File inputFile2 = new File(edgeFile);
    scanner = null;
    try {
      scanner = new Scanner(inputFile2);
    } 
    catch (FileNotFoundException e) {
        System.err.println(e);
        System.exit(1);
    }
    //parse each line of the edge file
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      ArrayList<String> currEdge = new ArrayList<>(Arrays.asList(line.split(",")));

        int weight = Integer.parseInt(currEdge.get(0));
        String node1 = currEdge.get(1);
        String node2 = currEdge.get(2);

        Edge newEdge = new Edge(node1, node2, weight);

        nodes.get(node1).addEdge(newEdge);
        nodes.get(node2).addEdge(newEdge);
      }
      scanner.close(); 
  }

   /**
    * dayDisplayer updates the graph and then prints out the information for that day.
    * @param day: int representing which day of the simulation it is currently.
    * @param disease: the disease that is being simulated. needed to update the graph.
    * @return: string representing the information for that day.
    */

  public String dayDisplayer(int day, Disease disease){
    String temp = "day " + (day + 1) + ":";

    for (Node node: infectedNodes){
    disease.update(node, nodes);
    }

    for (String key: nodes.keySet()){

      if (nodes.get(key).infected){
        temp = temp + "[" + nodes.get(key).name + ":" + "infected" + "] ";
      }
      else{
        temp = temp + "[" + nodes.get(key).name + ":" + "safe" + "] ";
      }
    }
    return temp;
  }

   /**
    * finalDisplay is used at the end of the program to display some information about the simulation.
    * @param day: int representing the total number of days simulated.
    * @param disease: the disease that is being simulated. needed to display information about the disease.
    * @return: string representing the simulation information.
    */

  public String finalDisplay(int day, Disease disease){
    String temp = "[Disease name: " + disease.getName() + "]" +" [infection rate: " + disease.getRate() + "%]" + " [days elapsed: " + day + "] " + " [" + infectedNodes.size() + " out of " + nodes.size() + " people infected] " + " [" + ppe + "]" ;
    return temp;
  }

   /**
    * Main function, reads in information, the "meat" of the program.
    * @param args[]: String args[]
    * @void
    */
  public static void main(String[] args) {
    if (args.length != 6) {
      System.out.println("oops! invalid number of command line arguments");
      System.exit(1);
      }

      String diseaseName = args[0];
      double infectionRate = Double.parseDouble(args[1]);
      String nodeFile = args[2];
      String edgeFile = args[3];
      boolean protection;
      int days = Integer.parseInt(args[5]);
      
      if (args[4].equals("PPE")){
        protection = true;
      }
      else if (args[4].equals("NoPPE")){
        protection = false;
      }
      else{
        System.out.println("oops! invalid indcation of personal protective equipment");
        protection = false;
      System.exit(1);
      }

      String ppeStatus;

      if (protection){
        ppeStatus = "PPE protected";
      }
      else{
        ppeStatus = "no PPE used";
      }
    //making the actual instance of graphMaker
    GraphMaker maker = new GraphMaker(nodeFile, edgeFile, ppeStatus);
    Disease disease;
    //This is where superclass subclass interaction is important. I need a disease, but depending on user input, a PPE of NoPPE subclass will be created.
    if (protection){
      disease = new PPE(infectionRate, diseaseName);
    }
    else{
      disease = new NoPPE(infectionRate, diseaseName);
    }
    
    //prints the intial information of the graph
    String intial = "day 0:";
    for (String key: nodes.keySet()){
      if (nodes.get(key).infected){
        intial = intial + "[" + nodes.get(key).name + ":" + "infected" + "] ";
      }
      else{
        intial = intial + "[" + nodes.get(key).name + ":" + "safe" + "] ";
      }
    }
    System.out.println(intial + "\n");

    //updates and prints for each day of the simulation
    for (int i = 0; i < days; i ++){

      System.out.println(maker.dayDisplayer(i, disease) + "\n");
    
    // after an update, this will add all new infected nodes to the infectedNodes ArrayList. That arraylist makes accessing infected nodes more simple.
      for (String key: nodes.keySet()){
        if (nodes.get(key).infected){
          if (!infectedNodes.contains(nodes.get(key))){
            infectedNodes.add(nodes.get(key));
          }
        }
      }
    }
    System.out.println(maker.finalDisplay(days, disease));
  }
}