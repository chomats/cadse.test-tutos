package fr.imag.adele.cadse.test.tutos.tuto2;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseField;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.deleteBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTTextEditor;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Tuto2Part2_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_run() throws Exception {

		/* Assert tree has been displayed */
		workspaceView.show();
		workspaceView.selectNode(new GTTreePath("HelloApp"), GTPreferences.TIMEOUT);

		/* Delete test.HelloServlet Servlet */
		deleteBasicItem(workspaceView, new GTTreePath("HelloApp", "test.HelloServlet"), GTPreferences.TIMEOUT);

		/* New Servlet */
		workspaceView.contextMenuNew(new GTTreePath("HelloApp"), "Servlet").click();
		shell = new GTCadseShell("Servlet");
		findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("test.HelloServlet");
		shell.findTextWithLabel("className").typeText("HelloServlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.findTextWithLabel("relativeURL").typeText("hello");
		shell.close();

		/* edition */
		packageExplorerView.show();
		GTTreePath path = new GTTreePath("HelloApp.test.HelloServlet", "sources", "test", "HelloServlet.java",
				"HelloServlet");
		packageExplorerView.findTree().doubleClick(path);
		packageExplorerView.capture("image060");
		GTTextEditor editor = new GTTextEditor("HelloServlet.java");
		editor.show();
		editor.capture("image062");
		editor.close();
	}
}
