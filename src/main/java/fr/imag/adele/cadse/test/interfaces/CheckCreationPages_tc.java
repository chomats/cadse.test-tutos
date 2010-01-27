package fr.imag.adele.cadse.test.interfaces;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;


/**
 * Performs the official simple tutorial
 */
public class CheckCreationPages_tc extends GTCadseTestCase {

	protected final String cadse_name = "my_CADSE";
	protected final String item_type_name = "my_item_type";
	
	protected GTTreePath cadse_model = new GTTreePath(cadse_name);
	
	protected GTTreePath build_model = cadse_model.concat(CadseDefinitionManager.BUILD_MODEL);
	protected GTTreePath data_model  = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);
	
	protected GTTreePath it_mit  = data_model.concat(item_type_name);
	
	
	/**
	 * Selects CADSEg in the launcher, and closes useless views. 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_selection() throws Exception {

		shell = new GTCadseShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE);
		shell.selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		shell.close();
		welcomeView.close();
	}

	/**
	 * Sets up the data-model by creating items. 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_data_model() throws Exception {

		workspaceView.show();

		// CADSE 
		workspaceView.contextMenu(null, GTCadseRTConstants.CONTEXTMENU_NEW, GTCadseRTConstants.CONTEXTMENU_NEW_CADSE_DEFINITION).click();
		shell = new GTCadseShell(CadseGCST.CADSE_DEFINITION);
		GTCadseFactory.findCadseWorkbenchPart(shell).findField(CadseGCST.ITEM_at_NAME_).typeText(cadse_name);
		GTCadseFactory.findCadseWorkbenchPart(shell).findField(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_).typeText("model.myCadse");
		shell.close();
				
		
		// Item Type
		workspaceView.contextMenu(data_model, GTCadseRTConstants.CONTEXTMENU_NEW, "Item type").click();
		shell = new GTCadseShell(CadseGCST.ITEM_TYPE);		
		GTCadseFactory.findCadseWorkbenchPart(shell).findField(CadseGCST.ITEM_at_NAME_).typeText(item_type_name);
		shell.close();
		workspaceView.findTree().selectNode(it_mit); /* Assert item has been displayed */
	}
}
