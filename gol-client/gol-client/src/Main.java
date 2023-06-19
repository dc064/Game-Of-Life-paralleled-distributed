import java.util.List;
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
import cell.CellRepository;
import constants.Constants;
import display.GOLFrame;
import settings.GlobalSettings;

public class Main {
    public static void main(String args[]) throws InterruptedException, IOException, URISyntaxException {

        final Type listOfMyClassObject = new TypeToken<ArrayList<Cell>>(){}.getType();

        int iteration = 0;
        
        GOLFrame golFrame = new GOLFrame();
        
        while (iteration < GlobalSettings.iterations) {
            HttpResponse<String> response = HttpClient
            .newBuilder()
            .proxy(ProxySelector.getDefault())
            .build()
            .send(HTTPhandler.requestGet(Constants.cellsPath + iteration), HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            List<Cell> outputList = gson.fromJson(response.body(), listOfMyClassObject);
            golFrame.panel.removeAll();
            CellRepository.cells = outputList;
            golFrame.panel.repaint();

            Thread.sleep(GlobalSettings.delay);
        
            iteration++;
        }
    }
}