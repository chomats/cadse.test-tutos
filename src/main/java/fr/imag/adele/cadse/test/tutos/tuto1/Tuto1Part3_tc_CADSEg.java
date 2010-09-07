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
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createJavaProjectContentModel;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.test.GTPreferences;

/**
 * Performs the official simple tutorial
 */
public class Tuto1Part3_tc_CADSEg extends TutoTestCase {

	GTCadseShell shell;

	/**
	 * @throws Exception
	 *         the exception
	 */
	@Test
	public void test_item_content() throws Exception {

		/* Assert tree has been displayed */
		workspaceView.selectNode(mappingWebApp, GTPreferences.TIMEOUT);
		workspaceView.findTree().collapse();

		workspaceView.selectNode(mappingWebApp);
		workspaceView.capture("image132");

		workspaceView.contextMenuNew(mappingWebApp, CadseGCST.JAVA_PROJECT_CONTENT_MODEL).click();
		shell = new GTCadseShell(CadseGCST.JAVA_PROJECT_CONTENT_MODEL);
		shell.findCadseField(CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_).check(true);
		shell.capture("image134");
		shell.close();
		workspaceView.selectNode(contentWebApp);

		createJavaProjectContentModel(workspaceView, mappingLibrary, KeyValue.jpcmSrcFolderKv());
		createJavaProjectContentModel(workspaceView, mappingServlet, KeyValue.jpcmSrcFolderKv());

		// For debug purpose
		Item itemContentServlet = workspaceView.findTree().getItem(mappingServlet.concat("content-item"));
		assertNotNull(itemContentServlet);

		KeyValue kv = new KeyValue(CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_, "/${#short-name}.jsp");
		workspaceView.contextMenuNew(mappingJsp, "FileContentModel").click();
		GTCadseShell shell = new GTCadseShell("FileContentModel");
		shell.setValue(kv);
		shell.capture("image135");
		shell.close();
		workspaceView.selectNode(contentJsp);
	}

	@Test
	public void test_zp14_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel);
	}
}
