package com.issproject.utils;

import com.issproject.service.SynchronizeService;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    public void startApp(){
        SynchronizeService synchronizeService = new SynchronizeService();
        System.out.println("Type 1 to search for satelite position || Type 2 to search for astronauts");
        String userInputStr = scanner.nextLine();
        int userInput = Integer.parseInt(userInputStr);
        switch (userInput){
            case 1:
                synchronizeService.displaySatelitePosition(synchronizeService.getSatelite());
                break;
            case 2:
                System.out.println("Enter craft name");
                String craftName = scanner.nextLine();
                synchronizeService.displayAstronoutsByCraft(craftName);
                break;
            default:
                synchronizeService.displayAllAstronouts();
        }
    }
}
