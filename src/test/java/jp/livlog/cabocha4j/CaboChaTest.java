package jp.livlog.cabocha4j;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jp.livlog.cabocha4j.data.Chunk;
import jp.livlog.cabocha4j.data.Token;

public class CaboChaTest {

    private static CaboCha cabochaWrapper;

    @BeforeAll
    public static void setUp() {

        final var cabochaPath = "C:\\Program Files (x86)\\CaboCha\\bin\\cabocha.exe";
        final var charset = StandardCharsets.UTF_8;
        CaboChaTest.cabochaWrapper = new CaboCha(cabochaPath, charset);
    }


    @Test
    public void testParseSentence() {

        final var sentence = "太郎は花子が読んでいる本を次郎に渡した。";
        Map <String, Chunk> result = null;

        try {
            result = CaboChaTest.cabochaWrapper.parse(sentence);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        Assertions.assertNotNull(result, "Result should not be null");
        Assertions.assertEquals(6, result.size(), "Result should contain 6 chunks");

        for (final Map.Entry <String, Chunk> entry : result.entrySet()) {
            final var chunk = entry.getValue();
            System.out.println("Chunk ID: " + chunk.getId() + ", Link: " + chunk.getLink());
            Assertions.assertNotNull(chunk.getId(), "Chunk ID should not be null");
            Assertions.assertNotNull(chunk.getLink(), "Chunk link should not be null");

            for (final Token token : chunk.getTokenList()) {
                System.out.println("Token: " + token.getSurface() + ", Feature: " + token.getFeature() + ", NE: " + token.getNe());
                Assertions.assertNotNull(token.getSurface(), "Token surface should not be null");
                Assertions.assertNotNull(token.getFeature(), "Token feature should not be null");
                Assertions.assertNotNull(token.getNe(), "Token NE should not be null");
            }
        }
    }
}
