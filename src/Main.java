import consts.Consts;
import model.CryptoModel;
import service.ConsoleService;
import service.CryptoService;
import service.FileService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CryptoService cryptoService = new CryptoService(new FileService());
        ConsoleService consoleService = new ConsoleService(new Scanner(System.in));

        int value = 1;

        switch (value) {
            case 1: {
                CryptoModel cryptoModel = consoleService.createCryptoModel(Consts.ENCRYPT_FILE);
                cryptoService.crypt(cryptoModel);
                break;
            }
            case 2: {
                CryptoModel cryptoModel = consoleService.createCryptoModel(Consts.DECRYPT_FILE);
                cryptoService.crypt(cryptoModel);
                break;
            }
        }
    }
}