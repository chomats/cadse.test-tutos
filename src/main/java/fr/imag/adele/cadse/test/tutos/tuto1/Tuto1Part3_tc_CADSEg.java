package fr.imag.adele.cadse.test.tutos.tuto1;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;


public class Tuto1Part3_tc_CADSEg extends TutoTestCase {
	
	/**
	 * Modification pages configuration.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_modification_pages() throws Exception {

		workspaceView.findTree().collapse();
		// FIXME Modification pages
		/*
		workspaceView.selectNode(mp_servlet);
		propertiesView.showTab("Page");
		GTCadseFactory.findField(propertiesView, CadseGCST.PAGE_at_TITLE_).typeText("Servlet Options");
		GTCadseFactory.findField(propertiesView, CadseGCST.PAGE_at_DESCRIPTION_).typeText("You can change the library dependencies");
		propertiesView.capture("image124");
		
		workspaceView.selectNode(mp_webComponent);
		propertiesView.showTab("Page");
		GTCadseFactory.findField(propertiesView, CadseGCST.PAGE_at_TITLE_).typeText("WebComponent Options");
		GTCadseFactory.findField(propertiesView, CadseGCST.PAGE_at_DESCRIPTION_).typeText("You can change the WebComponent options");
		propertiesView.capture("image126");
		*/
	}
	
	@Test
	public void test_zp13_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel);
	}
}