import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class EvaluationPanel extends JPanel {
    private Image fondo;
    private JTextArea exerciseArea;
    private Map<String, String> exercises;
    private int exerciseIndex = 0;

    public EvaluationPanel(Game game) {
        //Añado la imagen
        fondo = new ImageIcon(getClass().getResource("imagenes/fondo.jpg")).getImage();

        setLayout(new BorderLayout());

        // Inicializar los ejercicios
        exercises = new HashMap<>();
        exercises.put("Completa la palabra: _ppl_", "APPLE");
        exercises.put("Completa las vocales perdidas: _ _ _", "OUI");
        exercises.put("Escoge la vocal correcta: f_ll", "FILL");

        // Área para mostrar los ejercicios
        exerciseArea = new JTextArea(1, 2);
        exerciseArea.setEditable(false);
        exerciseArea.setFont(new Font("Serif", Font.PLAIN, 18));
        exerciseArea.setText(getCurrentExercise());

        // Panel para botones de respuestas
        JPanel answersPanel = new JPanel(new GridLayout(1, 5));
        String[] vowels = {"A", "E", "I", "O", "U"};
        for (String vowel : vowels) {
            JButton vowelButton = new JButton(vowel);
            vowelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkAnswer(vowel);
                }
            });
            answersPanel.add(vowelButton);
        }

        // Botón para pasar al siguiente ejercicio
        JButton nextButton = new JButton("Siguiente Ejercicio");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exerciseIndex++;
                if (exerciseIndex < exercises.size()) {
                    exerciseArea.setText(getCurrentExercise());
                } else {
                    JOptionPane.showMessageDialog(EvaluationPanel.this, "Congratulations! You've completed the evaluation.");
                    game.showPanel("Menu");
                }
            }
        });

        add(new JScrollPane(exerciseArea), BorderLayout.CENTER);
        add(answersPanel, BorderLayout.NORTH);
        add(nextButton, BorderLayout.SOUTH);
    }

    private String getCurrentExercise() {
        if (exercises.isEmpty()) {
            return "No exercises available.";
        }
        return (String) exercises.keySet().toArray()[exerciseIndex];
    }

    private void checkAnswer(String vowel) {
        String exerciseText = exerciseArea.getText();
        String correctAnswer = exercises.get(getCurrentExercise());

        if (correctAnswer != null && correctAnswer.contains(vowel)) {
            // Update the exercise text with the selected vowel
            exerciseText = exerciseText.replaceFirst("_", vowel);
            exerciseArea.setText(exerciseText);

            // Check if the exercise is complete
            if (!exerciseText.contains("_")) {
                JOptionPane.showMessageDialog(this, "Correcto! Vamos al siguiente.");
                exerciseIndex++;
                if (exerciseIndex < exercises.size()) {
                    exerciseArea.setText(getCurrentExercise());
                } else {
                    JOptionPane.showMessageDialog(this, "Felicidades! Completaste la evaluación.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Incorrecto! Intentalo de nuevo.");
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}