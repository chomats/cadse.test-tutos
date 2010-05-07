package fr.imag.adele.cadse.test.tutos.common;

import static fr.imag.adele.graphictests.gtworkbench_part.GTView.packageExplorerView;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gtmenu.GTMenu;
import fr.imag.adele.graphictests.gtworkbench_part.GTTextEditor;
import fr.imag.adele.graphictests.test.GTEclipseConstants;

public class EndOfCADSEg extends TutoTestCase {

	GTCadseShell shell;

	/**
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void end_of_test_copy_composer() throws Exception {

		// WebAppManager implementation
		packageExplorerView.findTree().doubleClick(webappManagerClass);
		GTTextEditor editor = new GTTextEditor("WebAppManager.java");

		editor.find("postCompose(");
		editor.find("}");

		editor.typeText("	// Retrieve project reference\n");
		editor.typeText("ProjectContentManager contentMgr = (ProjectContentManager) getContentItem();\n");
		editor.typeText("IProject project = contentMgr.getProject();\n");
		editor.typeText("// Retrieve servlet informations\n");
		editor.typeText("List servletList = new ArrayList();\n");
		editor.typeText("for (Item item : getHasComp(getItem())) {\n");
		editor.typeText("// We are only interrested in Servlet items\n");
		editor.typeText("if (!item.isInstanceOf(WebAppModelCST.SERVLET))\n");
		editor.typeText("continue;\n");
		editor
				.typeText("servletList.add(new ServletJO(ServletManager.getPackageNameAttribute(item), ServletManager.getClassNameAttribute(item), ServletManager.getRelativeURLAttribute(item)));\n");
		editor.typeText("}\n");
		editor.typeText("// Generate web.xml file content\n");
		editor.typeText("String content = new WebXMLGenerator().generate(servletList);\n");
		editor.typeText("// Generate web.xml file\n");
		editor.typeText("try {\n");
		editor
				.typeText("MappingManager.generate(project, new Path(\"WEB-INF\"), \"web.xml\", content, EclipseTool.getDefaultMonitor());\n");
		editor.typeText("} catch (CoreException e) {\n");
		editor.typeText("return; // ignore exceptions\n");
		editor.typeText("}\n");
		editor.typeText("// create dist folder\n");
		editor.typeText("try {\n");
		editor.typeText("MappingManager.createFolder(project.getFolder(\"dist\"), EclipseTool.getDefaultMonitor());\n");
		editor.typeText("} catch (CoreException e) {\n");
		editor.typeText("return; // ignore exceptions\n");
		editor.typeText("}\n");
		editor.typeText("// Generate the war file\n");
		editor.typeText("WarFileUtil.createWarFile(project, getItem().getUniqueName());\n");

		// suppresses the extra parentesis
		GTMenu.clickFindAndReplace();
		shell = new GTCadseShell(GTEclipseConstants.FIND_REPLACE_SHELL);
		shell.findComboBox().setText("}");
		shell.findButton(GTEclipseConstants.FIND_BUTTON).click();
		shell.findButton(GTEclipseConstants.REPLACE_BUTTON).click();
		shell.close();

		editor.save();

		// Quick fixes
		packageExplorerView.findTree().doubleClick(webappManagerClass);
		editor = new GTTextEditor("WebAppManager.java");

		editor.find("IProject");
		editor.quickfix("Import 'IProject' (org.eclipse.core.resources)");

		editor.find("ArrayList");
		editor.quickfix("Import 'ArrayList' (java.util)");

		editor.find("ServletJO");
		editor.quickfix("Import 'ServletJO' (model.webapp.template)");

		editor.find("WebXMLGenerator");
		editor.quickfix("Import 'WebXMLGenerator' (model.webapp.template)");

		editor.find("EclipseTool");
		editor.quickfix("Import 'EclipseTool' (fede.workspace.tool.eclipse)");

		editor.find("CoreException");
		editor.quickfix("Import 'CoreException' (org.eclipse.core.runtime)");

		editor.find("MappingManager");
		editor.quickfix("Import 'MappingManager' (fede.workspace.tool.eclipse)");

		editor.find("WarFileUtil");
		editor.quickfix("Import 'WarFileUtil' (model.webapp.template)");

		editor.save();
	}
}
