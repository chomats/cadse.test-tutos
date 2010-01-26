package fr.imag.adele.cadse.test.tutos.tuto1;


import org.junit.Test;


import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;

public class Tuto1Part5_tc_execution extends TutoTestCase {

	@Test
	public void test_feature() throws Exception {

		workspaceView.show();
		
		/* ServletAPI deletion */
		workspaceView.findTree().selectNode("ServletAPI").contextMenu("Delete ServletAPI").click();
		shell = new GTCadseShell(GTCadseRTConstants.DELETE_TITLE);
		shell.close();

		/* ServletAPI creation */
		workspaceView.contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("Library").click();
		shell = new GTCadseShell("Create Library");
		GTCadseFactory.findCadseWorkbenchPart(shell).findField(CadseGCST.ITEM_at_NAME_).typeText("ServletAPI");
		shell.close();
		
		/* hello3Servlet */
		workspaceView.findTree().selectNode("Hello3App", "hello3Servlet");
		propertiesView.showTab("WebComponent Options");
		propertiesView.findButton("Add...").click();
		
		shell = new GTCadseShell("Select a value.");
		shell.findTree().selectNode("ServletAPI");
		shell.close();
		
		/* Screenshot */
		packageExplorerView.show();
		packageExplorerView.findTree().selectNode("Hello3App").expand();
		packageExplorerView.findTree().selectNode("ServletAPI").expand();
		packageExplorerView.findTree().selectNode("Hello3App.Hello3Servlet", "Item Dependencies", "ServletAPI").expand();		
		packageExplorerView.capture("image148");
	}
}
