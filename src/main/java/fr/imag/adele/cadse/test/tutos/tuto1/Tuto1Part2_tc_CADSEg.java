package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import java.io.File;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.pages.CADSEG_UICST;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.KeyValue;
import fr.imag.adele.graphictests.test.GTEclipseConstants;

public class Tuto1Part2_tc_CADSEg extends TutoTestCase {

	GTCadseShell shell;

	/**
	 * Gives a naming constraint for new WebApp.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_mapping_name_constraint() throws Exception {
		workspaceView.findTree().collapse();
		workspaceView.selectNode(it_webApp);
		workspaceView.capture("image086");
		propertiesView.showTab(CADSEG_UICST.INSTANCE_NAME_CONTROL_PAGE_TITLE);
		GTCadseFactory.findCadseField(propertiesView, CadseGCST.MANAGER_at_VALID_PATTERN_ID_).typeText(".*App");
		GTCadseFactory.findCadseField(propertiesView, CadseGCST.MANAGER_at_MESSAGE_ERROR_ID_).typeText(
				"The Web application name must end with App.");
		propertiesView.capture("image092");
	}

	/**
	 * Adds icons to items.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_icons() throws Exception {

		packageExplorerView.selectNode(project_package, true);
		packageExplorerView.capture("image094");
		packageExplorerView.contextMenu(project_package, GTCadseRTConstants.CONTEXTMENU_IMPORT).click();

		shell = new GTCadseShell("Import");
		shell.selectNode(importArchiveFile);
		shell.capture("image096");
		shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();

		shell.setComboBoxWithLabelText("From archive file:", System.getProperty("test.resourcesPath") + File.separator
				+ "resources.zip");
		shell.selectNode(archivePath);
		shell.findButton("Select All").click();
		shell.capture("image098");
		shell.findCheckBox("Overwrite existing resources without warning").select();
		shell.close();

		packageExplorerView.selectNode(project_resources, true);
		packageExplorerView.capture("image100");

		workspaceView.show();

		workspaceView.selectNode(it_webApp);
		propertiesView.showTab(GTCadseRTConstants.ITEM_TYPE_TAB_NAME);
		final String project = "Model.Workspace.WebAppModel";
		GTCadseFactory.findCadseField(propertiesView, CadseGCST.ITEM_TYPE_at_ICON_).browser(project, "resources",
				"WarFile.gif");

		workspaceView.selectNode(it_jsp);
		propertiesView.showTab(GTCadseRTConstants.ITEM_TYPE_TAB_NAME);
		GTCadseFactory.findCadseField(propertiesView, CadseGCST.ITEM_TYPE_at_ICON_).browser(project, "resources",
				"JSP.gif");

		workspaceView.selectNode(it_library);
		propertiesView.showTab(GTCadseRTConstants.ITEM_TYPE_TAB_NAME);
		GTCadseFactory.findCadseField(propertiesView, CadseGCST.ITEM_TYPE_at_ICON_).browser(project, "resources",
				"Library.gif");

		workspaceView.selectNode(it_servlet);
		propertiesView.showTab(GTCadseRTConstants.ITEM_TYPE_TAB_NAME);
		GTCadseFactory.findCadseField(propertiesView, CadseGCST.ITEM_TYPE_at_ICON_).browser(project, "resources",
				"Servlet.gif");
	}

	/**
	 * Creation pages configuration.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_pages_configuration() throws Exception {

		workspaceView.findTree().collapse();
		workspaceView.selectNode(attr_className);
		workspaceView.capture("image106");
		propertiesView.showTab("String");
		GTCadseFactory.findCadseField(propertiesView, CadseGCST.ITEM_at_NAME).scroll();
		KeyValue.sicpKv.setValue(propertiesView);
		KeyValue.simpKv.setValue(propertiesView);
		propertiesView.capture("image108");

		workspaceView.selectNode(attr_packageName);
		propertiesView.showTab("String");
		KeyValue.sicpKv.setValue(propertiesView);
		KeyValue.simpKv.setValue(propertiesView);

		workspaceView.selectNode(link_hasComp);
		propertiesView.showTab("LinkType");
		KeyValue.notSicpKv.setValue(propertiesView);
		KeyValue.simpKv.setValue(propertiesView);

		workspaceView.selectNode(attr_relativeUrl);
		propertiesView.showTab("String");
		KeyValue.sicpKv.setValue(propertiesView);
		KeyValue.simpKv.setValue(propertiesView);

		workspaceView.selectNode(link_uses);
		propertiesView.showTab("LinkType");
		KeyValue.notSicpKv.setValue(propertiesView);
		KeyValue.simpKv.setValue(propertiesView);
	}

	@Test
	public void test_zp12_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel);
	}
}
