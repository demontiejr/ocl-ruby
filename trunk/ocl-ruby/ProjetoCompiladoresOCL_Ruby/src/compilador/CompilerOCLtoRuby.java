package compilador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import excecoes.FatalErrorException;
import excecoes.SemanticErrorException;

import analisadorLexico.ScannerOCL;
import analisadorSintatico.ParserOCL;

/*********************************************************
* Universidade Federal de Campina Grande - UFCG          *
* Centro de Engenharia Eletrica e Informatica - CEEI     *
* Departamento de Sistemas e Computacao - DSC            *
*                                                        *
* Projeto da disciplina de Compiladores, 2011.1          *
* Compilador de Pre e Pos condicoes para verificacao     *
* OCL para Ruby                                          *
*                                                        *
* Grupo: Francisco Demontie dos Santos Junior - 20911084 *
*        Izabela Vanessa de Almeida Melo - 20811018      *
*        Savyo Igor da Nobrega Santos - 20811034         *
*********************************************************/

public class CompilerOCLtoRuby {

	private static List<String> errors;
	
	private static ScannerOCL createScanner(String fileName){
		ScannerOCL scanner = null;
		try {
			scanner = new ScannerOCL(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.err.printf("Arquivo \"%s\" nao encontrado.\n", fileName);
			System.exit(0);
		} catch (Exception e) {
			System.err.printf("Erro nao esperado durante a leitura do arquivo:\n%s\n", e.getMessage());
			System.exit(0);
		}
		return scanner;
	}
	
	public static void lexicalAnalysis(String fileName){
		System.out.println("============================================");
		System.out.println("Realizando analise lexica...");
		System.out.println("============================================");
		ScannerOCL scanner = createScanner(fileName);
		errors = new ArrayList<String>();
		if (scanner != null){
			while (scanner.hasNextToken()){
				try {
					scanner.next_token();
				} catch (IOException e) {
					System.err.println("Erro durante a leitura do arquivo");
					System.exit(0);
				} catch (RuntimeException e) {
					errors.add(e.getMessage());
				}
			}
		}
		for (String token : scanner.foundTokens)
			System.out.print(token);
		System.out.println("\n" + errors.size() + " erro(s)");
		for (String error : errors)
			System.out.println(error);
		System.out.println();
		System.out.println("====================================================");
		System.out.println("Analise lexica realizada para o arquivo "  + fileName);
		System.out.println("====================================================");
	}
	
	public static void syntacticAnalysis(String fileName){
		System.out.println("============================================");
		System.out.println("Realizando analise sintatica...");
		System.out.println("============================================");
		ScannerOCL scanner = createScanner(fileName);
		List<String> fatalErrors = new ArrayList<String>();
		if (scanner != null) {
			ParserOCL parser = new ParserOCL(scanner);
			errors = parser.errorLog;
			try {
				parser.debug_parse();
			} catch (RuntimeException e) {
				errors.add(e.getMessage()); 
			} catch (FatalErrorException e) {
				fatalErrors.add(e.getMessage());
			} catch (Exception e) {
				parser.log.add("\nErro durante a analise sintatica: " + e.getMessage() + "\n");
			} finally {
				for (String s : parser.log)
					System.out.print(s);
			}
			System.out.println();
			for (String fatalError : fatalErrors)
				System.out.println(fatalError);
			System.out.println("\n" + errors.size() + " erro(s)");
			for (String error : errors)
				System.out.println(error);
			System.out.println();
		}
		System.out.println("====================================================");
		System.out.println("Analise sintatica realizada para o arquivo "  + fileName);
		System.out.println("====================================================");
	}
	
	public static void semanticAnalysis(String fileName) {
		System.out.println("============================================");
		System.out.println("Realizando analise...");
		System.out.println("============================================");
		System.out.println("Lendo arquivo XMI...");
		xmiParser.XMIParser.main(new String[]{"./files/Profe.xml"});
		ScannerOCL scanner = createScanner(fileName);
		List<String> fatalErrors = new ArrayList<String>();
		if (scanner != null) {
			ParserOCL parser = new ParserOCL(scanner);
			errors = parser.errorLog;
			try {
				parser.parse();
			} catch (RuntimeException e) {
				errors.add(e.getMessage());
				e.printStackTrace();
			} catch (FatalErrorException e) {
				fatalErrors.add(e.getMessage());
			} catch (SemanticErrorException e){
				errors.add(e.getMessage());
			}catch (Exception e) {
				parser.log.add("\nErro durante a analise: " + e.getMessage() + "\n");
			}
			System.out.println("\n" + (errors.size() + parser.as.getLogErros().size()) + " erro(s)\n");
			for (String error : errors)
				System.out.println(error);
			for (String error : parser.as.getLogErros())
				System.out.println(error);
			System.out.println();
		}
		System.out.println("====================================================");
		System.out.println("Analise realizada para o arquivo "  + fileName);
		System.out.println("====================================================");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String fileName;
		System.out.println("Digite o nome do Arquivo: ");
		fileName = in.readLine();
		semanticAnalysis(fileName);
	}
	
}
