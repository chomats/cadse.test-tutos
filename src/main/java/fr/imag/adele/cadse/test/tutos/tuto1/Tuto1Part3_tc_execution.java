package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseField;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.deleteBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Tuto1Part3_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_delete() throws Exception {

		/* Assert tree has been displayed */
		GTTreePath node = new GTTreePath("HelloApp");
		workspaceView.selectNode(node, GTPreferences.TIMEOUT);

		/* Delete HelloApp */
		workspaceView.contextMenu(node, "Delete HelloApp").click();
		shell = new GTCadseShell(GTCadseRTConstants.DELETE_TITLE);
		shell.capture("image138");
		shell.close();

		/* Delete Hello2App */
		deleteBasicItem(workspaceView, new GTTreePath("Hello2App"));

		/* Create Hello3App */
		createBasicItem(workspaceView, null, "WebApp", "Hello3App", new GTTreePath("Hello3App"));

		/* Create Hello3Servlet */
		workspaceView.contextMenuNew(new GTTreePath("Hello3App"), "Servlet").click();
		shell = new GTCadseShell("Servlet");
		findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("Hello3Servlet");
		findCadseField(shell, "className").typeText("Hello3Servlet");
		findCadseField(shell, "packageName").typeText("test");
		findCadseField(shell, "relativeURL").typeText("hello3");
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
