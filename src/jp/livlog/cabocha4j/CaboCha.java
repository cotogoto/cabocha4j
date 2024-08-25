package jp.livlog.cabocha4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import jp.livlog.cabocha4j.data.Chunk;
import jp.livlog.cabocha4j.data.Token;

public final class CaboCha {

    private final String        cabochaPath;

    private final Charset       charset;

    private static final String COMMAND = " -f3 -n1";

    public CaboCha(final String cabochaPath, final Charset charset) {

        this.cabochaPath = cabochaPath;
        this.charset = charset;
    }


    public Map <String, Chunk> parse(final String sentence) throws Exception {

        final Map <String, Chunk> ret = new LinkedHashMap <>();

        if (sentence == null || sentence.trim().isEmpty()) {
            return ret;
        }

        // CaboChaの出力を取得
        final var xmlOutput = this.execute(sentence);

        // XMLをパースして係り受け解析結果を構築
        final var factory = DocumentBuilderFactory.newInstance();
        final var builder = factory.newDocumentBuilder();
        final var doc = builder.parse(new InputSource(new StringReader(xmlOutput)));

        final var chunkNodes = doc.getElementsByTagName("chunk");
        for (var i = 0; i < chunkNodes.getLength(); i++) {
            final var chunkElement = (Element) chunkNodes.item(i);
            final var chunk = this.parseChunkTag(chunkElement);
            ret.put(chunk.getId(), chunk);
        }

        return ret;
    }


    private String execute(final String sentence) throws Exception {

        final var sb = new StringBuilder();
        final var process = Runtime.getRuntime().exec(this.cabochaPath + CaboCha.COMMAND);

        try (var writer = new OutputStreamWriter(process.getOutputStream(), this.charset);
                var br = new BufferedReader(new InputStreamReader(process.getInputStream(), this.charset))) {

            writer.write(sentence);
            writer.flush();
            writer.close();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }

            final var exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("CaboCha exited with error code: " + exitCode);
            }
        } finally {
            process.destroy();
        }

        return sb.toString().trim();
    }


    private Chunk parseChunkTag(final Element chunkElement) {

        final var chunk = new Chunk();
        chunk.setTokenList(new ArrayList <>());
        chunk.setId(chunkElement.getAttribute("id"));
        chunk.setLink(chunkElement.getAttribute("link"));
        chunk.setRel(chunkElement.getAttribute("rel"));
        chunk.setScore(chunkElement.getAttribute("score"));
        chunk.setHead(chunkElement.getAttribute("head"));
        chunk.setFunc(chunkElement.getAttribute("func"));

        final var tokNodes = chunkElement.getElementsByTagName("tok");
        for (var j = 0; j < tokNodes.getLength(); j++) {
            final var tokElement = (Element) tokNodes.item(j);
            final var token = this.parseTokTag(tokElement);
            chunk.getTokenList().add(token);
        }

        return chunk;
    }


    private Token parseTokTag(final Element tokElement) {

        final var token = new Token();
        token.setId(tokElement.getAttribute("id"));
        token.setFeature(tokElement.getAttribute("feature"));
        token.setNe(tokElement.getAttribute("ne"));
        token.setSurface(tokElement.getTextContent());
        return token;
    }
}
