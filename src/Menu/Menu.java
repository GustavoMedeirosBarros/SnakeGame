package Menu;

import Main.Dificil;
import Main.Facil;
import Main.Medio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class Menu extends JFrame {
    private JButton facilButton;
    private JButton medioButton;
    private JButton dificilButton;
    private JButton sairButton;
    private JPanel painelMenu;
    private PainelComImagem painelComImagem;

    public Menu() {
        // Configuração da janela
        setTitle("Menu de Seleção");
        setSize(400, 400); // Tamanho igual ao da tela de gameplay
        setResizable(false); // Não permite redimensionar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Inicializa o painel com a imagem de fundo
        painelComImagem = new PainelComImagem();
        painelComImagem.setLayout(new GridLayout(5, 1, 10, 10)); // Layout com 5 linhas e 1 coluna
        painelComImagem.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaçamento ao redor

        // Botões do menu
        facilButton = new JButton("Fácil");
        medioButton = new JButton("Médio");
        dificilButton = new JButton("Difícil");
        sairButton = new JButton("Sair");

        // Adicionando os botões no painel
        painelComImagem.add(facilButton);
        painelComImagem.add(medioButton);
        painelComImagem.add(dificilButton);
        painelComImagem.add(sairButton);

        // Ações dos botões
        facilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Inicia o jogo no modo Fácil
                new Facil();
                setVisible(false); // Fecha a janela de menu
            }
        });

        medioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Inicia o jogo no modo Médio
                new Medio(); // Aqui você deve criar a classe "Medio"
                setVisible(false); // Fecha a janela de menu
            }
        });

        dificilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Inicia o jogo no modo Difícil
                new Dificil(); // Aqui você deve criar a classe "Dificil"
                setVisible(false); // Fecha a janela de menu
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sair do jogo
                System.exit(0);
            }
        });

        // Adicionando o painel com imagem ao JFrame
        add(painelComImagem, BorderLayout.CENTER);

        // Exibe a janela de menuz
        setVisible(true);
    }

    public static void main(String[] args) {
        // Inicializa a janela do menu
        new Menu();
    }
}

// Classe personalizada para o painel com imagem de fundo
class PainelComImagem extends JPanel {
    private Image imagemFundo;

    public PainelComImagem() {
        try {
            // Carregar a imagem de fundo (substitua o caminho conforme necessário)
            imagemFundo = ImageIO.read(getClass().getResource("/resources/img/snakeGameMenu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha a imagem de fundo, ajustando ao tamanho do painel
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}