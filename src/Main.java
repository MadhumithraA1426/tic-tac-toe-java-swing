import ui.GameFrame;

public class Main {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {

            GameFrame gameFrame =
                    new GameFrame();

            gameFrame.setVisible(true);
        });
    }
}