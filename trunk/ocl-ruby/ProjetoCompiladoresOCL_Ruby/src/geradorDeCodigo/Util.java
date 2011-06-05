package geradorDeCodigo;

public class Util {

	public static String capitalizeFirst(String str){
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public static String isCollection(String entrada){
		if(entrada != null){
			String[] lista = entrada.split("<");
			if(lista.length > 1){
				String[] listaAux = (lista[lista.length-1]).split(">");
				if(listaAux.length == 1){
					return listaAux[0];
				}
			}
		}		
		return null;
	}
	
}
