package fr.imag.adele.cadse.test.tutos.tuto2;


import org.junit.Test;

import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gtmenu.GTMenu;
import fr.imag.adele.graphictests.gtmenu.GTMenuConstants;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTEditor;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;


/**
 * Performs the official simple tutorial
 */
public class Tuto2Part3_tc_CADSEg extends TutoTestCase {
	
	/**
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_item_creation_process_customization() throws Exception {

		// Copying content into clipboard
		packageExplorerView.findTree().doubleClick(file_sample2);
		GTEditor editor = new GTEditor("sample2.java");
		editor.show();
		GTMenu.clickselectAll();
		GTMenu.clickCopy();
		editor.close();
				
		// New Class
		packageExplorerView.selectNode(webapp_package);
		GTMenu.clickItem(GTMenuConstants.FILE_MENU, "New", "Class");
			
		shell = new GTCadseShell("New Java Class");
		shell.findTextWithLabel("Name:").typeText("WebAppServletSynchro");
		shell.close();

		packageExplorerView.findTree().doubleClick(webapp_servletSynchro);

		editor = new GTEditor("WebAppServletSynchro.java");
		GTMenu.clickselectAll();
		GTMenu.clickPaste();
		editor.save();
		

		// Manifest configuration
		packageExplorerView.collapse();
		packageExplorerView.selectNode(manifest);
		packageExplorerView.capture("image063");
		packageExplorerView.findTree().doubleClick(manifest);
		editor = new GTEditor("Model.Workspace.WebAppModel");
		editor.showCTab("Dependencies") ;

		editor.findSection("Imported Packages").findButton("Add...").click();
		shell = new GTCadseShell("Package Selection");
		shell.findText().typeText("fr.imag.adele.cadse.core.transaction");
		shell.close();

		editor.findSection("Imported Packages").findButton("Add...").click();
		shell = new GTCadseShell("Package Selection");
		shell.findText().typeText("fr.imag.adele.cadse.core.delta");
		shell.close();

		editor.save();
		editor.capture("image064");	
		

		// Adds imports
		// in fact, we have type them, so we don't need to add them

		
		// Init() method
		packageExplorerView.selectNode(servletManagerClass);
		GTMenu.clickItem(GTMenuConstants.SOURCE_MENU, "Override/Implement Methods...");
		editor = new GTEditor("ServletManager.java");
		shell = new GTCadseShell("Override/Implement Methods");
		shell.findTree().checkNode(new GTTreePath("DefaultItemManager", "init()"), true);
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
		checkCompilationErrors(workspaceView, webAppModel);
	}
}