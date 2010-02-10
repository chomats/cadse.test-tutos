package fr.imag.adele.cadse.test.tutos.tuto1;

import org.junit.Test;


import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;

public class Tuto1Part3_tc_execution extends TutoTestCase {

	@Test
	public void test_modification_pages() throws Exception {
		
		/* ServletAPI library */
		workspaceView.contextMenuNew("Library").click();
		shell = new GTCadseShell("Library");
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("ServletAPI");
		shell.close();
		
		/* hello2Servlet */
		workspaceView.selectNode("Hello2App", "Hello2Servlet");
		
		// FIXME PAGES!!!! propertiesView.showTab("WebComponent Options");
		propertiesView.showTab("Servlet");
		propertiesView.findButton("Add...").click();
		
		shell = new GTCadseShell(/*"Select a value."*/""); 
		shell.selectNode("ServletAPI");
		shell.capture("image128");
		shell.close();
	}
}
