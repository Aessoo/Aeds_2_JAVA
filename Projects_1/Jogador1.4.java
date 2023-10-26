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

class Jogador9 {
    static int quantMov = 0;
    static int quantidadeComp = 0;

    private static Jogador[] upArray(Jogador[] id, int tamanho){
        Jogador[] jogador = new Jogador[tamanho+1];
        for(int i=0; i<tamanho; i++){
            jogador[i] = id[i];
        }
        jogador[tamanho] = new Jogador();
        return jogador;
    }

    public static void buildHeap(Jogador[] array, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            createHeap(array, n, i);
        }
    }

    public static void createHeap(Jogador[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1; 
        int right = 2 * i + 2;
        if (left < n && array[left].getAltura() > array[largest].getAltura()) {
            largest = left;
        } else if (left < n && array[left].getAltura() == array[largest].getAltura()) {
            quantidadeComp++;
            if (array[left].getNome().compareTo(array[largest].getNome()) > 0) {
                largest = left;
            }
        }

        if (right < n && array[right].getAltura() > array[largest].getAltura()) {
            largest = right;
        } else if (right < n && array[right].getAltura() == array[largest].getAltura()) {
            quantidadeComp++;
            if (array[right].getNome().compareTo(array[largest].getNome()) > 0) {
                largest = right;
            }
        }

        if (largest != i) {
            Jogador swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            quantMov += 3;
            createHeap(array, n, largest);
        }
    }
    public static void heapSort(Jogador[] array, int n) {
        buildHeap(array, n);
        for (int i = n - 1; i > 0; i--) {
            Jogador temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            quantMov += 3;
            createHeap(array, i, 0);
        }
    }

    public static void main (String[] arg){
        try{
            Jogador[] jogador = new Jogador[0];
            String id = "";
            String nome = "";
            id = MyIO.readLine();
            int tam = 0;
                
            //Buscar o ID e guardar as informacoes
            while(!id.equals("FIM")){
                jogador = upArray(jogador, tam);
                jogador[tam].ler(jogador[tam].charInt(id));
                tam++;
                id = MyIO.readLine();
            }
            long inicio = new Date().getTime();  
            heapSort(jogador, tam); // Chama o método de ordenação Heapsort
            long fim = new Date().getTime();      

            for (int i = 0; i < tam; i++) {
                jogador[i].imprimir();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

