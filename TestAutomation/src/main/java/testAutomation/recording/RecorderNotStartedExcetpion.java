package testAutomation.recording;

public class RecorderNotStartedExcetpion extends Exception {

    public RecorderNotStartedExcetpion(String message){
        super(message);
    }

    public RecorderNotStartedExcetpion(String message, Throwable t){
        super(message, t);
    }
}
