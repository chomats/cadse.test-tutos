package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseFieldName;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;
import org.junit.Test;

import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.fede.workspace.si.persistence.Persistence;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Tuto1Part1_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_selection() throws Exception {

		// cadses selection
		shell = new GTCadseShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE, GTPreferences.TIMEOUT);
		shell.selectCadses("Cadse Model.Workspace.WebAppModel");
		shell.capture("image080");
		shell.close();
	}

	@Test
	public void test_feature() throws Exception {

		workspaceView.contextMenuNew("WebApp").click();
		shell = new GTCadseShell("WebApp");
		findCadseFieldName(shell).typeText("HelloApp");
		shell.capture("image082");
		shell.close();
	}

	@Test
	public void test_check_persistence_id() throws IOException, ClassNotFoundException {
		Location instanceLoc = Platform.getInstanceLocation();
		File location = new File(instanceLoc.getURL().getFile());
		Persistence.loadID_6(location.getAbsolutePath());
	}
}
