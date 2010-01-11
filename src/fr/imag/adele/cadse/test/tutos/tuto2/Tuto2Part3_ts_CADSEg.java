package fr.imag.adele.cadse.test.tutos.tuto2;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;


@RunWith(Suite.class)
@SuiteClasses(value={
		Tuto2Part3_tc_CADSEg.class
})

public class Tuto2Part3_ts_CADSEg {
	public static Test suite() {
		return (Test) new JUnit4TestAdapter(Tuto2Part3_ts_CADSEg.class);
	}
	
}
