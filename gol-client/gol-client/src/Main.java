import java.util.List;
import java.util.Random;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.ProxySelector;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cell.Cell;
import display.GOLFrame;
import settings.GlobalSettings;

public class Main {
    public static void main(String args[]) throws InterruptedException, IOException, URISyntaxException {

        final String path = "http://localhost:8080/cells/c";
        final Type listOfMyClassObject = new TypeToken<ArrayList<Cell>>(){}.getType();

        GOLFrame golFrame = new GOLFrame();
        
        while (true) {
            HttpResponse<String> response = HttpClient
            .newBuilder()
            .proxy(ProxySelector.getDefault())
            .build()
            .send(HTTPhandler.requestGet(path), HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            List<Cell> outputList = gson.fromJson(response.body(), listOfMyClassObject);

            for (Cell iterator : outputList) {
                golFrame.panel.add(new CellDisplay(iterator.x, iterator.y));
            }

            Thread.sleep(GlobalSettings.delay);
        
            golFrame.panel.removeAll();
        }
    }
}