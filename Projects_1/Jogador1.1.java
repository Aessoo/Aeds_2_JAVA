import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.io.IOException;
import java.util.Date;
import java.io.FileWriter;

class Jogador {
	
	//Variaveis Privadas da Classe
	private int id;
	private String nome;
	private int altura;
	private int peso;
	private String universidade;
	private int anoNascimento;
	private String cidadeNascimento;
	private String estadoNascimento;

	//Metodo Construtor 
	Jogador(){
	
	}	
Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento) {
    setAll(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
}

	//Metodo Get
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public int getAltura() {
		return altura;
	}

	public int getPeso() {
		return peso;
	}

	public String getUniversidade() {
		return universidade;
	}

	public int getAnoNascimento() {
		return anoNascimento;
	}

	public String getCidadeNascimento() {
		return cidadeNascimento;
	}

	public String getEstadoNascimento() {
		return estadoNascimento;
	}

	//Metodo Set
	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public void setUniversidade(String universidade) {
		if(universidade == ""){
			this.universidade = "nao informado";
		}else{
			this.universidade = universidade;
		}
	}

	public void setAnoNascimento(int anoNascimento){
		this.anoNascimento = anoNascimento;
	}
	
	public void setCidadeNascimento(String cidadeNascimento) {
		if(cidadeNascimento == ""){
			this.cidadeNascimento = "nao informado";
		}else{
			this.cidadeNascimento = cidadeNascimento;
	
		}
	}

	public void setEstadoNascimento(String estadoNascimento) {
		if(estadoNascimento == ""){
			this.estadoNascimento = "nao informado";
		}else{
			this.estadoNascimento = estadoNascimento;
	
		}
	}

	public void setAll(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento){
		setId(id);
		setNome(nome);
		setAltura(altura);
		setPeso(peso);
    setUniversidade(universidade);
		setAnoNascimento(anoNascimento);
		setCidadeNascimento(cidadeNascimento);
		setEstadoNascimento(estadoNascimento);
	}
		
	//Metodo Clone 
	public Jogador clone() {
		return new Jogador (id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
	}

	//Metodo para trocar numeros de base char para base int 
	public int charInt(String aux) {
		int auxId = 0;
		int auxInt = 0;
		for(int i = aux.length(), j = 0; i > 0; i--, j++){
			auxInt = (int)aux.charAt(i-1);
			auxId += (auxInt-'0') * Math.pow(10,j);
		}
		return auxId;
	}
	
	//Metodo ler ate a virgula 	
	public String lerAteVirgula (BufferedReader argBuffer) throws IOException{
		String aux = "";
		for(char idChar = (char)argBuffer.read(); idChar != ',' && idChar != '\n'; idChar = (char)argBuffer.read()){
			aux += idChar;
		}
		return aux;
	}

	//Metodo Leitura
	public void ler(int id) {
		try{
			File file = new File("tmp/players.csv");
			BufferedReader argBuffer = new BufferedReader(new FileReader(file));
			int auxN = 0;
			String aux = "";
			do{
				aux = "";
				argBuffer.readLine();
				aux = lerAteVirgula(argBuffer);	
				auxN = charInt(aux);
			}while (id != auxN);
			
			setId(auxN);

			aux = "";
			aux = lerAteVirgula(argBuffer);
			setNome(aux);
			
			auxN = 0;
			aux = "";
			aux = lerAteVirgula(argBuffer);
			auxN = charInt(aux);
			setAltura(auxN);
	
			auxN = 0;
			aux = "";
			aux = lerAteVirgula(argBuffer);
			auxN = charInt(aux);
			setPeso(auxN);

			aux = "";
			aux = lerAteVirgula(argBuffer);
			setUniversidade(aux);
			
			auxN = 0;
			aux = "";
			aux = lerAteVirgula(argBuffer);
			auxN = charInt(aux);
			setAnoNascimento(auxN);
			
			aux = "";
			aux = lerAteVirgula(argBuffer);
			setCidadeNascimento(aux);

			aux = "";
			aux = lerAteVirgula(argBuffer);
			setEstadoNascimento(aux);

		} catch (IOException e){
			MyIO.println("Um erro ocorreu");
			e.printStackTrace();
		}
	}

	//Metodo imprimir 
	public void imprimir() {
		MyIO.println("["+id+" ## "+ nome + " ## " + altura + 
				" ## " + peso + " ## " + anoNascimento + 
				" ## " + universidade + " ## " + cidadeNascimento + 
				" ## " + estadoNascimento + "]");
}


	
}
public class Jogador3 {
	private static Jogador[] upArray(Jogador[] id, int tamanho){
		Jogador[] jogador = new Jogador[tamanho+1];
		for(int i=0; i<tamanho; i++){
			jogador[i] = id[i];
		}
		jogador[tamanho] = new Jogador();
		return jogador;
	}


	public static void main (String[] arg){
		try{
			//tempo
			long inicio = new Date().getTime();

			Jogador[] jogador = new Jogador[0];
			String id = "";
			String nome = "";
			id = MyIO.readLine();
			int tam = 0;
			int quantidadeComp = 0;
				
			//Buscar o ID e guardar as informações
			while(!id.equals("FIM")){
				jogador = upArray(jogador, tam);
				jogador[tam].ler(jogador[tam].charInt(id));
				tam++;
				id = MyIO.readLine();
			}
			
			//Buscar o nome listado pelo programa
			nome = MyIO.readLine();
			while(!nome.equals("FIM")){
				boolean tem = false;
				//buscando nome informado 
				for(int i=0; i<tam; i++){
					if((jogador[i].getNome()).equals(nome)){
						tem = true;
						i = tam;
					}
					quantidadeComp++;//somando quantidade de COMP
				}
				//printando se tem ou não 
				if(tem == true){
					MyIO.println("SIM");
				}else{
					MyIO.println("NAO");
				}
				nome = MyIO.readLine();
			}
		}catch (IOException e){
			e.printStackTrace();
        	}

	}
}



