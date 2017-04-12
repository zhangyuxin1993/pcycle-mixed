package MyMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import network.Layer;
import network.NodePair;

public class Test {

	public static void main(String[] args) {
		Layer mylayer=new Layer(null, 0, null);
		mylayer.readTopology("G:/Topology/10.csv");
		mylayer.generateNodepairs();
	
		Test t=new Test();
		t.readDemand(mylayer, "D:/10node150circle.csv");
		 
	}
	
	public int readDemand(Layer layer, String writename) {
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
						System.out.println(currentnodepair.getName());
						int slotNum = Integer.parseInt(data[1]);
//						currentnodepair.setSlotsnum(slotNum);
						System.out.println(slotNum);
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
		return 0;
	}

}
