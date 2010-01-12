package fr.imag.adele.cadse.test.tutos.tuto1;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;


@RunWith(Suite.class)
@SuiteClasses(value={
		Tuto1Part4_tc_execution.class
})

public class Tuto1Part4_ts_execution {
	public static Test suite() {
		return (Test) new JUnit4TestAdapter(Tuto1Part4_ts_execution.class);
	}
}
