import java.util.concurrent.CopyOnWriteArrayList;
public class NodeSite {
    private String url;
    private CopyOnWriteArrayList<NodeSite> nodeChildren;

    public NodeSite(String url) {
        this.url = url;
        nodeChildren = new CopyOnWriteArrayList<>();
    }
    public void addNodeChild(NodeSite child) {
        nodeChildren.add(child);
    }
    public CopyOnWriteArrayList<NodeSite> getNodeChildren() {
        return nodeChildren;
    }
    public String getUrl() {
        return url;
    }
}