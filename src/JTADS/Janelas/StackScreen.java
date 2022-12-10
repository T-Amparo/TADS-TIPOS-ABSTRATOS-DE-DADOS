package JTADS.Janelas;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import JTADS.Menu.MainScreen;
import TADS.TAD_Pilha.TAD_Pilha_Arranjo.Excecoes.EmptyStackExceptionArrayStack;
import TADS.TAD_Pilha.TAD_Pilha_Arranjo.Excecoes.FullStackException;
import TADS.TAD_Pilha.TAD_Pilha_Arranjo.Fontes.ArrayStack;
import TADS.TAD_Pilha.TAD_Pilha_LSE.Excecoes.EmptyStackExceptionNodeStack;
import TADS.TAD_Pilha.TAD_Pilha_LSE.Fontes.NodeStack;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

@SuppressWarnings("serial")
public class StackScreen extends JFrame {
	
	NodeStack<JButton> nodeStack;
	ArrayStack<JButton> arrayStack;
	
	public StackScreen() {
		
		//Configurações da janela.
		
		super("Pilha");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);		
		
		//Criação do texto de apresentação do TAD.
		
		String textStack = "\n=================================Introdução==========================================="+
		
							"\n\nUma pilha é um Tipo Abstrato de Dados que permite armazena uma coleção de elementos de \n"
							+ "forma linear, aonde os acessos na pilha são realizados em apenas uma de suas extremidades, \no topo da pilha."
							
							+ "\n\nA estrutura de uma pilha se define pelo padrão LIFO (O último a entrar é o primeiro a sair), "
							+ "\nesse padrão é justamente o que define a pilha, pois a pilha só permiti acesso a uma de suas "
							+ "\nextremidades o topo, nesse caso quando se necessita de um elemento da pilha, o mesmo será "
							+ "\nremovido do topo da pilha e quando for necessário inserir um novo elemento o mesmo será "
							+ "\ninserido no topo da pilha se tornando o novo topo da pilha."
		
							+ "\n\n==================================Métodos==========================================="
							+ "\n\nMétodos básicos da Pilha:" +
							
							"\n\nInserir/Push: O elemento que for inserido na pilha receberá uma posição no topo da pilha." + 
							"\n\nRemover/Pop: O elemento que estiver localizado no topo da pilha será removido." + 
							"\n\nConsultar/Top: O Elemento será encontrado no topo da pilha." + 
		
							"\n\n================================Aplicação=========================================" +
							
							"\nA pilha dispõe de muitas aplicações:" + 
							"\n\nPode ser usada para armazenar endereços web recentes." + 
							"\n\nPode ser usada para reversão de operações realizadas em sistemas, como o undo ou desfazer." + 
							
							"\n\n================================Implementação=========================================" +
							
							"\nPara esta implementação foi implementada duas variantes da pilha, uma que utiliza a "
							+ "\nLista Simplesmente Encadeada e outra que utiliza arranjo." + 
							
							"\n\nPor questões de desenvolvimento a implementação da Pilha trata-se de uma pilha de Strings, "
							+ "\npermitindo uso de todo e qualquer caractere da tabela ASCII e tratando a entrada de "
							+ "\nnúmeros como Strings.";
							
		//Inserção do texto em uma caixa de texto para exibição.
		
		JTextArea boxTextStack = new JTextArea(textStack);
		
		//Configuração da caixa de texto.
		
		boxTextStack.setEditable(false);
		boxTextStack.setPreferredSize(new Dimension(550, 600));	
		
		//Criação de botoes para a janela.
		
		JButton createStack = new JButton("Criar Pilha");
		
		//Insercão dos componentes na janela.
		
		add(boxTextStack);
		add(createStack);
		
		//Configuração dos botoes da janela.
		
