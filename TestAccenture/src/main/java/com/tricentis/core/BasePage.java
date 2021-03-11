package com.tricentis.core;
import static com.tricentis.core.DriverFactory.getDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	/***************** TextField e TextArea **********************/
	
	// criando um metodo para ser utilizado toda a vez que um teste precisar escrever em um campo
	public void escrever(By by, String texto) {
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);
	}
	
	public void escrever(String id_campo, String texto) {
		escrever(By.id(id_campo),texto);	
	}
	
	public String obterValorCampo(String id_campo) {
		return getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}
	
	/***************** Radio e Check ****************************/

	public void clicarRadio(String id) {
		clicarRadio(By.id(id));
	}
	
	public void clicarRadio(By by) {
		getDriver().findElement(by).click();
	}
	
	public boolean isRadioMarcado(String id) {
		return getDriver().findElement(By.id(id)).isSelected();	
	}
	
	public void clicarCheck(String id) {
		getDriver().findElement(By.id(id)).click();
	}
	
	public void clicarCheck(By by) {
		getDriver().findElement(by).click();
	}
	
	public boolean isCheckMarcado(String id) {
		return getDriver().findElement(By.id(id)).isSelected();	
	}
	
	public void selecionarComboPrime(String radical, String valor) {
		clicarRadio(By.xpath("//*[@id='"+radical+"_input']/../..//span"));
		clicarRadio(By.xpath("//*[@id='"+radical+"_items']//li[.='"+valor+"']"));		
	}
	
	/***************** Combo  ***********************************/

	public void selecionarCombo(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor); // de preferencia ao valor q aparece natela
	}
	
	public void deselecionarCombo(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor); // de preferencia ao valor q aparece natela
	}
	
	public String obterValorCombo(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<String> obterValoresCombo(String id){
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int obterQuantidadeOpcoesCombo(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public boolean verificarOpcaoCombo(String id, String opcao) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)) {
				return true;
			}
		}
		return false;
	}
	
	/************************* Botao  *****************************/
	
	public void clicarBotao(String id) {
		clicarBotao(By.id(id));
	}
	
	public void clicarBotao(By by) {
		getDriver().findElement(by).click();
	}

	public void clicarBotaoPorTexto(String texto) {
		clicarBotao(By.xpath("//button[.='"+texto+"']"));
	}
	
	public String obterValueElemento(String id) {
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}
	
	/************************ Link  ******************************/
	
	public void clicarLink(String link) {
		getDriver().findElement(By.linkText(link)).click();
	}
	
	/************************  Texto  ****************************/

	public String obterTexto(By by) { // passando via By completo
		return getDriver().findElement(by).getText();
	}
	
	public String obterTexto(String id) { // passando id - montando codigo para reaproveitar o metodo acima.
		return obterTexto(By.id(id));
	}
	

	
	/************************ Alerts  ***************************/

	public String alertaObterTexto() { // passando via By completo
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}
	
	public String alertaObterTextoEAceita() { // passando via By completo
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
	}	
	
	public String alertaObterTextoENega() { // passando via By completo
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
	}
	
	public void alertaEscrever(String valor) { // passando via By completo
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}
	
	/********************** Frames e Janelas  ********************/
	
	public void entrarFrame(String id) {
		getDriver().switchTo().frame(id);
	}
	
	public void sairFrame() {
		getDriver().switchTo().defaultContent();
	}
	
	public void trocarJanela(String id) {
		getDriver().switchTo().window(id);
	}
	
	/*********************  JS  *********************************/

	public Object executarJS(String cmd, Object...  param) { // evite ao maximo usar isso, pois o webdriver usa comando nativos do browser, ja o js nao
		JavascriptExecutor js = (JavascriptExecutor) getDriver(); // javascript executor do selecium e pedir para fazer casting
		return js.executeScript(cmd, param);
	}
	
	
	/*********************  Tabela  *****************************/
	public WebElement obterCelula(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		WebElement tabela = getDriver().findElement(By.xpath("//*[@id='"+idTabela+"']"));

		// procurar coluna do registro onde contem o dado a ser buscado
		int idColuna = obterIndiceColuna(colunaBusca, tabela);

		// encontrar a linha do registro que contem o dado
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		// procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		// clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		return celula;
	}
	
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		WebElement celula = obterCelula(colunaBusca, valor, colunaBotao, idTabela);
		celula.findElement(By.xpath(".//input")).click();
	}

	private int  obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for(int i = 0; i < linhas.size(); i++) {
			if(linhas.get(i).getText().equals(valor)) {
				idLinha = i + 1;
				break;
			}
		}
		return idLinha;
	}
	
	public int obterQtdeRegistrosTabela(String tabela) {
		List<WebElement> elementosEncontrados = getDriver().findElements(By.xpath("//table[@id='"+tabela+"']/tbody/tr"));
		return elementosEncontrados.size();
	}

	private int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th")); // webelements traz todos os elementos do tipo th neste caso. Observe o ponto, que traz apenas th a partir do elemento selecionado na variabvel tabela. 
		int idColuna = -1;
		for(int i = 0; i <colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)){
				idColuna = i + 1; // xpath começa sempre do 1, já o list do webelement começa com numero 0.
				break;
			}
		}
		return idColuna;
	}
	
	/*********************  Carregamento  *************************/
	
	public void aguardarCarregamentoDeConteudo(String id, String conteudo) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30); // espera 30 segundos no maximo
		wait.until(ExpectedConditions.textToBe(By.id(id), conteudo));
	}
	
	public void aguardarCarregamento()
	{
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
	}
	
	public void aguardarCarregamentoDoCampo(String idCampo)
	{
		WebDriverWait wait = new WebDriverWait(getDriver(), 30); // espera 30 segundos no maximo
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(idCampo))); // continuar somente apos o elemento aparecer
	}

}
