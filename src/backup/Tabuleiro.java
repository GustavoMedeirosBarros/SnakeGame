package backup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tabuleiro extends JFrame {

    private JPanel painel;
    private JPanel menu;
    private JButton iniciarButton;
    private JButton resetButton;
    private JButton pauseButton;
    private JTextField placarField;
    private String direcao = "direita";
    private long tempoAtualizacao = 100;
    private int incremento = 10;
    private Quadrado obstaculo;
    private int larguraTabuleiro, alturaTabuleiro;
    private int placar = 0;
    private boolean pausado = false;
    private ArrayList<Quadrado> cobra;

    public Tabuleiro() {
        larguraTabuleiro = alturaTabuleiro = 400;

        // Iniciando o arreylist da cobra
        cobra = new ArrayList<>();
        cobra.add(new Quadrado(10, 10, Color.BLACK));
        // Iniciando a primeira posição da arraylist da cobra no centro da tela
        cobra.get(0).x = larguraTabuleiro / 2;
        cobra.get(0).y = alturaTabuleiro / 2;

        // Iniciando obstaculo no centro da tela
        obstaculo = new Quadrado(10, 10, Color.red);
        obstaculo.x = larguraTabuleiro / 2;
        obstaculo.y = alturaTabuleiro / 2;

        setTitle("Jogo da Cobrinha");
        setSize(alturaTabuleiro + 16, larguraTabuleiro + 75);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        menu = new JPanel();
        menu.setLayout(new FlowLayout());

        iniciarButton = new JButton("Iniciar");
        resetButton = new JButton("Reiniciar");
        pauseButton = new JButton("Pausar");
        placarField = new JTextField("Placar: 0");
        placarField.setEditable(false);

        // Aumentar o tamanho do placar pois não cabia numero de 2 digitos
        placarField.setColumns(7);

        menu.add(iniciarButton);
        menu.add(resetButton);
        menu.add(pauseButton);
        menu.add(placarField);

        painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Percorre a arraylist da cobra e pinta cada parte do corpo
                for (Quadrado corpo : cobra) {
                    g.setColor(corpo.cor);
                    g.fillRect(corpo.x, corpo.y, corpo.altura, corpo.largura);
                }

                // Pinta o obstaculo
                g.setColor(obstaculo.cor);
                g.fillRect(obstaculo.x, obstaculo.y, obstaculo.largura, obstaculo.altura);

                // Pinta a borda do tabuleiro
                g.setColor(Color.GREEN);
                g.drawRect(0, 0, larguraTabuleiro - 1, alturaTabuleiro - 1);
            }
        };

        add(menu, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        setVisible(true);

        // ActionListener para o botão Iniciar
        iniciarButton.addActionListener(e -> {
            Iniciar();
            painel.requestFocusInWindow(); // Devolve o foco para o painel
        });

        // ActionListener para o botão Reset
        resetButton.addActionListener(e -> {
            Reiniciar();
        });

        // ActionListener para o botão Pausar
        pauseButton.addActionListener(e -> {
            Pausar();
        });

        painel.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                // Exemplo de uso do campo de Texto placarField
                // placarField.setText("Placar: " + placar++);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        if (!direcao.equals("direita")) {
                            direcao = "esquerda";
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        if (!direcao.equals("esquerda")) {
                            direcao = "direita";
                        }
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        if (!direcao.equals("baixo")) {
                            direcao = "cima";
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        if (!direcao.equals("cima")) {
                            direcao = "baixo";
                        }
                        break;
                }
            }
        });
        painel.setFocusable(true);
        painel.requestFocusInWindow();
    }

    private void Iniciar() {
        pausado = false;

        new Thread(() -> {
            while (!pausado) {
                try {
                    Thread.sleep(tempoAtualizacao);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Inicia o jogo
                MovimentoCobra();
                VerificarColisoes();
                painel.repaint();
            }
        }).start();
    }

    public void MovimentoCobra() {
        // Pega o indice 0 da cobra e seta como cabeca
        Quadrado cabeca = cobra.get(0);
        int cabecaX = cabeca.x;
        int cabecaY = cabeca.y;

        switch (direcao) {
            case "esquerda":
                // Move 10 pixels para a esquerda
                cabecaX -= incremento;
                break;
            case "direita":
                // Move 10 pixels para a direita
                cabecaX += incremento;
                break;
            case "cima":
                // Move 10 pixels para cima
                cabecaY -= incremento;
                break;
            case "baixo":
                // Move 10 pixels para baixo
                cabecaY += incremento;
                break;
        }

        // Cria uma nova parte do corpo da cobra
        Quadrado novoCorpo = new Quadrado(10, 10, Color.BLACK);
        novoCorpo.x = cabecaX;
        novoCorpo.y = cabecaY;

        // Cria uma nova parte do corpo da cobra no começo da lista
        cobra.add(0, novoCorpo);

        // Verifica se a nova cabeça da cobra colidiu com o obstáculo
        if (novoCorpo.x == obstaculo.x && novoCorpo.y == obstaculo.y) {
            // Se a cobra colidir com o obstaculo incrementa o placar cria um novo
            // obstaculo em uma posição aleatoria do tabuleito e aumenta a velocidade do
            // jogo
            placar++;
            obstaculo.x = (int) (Math.random() * ((larguraTabuleiro / 10 - 1) * 10));
            obstaculo.y = (int) (Math.random() * ((alturaTabuleiro / 10 - 1) * 10));
            tempoAtualizacao = tempoAtualizacao - 2;
        } else {
            // Caso não colida remove a ultima parte da cobra
            cobra.remove(cobra.size() - 1);
        }
        // Atualiza o placar
        placarField.setText("Placar: " + placar);

        System.out.println(obstaculo.x);
        System.out.println(obstaculo.y);
    }

    private void VerificarColisoes() {
        // A cabeça da cobra
        Quadrado cabeca = cobra.get(0);

        // Percorre a arraylist da cobra
        for (int i = 1; i < cobra.size(); i++) {
            Quadrado corpo = cobra.get(i);
            // Se a cabeça da cobra esta na mesma posição do corpo o jogo reinicia
            if (cabeca.x == corpo.x && cabeca.y == corpo.y) {
                JOptionPane.showMessageDialog(this, "Fim de jogo! Colidiu com o corpo.", "Fim de Jogo",
                        JOptionPane.INFORMATION_MESSAGE);
                Reiniciar();
            }
        }
        // Se a cabeça da cobra ultrapassar as laterais do tabuleiro o jogo reinicia
        if (cabeca.x < 0 || cabeca.x >= larguraTabuleiro || cabeca.y < 0 || cabeca.y >= alturaTabuleiro) {
            JOptionPane.showMessageDialog(this, "Fim de jogo! Colidiu com a borda.", "Fim de Jogo",
                    JOptionPane.INFORMATION_MESSAGE);
            Reiniciar();
        }
    }

    private void Reiniciar() {
        // Adicione aqui a lógica para reiniciar o jogo
        tempoAtualizacao = 100;

        // Reseta a arraylist da cobra uma no meio da tela
        cobra.clear();
        cobra.add(new Quadrado(10, 10, Color.BLACK));
        cobra.get(0).x = larguraTabuleiro / 2;
        cobra.get(0).y = alturaTabuleiro / 2;

        // Reseta o obstaculo para o meio da tela
        obstaculo = new Quadrado(10, 10, Color.red);
        obstaculo.x = larguraTabuleiro / 2;
        obstaculo.y = alturaTabuleiro / 2;

        placar = 0;
        direcao = "direita";
        pausado = false;
        placarField.setText("Placar: " + placar);
        JOptionPane.showMessageDialog(this, "Jogo Reiniciado!", "Reset", JOptionPane.INFORMATION_MESSAGE);
    }

    private void Pausar() {
        // Interrompe o while(!pausado) do método Iniciar() pausando o jogo.
        pausado = !pausado;
        JOptionPane.showMessageDialog(this, "Jogo Pausado!", "Pause", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new Tabuleiro();
    }
}
