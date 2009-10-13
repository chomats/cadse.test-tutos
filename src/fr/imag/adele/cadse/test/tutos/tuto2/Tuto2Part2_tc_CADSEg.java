package fr.imag.adele.cadse.test.tutos.tuto2;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.GTCadseRTConstants;
import fr.imag.adele.cadse.test.GTEclipseConstants;
import fr.imag.adele.cadse.test.GTScreenshot;
import fr.imag.adele.cadse.test.GTTestParameters;
import fr.imag.adele.cadse.test.gtmenu.GTMenu;
import fr.imag.adele.cadse.test.gtworkbench_part.GTEditor;
import fr.imag.adele.cadse.test.gtworkbench_part.GTShell;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;


/**
 * Performs the official simple tutorial
 */
public class Tuto2Part2_tc_CADSEg extends TutoTestCase {
	
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
	public void test_item_content_customization() throws Exception {

		workspaceView.findTree().collapse();

		workspaceView.findTree().selectNode(webAppModel).expand();
		workspaceView.capture("image042");
		propertiesView.showTab("Cadse definition");

		propertiesView.findField(CadseGCST.CADSE_DEFINITION_at_IMPORTS_).add("model.webapp.template");
		propertiesView.capture("image044");

		// extends class check box
		workspaceView.findTree().selectNode(content_servlet);
		workspaceView.capture("image048");
		propertiesView.showTab("Java Project Content");
		propertiesView.findField(CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_).check(true);
		propertiesView.capture("image050");
	}	

	/**
	 * 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_item_manager_structure() throws Exception {

		// Getting samples into resources
		packageExplorerView.findTree().selectNode(project_package).expand().contextMenu(GTCadseRTConstants.CONTEXTMENU_IMPORT).click();

		shell = new GTShell("Import");
		shell.findTree().selectNode(importArchiveFile);
		shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		shell.setComboBoxWithLabelText("From archive file:", System.getProperty("test.resourcesPath")+"samples.zip");
		shell.findTree().selectNode(archivePath);
		shell.findButton("Select All").click();
		shell.findCheckBox("Overwrite existing resources without warning").click();
		shell.close();

		// Copying content into clipboard
		packageExplorerView.findTree().selectNode(file_sample1).doubleClick();
		GTEditor editor = new GTEditor("sample1.java");
		editor.show();
		GTMenu.clickselectAll();
		GTMenu.clickCopy();
		editor.close();
		
		// Takes screenshot
		packageExplorerView.findTree().collapse();
		packageExplorerView.maximize(); // Toggle maximize
		packageExplorerView.findTree().selectNode(servletContentItem, true);
		packageExplorerView.capture("image058");
		packageExplorerView.maximize(); // Toggle maximize

		// Edits the file
		packageExplorerView.findTree().selectNode(servletContentItem).doubleClick();
		editor = new GTEditor("ServletManager.java");
		editor.show();
		editor.navigateTo(editor.cursorPosition().line+1, 0);
		GTMenu.clickPaste();
		editor.save();
		
		// Adds org.eclipse.core.runtime
		workspaceView.findTree().selectNode(webAppModel);
		propertiesView.showTab("Cadse definition");
		// TODO C'est l'un ou l'autre!
		propertiesView.findField(CadseGCST.CADSE_DEFINITION_at_IMPORTS_).add("fr.imag.adele.cadse.core.impl"); // c'est l'un ou l'autre
		//propertiesView.findField(WorkspaceCST.CADSE_DEFINITION_at_IMPORTS_).add("org.eclipse.core.runtime"); // c'est l'un ou l'autre
		
		// Copying imports into clipboard
		packageExplorerView.findTree().selectNode(file_import1).doubleClick();
		editor = new GTEditor("imports1.java");
		editor.show();
		GTMenu.clickselectAll();
		GTMenu.clickCopy();
		editor.close();

		// Edits the file
		packageExplorerView.findTree().selectNode(servletContentItem).doubleClick();
		editor = new GTEditor("ServletManager.java");
		editor.show();
		editor.navigateTo(2, 0);
		GTMenu.clickPaste();
		editor.save();
	}
	
	@Test
	public void test_zp22_check_compilation() throws Exception {
		checkCompilationWebApp();
	}
}