package fr.imag.adele.cadse.test.tutos.tuto1;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;

import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
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
		workspaceView.selectNode(mapping_webApp);
		workspaceView.capture("image132");

		workspaceView.contextMenuNew(mapping_webApp, CadseGCST.JAVA_PROJECT_CONTENT_MODEL).click();
		shell = new GTCadseShell(CadseGCST.JAVA_PROJECT_CONTENT_MODEL);
		GTCadseFactory.findCadseField(shell, CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_).check(true); 
		shell.capture("image134");
		shell.close();
		workspaceView.selectNode(content_webApp);
		
		workspaceView.contextMenuNew(mapping_library, CadseGCST.JAVA_PROJECT_CONTENT_MODEL).click();
		shell = new GTCadseShell(CadseGCST.JAVA_PROJECT_CONTENT_MODEL);
		GTCadseFactory.findCadseField(shell, CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_).check(true);
		shell.close();
		workspaceView.selectNode(content_library);
	
		workspaceView.contextMenuNew(mapping_servlet, CadseGCST.JAVA_PROJECT_CONTENT_MODEL).click();
		shell = new GTCadseShell(CadseGCST.JAVA_PROJECT_CONTENT_MODEL);
		GTCadseFactory.findCadseField(shell, CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_).check(true);
		shell.close();
		workspaceView.selectNode(content_servlet);

		workspaceView.contextMenuNew(mapping_jsp, CadseGCST.FILE_CONTENT_MODEL).click();
		shell = new GTCadseShell(CadseGCST.FILE_CONTENT_MODEL);
		// FIXME to be updated with new mapping API
		// GTCadseFactory.findCadseField(shell, CadseGCST.FILE_CONTENT_MODEL_at_FILE_NAME_).typeText("${#short-name}.jsp");
		GTCadseFactory.findCadseField(shell, CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_).typeText("/${#short-name}.jsp");
		shell.capture("image135");
		shell.close();
		workspaceView.selectNode(content_jsp);
	}
	
	@Test
	public void test_zp14_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel);
	}
}