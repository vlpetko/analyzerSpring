package ru.vlpetko.analyzerspring.utils;

import org.springframework.stereotype.Service;
import ru.vlpetko.analyzerspring.model.Stock;

import java.time.LocalDate;

@Service
public class StockUtils {

    public static Stock convertStringToStock(String data){

        String[] stockData = data.split(",");

        Stock stock = new Stock();

        try {
            stock.setTradingDate(LocalDate.parse(stockData[1]));
        } catch (Exception e){
            System.out.println(e);
        }
        try {
            stock.setOpenPrice(Double.parseDouble(stockData[2]));
        }catch (Exception e){
            System.out.println(e);
        }
        try {
            stock.setHighPrice(Double.parseDouble(stockData[3]));
        }catch (Exception e){
            System.out.println(e);
        }
        try {
            stock.setLowPrice(Double.parseDouble(stockData[4]));
        }catch (Exception e){
            System.out.println(e);
        }
        try {
            stock.setClosePrice(Double.parseDouble(stockData[5]));
        }catch (Exception e){
            System.out.println(e);
        }
        try {
            stock.setAdjClosePrice(Double.parseDouble(stockData[6]));
        }catch (Exception e){
            System.out.println(e);
        }
        try {
            stock.setVolume(Integer.parseInt(stockData[7]));
        }catch (Exception e){
            System.out.println(e);
        }
        try {
            stock.setStockName((stockData[8]));
        }catch (Exception e){
            System.out.println(e);
        }
        return stock;
    }
    public static String convertStockToString(Stock stock){
        return "Дата торгов - " + stock.getTradingDate()
                + "; Цена открытия - " + stock.getOpenPrice()
                + "; Наибольшая цена - " + stock.getHighPrice()
                + "; Низшая цена - " + stock.getLowPrice()
                + "; Цена закрытия - " + stock.getClosePrice()
                + "; Средняя цена закрытия - " + stock.getAdjClosePrice()
                + "; Количество акций - " + stock.getVolume()
                + "; Наименование акции - " + stock.getStockName()
                + "; Номер отчета - " + stock.getReportNumber()
                + "; Дата загрузки " + stock.getUploadDate()
                + ";";
    }
}