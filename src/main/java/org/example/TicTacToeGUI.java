package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame {
    private Game game;
    private JLabel turnLabel;
    private CellPanel[][] cells = new CellPanel[3][3];

    // משתנים למעקב אחרי הסמן השחור במקלדת
    private int cursorRow = 0;
    private int cursorCol = 0;

    public TicTacToeGUI() {
        // 1. חלונות קופצים לקליטת שמות השחקנים
        String p1Name = JOptionPane.showInputDialog(null, "הכנס את שם שחקן 1 (משחק ב-X):", "הגדרת שחקן 1", JOptionPane.QUESTION_MESSAGE);
        if (p1Name == null || p1Name.trim().isEmpty()) p1Name = "שחקן 1";

        String p2Name = JOptionPane.showInputDialog(null, "הכנס את שם שחקן 2 (משחק ב-O):", "הגדרת שחקן 2", JOptionPane.QUESTION_MESSAGE);
        if (p2Name == null || p2Name.trim().isEmpty()) p2Name = "שחקן 2";

        // יצירת הלוגיקה שלך!
        Player p1 = new Player(p1Name, 'x', 1);
        Player p2 = new Player(p2Name, 'o', -1);
        game = new Game(p1, p2);

        // 2. הגדרות החלון הראשי
        setTitle("משחק איקס עיגול");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // ממקם את החלון באמצע המסך

        // 3. תווית עליונה המציגה של מי התור
        turnLabel = new JLabel("תור השחקן: " + game.getCurrentPlayer().getName(), SwingConstants.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 28));
        turnLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(turnLabel, BorderLayout.NORTH);

        // 4. בניית לוח המשבצות
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new CellPanel(i, j);
                boardPanel.add(cells[i][j]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        // 5. האזנה למקלדת (חיצים ואנטר)
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
            }
        });

        setFocusable(true);
        setVisible(true);
    }

    // ניהול הקשות המקלדת
    private void handleKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                cursorRow = (cursorRow > 0) ? cursorRow - 1 : 2;
                break;
            case KeyEvent.VK_DOWN:
                cursorRow = (cursorRow < 2) ? cursorRow + 1 : 0;
                break;
            case KeyEvent.VK_LEFT:
                cursorCol = (cursorCol > 0) ? cursorCol - 1 : 2;
                break;
            case KeyEvent.VK_RIGHT:
                cursorCol = (cursorCol < 2) ? cursorCol + 1 : 0;
                break;
            case KeyEvent.VK_ENTER:
                attemptPlay(cursorRow, cursorCol);
                break;
        }
        repaint(); // מצייר מחדש כדי שהסמן השחור יזוז למקום החדש
    }

    // ניסיון ביצוע מהלך
    private void attemptPlay(int r, int c) {
        // שומרים את שם השחקן לפני המהלך, כי הפעולה play מעבירה את התור מיד
        String currentPlayerName = game.getCurrentPlayer().getName();

        boolean success = game.play(r, c);

        if (success) {
            repaint(); // מעדכן את הצורות על המסך

            // בדיקות סיום משחק
            if (game.isWin(r, c)) {
                JOptionPane.showMessageDialog(this, "המשחק הסתיים! המנצח הוא: " + currentPlayerName, "ניצחון!", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0); // סגירת האפליקציה
            } else if (game.isTie()) {
                JOptionPane.showMessageDialog(this, "הלוח מלא, המשחק הסתיים בתיקו!", "תיקו", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } else {
                // עדכון התווית העליונה לתור הבא
                turnLabel.setText("תור השחקן: " + game.getCurrentPlayer().getName());
            }
        }
    }

    // מחלקה פנימית שמייצגת כל משבצת בלוח
    private class CellPanel extends JPanel {
        private int row, col;

        public CellPanel(int r, int c) {
            this.row = r;
            this.col = c;
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

            // האזנה ללחיצת עכבר על המשבצת
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    cursorRow = row; // מעדכן את מיקום הסמן
                    cursorCol = col;
                    attemptPlay(row, col);
                    TicTacToeGUI.this.requestFocus(); // מחזיר פוקוס לחלון הראשי עבור המקלדת
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            // החלקת הקווים (Anti-aliasing) למראה יפה יותר
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            // ציור X או O לפי המצב במערך של המחלקה Board
            char symbol = game.getSymbolAt(row, col);
            g2.setStroke(new BasicStroke(8)); // עובי הקווים של הצורות

            if (symbol == 'x') {
                g2.setColor(new Color(50, 150, 250)); // צבע כחלחל
                g2.drawLine(30, 30, width - 30, height - 30);
                g2.drawLine(width - 30, 30, 30, height - 30);
            } else if (symbol == 'o') {
                g2.setColor(new Color(250, 80, 80)); // צבע אדמדם
                g2.drawOval(30, 30, width - 60, height - 60);
            }

            // ציור הסמן השחור (מסגרת עבה) אם זו המשבצת המסומנת
            if (row == cursorRow && col == cursorCol) {
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(6));
                g2.drawRect(3, 3, width - 6, height - 6);
            }
        }
    }

    // הפעלת האפליקציה הגרפית
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGUI());
    }
}