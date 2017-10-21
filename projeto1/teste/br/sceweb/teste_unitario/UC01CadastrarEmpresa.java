package br.sceweb.teste_unitario;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import br.sceweb.model.Empresa;
import br.sceweb.model.EmpresaDAO;
import br.sceweb.servico.ConfiguraDB;
/**
 * Este script de teste verifica o comportamento do caso de uso UC01CadastrarEmpresa
 * @author Luiz Ramos
 *
 */
public class UC01CadastrarEmpresa {
	static EmpresaDAO empresaDAO;
	static Empresa empresa;
	static ConfiguraDB configuraDB;
	static ConfiguraDB configuraDB1;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String url = "jdbc:mysql://localhost/sceweb";
		String driver = "com.mysql.jdbc.Driver";
		String usuario = "root";
		String senha = "root";
		configuraDB = new ConfiguraDB(url, driver,usuario,senha);
		empresaDAO = new EmpresaDAO(configuraDB);
		empresa = new Empresa();
		empresa.setNomeDaEmpresa("empresa x");
		empresa.setCnpj("89424232000180");
		empresa.setNomeFantasia("empresa x");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("2222");
	}
	/**
	 * ID - CT01UC01FBCadastra_com_sucesso
	 * Objetivo - verificar o comportamento do sistema na inclusao de empresa com 
	 * sucesso
	 * Pr�-condi�ao - O cnpj 89424232000180 nao esta cadastrado.
	 */
	@Test
	public void CT01UC01FBCadastra_com_sucesso() {
		String url = "jdbc:mysql://localhost/sceweb";
		assertEquals(1,empresaDAO.adiciona(empresa)); //esse valor de adiciona, retorna 1, por isso compara-se com o primeiro 1
	}
	
	@Test
	public void CT02UC01FBCadastra_cnpj_invalido() {
		empresaDAO = new EmpresaDAO(configuraDB);
		assertEquals("CNPJ invalido.",empresa.setCnpj("89424"));
	}
	@Test
	public void CT03UC01FBCadastra_cnpj_ja_cadastrado() {
		empresaDAO = new EmpresaDAO(configuraDB);
		empresaDAO.adiciona(empresa);
		assertEquals(0,empresaDAO.adiciona(empresa)); //esse valor de adiciona, retorna 1 ou 0, dependendo se adicionou ou n�o, por isso compara-se com o primeiro 1
		
	}
	
	@Test
	public void CT04UC01FBCadastra_empresa_em_branco() {
		empresaDAO = new EmpresaDAO(configuraDB);
		assertEquals("Empresa invalida.",empresa.setNomeDaEmpresa(""));
	}
	
	@Test
	public void CT05UC01FBCadastra_cnpj_em_branco() {
		empresaDAO = new EmpresaDAO(configuraDB);
		assertEquals("CNPJ invalido.",empresa.setCnpj(""));
	}
	
	@After
	public void tearDownAfterClass() throws Exception {
		empresaDAO.exclui("89424232000180");
	}
}



