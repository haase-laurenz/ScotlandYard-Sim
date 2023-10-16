import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameGUI {

    private JFrame frame;
    private GameManager gameManager;
    private JTextField inputField;

    public GameGUI() {
        frame = new JFrame("Scotland Yard Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(startButton, BorderLayout.NORTH);
        

        // Hier füge das Bild (JLabel) ein
        ImageIcon mapImage = new ImageIcon("template/scot_yard.png");
        JLabel mapLabel = new JLabel(mapImage);
        frame.getContentPane().add(mapLabel, BorderLayout.CENTER);

        inputField = new JTextField();
        frame.getContentPane().add(inputField, BorderLayout.SOUTH);

        // Ändere die Position und Größe des Textfeldes manuell
        inputField.setPreferredSize(new Dimension(100, 30));
        inputField.setHorizontalAlignment(JTextField.CENTER); // Optional: Zentriere den Text im Textfeld

        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private void startGame() {
        try {
            gameManager = new GameManager();
            gameManager.playGames(1000);

            // Hier kannst du weitere Aktionen im Frontend hinzufügen
            // Zum Beispiel die Anzeige von Informationen aus der GameMap
            // oder die Implementierung einer GUI usw.

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameGUI();
            }
        });
    }
}
