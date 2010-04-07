package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class Tuto1Part2_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_App() throws Exception {

		/* Hello2App */
		workspaceView.contextMenuNew("WebApp").click();
		shell = new GTCadseShell("WebApp");
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("Hello2App");
		shell.close();

		/* hello2Servlet */
		workspaceView.contextMenuNew(new GTTreePath("Hello2App"), "Servlet").click();

		// FIXME [PAGES] to be updated with user defined pages
		// shell = new GTCadseShell("WebComponent URL Definition");
		shell = new GTCadseShell("Servlet");
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("Hello2Servlet");
		shell.findTextWithLabel("relativeURL").typeText("hello2");
		shell.capture("image117");
		// PAGES!!!! shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		shell.findTextWithLabel("className").typeText("hello2Servlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.capture("image118");
		shell.close();

		// Ancienne part 3...

		/* ServletAPI library */
		workspaceView.contextMenuNew("Library").click();
		shell = new GTCadseShell("Library");
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("ServletAPI");
		shell.close();

		/* hello2Servlet */
		workspaceView.selectNode("Hello2App", "Hello2Servlet");

		// FIXME [PAGES] propertiesView.showTab("WebComponent Options");
		propertiesView.showTab("Servlet");
		propertiesView.findButton("Add...").click();

		shell = new GTCadseShell("Select a value.");
		shell.selectNode("ServletAPI");
		shell.capture("image128");
		shell.close();

	}
}
