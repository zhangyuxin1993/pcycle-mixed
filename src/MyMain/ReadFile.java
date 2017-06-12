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

import network.Layer;
import network.Node;
import network.NodePair;

public class ReadFile {

	public static void main(String[] args) {
		ArrayList<NodePair> list = new ArrayList<NodePair>();
		Layer mylayer = new Layer(null, 0, null);
		mylayer.readTopology("G:/Topology/NSFNET.csv");
		mylayer.generateNodepairs();
		
	
		ReadFile t = new ReadFile();
		list=t.readDemand(mylayer, "D:/NSFNETalldemand.csv");
		
		for(NodePair nodepair:list){
			 System.out.println(nodepair.getName());
		}
	}

	public ArrayList<NodePair> readDemand(Layer layer, String writename) {
		ArrayList<NodePair> list = new ArrayList<NodePair>();
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
						list.add(currentnodepair);
						// System.out.println(currentnodepair.getName());
//						int slotNum = Integer.parseInt(data[1]);
//						currentnodepair.setSlotsnum(slotNum);
						// System.out.println(slotNum);
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
		return list;
	}

}
