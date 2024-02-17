import exceptions.ValidateException;
import model.CryptoModel;
import model.HackModel;
import model.enums.Menu;
import service.BruteForceService;
import service.ConsoleService;
import service.CryptoService;
import service.FileService;
import service.FileValidationService;
import service.LoggerService;
import service.TextAnalyzer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CryptoModel cryptoModel = new CryptoModel();
        LoggerService loggerService = new LoggerService();
        FileService fileService = new FileService(loggerService);

        FileValidationService fileValidationService = new FileValidationService(loggerService);
        CryptoService cryptoService = new CryptoService(fileService, fileValidationService);

        HackModel hackModel = new HackModel();
        TextAnalyzer textAnalyzer = new TextAnalyzer();
        BruteForceService bruteForceService = new BruteForceService(hackModel, cryptoService, textAnalyzer, fileService, fileValidationService);

        Scanner scanner = new Scanner(System.in);
        ConsoleService consoleService = new ConsoleService(scanner, cryptoModel, cryptoService, hackModel, bruteForceService);

        Menu menu;
        do {
            consoleService.printMenu();
            int choiceNumber = consoleService.chooseMenuNumber();

            try {
                menu = consoleService.getMenu(choiceNumber);
                consoleService.crypt(menu);
            } catch (ValidateException e) {
                System.err.println(e.getMessage());
            }
        } while (consoleService.doReplay());
    }
}
