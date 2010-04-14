package fr.imag.adele.cadse.test.tutos.tuto2;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.test.GTPreferences;

/**
 * Performs the official simple tutorial
 */
public class Tuto2Part1_tc_CADSEg extends TutoTestCase {

	GTCadseShell shell;

	/**
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_view() throws Exception {

		/* Assert tree has been displayed */
		workspaceView.selectNode(view_model, GTPreferences.TIMEOUT);

		workspaceView.findTree().collapse();
		workspaceView.selectNode(view_model);
		workspaceView.capture("image014");

		/* Creates a new view */
		workspaceView.contextMenuNew(view_model, CadseGCST.VIEW).click();
		shell = new GTCadseShell(CadseGCST.VIEW);
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("WebAppList");
		shell.capture("image016");
		shell.close();

		/* View configuration */
		workspaceView.selectNode(vm_webAppList);
		workspaceView.capture("image018");

		propertiesView.showTab("View");
		GTCadseFactory.findCadseField(propertiesView, "view-item-types").check(true, "WebAppModel",
				CadseDefinitionManager.DATA_MODEL, "WebApp", "hasComp");
		GTCadseFactory.findCadseField(propertiesView, "view-item-types").check(true, "WebAppModel",
				CadseDefinitionManager.DATA_MODEL, "WebComponent", "uses");
		GTCadseFactory.findCadseField(propertiesView, "view-item-types").check(false, "WebAppModel",
				CadseDefinitionManager.DATA_MODEL, "WebComponent", "uses");
		GTCadseFactory.findCadseField(propertiesView, "view-item-types").scroll();
		propertiesView.capture("image020");

		/* Can create item and Can create link check boxes */
		workspaceView.findTree().collapse();
		workspaceView.selectNode(vi_link_hasComp);
		workspaceView.capture("image024");
		propertiesView.showTab("View Link");

		GTCadseFactory.findCadseField(propertiesView, "can-create-item").check(false);
		GTCadseFactory.findCadseField(propertiesView, "can-create-link").check(false);
		propertiesView.capture("image026");
	}

	@Test
	public void test_zp21_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel, GTPreferences.TIMEOUT);
	}
}