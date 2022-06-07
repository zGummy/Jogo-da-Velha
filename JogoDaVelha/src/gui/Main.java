/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;

/**
 *
 * @author Usuário
 */
public class Main extends javax.swing.JFrame {
    
    private boolean jogador1 = true;
    
    private int[] tabuleiro = {0, 0, 0,
                               0, 0, 0,
                               0, 0, 0};
    
    private JButton[] botoes;
    
    private int vencedor = 0;
    
      
    /*    
        0 | 1 | 2
        ---------
        3 | 4 | 5
        ---------
        6 | 7 | 8
    */    
        
    private int[][] jogadasVencedoras = {
        {0,1,2},
        {3,4,5},
        {6,7,8},
        {0,3,6},
        {1,4,7},
        {2,5,8},
        {0,4,8},
        {2,4,6}
    };
    
    
    private List<JogadaDefesa> jogadasDefesa;
    
    

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        botoes = new JButton[9];
        
        botoes[0] = btn01;
        botoes[1] = btn02;
        botoes[2] = btn03;
        
        botoes[3] = btn04;
        botoes[4] = btn05;
        botoes[5] = btn06;
        
        botoes[6] = btn07;
        botoes[7] = btn08;
        botoes[8] = btn09;
        
        jogadasDefesa = new ArrayList<>();
        
        jogadasDefesa.add(new JogadaDefesa(0, 1, 2));
        jogadasDefesa.add(new JogadaDefesa(0, 2, 1));
        jogadasDefesa.add(new JogadaDefesa(1, 2, 0));

        jogadasDefesa.add(new JogadaDefesa(3, 4, 5));        
        jogadasDefesa.add(new JogadaDefesa(3, 5, 4));        
        jogadasDefesa.add(new JogadaDefesa(4, 5, 3));        
        
        jogadasDefesa.add(new JogadaDefesa(6, 7, 8));        
        jogadasDefesa.add(new JogadaDefesa(6, 8, 7));        
        jogadasDefesa.add(new JogadaDefesa(7, 8, 6));

        jogadasDefesa.add(new JogadaDefesa(0, 3, 6));        
        jogadasDefesa.add(new JogadaDefesa(0, 6, 3));        
        jogadasDefesa.add(new JogadaDefesa(3, 6, 0));
        
        jogadasDefesa.add(new JogadaDefesa(1, 4, 7));        
        jogadasDefesa.add(new JogadaDefesa(1, 7, 4));        
        jogadasDefesa.add(new JogadaDefesa(4, 7, 1));
        
        jogadasDefesa.add(new JogadaDefesa(2, 5, 8));        
        jogadasDefesa.add(new JogadaDefesa(2, 8, 5));
        jogadasDefesa.add(new JogadaDefesa(5, 8, 2));
        
        jogadasDefesa.add(new JogadaDefesa(0, 4, 8));
        jogadasDefesa.add(new JogadaDefesa(0, 8, 4));
        jogadasDefesa.add(new JogadaDefesa(4, 8, 0));
        
