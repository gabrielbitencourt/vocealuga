package routes;
import java.util.ArrayList;
import java.util.List;

import routes.Routing;


/**
 * @author EDS
 *
 */
public class Router {
    private List<Routing> listeners = new ArrayList<Routing>();

    public Router(Routing master) {
        listeners.add(master);
    }

    public void navigateTo(String tela) {
        for (Routing j : listeners) {
        	j.navigateTo(tela);
        }
    }
}