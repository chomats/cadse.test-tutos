package fr.imag.adele.cadse.test.interfaces;

import java.util.List;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTWorkbenchPart;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;


/**
 * Performs the official simple tutorial
 */
public class CheckCreationPages_tc extends GTCadseTestCase {

	protected final String cadse_name = "my_CADSE";
	protected final String item_type_name = "my_item_type";
	protected GTTreePath cadse_model = new GTTreePath(cadse_name);
	protected GTTreePath build_model = cadse_model.concat(CadseDefinitionManager.BUILD_MODEL);
	protected GTTreePath data_model  = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);
	protected GTTreePath it_mit  = data_model.concat(item_type_name);
	
	/**
	 * Selects CADSEg in the launcher, and closes useless views. 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_selection() throws Exception {

		shell = new GTCadseShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE);
		shell.selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		shell.close();
		welcomeView.close();
	}

	/**
	 * Creates a new CADSE definition and checks if correct fields are displayed in creation page.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_CADSE_definition() throws Exception {

		workspaceView.show();		
		
		workspaceView.contextMenu(null, GTCadseRTConstants.CONTEXTMENU_NEW, GTCadseRTConstants.CONTEXTMENU_NEW_CADSE_DEFINITION).click();
		shell = new GTCadseShell(CadseGCST.CADSE_DEFINITION);
		GTCadseFactory.findCadseWorkbenchPart(shell).findField(CadseGCST.ITEM_at_NAME_).typeText(cadse_name);
		GTCadseFactory.findCadseWorkbenchPart(shell).findField(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_).typeText("model.myCadse");
		String[] attributesCST = GTCadseFactory.findCadseWorkbenchPart(shell).findAttributeConstants();
		shell.close();

		// Checks is all the attributes are displayed 
		String[] expected_attributesCST = {"ITEM_at_NAME_", "CADSE_DEFINITION_at_PACKAGENAME_", "CADSE_lt_EXTENDS"};
		if (attributesCSTEquals(attributesCST, expected_attributesCST) == false)
			throw new WidgetNotFoundException("The workbench part doesn't contains expected attributes.");
	}
	
	/**
	 * Creates a new Item Type and checks if correct fields are displayed in creation page.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_Item_Type() throws Exception {
		// Item Type
		workspaceView.contextMenu(data_model, GTCadseRTConstants.CONTEXTMENU_NEW, "Item type").click();
		shell = new GTCadseShell(CadseGCST.ITEM_TYPE);		
		GTCadseFactory.findCadseWorkbenchPart(shell).findField(CadseGCST.ITEM_at_NAME_).typeText(item_type_name);
		String[] attributesCST = GTCadseFactory.findCadseWorkbenchPart(shell).findAttributeConstants();
		shell.close();
		
		workspaceView.findTree().selectNode(it_mit); /* Assert item has been displayed */
		
		// Checks is all the attributes are displayed
		String[] expected_attributesCST = {"ITEM_at_NAME_", "ITEM_TYPE_lt_SUPER_TYPE", "ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_", "ITEM_TYPE_at_IS_ROOT_ELEMENT_", "ITEM_TYPE_at_HAS_CONTENT_"};
		if (attributesCSTEquals(attributesCST, expected_attributesCST) == false)
			throw new WidgetNotFoundException("The workbench part doesn't contains expected attributes.");
	}
		
	@Test
	public void test_Boolean_Attribute() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		attributeCreationTest("Boolean", CadseGCST.BOOLEAN, expected_creationCST, expected_modifCST);
	}
	
	@Test
	public void test_Double_Attribute() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		attributeCreationTest("Boolean", CadseGCST.BOOLEAN, expected_creationCST, expected_modifCST);
	}
	
	@Test
	public void test_Integer_Attribute() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		attributeCreationTest("Boolean", CadseGCST.BOOLEAN, expected_creationCST, expected_modifCST);
	}
	
	@Test
	public void test_Long_Attribute() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		attributeCreationTest("Boolean", CadseGCST.BOOLEAN, expected_creationCST, expected_modifCST);
	}
	
	@Test
	public void test_String_Attribute() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		attributeCreationTest("Boolean", CadseGCST.BOOLEAN, expected_creationCST, expected_modifCST);
	}
	
	@Test
	public void test_LinkType_Attribute() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "LINK_TYPE_lt_DESTINATION", "LINK_TYPE_at_ANNOTATION_", "LINK_TYPE_at_AGGREGATION_", "LINK_TYPE_at_COMPOSITION_", "LINK_TYPE_at_PART_", "LINK_TYPE_at_REQUIRE_", "LINK_TYPE_at_MAPPING_", "LINK_TYPE_at_GROUP_", "LINK_TYPE_at_HIDDEN_", "LINK_TYPE_at_MIN_", "LINK_TYPE_at_MAX_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_", "LINK_TYPE_at_TWCOUPLED_", "LINK_TYPE_at_TWDEST_EVOL_"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "LINK_TYPE_lt_DESTINATION", "LINK_TYPE_at_ANNOTATION_", "LINK_TYPE_at_AGGREGATION_", "LINK_TYPE_at_COMPOSITION_", "LINK_TYPE_at_PART_", "LINK_TYPE_at_REQUIRE_", "LINK_TYPE_at_MAPPING_", "LINK_TYPE_at_GROUP_", "LINK_TYPE_at_HIDDEN_", "LINK_TYPE_at_MIN_", "LINK_TYPE_at_MAX_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_", "LINK_TYPE_at_TWCOUPLED_", "LINK_TYPE_at_TWDEST_EVOL_"};
		attributeCreationTest("Boolean", CadseGCST.BOOLEAN, expected_creationCST, expected_modifCST);
	}
		
	/**
	 * Attribute creation test.
	 * 
	 * @param attributeName            the name of the attribue as it appears in the contextual new menu
	 * @param itConstant               the CADSEG item type constant
	 * @param expected_creationCST     the expected creation page attributes constants
	 * @param expected_modifCST        the expected modification page attributes constants
	 * 
	 * @throws WidgetNotFoundException the widget not found exception
	 */
	private void attributeCreationTest(String attributeName, ItemType itConstant, String[] expected_creationCST, String[] expected_modifCST)
	throws WidgetNotFoundException {

		// Attribute creation
		workspaceView.contextMenu(it_mit, GTCadseRTConstants.CONTEXTMENU_NEW, attributeName).click();
		GTCadseShell shell = new GTCadseShell(itConstant);
		GTCadseFactory.findCadseWorkbenchPart(shell).findField(CadseGCST.ITEM_at_NAME_).typeText(attributeName);
		String[] creationCST = GTCadseFactory.findCadseWorkbenchPart(shell).findAttributeConstants();
	    System.out.println(getStringDef(shell));
		shell.close();
		workspaceView.findTree().selectNode(it_mit.concat(attributeName)); /* Assert item has been displayed */
		
		// Attribute comparison
		if (attributesCSTEquals(creationCST, expected_creationCST) == false)
			throw new WidgetNotFoundException("The workbench part doesn't contains expected attributes.");
		
		// Property view
		propertiesView.showTab(attributeName);
		String[] modifCST = GTCadseFactory.findCadseWorkbenchPart(propertiesView.findSection(attributeName)).findAttributeConstants();
		
		// Attribute comparison
		if (attributesCSTEquals(modifCST, expected_modifCST) == false)
			throw new WidgetNotFoundException("The workbench part doesn't contains expected attributes.");		
	}
	
	/**
	 * Tests is two string arrays are equal.
	 * 
	 * @param provided   The first string array
	 * @param expected   The second string array
	 * 
	 * @return a boolean
	 */
	private boolean attributesCSTEquals(String[] provided, String[] expected) {
		
		if (provided.length != expected.length)
			return false;
		
		for (int i=0; i< provided.length; i++)
			if (! provided[i].equals(expected[i]))
				return false;	
		
		return true;
	}
		
	/**
	 * Return a java piece of code with the definition of a list of strings 
	 * which ate the constants of fields found in the workbench part given into
	 * parameter. For debug purpose only.
	 * 
	 * @param    wp the workbench part for searching fields 
	 * 
	 * @return   the java code   
	 */
	private String getStringDef(GTWorkbenchPart wp) {
		
		List<IAttributeType<?>> attrlist = GTCadseFactory.findCadseWorkbenchPart(wp).findAttributeDefinition();
				
		StringBuilder sb = new StringBuilder();		
		boolean begin = true;
		
		sb.append("String[] expected_attributesCST = {");
		for (IAttributeType<?> attr : attrlist) {
			if (begin)
				begin = false;
			else
				sb.append(", ");
			sb.append("\"");
			sb.append(attr.getCSTName());
			sb.append("\"");
		}
		sb.append("};");
		
		return sb.toString();
	}
}
