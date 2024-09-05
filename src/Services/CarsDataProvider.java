package Services;


import Abstractions.IDataProvider;
import Abstractions.ILogger;
import Constans.ErrorCodes;
import Models.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class CarsDataProvider implements IDataProvider<Item> {
    private final HashMap <Integer, Item> _items;
    private final ILogger _logger;

    public CarsDataProvider(String path, ILogger logger) {
        _items = new HashMap<>();
        _logger = logger;

        ReadFile(path);
    }
    private void ReadFile(String path) {
        _logger.logInfo("Reading from an items file started");
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {




// Reading method here


            _logger.logInfo("Reading from an items file ended");
            _logger.logInfo("");
        } catch (IOException e) {
            System.out.println("Error while reading items file");
            _logger.logError(ErrorCodes.InternalServerError);
            System.exit(0);
        }
    }
    @Override
    public Item Get(Integer id) {
        return _items.get(id);
    }
    @Override
    public List<Item> GetAll() {
        return _items.values().stream().toList();
    }
}









