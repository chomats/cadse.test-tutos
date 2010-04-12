package fr.imag.adele.cadse.test.tutos.tuto2;

import org.junit.Test;

import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gtmenu.GTMenu;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTTextEditor;
import fr.imag.adele.graphictests.gtworkbench_part.GTView;

public class Tuto2Part4_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_run() throws Exception {
		packageExplorerView.contextMenu(new GTTreePath("HelloApp"), "Refresh").click();
		packageExplorerView.selectNode("HelloApp", "dist", "HelloApp.war");
		packageExplorerView.selectNode("HelloApp", "WEB-INF", "classes", "test", "HelloServlet.class");
		packageExplorerView.selectNode("HelloApp", "WEB-INF", "web.xml");
		packageExplorerView.capture("image122");

		packageExplorerView.contextMenu(new GTTreePath("HelloApp", "WEB-INF", "web.xml"), "Open").click();
		GTTextEditor editor = new GTTextEditor("web.xml");
		editor.showCTab("Source");
		editor.capture("image124");

		GTMenu.clickShowOtherView();
		shell = new GTCadseShell(GTMenu.SHOW_VIEW_MENU_ITEM);
		shell.selectNode("WebApp", "Example Webapp");
		shell.capture("image126");
		shell.close();

		GTView view = new GTView("Example Webapp");
		view.show();
		view.capture("image128");
		view.findButton("Browse").click();

		shell = new GTCadseShell("War File Definition");
		shell.selectNode("HelloApp", "dist", "HelloApp.war");
		shell.capture("image130");
		shell.close();

		view.capture("image132");
	}
}
