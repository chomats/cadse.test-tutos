package fr.imag.adele.cadse.test.tutos.tuto1;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.GTCadseRTConstants;
import fr.imag.adele.cadse.test.GTEclipseConstants;
import fr.imag.adele.cadse.test.gtworkbench_part.GTShell;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;



public class Tuto1Part2_tc_execution extends TutoTestCase {

	@Test
	public void test_App() throws Exception {
		
		/* Hello2App */
		workspaceView.contextMenu(null, GTCadseRTConstants.CONTEXTMENU_NEW, "WebApp").click();
		shell = new GTShell("Create WebApp");
		shell.findField(CadseGCST.ITEM_at_NAME_).typeText("Hello2App");
		shell.close();
		
		/* hello2Servlet */
		workspaceView.findTree().selectNode("Hello2App").contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("Servlet").click();
		shell = new GTShell("WebComponent URL Definition");
		shell.findField(CadseGCST.ITEM_at_NAME_).typeText("Hello2Servlet");
		shell.findTextWithLabel("relativeURL").typeText("hello2");
		shell.capture("image117");
		shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		shell.findTextWithLabel("className").typeText("hello2Servlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.capture("image118");
		shell.close();	
	}
}
