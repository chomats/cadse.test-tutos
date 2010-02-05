package fr.imag.adele.cadse.test.tutos.tuto2;


import model.workspace.copycomposer.CopyComposerCST;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gtmenu.GTMenu;
import fr.imag.adele.graphictests.gtworkbench_part.GTEditor;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;


/**
 * Performs the official simple tutorial
 */
public class Tuto2Part4_tc_CADSEg extends TutoTestCase {

	/**
	 * 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_build_model_definition() throws Exception {

		// Composition link
		workspaceView.collapse();
		workspaceView.selectNode(attr_hasComp);
		workspaceView.capture("image094");

		propertiesView.showTab("Link");
		GTCadseFactory.findField(propertiesView, CadseGCST.LINK_TYPE_at_COMPOSITION_).check(true);
		propertiesView.capture("image096");


		// Executed CADSEs
		workspaceView.contextMenu(webAppModel, "Executed CADSEs").click();
		shell = new GTCadseShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE);
		shell.selectCadses("CopyComposer");
		shell.capture("image097");
		shell.close();


		// JavaRefExporter
		workspaceView.contextMenuNew(mapping_servlet,CopyComposerCST.JAVA_REF_EXPORTER).click();
		shell = new GTCadseShell(/*CopyComposerCST.JAVA_REF_EXPORTER*/ ""); // FIXME to be updated with next cadse release
		shell.capture("image102");
		shell.close();

		workspaceView.collapse();
		workspaceView.selectNode(refExporter);
		workspaceView.capture("image104");
	}

	/**
	 * 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_copy_composer() throws Exception {

		// Creates copy composer
		workspaceView.collapse();
		workspaceView.selectNode(composite);
		workspaceView.capture("image106");
		workspaceView.contextMenu(composite, GTCadseRTConstants.CONTEXTMENU_NEW, "Copy Folder Content Composer").click();
		shell = new GTCadseShell(CopyComposerCST.COPY_INTO_FOLDER_COMPOSER);  
		
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("GenWarComposer");
		GTCadseFactory.findField(shell, CopyComposerCST.COPY_INTO_FOLDER_COMPOSER_at_TARGET_FOLDER_).typeText("WEB-INF/classes");
		GTCadseFactory.findField(shell, CadseGCST.COMPOSER_at_TYPES_).add("ref-classes");
		GTCadseFactory.findField(shell, CadseGCST.RUNTIME_ITEM_at_EXTENDS_CLASS_).check(true);
		shell.capture("image112");
		shell.close();
		
		
		// copy code into clipboard
		packageExplorerView.selectNode(file_postCompose).doubleClick();
		GTEditor editor = new GTEditor("sample-postCompose.java");
		editor.show();
		GTMenu.clickselectAll();
		GTMenu.clickCopy();
		editor.close();
		
		// copy composer implementation
		packageExplorerView.selectNode(webappManagerClass).doubleClick();
		editor = new GTEditor("WebAppManager.java");
		editor.find("postCompose(");
		editor.find("}");	
		GTMenu.clickPaste();
		editor.save();
		
		
		// Quick fixes		
		packageExplorerView.selectNode(webappManagerClass).doubleClick();
		editor = new GTEditor("WebAppManager.java");

		editor.find("IProject");
		editor.quickfix("Import 'IProject' (org.eclipse.core.resources)");
		editor.find("ArrayList");
		editor.quickfix("Import 'ArrayList' (java.util)");
		editor.find("ServletJO");
		editor.quickfix("Import 'ServletJO' (model.webapp.template)");
		editor.find("WebXMLGenerator");
		editor.quickfix("Import 'WebXMLGenerator' (model.webapp.template)");
		editor.find("MappingManager");
		editor.quickfix("Import 'MappingManager' (fede.workspace.tool.eclipse)");
		editor.find("EclipseTool");
		editor.quickfix("Import 'EclipseTool' (fede.workspace.tool.eclipse)");
		editor.find("CoreException");
		editor.quickfix("Import 'CoreException' (org.eclipse.core.runtime)");
		editor.find("WarFileUtil");
		editor.quickfix("Import 'WarFileUtil' (model.webapp.template)");
		
		editor.save();
	}

	@Test
	public void test_zp24_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel);
	}
}
