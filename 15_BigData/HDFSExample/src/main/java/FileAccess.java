import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileAccess {
    private Configuration configuration;
    private String rootPath;

    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param rootPath - the path to the root of HDFS,
     *                 for example, hdfs://localhost:32771
     */
    public FileAccess(String rootPath) {
        this.rootPath = rootPath;
        getConfigHadoop();
    }

    private void getConfigHadoop() {
        configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        configuration.set("dfs.support.append", "true");
        System.setProperty("HADOOP_USER_NAME", "root");
    }

    /**
     * Creates empty file or directory
     *
     * @param path
     */
    public void create(String path) throws Exception {
        Path file = new Path(rootPath + "/" + path);
        try (FileSystem hdfs = getConnection()) {
            if (hdfs.exists(file)) {
                return;
            }
            hdfs.create(file);
        }
    }

    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void append(String path, String content) throws Exception {
        Path file = new Path(rootPath + path);
        try (FileSystem hdfs = getConnection();
             FSDataOutputStream fsdops = hdfs.append(file)) {
            fsdops.writeBytes(content);
        }
    }

    public void appendFile(String path, String content) throws Exception {
        Path file = new Path(rootPath + path);
        Path way = new Path(content);
        try (FileSystem hdfs = getConnection()) {
            hdfs.copyFromLocalFile(way, file);
        }
    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(String path) throws Exception {
        Path file = new Path(rootPath + path);
        String result = "";
        try (FileSystem hdfs = getConnection();
             FSDataInputStream is = hdfs.open(file)) {
            result = IOUtils.toString(is, StandardCharsets.UTF_8);
        }
        return result;
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(String path) throws Exception {
        Path file = new Path(rootPath + path);
        try (FileSystem hdfs = getConnection()) {
            hdfs.delete(file, true);
        }
    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public boolean isDirectory(String path) throws Exception {
        Path file = new Path(rootPath + path);
        try (FileSystem hdfs = getConnection()) {
            return hdfs.getFileStatus(file).isDirectory();
        }
    }


    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list(String path) throws Exception {
        Path file = new Path(rootPath + path);
        try (FileSystem hdfs = getConnection()) {
            List<String> listPath = new ArrayList<>();
            getList(file, hdfs, listPath);
            return listPath;
        }
    }

    private FileSystem getConnection() throws Exception {
        return FileSystem.get(new URI(rootPath), configuration);
    }

    private void getList(Path path, FileSystem hdfs, List<String> listPath) throws Exception {
        FileStatus[] fileStatus = hdfs.listStatus(path);
        for (FileStatus file : fileStatus) {
            if (!file.isDirectory()) {
                listPath.add(file.getPath().toString());
            } else {
                getList(file.getPath(), hdfs, listPath);
            }
        }
    }
}
