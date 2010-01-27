package fr.imag.adele.cadse.test.tutos.common;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaModelMarker;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.graphictests.cadse.gtcadsetree.GTCadseTreeNode;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreeNode;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTView;


public abstract class TutoTestCase extends GTCadseTestCase {

	protected GTTreePath webAppModel = new GTTreePath("WebAppModel");

	protected GTTreePath build_model = webAppModel.concat(CadseDefinitionManager.BUILD_MODEL);
	protected GTTreePath composite = build_model.concat("WebApp-composite");

	protected GTTreePath data_model = webAppModel.concat(CadseDefinitionManager.DATA_MODEL);
	protected GTTreePath it_webApp = data_model.concat("WebApp");
	protected GTTreePath it_library = data_model.concat("Library");
	protected GTTreePath it_webComponent = data_model.concat("WebComponent");
	protected GTTreePath it_jsp = data_model.concat("JSP");
	protected GTTreePath it_servlet = data_model.concat("Servlet");

	protected GTTreePath mapping = webAppModel.concat(CadseDefinitionManager.MAPPING);
	protected GTTreePath mapping_webComponent = mapping.concat("WebComponent-manager");
	protected GTTreePath mapping_webApp = mapping.concat("WebApp-manager");
	protected GTTreePath mapping_jsp = mapping.concat("JSP-manager");
	protected GTTreePath mapping_library = mapping.concat("Library-manager");
	protected GTTreePath mapping_servlet = mapping.concat("Servlet-manager");
	
	protected GTTreePath content_jsp = mapping_jsp.concat("content-item");
	protected GTTreePath content_library = mapping_library.concat("content-item");
	protected GTTreePath content_servlet = mapping_servlet.concat("content-item");
	protected GTTreePath content_webApp = mapping_webApp.concat("content-item");
	
	protected GTTreePath refExporter = mapping_servlet.concat("JavaContentExporter");

	protected GTTreePath attr_hasComp = it_webApp.concat("hasComp");
	protected GTTreePath attr_packageName = it_servlet.concat("packageName");
	protected GTTreePath attr_className = it_servlet.concat("className");
	protected GTTreePath attr_relativeUrl = it_webComponent.concat("relativeURL");

	protected GTTreePath link_uses = it_webComponent.concat("uses");

	protected GTTreePath cd_servlet = it_servlet.concat("creation dialog");
	protected GTTreePath cp_servlet = cd_servlet.concat("creation-page-Servlet");
	
	protected GTTreePath cd_webComponent = it_webComponent.concat("creation dialog");
	protected GTTreePath cp_webComponent = cd_webComponent.concat("creation-page-WebComponent");
	
	protected GTTreePath md_servlet = it_servlet.concat("modification dialog");
	protected GTTreePath mp_servlet = md_servlet.concat("modification-page-Servlet");

	protected GTTreePath md_webComponent = it_webComponent.concat("modification dialog");
	protected GTTreePath mp_webComponent = md_webComponent.concat("modification-page-WebComponent");
	
	protected GTTreePath view_model = webAppModel.concat(CadseDefinitionManager.VIEW_MODEL);
	protected GTTreePath vm_webAppList = view_model.concat("WebAppList");
	protected GTTreePath vi_WebApp = vm_webAppList.concat("WebApp");
	protected GTTreePath vi_WebComponent = vm_webAppList.concat("WebComponent");
	

	protected GTTreePath vi_link_hasComp = vi_WebApp.concat("hasComp");

	protected GTTreePath project_package = new GTTreePath("Model.Workspace.WebAppModel");
	protected GTTreePath manifest = project_package.concat("META-INF", "MANIFEST.MF");
	protected GTTreePath project_resources = project_package.concat("resources");
	protected GTTreePath file_sample1 = project_resources.concat("sample1.java");
	protected GTTreePath file_sample2 = project_resources.concat("sample2.java");
	protected GTTreePath file_sample3 = project_resources.concat("sample3.java");
	protected GTTreePath file_sample4 = project_resources.concat("sample4.java");
	protected GTTreePath file_import1 = project_resources.concat("imports1.java");
	protected GTTreePath file_postCompose = project_resources.concat("sample-postCompose.java");
	
	protected GTTreePath project_sources = project_package.concat("src-gen");
	protected GTTreePath servletManagerClass = project_sources.concat("model.webapp.managers","ServletManager.java", "ServletManager");
	protected GTTreePath servletContentItem = servletManagerClass.concat("ServletContent");
	protected GTTreePath webappManagerClass = project_sources.concat("model.webapp.managers","WebAppManager.java", "WebAppManager");
	protected GTTreePath webapp_package = project_sources.concat("model.webapp");
	protected GTTreePath webapp_servletSynchro = webapp_package.concat("WebAppServletSynchro.java");
	
	protected GTTreePath importArchiveFile = new GTTreePath("General", "Archive File");
	protected GTTreePath archivePath = new GTTreePath("/", "resources");
	
	
	protected GTView webAppListView = new GTView("WebAppList");
	
	
	/**
	 * Check if webAppModel compiles.
	 * 
	 * @throws TimeoutException if WebAppModel does't compiles.
	 */
	public void checkCompilationErrors(GTView view, GTTreePath element)
	throws TimeoutException {

		long timeout=SWTBotPreferences.TIMEOUT;
		long limit = System.currentTimeMillis() + timeout;

		while (true) {
			try {
				checkCompilationErrorsInternal(view, element);
				System.out.println("Compilation check OK");
				return;
			} catch (Throwable e) {
				// do nothing
			}

			if (System.currentTimeMillis() > limit) 
				throw new TimeoutException("Timeout after: " + timeout + " ms. Compilation errors in WebApp.");
			
			bot.sleep(SWTBotPreferences.DEFAULT_POLL_DELAY);
		}
	}
	
	/**
	 * Check if webAppModel compiles. 
	 * 
	 * @throws Exception   if WebAppModel does't compiles.
	 */
	private void checkCompilationErrorsInternal(GTView view, GTTreePath element) 
	throws Exception {
		GTTreeNode node = view.findTree().selectNode(element);
		assertNotNull("cannot find tree node", node);
		Item cadseWEB_AppModel = new GTCadseTreeNode(node).getItem();
		assertNotNull("cannot find item", cadseWEB_AppModel);
		IJavaProject jp = cadseWEB_AppModel.getMainMappingContent(IJavaProject.class);
		assertNotNull("cannot find JavaProject", jp);
		checkError(jp, new NullProgressMonitor()); // throw an exception if compilation errors
	}
	
	/**
	 * Check if the java project given into parameter has no compilation errors.
	 * 
	 * @param jp        a java project
	 * @param monitor   
	 * @throws CoreException
	 */
	public void checkError(IJavaProject jp, IProgressMonitor monitor) throws CoreException {
		jp.getProject().build(IncrementalProjectBuilder.FULL_BUILD, monitor);
		if (!jp.hasBuildState())
			return ;
		
		IMarker[] markers = jp.getProject().findMarkers(IJavaModelMarker.JAVA_MODEL_PROBLEM_MARKER, true, IResource.DEPTH_INFINITE);
		if (markers == null) return ;
		StringBuilder errors = new StringBuilder();
		for (IMarker iMarker : markers) {
			Integer severity = (Integer) iMarker.getAttribute(IMarker.SEVERITY);
			if (severity != null && severity == IMarker.SEVERITY_ERROR) {
				errors.append(iMarker.getAttribute(IMarker.MESSAGE)).append("\n");
			}
		}
		if (errors.length() != 0) {
			fail("compilation error on "+jp.getElementName()+"\n"+errors);
		}
	}
}
