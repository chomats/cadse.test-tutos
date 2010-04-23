package fr.imag.adele.cadse.test.tutos.tuto2;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory.findCadseField;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.deleteBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import java.io.File;

import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadsetree.GTCadseTree;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Tuto2Part3_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_run() throws Exception {

		/* Delete test.HelloServlet Servlet */
		deleteBasicItem(workspaceView, new GTTreePath("HelloApp", "test.HelloServlet"), GTPreferences.TIMEOUT);

		/* New Servlet */
		workspaceView.contextMenuNew(new GTTreePath("HelloApp"), "Servlet").click();
		shell = new GTCadseShell("Servlet");
		findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText("test.HelloServlet");
		shell.findTextWithLabel("className").typeText("HelloServlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.findTextWithLabel("relativeURL").typeText("hello");
		shell.capture("image065");
		shell.close();
	}

	@Test
	public void test_build_path() throws Exception {

		packageExplorerView.show();
		packageExplorerView.selectNode("ServletAPI", "sources");
		packageExplorerView.selectNode("ServletAPI");
		packageExplorerView.capture("image076");

		/* Gets the IJavaProject */
		GTCadseTree cadseTree = workspaceView.findTree();
		Item servlet_item = cadseTree.getItem(new GTTreePath("ServletAPI"));
		IJavaProject jp = servlet_item.getMainMappingContent(IJavaProject.class);

		/* Creates a new entry */
		IClasspathEntry libEntry = JavaCore.newLibraryEntry(new Path(System.getProperty("test.resourcesPath")
				+ File.separator + "servlet-api.jar"), null, null);

		/* Add entry to classpath */
		IClasspathEntry[] classpath = jp.getRawClasspath();
		int cpLength = classpath.length;
		IClasspathEntry[] newClasspath = new IClasspathEntry[cpLength + 1];
		System.arraycopy(classpath, 0, newClasspath, 0, cpLength);
		newClasspath[cpLength] = libEntry;
		jp.setRawClasspath(newClasspath, null);

		GTTreePath api = new GTTreePath("ServletAPI", "Referenced Libraries", "servlet-api.jar" + " - "
				+ System.getProperty("test.resourcesPath"));
		packageExplorerView.selectNode(api);
		packageExplorerView.capture("image080");

		packageExplorerView.contextMenu(api, "Build Path", "Configure Build Path...").click();
		shell = new GTCadseShell("Properties for ServletAPI");

		bot.tabItem("Order and Export").activate();
		bot.table().getTableItem("servlet-api.jar" + " - " + System.getProperty("test.resourcesPath")).select();
		bot.table().getTableItem("servlet-api.jar" + " - " + System.getProperty("test.resourcesPath")).check();
		shell.capture("image082");
		shell.close();

		checkCompilationErrors(workspaceView, new GTTreePath("HelloApp", "test.HelloServlet"), GTPreferences.TIMEOUT);
		bot.sleep(2000); // Waits until errors disappear.
		packageExplorerView.show();
		packageExplorerView.capture("image084");
	}
}
