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
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.deleteBasicItem;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.packageExplorerView;

import org.junit.Test;

import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTTextEditor;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Tuto2Part2_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_run() throws Exception {

		/* Assert tree has been displayed */
		workspaceView.show();
		workspaceView.selectNode(new GTTreePath("HelloApp"), GTPreferences.TIMEOUT);

		/* Delete test.HelloServlet Servlet */
		deleteBasicItem(workspaceView, new GTTreePath("HelloApp", "test.HelloServlet"), GTPreferences.TIMEOUT);

		/* New Servlet */
		workspaceView.contextMenuNew(new GTTreePath("HelloApp"), "Servlet").click();
		shell = new GTCadseShell("Servlet");
		shell.findCadseFieldName().typeText("test.HelloServlet");
		shell.findTextWithLabel("className").typeText("HelloServlet");
		shell.findTextWithLabel("packageName").typeText("test");
		shell.findTextWithLabel("relativeURL").typeText("hello");
		shell.close();

		/* edition */
		packageExplorerView.show();
		GTTreePath path = new GTTreePath("HelloApp.test.HelloServlet", "sources", "test", "HelloServlet.java",
				"HelloServlet");
		packageExplorerView.findTree().doubleClick(path);
		packageExplorerView.capture("image060");
		GTTextEditor editor = new GTTextEditor("HelloServlet.java");
		editor.show();
		editor.capture("image062");
		editor.close();
	}
}
