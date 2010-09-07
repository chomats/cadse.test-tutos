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

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicItem;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.Test;

import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gtmenu.GTMenu;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTShell;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Tuto2Part1_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_selection() throws Exception {

		// cadses selection
		shell = new GTCadseShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE, GTPreferences.TIMEOUT);
		shell.selectCadses("Cadse Model.Workspace.WebAppModel");
		shell.capture("image030");
		shell.close();
	}

	@Test
	public void test_some_items() throws Exception {

		/* closing welcome view */
		welcomeView.close();

		/* HelloApp */
		createBasicItem(workspaceView, null, "WebApp", "HelloApp", new GTTreePath("HelloApp"), GTPreferences.TIMEOUT);

		/* test.HelloServlet */
		workspaceView.contextMenuNew(new GTTreePath("HelloApp"), "Servlet").click();
		shell = new GTCadseShell("Servlet");
		shell.findCadseFieldName().typeText("test.HelloServlet");
		shell.findTextWithLabel("className").typeText("HelloServlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.findTextWithLabel("relativeURL").typeText("hello");
		shell.close();

		/* JSP - ignore relativeURL */
		createBasicItem(workspaceView, new GTTreePath("HelloApp"), "JSP", "HelloJSP", null);

		/* MyLibrary */
		createBasicItem(workspaceView, null, "Library", "MyLibrary", new GTTreePath("MyLibrary"));

		/* Screenshot */
		workspaceView.selectNode("HelloApp", "HelloJSP");
		workspaceView.capture("image032");
		workspaceView.show();

		/* Window opening */
		GTMenu.clickShowOtherView();
		GTShell shell = new GTShell(GTMenu.SHOW_VIEW_MENU_ITEM);
		shell.selectNode("WebAppModel", "WebAppList");
		shell.capture("image036");
		shell.close();

		/* Assert view is shown and sets focus into */
		webAppListView.show();
		webAppListView.selectNode("HelloApp", "HelloJSP");
		webAppListView.capture("image038");
	}
}
