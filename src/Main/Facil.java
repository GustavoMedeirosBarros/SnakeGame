package Main;

import Entidades.Cobra;
import Entidades.Maca;
import Entidades.Quadrado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Facil extends JFrame {
    private Cobra cobra;
    private Maca maca;
    private int larguraTabuleiro, alturaTabuleiro;

    private JPanel painel;
    private JTextField placarField;
    private JPanel menu;
    private JButton iniciarButton;
    private JButton resetButton;
    private JButton pauseButton;

    public Facil() {
        maca = new Maca();
        cobra = new Cobra(maca); // Passa a referência da maçã para a cobra
        larguraTabuleiro = alturaTabuleiro = 400;

        setTitle("Jogo da Cobrinha");
        setSize(larguraTabuleiro + 16, alturaTabuleiro + 75);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        menu = new JPanel();
        menu.setLayout(new FlowLayout());

        iniciarButton = new JButton("Iniciar");
        resetButton = new JButton("Reiniciar");
        pauseButton = new JButton("Pausar");
        placarField = new JTextField("Placar: 0");
        placarField.setEditable(false);
        placarField.setColumns(7);

        menu.add(iniciarButton);
        menu.add(resetButton);
        menu.add(pauseButton);
        menu.add(placarField);

        painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Desenha a cobra
                for (Quadrado quadrado : cobra.getCorpo()) {
                    g.setColor(quadrado.getCor());
                    g.fillRect(quadrado.getX(), quadrado.getY(), quadrado.getLargura(), quadrado.getAltura());
                }

                // Desenha a maçã usando o método de desenhar da classe Maca
                maca.desenharMaca(g);

                // Pinta a borda do tabuleiro
                g.setColor(Color.GREEN);
                g.drawRect(0, 0, larguraTabuleiro - 1, alturaTabuleiro - 1);
            }
        };

        iniciarButton.addActionListener(e -> {
            Iniciar();
            painel.requestFocusInWindow(); // Devolve o foco para o painel
        });

        // ActionListener para o botão Reset
        resetButton.addActionListener(e -> {
            reiniciar();
        });

        // ActionListener para o botão Pausar
        pauseButton.addActionListener(e -> {
            // Pausar();
        });

        add(menu, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        setVisible(true);

        painel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        if (!cobra.getDirecao().equals("direita")) {
                            cobra.setDirecao("esquerda");
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        if (!cobra.getDirecao().equals("esquerda")) {
                            cobra.setDirecao("direita");
                        }
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        if (!cobra.getDirecao().equals("baixo")) {
                            cobra.setDirecao("cima");
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        if (!cobra.getDirecao().equals("cima")) {
                            cobra.setDirecao("baixo");
                        }
                        break;
                }
            }
        });

        painel.setFocusable(true);
        requestFocusInWindow();
    }

    public void Iniciar() {
        // Inicia o jogo
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                placarField.setText("Placar: " + cobra.getPlacar()); // Atualiza o placar

                cobra.MovimentoCobra();

                if (cobra.VerificarColisoesCorpo() == true) {
                    JOptionPane.showMessageDialog(this, "Fim de jogo! Colidiu com o corpo.", "Fim de Jogo",
                            JOptionPane.INFORMATION_MESSAGE);
                    reiniciar();
                }
                Quadrado cabeca = cobra.getCorpo().get(0);

                if (cabeca.getX() >= larguraTabuleiro) {
                    cabeca.setX(0);
                } else if (cabeca.getX() < 0) {
                    cabeca.setX(larguraTabuleiro - 10);
                } else if (cabeca.getY() >= alturaTabuleiro) {
                    cabeca.setY(0);
                } else if (cabeca.getY() < 0) {
                    cabeca.setY(alturaTabuleiro - 10);
                }

                cobra.setX(cabeca.getX());
                cobra.setY(cabeca.getY());

                painel.repaint(); // Redesenha a tela

            }
        }).start();
    }

    public void reiniciar() {
        // Limpar o corpo da cobra, mas garantir que a cabeça inicial seja reposicionada
        // corretamente
        cobra.getCorpo().clear();

        // Recria a cabeça da cobra na posição inicial
        cobra.setX(larguraTabuleiro / 2);
        cobra.setY(alturaTabuleiro / 2);

        // Cria a cabeça e adiciona ao corpo da cobra
        Quadrado cabeca = new Quadrado(10, 10, Color.MAGENTA);
        cabeca.setX(cobra.getX());
        cabeca.setY(cobra.getY());
        cobra.getCorpo().add(cabeca);

        // Resetar a direção para "direita"
        cobra.setDirecao("direita");

        // Resetar o placar
        cobra.setPlacar(0);

        // Reposicionar a maçã
        maca.reposicionar();
    }

    public static void main(String[] args) {
        new Facil();
    }
}
