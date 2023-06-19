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
import settings.GameSettings;

public class Main {
    public static void main(String args[]) throws Exception {

        final Type cellListType = new TypeToken<ArrayList<Cell>>(){}.getType();
        final Type settingsListType = new TypeToken<List<Integer>>(){}.getType();

        int currentIteration = 0;
        
        GOLFrame golFrame = new GOLFrame();
        
        HttpResponse<String> settingsResponse = HttpClient
        .newBuilder()
        .proxy(ProxySelector.getDefault())
        .build()
        .send(HTTPhandler.requestGet(Constants.settingPath), HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();

        List<Integer> settingsValues = gson.fromJson(settingsResponse.body(), settingsListType);

        GameSettings.readSettings(settingsValues);

        while (currentIteration < GameSettings.iterations) {
            HttpResponse<String> response = HttpClient
            .newBuilder()
            .proxy(ProxySelector.getDefault())
            .build()
            .send(HTTPhandler.requestGet(Constants.cellsPath + currentIteration), HttpResponse.BodyHandlers.ofString());

            List<Cell> outputList = gson.fromJson(response.body(), cellListType);
            golFrame.updateCellDisplay();
            CellRepository.cells = outputList;
            golFrame.updateCellDisplay();

            Thread.sleep(GameSettings.delay);
        
            currentIteration++;
        }
    }
}