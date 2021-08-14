import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Calendar nowCalendar = Calendar.getInstance(); //необходим для фильтрации №2
        List<Terminal> terminals = Airport.getInstance().getTerminals();
        terminals
                .forEach(terminal -> terminal.getFlights()
                        .stream()

                        //№1 фильтрация через System.currentTimeMillis()
//                        .filter(flight -> flight.getDate().getTime() >= System.currentTimeMillis())
//                        .filter(flight -> flight.getDate().getTime() <= System.currentTimeMillis()+(3600000*2))

                        //№2 фильтрация через after и before
//                        .filter(flight -> flight.getDate().after(nowCalendar.getTime()) &&
//                        flight.getDate().before(futureDate(nowCalendar)))
                        //или
//                        .filter(flight -> flight.getDate().after(nowCalendar.getTime()))
//                        .filter(flight -> flight.getDate().before(futureDate(nowCalendar)))

                        //№3 фильтрация через отдельный метод
                        .filter(flight -> inputIntervalDate(flight.getDate()))

                        .filter(flight -> flight.getType() == Flight.Type.DEPARTURE)
                        .forEach(flight -> System.out.println("Время вылета: " + dateFormat.format(flight.getDate())
                                + ". Модель самолёта: " + flight.getAircraft())));
    }

    public static Date futureDate(Calendar calendar) {  //необходим для фильтрации №2
        calendar.add(Calendar.HOUR, +2);
        return calendar.getTime();
    }

    public static boolean inputIntervalDate(Date date) {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar furureCalendar = (Calendar) nowCalendar.clone();
        furureCalendar.add(Calendar.HOUR, +2);
        return date.after(nowCalendar.getTime()) && date.before(furureCalendar.getTime());
    }
}
