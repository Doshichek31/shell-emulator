import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShellEmulator extends JFrame {

    private JTextArea outputArea;
    private JTextField inputField;
    private CommandExecutor executor;

    public ShellEmulator() {
        setTitle("VFX - Shell Emulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        outputArea.setText("Welcome to VFS Shell Emulator\n" +
                "Type your commands below\n" +
                "Type 'exit' to quit\n\n");
        JScrollPane scrollPane = new JScrollPane(outputArea);

        inputField = new JTextField();
        inputField.setFont(new Font("Courier New", Font.PLAIN, 12));

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String commandLine = inputField.getText();

                if (!commandLine.isEmpty()) {
                    processCommand(commandLine);
                    inputField.setText("");
                }
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputField, BorderLayout.SOUTH);
        add(mainPanel);

        executor = new CommandExecutor();
        setVisible(true);
    }

    private void processCommand(String commandLine) {
        outputArea.append("> " + commandLine + "\n");

        String[] parts = commandLine.trim().split("\\s+");
        String command = parts[0];

        try {
            String result = executor.execute(command, parts);
            outputArea.append(result + "\n");
        } catch (Exception e) {
            outputArea.append("ERROR: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShellEmulator());
    }
}
