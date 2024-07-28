import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class MinigamePaintPanel extends JPanel {
    private static final int[] GRID_ROWS = {5, 5, 5, 5, 5};
    private static final int[] GRID_COLS = {4, 3, 3, 4, 4};
    private static final String[] VOCALS = {"A", "E", "I", "O", "U"};
    private static final int CELL_SIZE = 100;
    private int level = 0;
    private int correctClicks = 0;
    private int totalClicks = 0;
    private boolean[][] paintedCells;
    private MinigamesPanel parentPanel;
    private JButton nextLevelButton;
    private JLabel feedbackLabel;
    private Image backgroundImage;

    private Set<Point> level1Points = new HashSet<>() {{
        add(new Point(0, 1));
        add(new Point(0, 2));
        add(new Point(1, 0));
        add(new Point(1, 3));
        add(new Point(2, 0));
        add(new Point(2, 3));
        add(new Point(3, 0));
        add(new Point(3, 1));
        add(new Point(3, 2));
        add(new Point(3, 3));
        add(new Point(4, 0));
        add(new Point(4, 3));
    }};

    private Set<Point> level2Points = new HashSet<>() {{
        add(new Point(0, 0));
        add(new Point(0, 1));
        add(new Point(0, 2));
        add(new Point(1, 0));
        add(new Point(2, 0));
        add(new Point(2, 1));
        add(new Point(2, 2));
        add(new Point(3, 0));
        add(new Point(4, 0));
        add(new Point(4, 1));
        add(new Point(4, 2));
    }};

    private Set<Point> level3Points = new HashSet<>() {{
        add(new Point(0, 0));
        add(new Point(0, 1));
        add(new Point(0, 2));
        add(new Point(1, 1));
        add(new Point(2, 1));
        add(new Point(3, 1));
        add(new Point(4, 0));
        add(new Point(4, 1));
        add(new Point(4, 2));
    }};

    private Set<Point> level4Points = new HashSet<>() {{
        add(new Point(0, 0));
        add(new Point(0, 1));
        add(new Point(0, 2));
        add(new Point(0, 3));
        add(new Point(1, 0));
        add(new Point(1, 3));
        add(new Point(2, 0));
        add(new Point(2, 3));
        add(new Point(3, 0));
        add(new Point(3, 3));
        add(new Point(4, 0));
        add(new Point(4, 1));
        add(new Point(4, 2));
        add(new Point(4, 3));
    }};

    private Set<Point> level5Points = new HashSet<>() {{
        add(new Point(0, 0));
        add(new Point(0, 3));
        add(new Point(1, 0));
        add(new Point(1, 3));
        add(new Point(2, 0));
        add(new Point(2, 3));
        add(new Point(3, 0));
        add(new Point(3, 3));
        add(new Point(4, 0));
        add(new Point(4, 1));
        add(new Point(4, 2));
        add(new Point(4, 3));
    }};

    public MinigamePaintPanel(MinigamesPanel parentPanel) {
        this.parentPanel = parentPanel;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(GRID_COLS[level] * CELL_SIZE + 200, GRID_ROWS[level] * CELL_SIZE + 200));
        setBackground(Color.WHITE);
        paintedCells = new boolean[GRID_ROWS[level]][GRID_COLS[level]];

        try {
            backgroundImage = new ImageIcon("background.jpg").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        nextLevelButton = new JButton("Siguiente Nivel");
        nextLevelButton.addActionListener(e -> {
            if (isLevelCorrect()) {
                feedbackLabel.setText("¡Correcto!");
                JOptionPane.showMessageDialog(MinigamePaintPanel.this, "¡Correcto!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                feedbackLabel.setText("Te equivocaste.");
                JOptionPane.showMessageDialog(MinigamePaintPanel.this, "Te equivocaste.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (level < 4) {
                level++;
                resetGrid();
            } else {
                showEvaluation();
            }
        });

        feedbackLabel = new JLabel("Haz clic en la cuadrícula para pintar las celdas.");
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(feedbackLabel);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        controlPanel.add(nextLevelButton);
        controlPanel.setOpaque(false);

        add(controlPanel, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = (e.getY() - 100) / CELL_SIZE;
                int col = (e.getX() - 100) / CELL_SIZE;
                if (row >= 0 && row < GRID_ROWS[level] && col >= 0 && col < GRID_COLS[level] && !paintedCells[row][col]) {
                    paintedCells[row][col] = true;
                    totalClicks++;
                    if (isCorrectCell(row, col)) {
                        correctClicks++;
                    }
                    repaint();
                }
            }
        });
    }

    private boolean isCorrectCell(int row, int col) {
        Set<Point> currentLevelPoints;
        switch (level) {
            case 0:
                currentLevelPoints = level1Points;
                break;
            case 1:
                currentLevelPoints = level2Points;
                break;
            case 2:
                currentLevelPoints = level3Points;
                break;
            case 3:
                currentLevelPoints = level4Points;
                break;
            case 4:
                currentLevelPoints = level5Points;
                break;
            default:
                currentLevelPoints = new HashSet<>();
        }
        return currentLevelPoints.contains(new Point(row, col));
    }

    private boolean isLevelCorrect() {
        Set<Point> currentLevelPoints;
        switch (level) {
            case 0:
                currentLevelPoints = level1Points;
                break;
            case 1:
                currentLevelPoints = level2Points;
                break;
            case 2:
                currentLevelPoints = level3Points;
                break;
            case 3:
                currentLevelPoints = level4Points;
                break;
            case 4:
                currentLevelPoints = level5Points;
                break;
            default:
                currentLevelPoints = new HashSet<>();
        }
        for (Point p : currentLevelPoints) {
            if (!paintedCells[p.x][p.y]) {
                return false;
            }
        }
        int paintedCount = 0;
        for (boolean[] row : paintedCells) {
            for (boolean cell : row) {
                if (cell) {
                    paintedCount++;
                }
            }
        }
        return paintedCount == currentLevelPoints.size();
    }

    private void resetGrid() {
        paintedCells = new boolean[GRID_ROWS[level]][GRID_COLS[level]];
        setPreferredSize(new Dimension(GRID_COLS[level] * CELL_SIZE + 200, GRID_ROWS[level] * CELL_SIZE + 200));
        revalidate();
        repaint();
        feedbackLabel.setText("Haz clic en la cuadrícula para pintar las celdas.");
    }

    private void showEvaluation() {
        double accuracy = ((double) correctClicks / totalClicks) * 100;
        JOptionPane.showMessageDialog(this,
                "Aciertos: " + correctClicks + "\n" +
                "Errores: " + (totalClicks - correctClicks) + "\n" +
                "Porcentaje de efectividad: " + String.format("%.2f", accuracy) + "%",
                "Evaluación",
                JOptionPane.INFORMATION_MESSAGE);
        parentPanel.getGame().showPanel("Evaluation");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        int offsetX = (getWidth() - GRID_COLS[level] * CELL_SIZE) / 2;
        int offsetY = (getHeight() - GRID_ROWS[level] * CELL_SIZE) / 2;

        for (int row = 0; row < GRID_ROWS[level]; row++) {
            for (int col = 0; col < GRID_COLS[level]; col++) {
                if (paintedCells[row][col]) {
                    g.setColor(Color.BLUE);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }
                g.fillRect(offsetX + col * CELL_SIZE, offsetY + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(offsetX + col * CELL_SIZE, offsetY + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
}
