package fr.imag.adele.cadse.test.tutos.tuto1;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.imag.adele.cadse.test.tutos.tuto2.Tuto2_ts_CADSEg;

@RunWith(Suite.class)
@SuiteClasses(value = { Tuto1_ts_CADSEg.class, Tuto2_ts_CADSEg.class })
public class All_ts_CADSEg {
	public static Test suite() {
		return new JUnit4TestAdapter(All_ts_CADSEg.class);
	}

}
