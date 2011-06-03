package geradorDeCodigo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
	private Map<String, List<String>> checkPre;
	private Map<String, List<String>> checkPost;
	private Map<String, String> mainCode;
	private int preNumber;
	private int postNumber;
	private Map<String, String> cMethods;
	
	private CodeGenerator(){
		directory = "";
		classesCode = new HashMap<String, String>();
		checkPre = new HashMap<String,List<String>>();
		checkPost = new HashMap<String,List<String>>();
		mainCode = new HashMap<String, String>();
		preNumber = 1;
		postNumber = 1;
		cMethods = new HashMap<String, String>();
		//generateAllClasses();
	}
	
	public static CodeGenerator getInstance(){
		if (codeGen == null)
			codeGen = new CodeGenerator();
		return codeGen;
	}
	
	public void reset(){
		checkPre = new HashMap<String,List<String>>();
		checkPost = new HashMap<String,List<String>>();
		preNumber = 1;
		postNumber = 1;
		cMethods = new HashMap<String, String>();
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
	
	public void setClassCode(String className, String code){
		classesCode.put(className, code);
	}
	
	public String getClassCode(String className){
		String code = classesCode.get(className);
		return code;
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
	
	public void generateAllClasses(){
		String code;
		for (Classe c : XMIManager.getAllClasses()){
			if (isCollection(c.getName()))
				continue;
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
		String code = "";
		for (Atributo a : atributos){
			String id = a.getNome();
			code += "\tattr \"" + id + "\"\n";
		}
		return code;
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
			System.out.println(fName);
			System.out.println("Problemas na escrita do arquivo");;
		}	
	}	
	
	private boolean isCollection(String entrada){
		if(entrada != null){
			String[] lista = entrada.split("<");
			if(lista.length > 1){
				String[] listaAux = (lista[lista.length-1]).split(">");
				if(listaAux.length == 1){
					return true;
				}
			}
		}		
		return false;
	}
	
	public int getPreNumber(){
		return preNumber++;
	}
	
	public int getPostNumber(){
		return postNumber++;
	}
	
	public void addContextMethod(String method){
		if (!checkPre.containsKey(method)){
			checkPre.put(method, new ArrayList<String>());
		}
		if (!checkPost.containsKey(method)){
			checkPost.put(method, new ArrayList<String>());
		}
	}
	
	public void addContextMethodCall(String method, String code){
		cMethods.put(method, code);
	}
	
	public void addPre(String method, String name){
		checkPre.get(method).add(name);
	}
	
	public void addPost(String method, String name){
		checkPost.get(method).add(name);
	}
	
	public String addPre(String method){
		String name = "checkPre" + getPreNumber() + Util.capitalizeFirst(method);
		checkPre.get(method).add(name);
		return name;
	}
	
	public String addPost(String method){
		String name = "checkPost" + getPostNumber() + Util.capitalizeFirst(method);
		checkPost.get(method).add(name);
		return name;
	}
	
	public String getAllPre(){
		String code = "";
		for (String m : checkPre.keySet()){
			code += "\n\tdef checkAllPre" + Util.capitalizeFirst(m)
			+ "()\n";
			if (checkPre.get(m).isEmpty()){
				code += "\t\treturn true\n\tend\n";
				continue;
			}
			code += "\t\treturn (";
			for (String c : checkPre.get(m)){
				code += c + "() and ";
			}
			code = code.substring(0,code.length()-5);
			code += ")";
			code += "\n\tend";
			code += "\n\n" + generateMethodViolate(m, "Pre");
		}
		return code;
	}
	
	public String getAllPost(){
		String code = "";
		for (String m : checkPost.keySet()){
			code += "\n\tdef checkAllPost" + Util.capitalizeFirst(m)
			+ "()\n";
			if (checkPost.get(m).isEmpty()){
				code += "\t\treturn true\n\tend\n";
				continue;
			}
			code += "\t\tif !(";
			for (String c : checkPost.get(m)){
				code += c + "() and ";
			}
			code = code.substring(0,code.length()-5);
			code += ")\n";
			code += "\t\t\t" + m + "PostViolated()\n\t\tend";
			code += "\n\t\treturn true\n\tend";
			code += "\n\n" + generateMethodViolate(m, "Post");
		}
		return code;
	}
	
	private String generateMethodViolate(String method, String stereotype){
		String code = "\tdef " + method + stereotype + "IsViolated()\n";
		code += "\t\traise Exception, \"" + stereotype + "condition of the method " + method  + " was violated\"";
		code += "\n\tend\n";
		return code;
	}
	
	public String getMain(){
		if (!mainCode.containsKey(fileName)){
			String code = createMain();
			mainCode.put(fileName, code);
			code = "\n\tdef main()\n" + code;
			code += "\n\tend";
			return code;
		}
		String code = mainCode.get(fileName);
		code += createMain();
		mainCode.put(fileName, code);
		code = "\n\tdef main()\n" + code;
		code += "\n\tend";
		return code;
	}
	
	private String createMain(){
		String code = "";
		for (String m : checkPre.keySet()){
			String methodName = Util.capitalizeFirst(m);
			code += "\n\t\tif checkAllPre" + methodName + "()\n";
			code += "\t\t\t" + "@result" + Util.capitalizeFirst(m) + " = "
			+ cMethods.get(m) + "\n";
			code += "\t\t\tcheckAllPost" + methodName + "()\n";
			code += "\t\telse\n\t\t\t" + m + "PreIsViolated()\n";
			code += "\t\tend\n";
		}
		return code;
	}
	
}
