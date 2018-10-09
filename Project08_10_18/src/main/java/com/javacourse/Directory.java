package com.javacourse;

import java.util.Objects;

public class Directory {
    private String path;
//you didn't override toString();
    @Override
    public String toString() {
        return getClass().getName() + "@path:" + path;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directory directory = (Directory) o;
        return Objects.equals(path, directory.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static class DirectoryBuilder{
        private String path;
        private String name;

        public DirectoryBuilder(String path) {
            this.path = path;
        }

        public DirectoryBuilder name(String name){
            this.name = name;
            return this;
        }

        public Directory build(){
            return new Directory(this);
        }
    }

    private Directory(DirectoryBuilder directoryBuilder){
        this.path = directoryBuilder.path;
    }


}
