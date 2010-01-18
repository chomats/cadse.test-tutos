package fr.imag.adele.cadse.test.tutos.tuto1;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;

import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gttree.GTTreeNode;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadsetree.GTCadseTreeNode;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;


/**
 * Performs the official simple tutorial
 */
public class Tuto1Part4_tc_CADSEg extends TutoTestCase {

	/**
	 * 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_item_content() throws Exception {
		workspaceView.findTree().collapse();
		workspaceView.findTree().selectNode(mapping_webApp);
		workspaceView.capture("image132");

		// FIXME the has source folder check box disappeared!
		/*workspaceView.findTree().selectNode(mapping_webApp).contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu(GTCadseRTConstants.CONTEXTMENU_JAVA_PROJECT_CONTENT_MODEL).click();
		shell = new GTCadseShell(CadseGCST.JAVA_PROJECT_CONTENT_MODEL);
		GTCadseFactory.findField(shell, CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_).check(true); 
		shell.capture("image134");
		shell.close();
		workspaceView.findTree().selectNode(content_webApp);
		
		workspaceView.contextMenu(mapping_library, GTCadseRTConstants.CONTEXTMENU_NEW, GTCadseRTConstants.CONTEXTMENU_JAVA_PROJECT_CONTENT_MODEL).click();
		shell = new GTCadseShell(CadseGCST.JAVA_PROJECT_CONTENT_MODEL);
		GTCadseFactory.findField(shell, CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_).check(true);
		shell.close();
		workspaceView.findTree().selectNode(content_library);
		
		workspaceView.findTree().selectNode(mapping_servlet).contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu(GTCadseRTConstants.CONTEXTMENU_JAVA_PROJECT_CONTENT_MODEL).click();
		shell = new GTCadseShell(CadseGCST.JAVA_PROJECT_CONTENT_MODEL);
		GTCadseFactory.findField(shell, CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_).check(true);
		shell.close();
		workspaceView.findTree().selectNode(content_servlet);*/

		// FIXME File content model : same problem
		/*
		workspaceView.findTree().selectNode(mapping_jsp).contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu(GTCadseRTConstants.CONTEXTMENU_FILE_CONTENT_MODEL).click();
		shell = new GTCadseShell(CadseGCST.FILE_CONTENT_MODEL);
		GTCadseFactory.findField(shell, CadseGCST.FILE_CONTENT_MODEL_at_FILE_NAME_).typeText("${#short-name}.jsp");
		GTCadseFactory.findField(shell, CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_).typeText("/");
		shell.capture("image135");
		shell.close();
		GTTreeNode node = workspaceView.findTree().selectNode(content_jsp);
		Item conten_jsp_item = new GTCadseTreeNode(node).getItem();
		conten_jsp_item.setAttribute(CadseGCST.FILE_CONTENT_MODEL_at_FILE_NAME_, "${#short-name}.jsp");
//		workspaceView.findTree().selectNode(content_jsp).getItem().setAttribute(CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_, "/");
		
		workspaceView.findTree().selectNode(content_jsp);*/
	}
	
	@Test
	public void test_zp14_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel);
	}
}