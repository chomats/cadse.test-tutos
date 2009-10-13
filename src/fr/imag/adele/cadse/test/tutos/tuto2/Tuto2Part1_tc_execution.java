package fr.imag.adele.cadse.test.tutos.tuto2;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.GTCadseRTConstants;
import fr.imag.adele.cadse.test.GTEclipseConstants;
import fr.imag.adele.cadse.test.GTScreenshot;
import fr.imag.adele.cadse.test.GTTestParameters;
import fr.imag.adele.cadse.test.gtworkbench_part.GTShell;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.cadse.test.gtmenu.GTMenu;
import fr.imag.adele.cadse.test.gtmenu.GTMenuConstants;

public class Tuto2Part1_tc_execution extends TutoTestCase {

	@Test
	public void test_init() throws Exception {
		
		GTTestParameters.banner();
		//SWTBotPreferences.PLAYBACK_DELAY = 100;
		SWTBotPreferences.TIMEOUT = 5000;
		GTScreenshot.setScreenshotPath(System.getProperty("test.screenshotPath"));
	}
		
	
	@Test
	public void test_selection() throws Exception {
		
		// cadses selection
		shell = new GTShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE);
		shell.selectCadses("WebAppModel");
		shell.capture("image030");
		shell.close();
	}
	
	
	@Test
	public void test_some_items() throws Exception {
		
		/* closing welcome view */
		welcomeView.close();
		
	
		/* HelloApp */
		workspaceView.contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("WebApp").click();
		shell = new GTShell("Create WebApp");
		shell.findField(CadseGCST.ITEM_at_NAME_).typeText("HelloApp");
		shell.close();

		/* test.HelloServlet */
		workspaceView.findTree().selectNode("HelloApp").contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("Servlet").click();
		shell = new GTShell("WebComponent URL Definition");
		shell.findField(CadseGCST.ITEM_at_NAME_).typeText("test.HelloServlet");
		shell.findTextWithLabel("relativeURL").typeText("hello");
		shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		shell.findTextWithLabel("className").typeText("helloServlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.close();	
		
		/* JSP */
		workspaceView.findTree().selectNode("HelloApp").contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("JSP").click();
		shell = new GTShell("WebComponent URL Definition");
		shell.findField(CadseGCST.ITEM_at_NAME_).typeText("HelloJSP");
		shell.close();	

		/* MyLibrary */
		workspaceView.contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("Library").click();
		shell = new GTShell("Create Library");
		shell.findField(CadseGCST.ITEM_at_NAME_).typeText("MyLibrary");
		shell.close();
	
		/* Screenshot */
		workspaceView.findTree().selectNode("HelloApp","HelloJSP");
		workspaceView.capture("image032");
		workspaceView.show();
		
		/* Window opening */
		GTMenu.clickShowOtherView();
		GTShell shell = new GTShell(GTMenuConstants.SHOW_VIEW_MENU_ITEM);
		shell.findTree().selectNode("WebAppModel", "WebAppList");
		shell.capture("image036");
		shell.close();

		/* Assert view is shown and sets focus into */  
		webAppListView.show();
		webAppListView.findTree().selectNode("HelloApp","HelloJSP");
		webAppListView.capture("image038");
	}
}
