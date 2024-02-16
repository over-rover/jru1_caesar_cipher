package service;

import consts.Consts;
import exceptions.ValidateException;
import model.CryptoModel;
import model.HackModel;
import model.enums.Menu;

import java.util.List;
import java.util.Scanner;

public class ConsoleService {
    private final Scanner scanner;
    private final CryptoModel cryptoModel;
    private final CryptoService cryptoService;
    private final HackModel hackModel;
    private final BruteForceService bruteForceService;

    public ConsoleService(Scanner scanner,
                          CryptoModel cryptoModel, CryptoService cryptoService,
                          HackModel hackModel, BruteForceService bruteForceService) {
        this.scanner = scanner;
        this.cryptoModel = cryptoModel;
        this.cryptoService = cryptoService;
        this.hackModel = hackModel;
        this.bruteForceService = bruteForceService;
    }

    public void printMenu() {
        System.out.println("Введите число соответствующее вашему выбору: ");
        List<Menu> menus = Menu.getAllDisplayable();
        for (Menu menu : menus) {
            System.out.println(menu.getNumber() + ": " + menu.getText());
        }
    }

    public int chooseMenuNumber() {
        return Integer.parseInt(scanner.nextLine());
    }

    public Menu getMenu(int number) throws ValidateException {
        return Menu.getMenu(number);
    }

    public void crypt(Menu menu) throws ValidateException {
        switch (menu) {
            case ENCRYPT: {
                fillCryptoModel(cryptoModel, Consts.ENCRYPT_FILE);
                cryptoService.crypt(cryptoModel);
                break;
            }
            case DECRYPT: {
                fillCryptoModel(cryptoModel, Consts.DECRYPT_FILE);
                cryptoService.crypt(cryptoModel);
                break;
            }
            case BRUTE_FORCE: {
                fillCryptoModel(hackModel, Consts.DECRYPT_FILE);
                bruteForceService.bruteForce();
                break;
            }
            case STATISTIC_ANALYZE: {
                fillCryptoModel(hackModel, Consts.DECRYPT_FILE);
                //statisticAnalyzeService. логика не реализована
            }
        }
    }

    public boolean doReplay() {
        while (true) {
            System.out.println("Хотите повторить? Введите y / n");
            switch (scanner.nextLine().toLowerCase()) {
                case "n": {
                    return false;
                }
                case "y": {
                    return true;
                }
                default: {
                    System.out.println("Введен некорректный символ. Проверьте раскладку клавиатуры");
                }
            }
        }


    }

    private void fillCryptoModel(CryptoModel cryptoModel, String sourceFile) {
        System.out.println(sourceFile);
        cryptoModel.setSourcePath(scanner.nextLine());

        System.out.println(Consts.RESULT_FILE);
        cryptoModel.setTargetPath(scanner.nextLine());

        if (cryptoModel instanceof HackModel)
            return;

        System.out.println(Consts.ENTER_KEY);
        int key = Integer.parseInt(scanner.nextLine());
        cryptoModel.setKey(Utils.normalizeKey(key));
    }
}
