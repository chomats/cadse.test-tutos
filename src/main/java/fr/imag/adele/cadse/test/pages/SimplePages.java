package fr.imag.adele.cadse.test.pages;


import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.inGroup;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.hamcrest.Matcher;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;


import fr.imag.adele.graphictests.cadse.gtcadsetree.GTCadseTreeNode;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.graphictests.gtmenu.GTMenu;
import fr.imag.adele.graphictests.gttree.GTTreeNode;
import fr.imag.adele.graphictests.gtworkbench_part.GTEditor;
import fr.imag.adele.graphictests.gtworkbench_part.GTSection;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;


/**
 * Performs the official simple tutorial
 */
public class SimplePages extends TutoTestCase {

	/**
	 * Selects CADSEg in the launcher, and closes useless views. 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_selection() throws Exception {
		shell = new GTCadseShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE);
		shell.selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		shell.capture("image020");
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

		// CADSE WebAppModel 
		workspaceView.contextMenu(null, GTCadseRTConstants.CONTEXTMENU_NEW, GTCadseRTConstants.CONTEXTMENU_NEW_CADSE_DEFINITION).click();
		shell = new GTCadseShell(CadseGCST.CADSE_DEFINITION);
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("WebAppModel");
		GTCadseFactory.findField(shell, CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_).typeText("model.webapp");
		shell.capture("image030");
		shell.close();
		
		// Querying model
		GTTreeNode cadseWebAppModelNode = workspaceView.findTree().selectNode(webAppModel);
		cadseWebAppModelNode.expand() ;
		Item cadseWebApp = new GTCadseTreeNode(cadseWebAppModelNode).getItem();
		assertNotNull(cadseWebApp);
		assertEquals("WebAppModel", cadseWebApp.getName());
		assertEquals("model.webapp", cadseWebApp.getAttribute(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_));
		
		
		// Screenshots
		workspaceView.capture("image032");
		packageExplorerView.show();
		packageExplorerView.findTree().selectNode(project_package).expand();
		packageExplorerView.capture("image034");
		workspaceView.show();
		
		// Item Type WebApp
		workspaceView.contextMenu(data_model, GTCadseRTConstants.CONTEXTMENU_NEW, "Item type").click();
		shell = new GTCadseShell(CadseGCST.ITEM_TYPE);
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("WebApp");
		shell.capture("image038");
		shell.close();
		workspaceView.findTree().selectNode(it_webApp); /* Assert item has been displayed */
		workspaceView.capture("image040");

		// Item Type Library
		workspaceView.contextMenu(data_model, GTCadseRTConstants.CONTEXTMENU_NEW, "Item type").click();
		shell = new GTCadseShell(CadseGCST.ITEM_TYPE);
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("Library");
		shell.close();
		workspaceView.findTree().selectNode(it_library); /* Assert item has been displayed */

		// Item Type WebComponent
		workspaceView.contextMenu(data_model, GTCadseRTConstants.CONTEXTMENU_NEW, "Item type").click();
		shell = new GTCadseShell(CadseGCST.ITEM_TYPE);
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("WebComponent");
		shell.close();
		workspaceView.findTree().selectNode(it_webComponent); /* Assert item has been displayed */

		// Item Type JSP
		workspaceView.contextMenu(data_model, GTCadseRTConstants.CONTEXTMENU_NEW, "Item type").click();
		shell = new GTCadseShell(CadseGCST.ITEM_TYPE);
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("JSP");
		GTCadseFactory.findField(shell, CadseGCST.ITEM_TYPE_lt_SUPER_TYPE).browser("WebAppModel", CadseDefinitionManager.DATA_MODEL, "WebComponent");
		shell.close();
		workspaceView.findTree().selectNode(it_jsp); /* Assert item has been displayed */

		// Item Type Servlet
		workspaceView.findTree().selectNode(data_model).expand().contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("Item type").click();
		shell = new GTCadseShell(CadseGCST.ITEM_TYPE);
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("Servlet");
		GTCadseFactory.findField(shell, CadseGCST.ITEM_TYPE_lt_SUPER_TYPE).browser("WebAppModel", CadseDefinitionManager.DATA_MODEL, "WebComponent");
		shell.close();
		workspaceView.findTree().selectNode(it_servlet); /* Assert item has been displayed */

