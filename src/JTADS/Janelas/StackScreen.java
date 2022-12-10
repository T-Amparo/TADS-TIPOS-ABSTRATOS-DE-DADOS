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
		
		//Configura��es da janela.
		
		super("Pilha");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);		
		
		//Cria��o do texto de apresenta��o do TAD.
		
		String textStack = "\n=================================Introdu��o==========================================="+
		
							"\n\nUma pilha � um Tipo Abstrato de Dados que permite armazena uma cole��o de elementos de \n"
							+ "forma linear, aonde os acessos na pilha s�o realizados em apenas uma de suas extremidades, \no topo da pilha."
							
							+ "\n\nA estrutura de uma pilha se define pelo padr�o LIFO (O �ltimo a entrar � o primeiro a sair), "
							+ "\nesse padr�o � justamente o que define a pilha, pois a pilha s� permiti acesso a uma de suas "
							+ "\nextremidades o topo, nesse caso quando se necessita de um elemento da pilha, o mesmo ser� "
							+ "\nremovido do topo da pilha e quando for necess�rio inserir um novo elemento o mesmo ser� "
							+ "\ninserido no topo da pilha se tornando o novo topo da pilha."
		
							+ "\n\n==================================M�todos==========================================="
							+ "\n\nM�todos b�sicos da Pilha:" +
							
							"\n\nInserir/Push: O elemento que for inserido na pilha receber� uma posi��o no topo da pilha." + 
							"\n\nRemover/Pop: O elemento que estiver localizado no topo da pilha ser� removido." + 
							"\n\nConsultar/Top: O Elemento ser� encontrado no topo da pilha." + 
		
							"\n\n================================Aplica��o=========================================" +
							
							"\nA pilha disp�e de muitas aplica��es:" + 
							"\n\nPode ser usada para armazenar endere�os web recentes." + 
							"\n\nPode ser usada para revers�o de opera��es realizadas em sistemas, como o undo ou desfazer." + 
							
							"\n\n================================Implementa��o=========================================" +
							
							"\nPara esta implementa��o foi implementada duas variantes da pilha, uma que utiliza a "
							+ "\nLista Simplesmente Encadeada e outra que utiliza arranjo." + 
							
							"\n\nPor quest�es de desenvolvimento a implementa��o da Pilha trata-se de uma pilha de Strings, "
							+ "\npermitindo uso de todo e qualquer caractere da tabela ASCII e tratando a entrada de "
							+ "\nn�meros como Strings.";
							
		//Inser��o do texto em uma caixa de texto para exibi��o.
		
		JTextArea boxTextStack = new JTextArea(textStack);
		
		//Configura��o da caixa de texto.
		
		boxTextStack.setEditable(false);
		boxTextStack.setPreferredSize(new Dimension(550, 600));	
		
		//Cria��o de botoes para a janela.
		
		JButton createStack = new JButton("Criar Pilha");
		
		//Inserc�o dos componentes na janela.
		
		add(boxTextStack);
		add(createStack);
		
		//Configura��o dos botoes da janela.
		
		createStack.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createStack();}});
	}
	
	//Cria uma pilha e uma tela de representa��o para � pilha.
	public void createStack() {		

		//Janela de cria��o da pilha.
		
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
		
		//Cria � pilha escolhida pelo usu�rio.
		
		switch(option) {
		
			case 0: //Cria uma pilha LSE.
				
				nodeStack = new NodeStack<JButton>(); //Inicia a pilha LSE.
				
				createRepresentationScreen("Pilha LSE", nodeStack); //Cria uma janela para a pilha.
				
				break;
			
			case 1: //Cria uma pilha de arranjos.				
				
				try { //Trata uma possivel exce��o.
					
					//Cria uma janela para coletar o tamanho da pilha de arranjos.
					
					int size = Integer.parseInt((String) JOptionPane.showInputDialog(
							
							this.getParent(),
							"Defina o Tamanho do Arranjo:",
							"Defina��o de Tamanho",
							JOptionPane.PLAIN_MESSAGE,
							null,
							null,
							null
					));
					
					if (size == 0) { 
						
						arrayStack = new ArrayStack<JButton>(); //Inicia a pilha de arranjos com o tamanho padr�o.
						
						JOptionPane.showMessageDialog(null, "A pilha foi iniciada com o valor padr�o!", "Aviso", JOptionPane.WARNING_MESSAGE); //Mensagem de aviso.						
					}
						
					else {arrayStack = new ArrayStack<JButton>(size);} //Inicia a pilha de arranjos com o tamanho fornecido pelo usu�rio.					
					
					createRepresentationScreen("Pilha de Arranjos", arrayStack); //Cria uma janela para a pilha.
				
				}catch(Exception error) {}
				
				break;		
		}		
	}
	
	//Cria uma janela para representar uma pilha.
	public void createRepresentationScreen(String typeStack, NodeStack<JButton> stack) {
		
		//Cria uma janela para representar uma pilha LSE.
		
		JFrame screen = new JFrame();
		
		//Configura��es da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeStack);
		screen.setSize(500, 115);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da pilha.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configura��o do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 115));
		
		//Cria��o do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Cria��o dos botoes do menu.
		
		JMenuItem push = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem pop = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem top = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configura��o dos botoes do menu.
		
		push.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {pushStack(stack, panel);}});
		pop.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {popStack(stack, panel);}});
		top.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {topStack(stack);}});
		
		//Inserc�o dos botoes no menu.
		
		menu.add(push);
		menu.add(pop);
		menu.add(top);		
		
		//Inserc�o do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inser��o do scroll na janela.
		
		screen.add(scroll);
	}	
	
	//Cria uma janela para representar uma pilha de arranjos.
	public void createRepresentationScreen(String typeStack, ArrayStack<JButton> stack) {
		
		//Cria uma janela para representar uma pilha LSE.
		
		JFrame screen = new JFrame();
		
		//Configura��es da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeStack);
		screen.setSize(500, 110);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da pilha.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configura��o do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 110));
		
		//Cria��o do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Cria��o dos botoes do menu.
		
		JMenuItem push = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem pop = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem top = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configura��o dos botoes do menu.
		
		push.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {pushStack(stack, panel);}});
		pop.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {popStack(stack, panel);}});
		top.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {topStack(stack);}});
		
		//Inserc�o dos botoes no menu.
		
		menu.add(push);
		menu.add(pop);
		menu.add(top);		
		
		//Inserc�o do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inser��o do scroll na janela.
		
		screen.add(scroll);
	}
	
	//Adiciona um item na pilha LSE e na janela de representa��o da pilha.
	public void pushStack(NodeStack<JButton> stack, JPanel panel) {		
		
		//Cria uma janela para coletar o elemento que ser� adicionado na pilha.
		
		String element = (String) JOptionPane.showInputDialog(
				
				this.getParent(),
				"Insira um Novo Elemento:",
				"Inser��o de Elementos",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null
		);
		
		if (element != null) {
			
			//Cria��o do bot�o que ser� inserido na pilha com o elemento inserido na tela de adi��o.
			
			JButton elementStack = new JButton(element);
			
			//Adiciona o bot�o criado na pilha.
			
			stack.push(elementStack);
			
			//Adiciona o bot�o criado na janela.
			panel.add(stack.top());
			
			//Recarrega a janela.
			refreshScreen(panel);
		}
	}
	
	//Adiciona um item na pilha de arranjos e na janela de representa��o da pilha.
	public void pushStack(ArrayStack<JButton> stack, JPanel panel) {		
		
		//Cria uma janela para coletar o elemento que ser� adicionado na pilha.
		
		String element = (String) JOptionPane.showInputDialog(
				
				this.getParent(),
				"Insira um Novo Elemento:",
				"Inser��o de Elementos",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null
		);
		
		//Cria��o do bot�o que ser� inserido na pilha com o elemento inserido na tela de adi��o.
		
		JButton elementStack = new JButton(element);
		
		try { //Trata uma possivel exce��o.
			
			if (element != null) {
				
				//Adiciona o bot�o criado na pilha.
				
				stack.push(elementStack);
				
				//Adiciona o bot�o criado na janela.
				panel.add(stack.top());
				
				//Recarrega a janela.
				refreshScreen(panel);
			}
		
		//Libera uma mensagem sobre o erro.
		} catch(FullStackException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}
	
	//Remove um item da pilha LSE e da janela de representa��o da pilha.
	public void popStack(NodeStack<JButton> stack, JPanel panel) {
		
		try { //Trata uma possivel exce��o.
			
			//Remove o bot�o do topo da pilha.
			panel.remove(stack.pop());
			
			//Recarrega a janela.
			refreshScreen(panel);
			
		//Libera uma mensagem sobre o erro.	
		} catch(EmptyStackExceptionNodeStack error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}
	
	//Remove um item da pilha de arranjos e da janela de representa��o da pilha.
	public void popStack(ArrayStack<JButton> stack, JPanel panel) {
		
		try { //Trata uma possivel exce��o.
			
			//Remove o bot�o do topo da pilha.
			panel.remove(stack.pop());
			
			//Recarrega a janela.
			refreshScreen(panel);
		
		//Libera uma mensagem sobre o erro.
		} catch(EmptyStackExceptionArrayStack error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}
	
	//Apresenta em uma janela o elemento do topo da pilha.
	public void topStack(NodeStack<JButton> stack) {
		
		//Trata uma possivel exce��o.
		try {JOptionPane.showMessageDialog(null, "Elemento do Topo: (" + stack.top().getText() + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);} //Apresenta o elemento no topo da pilha.
		catch(EmptyStackExceptionNodeStack error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.		
	}	
	
	//Apresenta em uma janela o elemento do topo da pilha.
	public void topStack(ArrayStack<JButton> stack) {
		
		//Trata uma possivel exce��o.
		try {JOptionPane.showMessageDialog(null, "Elemento do Topo: (" + stack.top().getText() + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);} //Apresenta o elemento no topo da pilha.
		catch(EmptyStackExceptionArrayStack error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.	
	}
	
	//Recarrega os componentes da janela.
	public void refreshScreen(JPanel screen) {
		
		//M�todos da janela para recarrega-l�.
		screen.revalidate();
		screen.repaint();		
	}
	
	public static void main(String[] args) {new MainScreen();}
	
}