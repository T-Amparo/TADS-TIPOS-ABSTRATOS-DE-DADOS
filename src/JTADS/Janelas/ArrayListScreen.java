package JTADS.Janelas;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import JTADS.Menu.MainScreen;
import TADS.TAD_Lista.TAD_Lista_Arranjo.Fontes.ArrayIndexList;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

@SuppressWarnings("serial")
public class ArrayListScreen extends JFrame {
	
	ArrayIndexList<ArrayIndexList<JComponent>> arrayList;
	
	public ArrayListScreen() {
		
		//Configurações da janela.
		
		super("Lista Arranjo");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);		
		
		//Criação do texto de apresentação do TAD.
		
		String textArrayList = "\n=================================Introdução===========================================" +
				
								"\n\nUma Lista de Arranjos é um dos Tipos Abstratos de Dados mais simples que existe, "
								+ "com ele é \npossível armazenar uma coleção de elementos de forma linear."
				
								+ "\n\nA estrutura da Lista de Arranjos se inicia com um tamanho predefinido, "
								+ "e tem seus elementos \ninseridos e acessados por meio de índices, iniciando-se sempre "
								+ "pelo índice 0 e se auto \norganizando de acordo com as novas entradas ou remoções."
								
								+ "\n\n==================================Métodos===========================================" 
								
								+ "\n\nMétodos básicos da Lista de Arranjos:\r\n" +
								
								"\nInserir: Os elementos são inseridos na posição informada da lista, e todos os demais "
								+ "\nelementos se reorganizam de acordo com a entrada do novo elemento.\r\n" +
								
								"\nRemover: Os elementos são removidos da posição informada da lista, e todos os \ndemais "
								+ "elementos se reorganizam de acordo com a saída do elemento.\r\n" +
								
								"\nConsulta: Os elementos são encontrados na lista de acordo com a posição deles dentro \nda mesma.\r\n" +
								
								"\n================================Implementação=========================================" +  
								
								"\n\nNesta implementação fizemos uso do Arranjo Extensível, um algoritmo que permite que o "
								+ "\narranjo dobre de tamanho sempre que atinja seu tamanho máximo. \r\n" +
								
								"\nPor questões de desenvolvimento a implementação da Lista Arranjo trata-se de uma "
								+ "lista de \nStrings, permitindo uso de todo e qualquer caractere da tabela ASCII e "
								+ "tratando a entrada de \nnúmeros como String.\r\n";
		
		//Inserção do texto em uma caixa de texto para exibição.
		
		JTextArea boxTextArrayList = new JTextArea(textArrayList);
		
		//Configuração da caixa de texto.
		
		boxTextArrayList.setEditable(false);
		boxTextArrayList.setPreferredSize(new Dimension(550, 600));	
		
		//Criação de botoes para a janela.
		
		JButton createArrayList = new JButton("Criar Lista Arranjo");
		
		//Insercão dos componentes na janela.
		
		add(boxTextArrayList);
		add(createArrayList);
		
		//Configuração dos botoes da janela.
		
