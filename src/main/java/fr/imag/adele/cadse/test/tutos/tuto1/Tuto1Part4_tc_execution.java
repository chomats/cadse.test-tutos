package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.deleteBasicItem;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.packageExplorerView;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.propertiesView;

import org.junit.Test;

import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Tuto1Part4_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_feature() throws Exception {

		/* Test init */
		workspaceView.show();
		/* Assert tree has been displayed */
		workspaceView.selectNode(new GTTreePath("ServletAPI"), GTPreferences.TIMEOUT);

		/* ServletAPI deletion */
		deleteBasicItem(workspaceView, new GTTreePath("ServletAPI"));

		/* ServletAPI creation */
		createBasicItem(workspaceView, null, "Library", "ServletAPI", new GTTreePath("ServletAPI"));

		/* hello3Servlet */
		workspaceView.selectNode("Hello3App", "Hello3Servlet");
		propertiesView.showTab("Servlet");
		propertiesView.findButton("Add...").click();

		shell = new GTCadseShell("Select a destination.");
		shell.selectNode("ServletAPI");
		shell.close();

		/* Screenshot */
		packageExplorerView.show();
		packageExplorerView.selectNode(new GTTreePath("Hello3App"), true);
		packageExplorerView.selectNode(new GTTreePath("ServletAPI"), true);
		packageExplorerView.selectNode(new GTTreePath("Hello3App.Hello3Servlet", "Item Dependencies", "ServletAPI"),
				true);
		packageExplorerView.capture("image148");
	}
}
