package jp.livlog.cabocha4j.data;
import java.util.ArrayList;
import java.util.List;

public class CaboChaResult {
    private List<Chunk> chunks;

    public CaboChaResult() {
        this.chunks = new ArrayList<>();
    }

    public void addChunk(Chunk chunk) {
        this.chunks.add(chunk);
    }

    public List<Chunk> getChunks() {
        return chunks;
    }
}
