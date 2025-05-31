package io.github.thdevonly.zictus;

import java.io.*;

public class FileNode
 {
    public String name;         // Nome do arquivo ou pasta
    public boolean isDirectory; // Se é diretório ou não
    public File file;           // Referência real ao arquivo

    public FileNode(File file) {
        this.name = file.getName();
        this.isDirectory = file.isDirectory();
        this.file = file;
    }
}
