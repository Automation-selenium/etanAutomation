package testAutomation.recording;

import java.awt.*;

public class ScreenRecorderFactory {

    public enum MOVIE_TYPE{
        AVI,
        MP4
    }

    private static ScreenRecorderFactory screenRecorderFactory = new ScreenRecorderFactory();

    private ScreenRecorderFactory(){}

    public static ScreenRecorderFactory getScreenRecorderFactory() {
        return screenRecorderFactory;
    }

    public ScreenRecorder getScreenRecorder(MOVIE_TYPE type){
        if(type == MOVIE_TYPE.AVI){
            ScreenRecorder r = new MonteScreenRecorder();
            return r;
        }else if(type == MOVIE_TYPE.MP4){
            try {
                ScreenRecorder r = new XugglerScreenRecorder();
                return r;
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
