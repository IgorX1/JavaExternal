package com.javacourse;

public class TestingConstants {
    public static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<site>\n" +
            "    <page id=\"ID-1\">\n" +
            "        <title>Home page</title>\n" +
            "        <type>ads</type>\n" +
            "        <authorize>false</authorize>\n" +
            "        <chars name=\"free\"/>\n" +
            "    </page>\n" +
            "    <page id=\"ID-2\">\n" +
            "        <title>World news</title>\n" +
            "        <type>news</type>\n" +
            "        <authorize>false</authorize>\n" +
            "        <chars name=\"free\"/>\n" +
            "        <chars name=\"hasEmail\"/>\n" +
            "    </page>\n" +
            "    <page id=\"ID-3\">\n" +
            "        <title>Mirror web page</title>\n" +
            "        <type>mirror</type>\n" +
            "        <authorize>true</authorize>\n" +
            "        <chars name=\"hasEmail\"/>\n" +
            "        <chars name=\"downloadable\"/>\n" +
            "    </page>\n" +
            "</site> ";
}
