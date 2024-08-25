
import java.nio.charset.StandardCharsets;
import java.util.Map;

import jp.livlog.cabocha4j.CaboCha;
import jp.livlog.cabocha4j.data.Chunk;
import jp.livlog.cabocha4j.data.Token;

public class Main {

    public static void main(final String[] args) {

        final var cabochaPath = "C:\\Program Files (x86)\\CaboCha\\bin\\cabocha.exe";
        final var charset = StandardCharsets.UTF_8;

        final var cabochaWrapper = new CaboCha(cabochaPath, charset);

        final var sentence = "太郎は花子が読んでいる本を次郎に渡した。";

        try {
            final var result = cabochaWrapper.parse(sentence);

            for (final Map.Entry <String, Chunk> entry : result.entrySet()) {
                final var chunk = entry.getValue();
                System.out.println("Chunk ID: " + chunk.getId() + ", Link: " + chunk.getLink());

                for (final Token token : chunk.getTokenList()) {
                    System.out.println("Token: " + token.getSurface() + ", Feature: " + token.getFeature() + ", NE: " + token.getNe());
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
