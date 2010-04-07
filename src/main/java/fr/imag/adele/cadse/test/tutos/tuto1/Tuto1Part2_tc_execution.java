package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseField;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class Tuto1Part2_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_App() throws Exception {

		/* Hello2App */
		createBasicItem(workspaceView, null, "WebApp", "Hello2App", new GTTreePath("Hello2App"));

		/* hello2Servlet */
		workspaceView.contextMenuNew(new GTTreePath("Hello2App"), "Servlet").click();
		shell = new GTCadseShell("Servlet");
		findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("Hello2Servlet");
		findCadseField(shell, "className").typeText("hello2Servlet");
		findCadseField(shell, "packageName").typeText("test");
		findCadseField(shell, "relativeURL").typeText("hello2");
		shell.capture("image118");
		shell.close();

		/* ServletAPI library */
		createBasicItem(workspaceView, null, "Library", "ServletAPI", new GTTreePath("ServletAPI"));

		/* hello2Servlet */
		workspaceView.selectNode("Hello2App", "Hello2Servlet");
		propertiesView.showTab("Servlet");
		propertiesView.findButton("Add...").click();

		shell = new GTCadseShell("Select a destination.");
		shell.selectNode("ServletAPI");
		shell.capture("image128");
		shell.close();
	}
}
