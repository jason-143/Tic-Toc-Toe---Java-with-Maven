/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tic.toc.toe;

import static com.mycompany.tic.toc.toe.TicTocToe.scoreX;
import static com.mycompany.tic.toc.toe.TicTocToe.scoreO;
import static com.mycompany.tic.toc.toe.TicTocToe.winMsg;
import static com.mycompany.tic.toc.toe.TicTocToe.moveCounter;
import static com.mycompany.tic.toc.toe.TicTocToe.lbl;
import static com.mycompany.tic.toc.toe.TicTocToe.isClick;
import static com.mycompany.tic.toc.toe.TicTocToe.SelectedIndex;
import static com.mycompany.tic.toc.toe.TicTocToe.disabled_board;
import static com.mycompany.tic.toc.toe.TicTocToe.log;
import static com.mycompany.tic.toc.toe.TicTocToe.isPlaying;
import static com.mycompany.tic.toc.toe.TicTocToe.tbplayer;
import static com.mycompany.tic.toc.toe.TicTocToe.tbbot;
import static com.mycompany.tic.toc.toe.TicTocToe.btnplay1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author esun
 */
public class TicTocToeClickHandler {

    File win = new File("D:\\Java\\Projects\\tic-toc-toe\\Tic-toc-toe\\src\\main\\resource\\sound\\win.wav");

    public Clip clip_win;
    public int RscoreX = 0;
    public int RscoreO = 0;
    public HashSet dupSet = new HashSet();
    public HashSet<Integer> BotSelectedIndex = new HashSet<>();

    /*
    Board layout
    WB_horizontal(n) - h1
    WB_vertical2(n) - v1
    WB_diagonalLeft - dl
    WB_diagonalRight -dr
    dl     v1  v2  v4    dr
    h1   [ l1, l2, l3 ]
    h2   [ l4, l5, l6 ]
    h3   [ l7, l6, l9 ]
     */
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

    public synchronized void winNotif() {
        Sound_win();
        clearSets();
        btnplay1.setEnabled(true);
        tbplayer.setEnabled(true);
        tbbot.setEnabled(true);
        isPlaying[0] = false;
        disabled_board();
        RscoreX = 0;
        RscoreO = 0;
    }

    public int generateRandomValue() {
        Random random = new Random();
        int dup_container;
        dupSet.clear();
        /*
            to start generating random number for bot
        
            dupSet - hashset from TicTocToeClickHandler class
            SelectedIndex - hashset from main class
            BotSelectedIndex - hashset from TicTocToeClickHandler class
        
        1.   isClick indexes must be add all to hashset(dupSet)
        2.   implement a new hashset(SelectedIndex) to insert the index of label for each player click in L(n) event
        3.   implement another hashset (BotSelectedIndex) to insert the random index that dup_container generate.
        4.   remove the indexs from dupSet that matches the SelectedIndex and BotSelectedIndex indexes
        
         */
        // Add index to hashset
        for (int i = 0; i < isClick.length; i++) {
            dupSet.add(i);
        }
        // Remove indices already clicked
        dupSet.removeAll(SelectedIndex);
        // Convert HashSet to List
        List<Integer> dupList = new ArrayList<>(dupSet);
        dup_container = dupList.get(random.nextInt(dupList.size()));
//        dup_container = random.nextInt(dupSet.size());
        System.out.println("dupList Size " + dupList.size() + " dupList index: " + dupList);
        return dup_container;
    }

    public boolean isGameOnlyStarted() {
        for (Button label : lbl) {
            if (!label.getLabel().isEmpty()) {
                return false;
            }
        }
        // all labels are empty. game has only started
        return true;
    }

    public synchronized void clearSets() {
        SelectedIndex.clear();
        BotSelectedIndex.clear();
        dupSet.clear();
    }

    public synchronized void winCondition() {
        if (WB_horizontal1(lbl)) {
            winNotif();
        } else if (WB_horizontal2(lbl)) {
            winNotif();
        } else if (WB_horizontal3(lbl)) {
            winNotif();
        } else if (WB_vertical1(lbl)) {
            winNotif();
        } else if (WB_vertical2(lbl)) {
            winNotif();
        } else if (WB_vertical3(lbl)) {
            winNotif();
        } else if (WB_diagonalLeft(lbl)) {
            winNotif();
        } else if (WB_diagonalRight(lbl)) {
            winNotif();
        } else {
            winMsg.setText("");
        }
    }

