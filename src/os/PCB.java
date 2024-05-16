
package os;
public class PCB implements Comparable<PCB>{
    private int Pid;   // 进程名
    private int RequiredTime;  // 运行时间
    private int Priority;  // 优先级
    private int Memory;  // 内存大小
    private int ComeTime; // 进入CPU时间
    private  int cpu;//进入的cpu序号

    // 初始化，这里初始化ComeTime = 0
    public PCB(int pid, int RequiredTime, int Priority, int Memory){
        this.Pid = pid;
        this.RequiredTime = RequiredTime;
        this.Priority = Priority;
        this.Memory = Memory;
        this.ComeTime = -1;
        this.cpu=-1;
    }

    public int getPid() {
        return Pid;
    }
    public int getRequiredTime() {
        return RequiredTime;
    }
    public int getPriority() {
        return Priority;
    }
    public int getMemory(){
        return Memory;
    }
    public int getCpu() {
        return cpu;
    }
    public void setComeTime(int time){
        this.ComeTime = time;
    }
    public void setCpu(int cpu){
        this.cpu = cpu;
    }
    public void setRequiredTime(int time){
        this.RequiredTime = time;
    }
    public void setPriority(int Priority){
        this.Priority = Priority;
    }

    // 比较两进程是否相等
    public boolean equals(PCB other){
        return other.getPid() == this.Pid;
    }

    // 返回字符形式
    @Override
    public String toString() {
        return Pid + " " + RequiredTime + " " + Priority +  " " + Memory;
    }

    @Override
    public int compareTo(PCB o) {
        return o.getPriority() - this.Priority; // 降序排列
    }
}