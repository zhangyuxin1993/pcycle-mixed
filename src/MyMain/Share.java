package MyMain;

import java.util.ArrayList;

import file.File_output;
import network.NodePair;
import subgraph.LinearRoute;

public class Share {
	public void share(ArrayList<NodePairProtect> NodeAndProRoute){
		NodelistCompare nc=new NodelistCompare();
		int cross=0,cross13=0,cross14=0,cross23=0,cross24=0,Wcross=0;
		int crossW1R3=0,crossW1R4=0,crossW2R1=0,crossW2R2=0;
		File_output fileout=new  File_output();
//		fileout.filewrite ("F:\\programFile\\p+mixed\\test2.dat", "here");
		fileout.filewrite ("F:\\programFile\\p+mixed\\firstfirst.dat", "param  FirstFirstShare :=");
		fileout.filewrite ("F:\\programFile\\p+mixed\\firstsecond.dat", "param  FirstSecondShare :=");
		fileout.filewrite ("F:\\programFile\\p+mixed\\secondsecond.dat", "param  SecondSecondShare :=");
		
		for(NodePairProtect npp:NodeAndProRoute){
			cross13=0;cross14=0;cross23=0;cross24=0;
			NodePair nodepair1=npp.getnodepair();
			ArrayList<LinearRoute> routelist1=npp.getroutelist();
			
			for(int a=0;a<routelist1.size()-1;a++){
				LinearRoute route1=routelist1.get(a);
//				System.out.println(routelist1.size());
//				System.out.println(nodepair1.getName());
//				route1.OutputRoute_node(route1);//debug
				for(int b=a+1;b<routelist1.size();b++){					
					LinearRoute route2=routelist1.get(b);
//					route2.OutputRoute_node(route2);//debug
					cross=0;
					cross=nc.nodelistcompare(route1.getNodelist(), route2.getNodelist());
					if(cross==1) continue;
					
					for(NodePairProtect npp2:NodeAndProRoute){//取出另一对nodepair
						NodePair nodepair2=npp2.getnodepair();
//					    System.out.println("节点对： "+nodepair2.getName());//debug
						if(nodepair2.getName().equals(nodepair1.getName())) continue;
						ArrayList<LinearRoute> routelist2=npp2.getroutelist();
						
						for(int c=0;c<routelist2.size()-1;c++){	
							LinearRoute route3=routelist2.get(c);
							
//							route3.OutputRoute_node(route3);//debug
							for(int d=c+1;d<routelist2.size();d++){					
								LinearRoute route4=routelist2.get(d);
//								route4.OutputRoute_node(route4);//debug
								cross=0;
								cross=nc.nodelistcompare(route3.getNodelist(), route4.getNodelist());
								if(cross==1) continue;
								//以上取出不同nodepair中不相交的两条route 
						 
								//判断工作路径与保护route是否相交
								Wcross=nc.nodelistcompare( npp.getworkroute().getNodelist(),  npp2.getworkroute().getNodelist());
								cross13=nc.nodelistcompare(route1.getNodelist(),route3.getNodelist());
								cross14=nc.nodelistcompare(route1.getNodelist(),route4.getNodelist());
								cross23=nc.nodelistcompare(route2.getNodelist(),route3.getNodelist());
								cross24=nc.nodelistcompare(route2.getNodelist(),route4.getNodelist());
								crossW1R3=nc.nodelistcompare(npp.getworkroute().getNodelist(),route3.getNodelist());
								crossW1R4=nc.nodelistcompare(npp.getworkroute().getNodelist(),route4.getNodelist());
								crossW2R1=nc.nodelistcompare(npp2.getworkroute().getNodelist(),route1.getNodelist());
								crossW2R2=nc.nodelistcompare(npp2.getworkroute().getNodelist(),route2.getNodelist());
					
							 //结果写入文件		
								int share13=0;
								if(cross13==1){
									if((crossW1R4==1&&crossW2R2==1)||(Wcross==1&&cross24==1))  
										share13=1;
								}
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", nodepair1.getName()+"   ");
								route1.OutputRoute_node(route1, "F:\\programFile\\p+mixed\\firstfirst.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");
								route2.OutputRoute_node(route2, "F:\\programFile\\p+mixed\\firstfirst.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");								
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", nodepair2.getName()+"   ");
								route3.OutputRoute_node(route3, "F:\\programFile\\p+mixed\\firstfirst.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");
								route4.OutputRoute_node(route4, "F:\\programFile\\p+mixed\\firstfirst.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");
								fileout.filewrite("F:\\programFile\\p+mixed\\firstfirst.dat", share13);
						
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", nodepair1.getName()+"   ");	
								route2.OutputRoute_node(route2, "F:\\programFile\\p+mixed\\secondsecond.dat");							
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");
								route1.OutputRoute_node(route1, "F:\\programFile\\p+mixed\\secondsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");								
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", nodepair2.getName()+"   ");
								route4.OutputRoute_node(route4, "F:\\programFile\\p+mixed\\secondsecond.dat");						
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");
								route3.OutputRoute_node(route3, "F:\\programFile\\p+mixed\\secondsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");
								fileout.filewrite("F:\\programFile\\p+mixed\\secondsecond.dat", share13);
							    
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", nodepair1.getName()+"   ");
								route1.OutputRoute_node(route1, "F:\\programFile\\p+mixed\\firstsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");
								route2.OutputRoute_node(route2, "F:\\programFile\\p+mixed\\firstsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");								
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", nodepair2.getName()+"   ");
								route4.OutputRoute_node(route4, "F:\\programFile\\p+mixed\\firstsecond.dat");						
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");
								route3.OutputRoute_node(route3, "F:\\programFile\\p+mixed\\firstsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");
								fileout.filewrite("F:\\programFile\\p+mixed\\firstsecond.dat", share13);
																
								int share14=0;
								if(cross14==1){
									if((crossW1R3==1&&crossW2R2==1)||(Wcross==1&&cross23==1))  
										share14=1;
								}
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", nodepair1.getName()+"   ");
								route1.OutputRoute_node(route1, "F:\\programFile\\p+mixed\\firstfirst.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");
								route2.OutputRoute_node(route2, "F:\\programFile\\p+mixed\\firstfirst.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");								
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", nodepair2.getName()+"   ");
								route4.OutputRoute_node(route4, "F:\\programFile\\p+mixed\\firstfirst.dat");			
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");
								route3.OutputRoute_node(route3, "F:\\programFile\\p+mixed\\firstfirst.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");
								fileout.filewrite("F:\\programFile\\p+mixed\\firstfirst.dat", share14);
						
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", nodepair1.getName()+"   ");	
								route2.OutputRoute_node(route2, "F:\\programFile\\p+mixed\\secondsecond.dat");							
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");
								route1.OutputRoute_node(route1, "F:\\programFile\\p+mixed\\secondsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");								
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", nodepair2.getName()+"   ");
								route3.OutputRoute_node(route3, "F:\\programFile\\p+mixed\\secondsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");								
								route4.OutputRoute_node(route4, "F:\\programFile\\p+mixed\\secondsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");
								fileout.filewrite("F:\\programFile\\p+mixed\\secondsecond.dat", share14);
							    
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", nodepair1.getName()+"   ");
								route1.OutputRoute_node(route1, "F:\\programFile\\p+mixed\\firstsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");
								route2.OutputRoute_node(route2, "F:\\programFile\\p+mixed\\firstsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");								
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", nodepair2.getName()+"   ");
								route3.OutputRoute_node(route3, "F:\\programFile\\p+mixed\\firstsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");								
								route4.OutputRoute_node(route4, "F:\\programFile\\p+mixed\\firstsecond.dat");		
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");
								fileout.filewrite("F:\\programFile\\p+mixed\\firstsecond.dat", share14);
						
								int share23=0;
								if(cross23==1){
									if((crossW1R4==1&&crossW2R1==1)||(Wcross==1&&cross14==1))  
										share23=1;
								}
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", nodepair1.getName()+"   ");							
								route2.OutputRoute_node(route2, "F:\\programFile\\p+mixed\\firstfirst.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");
								route1.OutputRoute_node(route1, "F:\\programFile\\p+mixed\\firstfirst.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");								
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", nodepair2.getName()+"   ");
								route3.OutputRoute_node(route3, "F:\\programFile\\p+mixed\\firstfirst.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");
								route4.OutputRoute_node(route4, "F:\\programFile\\p+mixed\\firstfirst.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");
								fileout.filewrite("F:\\programFile\\p+mixed\\firstfirst.dat", share23);
						
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", nodepair1.getName()+"   ");	
								route1.OutputRoute_node(route1, "F:\\programFile\\p+mixed\\secondsecond.dat");					
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");
								route2.OutputRoute_node(route2, "F:\\programFile\\p+mixed\\secondsecond.dat");	
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");								
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", nodepair2.getName()+"   ");
								route4.OutputRoute_node(route4, "F:\\programFile\\p+mixed\\secondsecond.dat");						
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");
								route3.OutputRoute_node(route3, "F:\\programFile\\p+mixed\\secondsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");
								fileout.filewrite("F:\\programFile\\p+mixed\\secondsecond.dat", share23);
							    
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", nodepair1.getName()+"   ");								
								route2.OutputRoute_node(route2, "F:\\programFile\\p+mixed\\firstsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");
								route1.OutputRoute_node(route1, "F:\\programFile\\p+mixed\\firstsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");								
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", nodepair2.getName()+"   ");
								route4.OutputRoute_node(route4, "F:\\programFile\\p+mixed\\firstsecond.dat");						
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");
								route3.OutputRoute_node(route3, "F:\\programFile\\p+mixed\\firstsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");
								fileout.filewrite("F:\\programFile\\p+mixed\\firstsecond.dat", share23);
							
								int share24=0;
								if(cross24==1){
									if((crossW1R3==1&&crossW2R1==1)||(Wcross==1&&cross13==1))  
										share24=1;
								}
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", nodepair1.getName()+"   ");							
								route2.OutputRoute_node(route2, "F:\\programFile\\p+mixed\\firstfirst.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");
								route1.OutputRoute_node(route1, "F:\\programFile\\p+mixed\\firstfirst.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");								
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", nodepair2.getName()+"   ");
						        route4.OutputRoute_node(route4, "F:\\programFile\\p+mixed\\firstfirst.dat");								
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");
								route3.OutputRoute_node(route3, "F:\\programFile\\p+mixed\\firstfirst.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstfirst.dat", "   ");
								fileout.filewrite("F:\\programFile\\p+mixed\\firstfirst.dat", share24);
						
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", nodepair1.getName()+"   ");	
								route1.OutputRoute_node(route1, "F:\\programFile\\p+mixed\\secondsecond.dat");					
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");
								route2.OutputRoute_node(route2, "F:\\programFile\\p+mixed\\secondsecond.dat");	
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");								
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", nodepair2.getName()+"   ");							
								route3.OutputRoute_node(route3, "F:\\programFile\\p+mixed\\secondsecond.dat");					
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");
								route4.OutputRoute_node(route4, "F:\\programFile\\p+mixed\\secondsecond.dat");	
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\secondsecond.dat", "   ");
								fileout.filewrite("F:\\programFile\\p+mixed\\secondsecond.dat", share24);
							    
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", nodepair1.getName()+"   ");								
								route2.OutputRoute_node(route2, "F:\\programFile\\p+mixed\\firstsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");
								route1.OutputRoute_node(route1, "F:\\programFile\\p+mixed\\firstsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");								
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", nodepair2.getName()+"   ");		
								route3.OutputRoute_node(route3, "F:\\programFile\\p+mixed\\firstsecond.dat");					
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");
								route4.OutputRoute_node(route4, "F:\\programFile\\p+mixed\\firstsecond.dat");
								fileout.filewrite_without ("F:\\programFile\\p+mixed\\firstsecond.dat", "   ");
								fileout.filewrite("F:\\programFile\\p+mixed\\firstsecond.dat", share24);
							
							}
				}
				
			}
		}
		
	}

}
		fileout.filewrite ("F:\\programFile\\p+mixed\\firstfirst.dat", ";");
		fileout.filewrite ("F:\\programFile\\p+mixed\\firstsecond.dat", ";");
		fileout.filewrite ("F:\\programFile\\p+mixed\\secondsecond.dat", ";");
	} 

}
