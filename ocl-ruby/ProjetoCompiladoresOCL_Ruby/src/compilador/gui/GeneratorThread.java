/* Essa interface grafica foi baseada na GUI do JFlex 1.4.3
 ( Copyright (C) 1998-2009  Gerwin Klein <lsf@jflex.de>  ) */

package compilador.gui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import JFlex.ErrorMessages;
import JFlex.Out;
import analisadorLexico.ScannerOCL;
import analisadorSintatico.ParserOCL;

import compilador.Main;

import excecoes.FatalErrorException;

public class GeneratorThread extends Thread {

	/** there must be at most one instance of this Thread running */
	private static volatile boolean running = false;

	/** input file setting from GUI */
	String inputFile;

	/** output directory */
	String outputDir;

	/** main UI component, likes to be notified when generator finishes */
	MainFrame parent;
	
	List<String> errors;

	/**
	 * Create a new GeneratorThread, but do not run it yet.
	 * 
	 * @param parent
	 *            the frame, main UI component
	 * @param inputFile
	 *            input file from UI settings
	 * @param messages
	 *            where generator messages should appear
	 * @param outputDir
	 *            output directory from UI settings
	 */
	public GeneratorThread(MainFrame parent, String inputFile, String outputDir) {
		this.parent = parent;
		this.inputFile = inputFile;
		this.outputDir = outputDir;
		this.errors = new ArrayList<String>();
	}

	/**
	 * Run the generator thread. Only one instance of it can run at any time.
	 */
	public void run() {
		if (Main.analysisType == Main.LEXICAL)
			lexicalAnalysis();
		else if (Main.analysisType == Main.SYNTACTIC)
			syntacticalAnalysis();
	}

	private ScannerOCL createScanner(String fileName) {
		ScannerOCL scanner = null;
		try {
			scanner = new ScannerOCL(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			Out.println("Arquivo " + fileName + " nao encontrado.");
		} catch (Exception e) {
			Out.println(
					"Erro nao esperado durante a leitura do arquivo:\n"+e.getMessage());
		}
		return scanner;
	}
	
	private void lexicalAnalysis(){
		if (running) {
			Out.error(ErrorMessages.ALREADY_RUNNING);
			parent.analysisFinished(false, new ArrayList<String>(),errors);
		} else {
			ScannerOCL scanner = createScanner(inputFile);
			if (scanner != null) {
				Out.print("Iniciando analise lexica para o arquivo\n" + inputFile);
				Out.println("................................................................\n");
				while (scanner.hasNextToken()) {
					try {
						scanner.next_token();
					} catch (IOException e) {
						Out.println("Erro durante a leitura do arquivo");
					} catch (RuntimeException e) {
						errors.add(e.getMessage());
					} finally {
						running = false;
					}
				}
				if (Main.debugMode == Main.ON)
					parent.analysisFinished(true, scanner.foundTokens, errors);
				else
					parent.analysisFinished(true, new ArrayList<String>(), errors);
			}
		}
	}

	private void syntacticalAnalysis(){
		if (running) {
			Out.error(ErrorMessages.ALREADY_RUNNING);
			parent.analysisFinished(false, new ArrayList<String>(),errors);
		} else {
			ScannerOCL scanner = createScanner(inputFile);
			if (scanner != null) {
				Out.print("Iniciando analise sintatica para o arquivo\n" + inputFile);
				Out.println("................................................................\n");
				ParserOCL parser = new ParserOCL(scanner);
				errors = parser.errorLog;
				try {
					if (Main.debugMode == Main.ON)
						parser.debug_parse();
					else
						parser.parse();
				} catch (RuntimeException e) {
					errors.add(e.getMessage());
				} catch (FatalErrorException e){
					parser.log.add("\n" + e.getMessage() + "\n");
				} catch (Exception e) {
					parser.log.add("\nErro durante a analise sintatica: " + e.getMessage() + "\n");
				} finally {
					running = false;
				}
				System.out.println("\n" + errors.size() + " erro(s)");
				for (String erro : errors)
					System.out.println(erro);
				System.out.println();
				parent.analysisFinished(true, parser.log, errors);
			}
			
		}
	}
	
}
