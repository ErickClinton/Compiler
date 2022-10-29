package nsp.compiler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class File {
    private String fileName;
    public File (String fileName){
        this.fileName = fileName;
    }

    public String getCodFont(){
        Path filePath = Paths.get(fileName, new String[0]);
        try {
            List<String> list = Files.readAllLines(filePath);
            return String.join("\n", list);
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado");
            System.exit(0);
            return "";
        }
    }
}
