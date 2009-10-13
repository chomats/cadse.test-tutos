package fr.imag.adele.cadse.test.tutos.tuto2;


import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Test;

import fr.imag.adele.cadse.test.GTScreenshot;
import fr.imag.adele.cadse.test.GTTestParameters;
import fr.imag.adele.cadse.test.gtmenu.GTMenu;
import fr.imag.adele.cadse.test.gtmenu.GTMenuConstants;
import fr.imag.adele.cadse.test.gtworkbench_part.GTEditor;
import fr.imag.adele.cadse.test.gtworkbench_part.GTShell;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;


/**
 * Performs the official simple tutorial
 */
public class Tuto2Part3_tc_CADSEg extends TutoTestCase {
	
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
	 * @throws Exception the exception
	 */
	@Test
	public void test_item_creation_process_customization() throws Exception {

		// Copying content into clipboard
		packageExplorerView.findTree().selectNode(file_sample2).doubleClick();
		GTEditor editor = new GTEditor("sample2.java");
		editor.show();
		GTMenu.clickselectAll();
		GTMenu.clickCopy();
		editor.close();
				
		// New Class
		packageExplorerView.findTree().selectNode(webapp_package);
		GTMenu.clickItem(GTMenuConstants.FILE_MENU, "New", "Class");
			
		shell = new GTShell("New Java Class");
		shell.findTextWithLabel("Name:").typeText("WebAppServletSynchro");
		shell.close();

		packageExplorerView.findTree().selectNode(webapp_servletSynchro).doubleClick();

		editor = new GTEditor("WebAppServletSynchro.java");
		GTMenu.clickselectAll();
		GTMenu.clickPaste();
		editor.save();
		

		// Manifest configuration
		packageExplorerView.findTree().collapse();
		packageExplorerView.findTree().selectNode(manifest);
		packageExplorerView.capture("image063");
		packageExplorerView.findTree().selectNode(manifest).doubleClick();
		editor = new GTEditor("Model.Workspace.WebAppModel");
		editor.showCTab("Dependencies") ;

		editor.buttonInSection("Add...", "Imported Packages").click();
		shell = new GTShell("Package Selection");
		shell.findText().typeText("fr.imag.adele.cadse.core.transaction");
		try {
			shell.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		editor.buttonInSection("Add...", "Imported Packages").click();
		shell = new GTShell("Package Selection");
		shell.findText().typeText("fr.imag.adele.cadse.core.delta");
		try {
			shell.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		editor.save();
		editor.capture("image064");	
		

		// Adds imports
		// in fact, we have type them, so we don't need to add them

		
		// Init() method
		packageExplorerView.findTree().selectNode(servletManagerClass);
		GTMenu.clickItem(GTMenuConstants.SOURCE_MENU, "Override/Implement Methods...");
		editor = new GTEditor("ServletManager.java");
		shell = new GTShell("Override/Implement Methods");
		shell.findTree().selectNode("DefaultItemManager", "init()").check(true);
		shell.close();

		editor.find("init()");
		editor.find("super.init();");
		
		editor.typeText("new WebAppServletSynchro();");
		editor.save(); // makes quickfix appears
		editor.quickfix("Import 'WebAppServletSynchro' (model.webapp)");
		editor.save();
	}
	
	@Test
	public void test_zp23_check_compilation() throws Exception {
		checkCompilationWebApp();
	}
}