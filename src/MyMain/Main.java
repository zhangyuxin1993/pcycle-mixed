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
		String filename="E:/ZYX/Topology/5.csv";
//		String filename="G:/Topology/5.csv";
		Layer mylayer=new Layer(null, 0, null);
		mylayer.readTopology(filename);
		mylayer.generateNodepairs(); 
		File_output fileout=new  File_output();
		//Ϊnodepair���

		int serial=0;
		HashMap<String,Integer> nodepair_serial=new HashMap<String,Integer>();
		HashMap<String, NodePair> Snodepair = mylayer.getNodepairlist();
		Iterator<String> iter1 = Snodepair.keySet().iterator();
		while (iter1.hasNext()) 
		{
			NodePair nodepairser=(NodePair) (Snodepair.get(iter1.next()));
			nodepair_serial.put(nodepairser.getName(),serial);
			serial++;
		}
		
		//ͨ����������Ų���nodepair
		randomfunction radom=new randomfunction();
		int[] nodepair_num=radom.Dif_random(10, mylayer.getNodepairNum());//�������nodepair����Ŀ
		int has=0;
		ArrayList<LinearRoute> routelist_once=new ArrayList<LinearRoute>();
		ArrayList<NodePair> nodepairlist= new ArrayList<NodePair>();
		HashMap<String, NodePair> map = mylayer.getNodepairlist();
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) 
		{
			has=0;
			routelist_once.clear();
			NodePair nodepair=(NodePair) (map.get(iter.next()));

			for(int a=0;a<nodepair_num.length;a++){
				if(nodepair_num[a]==nodepair_serial.get(nodepair.getName())){
					has=1;
					break;
				}		
			}
			if(has==0) continue;//�������demand
			nodepairlist.add(nodepair);
		}
		 
		//�������еĻ�
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
		
		 //���Ƿ���Ҫ����
		AEofCycle AE=new AEofCycle();
		ArrayList<Cycle> newcyclelist=new ArrayList<Cycle>();
		double average=AE.aeofcycle(cyclelist,mylayer);
		
		HashMap<String, Double> AeList=new HashMap<String, Double>();
	     AeList=AEofCycle.AeList;
		for(int b=0;b<cyclelist.size();b++){
			 Cycle nowcycle=cyclelist.get(b);
			 double nowae=AeList.get(nowcycle.toString());
			 if(nowae>2){
				 newcyclelist.add(nowcycle); 
			 }
		 }
		
		CO.cycleoutput(newcyclelist, "F:\\programFile\\p+mixed\\newCycle.dat");

		///*
		//�ж���Щ�ڵ�Կ��Ա���Щ������
		ArrayList<NodePairProtect> NodeAndProRoute=new ArrayList<NodePairProtect>();
		CycleProtectNodepair CPN=new CycleProtectNodepair();
		NodeAndProRoute=CPN.cycleprotectnodepair(nodepairlist, newcyclelist,mylayer);
		//����������нڵ�Լ����Ӧ�ı�����
		for(NodePairProtect nodepairprotect:NodeAndProRoute){
			fileout.filewrite("F:\\programFile\\p+mixed\\NodeAndProtectCycle2.dat", nodepairprotect.getnodepair().getName());		 
			fileout.filewrite("F:\\programFile\\p+mixed\\WorkRoute.dat", nodepairprotect.getnodepair().getName());		 
			nodepairprotect.getworkroute().OutputRoute_node(nodepairprotect.getworkroute(), "F:\\programFile\\p+mixed\\WorkRoute.dat");
			fileout.filewrite("F:\\programFile\\p+mixed\\WorkRoute.dat", "\r\n");		 
			CO.cycleoutput(nodepairprotect.getcyclelist(), "F:\\programFile\\p+mixed\\NodeAndProtectCycle2.dat");
			}
		//���R1R2
		WriteR1R2 write1=new WriteR1R2();
		NodeAndProRoute=write1.writer1r2(NodeAndProRoute,mylayer);

		Share s=new Share();
		s.share(NodeAndProRoute);
		
	//debug
//		ArrayList<LinearRoute> routelist=new ArrayList<LinearRoute>();
//		for(NodePairProtect nodepairprotect:NodeAndProRoute){		
//			fileout.filewrite("F:\\programFile\\p+mixed\\test.dat", "�ڵ�ԣ� "+nodepairprotect.getnodepair().getName());
//			routelist=nodepairprotect.getroutelist();
//			for(LinearRoute route:routelist){
//				route.OutputRoute_node(route, "F:\\programFile\\p+mixed\\test.dat");
//			}
//}
//		 /*
		//���demand nodepair	
		
				fileout.filewrite("F:\\programFile\\p+mixed\\workingdemand.dat", "param  WorkingDemand :=");
				fileout.filewrite("F:\\programFile\\p+mixed\\maindat.dat", "set D :=");
				for(NodePairProtect nodepairprotect:NodeAndProRoute){
					fileout.filewrite("F:\\programFile\\p+mixed\\maindat.dat", nodepairprotect.getnodepair().getName());
					fileout.filewrite_without("F:\\programFile\\p+mixed\\workingdemand.dat", nodepairprotect.getnodepair().getName());
					fileout.filewrite_without("F:\\programFile\\p+mixed\\workingdemand.dat", "   ");
					int demand=(int) (Math.random()*10+1);
					fileout.filewrite("F:\\programFile\\p+mixed\\workingdemand.dat", demand);	
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
				fileout.filewrite("F:\\programFile\\p+mixed\\maindat.dat", "10000;");
//	 */
		System.out.println("Finish");//debug

		
	}

}
