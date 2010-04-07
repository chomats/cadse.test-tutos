package fr.imag.adele.cadse.test.tutos.tuto2;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part1_tc_CADSEg;
import fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part2_tc_CADSEg;
import fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part3_tc_CADSEg;
import fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part4_tc_CADSEg;

@RunWith(Suite.class)
@SuiteClasses(value = { Tuto1Part1_tc_CADSEg.class, Tuto1Part2_tc_CADSEg.class, Tuto1Part3_tc_CADSEg.class,
		Tuto1Part4_tc_CADSEg.class, Tuto2Part1_tc_CADSEg.class })
public class Tuto2Part1_ts_CADSEg {
	public static Test suite() {
		return new JUnit4TestAdapter(Tuto2Part1_ts_CADSEg.class);
	}

}
