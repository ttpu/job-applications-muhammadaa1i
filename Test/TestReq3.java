package Test;

import java.util.*;

import junit.framework.TestCase;
import applications.*;



public class TestReq3 extends TestCase {
    HandleApplications ha;
    Position pos = null;
    protected void setUp() throws Exception {
        super.setUp();
        ha = new HandleApplications();
        try{
            ha.addSkills("java", "pascal", "javascript", "sql", "html", "css", "xml", "sw design");
            ha.addPosition("developer", "pascal", "css");
            ha.addPosition("team leader", "pascal", "css");
            ha.addPosition("analyst", "pascal", "sql", "xml");
            pos = ha.getPosition("analyst");
            ha.addApplicant("albert", "pascal:9,sql:7,css:5");
            ha.addApplicant("bob", "pascal:9,sql:7,xml:5");
            ha.addApplicant("james", "pascal:9,sql:7,xml:5");
            ha.addApplicant("robert", "pascal:9,sql:7,xml:5");
            ha.addApplicant("alfred", "pascal:9,sql:7,css:5");

            ha.addApplicant("mary", "pascal:9,sql:7,xml:5");
            ha.addApplicant("ellen", "pascal:8,sql:8,xml:7");
            ha.addApplicant("susan", "pascal:7,sql:7,xml:5");

            ha.enterApplication("albert", "developer");
            ha.enterApplication("mary", "analyst");
            ha.enterApplication("susan", "analyst");
            ha.enterApplication("ellen", "analyst");
        }catch(Exception e){
            fail("no exception must be thrown");
        }
    }

    public void testEnterApplicationEx1() {
        try {
            ha.enterApplication("frank", "developer");
            fail("applicant undefined");
        } catch (ApplicationException e) {
        }
    }
    public void testEnterApplicationEx2() {
        try {
            ha.enterApplication("robert", "gui designer");
            fail("position undefined");
        } catch (ApplicationException e) {
        }
    }
    public void testEnterApplicationEx3() {
        try {
            ha.enterApplication("james", "developer");
            fail("missing capability for position");
        } catch (ApplicationException e) {
        }
    }
    public void testEnterApplicationEx4() {
        try {
            ha.enterApplication("alfred", "team leader");
            ha.enterApplication("alfred", "developer");
            fail("applicant has already applied for a position");
        } catch (ApplicationException e) {
        }
    }
    public void testGetApplicants() {
        List<String> list = pos.getApplicants();
        System.out.println(list);
        assertNotNull(list );
        assertEquals(3,list.size());
    }

    public void testGetApplicantsT2() {
        List<String> list = pos.getApplicants();
        System.out.println(list);
        assertNotNull(list);
        assertEquals(3,list.size());
        assertEquals("ellen",list.get(0));
    }
}
