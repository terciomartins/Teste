package com.tricentis.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {
	
	public static Date obterDataComDiferencaDias(int dias) {// obtem a data atual e acrecenta x dias para obter data futura
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, dias);
		return calendar.getTime();
	}
	
	public static String obterDataFormatada(Date data) { // formata uma data no padrao dd/mm/aaaa
		DateFormat format = new SimpleDateFormat("MM/dd/YYYY");
		return format.format(data);
	}

}

