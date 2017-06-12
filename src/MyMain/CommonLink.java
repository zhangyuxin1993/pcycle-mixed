package MyMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import file.File_output;
import network.Layer;
import network.Link;
import network.Node;
import network.NodePair;
import subgraph.LinearRoute;

public class CommonLink {
	public void commonlink(Layer mylayer, ArrayList<NodePairProtect> NodeAndProRoute, String outFile) {

		NodelistCompare nc = new NodelistCompare();
		int cross = 0;
		File_output fileout = new File_output();
		fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat", "param  CommonLink_firstfirst :=");
		fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat", "param  CommonLink_firstsecond :=");
		fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat", "param  CommonLink_secondsecond :=");

		for (NodePairProtect npp : NodeAndProRoute) {
			NodePair nodepair1 = npp.getnodepair();
			ArrayList<LinearRoute> routelist1 = npp.getroutelist();

			for (int a = 0; a < routelist1.size() - 1; a++) {
				LinearRoute route1 = routelist1.get(a);
				// System.out.println(routelist1.size());
				// System.out.println(nodepair1.getName());
				// route1.OutputRoute_node(route1);//debug
				for (int b = a + 1; b < routelist1.size(); b++) {
					LinearRoute route2 = routelist1.get(b);
					// route2.OutputRoute_node(route2);//debug
					cross = 0;
					cross = nc.nodelistcompare(route1.getNodelist(), route2.getNodelist());
					if (cross == 1)
						continue;

					for (NodePairProtect npp2 : NodeAndProRoute) {// 取出另一对nodepair
						NodePair nodepair2 = npp2.getnodepair();
						// System.out.println("节点对：
						// "+nodepair2.getName());//debug
						if (nodepair2.getName().equals(nodepair1.getName()))
							continue;
						ArrayList<LinearRoute> routelist2 = npp2.getroutelist();

						for (int c = 0; c < routelist2.size() - 1; c++) {
							LinearRoute route3 = routelist2.get(c);

							// route3.OutputRoute_node(route3);//debug
							for (int d = c + 1; d < routelist2.size(); d++) {
								LinearRoute route4 = routelist2.get(d);
								// route4.OutputRoute_node(route4);//debug
								cross = 0;
								cross = nc.nodelistcompare(route3.getNodelist(), route4.getNodelist());
								if (cross == 1)
									continue;
								// 以上取出不同nodepair中不相交的两条route

								HashMap<String, Link> linklist = mylayer.getLinklist();
								Iterator<String> iter1 = linklist.keySet().iterator();
								ArrayList<Node> nodelistOnLink = new ArrayList<Node>();
								
								while (iter1.hasNext()) {
								int common1 = 0;
									Link link = (Link) (linklist.get(iter1.next()));
									nodelistOnLink.clear();
									nodelistOnLink.add(link.getNodeA());
									nodelistOnLink.add(link.getNodeB());

									int cross1 = 0;
									int cross2 = 0;
									cross1=nc.nodelistcompare(nodelistOnLink, route1.getNodelist());
									if(cross1==1)
									cross2=nc.nodelistcompare(nodelistOnLink, route3.getNodelist());
									if (cross1 == 1 && cross2 == 1) {
										common1 = 1;
									}

									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat",nodepair1.getName() + "   ");
									route1.OutputRoute_node(route1, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat","   ");
									route2.OutputRoute_node(route2, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat",nodepair2.getName() + "   ");
									route3.OutputRoute_node(route3, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat","   ");
									route4.OutputRoute_node(route4, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat",link.getName() + "    ");
									fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat", common1);

									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",nodepair1.getName() + "   ");
									route2.OutputRoute_node(route2,"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat","   ");
									route1.OutputRoute_node(route1,"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",nodepair2.getName() + "   ");
									route4.OutputRoute_node(route4,"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat","   ");
									route3.OutputRoute_node(route3,"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",link.getName() + "    ");
									fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat", common1);

									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",nodepair1.getName() + "   ");
									route1.OutputRoute_node(route1,"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat","   ");
									route2.OutputRoute_node(route2,"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",nodepair2.getName() + "   ");
									route4.OutputRoute_node(route4,"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat","   ");
									route3.OutputRoute_node(route3,"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",link.getName() + "    ");
									fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat", common1);
								}

								HashMap<String, Link> linklist2 = mylayer.getLinklist();
								Iterator<String> iter2 = linklist2.keySet().iterator();
								ArrayList<Node> nodelistOnLink2 = new ArrayList<Node>();
								
								while (iter2.hasNext()) {
								int common2 = 0;
									Link link = (Link) (linklist.get(iter2.next()));
									nodelistOnLink2.clear();
									nodelistOnLink2.add(link.getNodeA());
									nodelistOnLink2.add(link.getNodeB());

									int cross1 = 0;
									int cross2 = 0;
									cross1=nc.nodelistcompare(nodelistOnLink2, route1.getNodelist());
									if(cross1==1)
									cross2=nc.nodelistcompare(nodelistOnLink2, route4.getNodelist());
									if (cross1 == 1 && cross2 == 1) {
										common2 = 1;
									}

									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat",nodepair1.getName() + "   ");
									route1.OutputRoute_node(route1, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat","   ");
									route2.OutputRoute_node(route2, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat",nodepair2.getName() + "   ");
									route4.OutputRoute_node(route4, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat","   ");
									route3.OutputRoute_node(route3, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat",
											"   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat",
											link.getName() + "    ");
									fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat", common2);

									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",
											nodepair1.getName() + "   ");
									route2.OutputRoute_node(route2,
											"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",
											"   ");
									route1.OutputRoute_node(route1,
											"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",
											"   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",
											nodepair2.getName() + "   ");
									route3.OutputRoute_node(route3,
											"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",
											"   ");
									route4.OutputRoute_node(route4,
											"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",
											"   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",
											link.getName() + "    ");
									fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat", common2);

									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",
											nodepair1.getName() + "   ");
									route1.OutputRoute_node(route1,
											"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",
											"   ");
									route2.OutputRoute_node(route2,
											"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",
											"   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",
											nodepair2.getName() + "   ");
									route3.OutputRoute_node(route3,
											"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",
											"   ");
									route4.OutputRoute_node(route4,
											"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",
											"   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",
											link.getName() + "    ");
									fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat", common2);

								}
								HashMap<String, Link> linklist3 = mylayer.getLinklist();
								Iterator<String> iter3 = linklist3.keySet().iterator();
								ArrayList<Node> nodelistOnLink3 = new ArrayList<Node>();
								
								while (iter3.hasNext()) {
								int common3 = 0;
									Link link = (Link) (linklist.get(iter3.next()));
									nodelistOnLink3.clear();
									nodelistOnLink3.add(link.getNodeA());
									nodelistOnLink3.add(link.getNodeB());

									int cross1 = 0;
									int cross2 = 0;
									cross1=nc.nodelistcompare(nodelistOnLink3, route2.getNodelist());
									if(cross1==1)
									cross2=nc.nodelistcompare(nodelistOnLink3, route3.getNodelist());
									if (cross1 == 1 && cross2 == 1) {
										common3 = 1;
									}

									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat",nodepair1.getName() + "   ");
									route2.OutputRoute_node(route2, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat","   ");
									route1.OutputRoute_node(route1, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat",nodepair2.getName() + "   ");
									route3.OutputRoute_node(route3, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat","   ");
									route4.OutputRoute_node(route4, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat",link.getName() + "    ");
									fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat", common3);

									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",nodepair1.getName() + "   ");
									route1.OutputRoute_node(route1,"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat","   ");
									route2.OutputRoute_node(route2,"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",nodepair2.getName() + "   ");
									route4.OutputRoute_node(route4,"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat","   ");
									route3.OutputRoute_node(route3,"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",link.getName() + "    ");
									fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat", common3);

									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",nodepair1.getName() + "   ");
									route2.OutputRoute_node(route2,"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat","   ");
									route1.OutputRoute_node(route1,"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",nodepair2.getName() + "   ");
									route4.OutputRoute_node(route4,"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat","   ");
									route3.OutputRoute_node(route3,"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",link.getName() + "    ");
									fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat", common3);
								}

								HashMap<String, Link> linklist4 = mylayer.getLinklist();
								Iterator<String> iter4 = linklist4.keySet().iterator();
								ArrayList<Node> nodelistOnLink4 = new ArrayList<Node>();
								
								while (iter4.hasNext()) {
								int common4 = 0;
									Link link = (Link) (linklist.get(iter4.next()));
									nodelistOnLink4.clear();
									nodelistOnLink4.add(link.getNodeA());
									nodelistOnLink4.add(link.getNodeB());

									int cross1 = 0;
									int cross2 = 0;
									cross1=nc.nodelistcompare(nodelistOnLink4, route2.getNodelist());
									if(cross1==1)
									cross2=nc.nodelistcompare(nodelistOnLink4, route4.getNodelist());
									if (cross1 == 1 && cross2 == 1) {
										common4 = 1;
									}

									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat",nodepair1.getName() + "   ");
									route2.OutputRoute_node(route2, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat","   ");
									route1.OutputRoute_node(route1, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat",nodepair2.getName() + "   ");
									route4.OutputRoute_node(route4, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat","   ");
									route3.OutputRoute_node(route3, "F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat",link.getName() + "    ");
									fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat", common4);

									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",nodepair1.getName() + "   ");
									route1.OutputRoute_node(route1,"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat","   ");
									route2.OutputRoute_node(route2,"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",nodepair2.getName() + "   ");
									route3.OutputRoute_node(route3,"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat","   ");
									route4.OutputRoute_node(route4,"F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat",link.getName() + "    ");
									fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat", common4);

									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",nodepair1.getName() + "   ");
									route2.OutputRoute_node(route2,"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat","   ");
									route1.OutputRoute_node(route1,"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",nodepair2.getName() + "   ");
									route3.OutputRoute_node(route3,"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat","   ");
									route4.OutputRoute_node(route4,"F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat","   ");
									fileout.filewrite_without("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat",link.getName() + "    ");
									fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat", common4);
								}
							}
						}
					}
				}
			}
		}
		fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comfirstfirst.dat", ";");
		fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comfirstsecond.dat", ";");
		fileout.filewrite("F:\\programFile\\mixed1bi1bi1\\comsecondsecond.dat", ";");
	}
}
