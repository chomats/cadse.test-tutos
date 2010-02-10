package fr.imag.adele.cadse.test.tutos.tuto1;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;



public class Tuto1Part2_tc_execution extends TutoTestCase {

	@Test
	public void test_App() throws Exception {
		
		/* Hello2App */
		workspaceView.contextMenuNew("WebApp").click();
		shell = new GTCadseShell("WebApp");
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("Hello2App");
		shell.close();
		
		/* hello2Servlet */
		workspaceView.contextMenuNew(new GTTreePath("Hello2App"), "Servlet").click();
		
		// FIXME to be updated with user defined pages
		//shell = new GTCadseShell("WebComponent URL Definition");
		shell = new GTCadseShell("Servlet");
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("Hello2Servlet");
		shell.findTextWithLabel("relativeURL").typeText("hello2");
		shell.capture("image117");
		// PAGES!!!! shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		shell.findTextWithLabel("className").typeText("hello2Servlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.capture("image118");
		shell.close();
	}
}
