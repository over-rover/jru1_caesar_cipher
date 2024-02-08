package service;

import consts.Consts;
import model.CryptoModel;

import java.util.Scanner;

public class ConsoleService {
    Scanner sc;

    public ConsoleService(Scanner sc) {
        this.sc = sc;
    }

    public CryptoModel createCryptoModel(String sourceFile) {
        CryptoModel cryptoModel = new CryptoModel();
        System.out.println(sourceFile);
        cryptoModel.setSourcePath(sc.nextLine());

        System.out.println(Consts.RESULT_FILE);
        cryptoModel.setTargetPath(sc.nextLine());

        System.out.println(Consts.ENTER_KEY);
        cryptoModel.setKey(Utils.normalizeKey(sc.nextInt()));

        return cryptoModel;
    }
}