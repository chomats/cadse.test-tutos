package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseField;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Tuto1Part2_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_App() throws Exception {

		/* Hello2App */
		try {
			createBasicItem(workspaceView, null, "WebApp", "Hello2App", new GTTreePath("Hello2App"),
					GTPreferences.TIMEOUT);
		}
		catch (Exception e) {
			System.out
					.println("FIXME BLOQUE! regarder si le nom de l'instance est dans la boite de dialogue Tester le refresh sur la workspace view!");
			while (true) {
				// FIXME to be removed
				bot.sleep(1000);
			}
		}

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
