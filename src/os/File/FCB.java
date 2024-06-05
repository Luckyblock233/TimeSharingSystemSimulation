package os.File;

import java.util.ArrayList;
import java.util.Objects;

public class FCB implements Comparable<FCB>{
    private int Fid;
    private String name;
    private String path;
    private int size;
    private String type;
    private String datetime;
    private int[] directIndex = new int[10];
    private int[] firstLevelIndex = new int[100];
    private int[][] secondLevelIndex = new int[100][100];
    FCB next;
    FCB child;

    FCB(int Fid, String name, String path, int size, String type, String datetime) {
        this.Fid = Fid;
        this.name = name;
        this.path = path;
        this.size = size;
        this.type = type;
        this.datetime = datetime;
    }
    FCB() {

    }
    public int getFid() { return Fid; }
    public String getName() { return this.name; }
    public String getPath() { return this.path; }
    public int getSize() { return this.size; }
    public String getType() { return this.type; }
    public String getDatetime() { return this.datetime; }
    public FCB getNext() { return this.next; }
    public FCB getChild() { return this.child; }

    public void setFid(int Fid) { this.Fid = Fid; }
    public void setName(String name) { this.name = name; }
    public void setPath(String path) { this.path = path; }
    public void setSize(int size) { this.size = size; }
    public void setType(String type) { this.type = type; }
    public void setDatetime(String datetime) { this.datetime = datetime; }
    public void setNext(FCB next) { this.next = next; }
    public void setChild(FCB child) { this.child = child; }

    public boolean equels(FCB o) {
        return Objects.equals(this.path, o.path) && Objects.equals(this.name, o.name);
    }
    public int compareTo(FCB o) {
        return this.getFullDir().compareTo(o.getFullDir());
    }

    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer();
        for (int i = 0, len = this.path.length(); i < len; ++ i) {
            if (this.path.charAt(i) == '/') ret.append("  ");
        }
        return ret.append(this.path).append("/").append(this.name).toString();
    }

    public String getFullDir() {
        return this.path + '/' + this.name;
    }
    public void setIndex(int index, int storagePosition) {
        if (index < 10) {
            directIndex[index] = storagePosition;
        } else if (index < 110) {
            firstLevelIndex[index - 10] = storagePosition;
        } else {
            int i = (index - 110) / 100, j = (index - 110) % 100;
            secondLevelIndex[i][j] = storagePosition;
        }
    }
    public String getIndexString() {
        StringBuffer ret = new StringBuffer();
        for (int i: directIndex) {
            if (i == -1) return ret.toString();
            ret.append(String.valueOf(i)).append(' ');
        }
        for (int i: firstLevelIndex) {
            if (i == -1) return ret.toString();
            ret.append(String.valueOf(i)).append(' ');
        }
        for (int[] i: secondLevelIndex) {
            for (int j: i) {
                if (j == -1) return ret.toString();
                ret.append(String.valueOf(j)).append(' ');
            }
        }
        return ret.toString();
    }
}
