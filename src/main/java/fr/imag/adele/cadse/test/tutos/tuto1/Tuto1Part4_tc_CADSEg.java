package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;

/**
 * Performs the official simple tutorial
 */
public class Tuto1Part4_tc_CADSEg extends TutoTestCase {

	GTCadseShell shell;

	/**
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_item_content_dependency() throws Exception {

		workspaceView.findTree().collapse();

		workspaceView.selectNode(link_uses);
		workspaceView.capture("image144");

		propertiesView.showTab("LinkType");
		GTCadseFactory.findCadseField(propertiesView, CadseGCST.ITEM_at_QUALIFIED_NAME_).scroll();
		GTCadseFactory.findCadseField(propertiesView, CadseGCST.LINK_TYPE_at_REQUIRE_).check(true);
		propertiesView.capture("image146");
	}

	@Test
	public void test_zp15_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel);
	}
}
