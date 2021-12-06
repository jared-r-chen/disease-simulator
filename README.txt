Disease Simulator -- Jared Chen -- CS201 -- 2/14/2021

run from: GraphMaker.java

What this program does is twofold. one: it can generate a weighted graph of nodes and edges, and two: it can then take this graph, and simulate how a disease will spread through the graph given infection rate and PPE (whether the nodes are using personal pertective equipment (PPE), which reduces infection rates).



You cannot run all functions of the program at once, the graph generation and simulation are two seperate functions. This is so if you have a  custom graph that you want to use, you can simply put those in as command line arguments and the program will run your custom graph.

To run graph generation:

    javac GraphGen.java
    java GraphGen nodeNum EdgeNum minWeight maxWeight initInfection

  int nodeNum: an int of how many nodes to create in the graph
  int EdgeNum: an int of how many edges to create in the graph
  int minWeight: an int of the minimum weight of an edge
  int maxWeight: an int of the maximum weight of an edge
  int initInfection: an int of how many nodes are initially infected

  Example terminal input:

    javac GraphGen.java
    java GraphGen 7 9 4 19 2

  GraphGen will create a graph with the inputed number of nodes and number of edges. the weight of the edges of the graph is generated randomly given a lower and upper bound. the graph will initially have as many nodes set to infected as initInfection. The nodes will be written to autoGenNode.txt and the edges will be written to autoGenEdge.txt

To run disease simulation:

    javac GraphMaker.java
    java GraphMaker diseaseName infectionRate nodeFile edgeFile PPE days

  String diseaseName: a string of the name of the disease you're simulating
  double infectionRate: a double between 0-100 that represents the infection rate of the disease you're simulating
  String nodeFile: a string of the name of the file containing nodes
  String edgeFile: a string of the name of the file containing edges
  String PPE: type "PPE" or "NoPPE". This will determine of the nodes are using PPE, which impacts the transmission rate.
  int days: the number of days that will be simulated

  Example terminal input:

    javac GraphMaker.java
    java GraphMaker covid 5 autoGenNode.txt autoGenEdge.txt NoPPE 10

  GraphMaker, for every day that passes, will print out the status of of each node, whether it has been infected or not. Then, after simulating all the days indicated, it will print out some information about the final results of the simulation.



The superclass in the program is Disease.java. The two subclasses are PPE.java and NoPPE.java. Inheritence is useful in my program because the two subclasses are able to have the same methods execpt for the update method. in the two sublcasses, the udpate method is overwriden to modify the infection rate in accordance with whether PPE is used. Addtionally, since PPE and NoPPE share the same superclass, GraphMaker is able to use both as a parameter since it only has the disease superclass as a parameter.



The other class concepts that I used were hashmaps and graphs. I used a hashmap as my data structure for storing the nodes of the map. I used the graph structure in my claculations for who is getting infected (via edges and weight). I will say, my graph is less flexible than the one we used in class since it is completely internal and doesn't need all the functionality as the one we used in class. Additionally, I didn't use any starter code for graph, ensuring that the only features it had were features I'd actually use.

I used the hashmap structure because it is easier to search through when looking for nodes. Since my edges are string based, having the hashmap allows me to easier find the node that I am looking for. The reason my edges are string based is so when it comes to reading and writing to a file, it is relatively simple.

I used the graph structure since it is a good represention of how disease spreads. Not every person has contact with every other person, nor to the same degree. Edges and weights help better model how humans interact, and thus how disease spreads through humans.




