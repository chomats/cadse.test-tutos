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

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.test.GTPreferences;

/**
 * Performs the official simple tutorial
 */
public class Tuto2Part1_tc_CADSEg extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_content_item() throws Exception {
		workspaceView.selectNode(contentServlet, GTPreferences.TIMEOUT);
	}

	/**
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_view() throws Exception {

		/* Assert tree has been displayed */
		workspaceView.selectNode(viewModel, GTPreferences.TIMEOUT);

		workspaceView.findTree().collapse();
		workspaceView.selectNode(viewModel);
		workspaceView.capture("image014");

		/* Creates a new view */
		workspaceView.contextMenuNew(viewModel, CadseGCST.VIEW).click();
		shell = new GTCadseShell(CadseGCST.VIEW);
		shell.findCadseFieldName().typeText("WebAppList");
		shell.capture("image016");
		shell.close();

		/* View configuration */
		workspaceView.selectNode(vmWebAppList);
		workspaceView.capture("image018");

		propertiesView.showTab("View");
		propertiesView.findCadseField("view-item-types").check(true, "WebAppModel", CadseDefinitionManager.DATA_MODEL,
				"WebApp", "hasComp");
		propertiesView.findCadseField("view-item-types").check(true, "WebAppModel", CadseDefinitionManager.DATA_MODEL,
				"WebComponent", "uses");
		propertiesView.findCadseField("view-item-types").check(false, "WebAppModel", CadseDefinitionManager.DATA_MODEL,
				"WebComponent", "uses");
		propertiesView.findCadseField("view-item-types").scroll();
		propertiesView.capture("image020");

		/* Can create item and Can create link check boxes */
		workspaceView.findTree().collapse();
		workspaceView.selectNode(viLinkHasComp);
		workspaceView.capture("image024");
		propertiesView.showTab("View Link");

		propertiesView.findCadseField("can-create-item").check(false);
		propertiesView.findCadseField("can-create-link").check(false);
		propertiesView.capture("image026");
	}

	@Test
	public void test_zp21_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel);
	}
}
