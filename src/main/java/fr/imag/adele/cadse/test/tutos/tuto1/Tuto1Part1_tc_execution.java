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

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;
import org.junit.Test;

import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.fede.workspace.si.persistence.Persistence;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Tuto1Part1_tc_execution extends TutoTestCase {

	GTCadseShell shell;

	@Test
	public void test_selection() throws Exception {

		// cadses selection
		shell = new GTCadseShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE, GTPreferences.TIMEOUT);
		shell.selectCadses("Cadse Model.Workspace.WebAppModel");
		shell.capture("image080");
		shell.close();
	}

	@Test
	public void test_feature() throws Exception {

		workspaceView.contextMenuNew("WebApp").click();
		shell = new GTCadseShell("WebApp");
		shell.findCadseFieldName().typeText("HelloApp");
		shell.capture("image082");
		shell.close();
	}

	@Test
	public void test_check_persistence_id() throws IOException, ClassNotFoundException {
		Location instanceLoc = Platform.getInstanceLocation();
		File location = new File(instanceLoc.getURL().getFile());
		Persistence.loadID_6(location.getAbsolutePath());
	}
}
