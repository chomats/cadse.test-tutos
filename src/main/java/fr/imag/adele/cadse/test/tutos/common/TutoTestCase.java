package fr.imag.adele.cadse.test.tutos.common;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gtmenu.GTMenu;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTTextEditor;
import fr.imag.adele.graphictests.gtworkbench_part.GTView;

public abstract class TutoTestCase extends GTCadseTestCase {

	protected GTTreePath webAppModel = new GTTreePath("WebAppModel");

	protected GTTreePath buildModel = webAppModel.concat(CadseDefinitionManager.BUILD_MODEL);
	protected GTTreePath composite = buildModel.concat("WebApp-composite");

	protected GTTreePath dataModel = webAppModel.concat(CadseDefinitionManager.DATA_MODEL);
	protected GTTreePath itWebApp = dataModel.concat("WebApp");
	protected GTTreePath itLibrary = dataModel.concat("Library");
	protected GTTreePath itWebComponent = dataModel.concat("WebComponent");
	protected GTTreePath itJsp = dataModel.concat("JSP");
	protected GTTreePath itServlet = dataModel.concat("Servlet");

	protected GTTreePath mapping = webAppModel.concat(CadseDefinitionManager.MAPPING);
	protected GTTreePath mappingWebComponent = mapping.concat("WebComponent-manager");
	protected GTTreePath mappingWebApp = mapping.concat("WebApp-manager");
	protected GTTreePath mappingJsp = mapping.concat("JSP-manager");
	protected GTTreePath mappingLibrary = mapping.concat("Library-manager");
	protected GTTreePath mappingServlet = mapping.concat("Servlet-manager");

	protected GTTreePath contentJsp = mappingJsp.concat("content-item");
	protected GTTreePath contentLibrary = mappingLibrary.concat("content-item");
	protected GTTreePath contentServlet = mappingServlet.concat("content-item");
	protected GTTreePath contentWebApp = mappingWebApp.concat("content-item");

	protected GTTreePath refExporter = mappingServlet.concat("JavaContentExporter");

	protected GTTreePath linkHasComp = itWebApp.concat("hasComp");
	protected GTTreePath attrPackageName = itServlet.concat("packageName");
	protected GTTreePath attrClassName = itServlet.concat("className");
	protected GTTreePath attrRelativeUrl = itWebComponent.concat("relativeURL");

	protected GTTreePath linkUses = itWebComponent.concat("uses");

	protected GTTreePath cdServlet = itServlet.concat("creation dialog");
	protected GTTreePath cpServlet = cdServlet.concat("creation-page-Servlet");

	protected GTTreePath cdWebComponent = itWebComponent.concat("creation dialog");
	protected GTTreePath cpWebComponent = cdWebComponent.concat("creation-page-WebComponent");

	protected GTTreePath mdServlet = itServlet.concat("modification dialog");
	protected GTTreePath mpServlet = mdServlet.concat("modification-page-Servlet");

	protected GTTreePath mdWebComponent = itWebComponent.concat("modification dialog");
	protected GTTreePath mpWebComponent = mdWebComponent.concat("modification-page-WebComponent");

	protected GTTreePath viewModel = webAppModel.concat(CadseDefinitionManager.VIEW_MODEL);
	protected GTTreePath vmWebAppList = viewModel.concat("WebAppList");
	protected GTTreePath viWebApp = vmWebAppList.concat("WebApp");
	protected GTTreePath viWebComponent = vmWebAppList.concat("WebComponent");

	protected GTTreePath viLinkHasComp = viWebApp.concat("hasComp");

	protected GTTreePath projectPackage = new GTTreePath("Model.Workspace.WebAppModel");
	protected GTTreePath manifest = projectPackage.concat("META-INF", "MANIFEST.MF");
	protected GTTreePath projectResources = projectPackage.concat("resources");
	protected GTTreePath fileSample1 = projectResources.concat("sample1.java");
	protected GTTreePath fileSample2 = projectResources.concat("sample2.java");
	protected GTTreePath fileSample3 = projectResources.concat("sample3.java");
	protected GTTreePath fileSample4 = projectResources.concat("sample4.java");
	protected GTTreePath fileImport1 = projectResources.concat("imports1.java");
	protected GTTreePath fileImport2 = projectResources.concat("imports2.java");
	protected GTTreePath filePostCompose = projectResources.concat("sample-postCompose.java");
	protected GTTreePath fileInitMethod = projectResources.concat("initMethod.java");
	protected GTTreePath fileInitMethodImport = projectResources.concat("initMethodImport.java");

	protected GTTreePath projectSources = projectPackage.concat("src-gen");
	protected GTTreePath servletManagerClass = projectSources.concat("model.webapp.managers", "ServletManager.java",
			"ServletManager");
	protected GTTreePath servletContentItem = servletManagerClass.concat("ServletContent");
	protected GTTreePath webappManagerClass = projectSources.concat("model.webapp.managers", "WebAppManager.java",
			"WebAppManager");
	protected GTTreePath webappPackage = projectSources.concat("model.webapp");
	protected GTTreePath webappServletSynchro = webappPackage.concat("WebAppServletSynchro.java");

	protected GTTreePath importArchiveFile = new GTTreePath("General", "Archive File");
	protected GTTreePath archivePath = new GTTreePath("/", "resources");

	protected GTView webAppListView = new GTView("WebAppList");

	protected void copyFileIntoClipboard(GTTreePath path) {
		packageExplorerView.findTree().doubleClick(path);
		GTTextEditor editor = new GTTextEditor(path.getDestinationName());
		editor.show();
		GTMenu.clickselectAll();
		GTMenu.clickCopy();
		editor.close();
	}
}
