package fr.imag.adele.cadse.test.interfaces;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;


@RunWith(Suite.class)
@SuiteClasses(value={
	CheckCreationPages_tc.class
})

public class CheckCreationPages_ts {
	public static Test suite() {
		return (Test) new JUnit4TestAdapter(CheckCreationPages_ts.class);
	}
}
