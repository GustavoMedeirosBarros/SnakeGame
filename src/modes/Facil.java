package modes;

import entities.*;

import javax.swing.*;

import Main.Menu;
import Main.PainelJogo;

import java.awt.*;

public class Facil extends JFrame {
    private Cobra cobra;
    private Maca maca;
    private int larguraTabuleiro, alturaTabuleiro;
    private boolean jogoIniciado = false;
    private int tempoAtualizacao = 100;
    private Thread jogoThread;

    private PainelJogo painelJogo;
    private JTextField placarField;
    private JPanel menu;
    private JButton iniciarButton;
    private JButton resetButton;
    private JButton pauseButton;
    private JButton MenuButton;

    public Facil() {
        maca = new Maca();
        cobra = new Cobra(maca); // Passa a referência da maçã para a cobra
        larguraTabuleiro = alturaTabuleiro = 400;

        setTitle("Jogo da Cobrinha");
        setSize(larguraTabuleiro + 16, alturaTabuleiro + 75);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        painelJogo = new PainelJogo(cobra, maca);

        // Iniciando menu
        menu = new JPanel();
        menu.setLayout(new FlowLayout());

        // Iniciando botões do menu
        iniciarButton = new JButton("Iniciar");
        resetButton = new JButton("Reiniciar");
        pauseButton = new JButton("Pausar");
        MenuButton = new JButton("Menu");
        placarField = new JTextField("Placar: 0");
        placarField.setEditable(false);
        placarField.setColumns(7);

        menu.add(iniciarButton);
        menu.add(resetButton);
        menu.add(pauseButton);
        menu.add(MenuButton);
        menu.add(placarField);

        // ActionListener para botão iniciar
        iniciarButton.addActionListener(e -> {
            iniciarJogo();
        });
        // ActionListener para botão reset
        resetButton.addActionListener(e -> {
            reiniciarJogo();
        });
        // ActionListener para botão pause
        pauseButton.addActionListener(e -> {
            pausarJogo();
        });
        // ActionListener para botão menu
        MenuButton.addActionListener(e -> {
            Menu();
        });

        add(menu, BorderLayout.NORTH);
        add(painelJogo, BorderLayout.CENTER);

        setVisible(true);
    }

    public void iniciarJogo() {
        if (!jogoIniciado) {
            jogoIniciado = true;
            painelJogo.requestFocusInWindow();
            jogoThread = new Thread(() -> {
                while (jogoIniciado) {
                    try {
                        Thread.sleep(tempoAtualizacao);
                        if (painelJogo.isJogoPausado()) {
                            continue;
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    placarField.setText("Placar: " + cobra.getPlacar()); // Atualiza o placar

                    cobra.MovimentoCobra();

                    if (cobra.VerificarColisoesCorpo() == true) {
                        JOptionPane.showMessageDialog(this, "Fim de jogo! Colidiu com o corpo.", "Fim de Jogo",
                                JOptionPane.INFORMATION_MESSAGE);
                        reiniciarJogo();
                    }
                    Quadrado cabeca = cobra.getCorpo().get(0);

                    if (cabeca.getX() >= larguraTabuleiro) {
                        cabeca.setX(0);
                    } else if (cabeca.getX() < 0) {
                        cabeca.setX(larguraTabuleiro - 20);
                    } else if (cabeca.getY() >= alturaTabuleiro) {
                        cabeca.setY(0);
                    } else if (cabeca.getY() < 0) {
                        cabeca.setY(alturaTabuleiro - 20);
                    }

                    cobra.setX(cabeca.getX());
                    cobra.setY(cabeca.getY());

                    painelJogo.atualizarJogo(); // Redesenha a tela

                }
            });
            jogoThread.start();
        }
    }

    public void reiniciarJogo() {
        // Metodo para reiniciar o jogo
        jogoIniciado = false;
        tempoAtualizacao = 100;

        // Limpa a arraylist da cobra e seta sua posição no meio
        cobra.getCorpo().clear();
        cobra.setX(larguraTabuleiro / 2);
        cobra.setY(alturaTabuleiro / 2);

        // Cria uma nova cabeça para a cobra e adiciona sua coordenada e adiciona na
        // arraylist
        Quadrado cabeca = new Quadrado(20, 20, Color.MAGENTA);
        cabeca.setX(cobra.getX());
        cabeca.setY(cobra.getY());
        cobra.getCorpo().add(cabeca);

        // Seta a direção para a direita e zera o placar
        cobra.setDirecao("direita");
        cobra.setPlacar(0);

        // Reposiciona a maça com o metodo da classe Maca
        maca.reposicionar(cobra.getCorpo());

        JOptionPane.showMessageDialog(this, "Jogo Reiniciado!", "Reset", JOptionPane.INFORMATION_MESSAGE);
    }

    private void pausarJogo() {
        // Metodo para pausar o Jogo
        painelJogo.pausarJogo();
        if (painelJogo.isJogoPausado()) {
            JOptionPane.showMessageDialog(this, "Jogo Pausado!", "Pausado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Jogo Retomado!", "Retomar", JOptionPane.INFORMATION_MESSAGE);
        }

        if (!painelJogo.isJogoPausado()) {
            painelJogo.requestFocusInWindow();
        }
    }

    private void Menu() {
        // Metodo para voltar ao menu principal do jogo
        jogoIniciado = false;
        if (jogoThread != null && jogoThread.isAlive()) {
            jogoThread.interrupt();
        }
        this.dispose();
        new Menu();
    }

    public static void main(String[] args) {
        new Facil();
    }
}
