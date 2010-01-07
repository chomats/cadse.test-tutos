package fr.imag.adele.cadse.test.tutos.tuto2;

import java.io.File;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.graphictests.gtmenu.GTMenu;
import fr.imag.adele.graphictests.gtworkbench_part.GTEditor;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;


/**
 * Performs the official simple tutorial
 */
public class Tuto2Part2_tc_CADSEg extends TutoTestCase {
		
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

		GTCadseFactory.findField(propertiesView, CadseGCST.CADSE_DEFINITION_at_IMPORTS_).add("model.webapp.template");
		propertiesView.capture("image044");

		// extends class check box
		workspaceView.findTree().selectNode(content_servlet);
		workspaceView.capture("image048");
		propertiesView.showTab("Java Project Content");
		GTCadseFactory.findField(propertiesView, CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_).check(true);
		propertiesView.capture("image050");
	}	

	/**
	 * 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_item_manager_structure() throws Exception {

		packageExplorerView.findTree().selectNode(project_package).expand().contextMenu(GTCadseRTConstants.CONTEXTMENU_IMPORT).click();

		shell = new GTCadseShell("Import");
		shell.findTree().selectNode(importArchiveFile);
		shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		shell.setComboBoxWithLabelText("From archive file:", System.getProperty("test.resourcesPath") + File.separator + "samples.zip");
		shell.findTree().selectNode(archivePath);
		shell.findButton("Select All").click();
		shell.findCheckBox("Overwrite existing resources without warning").select();
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
		packageExplorerView.findTree().selectNode(servletContentItem).expand();
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
		GTCadseFactory.findField(propertiesView, CadseGCST.CADSE_DEFINITION_at_IMPORTS_).add("fr.imag.adele.cadse.core.impl"); 
		
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
		checkCompilationErrors(workspaceView, webAppModel);
	}
}