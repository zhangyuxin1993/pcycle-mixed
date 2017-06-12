package MyMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import file.File_output;
import graphalgorithms.RouteSearching;
import graphalgorithms.SearchConstraint;
import network.Node;
import network.Layer;
import network.Link;
import network.NodePair;
import randomfunctions.randomfunction;
import subgraph.Cycle;
import subgraph.LinearRoute;

public class Main {

	public static void main(String[] args) {
//		String filename="C:/ZYX/Topology/5.csv";
		String filename="G:/Topology/NSFNET.csv";
		Layer mylayer=new Layer(null, 0, null);
		mylayer.readTopology(filename);
		mylayer.generateNodepairs(); 
		File_output fileout=new  File_output();
 
		//产生所有的环
		ArrayList<Cycle> cyclelist=new ArrayList<Cycle>();
		RouteSearching shortestpath=new RouteSearching(); 
		SearchConstraint constraint = new SearchConstraint(100000);
		HashMap<String, Node> nodemap = mylayer.getNodelist();
		Iterator<String> nodeiter = nodemap.keySet().iterator();	
		while (nodeiter.hasNext()) 
		{			
			Node node = (Node) (nodemap.get(nodeiter.next()));	
			shortestpath.findAllCycle(node, mylayer, constraint, cyclelist);
		}
		CycleOutput CO=new CycleOutput();
//		CO.cycleoutput(cyclelist, "F:\\programFile\\p+mixed\\AllCycle.dat");
		
		 //环是否需要减少
		/*
		AEofCycle AE=new AEofCycle();
		ArrayList<Cycle> newcyclelist=new ArrayList<Cycle>();
		double average=AE.aeofcycle(cyclelist,mylayer);
		
		HashMap<String, Double> AeList=new HashMap<String, Double>();
	     AeList=AEofCycle.AeList;
		for(int b=0;b<cyclelist.size();b++){
			 Cycle nowcycle=cyclelist.get(b);
			 double nowae=AeList.get(nowcycle.toString());
			 if(nowae>7000){
				 newcyclelist.add(nowcycle); 
			 }
		 }
		CO.cycleoutput(newcyclelist, "F:\\programFile\\p+mixed\\newCycle.dat");
		*/
		ArrayList<NodePair> nodepairlist = new ArrayList<NodePair>();
//		nodepairlist=fileout.readDemand(mylayer, "C:/ZYX/5node25.csv");
		nodepairlist=fileout.readDemand(mylayer, "D:/5node.csv");
		
		//判断哪些节点对可以被哪些环保护
		ArrayList<NodePairProtect> NodeAndProRoute=new ArrayList<NodePairProtect>();
		CycleProtectNodepair CPN=new CycleProtectNodepair();
		NodeAndProRoute=CPN.cycleprotectnodepair(nodepairlist, cyclelist,mylayer);
		//可以输出所有节点对及其对应的保护环
		for(NodePairProtect nodepairprotect:NodeAndProRoute){
			fileout.filewrite("F:\\programFile\\p+mixed\\NodeAndProtectCycle.dat", nodepairprotect.getnodepair().getName());		 
			fileout.filewrite("F:\\programFile\\p+mixed\\WorkRoute.dat", nodepairprotect.getnodepair().getName());		 
			nodepairprotect.getworkroute().OutputRoute_node(nodepairprotect.getworkroute(), "F:\\programFile\\p+mixed\\WorkRoute.dat");
			fileout.filewrite("F:\\programFile\\p+mixed\\WorkRoute.dat", "\r\n");		 
			CO.cycleoutput(nodepairprotect.getcyclelist(), "F:\\programFile\\p+mixed\\NodeAndProtectCycle.dat");
			}
		//输出R1R2
		WriteR1R2 write1=new WriteR1R2();
		NodeAndProRoute=write1.writer1r2(NodeAndProRoute,mylayer);

		Share s=new Share();
		s.share(NodeAndProRoute);
		
		String outFile="F:\\programFile\\p+mixed\\commlink.dat";
		CommonLink cl=new CommonLink();
		cl.commonlink(mylayer, NodeAndProRoute, outFile);
	//debug
//		ArrayList<LinearRoute> routelist=new ArrayList<LinearRoute>();
//		for(NodePairProtect nodepairprotect:NodeAndProRoute){		
//			fileout.filewrite("F:\\programFile\\p+mixed\\test.dat", "节点对： "+nodepairprotect.getnodepair().getName());
//			routelist=nodepairprotect.getroutelist();
//			for(LinearRoute route:routelist){
//				route.OutputRoute_node(route, "F:\\programFile\\p+mixed\\test.dat");
//			}
//}
//		 /*
		//输出demand nodepair	
		
				fileout.filewrite("F:\\programFile\\p+mixed\\workingdemand.dat", "param  WorkingDemand :=");
				fileout.filewrite("F:\\programFile\\p+mixed\\maindat.dat", "set D :=");
				for(NodePairProtect nodepairprotect:NodeAndProRoute){
					fileout.filewrite("F:\\programFile\\p+mixed\\maindat.dat", nodepairprotect.getnodepair().getName());
					fileout.filewrite_without("F:\\programFile\\p+mixed\\workingdemand.dat", nodepairprotect.getnodepair().getName());
					fileout.filewrite_without("F:\\programFile\\p+mixed\\workingdemand.dat", "   ");
					fileout.filewrite("F:\\programFile\\p+mixed\\workingdemand.dat", nodepairprotect.getnodepair().getSlotsnum());
				}
				fileout.filewrite("F:\\programFile\\p+mixed\\maindat.dat", ";");
				fileout.filewrite("F:\\programFile\\p+mixed\\workingdemand.dat", ";");

				//set link
				HashMap<String, Link> linkmap = mylayer.getLinklist();
				Iterator<String> linkiter = linkmap.keySet().iterator();
				fileout.filewrite("F:\\programFile\\p+mixed\\maindat.dat", "set Link :=");
				while (linkiter.hasNext()) 
				{
					Link link=(Link) (linkmap.get(linkiter.next()));
					fileout.filewrite("F:\\programFile\\p+mixed\\maindat.dat", link.getName());
				}
				fileout.filewrite("F:\\programFile\\p+mixed\\maindat.dat", ";");
				
				fileout.filewrite("F:\\programFile\\p+mixed\\maindat.dat", "param  LargeConstant :=");
				fileout.filewrite("F:\\programFile\\p+mixed\\maindat.dat", "100000;");
//	 */
		System.out.println("Finish");//debug

		
	}

}
