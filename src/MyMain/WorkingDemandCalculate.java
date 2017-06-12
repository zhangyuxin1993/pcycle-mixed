package MyMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import file.File_output;
import graphalgorithms.RouteSearching;
import network.Layer;
import network.NodePair;
import subgraph.LinearRoute;

public class WorkingDemandCalculate {

	public static void main(String[] args) {
		String filename="G:/Topology/5.csv";
		Layer mylayer=new Layer(null, 0, null);
		mylayer.readTopology(filename);
		mylayer.generateNodepairs(); 
		
		WorkingDemandCalculate mm=new WorkingDemandCalculate();
		HashMap<NodePair, Integer> nodepairlist = new HashMap<NodePair, Integer>();
		nodepairlist=mm.readDemand(mylayer, "C:/Users/ZYX/Desktop/what you do/demandmatrix/p-cycle/5node/5DAT25/5node25.csv");
		
		int allWorkingCapacity=0;
		Iterator<NodePair> nodeiter = nodepairlist.keySet().iterator();	
		while (nodeiter.hasNext()) 
		{			
			NodePair nodepair=nodeiter.next();
			System.out.println("节点对：  "+nodepair.getName());
			LinearRoute newRoute=new LinearRoute(null, 0, null);
			RouteSearching dijkstra = new RouteSearching();
			dijkstra.Dijkstra(nodepair.getSrcNode(), nodepair.getDesNode(), mylayer, newRoute, null);
			System.out.print("工作路径  ");
			newRoute.OutputRoute_node(newRoute);
			int count=newRoute.getLinklist().size()*nodepairlist.get(nodepair);
			allWorkingCapacity=allWorkingCapacity+count;
			System.out.print("总的工作容量: "+allWorkingCapacity);
			
		}
	}
	
	
	public HashMap<NodePair, Integer> readDemand(Layer layer, String writename) {
		HashMap<NodePair, Integer> nodepairDemand=new HashMap<NodePair, Integer>();
		String[] data = new String[10];
		File file = new File(writename);

		BufferedReader bufRdr = null;
		try {
			bufRdr = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String line = null;
		int col = 0;
		try {
			line = bufRdr.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// read the first title line
		// read each line of text file
		try {
			boolean Nodepair = false;
			while ((line = bufRdr.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) {
					data[col] = st.nextToken();
					col++;
				}
				col = 0;
				String name = data[0];
				if (name.equals("Nodepair")) {
					Nodepair = true;
				}

				// read nodes
				if (Nodepair) {
					if (!(name.equals("Nodepair"))) {
						NodePair currentnodepair = layer.getNodepairlist().get(data[0]);
//						list.add(currentnodepair);
						// System.out.println(currentnodepair.getName());
						int slotNum = Integer.parseInt(data[1]);
//						currentnodepair.setSlotsnum(slotNum);
						nodepairDemand.put(currentnodepair, slotNum);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			bufRdr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nodepairDemand;
	}

}
