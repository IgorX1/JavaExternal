package com.javacourse;

import java.util.Objects;

public abstract class File {
    Directory directory;
    String name;

    /*Tried to benefit from using
    * builder pattern
    * (no, in fact I just wanted to
    * try using it in real life
    * but it's really redundant here)*/


    public File(String path, String name) {
        this.directory = new Directory.DirectoryBuilder(path).name(name).build();
    }

    @Override
    public String toString() {
        return "File{" +
                "directory=" + directory +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(directory, file.directory) &&
                Objects.equals(name, file.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directory, name);
    }

    public abstract void rename(String newName);
    public abstract void delete();

}
