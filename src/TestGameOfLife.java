import javax.swing.SwingUtilities;

public class TestGameOfLife {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Frame();
            }
        });

    }
}
// invokeLater() allows us to post a "job" to Swing