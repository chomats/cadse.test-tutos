package fr.imag.adele.cadse.test.tutos.tuto2;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseField;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gtmenu.GTMenu;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTShell;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Tuto2Part1_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_selection() throws Exception {

		// cadses selection
		shell = new GTCadseShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE, GTPreferences.TIMEOUT);
		shell.selectCadses("Cadse Model.Workspace.WebAppModel");
		shell.capture("image030");
		shell.close();
	}

	@Test
	public void test_some_items() throws Exception {

		/* closing welcome view */
		welcomeView.close();

		/* HelloApp */
		try {
			createBasicItem(workspaceView, null, "WebApp", "HelloApp", new GTTreePath("HelloApp"),
					GTPreferences.TIMEOUT);
		}
		catch (Exception e) {
			// FIXME !!! For debug purpose
			System.out.println("have a look!");
			while (true) {
				bot.sleep(2000);
			}
		}

		/* test.HelloServlet */
		workspaceView.contextMenuNew(new GTTreePath("HelloApp"), "Servlet").click();
		shell = new GTCadseShell("Servlet");
		findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("test.HelloServlet");
		shell.findTextWithLabel("className").typeText("HelloServlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.findTextWithLabel("relativeURL").typeText("hello");
		shell.close();

		/* JSP - ignore relativeURL */
		createBasicItem(workspaceView, new GTTreePath("HelloApp"), "JSP", "HelloJSP", null);

		/* MyLibrary */
		createBasicItem(workspaceView, null, "Library", "MyLibrary", new GTTreePath("MyLibrary"));

		/* Screenshot */
		workspaceView.selectNode("HelloApp", "HelloJSP");
		workspaceView.capture("image032");
		workspaceView.show();

		/* Window opening */
		GTMenu.clickShowOtherView();
		GTShell shell = new GTShell(GTMenu.SHOW_VIEW_MENU_ITEM);
		shell.selectNode("WebAppModel", "WebAppList");
		shell.capture("image036");
		shell.close();

		/* Assert view is shown and sets focus into */
		webAppListView.show();
		webAppListView.selectNode("HelloApp", "HelloJSP");
		webAppListView.capture("image038");
	}
}
