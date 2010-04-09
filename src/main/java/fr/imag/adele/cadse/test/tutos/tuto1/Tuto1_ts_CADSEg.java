package fr.imag.adele.cadse.test.tutos.tuto1;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { Tuto1Part1_tc_CADSEg.class, Tuto1Part2_tc_CADSEg.class, Tuto1Part3_tc_CADSEg.class,
		Tuto1Part4_tc_CADSEg.class })
public class Tuto1_ts_CADSEg {
	public static Test suite() {
		return new JUnit4TestAdapter(Tuto1_ts_CADSEg.class);
	}
}