		createArrayList.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createArrayList();}});
	}
	
	//Cria uma lista arranjo e uma tela de representação para à lista arranjo.
	public void createArrayList() {		
		
		try { //Trata uma possivel exceção.
			
			//Cria uma janela para coletar o tamanho da lista arranjo.
			
			int size = Integer.parseInt((String) JOptionPane.showInputDialog(
					
					this.getParent(),
					"Defina o Tamanho do Arranjo:",
					"Definação de Tamanho",
					JOptionPane.PLAIN_MESSAGE,
					null,
					null,
					null
			));
			
			if (size == 0) { 
				
				arrayList = new ArrayIndexList<ArrayIndexList<JComponent>>(); //Inicia a lista arranjo com o tamanho padrão.
				
				JOptionPane.showMessageDialog(null, "A lista arranjo foi iniciada com o valor padrão!", "Aviso", JOptionPane.WARNING_MESSAGE); //Mensagem de aviso.						
			}
				
			else {arrayList = new ArrayIndexList<ArrayIndexList<JComponent>>(size);} //Inicia a lista arranjo com o tamanho fornecido pelo usuário.					
			
			createRepresentationScreen("Lista Arranjo", arrayList); //Cria uma janela para a lista arranjo.
		
		}catch(Exception error) {}		
	}	
	
	//Cria uma janela para representar uma lista arranjo.
	public void createRepresentationScreen(String typeArrayList, ArrayIndexList<ArrayIndexList<JComponent>> arrayList) {
		
		//Cria uma janela para representar uma lista arranjo.
		
		JFrame screen = new JFrame();
		
		//Configurações da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeArrayList);
		screen.setSize(500, 145);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da lista arranjo.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configuração do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 145));
		
		//Criação do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Criação dos botoes do menu.
		
		JMenuItem add = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem remove = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem get = new JMenuItem("Consultar", KeyEvent.VK_M);
		JMenuItem set = new JMenuItem("Trocar", KeyEvent.VK_M);
		
		//Configuração dos botoes do menu.
		
		add.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {addArrayList(arrayList, arrayList.size(), panel);}});
		remove.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeArrayList(arrayList, -1, panel);}});
		get.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {getArrayList(arrayList, -1, panel);}});
		set.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {setArrayList(arrayList, -1, panel);}});
		
		//Insercão dos botoes no menu.
		
		menu.add(add);
		menu.add(remove);
		menu.add(get);
		menu.add(set);
		
		//Insercão do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inserção do scroll na janela.
		
		screen.add(scroll);
	}	
	
	//Cria uma janela para inserir elementos na lista arranjo.
	public void addArrayList(ArrayIndexList<ArrayIndexList<JComponent>> arrayList, int indexArrayList, JPanel panel) {
		
		//Cria uma lista arranjo para conter os componentes de exibição de uma dada posição da lista arranjo.
		
		ArrayIndexList<JComponent> components = new ArrayIndexList<JComponent>(3);
		
		//Cria uma janela para coletar o elemento que será adicionado na lista arranjo.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 4));		
		
		//Criação dos botoes da janela.
		
		panelEntry.add(new JLabel("Indice"));
		panelEntry.add(new JLabel("Elemento"));
		
		JTextField index = new JTextField(String.valueOf(indexArrayList));
		JTextField element = new JTextField();
		
		//Configuração dos botoes da janela.
		
		panelEntry.add(index);
		panelEntry.add(element);		
		
		Object[] options = {"OK", "Cancelar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				panelEntry, 
				"Insira um Novo Elemento:", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options,
				options				
		);
		
		if (option == 0) {
			
			//Criação do painel que será inserido na lista arranjo com o painel inserido na tela de adição.
			
			JPanel panelElement = new JPanel(new GridLayout(2, 1));		
			
			//Criação dos elementos do painel, que armazenarão os dados do usuário.
			
			JButton paneElementArrayList = new JButton(String.valueOf(element.getText()));
			JLabel paneIndexArrayList = new JLabel(String.valueOf(index.getText()), SwingConstants.CENTER);
			
			//Adiciona os componentes criado a lista arranjo de componentes.
			
			components.add(0, paneElementArrayList);
			components.add(1, paneIndexArrayList);
			components.add(2, panelElement);
			
			//Configuração dos elementos do painel.
			
			paneElementArrayList.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(arrayList, Integer.parseInt(((JLabel)components.get(1)).getText()), panel);}});
			
			//Adiciona os elementos no painel.
			
			panelElement.add(paneElementArrayList);
			panelElement.add(paneIndexArrayList);
			
			try { //Trata uma possivel exceção.
				
				//Adiciona o painel criado na lista.
				
				arrayList.add(Integer.parseInt(index.getText()), components);
				
				//Adiciona o painel criado na janela.
				
				panel.add(panelElement);
				
				//Recarrega a janela.
				
				refreshScreen(panel);
			}
			
			//Libera um mensagem de erro.
			catch(IndexOutOfBoundsException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
			
			//Libera um mensagem de erro.
			catch(Exception error) {JOptionPane.showMessageDialog(null, "Índice Inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);}
		}
	}
	
	//Cria uma janela para remover elementos da lista arranjo.
	public void removeArrayList(ArrayIndexList<ArrayIndexList<JComponent>> arrayList, int indexArrayList, JPanel panel) {
		
		//Cria uma janela para coletar o indice do elemento que será removido da lista arranjo.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 2));		
		
		//Criação dos botoes da janela.
		
		panelEntry.add(new JLabel("Indice: "));		
		
		JTextField index; 
		
		if(indexArrayList == -1) {index = new JTextField();}
		else {index = new JTextField(String.valueOf(indexArrayList));}
		
		//Configuração dos botoes da janela.
		
		panelEntry.add(index);	
		
		Object[] options = {"OK", "Cancelar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				panelEntry, 
				"Insira o Indice do Elemento:", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options,
				options				
		);
		
		if(option == 0) {
			
			//Trata uma possivel exceção.
			try {arrayList.remove(Integer.parseInt(index.getText())); refreshScreen(panel);} //Remove o elemento da lista arranjo e atualiza a janela.
			
			//Libera um mensagem de erro.
			catch(IndexOutOfBoundsException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
			
			//Libera um mensagem de erro.
			catch(Exception error) {JOptionPane.showMessageDialog(null, "Índice Inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);}
		}		
	}
	
	//Cria uma janela para consultar elementos da lista arranjo.
	public void getArrayList(ArrayIndexList<ArrayIndexList<JComponent>> arrayList, int indexArrayList, JPanel panel) {		
		
		//Cria uma janela para coletar o indice do elemento que será exibido na janela.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 2));		
		
		//Criação dos botoes da janela.
		
		panelEntry.add(new JLabel("Indice: "));		
		
		JTextField index; 
		
		if(indexArrayList == -1) {index = new JTextField();}
		else {index = new JTextField(String.valueOf(indexArrayList));}
		
		//Configuração dos botoes da janela.
		
		panelEntry.add(index);	
		
		Object[] options = {"OK", "Cancelar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				panelEntry, 
				"Insira o Indice do Elemento:", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options,
				options				
		);
		
		if(option == 0) {
			
			//Trata uma possivel exceção.
			try {
				
				String element = ((JButton)arrayList.get(Integer.parseInt(index.getText())).get(0)).getText();
				String message = "Índice [" + index.getText() + "], " + "Elemento ["+ element + "]";				
				
				JOptionPane.showMessageDialog(null, message, "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE); //Exibe uma janela com o indice e o elemento.				
			}
			
			//Libera um mensagem de erro.
			catch(IndexOutOfBoundsException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
			
			//Libera um mensagem de erro.
			catch(Exception error) {JOptionPane.showMessageDialog(null, "Índice Inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);}
		}		
	}
	
	//Cria uma janela para trocar elementos na lista arranjo.
	public void setArrayList(ArrayIndexList<ArrayIndexList<JComponent>> arrayList, int indexArrayList, JPanel panel) {		
		
		//Cria uma janela para coletar o indice do elemento que será exibido na janela.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 4));		
		
		//Criação dos botoes da janela.
		
		panelEntry.add(new JLabel("Indice: "));
		panelEntry.add(new JLabel("Elemento"));
		
		JTextField index;
		JTextField element = new JTextField();
		
		if(indexArrayList == -1) {index = new JTextField();}
		else {index = new JTextField(String.valueOf(indexArrayList));}
		
		//Configuração dos botoes da janela.
		
		panelEntry.add(index);
		panelEntry.add(element);
		
		Object[] options = {"OK", "Cancelar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				panelEntry, 
				"Insira o Indice do Elemento:", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options,
				options				
		);
		
		if(option == 0) {
			
			//Trata uma possivel exceção.
			try {((JButton)arrayList.get(Integer.parseInt(index.getText())).get(0)).setText(element.getText());} //Altera o elemento contido na Lista Arranjo
			
			//Libera um mensagem de erro.
			catch(IndexOutOfBoundsException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
			
			//Libera um mensagem de erro.
			catch(Exception error) {JOptionPane.showMessageDialog(null, "Índice Inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);}
		}		
	}
	
	//Exibi uma tela de opções ao clicar no elementos da lista.
	public void buttonManagerScreen(ArrayIndexList<ArrayIndexList<JComponent>> arrayList, int indexArrayList, JPanel panel) {		
		
		//Cria uma janela de opções.
		
		Object[] options = {"Adicionar", "Trocar", "Remover"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione uma Opção: ", 
				"Métodos Lista Arranjo", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		//Defini qual método será chamado de acordo com a opção escolhida.
		
		if (option == 0) {addArrayList(arrayList,  indexArrayList, panel);} //Adicionar na lista, usando o mesmo índice.
		else if (option == 1) {setArrayList(arrayList, indexArrayList, panel);} //Trocar um elemento de um dado índice da lista.
		else if (option == 2) {removeArrayList(arrayList, indexArrayList, panel);} //Remover da lista, usando o mesmo índice.		
	}
	
	//Recarrega os componentes da janela.
	public void refreshScreen(JPanel panel) {
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();
		
		//Percorre a lista arranjo e adiciona na janela os componentes contidos na lista arranjo.
		
		for(int i = 0; i < arrayList.size(); i++) {
			
			JLabel label = (JLabel) arrayList.get(i).get(1);
			label.setText(String.valueOf(i));			
			
			panel.add(arrayList.get(i).get(2));			
		}
		
		//Métodos da janela para recarrega-lá.
		
		panel.revalidate();
		panel.repaint();		
	}
	
	public static void main(String[] args) {new MainScreen();}

}