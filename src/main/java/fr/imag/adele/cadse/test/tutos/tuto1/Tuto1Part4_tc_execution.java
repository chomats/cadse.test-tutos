package fr.imag.adele.cadse.test.tutos.tuto1;

import org.junit.Test;


import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;

public class Tuto1Part4_tc_execution extends TutoTestCase {


	@Test
	public void test_delete() throws Exception {
		
		/* HelloApp */
		workspaceView.selectNode("HelloApp").contextMenu("Delete HelloApp").click();
		shell = new GTCadseShell(GTCadseRTConstants.DELETE_TITLE);
		shell.capture("image138");
		shell.close();
		
		/* Hello2App */
		workspaceView.selectNode("Hello2App").contextMenu("Delete Hello2App").click();
		shell = new GTCadseShell(GTCadseRTConstants.DELETE_TITLE);
		shell.close();
				
		/* Hello3App */
		workspaceView.contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("WebApp").click();
		shell = new GTCadseShell("Create WebApp");
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("Hello3App");
		shell.close();
		
		/* Hello3Servlet */
		workspaceView.selectNode("Hello3App").contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("Servlet").click();
		shell = new GTCadseShell("WebComponent URL Definition");
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("Hello3Servlet");
		shell.findTextWithLabel("relativeURL").typeText("hello3");
		shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		shell.findTextWithLabel("className").typeText("hello3Servlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.close();	
		
		/* Screenshot */
		packageExplorerView.show();
		packageExplorerView.selectNode("Hello3App", "sources");
		packageExplorerView.selectNode("Hello3App.Hello3Servlet", "sources");
		packageExplorerView.selectNode("ServletAPI", "sources");
		packageExplorerView.selectNode("Hello3App");
		packageExplorerView.capture("image140");
	}
}
