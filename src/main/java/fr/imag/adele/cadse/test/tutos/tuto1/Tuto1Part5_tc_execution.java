package fr.imag.adele.cadse.test.tutos.tuto1;


import org.junit.Test;


import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;

public class Tuto1Part5_tc_execution extends TutoTestCase {

	@Test
	public void test_feature() throws Exception {

		workspaceView.show();
		
		/* ServletAPI deletion */
		workspaceView.contextMenu(new GTTreePath("ServletAPI"), "Delete ServletAPI").click();
		shell = new GTCadseShell(GTCadseRTConstants.DELETE_TITLE);
		shell.close();

		/* ServletAPI creation */
		workspaceView.contextMenuNew("Library").click();
		shell = new GTCadseShell("Create Library");
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("ServletAPI");
		shell.close();
		
		/* hello3Servlet */
		workspaceView.selectNode("Hello3App", "hello3Servlet");
		propertiesView.showTab("WebComponent Options");
		propertiesView.findButton("Add...").click();
		
		shell = new GTCadseShell("Select a value.");
		shell.selectNode("ServletAPI");
		shell.close();
		
		/* Screenshot */
		packageExplorerView.show();
		packageExplorerView.selectNode(new GTTreePath("Hello3App"), true);
		packageExplorerView.selectNode(new GTTreePath("ServletAPI"), true);
		packageExplorerView.selectNode(new GTTreePath("Hello3App.Hello3Servlet", "Item Dependencies", "ServletAPI"), true);		
		packageExplorerView.capture("image148");
	}
}
