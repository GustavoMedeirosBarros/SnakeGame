package Main;

import modes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Menu extends JFrame {
    private JButton facilButton;
    private JButton normalButton;
    private JButton sairButton;
    private JPanel painelMenu;
    private Image imagemFundo;

    public Menu() {
        setTitle("Snake Game");
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        painelMenu = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Desenha a imagem de fundo
                if (imagemFundo != null) {
                    g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // Carregar a imagem de fundo
        try {
            imagemFundo = ImageIO.read(getClass().getResource("/resources/img/snakeGameMenu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Definir o layout do painel (GridLayout com 5 linhas e 1 coluna)
        painelMenu.setLayout(new GridLayout(5, 1, 10, 10));
        painelMenu.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Botões do menu
        facilButton = new JButton("Fácil");
        normalButton = new JButton("Normal");
        sairButton = new JButton("Sair");

        // Adicionando os botões no painel
        painelMenu.add(facilButton);
        painelMenu.add(normalButton);
        painelMenu.add(sairButton);

        // ActionListener para o botão Fácil
        facilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Facil();
                dispose();
            }
        });

        // ActionListener para o botão Normal
        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Normal();
                dispose();
            }
        });

        // ActionListener para o botão Sair
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(painelMenu, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();
    }
}