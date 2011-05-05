package compilador;


/**********************************************************
* Universidade Federal de Campina Grande - UFCG           *
* Centro de Engenharia Eletrica e Informatica - CEEI      *
* Departamento de Sistemas e Computacao - DSC             *
*                                                         *
* Projeto da disciplina de Compiladores, 2011.1           *
* Compilador de Pre e Pos condicoes para verificacao      *
* OCL para Ruby  (GUI)                                    *
*                                                         *
* Grupo: Francisco Demontie dos Santos Junior - 20911084  *
*        Izabela Vanessa de Almeida Melo - 20811018       *
*        Savyo Igor da Nobrega Santos - 20811034          *
**********************************************************/

public class Main {
	
	public final static int ON = 4, OFF = 5;
	
	public static int debugMode = OFF;
	
	public static void main(String[] args) {
		new compilador.gui.MainFrame();

	}

}

