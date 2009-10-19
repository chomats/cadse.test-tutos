package fr.imag.adele.cadse.test.tutos.tuto1;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Test;


import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.GTCadseRTConstants;
import fr.imag.adele.cadse.test.GTTestParameters;
import fr.imag.adele.cadse.test.gtworkbench_part.GTShell;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;

public class Tuto1Part3_tc_execution extends TutoTestCase {

	@Test
	public void test_init() throws Exception {
		
		GTTestParameters.banner();
		//SWTBotPreferences.PLAYBACK_DELAY = 100;
		SWTBotPreferences.TIMEOUT = 30000;
	}
			
	@Test
	public void test_modification_pages() throws Exception {
		
		/* ServletAPI library */
		workspaceView.contextMenu(null, GTCadseRTConstants.CONTEXTMENU_NEW, "Library").click();
		shell = new GTShell("Create Library");
		shell.findField(CadseGCST.ITEM_at_NAME_).typeText("ServletAPI");
		shell.close();
		
		/* hello2Servlet */
		workspaceView.findTree().selectNode("Hello2App", "hello2Servlet");
		propertiesView.showTab("WebComponent Options");
		propertiesView.findButton("Add...").click();
		
		shell = new GTShell("Select a value.");
		shell.findTree().selectNode("ServletAPI");
		shell.capture("image128");
		shell.close();
	}
}
