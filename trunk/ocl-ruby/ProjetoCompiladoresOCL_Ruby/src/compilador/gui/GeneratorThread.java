/* Essa interface grafica foi baseada na GUI do JFlex 1.4.3
 ( Copyright (C) 1998-2009  Gerwin Klein <lsf@jflex.de>  ) */

package compilador.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import xmiParser.XMIManager;
import xmiParser.XMIParser;
import JFlex.ErrorMessages;
import JFlex.Out;
import analisadorLexico.ScannerOCL;
import analisadorSintatico.ParserOCL;

import compilador.Main;

import excecoes.FatalErrorException;
import excecoes.SemanticErrorException;
import geradorDeCodigo.CodeGenerator;

public class GeneratorThread extends Thread {

	/** there must be at most one instance of this Thread running */
	private static volatile boolean running = false;

	/** input file setting from GUI */
	String inputFile;

	/** output directory */
	String inputXmiFile;

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
	 * @param inputXmiFile
	 *            output directory from UI settings
	 */
	public GeneratorThread(MainFrame parent, String inputFile, String inputXmiFile) {
		this.parent = parent;
		this.inputFile = inputFile;
		this.inputXmiFile = inputXmiFile;
		this.errors = new ArrayList<String>();
	}

	/**
	 * Run the generator thread. Only one instance of it can run at any time.
	 */
	public void run() {
		analysis();
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
	
	@SuppressWarnings("unused")
	private void lexicalAnalysis(){
		if (running) {
			Out.error(ErrorMessages.ALREADY_RUNNING);
			parent.analysisFinished(false, new ArrayList<String>(),errors, new HashSet<String>());
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
					parent.analysisFinished(true, scanner.foundTokens, errors, new HashSet<String>());
				else
					parent.analysisFinished(true, new ArrayList<String>(), errors, new HashSet<String>());
			}
		}
	}

	@SuppressWarnings("unused")
	private void syntacticalAnalysis(){
		if (running) {
			Out.error(ErrorMessages.ALREADY_RUNNING);
			parent.analysisFinished(false, new ArrayList<String>(),errors, new HashSet<String>());
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
				parent.analysisFinished(true, parser.log, errors, new HashSet<String>());
			}
			
		}
	}
	
	private void analysis(){
		if (running) {
			Out.error(ErrorMessages.ALREADY_RUNNING);
			parent.analysisFinished(false, new ArrayList<String>(),errors, new HashSet<String>());
		} else {
			Out.print("Iniciando analise para o arquivo\n" + inputFile);
			Out.println("................................................................\n");
			Out.println("Lendo arquivo XMI...");
			File xmi = new File(inputXmiFile);
			try {
				XMIParser parserxmi = new XMIParser(xmi);
				parserxmi.readXMI();
				XMIManager.setStaticClasses(parserxmi.getArrayClasses());
				CodeGenerator.getInstance().setDirectory((MainFrame.diretorio));
				CodeGenerator.getInstance().generateAllClasses();
			} catch (Exception e1) {
				Out.println(e1.getMessage());
				System.exit(0);
			}
			ScannerOCL scanner = createScanner(inputFile);
			if (scanner != null) {
				Out.println("Realizando analise...");
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
				} catch (SemanticErrorException e){
					errors.add(e.getMessage() + "\n");
				} catch (Exception e) {
					parser.log.add("\nErro durante a analise sintatica: " + e.getMessage() + "\n");
				} finally {
					running = false;
				}
				//System.out.println("\n" + errors.size() + " erro(s)");
				//for (String erro : errors)
				//	System.out.println(erro);
				//System.out.println();
				parent.analysisFinished(true, parser.log, errors, parser.as.getLogErros());
			}
			
		}
	}
	
}
