package JTADS.Janelas;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import JTADS.Menu.MainScreen;
import TADS.TAD_Lista.TAD_Lista_Arranjo.Fontes.ArrayIndexList;
import TADS.TAD_Lista.TAD_Lista_Nodos.Excecoes.BoundaryViolationException;
import TADS.TAD_Lista.TAD_Lista_Nodos.Excecoes.EmptyListException;
import TADS.TAD_Lista.TAD_Lista_Nodos.Excecoes.InvalidPositionException;
import TADS.TAD_Lista.TAD_Lista_Nodos.Fontes.NodePositionList;
import TADS.TAD_Lista.TAD_Lista_Nodos.Interfaces.Position;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

@SuppressWarnings("serial")
public class NodeListScreen extends JFrame {
	
	NodePositionList<ArrayIndexList<JComponent>> nodeList;
	
	public NodeListScreen() {
		
		//Configura��es da janela.
		
		super("Lista de Nodos");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);
		
		//Cria��o do texto de apresenta��o do TAD.
		
		String textNodeList = "\n=================================Introdu��o==========================================="
								+ "\n\nA estrutura de uma Lista de Nodos � baseada na utiliza��o de nodos para se referir a um"
								+ "\ndeterminado elemento dentro da lista, a lista � composta de nodos que est�o linearmente "
								+ "\ninterligados e que armazenam o elemento na dada posi��o da lista e a refer�ncia do pr�ximo nodo."
								+ "\nCom essa nova abordagem uma serie de novas opera��es surgem."
								
								+ "\n\n==================================M�todos==========================================="
								
								+ "\n\nM�todos b�sicos da Lista de Nodos:"
								
								+ "\n\nInserir no in�cio/addFirst: Insere um nodo com o elemento informado no in�cio da lista."
								+ "\n\nInserir no final/addLast: Insere um nodo com o elemento informado no final da lista."
								+ "\n\nInserir depois/addAfter: Insere um nodo com o elemento informado depois de um dado nodo."
								+ "\n\nInserir antes/addBefore: Insere um nodo com o elemento informado antes de um dado nodo."
								+ "\n\nRemover/remove: Remove o nodo informado da lista de nodos."
								
								+ "\n\n================================Implementa��o========================================="
								
								+ "\n\nPor quest�es de desenvolvimento a implementa��o da Lista de Nodos trata-se de uma lista de"
								+ "\nStrings, permitindo uso de todo e qualquer caractere da tabela ASCII e tratando a entrada de "
								+ "\nn�meros como Strings."
								
								+ "\n\nPara melhora a manipula��o de nodos dentro da implementa��o foi atribuido para cada nodo \num �ndice de referencia.";	
		
		//Inser��o do texto em uma caixa de texto para exibi��o.
		
		JTextArea boxTextNodeList = new JTextArea(textNodeList);
		
		//Configura��o da caixa de texto.
		
		boxTextNodeList.setEditable(false);
		boxTextNodeList.setPreferredSize(new Dimension(550, 600));	
		
		//Cria��o de botoes para a janela.
		
		JButton createArrayList = new JButton("Criar Lista de Nodos");
		
		//Inserc�o dos componentes na janela.
		
		add(boxTextNodeList);
		add(createArrayList);
		
		//Configura��o dos botoes da janela.
		
