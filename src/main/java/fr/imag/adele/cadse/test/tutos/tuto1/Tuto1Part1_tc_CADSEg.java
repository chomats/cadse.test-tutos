package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseField;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createLinkType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createString;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.inGroup;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.hamcrest.Matcher;
import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.KeyValue;
import fr.imag.adele.graphictests.gtmenu.GTMenu;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.graphictests.test.GTPreferences;

/**
 * Performs the official simple tutorial
 */
public class Tuto1Part1_tc_CADSEg extends TutoTestCase {

	GTCadseShell shell;

	/**
	 * Selects CADSEg in the launcher, and closes useless views.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_selection() throws Exception {

		shell = new GTCadseShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE, GTPreferences.TIMEOUT);
		shell.selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		shell.capture("image020");
		shell.close();
		welcomeView.close();
	}

	/**
	 * Sets up the data-model by creating items.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_data_model() throws Exception {

		workspaceView.show();

		// CADSE WebAppModel
		workspaceView.contextMenuNew(CadseGCST.CADSE_DEFINITION).click();
		shell = new GTCadseShell(CadseGCST.CADSE_DEFINITION);
		findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("WebAppModel");
		findCadseField(shell, CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_).typeText("model.webapp");
		shell.capture("image030");
		shell.close(GTPreferences.TIMEOUT);

		// Querying model
		workspaceView.selectNode(webAppModel, true);
		Item cadseWebApp = workspaceView.findTree().getItem(webAppModel);
		assertNotNull(cadseWebApp);
		assertEquals("WebAppModel", cadseWebApp.getName());
		assertEquals("model.webapp", cadseWebApp.getAttribute(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_));

		// Screenshots
		workspaceView.capture("image032");
		packageExplorerView.show();
		packageExplorerView.selectNode(project_package, true);
		packageExplorerView.capture("image034");
		workspaceView.show();

		// Item Type WebApp
		workspaceView.contextMenuNew(data_model, CadseGCST.ITEM_TYPE).click();
		shell = new GTCadseShell(CadseGCST.ITEM_TYPE);
		findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("WebApp");
		shell.capture("image038");
		shell.close();
		workspaceView.selectNode(it_webApp); /* Assert item has been displayed */
		workspaceView.capture("image040");

		// Other Item Type
		createItemType(data_model, "Library");
		createItemType(data_model, "WebComponent");
		createItemType(data_model, "JSP", new KeyValue(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, it_webComponent));
		createItemType(data_model, "Servlet", new KeyValue(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, it_webComponent));

		// Relative URL attribute
		workspaceView.contextMenuNew(it_webComponent, CadseGCST.STRING).click();
		shell = new GTCadseShell(CadseGCST.STRING);
		findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("relativeURL");
		shell.capture("image046");
		shell.close();
		workspaceView.selectNode(attr_relativeUrl); /* Assert item has been displayed */
		workspaceView.capture("image048");

		// Other attributes
		createString(it_servlet, "className");
		createString(it_servlet, "packageName");

		// hasComp link
		workspaceView.contextMenuNew(it_webApp, CadseGCST.LINK_TYPE).click();
		shell = new GTCadseShell(CadseGCST.LINK_TYPE);
		findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("hasComp");
		findCadseField(shell, CadseGCST.LINK_TYPE_lt_DESTINATION).browser("WebAppModel",
				CadseDefinitionManager.DATA_MODEL, "WebComponent");
		findCadseField(shell, CadseGCST.LINK_TYPE_at_PART_).check(true);
		findCadseField(shell, CadseGCST.LINK_TYPE_at_REQUIRE_).check(true);
		shell.capture("image052");
		shell.close();
		workspaceView.selectNode(link_hasComp); /* Assert item has been displayed */
		workspaceView.capture("image056");

		// Filtering options
		workspaceView.menu("Filters...").click();
		shell = new GTCadseShell("Element Filters");
		shell.findTable().checkTableItem("not aggregation link", false);
		shell.capture("image062");
		shell.close();

		workspaceView.findTree().collapse();
		workspaceView.selectNode(it_webApp.concat("hasComp", "WebComponent")); /*
																				 * Waits until filtering option
																				 * refreshes view
																				 */
		workspaceView.capture("image064");

		// uses link
		GTTreePath dest = new GTTreePath("WebAppModel", CadseDefinitionManager.DATA_MODEL, "Library");
		createLinkType("uses", it_webComponent, dest, null, null, new KeyValue(CadseGCST.LINK_TYPE_at_AGGREGATION_,
				false));

		// Root element attribute + is abstract
		workspaceView.findTree().collapse();
		workspaceView.selectNode(it_jsp);
		workspaceView.capture("image068");
		propertiesView.showTab(GTCadseRTConstants.ITEM_TYPE_TAB_NAME);
		findCadseField(propertiesView, CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_).check(false);
		propertiesView.capture("image070");

		workspaceView.selectNode(it_servlet);
		propertiesView.showTab(GTCadseRTConstants.ITEM_TYPE_TAB_NAME);
		findCadseField(propertiesView, CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_).check(false);

		workspaceView.selectNode(it_webComponent);
		propertiesView.showTab(GTCadseRTConstants.ITEM_TYPE_TAB_NAME);
		findCadseField(propertiesView, CadseGCST.ITEM_TYPE_at_IS_ROOT_ELEMENT_).check(false);
		findCadseField(propertiesView, CadseGCST.ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_).check(true);
	}

	/**
	 * Opens the run dialog and make screen shots.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void test_run() throws Exception {

		// Opens run dialog
		GTMenu.clickOpenRunDialog();

		shell = new GTCadseShell(GTEclipseConstants.RUN_SHELL);
		shell.selectNode("Eclipse Application", "run-cadse-WebAppModel");
		shell.waitUntilButtonEnabled("Run", GTPreferences.TIMEOUT);
		Matcher matcher = allOf(widgetOfType(Combo.class), inGroup("Program to Run"));
		SWTBotCombo combo = new SWTBotCombo((Combo) bot.widget(matcher));
		combo.setText("org.eclipse.platform.ide");
		shell.findButton("Apply").click();
		shell.waitUntilButtonEnabled("Run", GTPreferences.TIMEOUT);

		shell.capture("image076");
		shell.close();

		// Do you really want to clear the run-time workspace data in...
		long oldTimeout = SWTBotPreferences.TIMEOUT;
		try {
			SWTBotPreferences.TIMEOUT = 500;
			shell = new GTCadseShell(GTEclipseConstants.LAUNCHER_SHELL);
			shell.capture("image078");
			shell.close("Cancel");
		}
		catch (Exception e) {
			// do nothing
		}
		SWTBotPreferences.TIMEOUT = oldTimeout;
	}

	@Test
	public void test_zp11_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel, GTPreferences.TIMEOUT);
	}
}
