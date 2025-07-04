public class TestSound {
    public static void main(String[] args) {
        SoundUtils.playSound("D:/QuizApplication/icons/timeout.wav");

        try {
            Thread.sleep(2000); // wait 2 seconds so sound plays fully
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
