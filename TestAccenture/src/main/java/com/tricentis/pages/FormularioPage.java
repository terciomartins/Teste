package com.tricentis.pages;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.tricentis.core.BasePage;
import com.tricentis.core.DriverFactory;
import com.tricentis.utils.DataUtils;

public class FormularioPage extends BasePage{
	
	public void acessarSite() {
		DriverFactory.getDriver().get("http://sampleapp.tricentis.com/101/app.php");
	}
	
	public void setMake(String valor) {
		selecionarCombo("make", valor);
	}
	
	public void setModel(String valor) {
		selecionarCombo("model", valor);
	}
	
	public void setCylinderCapacity(String valor) {
		escrever("cylindercapacity", valor);
	}
	
	public void setEnginePerformance(String valor) {
		escrever("engineperformance", valor);
	}
	
	public void setDateOfManufacture(String data) {
		escrever("dateofmanufacture", data);
	}
	
	public void setNumberOfSeats(String valor) {
		selecionarCombo("numberofseats", valor);
	}
	
	public void setNumberOfSeatsMotorcycle(String valor) {
		selecionarCombo("numberofseatsmotorcycle", valor);
	}
	
	public void setFuelType(String valor) {
		selecionarCombo("fuel", valor);
	}
	
	public void setPayload(String valor) {
		escrever("payload", valor);
	}
	
	public void setTotalWeight(String valor) {
		escrever("totalweight", valor);
	}	
	
	public void setListPrice(String valor) {
		escrever("listprice", valor);
	}
	
	public void setAnnualMileage(String valor) {
		escrever("annualmileage", valor);
	}
	
	public void clicarNext(String aba) {
		if(aba == "Enter Vehicle Data")
		{
			clicarBotao("nextenterinsurantdata");
		}
		else if(aba == "Enter Vehicle Data")
		{
			clicarBotao("nextenterproductdata");
		}
		else if(aba == "Enter Product Data")
		{
			clicarBotao("nextselectpriceoption");
		}
	}
	
	public void setFirtsName(String valor){
		escrever("firstname", valor);
	}
	
	public void setLastName(String valor){
		escrever("lastname", valor);
	}
	
	public void setDateOfBirth(String data)
	{
		escrever("birthdate", data);
	}
	
	public void setCountry(String valor){
		selecionarCombo("country", valor);
	}
	
	public void setZipCode(String valor){
		escrever("zipcode", valor);
	}
	
	public void setOccupation(String valor){
		selecionarCombo("occupation", valor);
	}
	
	public void setHobbiesSpeeding(){
		clicarCheck(By.xpath("//*[@id=\"insurance-form\"]/div/section[2]/div[10]/p/label[1]/span"));
	}
	
	public void clicarAba(String valor)
	{
		clicarLink(valor);
	}
	
	public void setStartDate(String data){
		escrever("startdate", data);
	}
	
	public void setInsuranceSum(String valor){
		selecionarCombo("insurancesum", valor);
	}
	
	public void setMeritRating(String valor){
		selecionarCombo("meritrating", valor);
	}
	
	public void setDamageInsurance(String valor){
		selecionarCombo("damageinsurance", valor);
	}
	
	public void setOptionalProductsEuroProtection(){
		clicarCheck(By.xpath("//*[@id=\"insurance-form\"]/div/section[3]/div[5]/p/label[1]/span"));
	}
	
	public void setCourtesyCar(String valor)
	{
		selecionarCombo("courtesycar", valor);
	}
	
	public String obterMensagemSucesso() {
		return obterTexto(By.xpath("//div['sweet-alert showSweetAlert visible']//h2"));
	}
	
	public void setOptionSilver(){
		clicarCheck(By.xpath("//*[@id=\"insurance-form\"]/div/section[4]/section/div[1]/table/tfoot/tr/th[2]/label[1]/span"));
	}
	
	public void setEmail(String valor){
		escrever("email", valor);
	}
	
	public void setUsername(String valor){
		escrever("username", valor);
	}
	
	public void setPassword(String valor){
		escrever("password", valor);
	}
	
	public void setConfirmPassword(String valor){
		escrever("confirmpassword", valor);
	}
	
	public void clicarSend(){
		clicarBotao("sendemail");
	}
	
	public String obterTituloPagina() {
		return obterTexto("selectedinsurance");
	}
	
	public List<String> obterErros(){
		List<WebElement> erros = DriverFactory.getDriver().findElements(By.xpath("//div[@class='alert alert-danger']//li"));
		List<String> retorno = new ArrayList<String>();
		for(WebElement erro: erros){
			retorno.add(erro.getText());
		}
		return retorno;
	}
	
	public void aguardarCarregamentoDaTela() {
		aguardarCarregamento();
	}


}