    public synchronized boolean WB_Bot() {
        // add first the random index before it set to the Label text to avoid merging the bot index from use click index.
        // testing phase.
        // implement unit test. (on progress)

        int randomIndex = generateRandomValue();
        //insert random generated index for bot to be remove from dupSet
        BotSelectedIndex.add(randomIndex);
        //remove from dupSet after inserting
        dupSet.removeAll(BotSelectedIndex);
        if (dupSet.size() > 0) {
            if ("X".equals(moveCounter)) {
                lbl[randomIndex].setLabel("O");
                isClick[randomIndex] = true;
                winCondition();
            } else {
                lbl[randomIndex].setLabel("X");
                isClick[randomIndex] = true;
                winCondition();
            }
//            System.out.println("index playable: " + dupSet);
//            System.out.println("bot index - " + BotSelectedIndex);
            log.setText("[ rand: " + randomIndex + " ] [ usr: " + SelectedIndex + " ] index playable: " + dupSet + " " + lbl);
//            System.out.println("dupSet Size " + dupSet.size());
            return true;
        }
        TicTocToe.disabled_board();
        System.out.println("No more move");
        winCondition();
        return false;
    }

    public boolean WB_horizontal1(Button[] lbl) {
        // 0-1-2
        String label_pattern[] = {lbl[0].getLabel(), lbl[1].getLabel(), lbl[2].getLabel(),
            lbl[3].getLabel(), lbl[4].getLabel(), lbl[5].getLabel(),
            lbl[6].getLabel(), lbl[7].getLabel(), lbl[8].getLabel()};

        if (!label_pattern[0].isEmpty() && label_pattern[0].equals(label_pattern[1]) && label_pattern[1].equals(label_pattern[2])) {
            if ("X".equals(label_pattern[0])) {
                RscoreX += 1;
                scoreX.setText("" + RscoreX);
            } else {
                RscoreO += 1;
                scoreO.setText("" + RscoreO);
            }
            winMsg.setText(lbl[0].getLabel() + " win");
            return true;
        }
        return false;
    }

    public boolean WB_horizontal2(Button[] lbl) {
        //  3-4-5
        String label_pattern[] = {lbl[0].getLabel(), lbl[1].getLabel(), lbl[2].getLabel(),
            lbl[3].getLabel(), lbl[4].getLabel(), lbl[5].getLabel(),
            lbl[6].getLabel(), lbl[7].getLabel(), lbl[8].getLabel()};

        if (!label_pattern[3].isEmpty() && label_pattern[3].equals(label_pattern[4]) && label_pattern[4].equals(label_pattern[5])) {
            if ("X".equals(label_pattern[3])) {
                RscoreX = RscoreX + 1;
                scoreX.setText("" + RscoreX);
            } else {
                RscoreO = RscoreO + 1;
                scoreO.setText("" + RscoreO);
            }
            winMsg.setText(lbl[3].getLabel() + " win");
            return true;
        }
        return false;
    }

    public boolean WB_horizontal3(Button[] lbl) {
        //  6-7-8
        String label_pattern[] = {lbl[0].getLabel(), lbl[1].getLabel(), lbl[2].getLabel(),
            lbl[3].getLabel(), lbl[4].getLabel(), lbl[5].getLabel(),
            lbl[6].getLabel(), lbl[7].getLabel(), lbl[8].getLabel()};

        if (!label_pattern[6].isEmpty() && label_pattern[6].equals(label_pattern[7]) && label_pattern[7].equals(label_pattern[8])) {
            if ("X".equals(label_pattern[6])) {
                RscoreX = RscoreX + 1;
                scoreX.setText("" + RscoreX);
            } else {
                RscoreO = RscoreO + 1;
                scoreO.setText("" + RscoreO);
            }
            winMsg.setText(lbl[6].getLabel() + " win");
            return true;
        }
        return false;
    }

    public boolean WB_vertical1(Button[] lbl) {
        //  0-3-6
        String label_pattern[] = {lbl[0].getLabel(), lbl[1].getLabel(), lbl[2].getLabel(),
            lbl[3].getLabel(), lbl[4].getLabel(), lbl[5].getLabel(),
            lbl[6].getLabel(), lbl[7].getLabel(), lbl[8].getLabel()};

        if (!label_pattern[0].isEmpty() && label_pattern[0].equals(label_pattern[3]) && label_pattern[3].equals(label_pattern[6])) {
            if ("X".equals(label_pattern[0])) {
                RscoreX = RscoreX + 1;
                scoreX.setText("" + RscoreX);
            } else {
                RscoreO = RscoreO + 1;
                scoreO.setText("" + RscoreO);
            }
            winMsg.setText(lbl[0].getLabel() + " win");
            return true;
        }
        return false;
    }

