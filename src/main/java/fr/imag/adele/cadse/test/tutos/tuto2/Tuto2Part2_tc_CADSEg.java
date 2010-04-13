package fr.imag.adele.cadse.test.tutos.tuto2;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseField;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import java.io.File;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gtmenu.GTMenu;
import fr.imag.adele.graphictests.gtworkbench_part.GTTextEditor;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.graphictests.test.GTTestParameters;

/**
 * Performs the official simple tutorial
 */
public class Tuto2Part2_tc_CADSEg extends TutoTestCase {

	GTCadseShell shell;

	/**
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_item_content_customization() throws Exception {

		workspaceView.findTree().collapse();

		workspaceView.selectNode(webAppModel, true);
		workspaceView.capture("image042");
		propertiesView.showTab("CADSE definition");

		findCadseField(propertiesView, CadseGCST.CADSE_DEFINITION_at_IMPORTS_).addBrowser("model.webapp.template");
		findCadseField(propertiesView, CadseGCST.CADSE_DEFINITION_at_IMPORTS_).scroll();
		propertiesView.capture("image044");

		// extends class check box
		workspaceView.selectNode(content_servlet);
		workspaceView.capture("image048");
		propertiesView.showTab("JavaProjectContentModel");
		findCadseField(propertiesView, CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_).check(true);
		propertiesView.capture("image050");
	}

	/**
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_item_manager_structure() throws Exception {

		packageExplorerView.selectNode(project_package, true);
		packageExplorerView.contextMenu(null, GTCadseRTConstants.CONTEXTMENU_IMPORT).click();

		shell = new GTCadseShell("Import");
		shell.selectNode(importArchiveFile);
		shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		shell.setComboBoxWithLabelText("From archive file:", System.getProperty("test.resourcesPath") + File.separator
				+ "samples.zip");
		shell.selectNode(archivePath);
		shell.findButton("Select All").click();
		shell.findCheckBox("Overwrite existing resources without warning").select();
		shell.close();

		// Copying content into clipboard
		copyFileIntoClipboard(file_sample1);

		// Takes screenshot
		packageExplorerView.findTree().collapse();
		packageExplorerView.maximize(); // Toggle maximize
		packageExplorerView.selectNode(servletContentItem, true);
		packageExplorerView.capture("image058");
		packageExplorerView.maximize(); // Toggle maximize

		// Edits the file
		packageExplorerView.findTree().doubleClick(servletContentItem);
		GTTextEditor editor = new GTTextEditor("ServletManager.java");
		editor.show();
		editor.navigateTo(editor.cursorPosition().line + 1, 0);
		GTMenu.clickPaste();
		editor.save();

		// Adds org.eclipse.core.runtime
		workspaceView.selectNode(webAppModel);
		propertiesView.showTab("CADSE definition");
		findCadseField(propertiesView, CadseGCST.CADSE_DEFINITION_at_IMPORTS_).addBrowser(
				"fr.imag.adele.cadse.core.impl");

		// Copying imports into clipboard
		copyFileIntoClipboard(file_import1);

		// Edits the file
		packageExplorerView.findTree().doubleClick(servletContentItem);
		editor = new GTTextEditor("ServletManager.java");
		editor.show();
		editor.navigateTo(2, 0);
		GTMenu.clickPaste();
		editor.save();
	}

	@Test
	public void test_zp22_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel, GTTestParameters.TIMEOUT);
	}
}