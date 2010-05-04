package fr.imag.adele.cadse.test.tutos.tuto2;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseField;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseFieldName;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;
import model.workspace.copycomposer.CopyComposerCST;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.CadseRuntime;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.dialog.CadseDialog;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.fede.workspace.as.initmodel.ErrorWhenLoadedModel;
import fr.imag.adele.fede.workspace.si.view.View;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gtmenu.GTMenu;
import fr.imag.adele.graphictests.gtworkbench_part.GTTextEditor;
import fr.imag.adele.graphictests.test.GTPreferences;

/**
 * Performs the official simple tutorial
 */
public class Tuto2Part4_tc_CADSEg extends TutoTestCase {

	GTCadseShell shell;

	/**
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_build_model_definition() throws Exception {

		/* Assert tree has been displayed */
		workspaceView.selectNode(linkHasComp, GTPreferences.TIMEOUT);
		workspaceView.findTree().collapse();

		// Composition link
		workspaceView.selectNode(linkHasComp);
		workspaceView.capture("image094");

		propertiesView.showTab("LinkType");
		findCadseField(propertiesView, CadseGCST.LINK_TYPE_at_COMPOSITION_).check(true);
		propertiesView.capture("image096");

		// Executed CADSEs
		bot.getDisplay().asyncExec(new Runnable() {
			public void run() {
				CadseRuntime[] sCadsesNameToLoad = CadseDialog.openDialog(false);
				if (sCadsesNameToLoad != null && sCadsesNameToLoad.length != 0) {
					try {
						View.getInstance().getInitModel().executeCadses(sCadsesNameToLoad);
					}
					catch (ErrorWhenLoadedModel e) {
						e.printStackTrace();
					}
				}
			}
		});
		// workspaceView.contextMenu(/* webAppModel */null, "Executed CADSEs").click();
		shell = new GTCadseShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE);
		shell.selectCadses("CopyComposer");
		shell.capture("image097");
		shell.close();

		// JavaRefExporter
		workspaceView.contextMenuNew(mappingServlet, CopyComposerCST.JAVA_REF_EXPORTER).click();
		shell = new GTCadseShell(CopyComposerCST.JAVA_REF_EXPORTER);
		findCadseFieldName(shell).typeText("JavaContentExporter");
		shell.capture("image102");
		shell.close();

		workspaceView.findTree().collapse();
		workspaceView.selectNode(refExporter);
		workspaceView.capture("image104");
	}

	/**
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_copy_composer() throws Exception {

		// Creates copy composer
		workspaceView.findTree().collapse();
		workspaceView.selectNode(composite);
		workspaceView.capture("image106");
		workspaceView.contextMenuNew(composite, "Copy Folder Content Composer").click();
		shell = new GTCadseShell(CopyComposerCST.COPY_INTO_FOLDER_COMPOSER);

		findCadseFieldName(shell).typeText("GenWarComposer");
		findCadseField(shell, CopyComposerCST.COPY_INTO_FOLDER_COMPOSER_at_TARGET_FOLDER_).typeText("WEB-INF/classes");
		findCadseField(shell, CadseGCST.COMPOSER_at_TYPES_).addValue("ref-classes");
		findCadseField(shell, CadseGCST.RUNTIME_ITEM_at_EXTENDS_CLASS_).check(true);
		shell.capture("image112");
		shell.close();

		// copy code into clipboard
		copyFileIntoClipboard(filePostCompose);

		// copy composer implementation
		packageExplorerView.findTree().doubleClick(webappManagerClass);
		GTTextEditor editor = new GTTextEditor("WebAppManager.java");
		editor.find("postCompose(");
		editor.find("}");
		GTMenu.clickPaste();
		editor.save();

		// Quick fixes - adding imports
		copyFileIntoClipboard(importsPostCompose);
		packageExplorerView.findTree().doubleClick(webappManagerClass);
		editor = new GTTextEditor("WebAppManager.java");
		editor.navigateTo(2, 0);
		GTMenu.clickPaste();
		editor.save();
	}

	@Test
	public void test_zp24_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel, GTPreferences.TIMEOUT);
	}
}
