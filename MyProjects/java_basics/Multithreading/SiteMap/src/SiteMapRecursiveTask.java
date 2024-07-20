import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveAction;

public class SiteMapRecursiveTask extends RecursiveAction {
    private NodeSite nodeSite;
    private static CopyOnWriteArrayList allLinks;
    public SiteMapRecursiveTask(NodeSite nodeSite) {
        this.nodeSite = nodeSite;
        allLinks = new CopyOnWriteArrayList();
    }

    @Override
    protected void compute() {
        allLinks.add(nodeSite.getUrl());
        ConcurrentSkipListSet<String> links = WebPageParsing.getLinks(nodeSite.getUrl());
        for (String link : links) {
            if (!allLinks.contains(link)) {
                allLinks.add(link);
                nodeSite.addNodeChild(new NodeSite(link));
            }
        }
        List<SiteMapRecursiveTask> taskList = new ArrayList<>();
        for (NodeSite child : nodeSite.getNodeChildren()) {
            SiteMapRecursiveTask task = new SiteMapRecursiveTask(child);
            task.fork();
            taskList.add(task);
        }
        for (SiteMapRecursiveTask task : taskList) {
            task.join();
        }
    }
}