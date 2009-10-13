package fr.imag.adele.cadse.test.tutos.tuto2;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.inGroup;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.hamcrest.Matcher;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;

import fr.imag.adele.cadse.test.GTCadseRTConstants;
import fr.imag.adele.cadse.test.GTEclipseConstants;
import fr.imag.adele.cadse.test.GTScreenshot;
import fr.imag.adele.cadse.test.GTTestParameters;
import fr.imag.adele.cadse.test.gtmenu.GTMenu;
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
		SWTBotPreferences.TIMEOUT = 5000;
		GTScreenshot.setScreenshotPath(System.getProperty("test.screenshotPath"));
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
		propertiesView.findField("sel").check(true, "WebAppModel", CadseDefinitionManager.DATA_MODEL, "WebApp");
		propertiesView.findField("sel").check(true, "WebAppModel", CadseDefinitionManager.DATA_MODEL, "WebComponent");
		propertiesView.findField("sel").expand(true, "WebAppModel", CadseDefinitionManager.DATA_MODEL, "WebComponent");
	//	propertiesView.findField("sel").check(false, "WebAppModel", CadseDefinitionManager.DATA_MODEL, "WebComponent", "#invert_part_hasComp_to_WebApp"); /* This two lines performs scrolling */
		propertiesView.findField("sel").check(true, "WebAppModel", CadseDefinitionManager.DATA_MODEL, "WebComponent", "uses"); /* This two lines performs scrolling */ 
		propertiesView.findField("sel").check(false, "WebAppModel", CadseDefinitionManager.DATA_MODEL, "WebComponent", "uses");
		propertiesView.findField("sel").scroll();
		propertiesView.capture("image020");

		/* Can create item and Can create link check boxes */
		workspaceView.findTree().collapse();
		workspaceView.findTree().selectNode(vi_link_hasComp);
		workspaceView.capture("image024");
		propertiesView.showTab("ViewLinkType");
		propertiesView.findField("can-create-item").check(false);
		propertiesView.findField("can-create-link").check(false);
		propertiesView.capture("image026");
		
	}
	
	@Test
	public void test_zp21_check_compilation() throws Exception {
		checkCompilationWebApp();
	}
}