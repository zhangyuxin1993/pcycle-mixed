package MyMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import file.File_output;
import network.Layer;
import network.Link;
import network.Node;
import network.NodePair;
import subgraph.Cycle;
import subgraph.LinearRoute;

public class WriteR1R2 {
	public ArrayList<NodePairProtect>  writer1r2(ArrayList<NodePairProtect> NodeAndProRoute,Layer mylayer){
		File_output fileout=new  File_output();
		CycleOutput CO=new CycleOutput();

		fileout.filewrite("F:\\programFile\\p+mixed\\LinkOnRoute.dat", "param  LinkOnRoute_First :=");
 
		NodelistCompare nc=new NodelistCompare();
		int cross=0;
		ArrayList<Node> nodelist1=new ArrayList<Node>();
		ArrayList<Node> nodelist2=new ArrayList<Node>();
		LinearRoute route1=new LinearRoute(null, 0, null);
		ArrayList<LinearRoute> routelist=new ArrayList<LinearRoute>();
		boolean containFlag1=false,containFlag2=false;
		for(NodePairProtect nodepairprotect:NodeAndProRoute){	
			routelist.clear();
			NodePair nodepair=nodepairprotect.getnodepair();
			System.out.println("节点对："+nodepair.getName());//debug
			ArrayList<Cycle> cyclelist=nodepairprotect.getcyclelist();
			
			for(Cycle cycle:cyclelist){
				//将保护路径分成两条 分两条储存
				nodelist1.clear();
				nodelist2.clear();

				Node srcnode=nodepair.getSrcNode();
				Node desnode=nodepair.getDesNode();
				Node guodu=new Node(null, 0, null, null);
				if(cycle.getNodelist().indexOf(srcnode)>cycle.getNodelist().indexOf(desnode)){
					guodu=srcnode;
					srcnode=desnode;
					desnode=guodu;
				}//如果在环上的顺序不一致交换两个节点
				for(int n=cycle.getNodelist().indexOf(srcnode);n<=cycle.getNodelist().indexOf(desnode);n++){
					nodelist1.add(cycle.getNodelist().get(n));	
//					System.out.println("源节点的index"+cycle.getNodelist().indexOf(srcnode));//debug
//					System.out.println("目的节点的index"+cycle.getNodelist().indexOf(desnode));//debug
//					System.out.println(n);				
//						System.out.println(nodelist1.size());//debug
//						for(Node node:nodelist1){
//							System.out.println(node.getName());
//						}
				}
				//将环分成的两个路径保存为route 判断是否已经含有 
				route1.setNodelist(nodelist1);
//				System.out.print("route1:  ");
//				route1.OutputRoute_node(route1);//debug
				//判断原集合中是否含有该route
				containFlag1=true;//节点与节点顺序均相同
				containFlag2=true;//节点相同 节点顺序不同
				for(LinearRoute ro:routelist){
//					ro.OutputRoute_node(ro);
					if(ro.getNodelist().size()!=route1.getNodelist().size()) 
						 continue;
					for(int m=0;m<ro.getNodelist().size();m++){
						if(!ro.getNodelist().get(m).equals(route1.getNodelist().get(m))){
							break;	
						}
						if(m==ro.getNodelist().size()-1){
							containFlag1=false;
							break;
						}							
					}
					if(containFlag1){
						for(int i=0;i<ro.getNodelist().size();i++){
						int size=ro.getNodelist().size();
						if(!ro.getNodelist().get(i).equals(route1.getNodelist().get(size-1-i))){
							break;
						}
						if(i==ro.getNodelist().size()-1){
							containFlag2=false;
							break;
						}
						}
					}
					
					
					if(containFlag1==false||containFlag2==false)
						break;			
				}
				
				if(!(containFlag1==false||containFlag2==false)||routelist.size()==0){
					LinearRoute addroute=new LinearRoute(null, 0, null);
					addroute.getNodelist().addAll(route1.getNodelist());
//					addroute.OutputRoute_node(addroute);//debug
					routelist.add(addroute);
				}
				
				route1.getNodelist().clear();				
//				System.out.println(routelist.size());
			
				int m=0;
					m=cycle.getNodelist().indexOf(desnode)-1;
					do{
						m++;
//						System.out.println(m);
						nodelist2.add(cycle.getNodelist().get(m));
//						for(Node node:nodelist2){
//							System.out.println(node.getName());
//						}
						if(m==cycle.getNodelist().size()-1) m=0;
						
					}while(m!=cycle.getNodelist().indexOf(srcnode));
			
					route1.setNodelist(nodelist2);
//					System.out.print("route1:  ");
//					route1.OutputRoute_node(route1);//debug
					
					containFlag1=true;
					containFlag2=true;
					for(LinearRoute ro:routelist){
//						ro.OutputRoute_node(ro);
						if(ro.getNodelist().size()!=route1.getNodelist().size()) 
							 continue;
						for(int k=0;k<ro.getNodelist().size();k++){
							if(!ro.getNodelist().get(k).equals(route1.getNodelist().get(k))){
								break;	
							}
							if(k==ro.getNodelist().size()-1){
								containFlag1=false;
								break;
							}							
						}
						if(containFlag1){
							for(int i=0;i<ro.getNodelist().size();i++){
							int size=ro.getNodelist().size();
							if(!ro.getNodelist().get(i).equals(route1.getNodelist().get(size-1-i))){
								break;
							}
							if(i==ro.getNodelist().size()-1){
								containFlag2=false;
								break;
							}
							}
						}
						
						if(containFlag1==false||containFlag2==false)
							break;			
					}
					
					if(!(containFlag1==false||containFlag2==false)||routelist.size()==0){
						LinearRoute addroute=new LinearRoute(null, 0, null);
						addroute.getNodelist().addAll(route1.getNodelist());
//						addroute.OutputRoute_node(addroute);//debug
						routelist.add(addroute);
					}
					route1.getNodelist().clear();			
					
//					System.out.println("route size: "+routelist.size());//debug
//					System.out.println("routelist里面的route: ");
//					for(int p=0;p<routelist.size();p++){					
//						LinearRoute r=routelist.get(p);
//						r.OutputRoute_node(r);
//					}
			}//结束所有cyclelist的循环
			
			//输出一对节点能够保护她的所有环及其链路
			fileout.filewrite("F:\\programFile\\p+mixed\\maindat.dat", "set R1["+nodepair.getName()+"] :=");

			for(LinearRoute route:routelist){
				route.OutputRoute_node(route, "F:\\programFile\\p+mixed\\maindat.dat");
				fileout.filewrite_without("F:\\programFile\\p+mixed\\maindat.dat", "\r\n");

				HashMap<String, Link> linklist = mylayer.getLinklist();
				Iterator<String> iter1 = linklist.keySet().iterator();
				ArrayList<Node> nodelistOnlink=new ArrayList<Node>();
				int cross2=0;
				while (iter1.hasNext()) 
				{
					nodelistOnlink.clear();
					Link link=(Link) (linklist.get(iter1.next()));
					fileout.filewrite_without("F:\\programFile\\p+mixed\\LinkOnRoute.dat", link.getName()+"   ");
//					System.out.println(link.getName());
					fileout.filewrite_without("F:\\programFile\\p+mixed\\LinkOnRoute.dat", nodepair.getName()+"   ");
					route.OutputRoute_node(route, "F:\\programFile\\p+mixed\\LinkOnRoute.dat");
					fileout.filewrite_without("F:\\programFile\\p+mixed\\LinkOnRoute.dat", "   ");
					nodelistOnlink.add(link.getNodeA());
					nodelistOnlink.add(link.getNodeB());
					cross2=0;
					cross2=nc.nodelistcompare(route.getNodelist(), nodelistOnlink);			
					if(cross2==1)    
						fileout.filewrite("F:\\programFile\\p+mixed\\LinkOnRoute.dat", "1");	
					else 
						fileout.filewrite("F:\\programFile\\p+mixed\\LinkOnRoute.dat", "0");	
				}
			
			}
			fileout.filewrite("F:\\programFile\\p+mixed\\maindat.dat", ";");
	
			//将得到的routelist  set到类中
			for( NodePairProtect npp:NodeAndProRoute){
				if(npp.getnodepair().getName().equals(nodepair.getName())){
					npp.setroutelist(routelist);
				}		
			}			
		}
		fileout.filewrite("F:\\programFile\\p+mixed\\LinkOnRoute.dat", ";");
		R2Write(NodeAndProRoute,mylayer);
		return NodeAndProRoute;
	}
	
