import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    RouteCalculator routeCalculator;
    StationIndex stationIndex;
    List<Station> connections;
    List<Station> route;

    Line line1;
    Line line2;
    Line line3;

    Station station1;
    Station station2;
    Station station3;
    Station station4;
    Station station5;
    Station station6;
    Station station7;
    Station station8;
    Station station9;

    @Override
    protected void setUp() throws Exception {

//testCalculateDuration====================================================
        stationIndex = new StationIndex();
        routeCalculator = new RouteCalculator(stationIndex);
        line1 = new Line(1, "Первая");
        line2 = new Line(2, "Вторая");
        line3 = new Line(3, "Третья");

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        station1 = new Station("Чкаловская", stationIndex.getLine(1));
        station2 = new Station("Геологическая", stationIndex.getLine(1));
        station3 = new Station("Площадь 1905", stationIndex.getLine(1));
        station4 = new Station("Уральская", stationIndex.getLine(2));
        station5 = new Station("Динамо", stationIndex.getLine(2));
        station6 = new Station("Виз", stationIndex.getLine(2));
        station7 = new Station("Жби", stationIndex.getLine(3));
        station8 = new Station("Уралмаш", stationIndex.getLine(3));
        station9 = new Station("Ботаническая", stationIndex.getLine(3));

        Station[] stations = {station1, station2, station3, station4, station5, station6, station7, station8, station9};

        for (Station station : stations) {
            stationIndex.addStation(station);
        }

        for (int i = 0, j = 1; j < 4; i++) {
            stationIndex.number2line.get(j).addStation(stations[i]);
            if ((i + 1) % 3 == 0) {
                j++;
            }
        }

        connections = new ArrayList<>();
        connections.add(station1);
        connections.add(station4);
        connections.add(station6);
        connections.add(station8);

        for (int i = 0; i < connections.size(); i += 2) {
            List<Station> gap = new ArrayList<>();
            gap.add(connections.get(i));
            gap.add(connections.get(i + 1));
            stationIndex.addConnection(gap);
        }

//testGetShortestRoute=====================================================
        route = new ArrayList<>();
        route.add(station1);
        route.add(station2);
        route.add(station3);
        route.add(station4);
    }


    public void testGetShortestRoute() {
        List<Station> actual = routeCalculator.getShortestRoute(station1, station9);
        List<Station> expected = new ArrayList<>();
        expected.add(station1);
        expected.add(station4);
        expected.add(station5);
        expected.add(station6);
        expected.add(station8);
        expected.add(station9);

        assertEquals(expected, actual);
    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 8.5;
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
