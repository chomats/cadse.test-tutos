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
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.propertiesView;

import org.junit.Test;

import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Tuto1Part4_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_feature() throws Exception {

		/* Test init */
		workspaceView.show();
		/* Assert tree has been displayed */
		workspaceView.selectNode(new GTTreePath("ServletAPI"), GTPreferences.TIMEOUT);

		/* ServletAPI deletion */
		deleteBasicItem(workspaceView, new GTTreePath("ServletAPI"));

		/* ServletAPI creation */
		createBasicItem(workspaceView, null, "Library", "ServletAPI", new GTTreePath("ServletAPI"));

		/* hello3Servlet */
		workspaceView.selectNode("Hello3App", "Hello3Servlet");
		propertiesView.showTab("Servlet");
		propertiesView.findButton("Add...").click();

		shell = new GTCadseShell("Select a destination.");
		shell.selectNode("ServletAPI");
		shell.close();

		/* Screenshot */
		packageExplorerView.show();
		packageExplorerView.selectNode(new GTTreePath("Hello3App"), true);
		packageExplorerView.selectNode(new GTTreePath("ServletAPI"), true);
		packageExplorerView.selectNode(new GTTreePath("Hello3App.Hello3Servlet", "Item Dependencies", "ServletAPI"),
				true);
		packageExplorerView.capture("image148");
	}
}