		createArrayList.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createNodeList();}});
	}
	
	//Cria uma lista de nodos
	public void createNodeList() {
		
		nodeList = new NodePositionList<ArrayIndexList<JComponent>>(); //Cria uma lista de nodos.
		
		createRepresentationScreen("Lista de Nodos", nodeList);	//Cria uma janela para a lista de nodos.	
	}
	
	//Cria uma janela para representar uma lista de nodos.
	public void createRepresentationScreen(String typenodeList, NodePositionList<ArrayIndexList<JComponent>> nodeList) {		
		
		//Cria uma janela para representar uma lista de nodos.
		
		JFrame screen = new JFrame();
		
		//Configura��es da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typenodeList);
		screen.setSize(500, 145);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da lista de nodos.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configura��o do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 145));
		
		//Cria��o do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Cria��o dos botoes do menu.
		
		JMenu add = new JMenu("Adicionar");
		
		JMenuItem addFirst = new JMenuItem("Inicio", KeyEvent.VK_M);
		JMenuItem addLast = new JMenuItem("Final", KeyEvent.VK_M);
		JMenuItem addBefore = new JMenuItem("Antes", KeyEvent.VK_M);
		JMenuItem addAfter = new JMenuItem("Depois", KeyEvent.VK_M);
		
		add.add(addFirst);
		add.add(addLast);
		add.add(addBefore);
		add.add(addAfter);
		
		JMenu get = new JMenu("Consultar");		
		
		JMenuItem getFirst = new JMenuItem("Inicio", KeyEvent.VK_M);
		JMenuItem getLast = new JMenuItem("Final", KeyEvent.VK_M);
		JMenuItem getBefore = new JMenuItem("Antes", KeyEvent.VK_M);
		JMenuItem getAfter = new JMenuItem("Depois", KeyEvent.VK_M);		
		
		get.add(getFirst);
		get.add(getLast);
		get.add(getBefore);
		get.add(getAfter);
		
		JMenuItem set = new JMenuItem("Trocar", KeyEvent.VK_M);
		
		JMenuItem remove = new JMenuItem("Remover", KeyEvent.VK_M);
		
		//Configura��o dos botoes do menu.
		
		addFirst.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {addBasicNodeList(nodeList, -1, 0, panel);}});
		addLast.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {addBasicNodeList(nodeList, -1, 1, panel);}});
		addBefore.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {addComplexNodeList(nodeList, -1, 0, panel);}});
		addAfter.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {addComplexNodeList(nodeList, -1, 1, panel);}});
		
		getFirst.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {getNodeList(nodeList, -1, 0, panel);}});
		getLast.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {getNodeList(nodeList, -1, 1, panel);}});
		getBefore.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {getNodeList(nodeList, -1, 2, panel);}});
		getAfter.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {getNodeList(nodeList, -1, 3, panel);}});
		
		set.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {setNodeList(nodeList, -1, panel);}});
		
		remove.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {removeNodeList(nodeList, -1, panel);}});
		
		//Inserc�o dos botoes no menu.
		
		menu.add(add);
		menu.add(get);
		menu.add(remove);
		menu.add(set);
		
		//Inserc�o do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inser��o do scroll na janela.
		
		screen.add(scroll);		
	}
	
	//Adiciona na lista no inicio ou no final.
	public void addBasicNodeList(NodePositionList<ArrayIndexList<JComponent>> nodeList, int indexNodeList, int typeAdd, JPanel panel) {		
		
		//Cria uma janela para coletar o elemento que ser� adicionado na lista.
		
		String element = (String) JOptionPane.showInputDialog(
				
				this.getParent(),
				"Insira um Novo Elemento:",
				"Inser��o de Elementos",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null
		);
		
		if(element != null) {			
			
			//Cria uma lista arranjo para conter os componentes de exibi��o de uma dada posi��o da lista de nodos.
			
			ArrayIndexList<JComponent> components = new ArrayIndexList<JComponent>(3);
			
			//Cria��o do painel que ser� inserido na lista de nodos com o painel inserido na tela de adi��o.
			
			JPanel panelElement = new JPanel(new GridLayout(2, 1));		
			
			//Cria��o do bot�o que ser� inserido na lista com o elemento inserido na tela de adi��o.
			
			JButton elementList = new JButton(element);
			
			JLabel indexList = new JLabel(String.valueOf(indexNodeList), SwingConstants.CENTER);
			
			//Adiciona os componentes criado a lista arranjo de componentes.
			
			components.add(0, indexList);
			components.add(1, elementList);
			components.add(2, panelElement);			
			
			//Configura��o dos elementos do painel.
			
			elementList.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(nodeList, Integer.parseInt(((JLabel)components.get(0)).getText()), panel);}});
			
			//Adiciona os elementos no painel.
			
			panelElement.add(elementList);
			panelElement.add(indexList);
			
			try {
				
				if(typeAdd == 0) {
					
					//Adiciona o painel criado no inicio da lista.
					
					nodeList.addFirst(components);
				
				} else {
					
					//Adiciona o painel criado no final da lista.
					
					nodeList.addLast(components);					
				}
				
				//Adiciona o painel criado na janela.
				
				panel.add(panelElement);				
				
				//Recarrega a janela.
				
				refreshScreen(panel);
				
			} catch(Exception error) {}			
		}		
	}	
	
	//Adiciona na lista depois ou antes do �ndica informado.
	public void addComplexNodeList(NodePositionList<ArrayIndexList<JComponent>> nodeList, int indexNodeList, int typeAdd, JPanel panel) {				
		
		//Cria uma janela para coletar os elementos que ser�o adicionados na lista de nodos.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 4));		
		
		//Cria��o dos botoes da janela.
		
		panelEntry.add(new JLabel("Indice: "));
		panelEntry.add(new JLabel("Elemento: "));
		
		JTextField entryIndex;
		JTextField entryElement = new JTextField();
		
		if(indexNodeList == -1) {entryIndex = new JTextField();}
		else {entryIndex = new JTextField(String.valueOf(indexNodeList));}
		
		//Configura��o dos botoes da janela.
		
		panelEntry.add(entryIndex);
		panelEntry.add(entryElement);		
		
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
		
		if(option == 0) {			
			
			//Cria uma lista arranjo para conter os componentes de exibi��o de uma dada posi��o da lista de nodos.
			
			ArrayIndexList<JComponent> components = new ArrayIndexList<JComponent>(3);
			
			//Cria��o do painel que ser� inserido na lista de nodos com o painel inserido na tela de adi��o.
			
			JPanel panelElement = new JPanel(new GridLayout(2, 1));		
			
			//Cria��o do bot�o que ser� inserido na lista com o elemento inserido na tela de adi��o.
			
			JButton elementList = new JButton(String.valueOf(entryElement.getText()));
			
			JLabel indexList = new JLabel(String.valueOf(entryIndex), SwingConstants.CENTER);
			
			//Configura��o dos elementos do painel.
			
			elementList.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {buttonManagerScreen(nodeList, Integer.parseInt(((JLabel)components.get(0)).getText()), panel);}});
			
			//Adiciona os componentes criado a lista arranjo de componentes.
			
			components.add(0, indexList);
			components.add(1, elementList);
			components.add(2, panelElement);
			
			//Adiciona os elementos no painel.
			
			panelElement.add(elementList);
			panelElement.add(indexList);
			
			try { //Trata uma possivel exce��o.
				
				//Recupera a primeira posi��o da lista.
				
				Position<ArrayIndexList<JComponent>> current = nodeList.first();
				
				//Armazena a posi��o encontrada.
				
				Position<ArrayIndexList<JComponent>> find = null;
				
				//Percorre a lista de nodos.
				
				for(int i = 0; i < nodeList.size(); i++) {
					
					if(i == Integer.parseInt(String.valueOf(entryIndex.getText()))) {find = current;}
					
					if(i < nodeList.size()-1) {current = nodeList.next(current);}
				}
				
				//Adiciona o elemento a lista de acordo com a op��o escolhida pelo usu�rio.
				
				if(typeAdd == 0) {nodeList.addBefore(find, components);}
				else {nodeList.addAfter(find, components);}
				
				//Recarrega a janela.
				refreshScreen(panel);				
			} 
			
			//Libera uma mensagem sobre o erro.
			catch(EmptyListException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.
			
			//Libera uma mensagem sobre o erro.
			catch(InvalidPositionException error) {JOptionPane.showMessageDialog(null, "Posi��o Invalida!", "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.
		}	
	}
	
	//Consulta o elemento da lista e exibi em uma janela.
	public void getNodeList(NodePositionList<ArrayIndexList<JComponent>> nodeList, int indexNodeList, int typeAdd, JPanel panel) {
		
		try { //Trata uma possivel exce��o.
			
			//Consulta o primeiro elemento da lista de nodos.
			
			if(typeAdd == 0) {JOptionPane.showMessageDialog(null, "Elemento: (" + ((JButton)nodeList.first().element().get(1)).getText() + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);}
			
			//Consulta o ultimo elemento da lista de nodos.
			
			else if(typeAdd == 1) {JOptionPane.showMessageDialog(null, "Elemento: (" + ((JButton)nodeList.last().element().get(1)).getText() + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);}
			
			else {			
				
				//Cria uma janela para coletar os elementos que ser�o adicionados na lista de nodos.
				
				JPanel panelEntry = new JPanel(new GridLayout(2, 2));		
				
				//Cria��o dos botoes da janela.
				
				panelEntry.add(new JLabel("�ndice: "));
				
				JTextField entryIndex;
				
				if(indexNodeList == -1) {entryIndex = new JTextField();}
				else {entryIndex = new JTextField(String.valueOf(indexNodeList));}
				
				//Configura��o dos botoes da janela.
				
				panelEntry.add(entryIndex);		
				
				Object[] options = {"OK", "Cancelar"};
				
				int option = JOptionPane.showOptionDialog(
						
						this.getParent(), 
						panelEntry, 
						"Insira o �ndice do Elemento:", 
						JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, 
						null, 
						options,
						options				
				); 
				
				if(option == 0) {
					
					//Recupera a primeira posi��o da lista.
					
					Position<ArrayIndexList<JComponent>> current = nodeList.first();
					
					//Armazena a posi��o encontrada.
					
					Position<ArrayIndexList<JComponent>> find = null;
					
					//Percorre a lista de nodos.
					
					for(int i = 0; i < nodeList.size(); i++) {
						
						if(i == Integer.parseInt(String.valueOf(entryIndex.getText()))) {find = current;}
						
						if(i < nodeList.size()-1) {current = nodeList.next(current);}
					}
					
					//Consulta o elemento anterior ao indice informado.
					
					if(typeAdd == 2) {JOptionPane.showMessageDialog(null, "Elemento: (" + ((JButton)nodeList.prev(find).element().get(1)).getText() + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);}
					
					//Consulta o elemento seguinta ao indice informado.
					
					else {JOptionPane.showMessageDialog(null, "Elemento: (" + ((JButton)nodeList.next(find).element().get(1)).getText() + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);}	
				}
			}
		}		
		
		//Libera uma mensagem sobre o erro.
		catch(EmptyListException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.
		
		//Libera uma mensagem sobre o erro.
		catch(InvalidPositionException error) {JOptionPane.showMessageDialog(null, "Posi��o Invalida!", "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.		
		
		//Libera uma mensagem sobre o erro.
		catch(BoundaryViolationException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.
	}
	
	//Troca o elemento de uma determinada posi��o da lista.
	public void setNodeList(NodePositionList<ArrayIndexList<JComponent>> nodeList, int indexNodeList, JPanel panel) {		
		
		//Cria uma janela para coletar os elementos que ser�o adicionados na lista de nodos.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 4));		
		
		//Cria��o dos botoes da janela.
		
		panelEntry.add(new JLabel("�ndice: "));
		panelEntry.add(new JLabel("Elemento: "));
		
		JTextField entryIndex;
		JTextField entryElement = new JTextField();
		
		if(indexNodeList == -1) {entryIndex = new JTextField();}
		else {entryIndex = new JTextField(String.valueOf(indexNodeList));}
		
		//Configura��o dos botoes da janela.
		
		panelEntry.add(entryIndex);
		panelEntry.add(entryElement);		
		
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
		
		if(option == 0) {
			
			try {
				
				//Recupera a primeira posi��o da lista.
				
				Position<ArrayIndexList<JComponent>> current = nodeList.first();
				
				//Armazena a posi��o encontrada.
				
				Position<ArrayIndexList<JComponent>> find = null;
				
				//Percorre a lista de nodos.
				
				for(int i = 0; i < nodeList.size(); i++) {
					
					if(i == Integer.parseInt(String.valueOf(entryIndex.getText()))) {find = current;}
					
					if(i < nodeList.size()-1) {current = nodeList.next(current);}
				}
				
				//Troca o elemento do indice informado pelo usu�rio pelo novo valor.
				
				((JButton)find.element().get(1)).setText(entryElement.getText());
				
				//Recarrega a janela.
				
				refreshScreen(panel);
				
			}			
			
			//Libera uma mensagem sobre o erro.
			catch(EmptyListException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.
			
			//Libera uma mensagem sobre o erro.
			catch(InvalidPositionException error) {JOptionPane.showMessageDialog(null, "Posi��o Invalida!", "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.		
			
			//Libera uma mensagem sobre o erro.
			catch(BoundaryViolationException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.
			
			//Libera uma mensagem sobre o erro.
			catch(Exception error) {JOptionPane.showMessageDialog(null, "�ndice Invalido", "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.
		}		
	}
	
	//Remove o elemento da lista de nodos.
	public void removeNodeList(NodePositionList<ArrayIndexList<JComponent>> nodeList, int indexNodeList, JPanel panel) {		
		
		//Cria uma janela para coletar os elementos que ser�o adicionados na lista de nodos.
		
		JPanel panelEntry = new JPanel(new GridLayout(2, 2));		
		
		//Cria��o dos botoes da janela.
		
		panelEntry.add(new JLabel("�ndice: "));
		
		JTextField entryIndex;
		
		if(indexNodeList == -1) {entryIndex = new JTextField();}
		else {entryIndex = new JTextField(String.valueOf(indexNodeList));}
		
		//Configura��o dos botoes da janela.
		
		panelEntry.add(entryIndex);		
		
		Object[] options = {"OK", "Cancelar"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				panelEntry, 
				"Insira o �ndice do Elemento:", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options,
				options				
		);
		
		if(option == 0) {
			
			try { //Trata uma possivel exce��o.			
				
				//Recupera a primeira posi��o da lista.
				
				Position<ArrayIndexList<JComponent>> current = nodeList.first();
				
				//Armazena a posi��o encontrada.
				
				Position<ArrayIndexList<JComponent>> find = null;
				
				//Percorre a lista de nodos.
				
				for(int i = 0; i < nodeList.size(); i++) {
					
					if(i == Integer.parseInt(String.valueOf(entryIndex.getText()))) {find = current;}
					
					if(i < nodeList.size()-1) {current = nodeList.next(current);}
				}
				
				//Remove o elemento da lista de acordo com o �ndice informado pelo usu�rio.
				
				nodeList.remove(find);			
				
				//Recarrega a janela.
				
				refreshScreen(panel);	
			}		
			
			//Libera uma mensagem sobre o erro.
			catch(EmptyListException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.
			
			//Libera uma mensagem sobre o erro.
			catch(InvalidPositionException error) {JOptionPane.showMessageDialog(null, "Posi��o Invalida!", "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.			
			
			//Libera uma mensagem sobre o erro.
			catch(Exception error) {JOptionPane.showMessageDialog(null, "�ndice Invalido!", "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.
		}
	}	
	
	//Exibi uma tela de op��es ao clicar no elementos da lista.
	public void buttonManagerScreen(NodePositionList<ArrayIndexList<JComponent>> nodeList, int indexNodeList, JPanel panel) {		
		
		//Cria uma janela de op��es.
		
		Object[] options = {"Adicionar", "Trocar", "Remover"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione uma Op��o: ", 
				"M�todos Lista Arranjo", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		//Defini qual m�todo ser� chamado de acordo com a op��o escolhida.
		
		if (option == 0) {			
			
			//Cria uma janela de op��es.
			
			Object[] optionsAdd = {"Antes", "Depois"};
			
			option = JOptionPane.showOptionDialog(
					
					this.getParent(), 
					"Adicionar: ", 
					"M�todos de Adi��o", 
					JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					optionsAdd, 
					optionsAdd
			);
			
			addComplexNodeList(nodeList, indexNodeList, option, panel); //Adiciona o elemento segundo as inform��es do usu�rio.			
		}
		
		else if (option == 1) {setNodeList(nodeList, indexNodeList, panel);} //Trocar um elemento de um dado �ndice da lista.
		else if (option == 2) {removeNodeList(nodeList, indexNodeList, panel);} //Remover da lista, usando o mesmo �ndice.		
	}
	
	//Recarrega os componentes da janela.
	public void refreshScreen(JPanel panel) {		
		
		//Remove todos os componentes da janela.
		
		panel.removeAll();
		
		//Percorre a lista de nodos e adiciona na janela os componentes contidos na lista arranjo.
		
		int index = 0;
		
		for(ArrayIndexList<JComponent> element : nodeList) {
			
			((JLabel)element.get(0)).setText(String.valueOf(index));
			
			panel.add(((JPanel)element.get(2)));
			
			index++;
		}		
		
		//M�todos da janela para recarrega-l�.
		
		panel.revalidate();
		panel.repaint();		
	}
	
	public static void main(String[] args) {new MainScreen();}

}