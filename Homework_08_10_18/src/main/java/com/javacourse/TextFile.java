package com.javacourse;

import java.util.Objects;

public class TextFile extends File{

    private StringBuilder content;

    public TextFile(String path, String name){
        super(path, name);
        content = new StringBuilder();
    }

    @Override
    public void rename(String newName) {
        if(!name.equals(newName))
            name = newName;
    }

    public static TextFile create(String path, String name){
        return new TextFile(path, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TextFile textFile = (TextFile) o;
        return content.toString().equals(textFile.content.toString()) &&
                Objects.equals(directory, textFile.directory) &&
                Objects.equals(name, textFile.name);
    }

    /*I decided to calculate hash on the string representation of content,
    * because in our program we only need to take into akkount the string value of this field,
    * not the object itself*/
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), content.toString());
    }

    public void appendContent(String cnt){
        if(cnt!=null)
            content.append(cnt);
    }

    @Override
    public void delete() {
        //do stuff...
    }

    @Override
    public String toString() {
        return "TextFile{" +
                "directory=" + directory +
                "name=" + name +
                "content=" + content.toString()+"\n"+
                "} " + super.toString();
    }
}
