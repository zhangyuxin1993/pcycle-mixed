package MyMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import file.File_output;
import graphalgorithms.RouteSearching;
import graphalgorithms.SearchConstraint;
import network.Layer;
import network.Link;
import network.Node;
import subgraph.Cycle;
import subgraph.LinearRoute;

public class AEofCycle {
	static HashMap<String, Double> AeList=new HashMap<String, Double>();
	public  double aeofcycle(ArrayList<Cycle> cyclelist,Layer layer)
	{
		SearchConstraint constraint = new SearchConstraint(100000);
		double sum=0;
		
		for(int n=0;n<cyclelist.size();n++){
		ArrayList<Link> linklist=new ArrayList<Link>();
		int linknum=0,straddlinglinknum=0;
		double length=0;
		CycleOutput out =new CycleOutput();
		
		 Cycle nowcycle=cyclelist.get(n);
//		 System.out.println("�µĻ�");
//		 out.cycleoutput(nowcycle,"E:\\programFile\\test.dat");	
		 //�����������·���Լ����ĳ���
		 for(int m=0;m<nowcycle.getLinklist().size()-1;m++){
			   linklist.clear();
				Link link=nowcycle.getLinklist().get(m);	
//				System.out.println("linkname�� "+link.getName());
				length=length+link.getLength();
				linknum++;
				
		 }
		
//		 File_output filewrite=new File_output();	 
//		 filewrite.filewrite("E:\\programFile\\linknum.dat", "\r\n");
//		 out.cycleoutput(nowcycle,"E:\\programFile\\linknum.dat");	
//		 filewrite.filewrite("E:\\programFile\\linknum.dat", "      "+linknum);
		 //��������·��
//		  System.out.println("���Ľڵ㣺  ");
//			for(int b=0;b<nowcycle.getNodelist().size();b++){
//				System.out.print(nowcycle.getNodelist().get(b).getName()+"  ");
//			}
		 for(int b=0;b<nowcycle.getNodelist().size()-2;b++){
				Node A=nowcycle.getNodelist().get(b);
//			 System.out.print("�ڵ�A: "+A.getName());
				 for(int c=b+1;c<nowcycle.getNodelist().size()-1;c++){
						Node B=nowcycle.getNodelist().get(c);
//					 System.out.print("�ڵ�B: "+B.getName()+"   ");
//					 System.out.println();
					 //Ѱ��route��Դ�ڵ��ҵ�
		 RouteSearching RS=new RouteSearching();
		 ArrayList<LinearRoute> routelist=new ArrayList<LinearRoute>();
		 RS.findAllRoute(A, B, layer, constraint, 100, routelist);// route�ҵ���
		 
		 //����Ϊroute�뻷��Ƚ�
		 for(int routeout=0;routeout<routelist.size();routeout++){
			 LinearRoute route=routelist.get(routeout);
//			 route.OutputRoute_node(route);
			 
			 NodelistCompare NC=new NodelistCompare();
			 int cross=NC.nodelistcompare(route.getNodelist(), nowcycle.getNodelist());
//			 System.out.println("with cycle"+cross);
			 if(cross==1) continue;//�л���ͬ��route
			 //route֮����Ƚ� 
//			 System.out.println("linklist�����link");
//			 for(int linkout=0;linkout<linklist.size() ;linkout++){
//				 System.out.println(linklist.get(linkout).getName());
//			 }
//			 System.out.println("����ǰ��size"+linklist.size());
				 
			 for(int linkadd=0;linkadd<route.getLinklist().size() ;linkadd++){
				 Link addlink=route.getLinklist().get(linkadd);
				 Node src=addlink.getNodeA();
				 Node des=addlink.getNodeB();
				 //�ı���˳��
				 if(src.getIndex()>des.getIndex()){
					 addlink=layer.findLink(des, src);
				 }
//				 System.out.println("Ҫ�ӽ�ȥ��link"+addlink.getName());
				 if(!linklist.contains(addlink))
				 {
					 linklist.add(addlink);
				 }
//				 System.out.println("������size"+linklist.size());
			 }
							 
//				 System.out.println("���·��");
//				 route.OutputRoute_node(route);		
				 straddlinglinknum=linklist.size();
				 
//				 System.out.println("���  ��"+straddlinglinknum);
			 }			
		}
		 }
		 
//		 System.out.println("������·�� "+linknum+"�����·���� "+straddlinglinknum);
		 double AE=(linknum+straddlinglinknum*2)/length*1000;
		 AeList.put(nowcycle.toString(), AE);
//		 System.out.println(AE);
		 sum=sum+AE;
		 
		}
		 double average=0;
		 average=sum/cyclelist.size();
//		 System.out.println(" average ="+average);
//		 file_out_put filewrite=new file_out_put();
//		filewrite.filewrite("E:\\programFile\\AVERAGE.dat",  );

		 return average;
		 
	}
}
