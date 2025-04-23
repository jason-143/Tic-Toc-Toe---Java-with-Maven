/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.tic.toc.toe;

import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Button;
import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

/**
 *
 * @author esun
 */
public class TicTocToe extends javax.swing.JFrame {

    TicTocToeClickHandler TTTCH = new TicTocToeClickHandler();

    //Sound
    public Clip clip_win;
    public static Clip clip_draw;
    public Clip clip_click;
    public Clip clip_menu;
    File draw = new File("D:\\Java\\Projects\\tic-toc-toe\\Tic-toc-toe\\src\\main\\resource\\sound\\draw.wav");
    File click = new File("D:\\Java\\Projects\\tic-toc-toe\\Tic-toc-toe\\src\\main\\resource\\sound\\click.wav");
    File win = new File("D:\\Java\\Projects\\tic-toc-toe\\Tic-toc-toe\\src\\main\\resource\\sound\\win.wav");
    File menu = new File("D:\\Java\\Projects\\tic-toc-toe\\Tic-toc-toe\\src\\main\\resource\\sound\\menu.wav");

    //Bot reference
    public static Button btnplay1;
    public static JToggleButton tbplayer;
    public static JToggleButton tbbot;

    public static String moveCounter;
    public static Button[] lbl;
    public static JLabel scoreX;
    public static JLabel scoreO;
    public static JLabel drawXO;
    public static JLabel winMsg;
    public static JLabel log;
    public static HashSet SelectedIndex = new HashSet();
//    public ImageIcon bg = new ImageIcon("D:\\Java\\Projects\\tic-toc-toe\\Tic-toc-toe\\src\\main\\resource\\sakura_night.gif");

    public static final boolean[] isPlaying = {false};

    //disables on launch
    public static final boolean[] isClick = {false, false, false, false, false, false, false, false, false};

    /**
     * Creates new form TicTocToe
     */
    public TicTocToe() {
        initComponents();
        // Load the icon image
//        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource(""));
//        setIconImage(icon);
        TicTocToe.lbl = new Button[]{L1, L2, L3, L4, L5, L6, L7, L8, L9};
        TicTocToe.moveCounter = tbPlayer.getText(); // default letter is X;
//        Xplayer.setFocusable(false);
//        Oplayer.setFocusable(false);
        btnrestart.setFocusable(false);
//        btnPlay1.setFocusable(true);
        TicTocToe.scoreX = lbl_Xscore;
        TicTocToe.scoreO = lbl_Oscore;
        TicTocToe.winMsg = lblWinMessage;
        TicTocToe.log = gameLog;

        TicTocToe.btnplay1 = btnPlay1;
        TicTocToe.tbplayer = tbPlayer;
        TicTocToe.tbbot = tbBot;

        setTitle("Tic-Tac-Toe");
        setResizable(false);

        for (Button btn : lbl) {
            btn.setFocusable(false);
        }
//        lblgif.setIcon(bg);
    }

//    public class ConfigLoader {
//
//        public static Properties config = new Properties();
//
//        static {
//            try (FileInputStream fis = new FileInputStream("config.properties")) {
//                config.load(fis);
//                System.out.println("Configuration loaded.");
//            } catch (IOException e) {
//                System.err.println("Failed to load config: " + e.getMessage());
//                System.exit(1); // Optional: stop program if config is critical
//            }
//        }
//    }

