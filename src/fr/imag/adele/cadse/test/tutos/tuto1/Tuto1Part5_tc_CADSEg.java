package fr.imag.adele.cadse.test.tutos.tuto1;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;

/**
 * Performs the official simple tutorial
 */
public class Tuto1Part5_tc_CADSEg extends TutoTestCase {

	/**
	 * 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_item_content_dependency() throws Exception {

		workspaceView.findTree().collapse();

		workspaceView.findTree().selectNode(link_uses);
		workspaceView.capture("image144");

		propertiesView.showTab("Link");
		propertiesView.findField(CadseGCST.LINK_at_REQUIRE_).scroll();
		propertiesView.findField(CadseGCST.LINK_at_REQUIRE_).check(true);
		propertiesView.capture("image146");
	}
	
	@Test
	public void test_zp15_check_compilation() throws Exception {
		checkCompilationWebApp();
	}
}
