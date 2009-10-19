package fr.imag.adele.cadse.test.tutos.tuto2;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.GTCadseRTConstants;
import fr.imag.adele.cadse.test.GTTestParameters;
import fr.imag.adele.cadse.test.gtworkbench_part.GTShell;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;

/**
 * Performs the official simple tutorial
 */
public class Tuto2Part1_tc_CADSEg extends TutoTestCase {

	/**
	 * Performs initializations for this test.
	 * Sets the timeout, velocity,...
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_tuto1_init() throws Exception {

		GTTestParameters.banner();
		//SWTBotPreferences.PLAYBACK_DELAY = 100;
		SWTBotPreferences.TIMEOUT = 30000;
	}
	
	/**
	 * 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_view() throws Exception {

		workspaceView.findTree().collapse();

		/* Creates a new view */
		workspaceView.findTree().selectNode(view_model);
		workspaceView.capture("image014");
		workspaceView.findTree().selectNode(view_model).contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu(GTCadseRTConstants.CONTEXTMENU_VIEW).click();
		shell = new GTShell(CadseGCST.VIEW);
		shell.findField(CadseGCST.ITEM_at_NAME_).typeText("WebAppList");
		shell.capture("image016");
		shell.close();


		/* Configuration for this new view */
		workspaceView.findTree().selectNode(vm_webAppList);
		workspaceView.capture("image018");

		propertiesView.showTab("View");
		propertiesView.findField("sel").check(true, "WebAppModel", CadseDefinitionManager.DATA_MODEL, "WebApp", "hasComp");
		propertiesView.findField("sel").check(true, "WebAppModel", CadseDefinitionManager.DATA_MODEL, "WebComponent", "uses");
		propertiesView.findField("sel").check(false, "WebAppModel", CadseDefinitionManager.DATA_MODEL, "WebComponent", "uses");
		propertiesView.findField("sel").scroll();
		propertiesView.capture("image020"); // TODO pourquoi le capture enlève t-il le scrolling?

		/* Can create item and Can create link check boxes */
		workspaceView.findTree().collapse();
		workspaceView.findTree().selectNode(vi_link_hasComp);
		workspaceView.capture("image024");
		propertiesView.showTab("ViewLinkType");
		propertiesView.findField("can-create-item").check(false);
		propertiesView.findField("can-create-link").check(false);
		propertiesView.capture("image026");
		
		//TODO next line to prevent CADSE from throwing an exception. Should be removed soon (sept. 30, 2009)  
		bot.sleep(2000);
	}
	
	@Test
	public void test_zp21_check_compilation() throws Exception {
		checkCompilationWebApp();
	}
}