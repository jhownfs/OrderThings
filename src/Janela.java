import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class Janela extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblCarregar, lblQtd, lblRepetir, lblBolha, lblSelecao, lblInsercao,lbltime, lblexecutado;
	private JTextField txtQtd,txtRepetir,txtBolha,txtSelecao,txtInsercao,txtcarregar;
	private JTextField txtBolhaTime,txtSelecaoTime, txtInsercaoTime;
	@SuppressWarnings("unused")
	private JFileChooser fCarregar, fb,fs,fi;
	@SuppressWarnings("unused")
	private JButton carregar, iniciar, salvarB, salvarS, salvarI, salvarTB,salvarTS,salvarTI,save,saveT;
	private String caminho, cSalvar;
	private Ordenador ordenador;
	private JMenuBar menuBar;
	private JMenu info;
	private JMenuItem sobrei;

	
	public Janela(){
		super("Ordenador JMR - Versão 1.1");
		
		//Criar as Labels
		lblCarregar = new JLabel ("Carregar arquivo: ");
		lblQtd = new JLabel ("Quantidade de itens: ");
		lblRepetir = new JLabel ("Deseja repetir quantas vezes: ");
		lblBolha = new JLabel ("Bolha:      ");
		lblSelecao = new JLabel ("Seleção:  ");
		lblInsercao = new JLabel ("Inserção: ");
		lbltime = new JLabel("                Tempo médio(S/MS)");
		lblexecutado = new JLabel("   Executado");
		
		//Criar as caixas de texto
		
		txtQtd = new JTextField(5);
		txtRepetir = new JTextField(3);
		txtBolha = new JTextField(5);
		txtSelecao = new JTextField(5);
		txtInsercao = new JTextField(5);
		txtcarregar = new JTextField(20);
		txtBolhaTime = new JTextField(10);
		txtSelecaoTime = new JTextField(10);
		txtInsercaoTime = new JTextField(10);
		
		//criar a caixa de selecao
		fCarregar = new JFileChooser();
		fb = new JFileChooser();
		fs = new JFileChooser();
		fi = new JFileChooser();
		
		//criando botões
		carregar = new JButton("Carregar");
		iniciar = new JButton("Iniciar Ordenação");
		salvarB = new JButton("Arquivo Ordenado");
		salvarS = new JButton("Arquivo Ordenado");
		salvarI = new JButton("Arquivo Ordenado");
		salvarTB = new JButton("Tempo de execução");
		salvarTS = new JButton("Tempo de execução");
		salvarTI = new JButton("Tempo de execução");
		
		//check box
		//todos = new JCheckBox("Todos");
		
		//menu
		
		info = new JMenu("Mais");
		sobrei = new JMenuItem("Informações");
		sobrei.setToolTipText("Informações do projeto");
        menuBar = new JMenuBar();
		
	}
	
	@SuppressWarnings("deprecation")
	public void desenho(){
		setResizable(false);
		setForeground(Color.BLUE);
		setBackground(Color.CYAN);
		setSize(600, 400);
		setLayout(new GridLayout(10,3));
		FlowLayout esquerda = new FlowLayout(FlowLayout.LEFT);
		FlowLayout centro = new FlowLayout(FlowLayout.CENTER);
		setLocationRelativeTo(null);
		
		//Painel de carregar
		JPanel auxCarregar = new JPanel(centro);
		
		auxCarregar.add(lblCarregar);
		txtcarregar.enable(true);
		auxCarregar.add(txtcarregar);
		auxCarregar.add(carregar);
        
		//Painel de quantidade e repetir
		JPanel auxrpt = new JPanel(centro);
		auxrpt.add(lblQtd);
		auxrpt.add(txtQtd);
		auxrpt.add(lblRepetir);
		auxrpt.add(txtRepetir);
        txtRepetir.setText("1");
        txtQtd.setText("1");
		//Painel de Bolha
		
		JPanel auxBolha = new JPanel(esquerda);
		
		auxBolha.add(lblBolha);
		auxBolha.add(txtBolhaTime);
		auxBolha.add(txtBolha);
		auxBolha.add(salvarB);
		auxBolha.add(salvarTB);
		
		//Painel de Label
		
		JPanel auxLabel = new JPanel(esquerda);
		auxLabel.add(lbltime);
		auxLabel.add(lblexecutado);
		
		//Painel de Seleção
		
		JPanel auxSelecao = new JPanel(esquerda);
	
		auxSelecao.add(lblSelecao);
		auxSelecao.add(txtSelecaoTime);
		auxSelecao.add(txtSelecao);
		auxSelecao.add(salvarS);
		auxSelecao.add(salvarTS);
	   //Painel de inserção
		
		JPanel auxInsercao = new JPanel(esquerda);
	
		auxInsercao.add(lblInsercao);
		auxInsercao.add(txtInsercaoTime);
		auxInsercao.add(txtInsercao);
		auxInsercao.add(salvarI);
		auxInsercao.add(salvarTI);
		
		//Painel iniciar
		
		JPanel auxIniciar = new JPanel(centro);
		
		auxIniciar.add(iniciar);
		
		//menu
		
		info.add(sobrei);
		
		menuBar.add(info);
		add(menuBar);
		add(auxCarregar);
		add(auxrpt);
		add(auxIniciar);
		add(auxLabel);
		add(auxBolha);
		add(auxSelecao);
		add(auxInsercao);
		//fecha aplicação completamente;
		addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent we){
			
			System.exit(0);
			
			
		}
	});
		//acões dos botões;
		carregar.addActionListener(procurar);
		iniciar.addActionListener(rodar);
		salvarB.addActionListener(salve);
		salvarS.addActionListener(salve);
		salvarI.addActionListener(salve);
		salvarTB.addActionListener(saveT1);
		salvarTS.addActionListener(saveT1);
		salvarTI.addActionListener(saveT1);
		sobrei.addActionListener(sobre);
		setVisible(true);
	}

	//Tratamentos dos eventos
	
	//evento de procurar o arquivo
	 ActionListener procurar = new ActionListener(){

		 int r;
		@Override
		public void actionPerformed(ActionEvent e) {
			fCarregar.addChoosableFileFilter(new FileNameExtensionFilter("Texto(*.txt)", "txt"));
			r = fCarregar.showOpenDialog(null);
	        if(r == JFileChooser.APPROVE_OPTION){
	        	caminho = fCarregar.getSelectedFile().getAbsolutePath();
	        	txtcarregar.setText(caminho);
	        }
			
		}
 
	 };
	 
	 
	 
	 //evento de encontrar um lugar para salvar;
	 ActionListener ArquivoSalvar = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			int r;
			fb.setAcceptAllFileFilterUsed(false);
			fb.setMultiSelectionEnabled(false);
			fb.addChoosableFileFilter(new FileNameExtensionFilter("Texto(*.txt)", "txt"));
			r = fb.showSaveDialog(null);
			
			if(r == JFileChooser.APPROVE_OPTION){
				cSalvar = fb.getSelectedFile().getAbsolutePath();
			}else{
			
			}
			
		}
		 
	 };
	 
	 ActionListener sobre = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource() == sobrei){
				 
				JFrame novo = new JFrame("Sobre!");
				novo.setLayout(new BorderLayout());
				novo.setResizable(false);
				novo.setForeground(Color.BLUE);
				novo.setBackground(Color.CYAN);
				novo.setSize(400, 270);
				novo.setLocationRelativeTo(novo);
				novo.add(new JLabel(new ImageIcon("creditos.jpg")));
				novo.setVisible(true);
			}
			
		}
	 };
	 
	 
	 //início e organização do vetores
	 
	 ActionListener rodar = new ActionListener(){

		@SuppressWarnings("static-access")
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(txtcarregar.getText().equals("")){
				txtcarregar.setText("Por favor, selecione um arquivo");
			}else{
				
			File file = new File(caminho);
			
            int qtd = 0;
			try {
			
				Scanner  sc = new Scanner(file);
				ordenador = new Ordenador();
				
				
			    qtd = Integer.parseInt(txtQtd.getText());
				int vezes = Integer.parseInt(txtRepetir.getText());
				//criando vetor
			

				ordenador.criarVetor(qtd);
				
				for(int i=0; i<qtd;i++){
				String f = sc.nextLine();
				 ordenador.add(f);
				}
				
				sc.close();
				
				//executa as ordenações
				//bolha
				ordenador.sortBolha(vezes);
				txtBolha.setHorizontalAlignment(txtBolha.CENTER);
				txtBolhaTime.setHorizontalAlignment(txtBolhaTime.CENTER);
				String medioB = String.format("%.6f",ordenador.tempoMedio("B"));
				txtBolhaTime.setText(medioB);
				txtBolha.setText("OK!");
				//selecao
				ordenador.sortSelecao(vezes);
				txtSelecao.setHorizontalAlignment(txtSelecao.CENTER);
				txtSelecaoTime.setHorizontalAlignment(txtSelecaoTime.CENTER);
				String medioS = String.format("%.6f",ordenador.tempoMedio("S"));
				txtSelecaoTime.setText(medioS);
				txtSelecao.setText("OK!");
				//inserção
				ordenador.sortInsert(vezes);
				txtInsercao.setHorizontalAlignment(txtInsercao.CENTER);
				txtInsercaoTime.setHorizontalAlignment(txtInsercaoTime.CENTER);
				String medioI = String.format("%.6f",ordenador.tempoMedio("I"));
				txtInsercaoTime.setText(medioI);
				txtInsercao.setText("OK!");
				JOptionPane.showMessageDialog(null,"Ordenação concluida");
			} catch (FileNotFoundException e1) {

				e1.printStackTrace();
			}
			
		  }
		}
 
	 };

	 
	 //a partir daqui serão criados os eventos de extrair dados e salvar em um arquivo 
	 
	 //Exportar arquivos ordenados
	ActionListener salve = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			 
			ArquivoSalvar.actionPerformed(e);
			
			if(e.getSource() == salvarB){
			
				try {
					FileWriter rw = new FileWriter(cSalvar);
					BufferedWriter bolha = new BufferedWriter(rw);
						for(int i=0; i<ordenador.length();i++){
								bolha.write(ordenador.imprimir("B",i));
								bolha.newLine();
						}
						for(int i=0; i<ordenador.length();i++){
							System.out.println(ordenador.imprimir("B",i));
					}
					
					bolha.close();
					rw.close();		
					JOptionPane.showMessageDialog(null, "Arquivo Salvo com êxito!");
					
				} catch (IOException e1) {

					e1.printStackTrace();
				}
		
			}else if(e.getSource() == salvarS){
			
			 try {
				FileWriter rw = new FileWriter(cSalvar);
				BufferedWriter selecao = new BufferedWriter(rw);
					for(int i=0; i<ordenador.length();i++){
							selecao.write(ordenador.imprimir("S",i));
							selecao.newLine();
					}
				
				selecao.close();
				rw.close();		
				JOptionPane.showMessageDialog(null, "Arquivo Salvo com êxito!");
				
			 }catch (IOException e1) {

				e1.printStackTrace();
			  }
			
			}else if(e.getSource() == salvarI){
				
				try {
					FileWriter rw = new FileWriter(cSalvar);
					BufferedWriter insercao = new BufferedWriter(rw);
						for(int i=0; i<ordenador.length();i++){
								insercao.write(ordenador.imprimir("I",i));
								insercao.newLine();
						}
					
					insercao.close();
					rw.close();
					JOptionPane.showMessageDialog(null, "Arquivo Salvo com êxito!");
			  }catch (IOException e1) {

				e1.printStackTrace();
			}
		 }
	  }
		 
	 };
	 
	 //Exportar tempo de execuçãos
	 
	 ActionListener saveT1 = new ActionListener(){
		 @Override
			public void actionPerformed(ActionEvent e){
			 
			 ArquivoSalvar.actionPerformed(e);
			 
			 if(e.getSource() == salvarTB){
				
					try {
						FileWriter rw = new FileWriter(cSalvar);
						BufferedWriter tempo = new BufferedWriter(rw);
							for(int i=0; i<Integer.parseInt(txtRepetir.getText());i++){
								String print = String.format("%.6f",ordenador.tempo("B",i));
									tempo.write(print);
									tempo.newLine();
							}
							tempo.close();
							JOptionPane.showMessageDialog(null, "Arquivo Salvo com êxito!");	
					}catch(IOException e1) {

								e1.printStackTrace();
				}
			 }else if(e.getSource() == salvarTS){
				 try {
						FileWriter rw = new FileWriter(cSalvar);
						BufferedWriter tempo = new BufferedWriter(rw);
							for(int i=0; i<Integer.parseInt(txtRepetir.getText());i++){
								String print = String.format("%.6f",ordenador.tempo("S",i));
									tempo.write(print);
									tempo.newLine();
							}
							tempo.close();
							JOptionPane.showMessageDialog(null, "Arquivo Salvo com êxito!");
					}catch(IOException e1) {

								e1.printStackTrace();
				}
			 }else if(e.getSource() == salvarTI){
				 try {
						FileWriter rw = new FileWriter(cSalvar);
						BufferedWriter tempo = new BufferedWriter(rw);
							for(int i=0; i<Integer.parseInt(txtRepetir.getText());i++){
								String print = String.format("%.6f",ordenador.tempo("I",i));
									tempo.write(print);
									tempo.newLine();
							}
							tempo.close();
							JOptionPane.showMessageDialog(null, "Arquivo Salvo com êxito!");
					}catch(IOException e1) {

								e1.printStackTrace();
				}
			 }
		  }
	 };
}
