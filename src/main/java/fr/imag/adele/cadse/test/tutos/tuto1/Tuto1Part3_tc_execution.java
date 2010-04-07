package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTEclipseConstants;

public class Tuto1Part3_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_delete() throws Exception {

		/* HelloApp */
		workspaceView.contextMenu(new GTTreePath("HelloApp"), "Delete HelloApp").click();
		shell = new GTCadseShell(GTCadseRTConstants.DELETE_TITLE);
		shell.capture("image138");
		shell.close();

		/* Hello2App */
		workspaceView.contextMenu(new GTTreePath("Hello2App"), "Delete Hello2App").click();
		shell = new GTCadseShell(GTCadseRTConstants.DELETE_TITLE);
		shell.close();

		/* Hello3App */
		workspaceView.contextMenuNew("WebApp").click();
		shell = new GTCadseShell("Create WebApp");
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("Hello3App");
		shell.close();

		/* Hello3Servlet */
		workspaceView.contextMenuNew(new GTTreePath("Hello3App"), "Servlet").click();
		shell = new GTCadseShell("WebComponent URL Definition");
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("Hello3Servlet");
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
