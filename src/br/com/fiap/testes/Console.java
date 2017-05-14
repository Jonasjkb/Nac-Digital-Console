package br.com.fiap.testes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.apache.axis2.AxisFault;

import br.com.fiap.bo.ClienteBOStub;
import br.com.fiap.bo.ClienteBOStub.Buscar;
import br.com.fiap.bo.ClienteBOStub.BuscarResponse;
import br.com.fiap.bo.ClienteBOStub.Cadastrar;
import br.com.fiap.bo.ClienteBOStub.Cliente;
import br.com.fiap.bo.ClienteBOStub.Listar;
import br.com.fiap.bo.ClienteBOStub.ListarResponse;

public class Console {
	
	static public String texto(String p) throws AxisFault {
		return JOptionPane.showInputDialog(p);
	}
	
	public static void main(String[] args) throws AxisFault {
		
		ClienteBOStub stub = null;
		int opcao = 0;
		
		System.out.println("| 1 | - Cadastrar");
		System.out.println("| 2 | - Buscar");
		System.out.println("| 3 | - Listar");
		
		opcao = Integer.parseInt(texto("Digite a funcionalidade"));
		
		switch (opcao) {
		case 1:
			try {
				stub = new ClienteBOStub();
				Cadastrar params = new Cadastrar();
				Cliente c = new Cliente();
				c.setCodigo(0);
				c.setNome("Samuel");
				c.setSobrenome("Rosa");
				c.setCpf("23568974455");
				c.setDataNascimento(new GregorianCalendar(2017, Calendar.AUGUST, 6));
				c.setEndereco("Av. Barão de Limeira");
				c.setRg("457812334");
				
				params.setCliente(c);
				stub.cadastrar(params);
				
			} catch (Exception e) {
				System.out.println("Erro ao cadastrar!");
				e.printStackTrace();
			}			
			break;
			
		case 2: 
			try {
				stub = new ClienteBOStub();
				Buscar params = new Buscar();
				params.setCodigo((Integer.parseInt(texto("Digite o código: "))));
				BuscarResponse response = stub.buscar(params);
				Cliente c = response.get_return();				
			} catch (Exception e) {
				System.out.println("Erro na busca!");
				e.printStackTrace();
			}
			break;
			
		case 3:
			try {
				stub = new ClienteBOStub();
				Listar params = new Listar();
				ListarResponse response = stub.listar(params);
				Cliente[] cli = response.get_return();
			} catch (Exception e) {
				System.out.println("Erro ao listar!");
				e.printStackTrace();
			}
		default:
			break;
		}
		 
	}

}
