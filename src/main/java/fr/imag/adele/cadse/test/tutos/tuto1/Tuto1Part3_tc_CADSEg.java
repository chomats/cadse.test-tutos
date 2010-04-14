package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseField;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createJavaProjectContentModel;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.KeyValue;
import fr.imag.adele.graphictests.gtworkbench_part.GTShell;
import fr.imag.adele.graphictests.test.GTPreferences;

/**
 * Performs the official simple tutorial
 */
public class Tuto1Part3_tc_CADSEg extends TutoTestCase {

	GTCadseShell shell;

	/**
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_item_content() throws Exception {

		workspaceView.selectNode(mapping_webApp, GTPreferences.TIMEOUT); /* Assert tree has been displayed */
		workspaceView.findTree().collapse();
		workspaceView.selectNode(mapping_webApp);
		workspaceView.capture("image132");

		workspaceView.contextMenuNew(mapping_webApp, CadseGCST.JAVA_PROJECT_CONTENT_MODEL).click();
		shell = new GTCadseShell(CadseGCST.JAVA_PROJECT_CONTENT_MODEL);
		findCadseField(shell, CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_).check(true);
		shell.capture("image134");
		shell.close();
		workspaceView.selectNode(content_webApp);

		createJavaProjectContentModel(workspaceView, mapping_library, KeyValue.jpcmSrcFolderKv);
		createJavaProjectContentModel(workspaceView, mapping_servlet, KeyValue.jpcmSrcFolderKv);
		KeyValue kv = new KeyValue(CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_, "/${#short-name}.jsp");

		workspaceView.contextMenuNew(mapping_jsp, "FileContentModel").click();
		GTShell shell = new GTShell("FileContentModel");
		kv.setValue(shell);
		shell.capture("image135");
		shell.close();
	}

	@Test
	public void test_zp14_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel, GTPreferences.TIMEOUT);
	}
}