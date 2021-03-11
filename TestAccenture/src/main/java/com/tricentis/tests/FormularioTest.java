package com.tricentis.tests;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import com.tricentis.core.BaseTest;
import com.tricentis.pages.FormularioPage;
import com.tricentis.utils.DataUtils;

// @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FormularioTest extends BaseTest{
	

	private FormularioPage formularioPage = new FormularioPage();
	
	@Test
	public void test1_InserirMovimentacao() 
	{

		formularioPage.aguardarCarregamentoDaTela();
		formularioPage.setMake("Audi");
		formularioPage.setModel("Scooter");
		formularioPage.setCylinderCapacity("1");
		formularioPage.setEnginePerformance("1");
		formularioPage.setDateOfManufacture(DataUtils.obterDataFormatada(new Date()));
		formularioPage.setNumberOfSeats("2");
		formularioPage.setNumberOfSeatsMotorcycle("1");
		formularioPage.setFuelType("Diesel");
		formularioPage.setPayload("10");
		formularioPage.setTotalWeight("100");
		formularioPage.setListPrice("1000");
		formularioPage.setAnnualMileage("100000");		
		formularioPage.clicarNext("Enter Vehicle Data");
		
		formularioPage.aguardarCarregamentoDaTela();
		formularioPage.setFirtsName("Tercio");
		formularioPage.setLastName("Martins");
		formularioPage.setDateOfBirth("05/05/1974");
		formularioPage.setCountry("Brazil");
		formularioPage.setZipCode("222222");
		formularioPage.setOccupation("Public Official");
		formularioPage.setHobbiesSpeeding();		
		formularioPage.clicarAba("Enter Product Data");
		
		formularioPage.aguardarCarregamentoDaTela();
		formularioPage.setStartDate(DataUtils.obterDataFormatada(DataUtils.obterDataComDiferencaDias(90)));
		formularioPage.setInsuranceSum("3.000.000,00");
		formularioPage.setMeritRating("Super Bonus");
		formularioPage.setDamageInsurance("No Coverage");
		formularioPage.setOptionalProductsEuroProtection();
		formularioPage.setCourtesyCar("No");		
		formularioPage.clicarAba("Select Price Option");
		
		formularioPage.aguardarCarregamentoDaTela();
		formularioPage.setOptionSilver();
		formularioPage.clicarAba("Send Quote");
		
		formularioPage.aguardarCarregamentoDaTela();
		formularioPage.setEmail("tercio.martins@gmail.com");
		formularioPage.setUsername("Tercio");
		formularioPage.setPassword("Teste123");
		formularioPage.setConfirmPassword("Teste123");
		formularioPage.clicarSend();
		
		Assert.assertEquals("Sending e-mail success!", formularioPage.obterMensagemSucesso());
		
	}

}

