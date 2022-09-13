package Galo;
import static java.lang.System.*;
import java.util.Scanner;

public class Galo {
    static char[][] tabuleiro;
    static boolean fim;
    static final int TAMANHO = 3;
    static char simboloCorrente = 'X';

    public static void inicializacaoTabuleiro() {
        tabuleiro = new char[TAMANHO][TAMANHO];

        for (int linha = 0; linha < TAMANHO; linha++) {
            for (int coluna = 0; coluna < TAMANHO; coluna++) {
                tabuleiro[linha][coluna] = ' ';
            }
        }
    }

    public static void mostrarTabuleiro() {
        for (int linha = 0; linha < TAMANHO; linha++) {
            out.print("\n->\n");

            for (int coluna = 0; coluna < TAMANHO; coluna++) {
                out.print("_|_" + tabuleiro[linha][coluna]);
            }
            out.print("_|\n");
        }
    }

    public static boolean fazerJogada(char simbolo) {
        Scanner input = new Scanner(in);

        out.println("\nInsira a linha onde quer jogar com " + simbolo);
        int linha = input.nextInt();

        if (linha < 1 || linha > TAMANHO) {
            return false;
        }

        out.println("Insira a coluna onde quer jogar com " + simbolo);
        int coluna = input.nextInt();

        if (coluna < 1 || coluna > TAMANHO) {
            return false;
        }

        if (tabuleiro[linha - 1][coluna - 1] == ' ') {
            tabuleiro[linha - 1][coluna - 1] = simbolo;
            mostrarTabuleiro();
            return true;
        } else {
            out.println("Linha ou coluna inválida \n");
            return false;
        }
    }

    public static void mudarSimbolo() {
        if (simboloCorrente == 'X') {
                    simboloCorrente = 'O';
                } else {
                    simboloCorrente = 'X';
                }
    }

    public static void empateAcabado() {
        int check = 0;

        for (int linha = 0; linha < TAMANHO; linha++) {
            for (int coluna = 0; coluna < TAMANHO; coluna++) {
                if (tabuleiro[linha][coluna] != ' ') {
                    check++;
                }
            }
        }

        if (check == TAMANHO*TAMANHO) {
            fim = true;
            out.println("\nJogo acabado. Empate.");
        }
    }

public static void linhaAcabado() {
        int check = 0;

        for (int j = 0; j < TAMANHO; j++) {
            if (tabuleiro[0][j] == tabuleiro[TAMANHO-1][j] && tabuleiro[0][j] != ' ') {
                for (int i = 0; i < TAMANHO-1; i++) {
                    if (tabuleiro[i][j] == tabuleiro[i + 1][j] && tabuleiro[i][j] != ' ') {
                        check++;
                    }
                }
            }
        }

        if (check == TAMANHO-1) {
            fim = true;
        }
    }

    public static void colunaAcabado() {
        int check = 0;

        for (int i = 0; i < TAMANHO; i++) {
            if (tabuleiro[i][0] == tabuleiro[i][TAMANHO-1] && tabuleiro [i][0] != ' ') {
                for (int j = 0; j < TAMANHO-1; j++) {
                    if (tabuleiro[i][j] == tabuleiro[i][j+1] && tabuleiro[i][j] != ' ') {
                        check++;
                    }
                }
            }
        }

        if (check == TAMANHO-1) {
            fim = true;
        }
    }



    public static void diagonalAcabado() {
        int check1 = 0;

        if (tabuleiro[0][0] == tabuleiro[TAMANHO-1][TAMANHO-1] && tabuleiro[0][0] != ' ') {
                for (int i = 0; i < TAMANHO - 1; i++) {
                    if (tabuleiro[i][i] == tabuleiro[i + 1][i + 1] && tabuleiro[i][i] != ' ') {
                        check1++;
                    }
                }
            }

        if (check1 == TAMANHO-1) {
            fim = true;
        }

        int check2 = 0;

        if (tabuleiro[0][TAMANHO-1] == tabuleiro[TAMANHO-1][0] && tabuleiro[0][TAMANHO-1] != ' ') {
            for (int i = 0; i < TAMANHO-1; i++) {
                    if (tabuleiro[i][TAMANHO-1-i] == tabuleiro[i+1][TAMANHO-2-i] && tabuleiro[i][TAMANHO-1] != ' ') {
                        check2++;
                    }
                }
            }

        if (check2 == TAMANHO-1) {
            fim = true;
        }
    }
    
    public static boolean jogoAcabado() {
        linhaAcabado();
        colunaAcabado();
        diagonalAcabado();

        if (fim == true) {
            mudarSimbolo();
            out.println("\nJogo acabado. " + simboloCorrente + " ganhou.");
        } else if (fim == false) {
        empateAcabado();
        }
        return fim;
    }

    public static void main(String[] args) {
        inicializacaoTabuleiro();

        boolean jogoTerminado = false;
        while (!jogoTerminado) {
            if (fazerJogada(simboloCorrente)) {
                mudarSimbolo();
             }

            jogoTerminado = jogoAcabado();
        }
    }
}
