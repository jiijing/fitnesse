package fitnesse.junit;

import fitnesse.responders.run.CompositeExecutionLog;
import fitnesse.responders.run.ResultsListener;
import fitnesse.responders.run.TestSummary;
import fitnesse.responders.run.TestSystem;
import fitnesse.wiki.WikiPage;
import fitnesse.wiki.WikiPagePath;

public class PrintTestListener implements ResultsListener{
    @Override
    public void allTestingComplete() throws Exception {
      System.out.println("--complete--");
    }
    @Override
    public void announceNumberTestsToRun(int testsToRun) {
      
    }
    @Override
    public void errorOccured() {
      
    }
    @Override
    public void newTestStarted(WikiPage test, long time) throws Exception {
      
    }
    @Override
    public void setExecutionLogAndTrackingId(String stopResponderId, CompositeExecutionLog log)
        throws Exception {
      
    }
    @Override
    public void testComplete(WikiPage test, TestSummary testSummary) throws Exception {
      System.out.println(new WikiPagePath(test).toString() + " r " + testSummary.right + " w "+ testSummary.wrong + " "+  testSummary.exceptions );
    }
    @Override
    public void testOutputChunk(String output) throws Exception {

    }
    public void testSystemStarted(TestSystem testSystem, String testSystemName, String testRunner)
        throws Exception {
    }    
}
