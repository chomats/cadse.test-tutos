package fr.imag.adele.cadse.test.tutos.tuto1;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;

import fr.imag.adele.cadse.test.GTCadseRTConstants;
import fr.imag.adele.cadse.test.GTTestParameters;
import fr.imag.adele.cadse.test.gtworkbench_part.GTShell;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;


/**
 * Performs the official simple tutorial
 */
public class Tuto1Part4_tc_CADSEg extends TutoTestCase {

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
		SWTBotPreferences.TIMEOUT = 30000;
	}

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

		workspaceView.findTree().selectNode(mapping_webApp).contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu(GTCadseRTConstants.CONTEXTMENU_JAVA_PROJECT_CONTENT_MODEL).click();
		shell = new GTShell(CadseGCST.JAVA_PROJECT_CONTENT_MODEL);
		shell.findField(CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_).check(true);
		shell.capture("image134");
		shell.close();
		workspaceView.findTree().selectNode(content_webApp);
		
		workspaceView.contextMenu(mapping_library, GTCadseRTConstants.CONTEXTMENU_NEW, GTCadseRTConstants.CONTEXTMENU_JAVA_PROJECT_CONTENT_MODEL).click();
		shell = new GTShell(CadseGCST.JAVA_PROJECT_CONTENT_MODEL);
		shell.findField(CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_).check(true);
		shell.close();
		workspaceView.findTree().selectNode(content_library);
		
		workspaceView.findTree().selectNode(mapping_servlet).contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu(GTCadseRTConstants.CONTEXTMENU_JAVA_PROJECT_CONTENT_MODEL).click();
		shell = new GTShell(CadseGCST.JAVA_PROJECT_CONTENT_MODEL);
		shell.findField(CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_).check(true);
		shell.close();
		workspaceView.findTree().selectNode(content_servlet);

		workspaceView.findTree().selectNode(mapping_jsp).contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu(GTCadseRTConstants.CONTEXTMENU_FILE_CONTENT_MODEL).click();
		shell = new GTShell(CadseGCST.FILE_CONTENT_MODEL);
		// TODO see Bugzilla #280562
		shell.findField(CadseGCST.FILE_CONTENT_MODEL_at_FILE_NAME_).typeText(/*"myFile.jsp"); //*/"${#short-name}.jsp");
		shell.findField(CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_).typeText("/");
		shell.capture("image135");
		shell.close();
		
		// set attribute via api not swtbot (see Bugzilla #280562)
		Item conten_jsp_item = workspaceView.findTree().selectNode(content_jsp).getItem();
		conten_jsp_item.setAttribute(CadseGCST.FILE_CONTENT_MODEL_at_FILE_NAME_, "${#short-name}.jsp");
//		workspaceView.findTree().selectNode(content_jsp).getItem().setAttribute(CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_, "/");
		
		workspaceView.findTree().selectNode(content_jsp);
	}
	
	@Test
	public void test_zp14_check_compilation() throws Exception {
		checkCompilationWebApp();
	}
}