package JTADS.Menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import JTADS.Janelas.*;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

@SuppressWarnings("serial")
public class MainScreen extends JFrame {
	
	public MainScreen() {
		
		//Configurações da janela.
		
		super("Tipos Abstratos de Dados");		
		
		setLayout(new GridLayout(12, 1));
		setSize(250, 339);
		setResizable(true);		
		setVisible(true);				
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Criação de botoes para a janela.
		
		JButton arrayList = new JButton("Lista Arranjo");
		JButton stack = new JButton("Pilha");
		JButton queue = new JButton("Fila");
		JButton nodeList = new JButton("Lista de Nodos");
		JButton treeGeneric = new JButton("Árvore Genérica");
		JButton treeBinary = new JButton("Árvore Binaria");
		JButton priorityQueue = new JButton("Fila de Prioridade");
		JButton map = new JButton("Mapa");
		JButton Dictionary = new JButton("Dicionário");
		JButton mapSortedABB = new JButton("Mapa Ordenado - ABB");
		JButton mapSortedAVL = new JButton("Mapa Ordenado - AVL");
		JButton Graphs = new JButton("Grafos");
		
		//Configuração de botoes da janela.
		
		arrayList.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {new ArrayListScreen();}});
		stack.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {new StackScreen();}});
		queue.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {new QueueScreen();}});
		nodeList.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {new NodeListScreen();}});
		treeGeneric.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {new LinkedTreeScreen();}});
		treeBinary.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {new LinkedBinaryTreeScreen();}});
		priorityQueue.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {new PriorityQueueScreen();}});
		map.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {new HashTableMapScreen();}});
		Dictionary.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {new HashTableMultiMapScreen();}});
		mapSortedABB.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {new BinarySearchTreeSceen();}});
		mapSortedAVL.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {new AVLTreeMapScreen();}});
		Graphs.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {new GraphsScreen();}});
		
		//Insercão de botoes na janela.
		
		add(arrayList);
		add(stack);
		add(queue);
		add(nodeList);
		add(treeGeneric);
		add(treeBinary);
		add(priorityQueue);
		add(map);
		add(Dictionary);
		add(mapSortedABB);
		add(mapSortedAVL);
		add(Graphs);
		
		revalidate();
		repaint();
	}
	
	public static void main(String[] args) {new MainScreen();}

}