package Test;

import junit.framework.TestCase;
import applications.*;



public class TestReq4 extends TestCase {
    HandleApplications ha ;
    protected void setUp() throws Exception {
        super.setUp();
        ha = new HandleApplications();
        try{
            ha.addSkills("java", "pascal", "javascript", "sql", "html", "css", "xml", "sw design");
            ha.addPosition("developer", "pascal", "css");
            ha.addPosition("team leader", "pascal", "css");
            ha.addPosition("database expert", "pascal", "sql");
            ha.addPosition("junior developer", "pascal", "css");
            ha.addPosition("senior developer", "pascal", "css");

            ha.addApplicant("albert", "pascal:9,sql:7,css:5");
            ha.addApplicant("bob", "pascal:9,sql:7,xml:5");
            ha.addApplicant("robert", "pascal:9,sql:7,xml:5");
            ha.addApplicant("alfred", "pascal:9,sql:7,css:5");
            ha.addApplicant("ted", "pascal:5,sql:7,css:5");
            ha.addApplicant("mary", "pascal:9,sql:7,css:5");

            ha.enterApplication("albert", "developer");
            ha.enterApplication("bob", "database expert");
            ha.enterApplication("robert", "database expert");
            ha.enterApplication("ted", "junior developer");
            ha.enterApplication("mary", "team leader");

            ha.addPosition("gui designer", "html", "css");
            ha.addApplicant("susan", "pascal:9,html:7,css:8");
            ha.enterApplication("susan", "gui designer");
            ha.setWinner("susan", "gui designer");

        }catch(Exception e){
            fail("no exception must be thrown");
        }
    }

    public void testSetWinner() {
        try {
            int capSum = ha.setWinner("albert", "developer");
            System.out.println(capSum);
        } catch (ApplicationException e) {
            fail("no exception must be thrown");
        }
    }
    public void testSetWinnerEx1() {
        try {
            ha.setWinner("alfred", "senior developer");
            fail("didn't apply for the position");
        } catch (ApplicationException e) {
        }
    }
    public void testSetWinnerEx2() {
        try {
            int capSum = ha.setWinner("bob", "database expert");
            System.out.println(capSum);
            capSum = ha.setWinner("robert", "database expert");
            System.out.println(capSum);
            fail("position already assigned");
        } catch (ApplicationException e) {
        }
    }

    public void testSetWinnerEx3() {
        try {
            int capSum = ha.setWinner("ted", "junior developer");
            System.out.println(capSum);
            fail("insufficient capabilities");
        } catch (ApplicationException e) {
        }
    }

    public void testSetWinnerEx4() {
        try {
            int capSum = ha.setWinner("mary", "team leader");
            System.out.println(capSum);
            assertEquals(14,capSum);
        } catch (ApplicationException e) {
            fail("no exception must be thrown");
        }
    }

    public void testGetWinner() {
        Position pos = ha.getPosition("gui designer");
        String winner = pos.getWinner();
        assertNotNull(winner);
        assertEquals("susan",winner);
    }

}
