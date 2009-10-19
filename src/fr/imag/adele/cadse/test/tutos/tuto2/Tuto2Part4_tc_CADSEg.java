package fr.imag.adele.cadse.test.tutos.tuto2;


import model.workspace.copycomposer.CopyComposerCST;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.GTCadseRTConstants;
import fr.imag.adele.cadse.test.gtworkbench_part.GTShell;
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
		workspaceView.findTree().collapse();
		workspaceView.findTree().selectNode(attr_hasComp);
		workspaceView.capture("image094");

		propertiesView.showTab("Link");
		propertiesView.findField(CadseGCST.LINK_at_COMPOSITION_).check(true);
		propertiesView.capture("image096");


		// Executed CADSEs
		workspaceView.contextMenu(webAppModel, "Executed CADSEs").click();
		shell = new GTShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE);
		shell.selectCadses("CopyComposer");
		shell.capture("image097");
		shell.close();


		// JavaRefExporter
		workspaceView.contextMenu(mapping_servlet, GTCadseRTConstants.CONTEXTMENU_NEW, "JavaRefExporter").click();
		shell = new GTShell(CopyComposerCST.JAVA_REF_EXPORTER);
		shell.capture("image102");
		shell.close();

		workspaceView.findTree().collapse();
		workspaceView.findTree().selectNode(refExporter);
		workspaceView.capture("image104");
	}

	/**
	 * 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_copy_composer() throws Exception {

		workspaceView.findTree().collapse();
		workspaceView.findTree().selectNode(composite);
		workspaceView.capture("image106");
		workspaceView.contextMenu(composite, GTCadseRTConstants.CONTEXTMENU_NEW, "CopyIntoFolderComposer").click();
		shell = new GTShell(CopyComposerCST.COPY_INTO_FOLDER_COMPOSER);
		shell.findField(CadseGCST.ITEM_at_NAME_).typeText("GenWarComposer");
		shell.findField(CopyComposerCST.COPY_INTO_FOLDER_COMPOSER_at_TARGET_FOLDER_).typeText("WEB-INF/classes");
		shell.findField(CadseGCST.COMPOSER_at_EXTENDS_CLASS_).check(true);
		shell.findField(CadseGCST.COMPOSER_at_TYPES_).add("ref-classes"); 
		shell.capture("image112");
		shell.close();
	}

	@Test
	public void test_zp24_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel);
	}
}
