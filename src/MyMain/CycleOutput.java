package MyMain;

import java.util.ArrayList;

import file.File_output;
import network.Node;
import subgraph.Cycle;

public class CycleOutput {

	public void cycleoutput(ArrayList<Cycle> cyclelist, String filename) {
		File_output filewrite = new File_output();
		for (int i = 0; i < cyclelist.size(); i++) {

			Cycle CycleContain = cyclelist.get(i);

			int m = 0;
			for (Node Newnode : CycleContain.getNodelist()) {

				filewrite.filewrite_without(filename, Newnode.getName());
				// System.out.print(Newnode.getName());

				if (Newnode.getName() == CycleContain.getNodelist().get(0).getName() && m == 0) {
					filewrite.filewrite_without(filename, "-");
					// System.out.print("-");

				}
				if (Newnode.getName() != CycleContain.getNodelist().get(0).getName()) {
					// System.out.print("-");
					filewrite.filewrite_without(filename, "-");

				}
				m = 1;
			}

			filewrite.filewrite_without(filename, "\r\n");

			// System.out.println();
		}
	}

	public void cycleoutput(Cycle CycleContain, String filename) {

		File_output filewrite = new File_output();
		int m = 0;
		for (Node Newnode : CycleContain.getNodelist()) {

			filewrite.filewrite(filename, Newnode.getName());
			// System.out.print(Newnode.getName());
			if (Newnode.getName() == CycleContain.getNodelist().get(0).getName() && m == 0) {
				// System.out.print("-");
				filewrite.filewrite_without(filename, "-");
			}
			if (Newnode.getName() != CycleContain.getNodelist().get(0).getName()) {
				// System.out.print("-");
				filewrite.filewrite_without(filename, "-");
			}
			m = 1;
		}

		// filewrite.filewrite(filename, "\r\n");
		// System.out.println();
	}
}
