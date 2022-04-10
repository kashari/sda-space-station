package com.issproject.utils;

import com.issproject.service.SynchronizeService;

import java.util.Scanner;

public class Menu {
    SynchronizeService synchronizeService = new SynchronizeService();

    private static Menu instance;
    private Menu(){}
    public static Menu getInstance(){
        if (instance == null){
            instance = new Menu();
        }
        return instance;
    }
    Scanner scanner = new Scanner(System.in);
    public void startApp(){
        System.out.println("Type 1 to search for satelite position || Type 2 to search for astronauts || Type 3 to quit");
        String userInputStr = scanner.nextLine();
        int userInput = Integer.parseInt(userInputStr);
        switch (userInput){
            case 1:
                selectSatelitePositionMethod();
                startApp();
                break;
            case 2:
                System.out.println("Enter craft name");
                String craftName = scanner.nextLine();
                if (craftName.equalsIgnoreCase("all")){
                    synchronizeService.displayAllAstronauts();
                }
                else{
                    synchronizeService.displayAstronoutsByCraft(craftName);
                }
                startApp();
                break;
            case 3:
                quit();
                break;
            default:
                synchronizeService.displayAllAstronauts();
                startApp();
        }
    }

    private void selectSatelitePositionMethod() {
        System.out.println("Type 1 to get current position || Type 2 to get recent positions || Type 3 to get grouped reports");
        String userChoiceStr = scanner.nextLine();
        int userChoice = Integer.parseInt(userChoiceStr);
        switch (userChoice){
            case 1:
                synchronizeService.getReportJSON();
                break;
            case 2:
                synchronizeService.displayAllReports();
                break;
            case 3:
                synchronizeService.displayReportsGroupedByCountry();
                break;
            default:
                System.out.println("Wrong input");
                selectSatelitePositionMethod();
        }
    }

    private void quit() {
        System.out.println("Goodbye from space");
    }

}
