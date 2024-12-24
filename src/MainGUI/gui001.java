package MainGUI;


import javax.swing.*;
import javafx.embed.swing.JFXPanel;
import java.awt.*;
public class gui001 extends JFrame {

    public gui001() {
        setTitle("Flight Computer Interface");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(java.awt.Color.DARK_GRAY);
        headerPanel.setPreferredSize(new Dimension(1200, 40));
        add(headerPanel, BorderLayout.NORTH);

        // Main panels
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setBackground(java.awt.Color.BLACK);

        // Left: Graphs Panel
        JPanel graphsPanel = createGraphsPanel();
        mainPanel.add(graphsPanel);

        // Right: 3D Visualization
        JPanel visualizationPanel = create3DVisualizationPanel();
        mainPanel.add(visualizationPanel);

        add(mainPanel, BorderLayout.CENTER);

        // Footer panel
        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel create3DVisualizationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(java.awt.Color.BLACK);

        JLabel titleLabel = new JLabel("3D Flight Computer Visualization", SwingConstants.CENTER);
        titleLabel.setForeground(java.awt.Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Add JavaFX 3D content
        JFXPanel fxPanel = mediafx.create3DAxisPanel();

        panel.add(fxPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createGraphsPanel() {
        JPanel graphsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        graphsPanel.setBackground(java.awt.Color.BLACK);
        graphsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        graphsPanel.add(createGraph("Sensor Data 1"));
        graphsPanel.add(createGraph("Sensor Data 2"));
        graphsPanel.add(createGraph("Sensor Data 3"));
        graphsPanel.add(createGraph("Sensor Data 4"));

        return graphsPanel;
    }

    private JPanel createGraph(String title) {
        JPanel graphPanel = new JPanel();
        graphPanel.setBackground(java.awt.Color.DARK_GRAY);
        graphPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setForeground(java.awt.Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel placeholder = new JLabel("Graph Area", SwingConstants.CENTER);
        placeholder.setForeground(java.awt.Color.RED);

        graphPanel.add(titleLabel, BorderLayout.NORTH);
        graphPanel.add(placeholder, BorderLayout.CENTER);

        return graphPanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        footerPanel.setBackground(java.awt.Color.BLACK);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        footerPanel.add(createStatPanel("Battery Voltage", "28.52"));
        footerPanel.add(createStatPanel("Current", "4.64"));
        footerPanel.add(createStatPanel("Pitch", "12°"));
        footerPanel.add(createStatPanel("Yaw", "25°"));
        footerPanel.add(createStatPanel("Roll", "10°"));
        footerPanel.add(createStatPanel("Temperature", "35°C"));
        footerPanel.add(createStatPanel("Altitude", "1200m"));
        footerPanel.add(createStatPanel("Velocity", "250 m/s"));

        return footerPanel;
    }

    private JPanel createStatPanel(String label, String value) {
        JPanel statPanel = new JPanel(new BorderLayout());
        statPanel.setBackground(java.awt.Color.DARK_GRAY);
        statPanel.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED, 2));

        JLabel statLabel = new JLabel(label, SwingConstants.CENTER);
        statLabel.setForeground(java.awt.Color.WHITE);
        statLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel statValue = new JLabel(value, SwingConstants.CENTER);
        statValue.setForeground(java.awt.Color.GREEN);
        statValue.setFont(new Font("Arial", Font.BOLD, 14));

        statPanel.add(statLabel, BorderLayout.NORTH);
        statPanel.add(statValue, BorderLayout.CENTER);

        return statPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            gui001 dashboard = new gui001();
            dashboard.setVisible(true);
        });
    }
}
