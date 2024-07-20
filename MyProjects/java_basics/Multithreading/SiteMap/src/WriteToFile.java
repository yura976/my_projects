import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;

public class WriteToFile {
    private NodeSite nodeSite;
    private String filePath;
    private final String TABULATION = "\t";
    private final String SPACE = "\n";
    public WriteToFile(NodeSite nodeSite, String filePath) {
        this.nodeSite = nodeSite;
        this.filePath = filePath;
    }
    public void writeFile() {
        try {
            FileOutputStream stream = new FileOutputStream(filePath);
            String mapFile = createMapString(nodeSite, 0);
            stream.write(mapFile.getBytes());
            stream.flush();
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String createMapString(NodeSite nodeSite, int indent) {
        String tab = String.join("", Collections.nCopies(indent, TABULATION));
        StringBuilder finalFile = new StringBuilder(tab + nodeSite.getUrl());
        nodeSite.getNodeChildren().forEach(child -> finalFile
                .append(SPACE).append(createMapString(child, indent + 1)));
        return finalFile.toString();
    }
}