package fr.imag.adele.cadse.test.tutos.tuto2;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTShell;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.gtmenu.GTMenu;
import fr.imag.adele.graphictests.gtmenu.GTMenuConstants;

public class Tuto2Part1_tc_execution extends TutoTestCase {

	@Test
	public void test_selection() throws Exception {
		
		// cadses selection
		shell = new GTCadseShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE);
		shell.selectCadses("Cadse Model.Workspace.WebAppModel");
		shell.capture("image030");
		shell.close();
	}
	
	
	@Test
	public void test_some_items() throws Exception {
		
		/* closing welcome view */
		welcomeView.close();
		
	
		/* HelloApp */
		workspaceView.contextMenuNew("WebApp").click();
		shell = new GTCadseShell("Create WebApp");
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("HelloApp");
		shell.close();

		/* test.HelloServlet */
		workspaceView.contextMenuNew(new GTTreePath("HelloApp"), "Servlet").click();
		shell = new GTCadseShell("WebComponent URL Definition");
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("test.HelloServlet");
		shell.findTextWithLabel("relativeURL").typeText("hello");
		shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		shell.findTextWithLabel("className").typeText("helloServlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.close();	
		
		/* JSP */
		workspaceView.contextMenuNew(new GTTreePath("HelloApp"), "JSP").click();
		shell = new GTCadseShell("WebComponent URL Definition");
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("HelloJSP");
		shell.close();

		/* MyLibrary */
		workspaceView.contextMenuNew("Library").click();
		shell = new GTCadseShell("Create Library");
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("MyLibrary");
		shell.close();
	
		/* Screenshot */
		workspaceView.selectNode("HelloApp","HelloJSP");
		workspaceView.capture("image032");
		workspaceView.show();
		
		/* Window opening */
		GTMenu.clickShowOtherView();
		GTShell shell = new GTShell(GTMenuConstants.SHOW_VIEW_MENU_ITEM);
		shell.selectNode("WebAppModel", "WebAppList");
		shell.capture("image036");
		shell.close();

		/* Assert view is shown and sets focus into */  
		webAppListView.show();
		webAppListView.selectNode("HelloApp","HelloJSP");
		webAppListView.capture("image038");
	}
}
