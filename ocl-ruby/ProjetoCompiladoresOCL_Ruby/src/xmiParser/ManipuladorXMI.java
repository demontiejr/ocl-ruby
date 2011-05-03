package xmiParser;

import java.util.ArrayList;
import java.util.Collection;

import xmiParser.util.Atributo;
import xmiParser.util.Classe;
import xmiParser.util.Entidade;
import xmiParser.util.Operacao;

//Classe que vai ter os métodos estáticos
public class ManipuladorXMI {
	
	private static ArrayList<Entidade> classes;
	private static ArrayList<String> hierarquiaNumbers;
	
	private ManipuladorXMI() {
	}
	
	public static void setStaticClasses(Collection<Entidade> cls){
		classes = new ArrayList<Entidade>();
		for (Entidade entidade : cls) {
			classes.add(entidade);
		}
		hierarquiaNumbers = new ArrayList<String>();
		hierarquiaNumbers.add("Integer");
		hierarquiaNumbers.add("Long");
		hierarquiaNumbers.add("Float");
		hierarquiaNumbers.add("Double");
	}
	
	private static Classe getClasse(String idClasse) throws Exception{
		for (Entidade e : classes) {
			if(e.getName().equals(idClasse)){
				try{
					return (Classe) e;
				}catch(Exception ex){
					throw new Exception("Type: <"+idClasse+"> isn't a class.");
				}
			}
		}
		throw new Exception("Type: <"+idClasse+"> doesn't exists.");
	}
	
	private static Atributo getAtributoFromClass(Classe context, Classe classe,
			String idAtributo) throws Exception {
		Atributo att = findAttFromList(classe.getAtributos(),idAtributo);
		if(att!=null){
			if(!context.getName().equals(classe.getName()) && att.getVisibilidade().equalsIgnoreCase("private")){
				throw new Exception("Atribute: <"+idAtributo+"> doesn't come from inheritance <"+context.getName()+"> => <"+context.getName()+">.");
			}
			return att;
		}else{
			if(classe.temPai()){
				return getAtributoFromClass(context, classe.getClassePai(), idAtributo);
			}else{
				throw new Exception("Atribute: <"+idAtributo+"> not found in type <"+classe.getName()+">.");
			}
		}
	}
	
	private static Atributo findAttFromList(ArrayList<Atributo> atributos,
			String idAtributo) {
		for (Atributo atributo : atributos) {
			if(atributo.getNome().equals(idAtributo)){
				return atributo;
			}
		}
		return null;
	}

	/**
	 * função que receba o nome de uma classe e um nome de atributo e retorne o tipo do atributo se esse existir 
	 * 		na classe (ou na superclasse). Caso contrário, ele indica que o atributo não pertence à classe 
	 * 		(pode ser retornando null).
	 * @throws Exception 
	 */
	
	public static Atributo contemAtributo(String context, String idClasse, String idAtributo) throws Exception{
		Classe classe = getClasse(idClasse);
		Classe contexto = getClasse(context);
		if(classe!=null){
			Atributo ret = getAtributoFromClass(contexto,classe,idAtributo);
			return ret;
		}
		return null;
	}
	
	/**
	 * função que receba o nome de uma classe e um nome de função e retorne um objeto do tipo função 
	 * 	(contendo uma lista com os tipos dos parametros e um atributo que guarda o tipo de retorno) 
	 * 	se essa existir na classe (ou na superclasse). Caso contrário, ele indica que a função 
	 * 	não pertence à classe (pode ser retornando null).
	 */
	 
	public static Operacao contemFuncao(String context, String idClasse, String idAtributo) throws Exception{
		Classe classe = getClasse(idClasse);
		Classe contexto = getClasse(context);
		if(classe!=null){
			Operacao ret = getOperacaoFromClass(contexto,classe,idAtributo);
			return ret;
		}
		return null;
	}

	private static Operacao getOperacaoFromClass(Classe context,
			Classe classe, String idOperation) throws Exception {
		Operacao op = findOperationFromList(classe.getOperacoes(),idOperation);
		if(op!=null){
			if(!context.getName().equals(classe.getName()) && op.getVisibility().equalsIgnoreCase("private")){
				throw new Exception("Operation: <"+idOperation+"> doesn't come from inheritance <"+context.getName()+"> => <"+context.getName()+">.");
			}
			return op;
		}else{
			if(classe.temPai()){
				return getOperacaoFromClass(context, classe.getClassePai(), idOperation);
			}else{
				throw new Exception("Operation: <"+idOperation+"> not found in type <"+classe.getName()+">.");
			}
		}
	}

	private static Operacao findOperationFromList(
			ArrayList<Operacao> operacoes, String idOperation){
		for (Operacao op : operacoes) {
			if(op.getNome().equals(idOperation)){
				return op;
			}
		}
		return null;
	}
	
	
	/**
	 *  função que receba o nome de um atributo (que é uma coleção) e 
	 *  	retorne o tipo de objetos que tem dentro da coleção. 
	 *  
	 *  	Ex.: se A é um ArrayList<String>, entao funcao(A) retorna String. 
	 * @throws Exception 
	 */
	
	public static String getTipoColecao(String context, String classe, String idAtributo) throws Exception{
		Classe contexto = getClasse(context);
		Classe c = getClasse(classe);
		Atributo att = getAtributoFromClass(contexto, c, idAtributo);
		if(att!=null && att.ehColecao()){
			if(att.getIdTipo().contains("<")){
				return getTypeInCol(att.getIdTipo());
			}else{
				return getTypeInCol(att.getTipo().getName());
			}
		}else{
			throw new Exception("Atribute: <"+att.getNome()+"> isn't a Collection kind.");
		}
	}

	private static String getTypeInCol(String idTipo) {
		String[] separa = idTipo.split("<");
		return separa[1].split(">")[0];
	}
	
	/**
	 *  função maxType que receba duas strings indicando os tipos e retornem o "maior" tipos se os dois estiverem 
	 *  	em conformidade. Caso contrario, a função retorna erro semântico. Essa função poderá receber tanto 
	 *  	os tipos pré-definidos em ocl quanto os tipos definidos no xmi. 
	 *  	(ver hierarquia de tipos da aula sobre análise semântica de ocl)
	 * 
	 * @throws Exception
	 */
	
	public String maxType(String type1, String type2, int line) throws Exception{
		
		if(type1.equalsIgnoreCase(type2)){
			return type1;
		}else{
			try{
				Classe c1 = getClasse(type1);
				Classe c2 = getClasse(type2);
				if(c1.ehFilho(c2)){
					return type2;
				}else if(c2.ehFilho(c1)){
					return type1;
				}
			}catch(Exception e){
				if(hierarquiaNumbers.indexOf(type1)>=0){
					return hierarquiaNumbers.indexOf(type1)>=hierarquiaNumbers.indexOf(type2)?
							hierarquiaNumbers.get( hierarquiaNumbers.indexOf(type1) ) : 
								hierarquiaNumbers.get( hierarquiaNumbers.indexOf(type2) );
				}
			}
			throw new Exception("Operation not valid between " + type1 + " and " + type2+".\nAt line "+line);
		}
	}

}
