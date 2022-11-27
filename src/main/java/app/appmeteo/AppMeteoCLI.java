package app.appmeteo;

import java.io.IOException;
import java.util.Scanner;

public class AppMeteoCLI {
    public static void main(String[] args) throws IOException {
        System.out.println(" __        __   _                            _         __        __         _   _                _                  _ \n" +
                " \\ \\      / ___| | ___ ___  _ __ ___   ___  | |_ ___   \\ \\      / ___  __ _| |_| |__   ___ _ __ / \\   _ __  _ __   | |\n" +
                "  \\ \\ /\\ / / _ | |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\   \\ \\ /\\ / / _ \\/ _` | __| '_ \\ / _ | '__/ _ \\ | '_ \\| '_ \\  | |\n" +
                "   \\ V  V |  __| | (_| (_) | | | | | |  __/ | || (_) |   \\ V  V |  __| (_| | |_| | | |  __| | / ___ \\| |_) | |_) | |_|\n" +
                "    \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/     \\_/\\_/ \\___|\\__,_|\\__|_| |_|\\___|_|/_/   \\_| .__/| .__/  (_)\n" +
                "                                                                                                     |_|   |_|        ");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("We're happy to see you, if it's your first time here, please type " + "help " + "to see all existing commands !" + "\n");

        System.out.print("MAGZ$> ");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        do{
            String commandWord = command;
            String[] commandWordArray = commandWord.split(" ",2);
            if (commandWordArray.length == 2 && commandWordArray[0].equals("weather")) {

                    DataFetcher dataFetcher = new DataFetcher(commandWordArray[1]);
                    dataFetcher.nowQuery();

            }
            else if (commandWordArray.length == 2 && commandWordArray[0].equals("hourly")){

                try {
                    for (int i = 0; i <= 5 ; i++) {
                        DataFetcher dataFetcher = new DataFetcher(commandWordArray[1],i);
                        dataFetcher.geocodingQuery();
                        dataFetcher.oneCallHourlyQuery();
                    }
                } catch (IOException ignored) {
                }
            }
            else if (commandWordArray.length == 2 && commandWordArray[0].equals("daily")) {
                try {
                    for (int i = 0; i <= 5; i++) {
                        DataFetcher dataFetcher = new DataFetcher(commandWordArray[1], i);
                        dataFetcher.geocodingQuery();
                        dataFetcher.oneCallDailyQuery();
                    }
                } catch (IOException ignored) {

                }
            }

            else if (command.equals("weather")) {
                System.out.print("MAGZs$>City> ");
                Scanner cityScan = new Scanner(System.in);
                String city = cityScan.nextLine();
                DataFetcher dataFetcher = new DataFetcher(city);
                dataFetcher.nowQuery();
            }


            else if (command.equals("daily")){
                System.out.print("MAGZs$>City> ");
                Scanner cityScan = new Scanner(System.in);
                String city = cityScan.nextLine();
                try {
                    for (int i = 0; i <= 5; i++) {
                        DataFetcher dataFetcher = new DataFetcher(city,i);
                        dataFetcher.geocodingQuery();
                        dataFetcher.oneCallDailyQuery();
                    }
                } catch (IOException ignored) {
                }

            }


            else if (command.equals("hourly")){
                System.out.print("MAGZs$>City> ");
                Scanner cityScan = new Scanner(System.in);
                String city = cityScan.nextLine();
                try {
                    for (int i = 0; i <= 5; i++) {
                        DataFetcher dataFetcher = new DataFetcher(city, i);
                        dataFetcher.geocodingQuery();
                        dataFetcher.oneCallHourlyQuery();
                    }
                } catch (IOException ignored) {

                }
            }


            else if (command.equals("help")) {
                System.out.println("\n\n----------------------------------------------------\n" +
                        "\t\tBE CAREFUL WHEN YOU TYPE THE NAME OF A CITY TO NOT LEAVE ANY SPACE\n\n" +
                        "help : Shows all commands available at the moment.\n" +
                        "weather : This command allows you to have a to have the weather forecast of a given city at the time the command is executed. After writing weather, you have to enter a city name, be careful about the spelling.\n" +
                        "weather [NameOfTheCity] : Does the same as before but you can write the name of the city directly after writing weather.\n" +
                        "daily : First, the command asks you the city, then it gives you the weather forecast for the next 5 days.\n" +
                        "daily [NameOfTheCity] : Does the same as before, but you can write the name of the city directly after writing daily.\n" +
                        "hourly : First, the command asks you the city, then it gives you the weather forecast for the next 5 hours.\n" +
                        "hourly [NameOfTheCity] : Does the same as before, but you can write the name of the city directly after writing hourly.\n" +
                        "quit : Leaves the current terminal" +
                        "\n\n----------------------------------------------------\n\n");
            }
            else {
                System.out.print(" This command does not exist, if you don't know any command, you can write \"help\" \n");
            }

            System.out.print("MAGZ$> ");
            command = scanner.nextLine();
        } while (!command.equals("quit"));
        scanner.close();
    }
}
