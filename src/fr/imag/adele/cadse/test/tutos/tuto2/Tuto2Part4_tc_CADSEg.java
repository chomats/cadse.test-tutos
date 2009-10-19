package fr.imag.adele.cadse.test.tutos.tuto2;


import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.gtmenu.GTMenu;
import fr.imag.adele.cadse.test.gtworkbench_part.GTEditor;
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
	public void test_automatic_short_name_generation() throws Exception {

		workspaceView.findTree().collapse();
		workspaceView.findTree().selectNode(cd_servlet);
		workspaceView.capture("image066");

		// Copying content into clipboard
		packageExplorerView.findTree().selectNode(file_sample4).doubleClick();
		GTEditor editor = new GTEditor("sample4.java");
		editor.show();
		GTMenu.clickselectAll();
		GTMenu.clickCopy();
		editor.close();
		
		workspaceView.findTree().selectNode(cd_servlet);
		
		propertiesView.showTab("Creation dialog");
		propertiesView.findField(CadseGCST.CREATION_DIALOG_at_AUTOMATIC_SHORT_NAME_).check(true);
		propertiesView.findField(CadseGCST.CREATION_DIALOG_at_EXTENDS_DIALOG_CONTROLLER_).check(true);
		//propertiesView.findField(CadseGCST.CREATION_DIALOG_at_GENERATE_AUTOMATIC_SHORT_NAME_).typeText("${packageName}{.}${className}");
		propertiesView.findField(CadseGCST.CREATION_DIALOG_at_GENERATE_AUTOMATIC_SHORT_NAME_).typeText("FIXME");
		GTMenu.clickselectAll();
		GTMenu.clickPaste();
		
		
		propertiesView.capture("image068");		
	}
	
	@Test
	public void test_zp24_check_compilation() throws Exception {
		checkCompilationWebApp();
	}
}