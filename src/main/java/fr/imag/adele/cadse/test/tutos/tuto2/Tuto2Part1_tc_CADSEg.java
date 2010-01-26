package fr.imag.adele.cadse.test.tutos.tuto2;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.graphictests.cadse.gtcadsetree.GTCadseTreeNode;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gttree.GTTreeNode;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;

/**
 * Performs the official simple tutorial
 */
public class Tuto2Part1_tc_CADSEg extends TutoTestCase {
	
	/**
	 * 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_view() throws Exception {

		/* TO BE UPDATED WITH NEW RUNTIME
		workspaceView.findTree().collapse();

		/* Creates a new view 
		GTTreeNode select_model_nodel = workspaceView.findTree().selectNode(view_model);
		workspaceView.capture("image014");
		workspaceView.findTree().selectNode(view_model).contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu(GTCadseRTConstants.CONTEXTMENU_VIEW).click();
		shell = new GTCadseShell(CadseGCST.VIEW);
		GTCadseFactory.findCadseWorkbenchPart(shell).findField(CadseGCST.ITEM_at_NAME_).typeText("WebAppList");
		shell.capture("image016");
		shell.close();


		/* Configuration for this new view 
		workspaceView.findTree().selectNode(vm_webAppList);
		workspaceView.capture("image018");

		propertiesView.showTab("View");
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField("sel").check(true, "WebAppModel", CadseDefinitionManager.DATA_MODEL, "WebApp", "hasComp");
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField("sel").check(true, "WebAppModel", CadseDefinitionManager.DATA_MODEL, "WebComponent", "uses");
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField("sel").check(false, "WebAppModel", CadseDefinitionManager.DATA_MODEL, "WebComponent", "uses");
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField("sel").scroll();
		propertiesView.capture("image020");

		/* Can create item and Can create link check boxes 
		workspaceView.findTree().collapse();
		workspaceView.findTree().selectNode(vi_link_hasComp);
		workspaceView.capture("image024");
		propertiesView.showTab("ViewLinkType");
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField("can-create-item").check(false);
		GTCadseFactory.findCadseWorkbenchPart(propertiesView).findField("can-create-link").check(false);
		propertiesView.capture("image026");
		
		//TODO next line to prevent CADSE from throwing an exception. Should be removed soon (sept. 30, 2009)  
		bot.sleep(2000);

		// Assert generation is correct
		Item cadseDef = new GTCadseTreeNode(select_model_nodel).getItem().getPartParent(CadseGCST.CADSE_DEFINITION);
		IJavaProject jp = cadseDef.getMainMappingContent(IJavaProject.class);
		IType findType = jp.findType("model.webapp.views.webAppList.WebAppListView");
		assertNotNull(findType);
		assertTrue(findType.exists());*/
	}
	
	@Test
	public void test_zp21_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel);
	}
}