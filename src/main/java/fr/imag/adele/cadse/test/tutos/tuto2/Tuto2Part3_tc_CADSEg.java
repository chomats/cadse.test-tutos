package fr.imag.adele.cadse.test.tutos.tuto2;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gtmenu.GTMenu;
import fr.imag.adele.graphictests.gtmenu.GTMenuConstants;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTTextEditor;

/**
 * Performs the official simple tutorial
 */
public class Tuto2Part3_tc_CADSEg extends TutoTestCase {

	GTCadseShell shell;

	/**
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_item_creation_process_customization() throws Exception {

		// Copying content into clipboard
		copyFileIntoClipboard(file_sample2);

		// New Class
		packageExplorerView.selectNode(webapp_package);
		GTMenu.clickItem(GTMenuConstants.FILE_MENU, "New", "Class");
		shell = new GTCadseShell("New Java Class");
		shell.findTextWithLabel("Name:").typeText("WebAppServletSynchro");
		shell.close();
		packageExplorerView.findTree().doubleClick(webapp_servletSynchro);

		// Paste
		GTTextEditor editor = new GTTextEditor("WebAppServletSynchro.java");
		GTMenu.clickselectAll();
		GTMenu.clickPaste();
		editor.save();

		// Manifest configuration
		packageExplorerView.findTree().collapse();
		packageExplorerView.selectNode(manifest);
		packageExplorerView.capture("image063");
		packageExplorerView.findTree().doubleClick(manifest);
		editor = new GTTextEditor("Model.Workspace.WebAppModel");
		editor.showCTab("Dependencies");

		editor.findSection("Imported Packages").findButton("Add...").click();
		shell = new GTCadseShell("Package Selection");
		shell.findText().typeText("fr.imag.adele.cadse.core.transaction");
		shell.close();

		editor.findSection("Imported Packages").findButton("Add...").click();
		shell = new GTCadseShell("Package Selection");
		shell.findText().typeText("fr.imag.adele.cadse.core.transaction.delta");
		shell.close();

		editor.save();
		editor.capture("image064");

		// Adds imports
		// in fact, we have type them, so we don't need to add them

		// Before Init(), need to implement InitAction
		packageExplorerView.findTree().doubleClick(servletManagerClass);
		editor = new GTTextEditor("ServletManager.java");
		editor.find("{");
		editor.typeText("implements InitAction {");
		editor.save();
		editor.capture("image065");

		// Quickfix
		copyFileIntoClipboard(file_import2);
		editor.navigateTo(2, 0);
		GTMenu.clickPaste();
		editor.save();

		// Copying content into clipboard
		copyFileIntoClipboard(file_initMethodBody);

		// Init() method
		packageExplorerView.selectNode(servletManagerClass);
		GTMenu.clickItem(GTMenuConstants.SOURCE_MENU, "Override/Implement Methods...");
		shell = new GTCadseShell("Override/Implement Methods");
		shell.findTree().checkNode(new GTTreePath("InitAction", "init()"), true);
		shell.close();

		editor = new GTTextEditor("ServletManager.java");
		editor.find("init()");
		editor.find("// TODO Auto-generated method stub");
		GTMenu.clickPaste();
		editor.save();

		// Quickfix
		copyFileIntoClipboard(file_initMethodImport);
		editor.navigateTo(2, 0);
		GTMenu.clickPaste();
		editor.save();
	}

	@Test
	public void test_zp23_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel);
	}
}