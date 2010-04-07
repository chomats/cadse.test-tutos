package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseField;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;

public class Tuto1Part1_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_selection() throws Exception {

		// cadses selection
		shell = new GTCadseShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE);
		shell.selectCadses("Cadse Model.Workspace.WebAppModel");
		shell.capture("image080");
		shell.close();
	}

	@Test
	public void test_feature() throws Exception {

		workspaceView.contextMenuNew("WebApp").click();
		shell = new GTCadseShell("WebApp");
		findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("HelloApp");
		shell.capture("image082");
		shell.close();
	}
}