    public boolean WB_vertical2(Button[] lbl) {
        //  1-4-7
        String label_pattern[] = {lbl[0].getLabel(), lbl[1].getLabel(), lbl[2].getLabel(),
            lbl[3].getLabel(), lbl[4].getLabel(), lbl[5].getLabel(),
            lbl[6].getLabel(), lbl[7].getLabel(), lbl[8].getLabel()};

        if (!label_pattern[1].isEmpty() && label_pattern[1].equals(label_pattern[4]) && label_pattern[4].equals(label_pattern[7])) {
            if ("X".equals(label_pattern[1])) {
                RscoreX = RscoreX + 1;
                scoreX.setText("" + RscoreX);
            } else {
                RscoreO = RscoreO + 1;
                scoreO.setText("" + RscoreO);
            }
            winMsg.setText(lbl[1].getLabel() + " win");
            return true;
        }
        return false;
    }

    public boolean WB_vertical3(Button[] lbl) {
        // 2-5-8
        String label_pattern[] = {lbl[0].getLabel(), lbl[1].getLabel(), lbl[2].getLabel(),
            lbl[3].getLabel(), lbl[4].getLabel(), lbl[5].getLabel(),
            lbl[6].getLabel(), lbl[7].getLabel(), lbl[8].getLabel()};

        if (!label_pattern[2].isEmpty() && label_pattern[2].equals(label_pattern[5]) && label_pattern[5].equals(label_pattern[8])) {
            if ("X".equals(label_pattern[2])) {
                RscoreX = RscoreX + 1;
                scoreX.setText("" + RscoreX);
            } else {
                RscoreO = RscoreO + 1;
                scoreO.setText("" + RscoreO);
            }
            winMsg.setText(lbl[2].getLabel() + " win");
            return true;
        }
        return false;
    }

    public boolean WB_diagonalLeft(Button[] lbl) {
        // 0-4-8
        String label_pattern[] = {lbl[0].getLabel(), lbl[1].getLabel(), lbl[2].getLabel(),
            lbl[3].getLabel(), lbl[4].getLabel(), lbl[5].getLabel(),
            lbl[6].getLabel(), lbl[7].getLabel(), lbl[8].getLabel()};

        if (!label_pattern[0].isEmpty() && label_pattern[0].equals(label_pattern[4]) && label_pattern[4].equals(label_pattern[8])) {
            if ("X".equals(label_pattern[0])) {
                RscoreX = RscoreX + 1;
                scoreX.setText("" + RscoreX);
            } else {
                RscoreO = RscoreO + 1;
                scoreO.setText("" + RscoreO);
            }
            winMsg.setText(lbl[0].getLabel() + " win");
            return true;
        }
        return false;
    }

    public boolean WB_diagonalRight(Button[] lbl) {
        // 2-4-6
        String label_pattern[] = {lbl[0].getLabel(), lbl[1].getLabel(), lbl[2].getLabel(),
            lbl[3].getLabel(), lbl[4].getLabel(), lbl[5].getLabel(),
            lbl[6].getLabel(), lbl[7].getLabel(), lbl[8].getLabel()};

        if (!label_pattern[2].isEmpty() && label_pattern[2].equals(label_pattern[4]) && label_pattern[4].equals(label_pattern[6])) {
            if ("X".equals(label_pattern[2])) {
                RscoreX = RscoreX + 1;
                scoreX.setText("" + RscoreX);
            } else {
                RscoreO = RscoreO + 1;
                scoreO.setText("" + RscoreO);
            }
            winMsg.setText(lbl[2].getLabel() + " win");
            return true;
        }
        return false;
    }

    public boolean WB_draw(Button[] lbl) {

        String label_pattern[] = {lbl[0].getLabel(), lbl[1].getLabel(), lbl[2].getLabel(),
            lbl[3].getLabel(), lbl[4].getLabel(), lbl[5].getLabel(),
            lbl[6].getLabel(), lbl[7].getLabel(), lbl[8].getLabel()};

//        for (int i = 0; i < lbl.length; i++) {
        if (SelectedIndex.size() == 9) {
            return true;
        }
//        }
//        for (int i = 0; i < lbl.length; i++) {
//           
//            if (!label_pattern[2].isEmpty() && label_pattern[2].equals(label_pattern[4]) && label_pattern[4].equals(label_pattern[6])) {
//                if ("X".equals(label_pattern[2])) {
//                    RscoreX = RscoreX + 1;
//                    scoreX.setText("" + RscoreX);
//                } else {
//                    RscoreO = RscoreO + 1;
//                    scoreO.setText("" + RscoreO);
//                }
//                winMsg.setText(lbl[2].getLabel() + " win");
//                return true;
//            }
//        }
        return false;
    }
}
