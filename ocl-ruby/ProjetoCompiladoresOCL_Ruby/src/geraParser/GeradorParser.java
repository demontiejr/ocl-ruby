package geraParser;

import java.io.IOException;

import java_cup.internal_error;

public class GeradorParser {

	public static void main(String[] args) {
		String[] argumentos = {"-parser","ParserOCL", "-symbols", "Symbols", 
				"-destdir", "./src/analisadorSintatico", "./files/ocl.cup"};
		try {
			System.out.println("Gerando parser...");
			java_cup.Main.main(argumentos);
		} catch (internal_error e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro de entrada e saida: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Aconteceu um erro durante a geracao do parser");
		} finally {
			System.out.println("\nConcluido");
		}
	}
	
}
