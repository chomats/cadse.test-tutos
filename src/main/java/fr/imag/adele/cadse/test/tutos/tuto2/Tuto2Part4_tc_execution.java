/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.test.tutos.tuto2;

import static fr.imag.adele.graphictests.gtworkbench_part.GTView.packageExplorerView;

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
