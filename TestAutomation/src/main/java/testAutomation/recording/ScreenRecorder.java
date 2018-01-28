package testAutomation.recording;

import testAutomation.testdata.TestCaseNameManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class ScreenRecorder {

    protected String outputFileName;
    public ScreenRecorder(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

        outputFileName = TestCaseNameManager.getTestName() + formater.format(calendar.getTime());
    }
    public abstract ScreenRecorder startRecording();
    public abstract void stopRecording();
}
