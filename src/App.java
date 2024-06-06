import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Cachorro cachorro = new Cachorro();

        System.out.println("Digite qual ação quer executar");
        System.out.println("(1 -> inserir novo cachorro)");
        System.out.println("(2 -> deletar cachorro)");
        String answer = scanner.nextLine();

        if (answer.equals("1")) {
            System.out.println("Digite o nome do seu doguinho-> ");
            String name = scanner.nextLine();
            cachorro.setName(name);

            System.out.println("Digite o raça do seu doguinho-> ");
            String race = scanner.nextLine();
            cachorro.setRace(race);

            System.out.println("Digite o idade do seu doguinho-> ");
            int age = scanner.nextInt();
            cachorro.setAge(age);

            String countIdsString = readFile()[0];
            int countIds = Integer.valueOf(countIdsString).intValue();

            int id = countIds + 1;

            String content = String.format(
                "%d//%s//%d//%s", 
                id, 
                cachorro.getName(), 
                cachorro.getAge(), 
                cachorro.getRace()
            );

            writeFileAppend(Arrays.asList(content));
        } else if (answer.equals("2")) {
            System.out.println("digite o nome do cachorro que quer remover ->");
            String remove = scanner.nextLine();

            String[] lines = readFile();

            List<String> linhasFiltradas = Arrays.stream(lines)
                .filter(linha -> !linha.contains(remove))
                .collect(Collectors.toList())
            ;
        
            writeNewFile(linhasFiltradas);
        } else {
            System.out.println("opção invalida tente novamente");
        }
    }

    private static String[] readFile() {
        Path filePath = Paths.get("database/animais.txt");

        try {
            return (Files.readAllLines(filePath)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void writeFileAppend(List<String> content) {
        Path filePath = Paths.get("database/animais.txt");

        try {
            Files.write(
                filePath,
                content,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeNewFile(List<String> content) {
        Path filePath = Paths.get("database/animais.txt");

        try {
            Files.write(
                filePath,
                content
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}