package fr.imag.adele.cadse.test.tutos.tuto2;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.graphictests.gtworkbench_part.GTEditor;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;

public class Tuto2Part2_tc_execution extends TutoTestCase {

	@Test
	public void test_run() throws Exception {

		workspaceView.show();
		
		/* Delete test.HelloServlet Servlet */
		workspaceView.selectNode("HelloApp", "test.HelloServlet").contextMenu("Delete test.HelloServlet").click();
		shell = new GTCadseShell(GTCadseRTConstants.DELETE_TITLE);
		shell.close();

		/* New Servlet */
		workspaceView.selectNode("HelloApp").contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("Servlet").click();
		shell = new GTCadseShell("WebComponent URL Definition");
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("test.HelloServlet");
		shell.findTextWithLabel("relativeURL").typeText("hello");
		shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		shell.findTextWithLabel("className").typeText("helloServlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.close();
		
		/* edition */
		packageExplorerView.show();
		packageExplorerView.selectNode("HelloApp.test.HelloServlet", "sources", "test", "helloServlet.java", "helloServlet").doubleClick();
		packageExplorerView.capture("image060");
		GTEditor editor = new GTEditor("helloServlet.java");
		editor.show();
		editor.capture("image062");
		editor.close();
	}
}
