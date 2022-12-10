package JTADS.Janelas;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import JTADS.Menu.MainScreen;
import TADS.TAD_Fila.TAD_Fila_Arranjo.Excecoes.EmptyQueueExceptionArrayQueue;
import TADS.TAD_Fila.TAD_Fila_Arranjo.Excecoes.FullQueueException;
import TADS.TAD_Fila.TAD_Fila_Arranjo.Fontes.ArrayQueue;
import TADS.TAD_Fila.TAD_Fila_LSE.Excecoes.EmptyQueueExceptionNodeQueue;
import TADS.TAD_Fila.TAD_Fila_LSE.Fontes.NodeQueue;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

@SuppressWarnings("serial")
public class QueueScreen extends JFrame {
	
	NodeQueue<JButton> nodeQueue;
	ArrayQueue<JButton> arrayQueue;
	
	public QueueScreen() {
		
		//Configura��es da janela.
		
		super("Fila");
		
		setLayout(new FlowLayout());
		setSize(600, 700);
		setResizable(false);		
		setVisible(true);
		
		//Cria��o do texto de apresenta��o do TAD.		
		
		String textQueue = "\n=================================Introdu��o==========================================="+
		
							"\n\nUma fila � um Tipo Abstrato de Dados que permite armazena uma cole��o de elementos de \n"
							+ "forma linear, aonde os acessos na fila s�o realizados em apenas uma de suas extremidades, \no inicio da fila."
							
							+ "\n\nA estrutura de uma fila se define pelo padr�o FIFO (Primeiro a entrar, primeiro a sair), "
							+ "\nesse padr�o � justamente o que define a fila, pois a fila s� permiti acesso a uma de suas "
							+ "\nextremidades o inicio da fila, nesse caso quando se necessita de um elemento da fila, o \nmesmo ser� "
							+ "removido do inicio da fila e quando for necess�rio inserir um novo elemento o \nmesmo ser� "
							+ "inserido no final da fila podendo se tornar o novo inicio da fila."
							
							+ "\n\n==================================M�todos==========================================="
							+ "\n\nM�todos b�sicos da Fila:" +
							
							"\n\nInserir/Enqueue: O elemento que for inserido na fila receber� uma posi��o no final da fila." + 
							"\n\nRemover/Dequeue: O elemento que estiver localizado no inicioa da fila ser� removido." + 
							"\n\nConsultar/Front: O Elemento ser� encontrada no inicio da fila." +							
							
							"\n\n================================Implementa��o=========================================" +
							
							"\n\nPara esta implementa��o foi implementada duas variantes da fila, uma que utiliza a "
							+ "\nLista Simplesmente Encadeada e outra que utiliza arranjo." + 
							
							"\n\nPor quest�es de desenvolvimento a implementa��o da Fila trata-se de uma fila de Strings, "
							+ "\npermitindo uso de todo e qualquer caractere da tabela ASCII e tratando a entrada de "
							+ "\nn�meros como Strings.";	
			
		//Inser��o do texto em uma caixa de texto para exibi��o.
		
		JTextArea boxTextQueue = new JTextArea(textQueue);
		
		//Configura��o da caixa de texto.
		
		boxTextQueue.setEditable(false);
		boxTextQueue.setPreferredSize(new Dimension(550, 600));	
		
		//Cria��o de botoes para a janela.
		
		JButton createQueue = new JButton("Criar Fila");
		
		//Inserc�o dos componentes na janela.
		
		add(boxTextQueue);
		add(createQueue);
		
		//Configura��o dos botoes da janela.
		
		createQueue.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {createQueue();}});
	}
	
	//Cria uma fila e uma tela de representa��o para � fila.
	public void createQueue() {		

		//Janela de cria��o da fila.
		
		Object[] options = {"Fila com LSE", "Fila com Arranjo"};
		
		int option = JOptionPane.showOptionDialog(
				
				this.getParent(), 
				"Selecione o Tipo de Fila:", 
				"Tipos de Filas", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options
		);
		
		//Cria � fila escolhida pelo usu�rio.
		
		switch(option) {
		
			case 0: //Cria uma fila LSE.
				
				nodeQueue = new NodeQueue<JButton>(); //Inicia a fila LSE.
				
				createRepresentationScreen("Fila LSE", nodeQueue); //Cria uma janela para a fila.
				
				break;
			
			case 1: //Cria uma fila de arranjos.				
				
				try { //Trata uma possivel exce��o.
					
					//Cria uma janela para coletar o tamanho da fila de arranjos.
					
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
						
						arrayQueue = new ArrayQueue<JButton>(); //Inicia a fila de arranjos com o tamanho padr�o.
						
						JOptionPane.showMessageDialog(null, "A fila foi iniciada com o valor padr�o!", "Aviso", JOptionPane.WARNING_MESSAGE); //Mensagem de aviso.						
					}
						
					else {arrayQueue = new ArrayQueue<JButton>(size);} //Inicia a fila de arranjos com o tamanho fornecido pelo usu�rio.					
					
					createRepresentationScreen("Fila de Arranjos", arrayQueue); //Cria uma janela para a fila.
				
				}catch(Exception error) {}
				
				break;		
		}		
	}	
	
	//Cria uma janela para representar uma fila.
	public void createRepresentationScreen(String typeQueue, NodeQueue<JButton> queue) {
		
		//Cria uma janela para representar uma fila LSE.
		
		JFrame screen = new JFrame();
		
		//Configura��es da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeQueue);
		screen.setSize(500, 115);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da fila.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configura��o do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 115));
		
		//Cria��o do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Cria��o dos botoes do menu.
		
		JMenuItem enqueue = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem dequeue = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem front = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configura��o dos botoes do menu.
		
		enqueue.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {enqueueQueue(queue, panel);}});
		dequeue.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {dequeueQueue(queue, panel);}});
		front.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {frontQueue(queue);}});
		
		//Inserc�o dos botoes no menu.
		
		menu.add(enqueue);
		menu.add(dequeue);
		menu.add(front);		
		
		//Inserc�o do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inser��o do scroll na janela.
		
		screen.add(scroll);
	}	
	
	//Cria uma janela para representar uma fila.
	public void createRepresentationScreen(String typeQueue, ArrayQueue<JButton> queue) {
		
		//Cria uma janela para representar uma fila LSE.
		
		JFrame screen = new JFrame();
		
		//Configura��es da janela criada.
		
		screen.setLayout(new BorderLayout());
		screen.setTitle(typeQueue);
		screen.setSize(500, 115);
		screen.setResizable(false);
		screen.setVisible(true);
		
		//Cria um painel para conter os componentes da fila.
		
		JPanel panel = new JPanel();
		
		//Cria um painel com scroll.
		
		JScrollPane scroll = new JScrollPane(panel);
		
		//Configura��o do scroll da janela.
		
		scroll.setPreferredSize(new Dimension(500, 115));
		
		//Cria��o do menu para a janela.
		
		JMenuBar menu = new JMenuBar();
		
		//Cria��o dos botoes do menu.
		
		JMenuItem enqueue = new JMenuItem("Adicionar", KeyEvent.VK_M);
		JMenuItem dequeue = new JMenuItem("Remover", KeyEvent.VK_M);
		JMenuItem front = new JMenuItem("Consultar", KeyEvent.VK_M);
		
		//Configura��o dos botoes do menu.
		
		enqueue.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {enqueueQueue(queue, panel);}});
		dequeue.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {dequeueQueue(queue, panel);}});
		front.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {frontQueue(queue);}});
		
		//Inserc�o dos botoes no menu.
		
		menu.add(enqueue);
		menu.add(dequeue);
		menu.add(front);		
		
		//Inserc�o do menu na janela.
		
		screen.setJMenuBar(menu);
		
		//Inser��o do scroll na janela.
		
		screen.add(scroll);
	}
	
	//Adiciona um item na fila LSE e na janela de representa��o da fila.
	public void enqueueQueue(NodeQueue<JButton> queue, JPanel panel) {		
		
		//Cria uma janela para coletar o elemento que ser� adicionado na fila.
		
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
			
			//Cria��o do bot�o que ser� inserido na fila com o elemento inserido na tela de adi��o.
			
			JButton elementQueue = new JButton(element);
			
			//Adiciona o bot�o criado na fila.
			
			queue.enqueue(elementQueue);
			
			//Adiciona o bot�o criado na janela.
			panel.add(elementQueue);
			
			//Recarrega a janela.
			refreshScreen(panel);
		}
	}	
	
	//Adiciona um item na fila de arranjos e na janela de representa��o da fila.
	public void enqueueQueue(ArrayQueue<JButton> queue, JPanel panel) {		
		
		//Cria uma janela para coletar o elemento que ser� adicionado na fila.
		
		String element = (String) JOptionPane.showInputDialog(
				
				this.getParent(),
				"Insira um Novo Elemento:",
				"Inser��o de Elementos",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null
		);
		
		//Cria��o do bot�o que ser� inserido na fila com o elemento inserido na tela de adi��o.
		
		JButton elementQueue = new JButton(element);
		
		try { //Trata uma possivel exce��o.
			
			if (element != null) {
				
				//Adiciona o bot�o criado na fila.
				
				queue.enqueue(elementQueue);
				
				//Adiciona o bot�o criado na janela.
				panel.add(elementQueue);
				
				//Recarrega a janela.
				refreshScreen(panel);
			}
		
		//Libera uma mensagem sobre o erro.
		} catch(FullQueueException error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}
	
	//Remove um item da fila LSE e da janela de representa��o da fila.
	public void dequeueQueue(NodeQueue<JButton> queue, JPanel panel) {
		
		try { //Trata uma possivel exce��o.
			
			//Remove o bot�o do in�cio da fila.
			panel.remove(queue.dequeue());
			
			//Recarrega a janela.
			refreshScreen(panel);
			
		//Libera uma mensagem sobre o erro.	
		} catch(EmptyQueueExceptionNodeQueue error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}
	
	//Remove um item da fila de arranjos e da janela de representa��o da fila.
	public void dequeueQueue(ArrayQueue<JButton> queue, JPanel panel) {
		
		try { //Trata uma possivel exce��o.
			
			//Remove o bot�o do in�cio da fila.
			panel.remove(queue.dequeue());
			
			//Recarrega a janela.
			refreshScreen(panel);
		
		//Libera uma mensagem sobre o erro.
		} catch(EmptyQueueExceptionArrayQueue error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);}
	}
	
	//Apresenta em uma janela o elemento do in�cio da fila.
	public void frontQueue(NodeQueue<JButton> queue) {
		
		//Trata uma possivel exce��o.
		try {JOptionPane.showMessageDialog(null, "Elemento do In�cio: (" + queue.front().getText() + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);} //Apresenta o elemento do in�cio da fila.
		catch(EmptyQueueExceptionNodeQueue error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.		
	}	
	
	//Apresenta em uma janela o elemento do in�cio da fila.
	public void frontQueue(ArrayQueue<JButton> queue) {
		
		//Trata uma possivel exce��o.
		try {JOptionPane.showMessageDialog(null, "Elemento do In�cio: (" + queue.front().getText() + ")", "Consulta de Elementos", JOptionPane.INFORMATION_MESSAGE);} //Apresenta o elemento do in�cio da fila.
		catch(EmptyQueueExceptionArrayQueue error) {JOptionPane.showMessageDialog(null, error.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);} //Libera uma mensagem sobre o erro.	
	}	
	
	//Recarrega os componentes da janela.
	public void refreshScreen(JPanel screen) {
		
		//M�todos da janela para recarrega-l�.
		screen.revalidate();
		screen.repaint();		
	}
	
	public static void main(String[] args) {new MainScreen();}

}