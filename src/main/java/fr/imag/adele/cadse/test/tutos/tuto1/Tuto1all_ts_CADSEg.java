package fr.imag.adele.cadse.test.tutos.tuto1;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;


@RunWith(Suite.class)
@SuiteClasses(value={
		Tuto1Part1_tc_CADSEg.class,
		Tuto1Part2_tc_CADSEg.class,
		Tuto1Part3_tc_CADSEg.class,
		Tuto1Part4_tc_CADSEg.class,
		Tuto1Part5_tc_CADSEg.class
})

public class Tuto1all_ts_CADSEg {
	public static Test suite() {
		return (Test) new JUnit4TestAdapter(Tuto1all_ts_CADSEg.class);
	}
	
}