        jogadasDefesa.add(new JogadaDefesa(2, 4, 6));
        jogadasDefesa.add(new JogadaDefesa(2, 6, 4));
        jogadasDefesa.add(new JogadaDefesa(4, 6, 2));
        
    }
    
    
    private void verificaVencedor() {
        
        for (int i = 0; i < 8; ++i) {
            if ((tabuleiro[jogadasVencedoras[i][0]] != 0) &&
                    (tabuleiro[jogadasVencedoras[i][0]] ==  
                    tabuleiro[jogadasVencedoras[i][1]]) &&
                (tabuleiro[jogadasVencedoras[i][0]] ==  
                    tabuleiro[jogadasVencedoras[i][2]])) {

                vencedor = tabuleiro[jogadasVencedoras[i][0]];
                
                return;
            
            }
        }
        
        // Não houve vencedor. Se não temos mais
        // jogadas -> empate
        boolean casasDisponiveis = false;
        
        for (int i = 0; i < 9; ++i) {
            if (tabuleiro[i] == 0) {
                casasDisponiveis = true;
                break;
            }
        }
        
        if (!casasDisponiveis) {
            lblMensagem.setText("EMPATE!");
        }
        
    }
    
    /*
     * Retorna:
     * 1 : se CPU venceu
     * -1: se PLAYER venceu
     * 0 : se empate
     * 2 : caso contrário
    */
    private int testaVencedor(int[] t) {
        
        for (int i = 0; i < 8; ++i) {
            if ((t[jogadasVencedoras[i][0]] != 0) &&
                    (t[jogadasVencedoras[i][0]] ==  
                    t[jogadasVencedoras[i][1]]) &&
                (t[jogadasVencedoras[i][0]] ==  
                    t[jogadasVencedoras[i][2]])) {

                if (t[jogadasVencedoras[i][0]] == 1) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        
        // Não houve vencedor. Se não temos mais
        // jogadas -> empate
        boolean casasDisponiveis = false;
        
        for (int i = 0; i < 9; ++i) {
            if (t[i] == 0) {
                casasDisponiveis = true;
                break;
            }
        }
        
        if (!casasDisponiveis) {
            return 0;
        }
        
        return 2;
        
    }
    
    /*
     * Retorna as posições vagas do tabuleiro
     */
    public List<Integer> buscaPosicoesVagas(int[] t) {
        
        List<Integer> posicoesVagas = new ArrayList<>();
        
        for (int i = 0; i < 9; ++i) {
            if (t[i] == 0) {
                posicoesVagas.add(i);
            }
        }
        
        return posicoesVagas;
        
    }
        
    
    public void realizaJogada(int indice) {
        
        if (tabuleiro[indice] != 0) {
            return;
        }
        
        if (vencedor != 0) {
            return;
        }
        
        if (jogador1) {
            botoes[indice].setText("X");
            tabuleiro[indice] = 1;
        } else {
            botoes[indice].setText("O");
            tabuleiro[indice] = 2;
        }
        
        verificaVencedor();
        
        jogador1 = !jogador1;
        
        if (vencedor != 0) {
            lblMensagem.setText(
                    String.format("Jogador %d venceu!", 
                            vencedor));
            return;
        }
        
        // Verifica se é a vez do CPU
        if (!jogador1) {
            // cpuJogaPrimeiroVazio();
            // cpuJogaRandom();
            // cpuJogaDefesa();
            cpuJogaMinimax();
        }
        
    }
    
    
    public void cpuJogaPrimeiroVazio() {
        
        for (int i = 0; i < 9; ++i) {
            if (tabuleiro[i] == 0) {
                realizaJogada(i);
                break;
            }
        }
        
    }
    
    
    public void cpuJogaRandom() {
        
        List<Integer> posicoesVagas = new ArrayList<>();
        
        for (int i = 0; i < 9; ++i) {
            if (tabuleiro[i] == 0) {
                posicoesVagas.add(i);
            }
        }
        
        if (!posicoesVagas.isEmpty()) {
            Random gerador = new Random();
            int indice = gerador.nextInt(posicoesVagas.size());
            realizaJogada(posicoesVagas.get(indice));
        }
        
        
    }
    
    
    public void cpuJogaDefesa() {
        
        for (JogadaDefesa j : jogadasDefesa) {
            if ((tabuleiro[j.getPrimeira()] == 2) &&
                    (tabuleiro[j.getSegunda()] == 2)) {
                
                if (tabuleiro[j.getJogada()] == 0) {
                    realizaJogada(j.getJogada());
                    return;
                }
                
            }
        }
        
        for (JogadaDefesa j : jogadasDefesa) {
            if ((tabuleiro[j.getPrimeira()] == 1) &&
                    (tabuleiro[j.getSegunda()] == 1)) {
                
                if (tabuleiro[j.getJogada()] == 0) {
                    realizaJogada(j.getJogada());
                    return;
                }
                
            }
        }
               
        
        // Se não encontrada jogada de defesa
        // joga aleatoriamente
        cpuJogaRandom();
        
        
    }
    
    
    public void imprimeTabuleiro(int[] t) {
        System.out.println("-----------");
        for (int i = 0; i < t.length; ++i) {
            System.out.printf("%d ", t[i]);
            
            if ((i + 1) % 3 == 0)
                System.out.println("");
        }
        
        System.out.println("-----------");
        
    }
    
    
    
    public int minimax(int[] t, int profundidade, boolean cpu) {
        
        int vencedor = testaVencedor(t);
        
        if (vencedor != 2) {
            return vencedor;
        }
        
        if (profundidade == 0) {
            return 0;
        }
        
        if (cpu) {
            int melhorValor = -100;
            
            List<Integer> posicoesVagas = buscaPosicoesVagas(t);
            
            for (Integer i : posicoesVagas) {
                int[] copia = new int[9];
                System.arraycopy(t, 0, copia, 0, 9);
                
                // Simula a jogada
                copia[i] = 2;
                
                // Aplica o minimax à cópia
                int v = minimax(copia, profundidade - 1, false);
                
                if (v > melhorValor) {
                    melhorValor = v;
                }
                
            }
            
            return melhorValor;
            
        } else {
            
            int melhorValor = 100;
            
            List<Integer> posicoesVagas = buscaPosicoesVagas(t);
            
            for (Integer i : posicoesVagas) {
                int[] copia = new int[9];
                System.arraycopy(t, 0, copia, 0, 9);
                
                // Simula a jogada
                copia[i] = 1;
                
                // Aplica o minimax à cópia
                int v = minimax(copia, profundidade - 1, true);
                
                if (v < melhorValor) {
                    melhorValor = v;
                }
                
            }
            
            return melhorValor;
            
        }
        
        
        
    }
    
    
    
    public void cpuJogaMinimax() {
        
        List<Integer> posicoesVagas = buscaPosicoesVagas(tabuleiro);
        
        if (posicoesVagas.isEmpty())
            return;
        
        int melhorValor = -100;
        int melhorJogada = -1;
        
        
        for (Integer i : posicoesVagas) {
            int[] copia = new int[9];
            System.arraycopy(tabuleiro, 0, copia, 0, 9);
            
            copia[i] = 2;
            
            int v = minimax(copia, 5, false);
            
            if (v > melhorValor) {
                melhorValor = v;
                melhorJogada = i;
            }
       
            System.out.printf("Jogada: %d -> valor: %d\n",
                    i, v);
            
        }
        
        System.out.println("--------------");
        
        realizaJogada(melhorJogada);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        btn02 = new javax.swing.JButton();
        btn03 = new javax.swing.JButton();
        btn01 = new javax.swing.JButton();
        btn04 = new javax.swing.JButton();
        btn05 = new javax.swing.JButton();
        btn06 = new javax.swing.JButton();
        btn07 = new javax.swing.JButton();
        btn08 = new javax.swing.JButton();
        btn09 = new javax.swing.JButton();
        lblMensagem = new javax.swing.JLabel();
        btnReiniciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn02.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn02ActionPerformed(evt);
            }
        });

        btn03.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn03ActionPerformed(evt);
            }
        });

        btn01.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn01ActionPerformed(evt);
            }
        });

        btn04.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn04ActionPerformed(evt);
            }
        });

        btn05.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn05ActionPerformed(evt);
            }
        });

        btn06.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn06ActionPerformed(evt);
            }
        });

        btn07.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn07.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn07ActionPerformed(evt);
            }
        });

        btn08.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn08.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn08ActionPerformed(evt);
            }
        });

        btn09.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn09.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn09ActionPerformed(evt);
            }
        });

        lblMensagem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensagem.setText("Boa sorte!");

        btnReiniciar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReiniciar.setText("Reiniciar");
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn07, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn08, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn09, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn04, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn05, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn06, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn01, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn02, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn03, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReiniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn02, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn03, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn01, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn05, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn06, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn04, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn08, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn09, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn07, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReiniciar, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn01ActionPerformed
        realizaJogada(0);
    }//GEN-LAST:event_btn01ActionPerformed

    private void btn02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn02ActionPerformed
        realizaJogada(1);
    }//GEN-LAST:event_btn02ActionPerformed

    private void btn03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn03ActionPerformed
        realizaJogada(2);
    }//GEN-LAST:event_btn03ActionPerformed

    private void btn04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn04ActionPerformed
        realizaJogada(3);
    }//GEN-LAST:event_btn04ActionPerformed

    private void btn05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn05ActionPerformed
        realizaJogada(4);
    }//GEN-LAST:event_btn05ActionPerformed

    private void btn06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn06ActionPerformed
        realizaJogada(5);
    }//GEN-LAST:event_btn06ActionPerformed

    private void btn07ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn07ActionPerformed
        realizaJogada(6);
    }//GEN-LAST:event_btn07ActionPerformed

    private void btn08ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn08ActionPerformed
        realizaJogada(7);
    }//GEN-LAST:event_btn08ActionPerformed

    private void btn09ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn09ActionPerformed
        realizaJogada(8);
    }//GEN-LAST:event_btn09ActionPerformed

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        
        // Reinicia o tabuleiro
        for (int i = 0; i < 9; ++i) {
            tabuleiro[i] = 0;
        }
        
        // Limpa os textos dos botões
        for (int i = 0; i < 9; ++i) {
            botoes[i].setText("");
        }
        
        // Outras reinicializações
        jogador1 = true;
        vencedor = 0;
        lblMensagem.setText("Boa sorte!");
        
    }//GEN-LAST:event_btnReiniciarActionPerformed

    
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn01;
    private javax.swing.JButton btn02;
    private javax.swing.JButton btn03;
    private javax.swing.JButton btn04;
    private javax.swing.JButton btn05;
    private javax.swing.JButton btn06;
    private javax.swing.JButton btn07;
    private javax.swing.JButton btn08;
    private javax.swing.JButton btn09;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMensagem;
    // End of variables declaration//GEN-END:variables
}

