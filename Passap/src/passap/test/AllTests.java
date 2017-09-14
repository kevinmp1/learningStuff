package passap.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Accounts_test.class, DBInterface_test.class, Scrambler_test.class })
public class AllTests {

}
