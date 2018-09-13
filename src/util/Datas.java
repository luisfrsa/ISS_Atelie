package util;

import dao.AbstractDAOImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Datas {

    public static SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
    private static final Logger log = LoggerFactory.getLogger(Datas.class);

    public static Date stringToData(String data) {
        Date date = null;
        try {
            date = formatoData.parse(data);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return date;
    }

    public static String dateToString(Date date) {
        return formatoData.format(date);
    }

}
