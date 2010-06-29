package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.packageExplorerView;

import java.io.File;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.pages.CADSEG_UICST;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Tuto1Part2_tc_CADSEg extends TutoTestCase {

	GTCadseShell shell;

	/**
	 * Gives a naming constraint for new WebApp.
	 * 
	 * @throws Exception
	 *         the exception
	 */
	@Test
	public void test_mapping_name_constraint() throws Exception {

		/* Assert tree has been displayed */
		workspaceView.selectNode(webAppModel, GTPreferences.TIMEOUT);
		workspaceView.findTree().collapse();

		workspaceView.selectNode(itWebApp);
		workspaceView.capture("image086");
		propertiesView.showTab(CADSEG_UICST.INSTANCE_NAME_CONTROL_PAGE_TITLE);
		propertiesView.findCadseField(CadseGCST.MANAGER_at_VALID_PATTERN_ID_).typeText(".*App");
		propertiesView.findCadseField(CadseGCST.MANAGER_at_MESSAGE_ERROR_ID_).typeText(
				"The Web application name must end with App.");
		propertiesView.capture("image092");
	}

	/**
	 * Adds icons to items.
	 * 
	 * @throws Exception
	 *         the exception
	 */
	@Test
	public void test_icons() throws Exception {

		packageExplorerView.selectNode(projectPackage, true);
		packageExplorerView.capture("image094");
		packageExplorerView.contextMenu(projectPackage, GTCadseRTConstants.CONTEXTMENU_IMPORT).click();

		shell = new GTCadseShell("Import");
		shell.selectNode(importArchiveFile, GTPreferences.TIMEOUT);
		shell.capture("image096");
		shell.next();

		shell.setComboBoxWithLabelText("From archive file:", System.getProperty("test.resourcesPath") + File.separator
				+ "resources.zip");
		shell.selectNode(archivePath);
		shell.findButton("Select All").click();
		shell.capture("image098");
		shell.findCheckBox("Overwrite existing resources without warning").select();
		shell.close();

		packageExplorerView.selectNode(projectResources, true);
		packageExplorerView.capture("image100");

		workspaceView.show();

		workspaceView.selectNode(itWebApp);
		propertiesView.showTab(GTCadseRTConstants.ITEM_TYPE_TAB_NAME);
		final String project = "Model.Workspace.WebAppModel";
		propertiesView.findCadseField(CadseGCST.ITEM_TYPE_at_ICON_).browser(project, "resources", "WarFile.gif");

		workspaceView.selectNode(itJsp);
		propertiesView.showTab(GTCadseRTConstants.ITEM_TYPE_TAB_NAME);
		propertiesView.findCadseField(CadseGCST.ITEM_TYPE_at_ICON_).browser(project, "resources", "JSP.gif");

		workspaceView.selectNode(itLibrary);
		propertiesView.showTab(GTCadseRTConstants.ITEM_TYPE_TAB_NAME);
		propertiesView.findCadseField(CadseGCST.ITEM_TYPE_at_ICON_).browser(project, "resources", "Library.gif");

		workspaceView.selectNode(itServlet);
		propertiesView.showTab(GTCadseRTConstants.ITEM_TYPE_TAB_NAME);
		propertiesView.findCadseField(CadseGCST.ITEM_TYPE_at_ICON_).browser(project, "resources", "Servlet.gif");
	}

	/**
	 * Creation pages configuration.
	 * 
	 * @throws Exception
	 *         the exception
	 */
	@Test
	public void test_pages_configuration() throws Exception {

		workspaceView.findTree().collapse();
		workspaceView.selectNode(attrClassName);
		workspaceView.capture("image106");
		propertiesView.showTab("String");
		propertiesView.findCadseField(CadseGCST.ITEM_at_NAME).scroll();
		propertiesView.setValue(KeyValue.sicpKv());
		propertiesView.setValue(KeyValue.simpKv());
		propertiesView.capture("image108");

		workspaceView.selectNode(attrPackageName);
		propertiesView.showTab("String");
		propertiesView.setValue(KeyValue.sicpKv());
		propertiesView.setValue(KeyValue.simpKv());

		workspaceView.selectNode(linkHasComp);
		propertiesView.showTab("LinkType");
		propertiesView.setValue(KeyValue.notSicpKv());
		propertiesView.setValue(KeyValue.simpKv());

		workspaceView.selectNode(attrRelativeUrl);
		propertiesView.showTab("String");
		propertiesView.setValue(KeyValue.sicpKv());
		propertiesView.setValue(KeyValue.simpKv());

		workspaceView.selectNode(linkUses);
		propertiesView.showTab("LinkType");
		propertiesView.setValue(KeyValue.notSicpKv());
		propertiesView.setValue(KeyValue.simpKv());
	}

	@Test
	public void test_zp12_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel);
	}
}
