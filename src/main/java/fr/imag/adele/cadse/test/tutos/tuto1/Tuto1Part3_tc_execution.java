package fr.imag.adele.cadse.test.tutos.tuto1;

import org.junit.Test;


import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;

public class Tuto1Part3_tc_execution extends TutoTestCase {

	@Test
	public void test_modification_pages() throws Exception {
		
		/* ServletAPI library */
		workspaceView.contextMenu(null, GTCadseRTConstants.CONTEXTMENU_NEW, "Library").click();
		shell = new GTCadseShell("Create Library");
		GTCadseFactory.findCadseWorkbenchPart(shell).findField(CadseGCST.ITEM_at_NAME_).typeText("ServletAPI");
		shell.close();
		
		/* hello2Servlet */
		workspaceView.findTree().selectNode("Hello2App", "hello2Servlet");
		propertiesView.showTab("WebComponent Options");
		propertiesView.findButton("Add...").click();
		
		shell = new GTCadseShell("Select a value.");
		shell.findTree().selectNode("ServletAPI");
		shell.capture("image128");
		shell.close();
	}
}