    public void Sound_win() {

        javax.sound.sampled.AudioInputStream audiostream = null;
        try {
            audiostream = AudioSystem.getAudioInputStream(win);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            clip_win = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            clip_win.open(audiostream);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        clip_win.start();
    }

    public void Sound_draw() {

        javax.sound.sampled.AudioInputStream audiostream = null;
        try {
            audiostream = AudioSystem.getAudioInputStream(draw);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            clip_draw = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            clip_draw.open(audiostream);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        clip_draw.start();
    }

    public void Sound_menu() {

        javax.sound.sampled.AudioInputStream audiostream = null;
        try {
            audiostream = AudioSystem.getAudioInputStream(menu);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            clip_menu = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            clip_menu.open(audiostream);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        clip_menu.start();
    }

    public void Sound_click() {

        javax.sound.sampled.AudioInputStream audiostream = null;
        try {
            audiostream = AudioSystem.getAudioInputStream(click);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            clip_click = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            clip_click.open(audiostream);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TicTocToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        clip_click.start();
    }

//    public int GameScore() {
//        //overwrite the data
//        try (FileWriter writer = new FileWriter("C:\\data\\game_score.json")) {
//            writer.write(json);
//        }
//        //append the data
//        try (FileWriter writer = new FileWriter("C:\\data\\game_score.json")) {
//            writer.write(json);
//        }
//    }
    public void GameReset() {
        synchronized (this) {
            tbPlayer.setEnabled(true);
            tbBot.setEnabled(true);
            isPlaying[0] = false;
            moveCounter = tbPlayer.getText();
            lblWinMessage.setText("");
            lbl_Oscore.setText("--");
            lbl_Xscore.setText("--");
            clearSets();
            enabled_board();
            clear_board();
        }
    }

    public synchronized void winNotif() {
        Sound_win();
        clearSets();
//        if ("X".equals(moveCounter)) {
//            lblWinMessage.setText("O win");
//        } else {
//        lblWinMessage.setText(moveCounter + " win");
//        }
        btnPlay1.setEnabled(true);
        tbPlayer.setEnabled(true);
        tbBot.setEnabled(true);
        isPlaying[0] = false;
        disabled_board();
    }

    public synchronized void clearSets() {
        SelectedIndex.clear();
        TTTCH.BotSelectedIndex.clear();
        TTTCH.dupSet.clear();
//        TTTCH.RscoreX = 0;
//        TTTCH.RscoreO = 0;
    }

    public static synchronized void disabled_board() {
        for (int i = 0; i < isClick.length; i++) {
            isClick[i] = true;
        }
    }

    public static synchronized void enabled_board() {
        for (int i = 0; i < isClick.length; i++) {
            isClick[i] = false;
        }
    }

    public static synchronized void clear_board() {
        for (Button label : lbl) {
            label.setLabel("");
        }
    }

    public synchronized void GamePlayed() {
        tbPlayer.setEnabled(true);
        tbBot.setEnabled(true);
        lblWinMessage.setText("");
        moveCounter = tbPlayer.getText();
        clear_board();
        enabled_board();
    }

    public synchronized void player_click(Button lbl) {
        if (tbBot.isSelected()) { //player vs bot
            if (TTTCH.isGameOnlyStarted()) {
                lbl.setLabel(tbPlayer.getText());
                TTTCH.WB_Bot();
            } else {
                lbl.setLabel(moveCounter);
                winCondition();
                TTTCH.WB_Bot();
            }
        } else { //player vs player
            if (TTTCH.isGameOnlyStarted()) {
                lbl.setLabel(moveCounter);
            } else {
                if ("X".equals(moveCounter)) {
                    lbl.setLabel("O");
                    winCondition();
                } else {
                    lbl.setLabel("X");
                    winCondition();
                }
            }
        }
//        System.out.println("player index: " + SelectedIndex);
        tbPlayer.setEnabled(false);
        tbBot.setEnabled(false);
        isPlaying[0] = true;
        gameLog.setText("[ index played: " + SelectedIndex + " ] [ move: " + lbl.getLabel() + " ]");
    }

    public synchronized void isDraw() {
        Sound_draw();
        disabled_board();
        lblWinMessage.setText("draw");
    }

    public synchronized void winCondition() {
        if (TTTCH.WB_horizontal1(lbl)) {
            winNotif();
        } else if (TTTCH.WB_horizontal2(lbl)) {
            winNotif();
        } else if (TTTCH.WB_horizontal3(lbl)) {
            winNotif();
        } else if (TTTCH.WB_vertical1(lbl)) {
            winNotif();
        } else if (TTTCH.WB_vertical2(lbl)) {
            winNotif();
        } else if (TTTCH.WB_vertical3(lbl)) {
            winNotif();
        } else if (TTTCH.WB_diagonalLeft(lbl)) {
            winNotif();
        } else if (TTTCH.WB_diagonalRight(lbl)) {
            winNotif();
        } //        else if (TTTCH.WB_draw(lbl)) {
        //            isDraw();
        //        } 
        else {
            lblWinMessage.setText("");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainBoard = new javax.swing.JPanel();
        lblWinMessage = new javax.swing.JLabel();
        BoxHolder = new javax.swing.JPanel();
        L9 = new java.awt.Button();
        L8 = new java.awt.Button();
        L7 = new java.awt.Button();
        L4 = new java.awt.Button();
        L5 = new java.awt.Button();
        L6 = new java.awt.Button();
        L1 = new java.awt.Button();
        L2 = new java.awt.Button();
        L3 = new java.awt.Button();
        lbl_win = new javax.swing.JLabel();
        lvl_loss = new javax.swing.JLabel();
        lbl_Oscore = new javax.swing.JLabel();
        lbl_Xscore = new javax.swing.JLabel();
        btnPlay1 = new java.awt.Button();
        btnrestart = new java.awt.Button();
        tbPlayer = new javax.swing.JToggleButton();
        tbBot = new javax.swing.JToggleButton();
        gameLog = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic-Toc-Toe");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 106, 113));
        setBounds(new java.awt.Rectangle(420, 420, 420, 420));
        setSize(new java.awt.Dimension(0, 0));

        MainBoard.setBackground(new java.awt.Color(228, 239, 231));
        MainBoard.setForeground(new java.awt.Color(251, 255, 228));
        MainBoard.setToolTipText("");
        MainBoard.setEnabled(false);
        MainBoard.setPreferredSize(new java.awt.Dimension(420, 560));

        lblWinMessage.setBackground(new java.awt.Color(0, 0, 0));
        lblWinMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblWinMessage.setForeground(new java.awt.Color(128, 203, 196));
        lblWinMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWinMessage.setLabelFor(MainBoard);
        lblWinMessage.setToolTipText("\n");
        lblWinMessage.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblWinMessage.setName(""); // NOI18N

        BoxHolder.setBackground(new java.awt.Color(179, 216, 168));
        BoxHolder.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(179, 216, 168), null));
        BoxHolder.setForeground(new java.awt.Color(255, 255, 255));
        BoxHolder.setAlignmentX(1.0F);
        BoxHolder.setAlignmentY(1.0F);
        BoxHolder.setOpaque(false);
        BoxHolder.setPreferredSize(new java.awt.Dimension(340, 340));

        L9.setBackground(new java.awt.Color(179, 216, 168));
        L9.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        L9.setFont(new java.awt.Font("Segoe UI", 0, 70)); // NOI18N
        L9.setForeground(new java.awt.Color(251, 255, 228));
        L9.setName(""); // NOI18N
        L9.setPreferredSize(new java.awt.Dimension(60, 82));
        L9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L9MouseClicked(evt);
            }
        });

        L8.setBackground(new java.awt.Color(179, 216, 168));
        L8.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        L8.setFont(new java.awt.Font("Segoe UI", 0, 70)); // NOI18N
        L8.setForeground(new java.awt.Color(251, 255, 228));
        L8.setName(""); // NOI18N
        L8.setPreferredSize(new java.awt.Dimension(60, 82));
        L8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L8MouseClicked(evt);
            }
        });

        L7.setBackground(new java.awt.Color(179, 216, 168));
        L7.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        L7.setFont(new java.awt.Font("Segoe UI", 0, 70)); // NOI18N
        L7.setForeground(new java.awt.Color(251, 255, 228));
        L7.setName(""); // NOI18N
        L7.setPreferredSize(new java.awt.Dimension(60, 82));
        L7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L7MouseClicked(evt);
            }
        });

        L4.setBackground(new java.awt.Color(179, 216, 168));
        L4.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        L4.setFont(new java.awt.Font("Segoe UI", 0, 70)); // NOI18N
        L4.setForeground(new java.awt.Color(251, 255, 228));
        L4.setName(""); // NOI18N
        L4.setPreferredSize(new java.awt.Dimension(60, 82));
        L4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L4MouseClicked(evt);
            }
        });

        L5.setBackground(new java.awt.Color(179, 216, 168));
        L5.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        L5.setFont(new java.awt.Font("Segoe UI", 0, 70)); // NOI18N
        L5.setForeground(new java.awt.Color(251, 255, 228));
        L5.setName(""); // NOI18N
        L5.setPreferredSize(new java.awt.Dimension(60, 82));
        L5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L5MouseClicked(evt);
            }
        });

        L6.setBackground(new java.awt.Color(179, 216, 168));
        L6.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        L6.setFont(new java.awt.Font("Segoe UI", 0, 70)); // NOI18N
        L6.setForeground(new java.awt.Color(251, 255, 228));
        L6.setName(""); // NOI18N
        L6.setPreferredSize(new java.awt.Dimension(60, 82));
        L6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L6MouseClicked(evt);
            }
        });

        L1.setBackground(new java.awt.Color(179, 216, 168));
        L1.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        L1.setFont(new java.awt.Font("Segoe UI", 0, 70)); // NOI18N
        L1.setForeground(new java.awt.Color(251, 255, 228));
        L1.setName(""); // NOI18N
        L1.setPreferredSize(new java.awt.Dimension(60, 82));
        L1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L1MouseClicked(evt);
            }
        });

        L2.setBackground(new java.awt.Color(179, 216, 168));
        L2.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        L2.setFont(new java.awt.Font("Segoe UI", 0, 70)); // NOI18N
        L2.setForeground(new java.awt.Color(251, 255, 228));
        L2.setName(""); // NOI18N
        L2.setPreferredSize(new java.awt.Dimension(60, 82));
        L2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L2MouseClicked(evt);
            }
        });

        L3.setBackground(new java.awt.Color(179, 216, 168));
        L3.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        L3.setFont(new java.awt.Font("Segoe UI", 0, 70)); // NOI18N
        L3.setForeground(new java.awt.Color(251, 255, 228));
        L3.setName(""); // NOI18N
        L3.setPreferredSize(new java.awt.Dimension(60, 82));
        L3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BoxHolderLayout = new javax.swing.GroupLayout(BoxHolder);
        BoxHolder.setLayout(BoxHolderLayout);
        BoxHolderLayout.setHorizontalGroup(
            BoxHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BoxHolderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BoxHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BoxHolderLayout.createSequentialGroup()
                        .addComponent(L1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BoxHolderLayout.createSequentialGroup()
                        .addComponent(L4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BoxHolderLayout.createSequentialGroup()
                        .addComponent(L7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BoxHolderLayout.setVerticalGroup(
            BoxHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BoxHolderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BoxHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BoxHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(BoxHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(L9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lbl_win.setBackground(new java.awt.Color(0, 0, 0));
        lbl_win.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_win.setForeground(new java.awt.Color(61, 141, 122));
        lbl_win.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_win.setLabelFor(MainBoard);
        lbl_win.setText("X");
        lbl_win.setToolTipText("\n");
        lbl_win.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_win.setName(""); // NOI18N

        lvl_loss.setBackground(new java.awt.Color(0, 0, 0));
        lvl_loss.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lvl_loss.setForeground(new java.awt.Color(61, 141, 122));
        lvl_loss.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lvl_loss.setLabelFor(MainBoard);
        lvl_loss.setText("O");
        lvl_loss.setToolTipText("\n");
        lvl_loss.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lvl_loss.setName(""); // NOI18N

        lbl_Oscore.setBackground(new java.awt.Color(0, 0, 0));
        lbl_Oscore.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        lbl_Oscore.setForeground(new java.awt.Color(61, 141, 122));
        lbl_Oscore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Oscore.setLabelFor(MainBoard);
        lbl_Oscore.setText("--");
        lbl_Oscore.setToolTipText("\n");
        lbl_Oscore.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lbl_Oscore.setName(""); // NOI18N

        lbl_Xscore.setBackground(new java.awt.Color(0, 0, 0));
        lbl_Xscore.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        lbl_Xscore.setForeground(new java.awt.Color(61, 141, 122));
        lbl_Xscore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Xscore.setLabelFor(MainBoard);
        lbl_Xscore.setText("--");
        lbl_Xscore.setToolTipText("\n");
        lbl_Xscore.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_Xscore.setName(""); // NOI18N

        btnPlay1.setBackground(new java.awt.Color(179, 216, 168));
        btnPlay1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPlay1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPlay1.setForeground(new java.awt.Color(251, 255, 228));
        btnPlay1.setLabel("play");
        btnPlay1.setMaximumSize(new java.awt.Dimension(24, 20));
        btnPlay1.setMinimumSize(new java.awt.Dimension(24, 20));
        btnPlay1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlay1MouseClicked(evt);
            }
        });

        btnrestart.setBackground(new java.awt.Color(179, 216, 168));
        btnrestart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnrestart.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnrestart.setForeground(new java.awt.Color(251, 255, 228));
        btnrestart.setLabel("restart");
        btnrestart.setMaximumSize(new java.awt.Dimension(24, 20));
        btnrestart.setMinimumSize(new java.awt.Dimension(24, 20));
        btnrestart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnrestartMouseClicked(evt);
            }
        });

        tbPlayer.setBackground(new java.awt.Color(179, 216, 168));
        tbPlayer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbPlayer.setForeground(new java.awt.Color(61, 141, 122));
        tbPlayer.setText("X");
        tbPlayer.setToolTipText("X");
        tbPlayer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbPlayer.setContentAreaFilled(false);
        tbPlayer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbPlayer.setFocusPainted(false);
        tbPlayer.setFocusable(false);
        tbPlayer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbPlayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPlayerMouseClicked(evt);
            }
        });

        tbBot.setBackground(new java.awt.Color(179, 216, 168));
        tbBot.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbBot.setForeground(new java.awt.Color(61, 141, 122));
        tbBot.setText("PVB");
        tbBot.setToolTipText("Player vs Bot");
        tbBot.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbBot.setContentAreaFilled(false);
        tbBot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbBot.setFocusPainted(false);
        tbBot.setFocusable(false);
        tbBot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBotMouseClicked(evt);
            }
        });

        gameLog.setBackground(new java.awt.Color(0, 0, 0));
        gameLog.setForeground(new java.awt.Color(51, 51, 51));
        gameLog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameLog.setLabelFor(MainBoard);
        gameLog.setToolTipText("\n");
        gameLog.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        gameLog.setName(""); // NOI18N

        javax.swing.GroupLayout MainBoardLayout = new javax.swing.GroupLayout(MainBoard);
        MainBoard.setLayout(MainBoardLayout);
        MainBoardLayout.setHorizontalGroup(
            MainBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainBoardLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(MainBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(MainBoardLayout.createSequentialGroup()
                        .addComponent(tbPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tbBot, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(btnrestart, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPlay1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MainBoardLayout.createSequentialGroup()
                        .addGroup(MainBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_Xscore, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_win, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblWinMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(MainBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lvl_loss, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Oscore, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(MainBoardLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(MainBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BoxHolder, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                            .addComponent(gameLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        MainBoardLayout.setVerticalGroup(
            MainBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainBoardLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(MainBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnPlay1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnrestart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MainBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tbPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tbBot, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(MainBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(MainBoardLayout.createSequentialGroup()
                        .addComponent(lbl_Oscore, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(lvl_loss, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MainBoardLayout.createSequentialGroup()
                        .addComponent(lbl_Xscore, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_win, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblWinMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BoxHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameLog, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnPlay1.getAccessibleContext().setAccessibleName("btnPlay");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainBoard, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainBoard, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void L1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L1MouseClicked
        synchronized (this) {
            if (!isClick[0]) {
                isClick[0] = true;
                //remove from random index generator before calling the bot method.      
                SelectedIndex.add(0);
                player_click(L1);
                Sound_click();
            }
            TTTCH.dupSet.clear();
            moveCounter = L1.getLabel();
        }
    }//GEN-LAST:event_L1MouseClicked

    private void L2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L2MouseClicked
        synchronized (this) {
            if (!isClick[1]) {
                isClick[1] = true;
                SelectedIndex.add(1);
                player_click(L2);
                Sound_click();
            }
            TTTCH.dupSet.clear();
            moveCounter = L2.getLabel();
        }
    }//GEN-LAST:event_L2MouseClicked

    private void L3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L3MouseClicked
        synchronized (this) {
            if (!isClick[2]) {
                isClick[2] = true;
                SelectedIndex.add(2);
                player_click(L3);
                Sound_click();
            }
            TTTCH.dupSet.clear();
            moveCounter = L3.getLabel();
        }
    }//GEN-LAST:event_L3MouseClicked

    private void L4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L4MouseClicked
        synchronized (this) {
            if (!isClick[3]) {
                isClick[3] = true;
                SelectedIndex.add(3);
                player_click(L4);
                Sound_click();
            }
            TTTCH.dupSet.clear();
            moveCounter = L4.getLabel();
        }
    }//GEN-LAST:event_L4MouseClicked

    private void L5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L5MouseClicked
        synchronized (this) {
            if (!isClick[4]) {
                isClick[4] = true;
                SelectedIndex.add(4);
                player_click(L5);
                Sound_click();
            }
            TTTCH.dupSet.clear();
            moveCounter = L5.getLabel();
        }
    }//GEN-LAST:event_L5MouseClicked

    private void L6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L6MouseClicked
        synchronized (this) {
            if (!isClick[5]) {
                isClick[5] = true;
                SelectedIndex.add(5);
                player_click(L6);
                Sound_click();
            }
            TTTCH.dupSet.clear();
            moveCounter = L6.getLabel();
        }
    }//GEN-LAST:event_L6MouseClicked

    private void L7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L7MouseClicked
        synchronized (this) {
            if (!isClick[6]) {
                isClick[6] = true;
                SelectedIndex.add(6);
                player_click(L7);
                Sound_click();
            }
            TTTCH.dupSet.clear();
            moveCounter = L7.getLabel();
        }
    }//GEN-LAST:event_L7MouseClicked

    private void L8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L8MouseClicked
        synchronized (this) {
            if (!isClick[7]) {
                isClick[7] = true;
                SelectedIndex.add(7);
                player_click(L8);
                Sound_click();
            }
            TTTCH.dupSet.clear();
            moveCounter = L8.getLabel();
        }
    }//GEN-LAST:event_L8MouseClicked

    private void L9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L9MouseClicked
        synchronized (this) {
            if (!isClick[8]) {
                isClick[8] = true;
                SelectedIndex.add(8);
                player_click(L9);
                Sound_click();
            }
            TTTCH.dupSet.clear();
            moveCounter = L9.getLabel();
        }
    }//GEN-LAST:event_L9MouseClicked

    private void tbPlayerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPlayerMouseClicked
        Sound_menu();
        GameReset();
        if (tbPlayer.isSelected()) {
            tbPlayer.setText("O");
            tbPlayer.setToolTipText("O");
            TicTocToe.moveCounter = tbPlayer.getText();
        } else {
            tbPlayer.setText("X");
            tbPlayer.setToolTipText("X");
            TicTocToe.moveCounter = tbPlayer.getText();
        }
    }//GEN-LAST:event_tbPlayerMouseClicked

    private void tbBotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBotMouseClicked
        Sound_menu();
        GameReset();
        if (tbBot.isSelected()) {
            tbBot.setText("PVP");
            tbBot.setToolTipText("Player vs Player");
        } else {
            tbBot.setText("PVB");
            tbBot.setToolTipText("Player vs Bot");
        }
    }//GEN-LAST:event_tbBotMouseClicked

    private void btnrestartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnrestartMouseClicked
        Sound_menu();
        GameReset();
    }//GEN-LAST:event_btnrestartMouseClicked

    private void btnPlay1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlay1MouseClicked
        Sound_menu();
        GamePlayed();
    }//GEN-LAST:event_btnPlay1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TicTocToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicTocToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicTocToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicTocToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TicTocToe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BoxHolder;
    private java.awt.Button L1;
    private java.awt.Button L2;
    private java.awt.Button L3;
    private java.awt.Button L4;
    private java.awt.Button L5;
    private java.awt.Button L6;
    private java.awt.Button L7;
    private java.awt.Button L8;
    private java.awt.Button L9;
    private javax.swing.JPanel MainBoard;
    private java.awt.Button btnPlay1;
    private java.awt.Button btnrestart;
    private javax.swing.JLabel gameLog;
    private javax.swing.JLabel lblWinMessage;
    private javax.swing.JLabel lbl_Oscore;
    private javax.swing.JLabel lbl_Xscore;
    private javax.swing.JLabel lbl_win;
    private javax.swing.JLabel lvl_loss;
    private javax.swing.JToggleButton tbBot;
    private javax.swing.JToggleButton tbPlayer;
    // End of variables declaration//GEN-END:variables
}
