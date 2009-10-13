package fr.imag.adele.cadse.test.tutos.common;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;

import java.util.List;

import model.workspace.copycomposer.CopyComposerCST;

import org.eclipse.swt.widgets.Widget;
import org.hamcrest.Matcher;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.GTCadseRTConstants;
import fr.imag.adele.cadse.test.GTEclipseConstants;
import fr.imag.adele.cadse.test.gtmenu.GTMenu;
import fr.imag.adele.cadse.test.gtworkbench_part.GTEditor;
import fr.imag.adele.cadse.test.gtworkbench_part.GTShell;

public class EndOfCADSEg extends TutoTestCase {



	/**
	 * 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_build_model_definition() throws Exception {

		// Composition link
		workspaceView.findTree().collapse();
		workspaceView.findTree().selectNode(attr_hasComp);
		workspaceView.capture("image094");

		propertiesView.showTab("Link");
		propertiesView.findField(CadseGCST.LINK_at_COMPOSITION_).check(true);
		propertiesView.capture("image096");


		// Execute CADSE
		workspaceView.findTree().selectNode(webAppModel).contextMenu("Execute cadse").click();
		shell = new GTShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE);
		shell.selectCadses("CopyComposer");
		shell.capture("image097");
		shell.close();


		// JavaRefExporter
		workspaceView.findTree().selectNode(mapping_servlet).contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("JavaRefExporter").click();
		shell = new GTShell(CopyComposerCST.JAVA_REF_EXPORTER);
		shell.capture("image102");
		shell.close();

		workspaceView.findTree().collapse();
		workspaceView.findTree().selectNode(refExporter);
		workspaceView.capture("image104");
	}

	/**
	 * 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_copy_composer() throws Exception {

		workspaceView.findTree().collapse();
		workspaceView.findTree().selectNode(composite);
		workspaceView.capture("image106");
		workspaceView.findTree().selectNode(composite).contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("CopyIntoFolderComposer").click();
		shell = new GTShell(CopyComposerCST.COPY_INTO_FOLDER_COMPOSER);
		shell.findField(CadseGCST.ITEM_at_NAME_).typeText("GenWarComposer");
		shell.findField(CopyComposerCST.COPY_INTO_FOLDER_COMPOSER_at_TARGET_FOLDER_).typeText("WEB-INF/classes");
		shell.findField(CadseGCST.COMPOSER_at_EXTENDS_CLASS_).check(true);
		shell.findField(CadseGCST.COMPOSER_at_TYPES_).add("ref-classes"); 
		shell.capture("image112");
		shell.close();


		// WebAppManager implementation		
		packageExplorerView.findTree().selectNode(webappManagerClass).doubleClick();
		GTEditor editor = new GTEditor("WebAppManager.java");

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
		editor.typeText("servletList.add(new ServletJO(ServletManager.getPackageNameAttribute(item), ServletManager.getClassNameAttribute(item), ServletManager.getRelativeURLAttribute(item)));\n");
		editor.typeText("}\n");
		editor.typeText("// Generate web.xml file content\n");
		editor.typeText("String content = new WebXMLGenerator().generate(servletList);\n");
		editor.typeText("// Generate web.xml file\n");
		editor.typeText("try {\n");
		editor.typeText("MappingManager.generate(project, new Path(\"WEB-INF\"), \"web.xml\", content, EclipseTool.getDefaultMonitor());\n");
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
		shell = new GTShell(GTEclipseConstants.FIND_REPLACE_SHELL);
		shell.findComboBox().setText("}");
		shell.findButton(GTEclipseConstants.FIND_BUTTON).click();
		shell.findButton(GTEclipseConstants.REPLACE_BUTTON).click();
		shell.close();
		
		editor.save();
		
		
		// Quick fixes		
		packageExplorerView.findTree().selectNode(webappManagerClass).doubleClick();
		editor = new GTEditor("WebAppManager.java");

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

	/**
	 * End of test.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void test_close() throws Exception {

		Matcher matcher = allOf() ;
		List<? extends Widget> lw = bot.widgets(matcher);
		System.out.println("Fin");
	}
}
