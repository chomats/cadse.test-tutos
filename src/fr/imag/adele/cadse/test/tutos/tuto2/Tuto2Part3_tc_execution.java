package fr.imag.adele.cadse.test.tutos.tuto2;

import java.io.File;

import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.graphictests.cadse.gtcadsetree.GTCadseTreeNode;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.graphictests.gttree.GTTreeNode;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;

public class Tuto2Part3_tc_execution extends TutoTestCase {

	@Test
	public void test_run() throws Exception {

		/* Delete test.HelloServlet Servlet */ 
		workspaceView.findTree().selectNode("HelloApp", "test.HelloServlet").contextMenu("Delete test.HelloServlet").click();
		shell = new GTCadseShell(GTCadseRTConstants.DELETE_TITLE);
		shell.close();
		
		/* New Servlet */
		workspaceView.findTree().selectNode("HelloApp").contextMenu(GTCadseRTConstants.CONTEXTMENU_NEW).menu("Servlet").click();
		shell = new GTCadseShell("WebComponent URL Definition");
		GTCadseFactory.findField(shell, CadseGCST.ITEM_at_NAME_).typeText("test.HelloServlet");
		shell.findTextWithLabel("relativeURL").typeText("hello");
		shell.capture("image065");
		shell.findButton(GTEclipseConstants.NEXT_BUTTON).click();
		shell.findTextWithLabel("className").typeText("HelloServlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.close();
	}
	
	@Test
	public void test_build_path() throws Exception {
	
		packageExplorerView.show();
		packageExplorerView.findTree().selectNode("ServletAPI", "sources");
		packageExplorerView.findTree().selectNode("ServletAPI");
		packageExplorerView.capture("image076");
		
		/* Gets the IJavaProject */
		GTTreeNode node = workspaceView.findTree().selectNode("ServletAPI");
		Item servlet_item = new GTCadseTreeNode(node).getItem();
		IJavaProject jp = servlet_item.getMainMappingContent(IJavaProject.class);

		/* Creates a new entry */
		IClasspathEntry libEntry = JavaCore.newLibraryEntry(new Path(System.getProperty("test.resourcesPath") + File.separator + "servlet-api.jar"), null, null);
		
		/* Add entry to classpath */
		IClasspathEntry[] classpath = jp.getRawClasspath();
		int cpLength = classpath.length;
        IClasspathEntry[] newClasspath = new IClasspathEntry[cpLength + 1];
        System.arraycopy(classpath, 0, newClasspath, 0, cpLength);
        newClasspath[cpLength] = libEntry;
        jp.setRawClasspath(newClasspath, null);
        
        GTTreePath api = new GTTreePath("ServletAPI", "Referenced Libraries", "servlet-api.jar" + " - " + System.getProperty("test.resourcesPath"));
        packageExplorerView.findTree().selectNode(api);
        packageExplorerView.capture("image080");
        
        packageExplorerView.contextMenu(api, "Build Path", "Configure Build Path...").click();
        shell = new GTCadseShell("Properties for ServletAPI");
        
        bot.tabItem("Order and Export").activate();
        bot.table().getTableItem("servlet-api.jar" + " - " + System.getProperty("test.resourcesPath")).select();
        bot.table().getTableItem("servlet-api.jar" + " - " + System.getProperty("test.resourcesPath")).check();
        shell.capture("image082");
        shell.close();
        
        checkCompilationErrors(workspaceView, new GTTreePath("HelloApp", "test.HelloServlet"));
        bot.sleep(2000); // Waits until errors disappear.
        packageExplorerView.show();
        packageExplorerView.capture("image084");
	}	
}
