package geradorDeCodigo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {

	private String directory;
	private String fileName;
	private static CodeGenerator codeGen;
	private List<String> idsUtilizados;
	
	private CodeGenerator(){
		directory = "";
		idsUtilizados = new ArrayList<String>();
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
	
	public void addIdUtilizado(String id){
		idsUtilizados.add(id);
	}
	
	public List<String> getIdsUtilizados(){
		return idsUtilizados;
	}
	
	public String getInitializeCode(){
		String declaration = "\n\tdef initialize(";
		String code = "\n";
		for (String id : getIdsUtilizados()){
			declaration += id + ", ";
			code += "\t\t@" + id + " = " + id + "\n";
		}
		if (getIdsUtilizados().size() != 0)
			declaration = declaration.substring(0,declaration.length()-2);
		declaration += ")\n";
		return declaration + code + "\n\tend\n";
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
	
}
