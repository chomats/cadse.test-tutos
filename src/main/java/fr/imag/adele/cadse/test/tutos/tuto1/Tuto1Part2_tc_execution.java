package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseField;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.fede.workspace.si.persistence.Persistence;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Tuto1Part2_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_check_persistence_id() throws IOException, ClassNotFoundException {
		Location instanceLoc = Platform.getInstanceLocation();
		File location = new File(instanceLoc.getURL().getFile());
		Persistence.loadID_6(location.getAbsolutePath());
	}

	@Test
	public void test_App() throws Exception {

		try {
			workspaceView.selectNode(new GTTreePath("HelloApp"), GTPreferences.TIMEOUT);
		}
		catch (Exception e) {
			// FIXME has to be updated soon!
			System.out.println("FIXME : (Tuto1Part2 Execution) HelloApp n'apparait pas dans la vue");
			while (true) {
				bot.sleep(1000);
			}
		}

		/* Hello2App */
		try {
			createBasicItem(workspaceView, null, "WebApp", "Hello2App", new GTTreePath("Hello2App"),
					GTPreferences.TIMEOUT);
		}
		catch (Exception e) {
			// FIXME has to be updated soon!
			System.out
					.println("FIXME : (Tuto1Part2 Execution) Pour que la vue se rafraichisse, il faut ajouter un élément... (même le refresh ne marche pas)");
			while (true) {
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
		try {
			createBasicItem(workspaceView, null, "Library", "ServletAPI", new GTTreePath("ServletAPI"));
		}
		catch (Exception e) {
			// FIXME has to be updated soon!
			System.out.println("FIXME : (Tuto1Part2 Execution) ServletApi est déjà créé mais il n'est pas affiché.");
			while (true) {
				bot.sleep(5000);
			}
		}

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