	public void R2Write( ArrayList<NodePairProtect> NodeAndProRoute,Layer mylayer){
		File_output fileout=new  File_output();
		fileout.filewrite("F:\\programFile\\p+mixed\\LinkOnRoute.dat", "param  LinkOnRoute_Second :=");
		for(NodePairProtect npp:NodeAndProRoute){
			NodePair nodepair=npp.getnodepair();
			ArrayList<LinearRoute> routelist=npp.getroutelist();
		
			for(int m=0;m<routelist.size();m++){//m代表first保护路径
				fileout.filewrite_without("F:\\programFile\\p+mixed\\maindat.dat", "set R2["+nodepair.getName()+",");
				
				routelist.get(m).OutputRoute_node(routelist.get(m), "F:\\programFile\\p+mixed\\maindat.dat");
				fileout.filewrite("F:\\programFile\\p+mixed\\maindat.dat", "] :=");
				for(int n=0;n<routelist.size();n++){//n代表second保护路径
					if(m==n) continue;
					NodelistCompare nc=new NodelistCompare();
					int cross=nc.nodelistcompare(routelist.get(n).getNodelist(), routelist.get(m).getNodelist());
					if(cross!=1){
						HashMap<String, Link> linklist = mylayer.getLinklist();
						Iterator<String> iter1 = linklist.keySet().iterator();
						ArrayList<Node> nodelistOnlink=new ArrayList<Node>();
						int cross2=0;
						while (iter1.hasNext()) 
						{
							nodelistOnlink.clear();
							Link link=(Link) (linklist.get(iter1.next()));
							fileout.filewrite_without("F:\\programFile\\p+mixed\\LinkOnRoute.dat", link.getName()+"   ");
							fileout.filewrite_without("F:\\programFile\\p+mixed\\LinkOnRoute.dat", nodepair.getName()+"   ");
							routelist.get(m).OutputRoute_node(routelist.get(m), "F:\\programFile\\p+mixed\\LinkOnRoute.dat");
							fileout.filewrite_without("F:\\programFile\\p+mixed\\LinkOnRoute.dat", "   ");
							routelist.get(n).OutputRoute_node(routelist.get(n), "F:\\programFile\\p+mixed\\LinkOnRoute.dat");
							fileout.filewrite_without("F:\\programFile\\p+mixed\\LinkOnRoute.dat", "   ");
							nodelistOnlink.add(link.getNodeA());
							nodelistOnlink.add(link.getNodeB());
							cross2=0;
							cross2=nc.nodelistcompare(routelist.get(n).getNodelist(), nodelistOnlink);			
							if(cross2==1)    
								fileout.filewrite("F:\\programFile\\p+mixed\\LinkOnRoute.dat", "1");	
							else 
								fileout.filewrite("F:\\programFile\\p+mixed\\LinkOnRoute.dat", "0");	
						}	
				
						routelist.get(n).OutputRoute_node(routelist.get(n), "F:\\programFile\\p+mixed\\maindat.dat");
					    fileout.filewrite_without("F:\\programFile\\p+mixed\\maindat.dat", "\r\n");
					}

				
					
				}
				fileout.filewrite("F:\\programFile\\p+mixed\\maindat.dat", ";");	
			}
			
		}
		fileout.filewrite("F:\\programFile\\p+mixed\\LinkOnRoute.dat", ";");	
		
	}
	

}

