package Test;

import junit.framework.TestCase;
import applications.*;


public class TestReq2 extends TestCase {
    HandleApplications ha;
    protected void setUp() throws Exception {
        super.setUp();
        ha = new HandleApplications();
        try{
            ha.addSkills("java", "pascal", "javascript", "sql", "html", "css", "xml", "sw design");
            ha.addApplicant("albert", "pascal:9,sql:7");
            ha.addApplicant("ellen", "xml:9,css:8");
        }catch(Exception e){
            fail("no exception must be thrown");
        }
    }

    public void testAddApplicantEx1() {
        try {
            ha.addApplicant("albert", "css:8,xml:7");
            fail("applicant duplicated");
        } catch (ApplicationException e) {
        }
    }
    public void testAddApplicantEx2() {
        try {
            ha.addApplicant("bob", "fortran:8,xml:7");
            fail("undefined skill in addApplicant");
        } catch (ApplicationException e) {
        }
    }
    public void testAddApplicantEx3() {
        try {
            ha.addApplicant("ted", "css:8,xml:12");
            fail("invalid level in addApplicant");
        } catch (ApplicationException e) {
        }
    }
    public void testGetCapabilitiesEx1() {
        try {
            ha.getCapabilities("james");
            fail("applicant undefined in getCapabilities");
        } catch (ApplicationException e) {
        }
    }
    public void testgetCapabilitiesEx2() {
        try {
            String cap = ha.getCapabilities("ellen");
            assertEquals("css:8,xml:9",cap);
        } catch (ApplicationException e) {
        }
    }
}
