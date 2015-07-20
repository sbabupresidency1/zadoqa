
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.babu.zadoqa.util.MailingReport;

public class ProgramTestNG {

    private void testRunner(Map<String, String> testngParams) {
 
        TestNG testNG = new TestNG();

        XmlSuite suite = new XmlSuite();
        suite.setName("Zado QA");
        
        XmlTest test = new XmlTest(suite);
        test.setName("Test 1");
        test.setParameters(testngParams);

        List<XmlClass> classez = new ArrayList<XmlClass>();
        classez.add(new XmlClass("TestNGClass"));

        test.setXmlClasses(classez);

        List<XmlTest> tests = new ArrayList<XmlTest>();
        tests.add(test);

        suite.setTests(tests);

        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);

        testNG.setXmlSuites(suites);

        testNG.run();

    }

    public static void main(String args[]) {

        ProgramTestNG program = new ProgramTestNG();
        Map<String,String> params = new HashMap<String,String>();
        params.put("browser", "chrome");
        program.testRunner(params);
        
        MailingReport.SendMail();
    }

}

