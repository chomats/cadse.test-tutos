package fr.imag.adele.cadse.test.tutos.tuto2;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.GTCadseRTConstants;
import fr.imag.adele.cadse.test.GTEclipseConstants;
import fr.imag.adele.cadse.test.gtworkbench_part.GTEditor;
import fr.imag.adele.cadse.test.gtworkbench_part.GTShell;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;

public class Tuto2Part2_tc_execution extends TutoTestCase {

	@Test
	public void test_run() throws Exception {

		workspaceView.show();
		
		/* Delete test.HelloServlet Servlet */
		workspaceView.findTree().selectNode("HelloApp", "test.HelloServlet").contextMenu("Delete test.HelloServlet").click();
		shell = new GTShell(GTCadseRTConstants.DELETE_TITLE);
		shell.close();

		/* New Servlet */
		workspaceView.findTree().selectNode("HelloApp").contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("Servlet").click();
		shell = new GTShell("WebComponent URL Definition");
		shell.findField(CadseGCST.ITEM_at_NAME_).typeText("test.HelloServlet");
		shell.findTextWithLabel("relativeURL").typeText("hello");
		shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		shell.findTextWithLabel("className").typeText("helloServlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.close();
		
		/* edition */
		packageExplorerView.show();
		packageExplorerView.findTree().selectNode("test.HelloServlet", "sources", "test", "helloServlet.java", "helloServlet").doubleClick();
		packageExplorerView.capture("image060");
		GTEditor editor = new GTEditor("helloServlet.java");
		editor.show();
		editor.capture("image062");
		editor.close();
	}
}
