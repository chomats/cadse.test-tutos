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
package fr.imag.adele.cadse.test.tutos.tuto1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.deleteBasicItem;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.packageExplorerView;

import org.junit.Test;

import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Tuto1Part3_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_delete() throws Exception {

		/* Assert tree has been displayed */
		GTTreePath node = new GTTreePath("HelloApp");
		workspaceView.selectNode(node, GTPreferences.TIMEOUT);

		/* Delete HelloApp */
		workspaceView.contextMenu(node, "Delete HelloApp").click();
		shell = new GTCadseShell(GTCadseRTConstants.DELETE_TITLE);
		shell.capture("image138");
		shell.close();

		/* Delete Hello2App */
		deleteBasicItem(workspaceView, new GTTreePath("Hello2App"));

		/* Create Hello3App */
		createBasicItem(workspaceView, null, "WebApp", "Hello3App", new GTTreePath("Hello3App"));

		/* Create Hello3Servlet */
		workspaceView.contextMenuNew(new GTTreePath("Hello3App"), "Servlet").click();
		shell = new GTCadseShell("Servlet");
		shell.findCadseFieldName().typeText("Hello3Servlet");
		shell.findCadseField("className").typeText("Hello3Servlet");
		shell.findCadseField("packageName").typeText("test");
		shell.findCadseField("relativeURL").typeText("hello3");
		shell.close();

		/* Screenshot */
		packageExplorerView.show();
		packageExplorerView.selectNode("Hello3App", "sources");
		packageExplorerView.selectNode("Hello3App.Hello3Servlet", "sources");
		packageExplorerView.selectNode("ServletAPI", "sources");
		packageExplorerView.selectNode("Hello3App");
		packageExplorerView.capture("image140");
	}
}
