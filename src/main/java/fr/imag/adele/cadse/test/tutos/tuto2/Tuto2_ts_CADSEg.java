package fr.imag.adele.cadse.test.tutos.tuto2;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { Tuto2Part1_tc_CADSEg.class, Tuto2Part2_tc_CADSEg.class, Tuto2Part3_tc_CADSEg.class,
		Tuto2Part4_tc_CADSEg.class })
public class Tuto2_ts_CADSEg {
	public static Test suite() {
		return new JUnit4TestAdapter(Tuto2_ts_CADSEg.class);
	}
}