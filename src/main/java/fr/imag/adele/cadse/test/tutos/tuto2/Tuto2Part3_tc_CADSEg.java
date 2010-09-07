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
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.packageExplorerView;

import org.junit.Test;

import fr.imag.adele.cadse.test.tutos.common.TutoTestCase;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods;
import fr.imag.adele.graphictests.gtmenu.GTMenu;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTTextEditor;
import fr.imag.adele.graphictests.test.GTEclipseConstants;

/**
 * Performs the official simple tutorial
 */
public class Tuto2Part3_tc_CADSEg extends TutoTestCase {

	GTCadseShell shell;

	/**
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_item_creation_process_customization() throws Exception {

		// Copying content into clipboard
		copyFileIntoClipboard(fileSample2);

		// New Class
		packageExplorerView.selectNode(webappPackage);
		GTMenu.clickItem(GTMenu.FILE_MENU, "New", "Class");
		shell = new GTCadseShell("New Java Class");
		shell.findTextWithLabel("Name:").typeText("WebAppServletSynchro");
		shell.close();
		packageExplorerView.findTree().doubleClick(webappServletSynchro);

		// Paste
		GTTextEditor editor = new GTTextEditor("WebAppServletSynchro.java");
		GTMenu.clickselectAll();
		GTMenu.clickPaste();
		editor.save();

		editor = GTCadseHelperMethods.addImportOnManifest("Model.Workspace.WebAppModel", "fr.imag.adele.cadse.core.transaction",
				"fr.imag.adele.cadse.core.transaction.delta");
		editor.capture("image064");

		// Adds imports
		// in fact, we have type them, so we don't need to add them

		// Before Init(), need to implement InitAction
		packageExplorerView.findTree().doubleClick(servletManagerClass);
		editor = new GTTextEditor("ServletManager.java");
		editor.find("{");
		editor.typeText("implements InitAction {");
		editor.save();
		editor.capture("image070");

		// Quickfix
		copyFileIntoClipboard(fileImport2);
		editor.navigateTo(2, 0);
		GTMenu.clickPaste();
		editor.save();

		// Copying content into clipboard
		copyFileIntoClipboard(fileInitMethod);

		// Init() method
		editor = new GTTextEditor("ServletManager.java");
		editor.find("implements InitAction {");
		GTMenu.clickPaste();
		editor.save();

		// Quickfix
		copyFileIntoClipboard(fileInitMethodImport);
		editor.navigateTo(2, 0);
		GTMenu.clickPaste();
		editor.save();
	}

	

	@Test
	public void test_zp23_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, webAppModel);
	}
}
