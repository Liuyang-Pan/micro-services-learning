package org.example.constant;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/11/25 15:36
 */
public class ConstantPool {
    private ConstantPool() {
    }

    public final static String JAVA_CLIENT = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"id\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"name\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\",\n" +
            "        \"copy_to\": \"search_key\"\n" +
            "      },\n" +
            "      \"address\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\",\n" +
            "        \"copy_to\": \"search_key\"\n" +
            "      },\n" +
            "      \"title\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"copy_to\": \"search_key\"\n" +
            "      },\n" +
            "      \"search_key\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
