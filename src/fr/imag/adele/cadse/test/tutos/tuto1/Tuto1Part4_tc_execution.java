package fr.imag.adele.cadse.test.tutos.tuto1;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Test;


import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.GTCadseRTConstants;
import fr.imag.adele.cadse.test.GTEclipseConstants;
import fr.imag.adele.cadse.test.GTTestParameters;
import fr.imag.adele.cadse.test.gtworkbench_part.GTShell;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;

public class Tuto1Part4_tc_execution extends TutoTestCase {

	@Test
	public void test_init() throws Exception {
		
		GTTestParameters.banner();
		//SWTBotPreferences.PLAYBACK_DELAY = 100;
		SWTBotPreferences.TIMEOUT = 30000;
	}
	
	@Test
	public void test_delete() throws Exception {
		
		/* HelloApp */
		workspaceView.findTree().selectNode("HelloApp").contextMenu("Delete HelloApp").click();
		shell = new GTShell(GTCadseRTConstants.DELETE_TITLE);
		shell.capture("image138");
		shell.close();
		
		/* Hello2App */
		workspaceView.findTree().selectNode("Hello2App").contextMenu("Delete Hello2App").click();
		shell = new GTShell(GTCadseRTConstants.DELETE_TITLE);
		shell.close();
				
		/* Hello3App */
		workspaceView.contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("WebApp").click();
		shell = new GTShell("Create WebApp");
		shell.findField(CadseGCST.ITEM_at_NAME_).typeText("Hello3App");
		shell.close();
		
		/* Hello3Servlet */
		workspaceView.findTree().selectNode("Hello3App").contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("Servlet").click();
		shell = new GTShell("WebComponent URL Definition");
		shell.findField(CadseGCST.ITEM_at_NAME_).typeText("Hello3Servlet");
		shell.findTextWithLabel("relativeURL").typeText("hello3");
		shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		shell.findTextWithLabel("className").typeText("hello3Servlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.close();	
		
		/* Screenshot */
		packageExplorerView.show();
		packageExplorerView.findTree().selectNode("Hello3App", "sources");
		packageExplorerView.findTree().selectNode("Hello3App.Hello3Servlet", "sources");
		packageExplorerView.findTree().selectNode("ServletAPI", "sources");
		packageExplorerView.findTree().selectNode("Hello3App");
		packageExplorerView.capture("image140");
	}
}
