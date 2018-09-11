package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Datas {

    public static SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

    public static Date stringToData(String data) {
        Date date = null;
        try {
            date = formatoData.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