		createStack.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createStack();}});
	}
	
	//Cria uma pilha e uma tela de representação para à pilha.
	public void createStack() {		

		//Janela de criação da pilha.
		
		Object[] options = {"Pilha com LSE", "Pilha com Arranjo"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione o Tipo de Pilha:", 
				"Tipos de Pilhas", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		//Cria à pilha escolhida pelo usuário.
		
		switch(option) {
		
			case 0: //Cria uma pilha LSE.
				
				nodeStack = new NodeStack<JButton>(); //Inicia a pilha LSE.
				
				createRepresentationScreen("Pilha LSE", nodeStack); //Cria uma janela para a pilha.
				
				break;
			
			case 1: //Cria uma pilha de arranjos.				
				
				try { //Trata uma possivel exceção.
					
					//Cria uma janela para coletar o tamanho da pilha de arranjos.
					
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
						
						arrayStack = new ArrayStack<JButton>(); //Inicia a pilha de arranjos com o tamanho padrão.
						
						JOptionPane.showMessageDialog(null, "A pilha foi iniciada com o valor padrão!", "Aviso", JOptionPane.WARNING_MESSAGE); //Mensagem de aviso.						
					}
						
					else {arrayStack = new ArrayStack<JButton>(size);} //Inicia a pilha de arranjos com o tamanho fornecido pelo usuário.					
					
					createRepresentationScreen("Pilha de Arranjos", arrayStack); //Cria uma janela para a pilha.
				
				}catch(Exception error) {}
				
				break;		
		}		
	}
	
	//Cria uma janela para representar uma pilha.
	public void createRepresentationScreen(String typeStack, NodeStack<JButton> stack) {
		
		//Cria uma janela para representar uma pilha LSE.
		
		JFrame screen = new JFrame();
		
		//Configurações da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeStack);
		screen.setSize(500, 115);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da pilha.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configuração do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 115));
		
		//Criação do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Criação dos botoes do menu.
		
		JMenuItem push = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem pop = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem top = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configuração dos botoes do menu.
		
		push.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {pushStack(stack, panel);}});
		pop.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {popStack(stack, panel);}});
		top.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {topStack(stack);}});
		
		//Insercão dos botoes no menu.
		
		menu.add(push);
		menu.add(pop);
		menu.add(top);		
		
		//Insercão do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inserção do scroll na janela.
		
		screen.add(scroll);
	}	
	
	//Cria uma janela para representar uma pilha de arranjos.
	public void createRepresentationScreen(String typeStack, ArrayStack<JButton> stack) {
		
		//Cria uma janela para representar uma pilha LSE.
		
		JFrame screen = new JFrame();
		
		//Configurações da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeStack);
		screen.setSize(500, 110);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da pilha.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configuração do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 110));
		
		//Criação do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Criação dos botoes do menu.
		
		JMenuItem push = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem pop = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem top = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configuração dos botoes do menu.
		
		push.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {pushStack(stack, panel);}});
		pop.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {popStack(stack, panel);}});
		top.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {topStack(stack);}});
		
		//Insercão dos botoes no menu.
		
		menu.add(push);
		menu.add(pop);
		menu.add(top);		
		
		//Insercão do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inserção do scroll na janela.
		
		screen.add(scroll);
	}
	
	//Adiciona um item na pilha LSE e na janela de representação da pilha.
	public void pushStack(NodeStack<JButton> stack, JPanel panel) {		
		
		//Cria uma janela para coletar o elemento que será adicionado na pilha.
		
		String element = (String) JOptionPane.showInputDialog(
				
				this.getParent(),
				"Insira um Novo Elemento:",
				"Inserção de Elementos",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null
		);
		
		if (element != null) {
			
			//Criação do botão que será inserido na pilha com o elemento inserido na tela de adição.
			
			JButton elementStack = new JButton(element);
			
			//Adiciona o botão criado na pilha.
			
			stack.push(elementStack);
			
			//Adiciona o botão criado na janela.
			panel.add(stack.top());
			
			//Recarrega a janela.
			refreshScreen(panel);
		}
	}
	
	//Adiciona um item na pilha de arranjos e na janela de representação da pilha.
	public void pushStack(ArrayStack<JButton> stack, JPanel panel) {		
		
		//Cria uma janela para coletar o elemento que será adicionado na pilha.
		
		String element = (String) JOptionPane.showInputDialog(
				
				this.getParent(),
				"Insira um Novo Elemento:",
				"Inserção de Elementos",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null
		);
		
		//Criação do botão que será inserido na pilha com o elemento inserido na tela de adição.
		
		JButton elementStack = new JButton(element);
		
		try { //Trata uma possivel exceção.
			
			if (element != null) {
				
				//Adiciona o botão criado na pilha.
				
				stack.push(elementStack);
				
				//Adiciona o botão criado na janela.
				panel.add(stack.top());
				
				//Recarrega a janela.
				refreshScreen(panel);
			}
		
		//Libera uma mensagem sobre o erro.
		} catch(FullStackException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}
	
	//Remove um item da pilha LSE e da janela de representação da pilha.
	public void popStack(NodeStack<JButton> stack, JPanel panel) {
		
		try { //Trata uma possivel exceção.
			
			//Remove o botão do topo da pilha.
			panel.remove(stack.pop());
			
			//Recarrega a janela.
			refreshScreen(panel);
			
		//Libera uma mensagem sobre o erro.	
		} catch(EmptyStackExceptionNodeStack error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}
	
	//Remove um item da pilha de arranjos e da janela de representação da pilha.
	public void popStack(ArrayStack<JButton> stack, JPanel panel) {
		
		try { //Trata uma possivel exceção.
			
			//Remove o botão do topo da pilha.
			panel.remove(stack.pop());
			
			//Recarrega a janela.
			refreshScreen(panel);
		
		//Libera uma mensagem sobre o erro.
		} catch(EmptyStackExceptionArrayStack error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}
	
	//Apresenta em uma janela o elemento do topo da pilha.
	public void topStack(NodeStack<JButton> stack) {
		
		//Trata uma possivel exceção.
		try {JOptionPane.showMessageDialog(null, "Elemento do Topo: (" + stack.top().getText() + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);} //Apresenta o elemento no topo da pilha.
		catch(EmptyStackExceptionNodeStack error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.		
	}	
	
	//Apresenta em uma janela o elemento do topo da pilha.
	public void topStack(ArrayStack<JButton> stack) {
		
		//Trata uma possivel exceção.
		try {JOptionPane.showMessageDialog(null, "Elemento do Topo: (" + stack.top().getText() + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);} //Apresenta o elemento no topo da pilha.
		catch(EmptyStackExceptionArrayStack error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.	
	}
	
	//Recarrega os componentes da janela.
	public void refreshScreen(JPanel screen) {
		
		//Métodos da janela para recarrega-lá.
		screen.revalidate();
		screen.repaint();		
	}
	
	public static void main(String[] args) {new MainScreen();}
	
}