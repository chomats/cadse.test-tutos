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

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.test.GTPreferences;

/**
 * Performs the official simple tutorial
 */
public class Tuto1Part4_tc_CADSEg extends TutoTestCase {

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
	public void test_item_content_dependency() throws Exception {

		/* Assert tree has been displayed */
		workspaceView.selectNode(linkUses, GTPreferences.TIMEOUT);
		workspaceView.findTree().collapse();

		workspaceView.selectNode(linkUses);
		workspaceView.capture("image144");

		propertiesView.showTab("LinkType");
		propertiesView.findCadseField(CadseGCST.ITEM_at_QUALIFIED_NAME_).scroll();
		propertiesView.findCadseField(CadseGCST.LINK_TYPE_at_REQUIRE_).check(true);
		propertiesView.capture("image146");

		// Waits until files have been generated.
		bot.sleep(6000);
	}

	@Test
	public void test_zp15_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel);
	}
}
