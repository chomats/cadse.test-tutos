package fr.imag.adele.cadse.test.tutos.tuto2;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Test;

import fr.imag.adele.cadse.test.GTCadseRTConstants;
import fr.imag.adele.cadse.test.GTEclipseConstants;
import fr.imag.adele.cadse.test.GTScreenshot;
import fr.imag.adele.cadse.test.GTTestParameters;
import fr.imag.adele.cadse.test.gtworkbench_part.GTShell;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;

public class Tuto2Part3_tc_execution extends TutoTestCase {

	@Test
	public void test_init() throws Exception {

		GTTestParameters.banner();
		//SWTBotPreferences.PLAYBACK_DELAY = 100;
		SWTBotPreferences.TIMEOUT = 5000;
		GTScreenshot.setScreenshotPath(System.getProperty("test.screenshotPath"));
	}


	@Test
	public void test_run() throws Exception {

		// TODO all this part!
		
		
		/* Delete test.HelloServlet Servlet 
		workspaceView.findTree().selectNode("HelloApp", "test.HelloServlet").contextMenu("Delete test.HelloServlet").click();
		shell = new GTShell(GTCadseRTConstants.DELETE_TITLE);
		shell.close();
		
		/* New Servlet 
		workspaceView.findTree().selectNode("HelloApp").contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("Servlet").click();
		shell = new GTShell("WebComponent URL Definition");
		shell.findField(CadseRootCST.ITEM_TYPE_at_NAME_).typeText("test.HelloServlet");
		shell.findTextWithLabel("relativeURL").typeText("hello");
		shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		shell.findTextWithLabel("className").typeText("HelloServlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.close();
		
		packageExplorerView.show();
		packageExplorerView.findTree().selectNode("ServletAPI", "sources");
		packageExplorerView.findTree().selectNode("ServletAPI");
		packageExplorerView.capture("image076");
	
		*/
	}
}