		// Attribute relativeURL
		workspaceView.contextMenu(it_webComponent, GTCadseRTConstants.CONTEXTMENU_NEW, "String").click();
		shell = new GTCadseShell(CadseGCST.STRING);
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("relativeURL");
		shell.capture("image046");
		shell.close();
		workspaceView.findTree().selectNode(attr_relativeUrl);  /* Assert item has been displayed */
		workspaceView.capture("image048");

		// Attribute className
		workspaceView.contextMenu(it_servlet, GTCadseRTConstants.CONTEXTMENU_NEW, "String").click();
		shell = new GTCadseShell(CadseGCST.STRING);
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("className");
		shell.close();
		workspaceView.findTree().selectNode(attr_className);  /* Assert item has been displayed */

		// Attribute packageName
		workspaceView.contextMenu(it_servlet, GTCadseRTConstants.CONTEXTMENU_NEW, "String").click();
		shell = new GTCadseShell(CadseGCST.STRING);
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("packageName");
		shell.close();
		workspaceView.findTree().selectNode(attr_packageName);  /* Assert item has been displayed */

		// hasComp link
		workspaceView.contextMenu(it_webApp, GTCadseRTConstants.CONTEXTMENU_NEW, "Link").click();
		shell = new GTCadseShell(CadseGCST.LINK_TYPE);
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("hasComp");
		GTCadseFactory.findField(shell, CadseGCST.LINK_TYPE_lt_DESTINATION).browser("WebAppModel", CadseDefinitionManager.DATA_MODEL, "WebComponent");
		GTCadseFactory.findField(shell, CadseGCST.LINK_TYPE_at_PART_).check(true);
		GTCadseFactory.findField(shell, CadseGCST.LINK_TYPE_at_REQUIRE_).check(true);
		shell.capture("image052");
		shell.close();
		workspaceView.findTree().selectNode(attr_hasComp);  /* Assert item has been displayed */
		workspaceView.capture("image056");

		// Filtering options		
		workspaceView.menu("Filters...").click();
		shell = new GTCadseShell("Element Filters");
		shell.findTable().checkTableItem("not aggregation link", false);
		shell.capture("image062");
		shell.close();

		workspaceView.findTree().collapse();
		// Waits until filtering option refreshes view
		workspaceView.findTree().selectNode(it_webApp.concat("hasComp","WebComponent"));
		workspaceView.capture("image064");

		// uses link
		workspaceView.findTree().selectNode(it_webComponent).expand().contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("Link").click();
		shell = new GTCadseShell(CadseGCST.LINK_TYPE);
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("uses");
		GTCadseFactory.findField(shell, CadseGCST.LINK_TYPE_lt_DESTINATION).browser("WebAppModel", CadseDefinitionManager.DATA_MODEL, "Library");
		GTCadseFactory.findField(shell, CadseGCST.LINK_TYPE_at_AGGREGATION_).check(false);
		GTCadseFactory.findField(shell, CadseGCST.ATTRIBUTE_at_MUST_BE_INITIALIZED_).check(false);
		shell.close();
		workspaceView.findTree().selectNode(link_uses); /* Assert item has been displayed */

		// Root element attribute + is abstract
		workspaceView.findTree().collapse();
		workspaceView.findTree().selectNode(it_jsp);
		workspaceView.capture("image068");
		propertiesView.showTab(ITEM_TYPE_TAB_NAME);
		GTCadseFactory.findField(propertiesView, CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_).check(false);
		propertiesView.capture("image070");

		workspaceView.findTree().selectNode(it_servlet);
		propertiesView.showTab(ITEM_TYPE_TAB_NAME);
		GTCadseFactory.findField(propertiesView, CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_).check(false);
		
		workspaceView.findTree().selectNode(it_webComponent);
		propertiesView.showTab(ITEM_TYPE_TAB_NAME);
		GTCadseFactory.findField(propertiesView, CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_).check(false);
		GTCadseFactory.findField(propertiesView, CadseGCST.ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_).check(true);
	}
}
