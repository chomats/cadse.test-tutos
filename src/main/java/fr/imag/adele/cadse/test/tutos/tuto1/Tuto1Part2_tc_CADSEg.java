package fr.imag.adele.cadse.test.tutos.tuto1;

import java.io.File;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.pages.CADSEG_UICST;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;


public class Tuto1Part2_tc_CADSEg extends TutoTestCase {
	
	/**
	 * Gives a naming constraint for new WebApp.  
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_mapping_name_constraint() throws Exception {
		workspaceView.findTree().collapse();
		workspaceView.findTree().selectNode(it_webApp);
		workspaceView.capture("image086");
		propertiesView.showTab(CADSEG_UICST.INSTANCE_NAME_CONTROL_PAGE_TITLE);
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField(CadseGCST.MANAGER_at_VALID_PATTERN_ID_).typeText(".*App");
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField(CadseGCST.MANAGER_at_MESSAGE_ERROR_ID_).typeText("The Web application name must end with App.");
		propertiesView.capture("image092");		
	}

	/**
	 * Adds icons to items. 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_icons() throws Exception {

		packageExplorerView.findTree().selectNode(project_package).expand();
		packageExplorerView.capture("image094");
		packageExplorerView.findTree().selectNode(project_package).expand().contextMenu(GTCadseRTConstants.CONTEXTMENU_IMPORT).click();

		shell = new GTCadseShell("Import");
		shell.findTree().selectNode(importArchiveFile);
		shell.capture("image096");
		shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		
		shell.setComboBoxWithLabelText("From archive file:", System.getProperty("test.resourcesPath") + File.separator + "resources.zip");
		shell.findTree().selectNode(archivePath);
		shell.findButton("Select All").click();
		shell.capture("image098");
		shell.findCheckBox("Overwrite existing resources without warning").select();
		shell.close();

		packageExplorerView.findTree().selectNode(project_resources).expand();
		packageExplorerView.capture("image100");

		workspaceView.show();		
		 
		workspaceView.findTree().selectNode(it_webApp);
		propertiesView.showTab(GTCadseRTConstants.ITEM_TYPE_TAB_NAME);
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField(CadseGCST.ITEM_TYPE_at_ICON_).browser("resources", "WarFile.gif");

		workspaceView.findTree().selectNode(it_jsp);
		propertiesView.showTab(GTCadseRTConstants.ITEM_TYPE_TAB_NAME);
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField(CadseGCST.ITEM_TYPE_at_ICON_).browser("resources", "JSP.gif");

		workspaceView.findTree().selectNode(it_library);
		propertiesView.showTab(GTCadseRTConstants.ITEM_TYPE_TAB_NAME);
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField(CadseGCST.ITEM_TYPE_at_ICON_).browser("resources", "Library.gif");

		workspaceView.findTree().selectNode(it_servlet);
		propertiesView.showTab(GTCadseRTConstants.ITEM_TYPE_TAB_NAME);
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField(CadseGCST.ITEM_TYPE_at_ICON_).browser("resources", "Servlet.gif");
	}	

	/**
	 * Creation pages configuration.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_creation_pages() throws Exception {

		// FIXME creation pages
		/* Creation pages not yet implemented
		workspaceView.findTree().collapse();

		/* creation-page-WebComponent
		workspaceView.findTree().selectNode(cp_webComponent);
		workspaceView.capture("image106");

		propertiesView.showTab("Page");
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField(CadseGCST.PAGE_at_TITLE_).typeText("WebComponent URL Definition");
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField(CadseGCST.PAGE_at_DESCRIPTION_).typeText("Relative URL definition");
		propertiesView.capture("image108");
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField(CadseGCST.PAGE_lt_ATTRIBUTES).scroll();
		propertiesView.capture("image110");


		/* creation-page-Servlet 
		workspaceView.findTree().selectNode(cp_servlet);
		workspaceView.capture("image112");

		propertiesView.showTab("Page");
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField(CadseGCST.PAGE_at_TITLE_).typeText("Servlet Definition");
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField(CadseGCST.PAGE_at_DESCRIPTION_).typeText("Servlet Java class definition");
		propertiesView.capture("image114");
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField(CadseGCST.PAGE_lt_ATTRIBUTES).check(true, "className");
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField(CadseGCST.PAGE_lt_ATTRIBUTES).check(true, "packageName");
		*/
	}
	
	@Test
	public void test_zp12_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel);
	}
}

