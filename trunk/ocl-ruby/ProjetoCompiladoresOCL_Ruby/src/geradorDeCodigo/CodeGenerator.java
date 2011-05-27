package geradorDeCodigo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xmiParser.XMIManager;
import xmiParser.util.Atributo;
import xmiParser.util.Classe;
import xmiParser.util.OperacaoMaior;
import xmiParser.util.Parametro;

public class CodeGenerator {

	private String directory;
	private String fileName;
	private static CodeGenerator codeGen;
	private Map<String, String> classesCode;
	
	private CodeGenerator(){
		directory = "";
		classesCode = new HashMap<String, String>();
		generateAllClasses();
	}
	
	public static CodeGenerator getInstance(){
		if (codeGen == null)
			codeGen = new CodeGenerator();
		return codeGen;
	}
	
	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getClassCode(String className){
		String code = classesCode.get(className);
		return code.substring(0,code.length()-3);
	}

	public void writeToFile(String code){
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(directory + fileName + ".rb"));
			file.write(code);
			file.close();
		} catch (IOException e) {
			System.out.println("Problemas na escrita do arquivo");;
		}
		
	}
	
	private void generateAllClasses(){
		String code;
		for (Classe c : XMIManager.getAllClasses()){
			Classe sup = c.getClassePai();
			String s = "";
			if (sup != null){
				s = " < " + sup.getName();
			}
			code = "";
			code += "class " + c.getName() + s + "\n";
			code += getInitCode(c.getAtributos());
			code += getMethodsCode(c.getOperacoes());
			code += "\nend";
			classesCode.put(c.getName(),code);
			writeClassesToFile(code,c.getName());
		}
	}
	
	private String getInitCode(List<Atributo> atributos){
		String declaration = "\n\tdef initialize(";
		String code = "\n";
		for (Atributo a : atributos){
			String id = a.getNome();
			declaration += id + ", ";
			code += "\t\t@" + id + " = " + id + "\n";
		}
		if (atributos.size() != 0)
			declaration = declaration.substring(0,declaration.length()-2);
		declaration += ")\n";
		return declaration + code + "\n\tend\n";
	}
	
	private String getMethodsCode(List<OperacaoMaior> methods){
		String code = "";
		for (OperacaoMaior op : methods){
			for (List<Parametro> params : op.getListaParametros()){
				code += "\n\tdef " + op.getNome() + "(";
				for (Parametro p : params){
					code += p.getNome() + ", ";
				}
				if (params.size() != 0)
					code = code.substring(0,code.length()-2);
				code += ") end\n";
			}
		}
		return code;
	}
	
	private void writeClassesToFile(String code, String fName){
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(directory + fName + ".rb"));
			file.write(code);
			file.close();
		} catch (IOException e) {
			System.out.println("Problemas na escrita do arquivo");;
		}	
	}	
	
}
