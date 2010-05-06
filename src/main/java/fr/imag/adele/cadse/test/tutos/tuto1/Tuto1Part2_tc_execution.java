package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;
import org.junit.Test;

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

		workspaceView.selectNode(new GTTreePath("HelloApp"), GTPreferences.TIMEOUT);

		/* Hello2App */
		createBasicItem(workspaceView, null, "WebApp", "Hello2App", new GTTreePath("Hello2App"), GTPreferences.TIMEOUT);

		/* hello2Servlet */
		workspaceView.contextMenuNew(new GTTreePath("Hello2App"), "Servlet").click();
		shell = new GTCadseShell("Servlet");
		shell.findCadseFieldName().typeText("Hello2Servlet");
		shell.findCadseField("className").typeText("hello2Servlet");
		shell.findCadseField("packageName").typeText("test");
		shell.findCadseField("relativeURL").typeText("hello2");
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
